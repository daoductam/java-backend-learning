# Buổi 2: Spring Boot
## Mục tiêu
- Viết được REST API đơn giản (CRUD)
- Cấu trúc 1 project Spring Boot chuẩn (Controller - Service - Repository - DTO - Entity)
- Các annotation thường gặp 
(@RestController, @RequestMapping, 
@GetMapping, @PostMapping, @RequestParam, @PathVariable, @RequestBody, 
@Service, @Entity, 
@Transaction,
 @Value, @NotNull, @Valid)
## Lý thuyết
### Khái niệm
- Spring là 1 framework rất nổi tiếng, dùng để xây dựng những ứng dụng java. 
    - Nếu dùng Spring 1 cách truyền thống để làm app thì rất khó -> vấn đề
- Spring boot dùng Spring bên trong, làm đơn giản hóa việc sử dụng Spring
    - Cấu hình tự động
    - Giải quyết dêpndency conflicts
    - Nhúng web server
### Giới thiệu
- API: là bộ quy tắc giúp các phần mềm giao tiếp với nhau -> quy định giao thức, đầu vào, đầu ra...
- REST: là 1 kiến trúc hoặc 1 cách thiết kế để xây dựng các dịch vụ web. Nó cung cấp các nguyên tắc và ràng buộc để xây dựng 1 hệ thống phân tán hiệu quả đơn giản và có thể mở rộng
- Nguyên tắc
    - Stateless (Không lưu thông tin của request trước đó): Mỗi request từ client tới server phải chứa đủ thông tin để server hiểu và xủ lý, và server không lưu trạng thái của client giữa các request
    - Client-Server: Client và Server tách biệt nhau, mỗi bên chỉ tập trung vào nhiệm vụ của mình
    - Cacheable(Có thể cache): Các phản hồi từ server có thể được đánh dấu là có thể cache được để cải thiện hiệu suất
    - Uniform Interface(Giao diện thống nhất): Tất cả các tài nguyên đều được truy cập và thao tác theo 1 cách thống nhất
    - Layered System (Hệ thống phân lớp): Client không cần biết server mà mình tương tác trực tiếp hay thông qua 1 lớp trung gian
    - Code on Demand (Code theo yêu cầu): Server có thể cung cấp code để client thực thi 
### Rest API
- Rest API: là 1 triển khai cụ thể của các nguyên tắc REST dưới dạng API. Nó cung cấp 1 tập hợp các endpoint(url) cho phép các ứng dụng tương tác với nhau thông qua các giao thức HTTP
- URL: 
    - *Scheme*: giao thức. Đối với web, giao thức phổ biến là http và https
    - *Domain*: Tên máy thay cho địa chỉ IP của máy
    - *Port*: Cổng tiếp nhận request
    - *Path*: Đường dẫn tới source
    - * *: thông tin bố sung (Không bắt buộc)
    - anchor: chỉ ra 1 phần cụ thẻ trong source
- HTTP Methods
    - Post: tạo mới resource
    - GET: Lấy thông tin resource
    - PUT: Cập nhật toàn phần
    - DELETE: xóa hoặc vô hiệu hóa
    - PATCH: cập nhật 1 phần
- JSON: 
    - JSON là một định dạng trình bày dữ liệu text
    - JSON dùng để lưu trữ và truyền tải dữ liệu
    - Các định dạng khác: XML, YAML, ...
    - RESP API sử dụng JSON trong request body, response body
- Quy ước REST
    - Trên URL
        - Dùng danh từ thay cho động từ
        - Dùng danh từ nhiều thay vì số ít
        - Đánh phiên bản cho API
        - Dùng slug-case
    - snake_case cho request body, response body
    - Ví du: https://tam-dao.com/api/v1/users/12
