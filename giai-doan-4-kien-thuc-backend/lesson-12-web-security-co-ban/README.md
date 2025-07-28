# Buổi 12: Web security cơ bản
## OWASP Top 10 (2021)
### **A01 Broken Access Control** - Lỗi kiểm soát truy cập
- Access Control là việc giới hạn hành động của người dùng dựa trên quyền hạn của họ.
Khi lỗi xảy ra, người dùng có thể vượt quyền để Xem/sửa/xóa dữ liệu không thuộc về họ, Thực thi hành động admin (dù là user) và Truy cập API nhạy cảm chỉ qua việc đoán URL
| Hành vi                      | Mô tả                                                             |
| ---------------------------- | ----------------------------------------------------------------- |
|  **Force Browsing**        | User thường truy cập URL `/admin/deleteUser?id=3` mà không bị cấm |
|  **Parameter Tampering**   | Đổi `user_id=123` thành `user_id=1` → xem dữ liệu người khác      |
|  **Privilege Escalation**  | Dùng token/ID session để “giả mạo” vai trò admin                  |
|  **JWT Manipulation**      | Tự chỉnh JWT, đổi `role: USER` thành `ADMIN`                      |
|  **CORS Misconfiguration** | Cho phép frontend từ domain lạ gọi API backend                    |
|  **Directory Traversal**   | Truy cập file `.git`, `.env`, `/backup.sql` trực tiếp             |

- Cách Phòng tránh: 
    - Deny by default -> Mặc định từ chối mọi truy cập, chỉ cấp quyền khi cần
    - Check quyền phía server -> Không dựa vào frontend (JS, URL...) để xác thực
    - Dùng middleware kiểm soát truy cập -> VD: Spring Security, Laravel middleware, Express.js RBAC
    - Xác thực ownership -> Không để user có thể xem bất kỳ ID nào nếu không phải của họ
    - Invalidate token/session sau logout -> JWT nên ngắn hạn hoặc hỗ trợ revoke theo chuẩn OAuth
    - Viết test cho kiểm soát truy cập -> Có integration test để đảm bảo logic role hoạt động đúng

### **A02 Cryptographic Failures** - Lỗi mã hóa và bảo mật dữ liệu
- Mô tả: Lỗi Cryptographic Failures không chỉ là do thuật toán yếu, mà còn có thể do:
    - Truyền dữ liệu không mã hóa (plain HTTP)
    - Dùng các hàm mã hóa lỗi thời (MD5, SHA1, ECB…)
    - Lưu khóa mã hóa trong code (hardcoded key)
    - Quản lý khóa sai (reuse, không xoay vòng, không xóa đúng)
    - Thiếu xác thực mã hóa
    - Hash password quá yếu
    
| Lỗi thường gặp                                                | Mô tả                                         |
| ------------------------------------------------------------- | --------------------------------------------- |
|  **Truyền dữ liệu qua HTTP**                                | Session cookie bị đánh cắp qua WiFi công cộng |
|  **Hash password không có salt**                            | Dễ bị tấn công bằng rainbow table             |
|  **Dùng key cố định hoặc sinh key yếu**                     | Hacker có thể đoán hoặc brute-force           |
|  **Dùng MD5 hoặc SHA1**                                     | Các thuật toán hash này có thể bị phá dễ dàng |
|  **Lưu thông tin nhạy cảm (số thẻ, mật khẩu) không mã hóa** | Hacker truy cập DB là lộ thông tin ngay       |
|  **Dùng cùng IV (initialization vector) cho AES nhiều lần** | Làm lộ mẫu mã hóa → giải mã dễ dàng           |

