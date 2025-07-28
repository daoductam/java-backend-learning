# Buổi 10: Codebase structure
## Cơ bản
### 1 số khái niệm
| Khái niệm            | Giải thích ngắn gọn                            |
| -------------------- | ---------------------------------------------- |
| **Monolithic**       | App chạy chung 1 project, 1 DB, 1 codebase     |
| **Microservice**     | Tách app thành nhiều service độc lập           |
| **Modular Monolith** | Monolith nhưng tách module rõ ràng theo domain |
### Monolithic Architecture (Mô hình tập trung)
- Monolithic architecture là mô hình truyền thống: toàn bộ ứng dụng là một khối thống nhất, độc lập với ứng dụng khác. 
#### Ưu
- Triển khai dễ dàng – một tệp thực thi hoặc thư mục duy nhất.
- Phát triển đơn giản – một codebase duy nhất.
- Hiệu suất tốt – Monolithic thường sử dụng gọi hàm nội bộ nên nhanh hơn kiểu microservices (không cần qua mạng).
- Kiểm thử dễ dàng – toàn bộ ứng dụng là một khối thống nhất.
- Debug dễ – mã nằm ở một nơi, dễ lần dấu lỗi.
#### Nhược
- Chậm phát triển khi hệ thống lớn lên do nhiều người cùng sửa một codebase 
→ Dễ sinh xung đột git, khó review.
    - Dù chỉ sửa 1 dòng code, bạn vẫn phải build lại toàn bộ app → lâu.
    - Vì mọi thứ nằm chung một app → khó giao hẳn 1 phần cho team tự vận hành.
- Khó mở rộng từng phần riêng lẻ.
    - Các module gọi lẫn nhau trực tiếp, nếu không thiết kế tốt → thay đổi một chỗ kéo theo nhiều chỗ.
- Dễ sụp đổ toàn hệ thống nếu một phần bị lỗi.
- Khó thay đổi công nghệ, mọi thay đổi ảnh hưởng toàn ứng dụng.
- Không linh hoạt, phụ thuộc công nghệ đã sử dụng.
- Cập nhật tốn công – sửa một chút cũng phải build lại toàn bộ.
### Microservices
- Microservices là kiến trúc gồm nhiều dịch vụ nhỏ, độc lập, mỗi dịch vụ xử lý một nghiệp vụ riêng, có cơ sở dữ liệu và logic riêng. Việc cập nhật, kiểm thử, triển khai và mở rộng diễn ra trong từng service riêng biệt.
#### Ưu 
- Linh hoạt và nhanh nhạy – đội nhỏ triển khai thường xuyên.
- Mở rộng linh hoạt – scale từng service riêng biệt.
- Triển khai liên tục (CI/CD) – cập nhật nhanh hơn, vài lần/ngày.
- Dễ bảo trì, dễ test – rollback khi lỗi, cách ly lỗi từng service.
- Triển khai độc lập – không ảnh hưởng toàn hệ thống.
- Tự do công nghệ – mỗi service có thể dùng tech stack riêng.
- Độ tin cậy cao – lỗi ở một service không làm sập toàn hệ thống.
- Đội ngũ hạnh phúc hơn – tự chủ, không phải chờ phê duyệt hàng tuần.
#### Nhược
- Phát triển phân tán quá mức – gây chậm trễ, khó vận hành.
- Chi phí hạ tầng tăng cao – mỗi service cần test, deploy, giám sát riêng.
- Tăng gánh nặng tổ chức – cần phối hợp và đồng bộ nhiều hơn.
- Debug phức tạp hơn – log tản mát, xử lý phân tán qua nhiều máy.
- Thiếu tiêu chuẩn chung – mỗi team dùng ngôn ngữ, logging khác nhau.
- Khó xác định ownership – nhiều service, nhiều team, khó quản lý tài nguyên.

## Chi tiết
### CẤU TRÚC CODEBASE MONOLITHIC
- Cấu trúc phổ biến:
src/
├── controller/
├── service/
├── repository/
├── dto/
├── model/
├── config/
├── exception/
├── utils/
├── interceptor/
├── filter/
### MODULAR MONOLITH – PHÂN MODULE
- Tách thư mục theo tính năng chứ không chỉ theo layer
- VD:
src/
├── order/
│   ├── controller/
│   ├── service/
│   ├── repo/
├── payment/
├── recommend/
### MICROSERVICE
- Mỗi tính năng lớn là 1 service riêng biệt: order-service, payment-service, recommend-service.... Giao tiếp qua HTTP, RabbitMQ, Kafka, v.v.
order-service/
├── controller/
├── service/
├── repo/
├── application.yml
payment-service/
recommend-service/






