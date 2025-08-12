# Message broker
### So sánh Kafka và RabbitMQ

| Tiêu chí                       | Apache Kafka                                             | RabbitMQ                                                                  |
| ------------------------------ | -------------------------------------------------------- | ------------------------------------------------------------------------- |
| **Kiến trúc**                  | Dựa trên log phân tán (distributed commit log)           | Message queue truyền thống (broker-based queue)                           |
| **Mô hình message**            | Publish-subscribe, topic-based, phân vùng (partition)    | Queue-based, hỗ trợ pub-sub, routing với Exchange                         |
| **Lưu trữ dữ liệu**            | Lưu trữ lâu dài trên đĩa, message không bị xóa ngay      | Message được giữ trong queue cho đến khi consumer lấy, thường mất sau đọc |
| **Tính bền vững**              | Rất cao, dữ liệu được replicated trên nhiều broker       | Tùy cấu hình: durable queue, message ack                                  |
| **Tính nhất quán**             | Event ordering trong partition, offset quản lý chính xác | Không đảm bảo thứ tự toàn cục trên nhiều queue                            |
| **Hiệu năng**                  | Rất cao, xử lý hàng triệu message/giây                   | Tốt nhưng thường chậm hơn Kafka                                           |
| **Khả năng mở rộng**           | Dễ dàng mở rộng ngang với phân vùng và consumer group    | Mở rộng khó hơn, cần clustering hoặc federation                           |
| **Giao thức**                  | Giao thức nội bộ Kafka, không tương thích AMQP           | Hỗ trợ AMQP, MQTT, STOMP                                                  |
| **Phức tạp vận hành**          | Phức tạp hơn do phân tán, cần Zookeeper                  | Đơn giản hơn, dễ cài đặt, vận hành                                        |
| **Trường hợp dùng phổ biến**   | Streaming data, event sourcing, log aggregation          | Task queue, message routing, job dispatch                                 |
| **Hỗ trợ retry / Dead Letter** | Cần config thêm, có Dead Letter Topic (DLT)              | Hỗ trợ sẵn DLQ, dễ cấu hình                                               |
| **Tích hợp**                   | Thường dùng trong hệ sinh thái big data, streaming       | Phổ biến trong các hệ thống backend truyền thống                          |

-> Kafka phù hợp với hệ thống cần xử lý lượng lớn dữ liệu liên tục, bền vững, có thể mở rộng lớn, streaming hoặc event sourcing.
-> RabbitMQ phù hợp hệ thống cần message queue, task queue đơn giản, routing linh hoạt, dễ vận hành.