- Phòng tránh: 
    - Luôn dùng HTTPS (TLS) -> Kể cả với traffic nội bộ
    - Dùng thuật toán mã hóa mạnh -> AES-GCM, RSA-2048 trở lên, SHA-256+
    - Không dùng MD5/SHA1 -> Sử dụng SHA-256, bcrypt, Argon2...
    - Hash mật khẩu đúng cách -> Dùng bcrypt, Argon2, PBKDF2 có salt và work factor cao
    - Không lưu khóa trong code -> Sử dụng key vault hoặc env secrets
    - Xoay vòng khóa định kỳ (Key Rotation) -> Đảm bảo key cũ không bị lộ gây ảnh hưởng lâu dài
    - Sử dụng mã hóa có xác thực (Authenticated Encryption) -> Như AES-GCM thay vì AES-CBC
    - Tắt cache cho dữ liệu nhạy cảm -> Tránh bị đọc lại từ trình duyệt hoặc proxy
    - Xác thực chứng chỉ đúng cách -> Tránh tấn công MITM (Man-in-the-Middle)
    - Không dùng thuật toán ngẫu nhiên không bảo mật (Math.random)

### **A03 Injection** - Chèn mã độc vào truy vấn
- Mô tả: Injection xảy ra khi:
    - Ứng dụng không lọc hoặc xác thực đầu vào người dùng
    - Dữ liệu từ người dùng được ghép trực tiếp vào truy vấn động
    - Có thể bị lợi dụng để Truy cập/trộm dữ liệu, Xóa/sửa bảng, Chạy mã độc hệ điều hành (OS command), Điều khiển hành vi ứng dụng
- Ví dụ lỗi:
    - SQL Injection đơn giản
        - String query = "SELECT * FROM accounts WHERE custID='" + request.getParameter("id") + "'";
        - Attacker gửi: ?id=' OR '1'='1
        - Kết quả SQL: SELECT * FROM accounts WHERE custID='' OR '1'='1'
    → Trả về toàn bộ tài khoản vì điều kiện luôn đúng.

    - Time-based SQL Injection
    http://example.com/app/accountView?id=' UNION SELECT SLEEP(10);--
    → Nếu server phản hồi chậm đúng 10 giây → attacker xác định lỗ hổng tồn tại.

    - OS Command Injection
        - os.system("ping " + user_input)
        - Attacker nhập: 127.0.0.1 && rm -rf /

    - NoSQL Injection (MongoDB)
        - db.users.find({ username: userInput.username, password: userInput.password })
        - Attacker gửi JSON: { "username": { "$ne": null }, "password": { "$ne": null } }
    → Bypass xác thực hoàn toàn.

- Cách phòng tránh: 
    - Sử dụng Prepared Statements (Parameterized Queries) 
    - Dùng ORM cẩn thận -> Hibernate, Sequelize... có thể bị khai thác nếu dùng HQL/raw query sai cách
    - Escape đặc biệt (nếu cần) -> Dùng escape đúng với từng loại interpreter (SQL, LDAP, shell...)
    - Hạn chế chức năng input đặc biệt (white-list) -> Nếu chỉ chấp nhận số thì reject bất kỳ ký tự nào khác
    - Không bao giờ tin tưởng dữ liệu người dùng dù là headers, cookies, body, URL params
    - Sử dụng stored procedures an toàn -> Không chứa truy vấn động bên trong
    - Dùng thư viện bảo mật có sẵn -> ORM, query builder đáng tin cậy như JPA, Dapper, Knex, Prisma...

### **A04 Insecure Design** - Thiết kế không an toàn
- Insecure Design xuất hiện khi:
    - Phần mềm thiếu kiểm soát an toàn phù hợp trong quá trình thiết kế
    - Thiếu tư duy thẩm định rủi ro, mô hình đe dọa (threat modeling) hoặc không tuân thủ tiêu chuẩn như NIST, OWASP ASVS
    - Lẫn lộn giữa lỗi thiết kế và lỗi triển khai (insecure implementation)

