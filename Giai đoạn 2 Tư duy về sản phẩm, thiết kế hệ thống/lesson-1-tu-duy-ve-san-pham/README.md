# Buổi 3: Tư duy về sản phẩm
## 1. Mục tiêu
- Hiểu các đặc điểm của sản phẩm (MVP, scalable, performance, data sensitive) -> Từ đó hình dung việc trade-off khi lựa chọn phương án kỹ thuật (VD cần tối ưu về bảo mật thì sẽ tốn thời gian và chi phí hơn, còn nếu muốn làm nhanh bản MVP thì đổi lại hệ thống sẽ ko làm những luồng ko phải happy case)
- Có thể mapping các thông tin từ những business requirement để thiết kế DB/API
- Nắm được cách phân tích và đọc business requirement
- Biết cách vẽ user flow diagram
## 2. Các thuật ngữ
### 2.1 MVP
* Khái niệm:
- Minimum Viable Product (Sản phẩm khả dụng tối thiểu) là một sản phẩm có đủ tính năng cần thiết để thu hút nhóm người dùng đầu tiên (early adopters) và xác thực ý tưởng sản phẩm ngay từ giai đoạn đầu trong vòng đời phát triển sản phẩm.
- Trong các ngành như phần mềm, MVP giúp nhóm sản phẩm nhận được phản hồi từ người dùng càng sớm càng tốt để từ đó cải tiến sản phẩm liên tục.
- Bởi vì phương pháp Agile dựa trên việc xác thực và cải tiến sản phẩm dựa trên phản hồi người dùng, MVP giữ vai trò trung tâm trong quy trình phát triển Agile.
* Mục đích: MVP là phiên bản của một sản phẩm mới cho phép nhóm phát triển thu thập được lượng kiến thức xác thực tối đa về khách hàng với nỗ lực tối thiểu.
* Đặc điểm: 
    * Tiết kiệm chi phí
    * Ra thị trường nhanh
    * Xác thực ý tưởng
    * Tập trung vào người dùng
    * Thu hút nhà đầu tư
* Trade-off kỹ thuật ví dụ:
    Nếu muốn sản phẩm làm nhanh thì bỏ qua các luồng phụ như retry, validate nâng cao -> Rủi ro lỗi user input, UX chưa tối ưu
* Link tham khảo: 
    * https://www.productplan.com/glossary/minimum-viable-product/
    * https://www.netsuite.com/portal/resource/articles/erp/minimum-viable-product-mvp.shtml   

### 2.2 Scalability (Khả năng mở rộng) 
* Khái niệm: 
- Gartner định nghĩa khả năng mở rộng là “mức độ một hệ thống có thể tăng hoặc giảm hiệu suất và chi phí để đáp ứng với biến động về nhu cầu xử lý”.
- Hiểu đơn giản, đó là khả năng phần mềm xử lý tải thay đổi (nhiều hay ít người dùng hơn) mà không làm tăng chi phí quá mức hoặc gây gián đoạn. 
* Phân loại:
    * Mở rộng ngang (Horizontal scaling – Scaling out): thêm nhiều máy chủ/nút để chia tải
        Ví dụ: khi ứng dụng bị trễ thì thêm server thứ hai để xử lý.
        - Ưu: Kháng lỗi cao, Không downtime khi mở rộng, Có thể mở rộng gần như không giới hạn
        - Nhược: Phức tạp, Chi phí cao hơn, Tốc độ toàn hệ thống phụ thuộc vào tốc độ liên lạc giữa các máy
    * Mở rộng dọc (Vertical scaling – Scaling up)
        Thay vì thêm máy thì nâng cấp phần cứng hiện tại (RAM, CPU, SSD mạnh hơn...)
        - Ưu: Không cần thay đổi cấu hình hoặc logic ứng dụng, Chi phí thấp hơn
        - Nhược: dừng hệ thống khi nâng cấp, Single point of failure (nếu máy hỏng thì chết cả hệ), không thể nâng cấp mãi
* Đặc điểm: 
    * Quản lý tăng trưởng
    * Tăng hiệu suất
    * Đảm bảo tính sẵn sàng
    * Tối ưu chi phí
    * Khuyến khích đổi mới
