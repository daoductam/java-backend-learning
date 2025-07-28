# Buổi 8: Xử lý ngoại lệ/ retry
## Mục tiêu:
- Hiểu được exception, tránh việc bị crash ứng dụng
- Khi nào thì nên dùng retry
## Một số lỗi exception khác (lỗi kết nối, mất mạng,...)
- Exception liên quan đến mạng và kết nối
| Exception                  | Mô tả                                                        | Khi nào xảy ra                    |
| -------------------------- | ------------------------------------------------------------ | --------------------------------- |
| `SocketTimeoutException`   | Timeout khi chờ kết nối đến server (client → server)         | Gọi API nhưng không phản hồi kịp  |
| `ConnectException`         | Không kết nối được đến host (thường do server chết, DNS lỗi) | Server không online, sai IP/port  |
| `UnknownHostException`     | Không phân giải được domain name                             | Gọi đến host sai hoặc mất mạng    |
| `SSLHandshakeException`    | Lỗi khi bắt tay SSL/TLS                                      | HTTPS nhưng chứng chỉ sai/expired |
| `HttpServerErrorException` | Server trả mã lỗi 5xx                                        | Lỗi phía server (500, 502, 503)   |
| `HttpClientErrorException` | Client gửi sai, lỗi 4xx                                      | Sai URL, thiếu header, 401, 403   |
| `IOException`              | I/O lỗi chung chung, có thể do kết nối mạng                  | Mạng yếu, mất mạng, socket đóng   |

- Exception liên quan đến Cơ sở dữ liệu
| Exception                        | Mô tả                     | Khi nào xảy ra                |
| -------------------------------- | ------------------------- | ----------------------------- |
| `SQLException`                   | Lỗi thao tác với DB       | Kết nối DB lỗi, SQL sai       |
| `DataAccessException` *(Spring)* | Lỗi chung khi thao tác DB | DB unavailable hoặc query sai |
| `TransactionTimedOutException`   | Giao dịch quá thời gian   | Lock lâu, DB quá tải          |

- Exception do logic / dữ liệu đầu vào
| Exception                   | Mô tả                        | Khi nào xảy ra               |
| --------------------------- | ---------------------------- | ---------------------------- |
| `NullPointerException`      | Gọi hàm trên biến null       | Quên kiểm tra null           |
| `IllegalArgumentException`  | Đối số không hợp lệ          | Gọi hàm với giá trị sai      |
| `NumberFormatException`     | Chuyển chuỗi → số thất bại   | `Integer.parseInt("abc")`    |
| `IndexOutOfBoundsException` | Truy cập index không tồn tại | List/Array bị out of range   |
| `ValidationException`       | Lỗi validate dữ liệu         | Dữ liệu đầu vào không hợp lệ |

## Mô tả tất cả các lỗi exception trong Java
### I. Checked Exceptions (phải xử lý)
- 1. I/O và File
| Exception                                   | Mô tả                       |
| ------------------------------------------- | --------------------------- |
| `IOException`                               | Lỗi nhập/xuất chung         |
| `FileNotFoundException`                     | Không tìm thấy file         |
| `EOFException`                              | Đọc file quá cuối           |
| `InterruptedIOException`                    | Thread bị ngắt khi đang I/O |
| `UnsupportedEncodingException`              | Encoding sai                |
| `MalformedURLException`                     | URL sai định dạng           |
| `SocketException`, `SocketTimeoutException` | Lỗi khi socket giao tiếp    |
| `UnknownHostException`                      | Không phân giải được domain |
- 2. Database (JDBC)
| Exception                                  | Mô tả                           |
| ------------------------------------------ | ------------------------------- |
| `SQLException`                             | Lỗi khi thao tác SQL            |
| `SQLTimeoutException`                      | Giao dịch SQL timeout           |
| `SQLIntegrityConstraintViolationException` | Vi phạm ràng buộc (dupe key...) |
- 3. Class & Reflect
| Exception                                       | Mô tả                     |
| ----------------------------------------------- | ------------------------- |
| `ClassNotFoundException`                        | Không tìm thấy class      |
| `NoSuchMethodException`, `NoSuchFieldException` | Phản xạ sai               |
| `InstantiationException`                        | Không thể khởi tạo object |
| `IllegalAccessException`                        | Truy cập không hợp lệ     |