- Ví dụ: 
    - Chức năng khôi phục tài khoản dựa vào "câu hỏi bảo mật" như: "Tên con mèo đầu tiên của bạn?"
    → Bị NIST 800-63b và OWASP cấm vì ai cũng có thể biết được.
    Giải pháp: Dùng xác thực đa yếu tố hoặc email xác nhận an toàn hơn.
    - Booking ảo không giới hạn
        - Một hệ thống đặt vé nhóm cho phép tối đa 15 người mới yêu cầu đặt cọc.
        - Attacker gửi yêu cầu book 600 ghế ở nhiều rạp khác nhau → Gây thiệt hại lớn cho doanh nghiệp.
        - Giải pháp: Thiết kế giới hạn logic rõ ràng & xác thực các luồng nghiệp vụ.
    - Scalper bot mua sạch hàng
        - Trang thương mại điện tử không có kiểm soát chống bot → Bot mua hết hàng hot (VD: card màn hình), tạo bức xúc lớn với người tiêu dùng.
        - Giải pháp: Thiết kế luồng kiểm tra chống bot từ đầu, không chỉ rely vào CAPTCHA.

- Cách phòng tránh: 
    - Thiết lập Secure Development Lifecycle (SDLC) -> Bao gồm thiết kế, threat modeling, kiểm thử, kiểm tra bảo mật
    - Sử dụng thư viện/kiểu thiết kế an toàn có sẵn -> Design pattern bảo mật, kiến trúc chuẩn OWASP
    -  Tích hợp Threat Modeling -> Xem xét các dòng dữ liệu và điểm truy cập có thể bị tấn công
    - Thêm ngữ cảnh bảo mật vào User Story -> Viết rõ yêu cầu bảo mật cho từng tính năng
    - Kiểm tra hợp lý tại từng tầng (tier) -> Frontend, backend, DB... đều cần validation và kiểm soát
    - Kiểm thử tính hợp lệ của logic nghiệp vụ -> Tạo Use-case và Misuse-case
    - Phân tách tenant và tầng mạng rõ ràng -> Đảm bảo cô lập dữ liệu và người dùng
    - Giới hạn tài nguyên mỗi người dùng/dịch vụ -> Tránh spam, abuse, DoS nội bộ

### **A05 Security Misconfiguration** - Cấu hình bảo mật sai
- Misconfiguration xảy ra khi:
    - Thiếu các thiết lập bảo mật hợp lý
    - Có tính năng dư thừa được bật (port, service, demo page…)
    - Mật khẩu mặc định chưa thay đổi
    - Thông báo lỗi quá chi tiết (ví dụ: stack trace)
    - Sử dụng cấu hình mặc định không an toàn trong framework như Spring, Struts, ASP.NET
    - Không bật các security headers quan trọng
    - Hệ thống lỗi thời, không cập nhật bản vá mới

- Ví dụ: 
    - Mật khẩu mặc định
        - Server vẫn giữ lại các ứng dụng mẫu (sample app) từ khi cài đặt. Một trong số đó là admin console, đăng nhập được bằng tài khoản mặc định như admin/admin.
        - Giải pháp: Xóa ứng dụng mẫu, đổi mật khẩu mặc định
    
    -  Directory Listing mở
        - Không tắt directory listing → Attacker có thể truy cập các thư mục chứa .class, tải về, decompile, tìm ra lỗi truy cập trái phép.
        - Giải pháp: Vô hiệu hóa directory listing, giới hạn truy cập thư mục qua .htaccess hoặc cấu hình server.

    - Stack Trace lộ ra ngoài
        - Khi có lỗi xảy ra, trang web trả về thông tin chi tiết lỗi nội bộ (stack trace, path...), vô tình tiết lộ phiên bản framework, database và gợi ý cho attacker cách tấn công.
        - Giải pháp: Hiển thị lỗi chung cho người dùng, ghi chi tiết vào file log nội bộ.

    - S3 bucket công khai
        - Cloud bucket có quyền truy cập mặc định công khai (public) → Dữ liệu nhạy cảm bị lộ trên Internet.
        - Giải pháp: Cấu hình lại quyền truy cập tài nguyên cloud, chỉ cho phép người dùng hoặc role cụ thể truy cập.