* 8 mẹo:
    * Chạy trên Cloud (thay vì on-premise)
    * Dùng Load Balancer
    * Dùng Cache nhiều nhất có thể
    * Triển khai qua API
    * Xử lý bất đồng bộ (Async processing)
    * Chọn CSDL dễ mở rộng
    * Ưu tiên kiến trúc Microservices
    * Giám sát hiệu suất để biết khi nào cần mở rộng
* Trade-off kỹ thuật ví dụ:
    Nếu muốn sản phẩm xử lý đồng thời cao thì phải dùng load balancer, xử lý async -> Phải code nhiều luồng, quản lý lỗi khó hơn
* Link tham khảo: 
    * https://itrexgroup.com/blog/what-is-software-scalability/
    * https://www.geeksforgeeks.org/what-is-scalability/
### 2.3 Performance(Hiệu năng)
* Khái niệm:
- Hiệu năng được thể hiện qua:
    * Thời gian phản hồi (Response Time): Thời gian ứng dụng phản hồi yêu cầu từ người dùng.
    * Thông lượng (Throughput): Số lượng yêu cầu được xử lý trong một đơn vị thời gian.
    * Độ trễ (Latency): Độ trễ giữa khi gửi yêu cầu và nhận được phản hồi.
    * Tỷ lệ lỗi (Error Rate): Tần suất lỗi xảy ra.
    * Mức sử dụng tài nguyên (Resource Utilization): Hiệu quả sử dụng CPU, RAM, ổ đĩa...
* Mục đích
    Tối ưu hiệu năng để ứng dụng chạy nhanh nhất có thể, tiêu tốn ít tài nguyên như CPU, RAM, băng thông, tăng tốc độ, phản hồi và UX tổng thể, giảm downtime, lỗi kỹ thuật, tăng khả năng mở rộng và giảm chi phí vận hành
* Nhận diện điểm nghẽn (bottlenecks)
    * Tài nguyên yếu hoặc cấu hình sai
    * Mã không tối ưu: ví dụ 5 truy vấn DB thay vì gộp 1 truy vấn
    * Kiến trúc sai lệch: ví dụ mobile app gửi quá nhiều request khi đăng nhập
    * Cấu hình môi trường kém: server, router, network...
* Cách cách tối ưu
    * Tối ưu ở cấp độ mã nguồn: dùng thuật toán & cấu trúc dữ liệu phù hợp, loại bỏ lặp lại, dùng giải pháp nhanh hơn, Lập trình bất đồng bộ, Tăng scalability và giảm tải cho tài nguyên hệ thống
    * Tối ưu cơ sở dữ liệu: tối ưu truy vấn SQL, tạo chỉ mục (indexing) phù hợp, Caching kết quả truy vấn lặp lại, chia nhỏ DB trên nhiều server
    * Tối ưu giao diện (frontend): nén, rút gọn file JS/CSS/HTML, tải nội dung khi cần, Giảm số request HTTP, Dùng CDN để giảm latency, tăng tốc độ tải
    * Tối ưu backend và mạng: Load balancing để chia tải, dùng kiến trúc microservices, tối ưu buffer, thread, cài đặt hệ thống
* Trade-off kỹ thuật ví dụ:
    Nếu muốn sản phẩm tốc độ phản hồi < 500ms thì Cache, chỉ trả dữ liệu cần thiết, index DB tốt -> Mất thêm công tối ưu, khó maintain
* Link tham khảo: 
    * https://dev.to/adityabhuyan/optimizing-application-performance-tools-techniques-and-best-practices-45nm
    * https://senlainc.com/blog/performance-optimization-in-software-development/
### 2.4 Data sensitive
* Khái niệm:
    * Dữ liệu nhạy cảm (Sensitive Data) là dữ liệu cá nhân hoặc bí mật mà nếu bị tiết lộ, đánh cắp, sử dụng sai mục đích sẽ gây tổn hại cho người dùng hoặc tổ chức.
* Đặc điểm:
    * Phân tích requirement -> Phải xác định dữ liệu nào là nhạy cảm để bảo vệ
    * Thiết kế user flow -> Xác định luồng dữ liệu nào có nguy cơ lộ lọt
    * Mapping sang DB/API -> Cần xác định trường dữ liệu nào cần mã hóa hoặc che giấu
    * MVP hay Scalable -> Nếu sản phẩm cần scale lớn → dữ liệu càng cần được bảo vệ kỹ càng hơn
    * Tuân thủ pháp lý -> Nếu có người dùng ở EU → phải tuân thủ GDPR, ở California → CCPA
