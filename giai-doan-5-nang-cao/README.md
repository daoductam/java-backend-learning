# Buổi 17: Caching
## Cache là gì
*Cache* là bộ nhớ trong (in-memory) được dùng để lưu trữ tạm thời dữ liệu không nhạy cảm và thường xuyên được truy cập. Điều này giúp giảm tải cơ sở dữ liệu và tăng tốc độ truy xuất dữ liệu.
## Các loại cache
Có thể phân loại cache chính thành:
- In-Memory Caching: Cache nằm trong bộ nhớ cục bộ của một instance dịch vụ.
- Distributed Caching: Cache được chia sẻ giữa nhiều dịch vụ, độc lập với từng ứng dụng.
## Tại sao cần cache phân tán (Distributed Caching)
Khi nhiều instance dịch vụ chạy trong các môi trường cách ly (ví dụ: Kubernetes, cloud scaling), cache trong bộ nhớ cục bộ mất tính hiệu quả. Distributed caching giải quyết vấn đề này bằng cách tách cache ra khỏi từng dịch vụ và chia sẻ cho toàn hệ thống.
## Memcached là gì?
**Memcached** là một hệ thống cache phân tán, tốc độ cao, lưu trữ dữ liệu trong bộ nhớ RAM để tăng tốc các ứng dụng web động.  
Hoạt động theo mô hình **key-value store**, trong đó dữ liệu được truy xuất bằng **key duy nhất**.  
Memcached có đặc điểm **nhẹ, đơn giản** và thường được dùng để:
- Cache kết quả truy vấn cơ sở dữ liệu.
- Lưu trữ session.
- Tăng tốc độ phản hồi website.

## Redis là gì?
**Redis** là một hệ thống lưu trữ dữ liệu cấu trúc trong bộ nhớ (in-memory data structure store) mã nguồn mở, nổi tiếng nhờ **tốc độ và tính linh hoạt**.  
Redis có thể đóng vai trò:
- **Cơ sở dữ liệu**
- **Bộ nhớ cache**
- **Message broker**  

Hỗ trợ nhiều cấu trúc dữ liệu như: **string**, **list**, **set**, **hash**.  
Redis thường được sử dụng cho:
- Ứng dụng yêu cầu truy xuất dữ liệu nhanh và độ trễ thấp.
- Phân tích dữ liệu thời gian thực.
- Hệ thống hàng đợi tin nhắn (messaging queues).

## So sánh Memcached và Redis
| Tính năng / Khía cạnh                      | **Memcached**                            | **Redis**                                       |
| ------------------------------------------ | ---------------------------------------- | ----------------------------------------------- |
| **Kiểu dữ liệu**                           | Chỉ hỗ trợ chuỗi (Strings only)          | Strings, Lists, Sets, Hashes, Sorted Sets, v.v. |
| **Lưu trữ bền vững (Persistence)**         |  Không hỗ trợ                           |  RDB, AOF, v.v.                                |
| **Replication & Clustering**               |  Chỉ hỗ trợ sharding cơ bản phía client |  Hỗ trợ gốc                                    |
| **Pub/Sub**                                |  Không hỗ trợ                           | Có                                            |
| **Chính sách loại bỏ (Eviction Policies)** | Chỉ LRU                                  | LRU, LFU, TTL, v.v.                             |
| **Hiệu quả bộ nhớ**                        | Rất cao cho các trường hợp đơn giản      | Tốn hơn một chút                                |
| **Tốc độ**                                 | Cực nhanh (Ultra fast)                   | Nhanh, nhưng tốn thêm chút tài nguyên           |
| **Hết hạn dữ liệu & TTL**                  |  Hỗ trợ                                 |  Hỗ trợ                                        |
| **Thao tác nguyên tử (Atomic Operations)** |  Giới hạn                               |  Đa dạng                                       |
| **Sao lưu / Phục hồi (Backup / Restore)**  |  Không hỗ trợ                           |  Có                                            |
| **Giao dịch / Script LUA**                 |  Không có                               |  Có                                            |
| **Hàng đợi tin nhắn (Message Queues)**     |  Không phù hợp                          |   thể dùng như broker nhẹ                    |


## Khi nào nên trong Memcached
- Chỉ cần key-value store cơ bản
- Dữ liệu tạm thời (ví dụ: session, API rate limit)
- Không cần backup hoặc persistence
- Muốn tốc độ cực nhanh với bộ nhớ nhẹ
- Có thể tự quản lý client-side sharding

## Khi nào nên trong Redis
- Cần dữ liệu có cấu trúc (list, set, counter, queue)
- Cần persistence hoặc backup/restore
- Cần Pub/Sub, transaction, Lua scripting
- Muốn clustering và replication tích hợp sẵn
- Cần chiến lược TTL và eviction thông minh

## Trade-offs

| Nhu cầu                  | Dùng **Memcached** nếu...              | Dùng **Redis** nếu...                                            |
| ------------------------------------ | -------------------------------------- | ---------------------------------------------------------------- |
| **Chỉ cần cache đơn giản**           | Bạn chỉ cache chuỗi (string)           | Bạn cần xử lý logic như hàng đợi (queue), bộ đếm (counter), v.v. |
| **Bộ nhớ là mối quan tâm hàng đầu**  | Bạn muốn tiêu tốn tài nguyên tối thiểu | Bạn có thể chấp nhận dùng nhiều bộ nhớ hơn                       |
| **Chấp nhận mất dữ liệu**            | Dữ liệu chỉ mang tính tạm thời         | Bạn cần lưu trữ bền vững hoặc lâu dài                            |
| **Cần mở rộng ngang trên diện rộng** | Bạn thực hiện chia shard phía client   | Bạn muốn auto-sharding và High Availability                      |
| **Cài đặt đơn giản, nhanh**          | Không cần tính năng nâng cao           | Bạn cần clustering, thao tác nguyên tử, pub/sub, v.v.            |
| **Độ trễ cực thấp**                  | Cần tốc độ tối đa                      | Chậm hơn một chút nhưng mạnh mẽ hơn                              |