- Cách phòng tránh: 
    - Hardening toàn bộ hệ thống -> Gỡ bỏ tính năng không cần thiết, đóng port không dùng, tắt dịch vụ mặc định
    - Quy trình cài đặt bảo mật lặp lại (automated) -> Dễ tái sử dụng khi triển khai môi trường mới (Dev/QA/Prod)
    - Tách biệt các môi trường -> Mỗi môi trường dùng cấu hình riêng biệt và credentials riêng
    - Sử dụng phiên bản phần mềm mới nhất -> Update OS, library, framework kịp thời
    -  Kiểm tra định kỳ cấu hình bảo mật -> Dùng script hoặc công cụ kiểm tra tự động
    - Thiết lập HTTP Security Headers -> Bao gồm: Content-Security-Policy, X-Frame-Options, Strict-Transport-Security, v.v.
    - Giới hạn thông tin trả về khi lỗi xảy ra -> Hiển thị lỗi chung chung cho người dùng cuối
    - Kiểm soát quyền truy cập cloud (S3, Azure Blob...) -> Không để bucket ở chế độ public trừ khi thật sự cần thiết
    - Sử dụng công cụ kiểm thử cấu hình -> VD: Mozilla Observatory, OWASP ZAP Baseline Scan, DevSecOps scanner

### **A06 Vulnerable and Outdated Components**
- Ứng dụng dễ bị khai thác nếu:
    - Không biết rõ phiên bản của tất cả thành phần sử dụng (framework, library, runtime…)
    - Sử dụng các thành phần không còn hỗ trợ hoặc lỗi thời
    - Không scan lỗ hổng thường xuyên hoặc không cập nhật thông tin CVE
    - Không có quy trình cập nhật/phát hiện/thay thế bản vá
    - Không kiểm tra tương thích sau khi nâng cấp hoặc cập nhật
    - Cấu hình của thành phần sử dụng không được bảo mật

- Ví dụ:
    - Lỗi RCE Struts2
        - CVE-2017-5638 là lỗi Remote Code Execution trong Apache Struts2 cho phép kẻ tấn công thực thi mã độc trên server. Nhiều tổ chức lớn bị tấn công vì không cập nhật Struts2 dù bản vá đã có từ lâu.
        - Giải pháp: Cập nhật thư viện Struts2 hoặc thay thế bằng thư viện hiện đại khác.
    - IoT không thể vá
        - Các thiết bị IoT (ví dụ: camera, thiết bị y tế) thường không có khả năng tự cập nhật. Nếu chạy phần mềm lỗi thời, dễ trở thành điểm tấn công.
        - Giải pháp: Giám sát bằng firewall/IDS/IPS, thiết lập “virtual patching” hoặc cô lập mạng nội bộ cho thiết bị.
    
    - Heartbleed còn tồn tại sau nhiều năm
        - Lỗ hổng nổi tiếng Heartbleed (2014) vẫn được tìm thấy nhiều năm sau qua công cụ như Shodan.io.
        - Giải pháp: Kiểm tra định kỳ với các scanner để phát hiện lỗ hổng đã biết (CVE).

- Cách phòng tránh:
    - Xóa bỏ thành phần không dùng -> Gỡ bỏ file, thư viện, plugin dư thừa
    - Theo dõi & kiểm kê tất cả component -> Ghi lại tên + phiên bản cho cả frontend & backend
    - Dùng công cụ kiểm tra định kỳ -> Ví dụ: OWASP Dependency-Check, npm audit, retire.js, Snyk, GitHub Advisory
    - Chỉ sử dụng nguồn chính thức -> Không tải thư viện từ trang không rõ nguồn gốc. Ưu tiên thư viện được ký số
    - Tự động hóa quy trình kiểm tra -> Tích hợp công cụ SCA (Software Composition Analysis) vào CI/CD
    - Theo dõi cảnh báo bảo mật (CVE) -> Đăng ký các mailing list, hoặc dùng bot tự quét
    - Cân nhắc cập nhật thay vì vá chậm -> Hạn chế mô hình vá lỗi theo tháng hoặc quý quá chậm trễ
    - Dùng phiên bản hỗ trợ dài hạn (LTS) -> Với framework, OS hoặc nền tảng quan trọng