* Trade-off kỹ thuật ví dụ:
    Nếu muốn thông tin không bị tấn công thì Xác thực mạnh, rate limit, kiểm tra input -> Có thể ảnh hưởng tới UX (nhiều bước xác thực)

## 3. Phân tích tính năng: Thêm sản phẩm vào giỏ hàng của Shopee
### 3.1 Câu hỏi
* Người dùng expect điều gì?
- Việc thêm hàng phải nhanh, hiển thị phản hồi rõ ràng.
- Sản phẩm đã thêm phải hiển thị trong giỏ hàng.
- Không bị trùng sản phẩm nếu đã thêm.
- Khi quay lại, giỏ hàng vẫn còn sản phẩm.

* Nếu sản phẩm trong giỏ hết hàng thì xử lý sao?
- Giữ sản phẩm trong giỏ, đánh dấu "hết hàng".
- Cấm đặt hàng nếu sản phẩm đã hết.

* Nếu user chưa đăng nhập, có được cho sản phẩm vào giỏ không?
- Không, sẽ được chuyển về màn hình đăng nhập

* Nếu người dùng cố tình gửi 1 loạt yêu cầu thêm 1 sản phẩm vào giỏ hàng? 
- Có thể hiển thị lỗi hoặc không phản hồi
- Số lượng bị cộng dồn không kiểm soát
- Hệ thống có thể chặn tạm chức năng do phát hiện hành vi bất thường
- Gánh nặng hệ thống (CPU, DB).

* Khi thêm sản phẩm vào bị lỗi (Ví dụ do product ko tồn tại trên hệ thống) thì phản hồi như nào cho KH?
- Thông báo lỗi rõ ràng và dễ hiểu như "Sản phẩm này hiện không tồn tại" lên màn hình

* Nếu mạng chậm, điều gì xảy ra? Có giải pháp?
- Request gửi đi nhưng phản hồi chậm → user không biết đã thêm chưa.
- Giải pháp: Dùng optimistic UI để hiển thị sản phẩm đã thêm ngay, nếu lỗi thì rollback.

* Nếu backend chậm, ảnh hưởng user thế nào?
- Giỏ hàng phản hồi chậm → UX tệ, dễ click lại → trùng.
- Giải pháp: Cache tồn kho/sp info, Tối ưu query backend.

### 3.2 Thiết kế Requirements cho tính năng "Khách hàng thêm sản phẩm vào giỏ hàng"
* Functional Requirements (Yêu cầu chức năng)

| Mã  | Mô tả                                                               |
| --- | ------------------------------------------------------------------- |
| FR1 | User có thể thêm sản phẩm vào giỏ qua nút “Thêm vào giỏ”            |
| FR2 | Nếu chưa đăng nhập → chuyển sang trang đăng nhập                    |
| FR3 | Khi login → chuyển sang trang chi tiết sản phẩm đã chọn             |
| FR4 | Nếu sản phẩm đã tồn tại → chỉ tăng số lượng, không tạo mới          |
| FR5 | Nếu sản phẩm không tồn tại → trả lỗi API trả lỗi 404                |
| FR7 | Sau khi thêm thành công, hệ thống cập nhật lại icon giỏ hàng, và hiển thị thông báo “Thêm thành công”. |

* Non-functional Requirements (NFR)

| Mã   | Mô tả                                                        |
| ---- | ------------------------------------------------------------ |
| NFR1 | Tốc độ phản hồi API ≤ 300ms (95th percentile)                |
| NFR2 | Hệ thống chịu tải tối thiểu 1000 req/s (scalable)            |
| NFR3 | API đảm bảo **idempotent** (ngăn thêm trùng)                 |
| NFR4 | Giỏ hàng được lưu cache tối ưu (Redis/memory/localStorage)   |
| NFR5 | Tương thích cả mobile app và web                             |
| NFR6 | Xử lý trơn tru trong trường hợp mạng yếu (offline/slow mode) |

* User Flow Diagram: .\Giai đoạn 2 Tư duy về sản phẩm, thiết kế hệ thống\lesson-1-tu-duy-ve-san-pham\add-product.jpg