### Cấu trúc dự án Spring Boot
- Các tầng trong dự án spring boot
    - Controller: Xử lý các request đến từ người dùng, ánh xạ các endpoint và gọi các phương thức  của tầng Service
    - DTO: Được sử dụng để vận chuyển dữ liệu giữa các tầng. DTO giúp tránh lộ các thông tin không cần thiết hoặc nhạy cảm của entity khi truyền ra ngoài
    - Service: Tầng chứa logic nghiệp vụ  chính ủa ứng dụng. Nó tương tác với tầng Repositoty để lấy hoặc lưu trũ dữ liệu
    - Repository: Được sử dụng để truy vấn và thao tác dữ liệu 1 cách dễ dàng
    - Entity: Là các class đại diện cho bảng trong cơ sở dữ liệu
- Cấu trúc dự án spring boot
    - Model: Trong ứng dụng thực, các class đại diện cho kết quả tính toán, các class làm tham số đầu vào cho tầng service tính toán,... được coi là model
    - Util: Package chứa các util(xử lý linh tinh)
    - Constant: Package chứa các class định nghĩa enum, constant
    - Exception: Package chứa các class có nhiêm vụ xử lý exception
    - Config: Package chứa các bean được định nghĩa còn lại nhưng không thuộc lớp layer nào
    - Filter: Chứa các class filter trong spring boot
## Cách sử dụng
### 1. @Primary annotation và @Qualifier annotation
- @Component là một annotation đánh dấu trên các class để cho biết chúng là các bean được quản lý bởi Spring Boot. Điều này có nghĩa là Spring Boot sẽ tạo và quản lý các instance của các class được đánh dấu @Component.
- @Autowired được sử dụng để tiêm (inject) các dependency vào các thành phần khác. Khi bạn đánh dấu một thuộc tính bằng @Autowired, Spring Boot sẽ tự động tiêm một instance của dependency tương ứng vào thuộc tính đó.
    - @Autowired cũng có thể được sử dụng trên constructor hoặc method, thay vì thuộc tính, để Spring Boot tự động inject các dependency vào Class đã gọi. 
- Trong thực tế, khi ứng dụng của bạn chứa nhiều bean cùng loại trong Spring context, Spring Boot có thể gặp khó khăn trong việc xác định bean nào cần được inject. ->  Spring Boot không biết phải chọn bean nào để inject vào khi có nhiều bean cùng loại trong context. -> giải quyết đầu tiên là sử dụng Annotation @Primary.
    - @Primary đánh dấu một bean là ưu tiên và sẽ luôn được ưu tiên chọn trong trường hợp có nhiều bean cùng loại trong context.
    - Cách giải quyết thứ hai là sử dụng Annotation @Qualifier. @Qualifier xác định tên của một bean mà bạn muốn chỉ định để inject.
### 2. @PostConstruct, @PreDestroy
- @PostConstruct là một Annotation đánh dấu trên một method bên trong một Bean. oC Container hoặc ApplicationContext sẽ gọi method này sau khi Bean đó được tạo ra và quản lý.
- @PreDestroy là một Annotation đánh dấu trên một method bên trong một Bean. IoC Container hoặc ApplicationContext sẽ gọi method này trước khi Bean đó bị xóa hoặc không được quản lý nữa.
*@PostConstruct và @PreDestroy rất hữu ích trong việc quản lý vòng đời của Bean. Bạn có thể sử dụng chúng để thực hiện các nhiệm vụ như cài đặt giá trị mặc định trong thuộc tính sau khi Bean được khởi tạo hoặc xóa dữ liệu trước khi Bean bị xóa.*
### 3. @Component @Service @Repository trong Spring Boot
- @Controller: Đánh dấu một class là tầng Controller, chịu trách nhiệm xử lý các yêu cầu từ người dùng hoặc các yêu cầu gửi đến từ bên ngoài hệ thống.
- @Service: Đánh dấu một class là tầng Service, chứa các logic nghiệp vụ và xử lý các yêu cầu từ tầng Controller.
- @Repository: Đánh dấu một class là tầng Repository, chịu trách nhiệm giao tiếp với cơ sở dữ liệu hoặc các nguồn dữ liệu khác, thực hiện các thao tác truy vấn và cung cấp dữ liệu cho tầng Service.
- *Note*:
    - @Service và @Repository đánh dấu các lớp là các Bean đảm nhiệm các nhiệm vụ cụ thể trong kiến trúc ứng dụng.
    - Bạn có thể sử dụng @Component thay cho @Service và @Repository, và chương trình vẫn hoạt động bình thường. Tuy nhiên, việc sử dụng các Annotation phù hợp giúp làm rõ mục tiêu và nhiệm vụ của mỗi lớp.