### **A07 Identification and Authentication Failures** - Lỗi Xác Thực và Nhận Diện
- Ứng dụng dễ bị tấn công xác thực nếu:
    - Cho phép tấn công credential stuffing (dùng danh sách mật khẩu hợp lệ)
    - Cho phép brute-force hoặc không giới hạn lần đăng nhập
    - Sử dụng mật khẩu mặc định (admin/admin, 123456…)
    - Cơ chế lấy lại mật khẩu yếu (dựa vào câu hỏi bảo mật)
    - Mật khẩu lưu trữ ở dạng plaintext hoặc hash yếu (xem thêm A02)
    - Không sử dụng multi-factor authentication (MFA)
    - Session ID lộ qua URL
    - Session không được làm mới sau đăng nhập
    - Session không bị hủy sau logout hoặc timeout

- Ví dụ: 
    - Credential Stuffing
        - Hacker dùng tập hợp email & mật khẩu từ vụ rò rỉ (ví dụ: từ LinkedIn hoặc Facebook) để thử đăng nhập hàng loạt vào ứng dụng không có giới hạn login.
        - Giải pháp: Triển khai CAPTCHA, khóa tài khoản tạm thời, hoặc sử dụng dịch vụ chống credential stuffing.
    - Không có MFA
        - Người dùng dùng mật khẩu yếu, và hacker đoán được. Do không có xác minh 2 bước, attacker đăng nhập dễ dàng.
        - Giải pháp: Bắt buộc MFA (qua OTP, email, app như Google Authenticator…).
    - Session không timeout
        - Người dùng đăng nhập tại quán net nhưng chỉ đóng tab thay vì logout. Kẻ tấn công mở lại tab hoặc back lại, session vẫn hoạt động.
        - Giải pháp: Thiết lập timeout session (ví dụ: tự logout sau 10 phút không hoạt động) và hủy token sau khi logout.

- Cách phòng tránh: 
    - Triển khai MFA -> Bắt buộc xác minh 2 bước để giảm thiểu rủi ro rò rỉ mật khẩu
    - Không dùng mật khẩu mặc định -> Đặc biệt là tài khoản admin — đổi trước khi triển khai
    - Kiểm tra mật khẩu yếu	-> So sánh với danh sách top 10.000 mật khẩu tệ hại (ví dụ: từ HaveIBeenPwned)
    - Định dạng chính sách mật khẩu theo NIST 800-63b -> Không bắt đổi mật khẩu liên tục hoặc yêu cầu quá phức tạp, thay vào đó tập trung vào bảo mật thực tế
    - Ẩn thông tin khi login thất bại -> Không phân biệt giữa “tài khoản không tồn tại” và “mật khẩu sai”
    - Giới hạn số lần đăng nhập thất bại	-> Ví dụ: khóa tạm thời tài khoản sau 5 lần sai
    - Dùng session ID ngẫu nhiên có độ phức tạp cao	 -> Không gán ID dễ đoán hoặc lưu ID trong URL
    - Hủy session sau logout và timeout hợp lý -> Xác định timeout phù hợp (ví dụ: 15 phút không hoạt động hoặc 1 giờ tuyệt đối)


### **A08 Software and Data Integrity Failures** - Lỗi Tích Hợp và Tính Toàn Vẹn Phần Mềm/Dữ Liệu
- Lỗ hổng này xảy ra khi:
    - Cập nhật phần mềm không có xác minh chữ ký số
    - CI/CD pipeline không được kiểm soát quyền truy cập, dễ bị chèn mã độc
    - Dùng plugin, thư viện hoặc nội dung từ nguồn không tin cậy
    - Không kiểm tra toàn vẹn khi nhận dữ liệu từ client
    - Dùng serialization/deserialization không an toàn dẫn đến thực thi mã từ xa

