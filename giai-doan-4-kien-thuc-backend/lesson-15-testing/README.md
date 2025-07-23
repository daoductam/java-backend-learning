# Testing
## Tại sao cần testing?
- Software testing giúp cho tiết kiệm thời gian và tiền bạc bằng cách giảm thiểu
chi phí phát triển và maintain
    - Khi phát triển tính năng mới, đảm bảo các tính năng đã có vẫn hoạt động ổn định và user khi sử dụng sẽ không gặp phải lỗi
    - Bằng việc đưa ra các test cases, team sẽ có target nhất định cho việc phát triển tính năng và giúp cho việc ước lượng thời gian dễ dàng hơn.
    - Khi các test cases đã có, chi phí maintain sẽ giảm xuống nhờ việc chạy lại các test cases để đảm bảo không có lỗi
- Việc viết test có thể giúp bạn viết code tốt hơn
    - Tránh việc lặp code sẽ khiến phải test nhiều hơn
    - Đảm bảo test coverage trong tiêu chuẩn team, project

## Testing Level
- Unit Testing:
    - Test ở mức unit của code cho
    các function một cách độc lập
    - Đơn giản, thời gian chạy ngắn
    - Có thể mock các input và output
- Integration Test:
    - Dùng để kiểm tra cách các components/service tương tác lẫn nhau (e.g. gọi third-party
    API, thao tác tới DB)
    - Thời gian chạy lâu hơn
- Functional or end-to-end Testing
    - Test case mô tả lại đầy đủ quá trình hoạt động của hệ thống từ đầu tới cuối
    - Ví dụ: 1 hệ thống review gồm nhiều microservice, khi test E2E thì test input và đợi
    asyncronous đến khi nhận được kết quả
    - Ví dụ: 1 hệ thống cần test giao diện cần click vào button, nhập form và submit form và xác nhận lại kết quả
- Acceptance Testing
    - được test bởi khách hàng hoặc người dùng để xem đáp ứng y/c chức năng hay chưa
## Load & Stress Test
- Load Test và Stress Test đều là non-functional testing được sử dụng để đánh
giá performance của hệ thống dưới một kịch bản cụ thể trong thực tế.
- Load Test
    - Test bằng lưu lượng bình thường mà hệ thống ghi nhận để xác định hệ thống có vấn đề gì không
    - Có thể giúp xác định bootleneck, như là slow response time, high CPU usage, hay memory
leaks
- Stress Test:
    - Dùng để đánh giá application trong điều kiện khắc nghiệt trên mức hoạt động bình thường
    - Dùng để xác định breaking-point của hệ thống và xem hệ thống sẽ phản ứng như thế nào khi đạt tới ngưỡng đó.
    - Test bằng cách tăng dần lưu lượng trên mức bình thường cho đến khi hệ thống “sập”
    - Hoặc tăng lưu lượng một cách đột ngột để xem hệ thống phản ứng như thế nào
    - Kết quả của Stress Test có thể giúp developer quản lý hậu quả tốt hơn sự cố xảy ra

## Chi Tiết Cách làm
### Unit Test
#### 0. Mô tả
- Mục tiêu: Kiểm tra logic từng đơn vị nhỏ nhất trong hệ thống (thường là một hàm hoặc một class).
- Đặc điểm:
  - Không phụ thuộc vào bên ngoài (database, API, hệ thống file...).
  - Dùng các kỹ thuật mocking/stubbing.