### II. Unchecked Exceptions (RuntimeException)
- 1. Cơ bản
| Exception                         | Mô tả                         |
| --------------------------------- | ----------------------------- |
| `NullPointerException`            | Gọi hàm trên biến null        |
| `IllegalArgumentException`        | Đối số sai logic              |
| `IllegalStateException`           | Trạng thái không hợp lệ       |
| `IndexOutOfBoundsException`       | Truy cập mảng, list sai index |
| `ArrayIndexOutOfBoundsException`  | Mảng                          |
| `StringIndexOutOfBoundsException` | Chuỗi                         |
| `ArithmeticException`             | Chia cho 0                    |
| `NumberFormatException`           | Chuyển chuỗi sang số sai      |
| `ClassCastException`              | Ép kiểu sai                   |
| `UnsupportedOperationException`   | Gọi hàm không được hỗ trợ     |
- 2. Collection & Concurrent
| Exception                         | Mô tả                            |
| --------------------------------- | -------------------------------- |
| `ConcurrentModificationException` | Thay đổi Collection khi đang lặp |
| `NoSuchElementException`          | Gọi next() khi không còn phần tử |
| `EmptyStackException`             | Stack rỗng                       |
- 3. Threading & Multithreading
| Exception                          | Mô tả                           |
| ---------------------------------- | ------------------------------- |
| `IllegalMonitorStateException`     | `wait()`/`notify()` sai         |
| `InterruptedException` *(checked)* | Thread bị interrupt             |
| `RejectedExecutionException`       | Thread pool không nhận task mới |
| `TimeoutException` *(checked)*     | Timeout xảy ra                  |

### III. Error (không nên xử lý – do hệ thống gây ra)
| Error                  | Mô tả                     |
| ---------------------- | ------------------------- |
| `OutOfMemoryError`     | Hết RAM                   |
| `StackOverflowError`   | Gọi đệ quy vô hạn         |
| `VirtualMachineError`  | JVM lỗi nặng              |
| `AssertionError`       | Lỗi do assert             |
| `NoClassDefFoundError` | JVM không load được class |

###  IV. Một số Exception khác trong Spring/Backend
| Exception                         | Thư viện                  | Mô tả                   |
| --------------------------------- | ------------------------- | ----------------------- |
| `HttpClientErrorException`        | Spring                    | HTTP 4xx                |
| `HttpServerErrorException`        | Spring                    | HTTP 5xx                |
| `DataAccessException`             | Spring                    | Bao lỗi khi truy cập DB |
| `TransactionException`            | Spring                    | Lỗi transaction         |
| `ValidationException`             | Hibernate/Bean Validation | Dữ liệu không hợp lệ    |
| `MethodArgumentNotValidException` | Spring                    | @Valid bị lỗi           |

