# Phân Tích và Hiểu về Cơ Sở Dữ Liệu
## 1. Relational database vs NoSql Database
- Relational database dựa trên mô hình quan hệ giữa các bảng
(tables) dữ liệu. Trong hệ thống này, dữ liệu được lưu trữ trong các
bảng, mỗi bảng đại diện cho một thực thể (entity) của hệ thống và
các mối quan hệ giữa các bảng được xác định bằng các khóa ngoại
(foreign key)
- Mỗi bảng trong hệ thống quan hệ được thiết kế với một tập các cột
(columns), mỗi cột đại diện cho một thuộc tính (attribute) của thực
thể tương ứng. Mỗi hàng (row) trong bảng chứa thông tin của một
thực thể cụ thể, với giá trị của các thuộc tính được lưu trữ tương
ứng
- NoSQL cho phép lưu trữ dữ liệu dưới nhiều dạng khác nhau như
tài liệu (document), cặp khóa-giá trị (key-value), đồ thị (graph) và
trình tự (time-series). Đây là những dạng dữ liệu phổ biến trong các
ứng dụng web và mobile.
- Các hệ thống NoSQL thường có khả năng mở rộng tốt hơn so với
hệ thống quan hệ, đặc biệt là khả năng mở rộng theo chiều ngang
(horizontal scaling), cho phép phân tán dữ liệu trên nhiều máy chủ,
nút (node) để tăng hiệu năng và khả năng mở rộng
## 2. Ưu Điểm của Relationship DB (Cơ Sở Dữ Liệu Quan Hệ)
- **ACID Compliance:**  
    - Atomicity, Consistency, Isolation, and Durability (ACID) là một tiêu chuẩn
    đảm bảo tính đáng tin cậy của các giao dịch cơ sở dữ liệu. Nguyên tắc
    chung là nếu một thay đổi thất bại, toàn bộ giao dịch sẽ thất bại và cơ sở dữ
    liệu sẽ giữ nguyên trạng thái trước khi giao dịch được thực hiện. Điều này
    rất quan trọng vì một số giao dịch sẽ có hậu quả thực sự nếu không hoàn
    thành đầy đủ - ví dụ như giao dịch ngân hàng.
- **Data Accuracy:** 
    - Sử dụng khóa chính và khóa ngoại giúp bạn đảm bảo không có thông tin
    trùng lặp. Điều này giúp tăng cường độ chính xác dữ liệu vì không bao giờ
    có thông tin bị lặp lại.
- **Normalization:** 
    - Quá trình chuẩn hóa liên quan đến đảm bảo dữ liệu được tổ chức theo cách
    giảm thiểu hoặc loại bỏ các bất thường dữ liệu. Điều này, trong quá trình đó,
    giảm chi phí lưu trữ.
- **Simplicity:** 
    - Cơ sở dữ liệu quan hệ hoặc SQL đã tồn tại trong một thời gian dài, do đó
    đã phát triển ra nhiều công cụ và tài nguyên để giúp bắt đầu và tương tác
    với cơ sở dữ liệu quan hệ. Cú pháp gần giống tiếng Anh của SQL cũng làm
    cho người không phải là nhà phát triển có thể tạo báo cáo và truy vấn dữ
    liệu
## 3. Nhược Điểm của Relationship DB
- **Scalability:** 
    - Hệ thống quản lý cơ sở dữ liệu quan hệ (RDMS) được thiết kế để chạy
    trên một máy tính đơn lẻ. Điều này có nghĩa là nếu yêu cầu của máy
    tính không đủ, do kích thước dữ liệu hoặc tần suất truy cập tăng, bạn sẽ
    phải nâng cấp phần cứng trong máy tính, còn gọi là mở rộng dọc
    - Điều này có thể rất đắt tiền và có một giới hạn, vì cuối cùng chi phí sẽ
    vượt quá lợi ích. Ngoài ra, sẽ có thể đến một giai đoạn nào đó mà bạn
    đơn giản không thể có phần cứng có khả năng lưu trữ cơ sở dữ liệu.
    Giải pháp duy nhất sẽ là mua một máy tính hỗ trợ phần cứng tốt hơn,
    nhưng điều đó không rẻ.