- Dùng khi nào: Test logic tính toán, validate input, xử lý nghiệp vụ cục bộ.
- Công cụ: JUnit, Mockito, NUnit, xUnit, etc.
#### 1. Cấu trúc một Unit Test cơ bản
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class CalculatorTest {

    @Test
    void testAdd() {
        Calculator calculator = new Calculator();
        int result = calculator.add(2, 3);
        assertEquals(5, result);  // ✅ assertion
    }
}
#### 2. Các annotation quan trọng của JUnit 5
| Annotation           | Ý nghĩa                                                 |
| -------------------- | ------------------------------------------------------- |
| `@Test`              | Đánh dấu 1 method là test                               |
| `@BeforeEach`        | Chạy **trước mỗi test**                                 |
| `@AfterEach`         | Chạy **sau mỗi test**                                   |
| `@BeforeAll`         | Chạy **1 lần trước tất cả test** (method phải `static`) |
| `@AfterAll`          | Chạy **1 lần sau tất cả test** (method phải `static`)   |
| `@DisplayName`       | Gán tên hiển thị cho test                               |
| `@Disabled`          | Bỏ qua test                                             |
| `@Nested`            | Gom nhóm test lại                                       |
| `@ParameterizedTest` | Test với nhiều input                                    |
#### 3. Các Assertion phổ biến (org.junit.jupiter.api.Assertions)
- assertEquals(expected, actual);                 // So sánh bằng
- assertNotEquals(notExpected, actual);           // So sánh khác
- assertTrue(condition);                          // Kiểm tra đúng
- assertFalse(condition);                         // Kiểm tra sai
- assertNull(object);                             // Kiểm tra null
- assertNotNull(object);                          // Kiểm tra khác null
- assertThrows(Exception.class, () -> { ... });   // Kiểm tra exception
- assertAll(() -> {...}, () -> {...});            // Gộp nhiều assert
#### 4. Mocking với Mockito
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void testCreateOrder() {
        Order order = new Order("sp001", 3);

        when(orderRepository.save(any())).thenReturn(order);

        Order saved = orderService.createOrder(order);

        assertEquals("sp001", saved.getProductCode());
        verify(orderRepository).save(order); // kiểm tra đã gọi save
    }
}
#### 5. Test Exception
@Test
void testThrowException() {
    assertThrows(IllegalArgumentException.class, () -> {
        calculator.divide(1, 0);
    });
}
#### 6. @ParameterizedTest (test với nhiều input)
@ParameterizedTest
@CsvSource({
    "2, 3, 5",
    "10, 15, 25"
})
void testAdd(int a, int b, int expected) {
    assertEquals(expected, calculator.add(a, b));
}
#### 7. Cách đặt tên file test và method
| Thành phần | Quy ước                                                           |
| ---------- | ----------------------------------------------------------------- |
| Tên class  | `OrderServiceTest` (tên class + Test)                             |
| Tên method | `shouldReturnOrder_whenValidInput()` (có dạng `should...when...`) |

### API First test
#### 0. Mô tả
- Mục tiêu: Kiểm tra rằng API tuân theo định nghĩa đã mô tả trong contract (OpenAPI/Swagger).
- Đặc điểm:
  - Giao tiếp rõ ràng giữa frontend/backend.
  - Phát hiện sớm lỗi về thay đổi field, format, missing params...
- Dùng khi nào: Phát triển theo mô hình API-first (định nghĩa trước API rồi mới code).
- Công cụ: Postman, Swagger Validator, Pact, Dredd, Spring Cloud Contract.
#### 1. Định nghĩa API bằng OpenAPI (Swagger)
- Bạn mô tả API trước khi phát triển, ví dụ: openapi.yaml
openapi: 3.0.0
info:
  title: Order API
  version: 1.0.0
paths:
  /orders:
    post:
      summary: Create order
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/OrderRequest'
      responses:
        '201':
          description: Created
components:
  schemas:
    OrderRequest:
      type: object
      properties:
        productId:
          type: string
        quantity:
          type: integer
#### 2. Sử dụng Swagger Codegen hoặc OpenAPI Generator
- Từ file YAML trên, bạn có thể tạo:
    - Client SDK
    - Server stub
    - Test skeleton
- openapi-generator generate -i openapi.yaml -g spring
#### 3. Viết API Test (Rest Assured - Java)
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;

public class OrderApiTest {

    @Test
    void testCreateOrder() {
        RestAssured.baseURI = "http://localhost:8080";

        given()
            .header("Content-Type", "application/json")
            .body("{ \"productId\": \"123\", \"quantity\": 2 }")
        .when()
            .post("/orders")
        .then()
            .statusCode(201)
            .body("orderId", notNullValue());
    }
}

### E2E Test
#### Mô tả
- Mục tiêu: Kiểm tra toàn bộ luồng của hệ thống từ frontend → backend → database → 3rd services.
- Đặc điểm:
  - Giống cách người dùng tương tác thật sự.
  - Tốn thời gian, dễ flakey (bị lỗi vì phụ thuộc nhiều).
- Dùng khi nào: Trước release, để đảm bảo hệ thống vận hành đúng.
- Công cụ: Selenium, Cypress, Playwright, Robot Framework, etc.

