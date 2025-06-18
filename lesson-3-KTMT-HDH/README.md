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