- **Flexibility:** 
    - Trong cơ sở dữ liệu quan hệ, cấu trúc dữ liệu rất cứng nhắc. Bạn xác định
    các cột và loại dữ liệu cho các cột đó, bao gồm bất kỳ hạn chế nào như định
    dạng hoặc độ dài. Các ví dụ phổ biến về hạn chế bao gồm độ dài số điện
    thoại hoặc độ dài tối thiểu / tối đa cho một cột tên
    - Mặc dù điều này có nghĩa là bạn có thể dễ dàng giải thích dữ liệu và
    xác định mối quan hệ giữa các bảng, nhưng điều đó có nghĩa là việc
    thay đổi cấu trúc dữ liệu là rất phức tạp. Bạn phải quyết định từ đầu dữ
    liệu sẽ như thế nào, điều đó không phải lúc nào cũng khả thi. Nếu bạn
    muốn thay đổi sau này, bạn phải thay đổi toàn bộ dữ liệu, điều này đòi
    hỏi cơ sở dữ liệu phải tạm thời offline.
- **Performance:** 
    - Hiệu suất của cơ sở dữ liệu chặt chẽ liên quan đến độ phức tạp của các
    bảng - số lượng bảng cũng như lượng dữ liệu trong mỗi bảng. Khi điều
    này tăng lên, thời gian thực hiện các truy vấn cũng tăng lên
## 4. Ưu Nhược Điểm của NoSQL
### Ưu Điểm:
- **Performance:** Xử lý truy vấn nhanh hơn, đặc biệt khi làm việc với các loại dữ liệu không đồng nhất hoặc dữ liệu lớn.
- **Flexibility:** NoSQL không yêu cầu một cấu trúc dữ liệu cố định, cho phép dễ dàng thay đổi hoặc mở rộng mô hình dữ liệu.
- **Scalability:** Hệ thống NoSQL có khả năng mở rộng linh hoạt và dễ dàng, thích hợp cho các ứng dụng cần xử lý dữ liệu phân tán.
### Nhược Điểm:
- **Lack of ACID:** Không phải tất cả các hệ thống NoSQL đều tuân thủ các đặc tính ACID, điều này có thể ảnh hưởng đến tính toàn vẹn của dữ liệu.
- **Consistency:** Hệ thống NoSQL thường sử dụng mô hình "eventual consistency" thay vì "strong consistency", có thể dẫn đến tình trạng dữ liệu không đồng bộ trong một khoảng thời gian ngắn.
- **Complexity:** Việc lựa chọn và triển khai hệ thống NoSQL có thể phức tạp hơn trong một số trường hợp, đặc biệt là khi cần đồng bộ hóa dữ liệu giữa các nút.

## 5. Khi Nào Nên Dùng Relationship DB?
- Nếu bạn tạo một dự án có dữ liệu dự đoán được về cấu trúc, kích thước
và tần suất truy cập, cơ sở dữ liệu quan hệ vẫn là lựa chọn tốt nhất. Việc
normalize dữ liệu có thể giúp giảm kích thước dữ liệu trên đĩa bằng cách
giới hạn dữ liệu trùng lặp và các bất thường, giảm rủi ro của việc yêu cầu
nâng cấp phần cứng trong tương lai.
- Cơ sở dữ liệu quan hệ cũng là lựa chọn tốt nhất nếu mối quan hệ giữa các
thực thể quan trọng.
- Cơ sở dữ liệu phi quan hệ có thể lưu trữ tài liệu trong tài liệu, giúp giữ dữ
liệu được truy cập cùng nhau trong cùng một nơi. Tuy nhiên, nếu điều này
không phù hợp với nhu cầu của bạn, cơ sở dữ liệu quan hệ vẫn là giải
pháp. Nếu ví dụ bạn có một tập dữ liệu lớn với cấu trúc và mối quan hệ
phức tạp, việc nhúng có thể không tạo ra mối quan hệ rõ ràng đủ.
- Các RDMS đã có từ lâu đến nay, điều này có nghĩa là có sự hỗ trợ rộng
rãi có sẵn, từ các công cụ đến tích hợp với dữ liệu từ các hệ thống khác.

