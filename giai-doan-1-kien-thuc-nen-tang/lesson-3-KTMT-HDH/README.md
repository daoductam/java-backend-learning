# Buổi 3: Kiến trúc máy tính và HDH

## 1. Mục tiêu
* Nắm được các khái niệm:
    * Process & Thread
    * Blocking và Non-blocking I/O
    * Concurrency & Parallelism
    * CPU Cache
    * Memory Leak

## 2. Các thuật ngữ
### 2.1 Process & Thread

|           | Process                              | Thread                            |
| --------- | ------------------------------------ | --------------------------------- |
| Khái niệm | Tiến trình là một chương trình đang  | Luồng là một thành phần con       |
|           | được thực thi (đang chạy)            | của tiến trình. Một tiến trình có |
|           |                                      | thể chạy đồng thời nhiều luồng    |
| Bộ nhớ    | Các process là độc lập, không chia   | Các threads chia sẻ không gian    |
|           | sẻ không gian nhớ với nhau           | nhớ                               |
| Khởi tạo  | Cần nhiều system call để tạo nhiều   | Có thể tạo nhiều threads với      |
|           | process, tốn nhiều thời gian hơn     | một system call, tốn ít thời gian |
|           |                                      | hơn                               |
| Chấm dứt  | Tốn thời gian hơn so với thread      | Tốn ít thời gian hơn so với       |
|           |                                      | process                           |
| Giao tiếp | Cần sử dụng IPC, tốn kém hơn         | Ít tốn kém hơn so với process     |
| Context   | Chậm hơn threads                     | Nhanh hơn nhiều so với process    |
| switching |                                      |                                   |

* 2 loại process: Single-threaded Process và Multi-threaded Process
* Khi nào dùng?

| Tình huống                                     | Nên dùng gì? | Lý do                                                                 |
|------------------------------------------------|--------------|-----------------------------------------------------------------------|
| Chạy các chương trình riêng biệt, độc lập      | Process      | Độc lập, an toàn, dễ kiểm soát lỗi và tài nguyên.                     |
| Làm nhiều việc một lúc trong cùng chương trình | Thread       | Nhẹ hơn, chia sẻ bộ nhớ, tăng tốc độ phản hồi và hiệu năng.           |
| Tách biệt xử lý nặng (AI, phân tích...)        | Process      | Tránh crash ảnh hưởng phần còn lại, dễ scale hoặc restart riêng biệt. |
| Tải dữ liệu, gửi API nền, không chặn giao diện | Thread       | Giúp chương trình mượt mà, không bị đứng.                             |

* Làm sao để OS chạy 100 tiến trình đồng thời nếu chỉ có 1 CPU?
    * OS xử lý bằng cách ảo hóa CPU. Ví dụ: Thực thi 1 process, tạm dừng nó và chạy 1 process khác...
    * Khái niệm này gọi là CPU time-sharing (time-slicing), giúp cho người dùng chạy nhiều process.
    * Khả năng dừng 1 process và chạy process khác gọi là context switching.
* Các trạng thái của Process:
    * Running: process đang được chạy trên môt bộ xử lý và đang thực thi các câu lệnh
    * Ready: process đã sẵn sàng để thực thi, nhưng hệ điều hành (OS) chưa quyết định chạy lại tại thời điểm này
    * Blocked: process hoàn thành 1 số hành động mà khiến nó không thể chạy cho đến khi một sự kiện xảy ra.
        Ví dụ: process yêu cầu I/O tới một đĩa, nó sẽ bị block. Việc này cho phép 1 process khác được sử dụng CPU

