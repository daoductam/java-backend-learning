# Integration
## 1. Khái niệm
- Integration là quá trình kết nối và tương tác giữa các hệ thống phần mềm khác nhau, để chúng có thể trao đổi dữ liệu, thực hiện chức năng chung, hoặc mở rộng tính năng cho nhau.
- Ví dụ: Bạn xây dựng một website bán sách. Thay vì tự nhập dữ liệu sách thủ công, bạn tích hợp với Google Books API để lấy danh sách sách có sẵn.
- Tại sao cần tích hợp với hệ thống khác?
    - Không cần xây từ đầu, chỉ cần kết nối với dịch vụ đã có (như Google Books, PayPal, VNPay...)
    - Dữ liệu lớn, chất lượng cao từ bên thứ ba (ví dụ: danh sách sách, người dùng, bản đồ, thời tiết...)
    - Có thể thêm tính năng thanh toán, vận chuyển, xác thực email, v.v. mà không cần tự xây.
    - Kết nối với bên thứ ba giúp người dùng thao tác nhanh, tiện hơn (ví dụ: đăng nhập bằng Google).
    - Dễ dàng thay thế hoặc cập nhật từng phần hệ thống mà không ảnh hưởng toàn bộ.

## 2. cách tích hợp hệ thống khác (qua HTTP REST API/ qua web service, qua message queue/ qua DB)
### 2.1 Tích hợp qua HTTP REST API
- Các hệ thống giao tiếp với nhau thông qua các endpoint HTTP/HTTPS (gọi là API), trả về dữ liệu dạng JSON/XML. -> phổ biến nhất hiện nay
- Dùng khi hệ thống bên ngoài có cung cấp RESTful API (như Google Books, ZaloPay, MoMo, OpenWeatherMap…). (Giao tiếp real-time, client-server.)
- Cách tích hợp: Sử dụng thư viện như RestTemplate, WebClient (Spring), hoặc HttpClient
// Tích hợp với Google Books API
@RestController
@RequestMapping("/books")
public class BookController {

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam String keyword) {
        String apiUrl = "https://www.googleapis.com/books/v1/volumes?q=" + keyword;

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(apiUrl, String.class);

        return ResponseEntity.ok(response);
    }
}

### 2.2 Tích hợp qua Web Service (SOAP)
- Cũ nhưng vẫn dùng nhiều trong các hệ thống tài chính, ngân hàng, bảo hiểm.
- Dùng khi Hệ thống cũ, yêu cầu nghiêm ngặt (bảo mật, giao thức WS-Security), Dữ liệu truyền bằng XML.
- Cách tích hợp: Dùng JAX-WS, Spring WS, Apache CXF, tạo proxy từ WSDL.
// Generate code từ WSDL trước (wsimport)
// Sau đó gọi service như một object Java
BankService bankService = new BankService();
BankPort port = bankService.getBankPort();
String result = port.getBalance("123456789");
### 2.3. Tích hợp qua Message Queue (Asynchronous)
- Cho phép tích hợp không đồng bộ, hệ thống không cần chờ phản hồi ngay.
- Khi nào dùng:
    - Hệ thống lớn, microservices.
    - Cần decouple (phân rời) giữa các thành phần.
    - Dùng cho gửi mail, log, xử lý nền (background jobs).
- Công nghệ phổ biến:
    - RabbitMQ, Kafka, ActiveMQ.
    - Java dùng thư viện spring-kafka, spring-amqp.
- Ví dụ: Gửi message vào Kafka
@Autowired
private KafkaTemplate<String, String> kafkaTemplate;

public void sendBookEvent(String bookInfo) {
    kafkaTemplate.send("book-events", bookInfo);
}

### 2.4. Tích hợp qua Database chia sẻ
- Hai hệ thống dùng chung 1 database.
- Dùng khi Hệ thống nội bộ

## 3. Kỹ thuật thực hiện call API (RestTemplate, WebClient)
### 3.1. Sử dụng RestTemplate (cổ điển, đồng bộ)
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class GoogleBooksClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public void fetchBooks() {
        String url = "https://www.googleapis.com/books/v1/volumes?q=java";

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        System.out.println("Response: " + response.getBody());
    }

    public static void main(String[] args) {
        new GoogleBooksClient().fetchBooks();
    }
}
- Ưu: Dễ dùng, ngắn gọn, phù hợp khi viết app đơn giản hoặc microservice nhỏ.
- Nhược: Đồng bộ, có thể chặn luồng, không tối ưu khi gọi nhiều API.

### 3.2. Sử dụng WebClient (hiện đại, bất đồng bộ)
- Cách dùng với Spring WebFlux:
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class GoogleBooksWebClient {

    private final WebClient webClient = WebClient.create();

    public void fetchBooks() {
        String url = "https://www.googleapis.com/books/v1/volumes?q=java";

        Mono<String> result = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class);

        result.subscribe(System.out::println); // in ra nội dung JSON
    }

    public static void main(String[] args) {
        new GoogleBooksWebClient().fetchBooks();
    }
}
- Ưu: Không chặn luồng (non-blocking), phù hợp cho các hệ thống lớn, cần xử lý đồng thời cao.
- Nhược: Phức tạp hơn, cần hiểu reactive programming.

## 4. Cách xử lý lỗi, retry, timeout khi tích hợp
### 4.1. Xử lý lỗi – timeout – retry với RestTemplate
- Cấu hình timeout:
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

RestTemplate restTemplate = new RestTemplate();

HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
factory.setConnectTimeout(5000);    // kết nối tối đa 5s
factory.setReadTimeout(5000);       // đọc dữ liệu tối đa 5s

restTemplate.setRequestFactory(factory);

- Xử lý lỗi (try-catch):
try {
    ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
    System.out.println(response.getBody());
} catch (HttpClientErrorException e) {
    System.out.println("Lỗi 4xx: " + e.getStatusCode());
} catch (HttpServerErrorException e) {
    System.out.println("Lỗi 5xx: " + e.getStatusCode());
} catch (ResourceAccessException e) {
    System.out.println("Timeout hoặc không kết nối được: " + e.getMessage());
}

- Retry thủ công:
int retryCount = 3;
while (retryCount-- > 0) {
    try {
        return restTemplate.getForObject(url, String.class);
    } catch (Exception e) {
        System.out.println("Lỗi gọi API, thử lại...");
        Thread.sleep(1000); // chờ 1s rồi thử lại
    }
}
throw new RuntimeException("Không gọi được API sau nhiều lần thử!");

### 4.2. WebClient: xử lý lỗi – timeout – retry (reactive)
- Cấu hình timeout:
WebClient webClient = WebClient.builder()
        .baseUrl("https://www.googleapis.com")
        .clientConnector(new ReactorClientHttpConnector(
            HttpClient.create()
                      .responseTimeout(Duration.ofSeconds(5))
        ))
        .build();

- Retry tự động (bằng retryWhen):
webClient.get()
        .uri("/books/v1/volumes?q=java")
        .retrieve()
        .bodyToMono(String.class)
        .retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(2)))  // retry 3 lần mỗi 2s
        .timeout(Duration.ofSeconds(5))                         // timeout sau 5s
        .doOnError(e -> System.out.println("Gọi API lỗi: " + e.getMessage()))
        .subscribe(System.out::println);