## 6. Khi Nào Nên Dùng Non-Relationship DB (NoSQL)?

- Non-relational databases thích hợp cho cả operational and
transactional data.
- Phù hợp với các ứng dụng có unstructured big data.
- Higher performance and availability.
- Flexible schema giúp cơ sở dữ liệu phi quan hệ lưu trữ nhiều dữ
liệu loại khác nhau mà không cần thay đổi schema quan trọng.

## 7. Có Phải Loại Này Luôn Tốt Hơn Loại Kia?

Không, không có loại cơ sở dữ liệu nào luôn tốt hơn loại còn lại. Việc chọn loại cơ sở dữ liệu phụ thuộc vào yêu cầu cụ thể của ứng dụng. Cơ sở dữ liệu quan hệ thích hợp với các ứng dụng yêu cầu tính chính xác và nhất quán của dữ liệu, trong khi NoSQL lại phù hợp với các ứng dụng cần khả năng mở rộng linh hoạt và xử lý dữ liệu phi cấu trúc.
## 8. Index
- Index được sử dụng để tăng tốc độ truy vấn vf sẽ được sử dụng bởi có sở dữ liệu tìm kiếm để xác định vị trí bản ghi rất nhanh
- Ngoài ra index còn có thể làm điều kiện để dữ liệu được unique
- Các lệnh Insert và update tốn nhiều thời gian hơn trên các bảng có index trong khi các lệnh SELECT trở nên nhanh hơn trên các bảng này. Lý do là vì trong khi chèn và cập nhật database cũng phải cần chèn hoặc cập nhật các giá trị index
- Các loại index 
    - primary key
    - unique key
    - foreign key
    - composite key (index order matter)
- Dựa vào câu query và hoàn cảnh để quyết định query  cho mỗi cột trong bản
## 9. Hiểu về Khái Niệm ACID trong Database
- **Atomicity (Tính nguyên tử):** Toàn bộ các lệnh của transaction được coi như một
single-unit và hoặc tất cả lệnh cùng được thực hiện hoặc tất cả
cùng không xảy ra. All-or-nothing.
    - **Ví dụ:** Khi chuyển tiền giữa hai tài khoản, nếu phần trừ tiền khỏi tài khoản A thành công nhưng phần cộng tiền vào tài khoản B thất bại, giao dịch sẽ bị hủy toàn bộ để tránh mất tiền.

- **Consistency (Tính nhất quán):** Những thay đổi trong 1 transaction là nhất quán với
database constraints (ràng buộc).
    - **Ví dụ:** Trong hệ thống ngân hàng, mỗi giao dịch phải đảm bảo rằng số tiền trong tài khoản không thể âm.

- **Isolation (Tính cách ly):** Các transactions đồng thời chạy ở một môi trường độc
lập, không can thiệp lẫn nhau.
    - **Ví dụ:** Nếu hai người cùng thực hiện giao dịch rút tiền từ cùng một tài khoản, hệ thống sẽ xử lý các giao dịch như thể chúng là độc lập, không gây ra sai sót.

- **Durability (Tính bền vững):** Khi hoàn thành một transaction thì những thay đổi đó sẽ
ở trạng thái bền vững, kể cả khi hệ thống bị dừng hoặc mất điện.
    - **Ví dụ:** Nếu bạn thực hiện một giao dịch chuyển tiền và sau đó hệ thống bị tắt nguồn, giao dịch vẫn được ghi nhận khi hệ thống khởi động lại.

### Tại sao lại cần ACID?
ACID đảm bảo rằng dữ liệu trong cơ sở dữ liệu luôn chính xác, không bị mất mát và không gặp phải các lỗi đồng thời khi nhiều giao dịch xảy ra. Điều này đặc biệt quan trọng trong các ứng dụng yêu cầu tính chính xác và nhất quán của dữ liệu như trong ngân hàng, chứng khoán, và các hệ thống quản lý tài chính.