### 4. Component Scan là gì
- Trong các ứng dụng Spring Boot, chúng ta thường định nghĩa các bean bằng cách sử dụng các Annotation như @Component, @Service, @Repository, hoặc @Controller.
->  việc đánh dấu các bean này không đủ để Spring Boot biết cần tạo và quản lý chúng như thế nào. Đây là lúc Component Scan ra đời.
- Component Scan cho phép Spring Boot tự động tìm kiếm và quản lý các bean trong ứng dụng của bạn. Mặc định, Spring Boot sẽ quét toàn bộ các package và các package con của package chứa class chứa hàm main.
- Tuỳ chỉnh cấu hình cho Component Scan để chỉ tìm kiếm các bean trong một package nhất định có 2 cách
    - Cách 1: Sử dụng @ComponentScan trên class 
    - Cách 2: Sử dụng thuộc tính scanBasePackages trong Annotation @SpringBootApplication
-> Việc sử dụng Component Scan giúp giảm sự phức tạp trong việc cấu hình và quản lý các bean.
### 5. @Configuration annotation và @Bean annotation
- @Configuration là một Annotation đánh dấu trên một class, cho biết rằng class đó chứa các thông tin cấu hình cho ứng dụng. Spring Boot sẽ tìm và quét các class được đánh dấu @Configuration để tạo và quản lý các beans.
- @Bean là một Annotation đánh dấu trên một method trong class được đánh dấu @Configuration. Nó cho biết rằng method đó tạo và trả về một bean, và Spring Boot nên quản lý bean đó trong ứng dụng
    - @Bean có tham số: Nếu method được đánh dấu @Bean có tham số, Spring Boot sẽ tự động inject các beans khác từ context vào làm tham số.
- Lợi ích:
    - @Configuration và @Bean cho phép bạn tạo và quản lý các beans một cách tường minh và linh hoạt.
    - Bạn có thể tạo ra các beans với các cấu hình phức tạp hoặc phụ thuộc vào các beans khác.
    - Điều này giúp quản lý phần cấu hình và khởi tạo beans một cách hiệu quả.
### 6. Spring Boot Application Config và @Value annotation
- Trong thực tế, không phải lúc nào chúng ta cũng nên để mọi thứ trong code của mình. Có những thông số tốt hơn nên được truyền từ bên ngoài vào ứng dụng, để giúp ứng dụng của bạn dễ dàng thay đổi giữa các môi trường khác nhau. -> cấu hình ứng dụng Spring Boot và cách sử dụng annotation @Value để đọc các giá trị cấu hình từ file application.properties
- @Value được sử dụng để chú thích các thuộc tính trong class, nơi mà bạn muốn đọc các giá trị cấu hình từ file application.properties hoặc các nguồn khác.
- Ví dụ, nếu bạn muốn cấu hình thông tin về cơ sở dữ liệu của mình từ bên ngoài ứng dụng:
    - kungfutech.mysql.url=jdbc:mysql://host1:33060/kungfutech
    - @Value("${kungfutech.mysql.url}")
    private String mysqlUrl;
### 7. @PostMapping annotation và @RequestMapping annotation
- @PostMapping là một annotation trong Spring Boot được sử dụng để đánh dấu một phương thức xử lý yêu cầu HTTP POST. Trong HTTP, POST được sử dụng để gửi dữ liệu từ máy khách đến máy chủ.
- @RequestMapping là một annotation mạnh mẽ trong Spring Boot cho phép bạn định nghĩa một phương thức xử lý yêu cầu HTTP cho nhiều phương thức HTTP khác nhau (GET, POST, PUT, DELETE, v.v.). Nó cũng cho phép bạn chỉ định một đường dẫn cơ bản cho một nhóm phương thức xử lý yêu cầu.
### 8. Giới thiệu Spring Boot JPA
- Spring Boot JPA là một phần của hệ sinh thái Spring Data, giúp tạo ra một lớp trung gian giữa tầng dịch vụ (service layer) và cơ sở dữ liệu. 
    - Nó giúp chúng ta thao tác với cơ sở dữ liệu dễ dàng hơn, tự động cấu hình và giảm thiểu code lặp lại. 
    - Spring Boot JPA sử dụng Hibernate và cung cấp một giao diện mạnh mẽ, giúp giải quyết các vấn đề khi làm việc với Hibernate.
