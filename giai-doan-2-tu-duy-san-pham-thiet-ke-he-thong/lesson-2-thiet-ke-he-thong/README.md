# Buổi 2: Thiết kế hệ thống
## Thực hành
- Thiết kế hệ thống backend cho tính năng “Thêm vào giỏ hàng” của Shopee.
- Đầu vào: 
    - Dựa vào những business requirement define ở buổi trước -> gọi là functional requirement
    - Define 1 số non-functional requirement 
- Output: (ko cần code)
    - Dự đoán volume (VD: Sản phẩm này sẽ có 1000 request/ giờ, cao điểm hàng ngày là tầm 2000 request/giờ, flash sale dạng 6/6 có x request giờ)...
    - List ra danh sách các API cần có (bao gồm method, url, request, response) theo chuẩn REST
    - Thiết kế DB
## Bài làm 
### 1. Kết quả buổi trước
* Functional Requirements (Yêu cầu chức năng) - Buổi trước
- Hệ thống phải cho phép người dùng đã đăng nhập thêm sản phẩm vào giỏ hàng thông qua nút “Thêm vào giỏ hàng” trên trang chi tiết sản phẩm.
- Hệ thống phải chuyển hướng người dùng chưa đăng nhập sang trang đăng nhập khi họ cố gắng thêm sản phẩm vào giỏ hàng.   
- Nếu sản phẩm đã tồn tại trong giỏ hàng, hệ thống chỉ tăng số lượng thay vì tạo thêm dòng mới.
- Nếu mã sản phẩm không tồn tại trong hệ thống, API phải trả về lỗi 404 “Product not found”.
- Nếu sản phẩm đã tồn tại trong giỏ hàng, hệ thống phải tăng số lượng sản phẩm đó thay vì thêm dòng mới.
- Sau khi thêm sản phẩm thành công, hệ thống phải cập nhật biểu tượng giỏ hàng (số lượng) và hiển thị thông báo “Thêm thành công”.

* Non-functional Requirements (NFR)

- Hệ thống phải đảm bảo tốc độ phản hồi của các API không vượt quá 300ms đối với 95% tổng số request (95th percentile).
- Hệ thống phải có khả năng xử lý tối thiểu 1.000 request mỗi giây mà không làm giảm hiệu năng đáng kể.
- Mỗi API thêm sản phẩm vào giỏ hàng phải đảm bảo tính idempotent – cùng một yêu cầu được gửi nhiều lần chỉ gây ra một lần thêm duy nhất.
- Hệ thống phải sử dụng bộ nhớ tạm (cache) như Redis hoặc local memory để lưu trữ giỏ hàng nhằm tăng tốc độ truy xuất dữ liệu.
- Giao diện và API của hệ thống phải tương thích và hoạt động ổn định trên cả nền tảng web và ứng dụng di động (iOS, Android).

### 2. Dự đoán Volume
- Ngày thường: ~1.000 req/giờ
- Giờ cao điểm (7-9PM): 2.000-2.500 req/giờ
- Flash Sale (6/6): 50.000 req/giờ

### 3. Danh Sách API

- API Thêm sản phẩm vào giỏ
    - Method: Post
    - URL: /api/carts/{userId}/items
    - Request Body: 
    {
        "productId": 123,
        "quantity": 2
    }
    - Response Body:
    {
        "cartId": 456, 
        "items": [ { 
                    "productId":123, 
                    "quantity":2
                } ], 
        "totalItems": 5 
    }
- API Lấy Giỏ hàng của user:
    - Method: Get
    - URL: /api/carts/{userId}
    - Request Body: không có
    - Response Body:
    { 
        "cartId": 456, 
        "items": [ … ], 
        "totalItems": 5 
    }
- API Cập nhật số lượng item:
    - Method: PUT
    - URL: /api/carts/{userId}/items/{productId}
    - Request Body: { "quantity": 3 }
    - Response Body: 
    { 
        "message": "Cập nhật thành công",
        "productId":123, 
        "quantity":3, 
        "totalItems":5 
    }
- API Xóa sản phẩm khỏi giỏ:
    - Method: DELETE
    - URL: /api/carts/{userId}/items/{productId}
    - Request Body: không có
    - Response Body:
    { 
        "message": "Xóa thành công", 
    }

### 4. Database
create database add_item_to_cart;
use add_item_to_cart;

CREATE TABLE user (
  user_id      int       PRIMARY KEY AUTO_INCREMENT,
  username     VARCHAR(100) NOT NULL UNIQUE,
  email        VARCHAR(150) NOT NULL UNIQUE,
  created_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  password VARCHAR(255) NOT NULL
);

CREATE TABLE  product (
  product_id   int       PRIMARY KEY AUTO_INCREMENT,
  name         VARCHAR(255) NOT NULL,
  price        double NOT NULL,
  created_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE  cart (
  cart_id      int       PRIMARY KEY AUTO_INCREMENT,
  user_id      int       NOT NULL,
  CONSTRAINT fk_cart_user
    FOREIGN KEY (user_id) REFERENCES user (user_id)
    ON DELETE CASCADE
);

CREATE TABLE cart_items (
  cart_item_id int       PRIMARY KEY AUTO_INCREMENT,
  cart_id      int       NOT NULL,
  product_id   int       NOT NULL,
  quantity     int          NOT NULL DEFAULT 1,
  created_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT uc_cart_product UNIQUE (cart_id, product_id),
  CONSTRAINT fk_ci_cart
    FOREIGN KEY (cart_id) REFERENCES cart (cart_id)
    ON DELETE CASCADE,
  CONSTRAINT fk_ci_product
    FOREIGN KEY (product_id) REFERENCES product (product_id)
    ON DELETE RESTRICT
);