- Ví dụ:
    - Cập nhật phần mềm không có chữ ký
        - Một số thiết bị IoT như router hoặc camera không xác minh firmware update bằng chữ ký số. Hacker chèn firmware độc hại vào bản cập nhật, và nó được cài đặt vào hàng ngàn thiết bị.
        - Phòng tránh: Chỉ cho phép cập nhật phần mềm đã được ký bằng chữ ký số xác thực.
    - SolarWinds Orion Breach
        - Một trong các vụ tấn công APT nghiêm trọng nhất. Hacker xâm nhập hệ thống build CI/CD của SolarWinds, và chèn mã độc vào phần mềm hợp lệ, sau đó cập nhật này được phân phối đến hơn 18.000 tổ chức, trong đó có Microsoft, FireEye, chính phủ Mỹ…
        - Phòng tránh: Kiểm soát truy cập vào CI/CD pipeline, xác minh checksum/hash/chữ ký cho mọi build, mã nguồn, và artifacts.

    - Insecure Deserialization
        - Một ứng dụng React truyền dữ liệu người dùng đã serialize (mã hóa object Java) qua HTTP. Hacker phát hiện chuỗi "rO0" (dấu hiệu của Java serialization), sử dụng công cụ Java Serial Killer để chèn payload và chiếm quyền điều khiển server từ xa.
        - Phòng tránh: Không bao giờ deserialize dữ liệu từ nguồn không tin cậy trừ khi đã được xác minh toàn vẹn.

- Cách phòng tránh: 
    - Dùng chữ ký số cho cập nhật phần mềm -> Xác minh mỗi bản cập nhật đến từ nguồn đáng tin cậy và không bị chỉnh sửa
    - Kiểm tra toàn vẹn dữ liệu -> Dùng HMAC, SHA-256, hoặc digital signature trước khi deserialize dữ liệu
    - Sử dụng kho thư viện đáng tin cậy -> Nên dùng registry riêng hoặc proxy kiểm soát npm, pip, Maven… (VD: Nexus, Artifactory)
    - Bảo vệ CI/CD pipeline -> Kiểm soát quyền, tách biệt môi trường, audit log đầy đủ
    - Kiểm tra và audit mã nguồn trước khi build -> Không để lập trình viên commit trực tiếp vào nhánh build nếu không review
    - Không dùng serialize/deserialization không an toàn -> Tránh Java native serialization hoặc PHP unserialize() – thay vào đó dùng định dạng an toàn như JSON kèm chữ ký

### **A09 Security Logging and Monitoring Failures**
- Bạn có thể dễ dàng bị tấn công hoặc không phát hiện ra tấn công nếu:
    - Không ghi lại các sự kiện quan trọng như đăng nhập, thay đổi quyền, lỗi xác thực...
    - Không giám sát log theo thời gian thực hoặc không cảnh báo khi xảy ra sự cố.
    - Log chỉ lưu cục bộ và dễ bị xóa/thay đổi mà không có dấu vết.
    - Các lỗi ứng dụng không tạo ra log, hoặc log không đầy đủ, không rõ ràng.
    - Hệ thống không có cảnh báo tự động, hoặc không có quy trình phản ứng khi phát hiện bất thường.
    - Log chứa thông tin nhạy cảm (mật khẩu, token, key API) dễ bị lộ hoặc khai thác.
    - Log không được mã hóa/encode đúng cách, dễ bị log injection.

- Ví dụ
    - Mất dữ liệu sức khỏe trẻ em trong 7 năm
        - Một tổ chức y tế của trẻ em bị tấn công và mất dữ liệu của 3.5 triệu trẻ em. Họ không biết gì cho đến khi một bên thứ ba thông báo. Lý do: không có hệ thống ghi log hoặc giám sát để phát hiện tấn công.
        - Bài học: Ghi log là điều kiện cần để phát hiện sớm và điều tra sau khi tấn công xảy ra.
    - Hãng hàng không Ấn Độ bị rò rỉ dữ liệu
        - Một nhà cung cấp hạ tầng bị tấn công, dẫn đến rò rỉ dữ liệu cá nhân và thẻ tín dụng trong hơn 10 năm. Tổ chức không tự phát hiện ra mà phải chờ được báo từ bên ngoài.
        - Bài học: Giao trách nhiệm logging/monitoring cho bên thứ ba mà không giám sát họ cũng nguy hiểm như không làm gì.
    - Hãng bay châu Âu bị phạt GDPR
        - Hacker khai thác lỗ hổng ở hệ thống thanh toán, đánh cắp hơn 400.000 bản ghi thanh toán. Do không có giám sát và cảnh báo kịp thời, thiệt hại lớn đã xảy ra. Hãng bị phạt £20 triệu theo GDPR.
        - Bài học: Cảnh báo kịp thời có thể giúp hạn chế thiệt hại và tránh án phạt.

