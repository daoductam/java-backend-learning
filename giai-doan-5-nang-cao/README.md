# So sánh Memcached và Redis
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
| Tiêu chí              | Memcached | Redis |
|-----------------------|-----------|-------|
| **Cấu trúc dữ liệu**  | Key-Value Store | Hỗ trợ nhiều cấu trúc dữ liệu (string, list, set, hash, ...) |
| **Lưu trữ bền vững**  | Không hỗ trợ | Hỗ trợ lưu xuống đĩa |
| **Chính sách loại bỏ**| LRU và một số khác | LRU, LFU và nhiều chính sách khác |
| **Giao dịch**         | Không hỗ trợ | Hỗ trợ giao dịch nhiều thao tác |
| **Pub/Sub Messaging** | Không hỗ trợ | Hỗ trợ Pub/Sub |
| **Replication**       | Hỗ trợ replication để tăng HA | Hỗ trợ replication + clustering |
| **Khả năng mở rộng**  | Hạn chế | Mở rộng tốt, hỗ trợ clustering |
| **Tính năng nâng cao**| Chỉ cơ bản | Nhiều tính năng nâng cao (pub/sub, transaction, ...) |

##Khi nào nên trong Redis
| Kịch bản sử dụng                                                                                    | Memcached       | Redis           |
| --------------------------------------------------------------------------------------------------- | --------------- | --------------- |
| **Cần lưu trữ dữ liệu bền vững (Data Persistence)**                                                 | ❌ Không phù hợp | ✅ Phù hợp       |
| **Cấu trúc dữ liệu phức tạp (Complex Data Structures)**                                             | ❌ Không phù hợp | ✅ Phù hợp       |
| **Giao tiếp thời gian thực (Real-Time Communication)**                                              | ❌ Không phù hợp | ✅ Phù hợp       |
| **Lưu trữ và truy xuất dữ liệu vị trí hiệu quả (Efficient Storage and Retrieval of Location Data)** | ❌ Không phù hợp | ✅ Phù hợp       |
| **Tài nguyên server hạn chế (Limited Server Resources)**                                            | ✅ Phù hợp       | ❌ Không phù hợp |
| **Lưu trữ dữ liệu tạm thời (Temporary Data Storage)**                                               | ✅ Phù hợp       | ❌ Không phù hợp |
