# Phân Tích và Hiểu về Cơ Sở Dữ Liệu

## 1. Hiểu về Khái Niệm ACID trong Database

ACID là một tập hợp các đặc tính quan trọng giúp đảm bảo tính toàn vẹn và nhất quán của dữ liệu trong các hệ thống cơ sở dữ liệu quan hệ. ACID bao gồm 4 yếu tố chính:

- **Atomicity (Tính nguyên tử):** Đảm bảo rằng một giao dịch (transaction) sẽ được thực hiện hoàn toàn hoặc không thực hiện chút nào. Nếu một phần của giao dịch không thành công, toàn bộ giao dịch sẽ bị hủy bỏ.
    - **Ví dụ:** Khi chuyển tiền giữa hai tài khoản, nếu phần trừ tiền khỏi tài khoản A thành công nhưng phần cộng tiền vào tài khoản B thất bại, giao dịch sẽ bị hủy toàn bộ để tránh mất tiền.

- **Consistency (Tính nhất quán):** Dữ liệu sẽ chuyển từ một trạng thái hợp lệ sang một trạng thái hợp lệ khác sau khi giao dịch hoàn tất. Điều này giúp đảm bảo rằng dữ liệu trong cơ sở dữ liệu luôn tuân thủ các quy tắc và ràng buộc.
    - **Ví dụ:** Trong hệ thống ngân hàng, mỗi giao dịch phải đảm bảo rằng số tiền trong tài khoản không thể âm.

- **Isolation (Tính cách ly):** Mỗi giao dịch sẽ được thực hiện độc lập và không bị ảnh hưởng bởi các giao dịch khác đang thực hiện cùng lúc. Điều này giúp đảm bảo không có sự can thiệp vào quá trình giao dịch.
    - **Ví dụ:** Nếu hai người cùng thực hiện giao dịch rút tiền từ cùng một tài khoản, hệ thống sẽ xử lý các giao dịch như thể chúng là độc lập, không gây ra sai sót.

- **Durability (Tính bền vững):** Sau khi một giao dịch được hoàn tất, kết quả của nó sẽ được lưu trữ vĩnh viễn, ngay cả khi hệ thống gặp sự cố.
    - **Ví dụ:** Nếu bạn thực hiện một giao dịch chuyển tiền và sau đó hệ thống bị tắt nguồn, giao dịch vẫn được ghi nhận khi hệ thống khởi động lại.

### Tại sao lại cần ACID?
ACID đảm bảo rằng dữ liệu trong cơ sở dữ liệu luôn chính xác, không bị mất mát và không gặp phải các lỗi đồng thời khi nhiều giao dịch xảy ra. Điều này đặc biệt quan trọng trong các ứng dụng yêu cầu tính chính xác và nhất quán của dữ liệu như trong ngân hàng, chứng khoán, và các hệ thống quản lý tài chính.

## 2. Ưu Điểm của Relationship DB (Cơ Sở Dữ Liệu Quan Hệ)

- **ACID Compliance:** Đảm bảo tính toàn vẹn của dữ liệu, giúp duy trì dữ liệu chính xác, nhất quán và không bị mất mát.
- **Data Accuracy:** Cơ sở dữ liệu quan hệ thường sử dụng các ràng buộc (constraints) như khóa chính và khóa ngoại để đảm bảo tính chính xác của dữ liệu.
- **Normalization:** Giúp giảm thiểu sự dư thừa của dữ liệu thông qua quá trình phân tách và chuẩn hóa bảng, từ đó tăng cường tính toàn vẹn dữ liệu.
- **Simplicity:** Dễ dàng triển khai và duy trì, đặc biệt là khi dữ liệu có mối quan hệ rõ ràng giữa các bảng.

## 3. Nhược Điểm của Relationship DB

- **Performance:** Các hệ thống cơ sở dữ liệu quan hệ có thể gặp vấn đề về hiệu suất khi phải xử lý khối lượng dữ liệu lớn và các câu truy vấn phức tạp.
- **Flexibility:** Cơ sở dữ liệu quan hệ yêu cầu cấu trúc cố định, điều này có thể gây khó khăn khi cần thay đổi mô hình dữ liệu.
- **Scalability:** Cơ sở dữ liệu quan hệ thường gặp khó khăn khi phải mở rộng quy mô với dữ liệu phân tán hoặc dữ liệu phi cấu trúc.

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

Cơ sở dữ liệu quan hệ phù hợp khi:
- Dữ liệu có mối quan hệ rõ ràng và cố định (ví dụ: dữ liệu tài chính, quản lý nhân sự, hay giao dịch ngân hàng).
- Cần tuân thủ các nguyên tắc ACID để đảm bảo tính chính xác và nhất quán của dữ liệu.
- Cần các truy vấn phức tạp với các phép JOIN hoặc cần đảm bảo dữ liệu được chuẩn hóa.

## 6. Khi Nào Nên Dùng Non-Relationship DB (NoSQL)?

NoSQL phù hợp khi:
- Dữ liệu không có cấu trúc rõ ràng hoặc có sự thay đổi liên tục về cấu trúc.
- Cần phải xử lý dữ liệu lớn hoặc yêu cầu mở rộng theo chiều ngang.
- Dữ liệu không cần tuân thủ chặt chẽ các nguyên tắc ACID mà có thể sử dụng mô hình eventual consistency.

## 7. Có Phải Loại Này Luôn Tốt Hơn Loại Kia?

Không, không có loại cơ sở dữ liệu nào luôn tốt hơn loại còn lại. Việc chọn loại cơ sở dữ liệu phụ thuộc vào yêu cầu cụ thể của ứng dụng. Cơ sở dữ liệu quan hệ thích hợp với các ứng dụng yêu cầu tính chính xác và nhất quán của dữ liệu, trong khi NoSQL lại phù hợp với các ứng dụng cần khả năng mở rộng linh hoạt và xử lý dữ liệu phi cấu trúc.


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

