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
                                                     thể chạy đồng thời nhiều luồng    
| Bộ nhớ    | Các process là độc lập, không chia   | Các threads chia sẻ không gian    |
|           | sẻ không gian nhớ với nhau           | nhớ                               |
| Khởi tạo  | Cần nhiều system call để tạo nhiều   | Có thể tạo nhiều threads với      |
|           | process, tốn nhiều thời gian hơn     | một system call, tốn ít thời gian |
|           |                                      | hơn                               |
| Chấm dứt  | Tốn thời gian hơn so với thread      | Tốn ít thời gian hơn so với       |
|           |                                      | process                           |
| Giao tiếp | Cần sử dụng IPC, tốn kém hơn         | Ít tốn kém hơn so với process     |
| Context   | Chậm hơn threads                     | Nhanh hơn nhiều so với process    |
| switching |

---

