# Buổi 2: Mạng Máy Tính

## Mục tiêu

* Hiểu cách hoạt động của dữ liệu (Từ client → server)
* Nắm vững mô hình **OSI model**
* Làm quen và áp dụng các thuật ngữ:

  * HTTP, HTTPS
  * DNS, IP
  * TLS, SSL
  * Latency, Bandwidth
  * Domain
* Áp dụng vào tình huống thực tiễn: truy cập và mua hàng trên Shopee

---

## 1. Luồng dữ liệu từ Client → Server

Khi người dùng thực hiện một hành động (ví dụ: gõ URL, nhấn nút gửi form), trình duyệt (client) sẽ gửi một **request** đến server, server xử lý và gửi lại **response**. Quá trình chung gồm:

1. **DNS Resolution**: Đổi domain thành IP.
2. **TCP Handshake**: Thiết lập kết nối tin cậy.
3. **TLS Handshake** (nếu HTTPS): Tạo kênh mã hóa an toàn.
4. **Gửi HTTP Request**: Client gửi yêu cầu tài nguyên.
5. **Server Xử lý**: Truy vấn, tính toán, tạo nội dung.
6. **Gửi HTTP Response**: Server trả dữ liệu về.
7. **Render**: Trình duyệt hiển thị nội dung, có thể gọi thêm các request phụ (API, ảnh, CSS, JS).

---

## 2. OSI Model (7 tầng)

| Tầng | Tên tầng     | Chức năng chính                          |
| ---- | ------------ | ---------------------------------------- |
| 7    | Application  | Giao tiếp với ứng dụng người dùng (HTTP) |
| 6    | Presentation | Mã hóa/giải mã, nén/giải nén (TLS/SSL)   |
| 5    | Session      | Quản lý phiên kết nối                    |
| 4    | Transport    | Đảm bảo truyền tin cậy, thứ tự (TCP/UDP) |
| 3    | Network      | Định tuyến, địa chỉ IP                   |
| 2    | Data Link    | Truyền dữ liệu trong cùng mạng LAN (MAC) |
| 1    | Physical     | Tín hiệu vật lý qua cáp/Wi-Fi            |

---
Hoạt động của OSI: 
-> https://...... trong trình duyệt:
1. Tầng 7: Trình duyệt gửi HTTP request
2. Tầng 6: Dữ liệu được mã hóa TLS
3. Tầng 4–5: Chia thành các đoạn nhỏ (TCP segment)
4. Tầng 3: Gắn địa chỉ IP (Shopee server)
5. Tầng 2: Gắn MAC Address
6. Tầng 1: Biến thành tín hiệu điện, truyền qua cáp mạng/Wi-Fi
→ Server nhận được → trả về kết quả theo quy trình ngược lại

## 3. Các thuật ngữ quan trọng

### 3.1 HTTP và HTTPS

* **HTTP**
HTTP (HyperText Transfer Protocol) là giao thức cho phép client (ví dụ: trình duyệt) và server (máy chủ) trao đổi dữ liệu dạng văn bản (HTML, JSON, hình ảnh...).

Hoạt động của HTTP:
1. Client gửi yêu cầu (Request) đến server.
2. Server xử lý yêu cầu, sau đó gửi lại phản hồi (Response).

* **HTTPS**
HTTPS (HTTP Secure) là HTTP có mã hóa bằng cách sử dụng TLS (trước đây gọi là SSL).
Mọi dữ liệu trao đổi qua HTTPS đều được mã hóa → tránh bị đánh cắp, nghe lén hoặc chỉnh sửa.

Hoạt động của HTTPS:
1. Trình duyệt kết nối đến server qua cổng 443.
2. Thực hiện TLS Handshake:
    - Server gửi chứng chỉ số (SSL Certificate) để xác minh danh tính.
    - Hai bên thống nhất một khóa mã hóa phiên.
3. Sau đó, dữ liệu HTTP sẽ được mã hóa và gửi đi.

### 3.2 DNS và IP

* **DNS**: Hệ thống phân giải tên miền (domain) sang địa chỉ IP.
- Quy trình phân giải DNS:
    1. Người dùng nhập shopee.vn vào trình duyệt.
    2. Trình duyệt kiểm tra cache DNS trong máy.
    3. Nếu không có → hỏi hệ điều hành.
    4. Nếu không có → hỏi tiếp DNS server (VD: 8.8.8.8 của Google).
    5. DNS server tìm IP tương ứng → gửi lại cho client.
    6. Trình duyệt dùng IP đó để kết nối đến server Shopee.

* **IP**: Địa chỉ định danh thiết bị trên mạng (IPv4, IPv6).
- IPv4 là phổ biến nhất hiện nay, nhưng IPv6 đang dần thay thế do số lượng địa chỉ lớn hơn.

### 3.3 TLS và SSL

* **SSL**: Giao thức bảo mật cũ, đã lỗi thời.
* **TLS**: Phiên bản cải tiến, mã hóa kênh giao tiếp.
- Hoạt động của TLS(TLS Handshake):
    1. Client Hello: Trình duyệt gửi danh sách thuật toán mã hóa mà nó hỗ trợ.
    2. Server Hello + Certificate:
        + Server chọn thuật toán phù hợp.
        + Gửi chứng chỉ số (SSL certificate) để trình duyệt xác minh.
    3. Verify certificate:
        + Trình duyệt kiểm tra tính hợp lệ của chứng chỉ:Có đúng domain không?Có được CA uy tín ký không?Còn hạn không?
    4. Key exchange: Hai bên tạo ra một khóa bí mật chung để mã hóa dữ liệu.
    5. Start encrypted communication:Từ giờ trở đi, toàn bộ dữ liệu đều mã hóa.

