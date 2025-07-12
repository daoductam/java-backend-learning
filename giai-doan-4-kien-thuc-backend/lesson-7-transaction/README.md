# Buổi 7: Transaction
# Mục tiêu:
- Hiểu khái niệm transaction trong CSDL.
- 4 tính chất ACID của transaction
- Hiểu tầm quan trọng của transaction trong hệ thống (VD: Shopee)
- Biết sử dụng transaction để đảm bảo toàn vẹn dữ liệu
- Cách 1 transaction hoạt động (commit, rollback)
- Deadlock, performance, locking
- Học annotation @Transactional trong Spring
# Lý thuyết 
## Khái niệm Transaction
- Transaction (giao dịch) trong cơ sở dữ liệu là một chuỗi các thao tác dữ liệu (thường là INSERT, UPDATE, DELETE) được nhóm lại và xử lý như một đơn vị công việc logic duy nhất.
    - Nếu toàn bộ các thao tác thành công → dữ liệu được ghi vĩnh viễn (COMMIT)
    - Nếu một thao tác thất bại → hệ thống phục hồi về trạng thái ban đầu (ROLLBACK)
## 4 tính chất ACID của transaction

| Tên                 | Mục tiêu                                     |
| ------------------- | -------------------------------------------- |
| **A** – Atomicity   | Hoặc thực hiện hết, hoặc không thực hiện gì  |
| **C** – Consistency | Dữ liệu luôn hợp lệ trước và sau transaction |
| **I** – Isolation   | Transaction không ảnh hưởng lẫn nhau         |
| **D** – Durability  | Dữ liệu sẽ không bị mất sau khi commit       |

-  1. Atomicity – Tính nguyên tử
    - Một transaction là một đơn vị không thể chia nhỏ.
    -  Nếu một thao tác lỗi → toàn bộ transaction bị huỷ (rollback).
    - Ví dụ: Thanh toán đơn hàng
        - Trừ tiền người mua
        - Giao dịch ngân hàng
        - Cập nhật đơn hàng thành “Đã thanh toán”
        -> Nếu lỗi bước giao dịch ngân hàng → rollback, không ai bị mất tiền hoặc đơn hàng lỗi.
-  2. Consistency – Tính nhất quán
    - Transaction đảm bảo dữ liệu chuyển từ trạng thái hợp lệ này sang hợp lệ khác.
    - Không làm hệ thống rơi vào trạng thái sai lệch.
    - Ví dụ: Số dư tổng trước và sau khi chuyển tiền phải bằng nhau
        - Trước: A: 5tr, B: 3tr	
        - Sau: A: 4tr, B: 4tr
        -> Nếu xảy ra lỗi, dữ liệu sẽ rollback → đảm bảo không bị sai logic, âm tiền, thiếu kho
- 3. Isolation – Tính độc lập
    - Các transaction đang chạy song song không làm ảnh hưởng kết quả lẫn nhau.
    - Tránh xung đột, race condition.
    - Ví dụ: 2 người cùng mua sản phẩm còn 1 cái
        - Nếu không cách ly → cả 2 người đều mua thành công → lỗi
        - Nếu có isolation → chỉ 1 người thành công, người kia báo “hết hàng”
- 4. Durability – Tính bền vững
    - Khi transaction đã commit, thì dữ liệu được lưu vĩnh viễn, không bị mất kể cả khi mất điện, crash server.
    - Cơ sở dữ liệu đảm bảo điều này bằng: Ghi log ra Write-Ahead Log (WAL) trước | Ghi đĩa cứng sau khi commit
    - Ví dụ:
        - Bạn đã chuyển khoản thành công
        - Dù hệ thống bị tắt máy sau đó, giao dịch vẫn tồn tại
## Tầm quan trọng của transaction trong hệ thống
- Transaction là “tấm khiên” bảo vệ tính đúng đắn của dữ liệu, nhất là trong các quy trình gồm nhiều bước logic phức tạp.
- Khi nào cần transaction?
    - Khi thao tác với nhiều bảng liên quan
    - Khi có nhiều thao tác liên tiếp cần thực hiện đồng bộ
    - Khi cần bảo vệ dữ liệu tránh sai lệch trong môi trường có nhiều người dùng đồng thời
- Ví dụ: Shopee
    - Người dùng bấm “Đặt hàng”
    - Hệ thống:
        - Kiểm tra tồn kho
        - Trừ kho
        - Tạo đơn hàng
        - Ghi nhận thanh toán
        - Cập nhật trạng thái đơn hàng
-> Đây là 1 chuỗi thao tác cần được thực hiện thành công toàn bộ → nếu bất kỳ bước nào lỗi, toàn bộ thao tác phải bị huỷ.

| Tình huống                    | Có Transaction           | Không có Transaction                      |
| ----------------------------- | ------------------------ | ----------------------------------------- |
| Đặt hàng → thanh toán lỗi     | Rollback, đơn không tạo  | Trừ kho, mất đơn, lỗi                     |
| Thanh toán → tạo đơn hàng lỗi | Rollback, không mất tiền | Người dùng bị trừ tiền nhưng không có đơn |
| Mất điện giữa chừng           | Không sao, chưa commit   | Dữ liệu dang dở, lỗi hệ thống             |

## Cách 1 transaction hoạt động (commit, rollback)
- START → EXECUTE → COMMIT (✔) hoặc ROLLBACK (❌) → END
- Khi nào commit
    - Khi tất cả các thao tác trong transaction đều thực hiện thành công
    - Khi không có exception
    - Khi hệ thống/ứng dụng gửi lệnh COMMIT