#### Ví dụ cụ thể: Sử dụng RestAssured để test E2E một API bằng Java
- Setup:
<!-- pom.xml -->
<dependency>
    <groupId>io.rest-assured</groupId>
    <artifactId>rest-assured</artifactId>
    <version>5.3.0</version>
    <scope>test</scope>
</dependency>
- File Test: E2ETest.java
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class E2ETest {

    @Test
    public void testOrderFlow() {
        // Step 1: Đăng nhập để lấy token
        String token = given()
            .contentType(ContentType.JSON)
            .body("{\"username\":\"user1\", \"password\":\"pass123\"}")
        .when()
            .post("http://localhost:8080/api/auth/login")
        .then()
            .statusCode(200)
            .extract()
            .path("token");

        // Step 2: Đặt hàng
        int orderId = given()
            .header("Authorization", "Bearer " + token)
            .contentType(ContentType.JSON)
            .body("{\"productId\": 101, \"quantity\": 2}")
        .when()
            .post("http://localhost:8080/api/orders")
        .then()
            .statusCode(201)
            .body("status", equalTo("CREATED"))
            .extract()
            .path("id");

        // Step 3: Xác minh đơn hàng đã được tạo
        given()
            .header("Authorization", "Bearer " + token)
        .when()
            .get("http://localhost:8080/api/orders/" + orderId)
        .then()
            .statusCode(200)
            .body("productId", equalTo(101))
            .body("quantity", equalTo(2));
    }
}
- Một số cú pháp quan trọng (RestAssured):
| Cú pháp                          | Mô tả                   |
| -------------------------------- | ----------------------- |
| `given()`                        | Khởi tạo request        |
| `.body(...)`                     | Truyền nội dung JSON    |
| `.header("Authorization", ...)`  | Gửi token               |
| `.post(url)`                     | Gửi POST request        |
| `.get(url)`                      | Gửi GET request         |
| `.then().statusCode(...)`        | Xác minh HTTP code      |
| `.body("field", equalTo(value))` | So sánh dữ liệu trả về  |
| `.extract().path("token")`       | Lấy dữ liệu từ response |
#### E2E với Postman (alternatively)
- Tạo các request trong Collection (login → create order → get order)
- Thêm Test Script ở mỗi bước:
// Test sau khi login
pm.test("Status code is 200", function () {
    pm.response.to.have.status(200);
});

pm.test("Token exists", function () {
    var json = pm.response.json();
    pm.environment.set("token", json.token);
});

### Load Test
#### Mô tả
- Mục tiêu: Kiểm tra hiệu năng, khả năng chịu tải, phản hồi của hệ thống dưới áp lực lớn.
- Đặc điểm:
  - Kiểm tra số lượng request/s, độ trễ, CPU/memory usage...
  - Giúp xác định bottlenecks (nút cổ chai).
- Dùng khi nào: Trước khi đưa vào production hoặc khi hệ thống có lượng truy cập cao.
- Công cụ: JMeter, Gatling, k6, Locust, Artillery.
#### 1. Cài đặt K6
brew install k6           # macOS
choco install k6          # Windows
#### 2. Cấu trúc file test (JavaScript)
import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
  vus: 100, // số lượng virtual users
  duration: '30s', // thời gian chạy test
};

export default function () {
  const res = http.get('https://your-api.com/products');

  check(res, {
    'status is 200': (r) => r.status === 200,
    'body is not empty': (r) => r.body.length > 0,
  });

  sleep(1); // delay giữa các request
}

#### 3. Chạy test
- k6 run your-test.js
#### 4. Cấu hình nâng cao (options)
export let options = {
  stages: [
    { duration: '10s', target: 50 },  // tăng đến 50 VUs trong 10 giây
    { duration: '20s', target: 100 }, // giữ 100 VUs trong 20 giây
    { duration: '10s', target: 0 },   // giảm về 0 trong 10 giây
  ],
  thresholds: {
    http_req_duration: ['p(95)<500'], // 95% request phải < 500ms
  },
};
#### 5. POST request có body
export default function () {
  const url = 'https://your-api.com/orders';
  const payload = JSON.stringify({
    productId: '123',
    quantity: 2,
  });

  const params = {
    headers: {
      'Content-Type': 'application/json',
    },
  };

  const res = http.post(url, payload, params);
  check(res, { 'status was 201': (r) => r.status === 201 });
}
#### 6. Kết hợp nhiều request
export default function () {
  let res1 = http.get('https://your-api.com/products');
  let res2 = http.get('https://your-api.com/cart');
}
#### 7. Ghi dữ liệu test ra file (HTML, JSON...)
k6 run your-test.js --out json=result.json
k6 run your-test.js --summary-export=result.json
- Dùng k6-html-reporter để tạo file HTML:
k6 run your-test.js --summary-export=summary.json
npx k6-reporter --summary-export=summary.json
#### 8. Thư viện hỗ trợ (import thêm nếu cần)
import { Counter } from 'k6/metrics';
import { Trend } from 'k6/metrics';
import { Rate } from 'k6/metrics';