### 2.2 Blocking vs Non-blocking I/O
* Blocking I/O: Là mô hình lập trình mà trong đó, một thao tác (thường là I/O) sẽ chặn luồng thực thi hiện tại cho đến khi thao tác đó hoàn tất.
* Non-blocking I/O: Là mô hình lập trình trong đó thao tác không chặn luồng thực thi hiện tại. Thay vì đợi, chương trình tiếp tục xử lý các công việc khác. Khi thao tác hoàn tất, callback hoặc future/promise sẽ nhận kết quả.
* Cách sử dụng
    * Blocking
        * Lập trình tuần tự, dễ hiểu, dễ viết.
        * Gọi một hàm → Chờ kết quả trả về → Xử lý tiếp.
        * Dễ gây nghẽn cổ chai nếu thao tác mất thời gian dài (như I/O hoặc request mạng).
        * Mặc định trong hầu hết các ngôn ngữ truyền thống (Java, C, Python...).
    * Non-Blocking
        * Không chờ thao tác hoàn tất → tiếp tục xử lý ngay các phần khác.
        * Dữ liệu sẽ được xử lý sau thông qua callback, future, hoặc cơ chế tương tự.
        * Sử dụng thư viện, API hỗ trợ 
* Vì sao cần Non-Blocking?
Ngày xưa máy tính chỉ có 1 lõi, làm từng việc một (Blocking là hợp lý).
Nay máy tính có nhiều lõi, nhiều luồng, có thể làm nhiều việc cùng lúc ⇒ Nếu cứ chờ đợi thì lãng phí tài nguyên.
==> Non-Blocking giúp:
    Tối ưu hiệu năng
    Tăng tốc độ xử lý
    Tránh “chết đứng” khi chờ I/O (đọc file, kết nối mạng…)
*  Khi nào dùng cái nào?

| Trường hợp                                     | Nên dùng                                  |
| ---------------------------------------------- | ----------------------------------------- |
| Ứng dụng nhỏ, đơn luồng, logic đơn giản        | **Blocking** để dễ phát triển, dễ debug   |
| Xử lý file nhỏ, không quan trọng hiệu năng     | **Blocking** hoàn toàn chấp nhận được     |
| Ứng dụng xử lý **nhiều request cùng lúc**      | **Non-Blocking** để tận dụng CPU hiệu quả |
| App cần phản hồi nhanh (chat, game, real-time) | **Non-Blocking** để giảm độ trễ tối đa    |
| Xử lý trên server nhiều kết nối, nhiều I/O     | **Non-Blocking** là giải pháp tốt nhất    |

* Link tham khảo: https://codersontrang.wordpress.com/2017/09/05/blocking-va-non-blocking-trong-lap-trinh/

### 2.3 Concurrency & Parallelism
* Khái niệm

| Thuộc tính     | **Concurrency (Đồng thời)**                                                                          | **Parallelism (Song song)**                                                                |
| -------------- | ---------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------ |
| **Định nghĩa** | Là khả năng xử lý **nhiều tác vụ gần như cùng lúc** bằng cách **chia nhỏ và xen kẽ thời gian xử lý** | Là khả năng **thực hiện nhiều tác vụ thật sự cùng lúc** trên nhiều CPU hoặc lõi (cores)    |
| **Cơ chế**     | Một CPU “giả vờ” làm nhiều việc cùng lúc bằng cách **chuyển ngữ cảnh (context switching)**           | Mỗi tác vụ chạy **đồng thời trên các CPU/lõi riêng biệt** mà **không cần chuyển ngữ cảnh** |
| **Điều kiện**  | Không cần nhiều lõi CPU                                                                              | Cần ít nhất **nhiều hơn 1 lõi CPU**                                                        |
| **Ví dụ**      | Máy chỉ có 1 bếp nấu, nhưng đầu bếp thay phiên xào, luộc, nêm, đảo từng món một cách luân phiên      | Có 4 bếp và 4 đầu bếp, mỗi người nấu 1 món riêng biệt cùng lúc                             |

* Cách sử dụng
Concurrency:
    Dùng multi-threading (nhiều luồng trên một tiến trình)
    Dùng async/await, hoặc event loop (như Node.js)
    Dùng trong lập trình I/O-bound: chờ đọc/ghi file, mạng, cơ sở dữ liệu