-> Lúc này dữ liệu sẽ được ghi vĩnh viễn vào DB (không thể quay lại)
- Khi nào rollback?
    - Khi có lỗi SQL (vi phạm ràng buộc, lỗi dữ liệu)
    - Khi ứng dụng ném exception
    - Khi bạn gọi lệnh ROLLBACK (thủ công hoặc tự động)
    - Khi hệ thống mất điện hoặc crash trước khi commit
-> Dữ liệu sẽ không thay đổi như chưa từng xảy ra giao dịch
## Deadlock, performance, locking
### 1. Locking – Cơ chế khóa dữ liệu
#### Pessimistic Locking
- tránh race condition bằng cách khóa các data đang được truy cập và
update
- đảm bảo ở 1 thời điểm, chỉ có 1 transaction có thể truy cập đến data,
transaction khác sẽ phải đợi
- MySQL:
    - SELECT * FROM Room  WHERE id = 123 FOR UPDATE;
    - COMMIT;
    - ROLLBACK;
#### Optimistic Locking
- Ngược lại với pessimistic locking, cho phép nhiều transaction truy cập và
update data
- Sau đó, sẽ kiểm tra conflict trước khi commit thay đổi.
- Nếu conflict xảy ra, thì sẽ rollback
- Một cách cài đặt đơn giản là thêm 1 cột “version” ở bảng Room. Sau mỗi
lần đặt sẽ tăng version này lên
- Sau khi tăng lên 1, thì transaction chạy đồng thời khác sẽ không update
được.
- MySQL:
    - UPDATE Room
    - SET available = FALSE, version = version + 1
    - WHERE id =123 AND version = 1
    - INSERT INTO BOOKING (room_id, start_date, end_date, version) values...
### 3. Deadlock – Khi 2 transaction chờ nhau mãi mãi
- Deadlock xảy ra khi 2 hoặc nhiều transaction đang chờ nhau giải phóng lock, nhưng không ai nhường ai → “kẹt xe vĩnh viễn”.
- Ví dụ deadlock:
    - T1: LOCK row A → chờ row B  
    - T2: LOCK row B → chờ row A
    → Cả 2 đứng mãi → DEADLOCK!
- Cách phòng tránh deadlock:
    - Luôn lock theo cùng thứ tự	
    - Giữ transaction ngắn gọn	
    - Dùng optimistic lock nếu phù hợp	
    - Đặt timeout cho transaction	
    - Sử dụng isolation level phù hợp	
### 4. Performance – Hiệu năng và Transaction
- Những điều làm giảm hiệu năng:
    - Transaction quá dài → giữ lock lâu
    - Ghi nhiều bảng cùng lúc
    - Isolation level cao → giảm song song
    - Deadlock xảy ra thường xuyên → rollback lặp lại → tốn tài nguyên
- Tối ưu performance khi dùng transaction:
    - Giữ transaction ngắn và rõ ràng	-> Giảm thời gian lock
    - Tránh thao tác IO hoặc API trong transaction	-> Không làm transaction bị treo
    - Chỉ @Transactional khi thật sự cần -> Giảm chi phí tạo rollback context
    - Chọn isolation level hợp lý (READ_COMMITTED)	-> Cân bằng giữa an toàn và hiệu năng
    - Log lại deadlock để phân tích	-> Giúp debug và tối ưu code

## Học annotation @Transactional trong Spring
- Cách sử dụng cơ bản:
@Service
public class OrderService {

    @Transactional
    public void placeOrder(Long userId, Long productId) {
        reduceStock(productId);
        createOrder(userId, productId);
        chargePayment(userId);
    }
}
- Nếu chargePayment() ném lỗi → toàn bộ các bước phía trên sẽ bị rollback.
- Gặp Checked Exception (ví dụ IOException)	 -> ko roll back -> Tự cấu hình @Transactional(rollbackFor = IOException.class)
- Một số thuộc tính
| Thuộc tính      | Ý nghĩa                                     |
| --------------- | ------------------------------------------- |
| `rollbackFor`   | Định nghĩa exception nào sẽ rollback        |
| `noRollbackFor` | Định nghĩa exception nào không rollback     |
| `propagation`   | Cách transaction lồng nhau hoạt động        |
| `isolation`     | Isolation level (độ cô lập) của transaction |
| `readOnly`      | Tối ưu performance cho truy vấn không ghi   |
| `timeout`       | Giới hạn thời gian thực thi transaction     |
- Propagation – khi gọi nhiều transaction lồng nhau
| Loại propagation        | Ý nghĩa                                           |
| ----------------------- | ------------------------------------------------- |
| `REQUIRED` *(mặc định)* | Dùng transaction đang có, nếu chưa có thì tạo mới |
| `REQUIRES_NEW`          | Luôn tạo transaction mới (tách biệt)              |
| `NESTED`                | Tạo transaction lồng trong transaction cha        |
| `SUPPORTS`              | Có thì dùng, không có thì chạy bình thường        |
| `NOT_SUPPORTED`         | Không dùng transaction                            |
| `NEVER`                 | Không được dùng transaction (lỗi nếu đang có)     |
- Thường dùng nhất:
    - REQUIRED cho logic chính
    - REQUIRES_NEW nếu muốn rollback độc lập (ví dụ: ghi log lỗi)