## Global Exception Handler
### ĐỊnh nghĩa 
- Global Exception Handler là một lớp trung tâm giúp bắt và xử lý mọi exception phát sinh trong toàn bộ ứng dụng theo cách nhất quán, rõ ràng và có thể log chi tiết lỗi.
### Tại sao cần Global Exception Handler
- Tránh phải viết try-catch rải rác khắp nơi.
- Gom tất cả lỗi vào một chỗ để dễ log, phân tích và trả về cho client.
- Chuẩn hóa response lỗi (có mã lỗi, message, timestamp...).
### Cách triển khai Global Exception Handler trong Spring Boot
- 1. Tạo lớp GlobalExceptionHandler với @ControllerAdvice và @ExceptionHandler
@RestControllerAdvice // Kết hợp @ControllerAdvice + @ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationError(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
            .stream().map(err -> err.getField() + ": " + err.getDefaultMessage())
            .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(new ErrorResponse("VALIDATION_ERROR", errors.toString()));
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBusinessException(BusinessException ex) {
        return ResponseEntity.badRequest().body(new ErrorResponse("BUSINESS_ERROR", ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralException(Exception ex) {
        ex.printStackTrace(); // log kỹ nếu cần
        return ResponseEntity.status(500).body(new ErrorResponse("INTERNAL_ERROR", "Đã xảy ra lỗi hệ thống"));
    }
}
- 2. Tạo class ErrorResponse để chuẩn hóa thông tin trả về
public class ErrorResponse {
    private String code;
    private String message;
    private LocalDateTime timestamp = LocalDateTime.now();

    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    // getters/setters
}
- 3. Tạo custom exception (nếu cần)
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
## Try catch    
- Chỉ bắt lỗi bạn biết cách xử lý.
- Đừng catch rồi im lặng (catch (e) {}) → phải log, hoặc rethrow lại.
- Tránh catch (Exception e) nếu không cần → dễ che giấu lỗi nghiêm trọng.
## RETRY – KHI NÀO NÊN DÙNG VÀ KHI NÀO KHÔNG?
- NÊN DÙNG RETRY khi lỗi là tạm thời
- Những lỗi có khả năng hết sau vài giây hoặc lần thử lại sau sẽ thành công.
- Ví du:
    - Gửi email thất bại (MailException, SMTP timeout)	-> Có thể do mạng chập chờn, retry sẽ gửi thành công
    - Gọi API bên thứ ba bị timeout / HTTP 503 (Service Unavailable)	-> Hệ thống đối tác có thể tạm quá tải
    - Lỗi đọc ghi Redis, Kafka, RabbitMQ (connection drop)	-> Các hệ thống message queue/ cache thường hồi lại nhanh
    - Kết nối cơ sở dữ liệu thất bại (timeout, connection reset)	-> DB hoặc mạng có thể bị overload tạm thời
    -> Trong các tình huống trên, retry sau 1 thời gian ngắn (3–5s, tối đa 3–5 lần) thường giúp phục hồi thành công.
- KHÔNG NÊN RETRY khi lỗi là vĩnh viễn hoặc logic sai
- Những lỗi không thể sửa được bằng cách thử lại – càng retry càng tốn tài nguyên, thậm chí gây hại.
- Ví dụ
    - Lỗi nghiệp vụ (BusinessException)	-> Ví dụ: số tiền âm, thiếu trường bắt buộc, user không tồn tại
    - HTTP 400, 401, 403, 404 từ REST API	-> Request sai, thiếu token, không có quyền, sai đường dẫn
    - Validation lỗi từ Bean Validation	 -> Sai format email, số âm, chuỗi trống…
    - File không tồn tại, không mở được	-> File không có thật – retry cũng không giúp được
    - Custom exception như OrderAlreadyExistsException	 -> Lỗi do dữ liệu sai logic
    -> Nếu retry những lỗi này, ứng dụng có thể gửi request lỗi liên tục → spam API hoặc server đích, Gây vòng lặp vô hạn, Gây tải vô ích, đặc biệt khi retry song song

## Cách triển khai Retry trong Spring Boot
-  CÁCH 1: Thủ công – dùng while + try-catch + sleep
@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmailWithRetry(String to, String subject, String body) {
        int maxRetries = 3;
        int attempt = 0;

        while (attempt < maxRetries) {
            try {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(to);
                message.setSubject(subject);
                message.setText(body);
                mailSender.send(message);
                System.out.println("Email sent successfully!");
                return;
            } catch (MailException e) {
                attempt++;
                System.err.println("Failed to send email, attempt " + attempt + ": " + e.getMessage());

                if (attempt >= maxRetries) {
                    throw new RuntimeException("Failed to send email after " + attempt + " attempts");
                }

                try {
                    Thread.sleep(5000); // chờ 5 giây rồi thử lại
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
- CÁCH 2: Dùng thư viện spring-retry + annotation @Retryable
    - Bước 1: Thêm dependency vào pom.xml
    <dependency>
        <groupId>org.springframework.retry</groupId>
        <artifactId>spring-retry</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-aop</artifactId>
    </dependency>
    - Bước 2: Bật retry trong cấu hình Spring
        @SpringBootApplication
    @EnableRetry
    public class YourApp {
        public static void main(String[] args) {
            SpringApplication.run(YourApp.class, args);
        }
    }
    - Bước 3: Đánh dấu phương thức với @Retryable
    @Service
    public class EmailService {

        @Autowired
        private JavaMailSender mailSender;

        @Retryable(
            value = { MailException.class },
            maxAttempts = 3,
            backoff = @Backoff(delay = 5000) // 5 giây giữa các lần retry
        )
        public void sendEmail(String to, String subject, String body) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);
            System.out.println("Email sent!");
        }

        @Recover
        public void recover(MailException e, String to, String subject, String body) {
            System.err.println("Gửi email thất bại sau khi retry: " + e.getMessage());
            // Có thể ghi log hoặc cảnh báo qua Slack, Telegram...
        }
    }
- Cách 1 thì rất dễ hiểu, tuỳ biến tự do phù hợp với người mới bắt đầu học để hiểu retry là gì
- Cách 2 thì hiện đại hơn, tự động hóa có @Retryable phù hợp với xây dựng hệ thông lớn dễ bảo trì và mở rộng