## 10. Transaction 
- Transaction là một chuỗi gồm 1 hoặc nhiều câu lệnh SQL được
thực hiện như 1 single-unit-of-work.
- Transaction giúp toàn vẹn dữ liệu bằng cơ chế hoặc tất cả câu lệnh
SQL cùng được hoàn thành, hoặc sẽ rollback lại nếu có lỗi.
- Cấu trúc:
    - START TRANSACTION
    - --- Các lệnh SQL
    - COMMIT hoặc ROLLBACK
- Mặc định, MySQL ở cơ chế autocommit = mỗi lệnh SQL được coi
như 1 transaction và tự động COMMIT sau mỗi lệnh. Có thể tắt
autocommit bằng lệnh `SET AUTOCOMMIT = 0`

## 11. Isolation Level
- Dirty Read: khi 1 transaction đọc data được thay đổi bởi transaction
khác, nhưng thay đổi này chưa được committed.
- Non-Repeatable Read: khi 1 transaction đọc 1 dòng dữ liệu nhiều
lần, và nhận được kết quả khác nhau.
- Phantom Read: khi một transaction query nhiều lần, nhưng số kết
quả của mỗi lần là khác nhau
## 12. MySQL Lock
- Lock là cơ chế để kiểm soát truy cập tới data khi có nhiều user
cùng truy cập cùng lúc nhằm bảo toàn sự toàn vẹn của dữ liệu. Một
số cơ chế lock:
    - Table-Lock:
        - Read Lock (Shared Lock): cho phép nhiều transaction cùng đọc nhưng không ghi
        - Write Lock (Exclusive Lock): cho phép 1 transaction cùng đọc ghi, các transaction khác
        không thể truy cập
    - Row-level Lock:
        - chỉ lock theo dòng thay vì cả bảng => linh động hơn, cho phép nhiều concurrency hơn.
        - Sử dụng: SELECT ... FOR UPDATE
### Pessimistic Locking
- tránh race condition bằng cách khóa các data đang được truy cập và
update
- đảm bảo ở 1 thời điểm, chỉ có 1 transaction có thể truy cập đến data,
transaction khác sẽ phải đợi
- MySQL:
    - SELECT * FROM Room  WHERE id = 123 FOR UPDATE;
    - COMMIT;
    - ROLLBACK;
### Optimistic Locking
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
### Pessimistic: Pros vs Cons
- Pessimistic Locking – Pros:
    - Data Integrity: rất hiệu quả để bảo toàn tính tính toàn vẹn vì lock data trước
    khi đọc hay sửa , tránh các transaction khác xử lý đồng thời
    - Đơn giản: thường cài đặt đơn giản hơn
    - Built-in DB support
- Cons:
    - Performance: có thể dẫn đến bottleneck nếu thời gian lock lâu vì các
    transaction khác cần phải đợi transaction giữ lock thực hiện xong
    - Deadlock: 2 hoặc nhiều transaction đợi lẫn nhau

### Optimistic: Pros vs Cons
- Optimistic Locking – Pros:
    - Performance : tránh phải chờ đợi lock
    - Concurrency: tốt hơn về xử lý đồng thời vì không có lock hay deadlock
- Cons:
    - Xử lý conflict: Conflict chỉ có thể biết sau ở cuối transaction và rollback nếu
    conflict xảy ra.
    - Buộc phải maintain thêm version trong table
# Execute plan:

Nested Loop  (cost=0.29..197.32 rows=2 width=17)
->  Index Scan using users_pkey on users u  (cost=0.29..8.30 rows=1 width=17)
Index Cond: (id = 123)
->  Seq Scan on orders o  (cost=0.00..189.00 rows=2 width=16)
Filter: (user_id = 123)

chi phí min 0.29 max 197.32

# Execute plan sau khi Tiến hành tạo index cho field user_id trong bảng orders:

Nested Loop  (cost=4.59..19.59 rows=2 width=17)
->  Index Scan using users_pkey on users u  (cost=0.29..8.30 rows=1 width=17)
Index Cond: (id = 123)
->  Bitmap Heap Scan on orders o  (cost=4.30..11.26 rows=2 width=16)
Recheck Cond: (user_id = 123)
->  Bitmap Index Scan on idx_orders_user_id  (cost=0.00..4.30 rows=2 width=0)
Index Cond: (user_id = 123)

chi phí: min 4.59 max 19.59