### 3.4 Latency và Bandwidth

* **Latency**: Độ trễ, thời gian mất để gửi–nhận gói tin (ms).
* **Bandwidth**: Băng thông, dung lượng dữ liệu tối đa truyền trong giây (Mbps).

### 3.5 Domain

* **Domain**: Tên dễ nhớ, ví dụ `shopee.vn`.
* **Subdomain**: Mở rộng, ví dụ `seller.shopee.vn`.
* **TLD**: Đuôi tên miền, ví dụ `.vn`, `.com`.

---

## 4. Ứng dụng trong phát triển phần mềm

* **Dùng HTTPS** cho API để bảo mật dữ liệu.
* **Sử dụng domain** thay vì IP cố định để dễ bảo trì.
* **Tối ưu latency** bằng CDN, caching.
* **Giám sát bandwidth** để tránh nghẽn đường truyền.
* **Xử lý lỗi DNS** bằng retry, fallback.

---

## 5. Tình huống thực tế: Truy cập và mua hàng trên Shopee

1. **Người dùng gõ địa chỉ https://shopee.vn vào trình duyệt**

   * Đây là domain dễ nhớ → không cần gõ IP.
2. **Trình duyệt kiểm tra cache DNS**

   * Nếu đã từng truy cập Shopee → trình duyệt có thể nhớ IP cũ.
   * Nếu chưa có IP → thực hiện DNS Resolution
3. **DNS Resolution – phân giải tên miền**

   * Kiểm tra chứng chỉ số, tạo kênh mã hóa.
   * Trình duyệt hỏi DNS Server: “IP của shopee.vn là gì?”
        a.	Trình duyệt hỏi hệ điều hành → cache local?
        b.	Nếu không có → hỏi DNS server của nhà mạng
        c.	DNS Server hỏi Root → .vn → shopee.vn
        d.	Kết quả: shopee.vn → 13.250.232.99 (IP)
    -> Kết quả: có IP để kết nối
    * Liên quan đến: Domain, DNS, Latency, OSI Layer: Tầng 7 (Application) → Tầng 3 (Network)
4. **Trình duyệt thiết lập kết nối bảo mật HTTPS (TLS handshake)**

   * Vì là https:// nên trình duyệt phải:
        - Mở kết nối TCP đến IP của Shopee (qua cổng 443)
        - Bắt tay TLS → kiểm tra SSL certificate:
            + Tên miền có đúng shopee.vn không?
            + Có phải do CA uy tín cấp không?
            + Chứng chỉ còn hạn không?
        - Tạo kênh mã hóa an toàn
    * Giao thức: TLS (SSL)
    * OSI layer: Tầng 6 – Presentation
5. **Gửi HTTP Request đến server Shopee**

   * Qua HTTP/HTTPS -> Ứng dụng: Tầng 7 – Application - Tầng 4 – TCP đảm bảo không mất gói
6. **Server Shopee xử lý & phản hồi**

   *  Gửi lại qua HTTP Response. Server xác thực request, Truy xuất dữ liệu: sản phẩm hot, banner, user info nếu login và trả về HTML, CSS, JS, ảnh, JSON…
   * Băng thông Bandwidth ảnh hưởng tốc độ tải ảnh, video
7. **Trình duyệt dựng giao diện trang Shopee**

   * Dựa vào HTML, CSS, JS, media từ server
   * Nếu có gọi thêm API (sản phẩm hot, khuyến mãi, v.v.) → lại thực hiện request HTTP nhỏ lẻ (gọi nhiều API phụ)
   * Nếu latency cao → ảnh hưởng đến tốc độ phản hồi

| OSI Layer       | Vai trò cụ thể trong truy cập Shopee      |
| --------------- | ----------------------------------------- |
| 7. Application  | HTTP, HTTPS, API, HTML/JS/CSS             |
| 6. Presentation | TLS/SSL mã hóa dữ liệu                    |
| 5. Session      | Duy trì kết nối, giữ phiên login          |
| 4. Transport    | TCP/UDP – Đảm bảo truyền đủ & đúng        |
| 3. Network      | Định tuyến qua IP – từ client đến Shopee  |
| 2. Data Link    | Giao tiếp trong mạng nội bộ (MAC, switch) |
| 1. Physical     | Tín hiệu truyền qua cáp/Wi-Fi             |

Tóm tắt luồng: 
* [Client] Gõ https://shopee.vn
   ↓
* DNS phân giải domain → IP (13.250.232.99)
   ↓
* TCP + TLS bắt tay bảo mật (SSL Certificate)
   ↓
* Gửi HTTP request: GET /
   ↓
* [Server Shopee] xử lý → trả HTML, ảnh, JS...
   ↓
* Hiển thị trang Shopee → gọi thêm API sản phẩm

**Sự cố mạng ???**
* Nếu người dùng ko truy cập được trang web shopee.vn có thể do
    - DNS server không phản hồi hoặc tên miền sai → Không phân giải được IP
    - IP bị chặn bởi tường lửa hoặc cổng mạng nội bộ
    - Kết nối mạng người dùng không ổn định hoặc bị mất
    - Chứng chỉ SSL không hợp lệ → HTTPS handshake thất bại
* Nếu người dùng truy cập trang web chậm, load hình ảnh chậm có thể do
    - Độ trễ (Latency) cao: Do định tuyến mạng không tối ưu hoặc server đặt quá xa địa lý
    - Băng thông thấp (Low Bandwidth): Mạng bị nghẽn, người dùng đang dùng chung kết nối với nhiều người khác
    - Server Shopee quá tải: Không đủ tài nguyên để xử lý nhanh các request
    - CDN không hoạt động hiệu quả: Ảnh/tệp tĩnh chưa được phân phối gần client
---