- Cách phòng tránh
    - Ghi log tất cả hành động -> Đăng nhập, đăng xuất, thay đổi quyền, thất bại đăng nhập, sửa dữ liệu…
    - Không lưu dữ liệu nhạy cảm vào log -> Không ghi mật khẩu, token, API key, thông tin thẻ vào log
    - Chuẩn hóa và encode log -> Dùng định dạng chuẩn như JSON hoặc Syslog, và đảm bảo output neutralization để ngăn log injection (CWE-117)
    - Giám sát và cảnh báo tự động -> Dùng công cụ như ELK Stack, Splunk, SIEM, Sentry để giám sát log thời gian thực
    - Đảm bảo tính toàn vẹn log -> Ghi log vào append-only storage, dùng hash hoặc chữ ký để tránh sửa/xóa
    - Có quy trình phản ứng sự cố (IRP) -> Tuân theo chuẩn như NIST 800-61r2, có nhóm IR sẵn sàng khi bị tấn công
    - Kích hoạt alert cho scan và tấn công phổ biến -> OWASP ZAP, Burp Suite, DAST, credential stuffing... phải được phát hiện và cảnh báo


### **A10 Server‑Side Request Forgery (SSRF)**
- Lỗi SSRF thường xảy ra khi ứng dụng web lấy tài nguyên từ xa dựa trên URL do người dùng nhập mà không kiểm tra kỹ càng. SSRF đặc biệt nguy hiểm trong các hệ thống cloud hoặc kiến trúc phân tán vì:
    - Có thể truy cập vào mạng nội bộ vốn không cho phép truy cập từ bên ngoài.
    - Có thể truy cập các dịch vụ nhạy cảm như cloud metadata, Docker APIs, hoặc internal dashboards.

- Ví dụ: 
    - Quét cổng mạng nội bộ
        - Ứng dụng cho phép người dùng nhập URL để lấy ảnh. Hacker nhập:
        http://internal-service:8080
        - và phân tích thời gian phản hồi để xác định cổng có mở hay không → Port Scanning từ bên trong firewall.
    - Đọc file nhạy cảm
        - Nhập URL: file:///etc/passwd hoặc http://localhost:8080/admin
        → Đọc thông tin hệ thống hoặc truy cập dashboard nội bộ mà bình thường người dùng không truy cập được.
    - Truy cập metadata cloud
        - Trên AWS, GCP hoặc Azure, địa chỉ metadata thường là:
        http://169.254.169.254/latest/meta-data/
        - Nếu ứng dụng không lọc URL, attacker có thể lấy được Thông tin cấu hình máy chủ,
        Access tokens, Credentials IAM
    - Tấn công dịch vụ nội bộ: Sau khi truy cập được một API nội bộ, attacker thực hiện Remote Code Execution (RCE) hoặc Từ chối dịch vụ (DoS) bằng cách gửi payload đặc biệt qua SSRF.

- Cách phòng tránh: 
    - Mạng (Network Layer): 
        - Segmentation: tách riêng chức năng truy cập tài nguyên từ xa ra một mạng khác
        - "Deny by default": chỉ cho phép các IP/port nội bộ cần thiết
        -  Log mọi traffic chặn/cho phép
    - Ứng dụng (App Layer)	
        - Chỉ cho phép các URL từ danh sách trắng (whitelist)
        - Không dùng deny list hoặc regex – rất dễ bị bypass
        - Kiểm tra schema (http, https), port, hostname
        - Không gửi toàn bộ phản hồi về cho client
        - Không cho phép redirect HTTP từ URL
    - Khác 
        - Không chạy dịch vụ quan trọng (như OpenID, dashboard admin) trên frontend
        - Nếu có frontend riêng biệt, nên bảo vệ bằng VPN hoặc encryption nội bộ