### 9. Spring JPA Method và @Query
- Cơ chế tự động tạo truy vấn: Spring JPA cho phép tạo câu truy vấn dựa trên tên của method trong repository. Cơ chế này xây dựng câu truy vấn tự động từ tên method mà không cần viết thêm code SQL.
- Quy tắc đặt tên method:
    - find…By, read…By, query…By, count…By, get…By
    - Phần còn lại của tên method sẽ được phân tích dựa trên tên thuộc tính (viết hoa chữ cái đầu). Nếu bạn có nhiều điều kiện, bạn có thể kết hợp chúng bằng các từ khóa And hoặc Or.
- @Query Annotation: Nếu bạn muốn tạo các truy vấn phức tạp hơn hoặc sử dụng SQL trực tiếp, bạn có thể sử dụng annotation @Query.
    - Với @Query, bạn có thể sử dụng JPQL để tạo câu truy vấn. JPQL giống với SQL nhưng sử dụng các tên đối tượng Java thay vì tên bảng cơ sở dữ liệu.
    - Nếu bạn muốn sử dụng SQL trực tiếp, bạn có thể sử dụng *nativeQuery = true*
    - Truyền tham số: sử dụng :tên_tham_số trong câu truy vấn và sử dụng @Param("tên_tham_số") trong phương thức repository.
### 10. Restful API với @RestController, @PathVariable, @RequestBody
- @RestController là một chú thích quan trọng trong Spring Boot. Khác với @Controller, nó không trả về một template mà thay vào đó trả về dữ liệu dưới dạng JSON.
    - Ví dụ: 
        - @RestController
        - @RequestMapping("/api/v1")
        - public class RestApiController {}
- @RequestBody annotation: 
    - Khi bạn xây dựng các API, thông tin từ phía client thường được gửi trong phần Body của request dưới dạng JSON.
    - Spring Boot sẽ tự động chuyển đổi chuỗi JSON trong request thành một đối tượng Java. Bạn chỉ cần sử dụng chú thích @RequestBody để cho Spring Boot biết bạn muốn chuyển đổi JSON thành đối tượng Java.
    - Ví dụ: public ResponseEntity addTodo(@RequestBody Todo todo)
- @PathVariable annotation
    - RESTful API thường đặt thông tin quan trọng trong URL của API. Để truy cập thông tin này, chúng ta sử dụng @PathVariable.
    - @PathVariable cho biết cho Spring Boot lấy thông tin từ URL dựa trên tên thuộc tính đã định nghĩa trong chú thích @GetMapping.
    - Ví dụ: 
        - @GetMapping("/todo/{todoId}")
        - public Todo getTodo(@PathVariable(name = "todoId") Integer todoId)
### 11. Exception handling trong Spring Boot
- @RestControllerAdvice & @ControllerAdvice + @ExceptionHandler
    - @RestControllerAdvice là một Annotation được áp dụng trên một class và thường được sử dụng cùng với @ExceptionHandler. Nó can thiệp vào việc xử lý của các @RestController.
    - @ControllerAdvice là một Annotation tương tự như @RestControllerAdvice, nhưng thường được sử dụng cùng với @ExceptionHandler để can thiệp vào việc xử lý của các Controller thông thường.
    - @RestControllerAdvice và @ControllerAdvice thường được kết hợp với @ExceptionHandler để xử lý ngoại lệ.
    - Ví dụ: 
        - @RestControllerAdvice
        - public class ApiExceptionHandler 
        - @ExceptionHandler(IndexOutOfBoundsException.class)
        - @ResponseStatus(value = HttpStatus.BAD_REQUEST)
        - public ErrorMessage todoException(Exception ex, WebRequest request){}
