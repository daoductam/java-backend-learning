# Buổi 2: Docker Cơ Bản

Docker giúp Tâm đóng gói và triển khai ứng dụng một cách dễ dàng và nhất quán, giải quyết vấn đề "cài đặt và cấu hình môi trường" cho các ứng dụng trên các máy khác nhau.

Nếu không sử dụng Docker, mỗi người dùng sẽ phải cài đặt và cấu hình lại môi trường trên máy của họ, gây tốn thời gian và dễ phát sinh lỗi.

# Khái niệm

Image : Mẫu (template) dùng để tạo container, chứa hệ điều hành, ứng dụng, và phụ thuộc.
Container : Một instance của Image, thực thi và cô lập ứng dụng.
Dockerfile : File text để định nghĩa các bước xây dựng một Docker image.
docker-compose : Công cụ giúp định nghĩa và quản lý nhiều container một cách dễ dàng với file YAML.
Volume : Cơ chế lưu trữ dữ liệu để container restart vẫn còn dữ liệu.
Network : Tạo các mạng ảo để các container có thể giao tiếp với nhau.
Registry : Kho chứa các Docker image (ví dụ: Docker Hub).

# Hiển thị container đang chạy
docker ps

# Hiển thị danh sách image
docker images

# Chạy một container từ image
docker run [OPTIONS] IMAGE

# Dừng một container
docker stop [CONTAINER_ID]

# Xoá container
docker rm [CONTAINER_ID]

# Xoá image
docker rmi [IMAGE_ID]

# Build image từ Dockerfile
docker build -t name .

# Khởi động dịch vụ qua docker-compose
docker compose up -d

# Tắt và xóa toàn bộ container, network
docker compose down

# flow sử dụng Docker
1.Viết Dockerfile 

2.Tạo .dockerignore để loại trừ các file không cần thiết khi build Docker image

3.Build Docker Image (Câu lệnh: docker build -t my-app .)

4.Tạo docker-compose.yml 

5.Chạy docker compose up -d

6.Mở Docker Desktop để kiểm tra container 

7.Push lên Registry(docker hub)