let errorRate = new Rate('errors');

export default function () {
  const res = http.get('https://your-api.com');

  errorRate.add(res.status !== 200);
}
#### 9. Environment Variables
k6 run -e BASE_URL=https://your-api.com loadtest.js
- Trong code: const BASE_URL = __ENV.BASE_URL;

###  Integration Test
#### Mô tả
- Mục tiêu: Kiểm tra việc tích hợp giữa các module/hệ thống với nhau.
- Đặc điểm:
  - Có sử dụng database, API, hoặc service thực tế.
  - Phát hiện lỗi do khác biệt cấu hình, kết nối, transaction...
- Dùng khi nào: Kiểm tra controller + service + repository + DB cùng hoạt động đúng.
- Công cụ: Spring Boot @SpringBootTest, Testcontainers, DBUnit, etc.
#### Annotation thường dùng
| Annotation                | Ý nghĩa                                               |
| ------------------------- | ----------------------------------------------------- |
| `@SpringBootTest`         | Chạy toàn bộ app context (thường dùng nhất)           |
| `@AutoConfigureMockMvc`   | Kích hoạt MockMvc để test controller qua HTTP giả lập |
| `@Transactional`          | Reset DB sau mỗi test                                 |
| `@Testcontainers`         | Dùng với Testcontainers (PostgreSQL, Redis, Kafka...) |
| `@Sql("/init-data.sql")`  | Load data test từ file SQL                            |
| `@ActiveProfiles("test")` | Dùng `application-test.yml` khi chạy test             |
#### Cấu trúc ví dụ chuẩn
- Ví dụ test API tạo sản phẩm /api/products
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class ProductIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreateProduct() throws Exception {
        String payload = """
            {
              "name": "Iphone 15",
              "price": 2500
            }
        """;

        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.name").value("Iphone 15"));
    }
}
#### Các lệnh kiểm tra trong MockMvc
| Kiểu kiểm tra   | Ví dụ                                                |
| --------------- | ---------------------------------------------------- |
| Status          | `status().isOk()` / `isCreated()` / `isBadRequest()` |
| Content Type    | `content().contentType("application/json")`          |
| Body JSON field | `jsonPath("$.name").value("abc")`                    |
| Body chứa chuỗi | `content().string(containsString("success"))`        |
#### Các method HTTP trong MockMvc
| Method   | Gọi như                                          |
| -------- | ------------------------------------------------ |
| `GET`    | `mockMvc.perform(get("/api/..."))`               |
| `POST`   | `mockMvc.perform(post("/api/...").content(...))` |
| `PUT`    | `mockMvc.perform(put("/api/...").content(...))`  |
| `DELETE` | `mockMvc.perform(delete("/api/123"))`            |
#### Test với database H2
spring:
  datasource:
    url: jdbc:h2:mem:testdb
    driverClassName: org.h2.Driver
    username: sa
    password:
  h2:
    console:
      enabled: true
  jpa:
    hibernate:
      ddl-auto: create-drop
    show-sql: true
#### Test với Testcontainers (cấp cao hơn)
@Testcontainers
@SpringBootTest
class ProductIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")
                .withDatabaseName("test")
                .withUsername("test")
                .withPassword("test");

    @DynamicPropertySource
    static void dbProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    ...
}
#### Cách đặt tên file test
| Thành phần | Test class                         |
| ---------- | ---------------------------------- |
| Controller | `ProductControllerIntegrationTest` |
| Flow Login | `AuthIntegrationTest`              |
| Đăng ký    | `UserRegistrationIntegrationTest`  |
#### Cách chạy test bằng terminal
- ./mvnw test