- @ResponseStatus cho phép bạn định nghĩa HTTP status code mà bạn muốn trả về cho người dùng. Bạn có thể sử dụng nó trên một đối tượng được trả về từ một phương thức. Nếu bạn không muốn sử dụng ResponseEntity, bạn có thể sử dụng @ResponseStatus.
### 12. @ConfigurationProperties annotation là gì
- Cấu Hình Đơn Giản
    - Giả sử bạn muốn ứng dụng của mình có một số giá trị toàn cục, chẳng hạn như email và ID của Google Analytics, mà bạn muốn lưu chúng ở bên ngoài ứng dụng để dễ dàng thay đổi. Để làm điều này, bạn có thể sử dụng @ConfigurationProperties.
    - Ví dụ
        - @Data
        - @Component -> Đánh dấu class này là một Spring bean, cho phép Spring quản lý nó.-> Đánh dấu class bên dưới nó là một properties class. Các thuộc tính trong class này sẽ tự động nạp giá trị từ cấu hình ứng dụng.
        - @ConfigurationProperties(prefix = "tamdao")
        - public class TamDaoAppProperties {private String email;private String googleAnalyticsId;}
    - Để kích hoạt tính năng này, bạn cần thêm annotation @EnableConfigurationProperties vào một configuration nào đó trong ứng dụng.(Thường là main)
    - Sau đó, bạn cần tạo một file application.yml trong thư mục resources để chứa cấu hình:
        - kungfutech:
            - email: tam@gmail.com
            - googleAnalyticsId: U-xxxxx
- Sử Dụng Cấu Hình: @Autowired TamDaoAppProperties tamDaoAppProperties;
- Nested Properties: có thể cấu hình các thuộc tính bên trong class, kể cả khi chúng là các danh sách (Lists), bản đồ (Maps) hoặc một class khác. 
### 13. Tạo Bean có điều kiện với @Conditional
- Muốn tạo một bean chỉ khi nó đáp ứng một số điều kiện cụ thể.-> @Conditional
- Cách tạo bean có điều kiện
    - Có nhiều cách để tạo bean, bao gồm @Component, @Configuration, @Bean, @Service, v.v. -> có thể thêm một hoặc nhiều điều kiện để chỉ tạo bean khi các điều kiện đó được đáp ứng.
    - Tất cả các điều kiện sử dụng tiền tố @Conditional... Cách hoạt động của tất cả các điều kiện @Conditional giống nhau. 
    - Ví dụ: Bean này chỉ được tạo khi các điều kiện thỏa mãn
        - @Bean
        - @Conditional...
        - ABeanWithCondition aBeanWithCondition(){} 
    - Nếu bạn gắn điều kiện trên một @Configuration, tất cả các bean bên trong nó sẽ chịu tác động của điều kiện đó. Tương tự, bạn có thể sử dụng các điều kiện với các annotation khác như @Component, @Service, @Repository, v.v.
        - Ví du: Bean này chỉ được tạo khi điều kiện thỏa mãn
            - @Conditional...
            - @Configuration
            - public class ConditionalExample