Parallelism:
    Dùng cho CPU-bound tasks: xử lý thuật toán nặng, tính toán, AI
    Tận dụng đa lõi (multi-core) hoặc đa máy (cluster)
    Dùng thư viện như Java Fork/Join Framework, parallelStream()
* Lý do sử dụng

| Mục tiêu                       | Concurrency                                | Parallelism                                    |
| ------------------------------ | ------------------------------------------ | ---------------------------------------------- |
| ✅ Tăng hiệu suất tổng thể      | ✅ (Giảm thời gian chờ, tận dụng idle time) | ✅ (Chạy nhiều thứ cùng lúc thật sự)            |
| ✅ Phản hồi nhanh hơn (UI, API) | ✅ (Không block thread chính)               | 🚫 (Không ưu tiên phản hồi mà là tốc độ xử lý) |
| ✅ Tối ưu phần cứng đa nhân     | 🚫 (Dùng một nhân hiệu quả hơn)            | ✅ (Tận dụng nhiều nhân tối đa)                 |
| ✅ Quản lý nhiều kết nối mạng   | ✅ (Dùng I/O non-blocking)                  | 🚫 (Không liên quan I/O)                       |

* Dùng khi nào ?

| Tình huống                                  | Nên dùng gì?    | Lý do                                                       |
| ------------------------------------------- | --------------- | ----------------------------------------------------------- |
| Xử lý đồng thời 1000 request API            | **Concurrency** | I/O nhiều → dùng async hoặc threadpool                      |
| Tính toán 1 bài toán lớn (machine learning) | **Parallelism** | CPU-bound → cần chia nhỏ và xử lý song song trên nhiều core |
| Giao diện người dùng (UI responsive)        | **Concurrency** | Không để UI block → chạy background thread                  |
| Streaming video/data                        | **Concurrency** | Luồng dữ liệu liên tục → cần xử lý và đệm I/O đồng thời     |
| Render ảnh hoặc video hàng loạt             | **Parallelism** | Chia việc render thành nhiều phần → render song song        |

* So sánh

|                        | **Concurrency**               | **Parallelism**           |
| ---------------------- | ----------------------------- | ------------------------- |
| Chạy thực sự cùng lúc? | ❌ (xen kẽ)                    | ✅ (đồng thời)             |
| Tối ưu khi?            | I/O-bound, nhiều tác vụ       | CPU-bound, tính toán nặng |
| Cần nhiều core?        | Không bắt buộc                | Có                        |
| Phức tạp?              | Quản lý thread, sync phức tạp | Dễ hơn nếu chia tốt       |

* Link tham khảo: 
    * https://viblo.asia/p/phan-biet-concurrency-va-parallelism-trong-lap-trinh-gAm5y8PVldb
    * https://viblo.asia/p/phan-biet-khai-niem-xu-ly-concurrency-dong-thoi-va-parallelism-song-song-4P856nBO5Y3

### 2.4 Cache CPU
* Khái niệm: CPU cache là bộ nhớ đệm siêu nhanh nằm bên trong hoặc rất gần CPU, dùng để lưu trữ dữ liệu và lệnh mà CPU truy cập thường xuyên. Gồm 3 cấp chính: L1(Gần core nhất, nhanh nhất), L2(Xa hơn L1, dung lượng lớn hơn), L3(Dùng chung giữa nhiều core)
CPU sẽ tìm dữ liệu theo thứ tự: L1 → L2 → L3 → RAM
=> Nếu Cache Hit (tìm thấy), tốc độ xử lý rất nhanh
=> Nếu Cache Miss (không thấy), phải đi xa hơn → chậm

* Cách hoạt động: Fetch(Lấy dũ liệu và chỉ dẫn/lệnh tính toán) -> Decode(Giải mã lệnh để biết làm gì) -> Excute(Thực thi lệnh và phép tính) -> Store(Lưu trữ kết quả của phép tính)

* Lý do dùng: 