- Các loại điều kiện:
    - @ConditionalOnBean được sử dụng khi bạn muốn tạo một bean chỉ khi một bean khác đã tồn tại trong context.
        - Ví dụ: ABeanWithCondition chỉ được tạo ra khi RandomBean đã tồn tại trong context.
            - @Configuration
            - public class ConditionalOnBeanExample 
            - @Bean
            - @ConditionalOnBean(RandomBean.class)
            - ABeanWithCondition aBeanWithACondition() {return new ABeanWithCondition();}
    - @ConditionalOnProperty được sử dụng khi bạn muốn quyết định việc tạo bean dựa trên giá trị của một thuộc tính cấu hình (property).
        - Ví dụ: 
            - @Bean
            - @ConditionalOnProperty(value="tamdao.bean2.enabled",havingValue = "true", // Bean chỉ được tạo nếu giá trị property là "true" matchIfMissing = false) // Bean không được tạo nếu property không tồn tại
            - ABeanWithCondition2 aBeanWithCondition2()
            - File cấu hình application.properties: tamdao.bean2.enabled=true
    - @ConditionalOnExpression được sử dụng khi bạn muốn đặt nhiều điều kiện và thỏa mãn điều kiện này bằng một biểu thức.
        - Ví du: @Configuration @ConditionalOnExpression( "${kungfutech.expression1.enabled:true} and ${kungfutech.expression2.enabled:true}")
    - @ConditionalOnMissingBean được sử dụng khi bạn muốn tạo một bean chỉ khi chưa tồn tại bất kỳ bean nào tương tự trong context.
    - @ConditionalOnResource được sử dụng khi bạn muốn tạo bean chỉ khi một tài nguyên nào đó mà bạn chỉ định tồn tại. Ví dụ: @ConditionalOnResource(resources = "/application.properties")
    - @ConditionalOnClass được sử dụng khi bạn muốn tạo bean chỉ khi một class cụ thể nào đó tồn tại trong classpath. Ví dụ: @ConditionalOnClass(name = "com.example.SomeClass")
    - @ConditionalOnMissingClass ngược lại với @ConditionalOnClass. Nó thỏa mãn khi class cụ thể không tồn tại trong classpath. Ví dụ: @ConditionalOnMissingClass(name = "com.example.SomeClass")
    - @ConditionalOnJava thỏa mãn khi môi trường chạy Java có phiên bản đúng với điều kiện. 
        - Ví dụ: @ConditionalOnJava(JavaVersion.EIGHT)
### Tạo Custom @Conditional trong Spring Boot
- Tạo Custom @Conditional
    - Để tạo một điều kiện tùy chỉnh, bạn cần kế thừa lớp Condition và triển khai lại phương thức matches. Phương thức matches là nơi bạn đặt các điều kiện kiểm tra xem có nên tạo bean không.
        - Ví dụ: public class MyCustomCondition implements Condition - @Override public boolean matches....
    - Sau đó sử dụng nó: @Conditional(MyCustomCondition.class) @Bean
- Tạo Custom Annotation @Conditional
    - Nếu bạn muốn sử dụng một Annotation thay vì sử dụng @Conditional(MyCustomCondition.class), bạn có thể tự tạo một Annotation tùy chỉnh.
    - Ví dụ: 
        - @Target({ ElementType.TYPE, ElementType.METHOD })
        - @Retention(RetentionPolicy.RUNTIME)
        - @Documented
        - @Conditional(MyCustomCondition.class) 
        - public @interface MyCustomConditional
    - Sau đó: @MyCustomConditional @Bean
- Kết hợp Nhiều Điều Kiện với OR: bằng cách kế thừa lớp AnyNestedCondition
- Kết hợp Nhiều Điều Kiện với AND: ằng cách kế thừa lớp AllNestedConditions
### Xử lý Sự kiện với @EventListener và @Async trong Spring Boot
- Sự kiện (Event): Để sử dụng Spring Boot hỗ trợ cho sự kiện, chúng ta cần định nghĩa một lớp sự kiện và kế thừa lớp ApplicationEvent.
    - Ví du:
        - public class DoorBellEvent extends ApplicationEvent
        - public DoorBellEvent(Object source, String guestName){ super(source);}
- Người phát sự kiện (Event Publisher): sử dụng đối tượng ApplicationEventPublisher để phát ra các sự kiện. Đây là một bean được cung cấp bởi Spring và có thể được tự động nạp vào ứng dụng của bạn.
    - Ví dụ:
        - @Component
        - public class MyHouse {
        - @Autowired
        - ApplicationEventPublisher applicationEventPublisher;
        - public void ringDoorbellBy(String guestName) {
        applicationEventPublisher.publishEvent(new DoorBellEvent(this, guestName));}}
- Người lắng nghe sự kiện (Event Listener): Để lắng nghe các sự kiện, chúng ta sử dụng @EventListener trên các phương thức trong các bean của ứng dụng. 
    - Ví dụ: @Component
        - public class MyDog 
        - @Async
        - @EventListener
        - public void doorBellEventListener(DoorBellEvent doorBellEvent)///
        