| Vấn đề nếu không có cache                | Giải pháp khi dùng CPU cache                      |
| ---------------------------------------- | ------------------------------------------------- |
| RAM truy xuất chậm hơn CPU hàng chục lần | Cache lưu lại dữ liệu quan trọng ngay cạnh CPU    |
| Lập trình hiệu năng thấp                 | Cache giúp tăng tốc các vòng lặp, truy cập bộ nhớ |
| CPU phải chờ RAM → giảm hiệu suất        | Cache giảm chờ đợi, tăng throughput hệ thống      |

* Dùng khi nào

| Ngữ cảnh                          | Cache có vai trò gì?                       |
| --------------------------------- | ------------------------------------------ |
| Backend xử lý nhiều request       | Tối ưu vòng lặp, biến local → giảm latency |
| Xử lý dữ liệu lớn (big array, AI) | Tránh cache miss → tăng hiệu năng          |
| Hệ thống real-time                | Đảm bảo tính ổn định, giảm jitter          |
| Viết code low-level (C, C++)      | Tối ưu alignment, locality, memory layout  |

* Link tham khảo: https://tinhte.vn/thread/cpu-cache-la-gi-cpu-cache-co-cong-dung-ra-sao.3341297/

### 2.5 Memory Leak
* Khái niệm: là hiện tượng bộ nhớ được cấp phát nhưng không được giải phóng, khiến bộ nhớ bị chiếm giữ mãi mãi — dù không còn dùng đến nữa. Hệ thống vẫn giữ tham chiếu đến dữ liệu không cần thiết ⇒ garbage collector (GC) hoặc hệ điều hành không thể thu hồi lại ⇒ bộ nhớ dùng ngày càng tăng ⇒ hết RAM, chậm, crash.

* 1 số tình huống
    * Dữ liệu không còn dùng nhưng vẫn còn trong `Map`, `List`, `cache`... 
    * Thread giữ tham chiếu ⇒ GC không thể dọn dẹp                         
    * Dữ liệu bị giữ lại bởi các listener chưa xóa                         
    * Biến tĩnh hoặc singleton giữ object lâu dài                          
    * Tạo quá nhiều object nhỏ ⇒ heap đầy dần                              

* Lý do cần biết: Ứng dụng dùng ngày càng nhiều RAM, Server chậm dần, lag, thậm chí crash, Ảnh hưởng người dùng cuối,Tốn chi phí hạ tầng

* Link tham khảo: https://viblo.asia/p/memory-leak-la-gi-mot-so-cach-de-tranh-memory-leak-924lJdYbKPM

## 3. Bài toán
## Nếu khi user gọi lấy danh sách hàng hoá của shopee mà shopee trả về tất cả mọi loại hàng hóa trên hệ thống của shopee cho user cùng 1 lúc, điều gì sẽ xảy ra? Shopee có thể xử lý hàng trăm ngàn request cùng lúc bằng cách nào ?

* Khi người dùng gọi API lấy danh sách hàng hóa, Shopee trả về toàn bộ hàng hóa trên hệ thống (hàng triệu sản phẩm) cho tất cả user cùng lúc thì 
    * Tốn băng thông cực lớn do gửi hàng triệu sản phẩm → Payload nặng hàng trăm MB → Network nghẽn
    * Tốn RAM để xử lý dữ liệu khổng lồ do server phải load toàn bộ data từ DB → memory bị chiếm nhiều (Memory Leak)
    * Phản hồi chậm hoặc timeout nếu xử lý Blocking I/O → request bị block → hết thread → treo hệ thống (Blocking I/O)
* Giải pháp:
    *  Chạy nhiều thread để xử lý nhiều request đồng thời
    *  Dữ liệu lấy theo cách non-blocking vì Non-blocking I/O → xử lý hàng trăm request mà không chặn thread
    * Dùng CPU cache, memory cache để không phải truy vấn DB nhiều lần, giảm thời gian phản hồi
    * Tối ưu code backend để tránh memory leak
    * Load balancer chia request ra nhiều server nhỏ (scale horizontal)
    * Concurrency & Parallelism để xử lý cùng lúc nhiều request: Tận dụng cả 2 multi-thread & multi-process
