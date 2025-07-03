# Buổi 4: Spring Security (Authorize & Authentication)
## Mục tiêu: 
- Hiểu được khác biệt giữa authentication và authorization
- Hiểu cách Spring security hoạt động và tích hợp vào spring boot
- Hiểu về JWT/Basic Auth/ Oauth2 (Ưu tiên hiểu về JWT và basic Auth trước)
- Phân quyền
## Lý thuyết
### Khái niệm
- Spring Security là một trong những tính năng cốt lõi của Spring Framework, nó cho phép bạn quản lý việc phân quyền và xác thực người dùng trước khi cho họ truy cập vào các tài nguyên của ứng dụng web.
- sự khác biệt giữa authentication và authorization:
    - Authentication (xác thực) là quá trình kiểm tra danh tính của người dùng, tức là kiểm tra xem người đó là ai. Ví dụ, khi một người dùng nhập username và password để đăng nhập vào hệ thống, Spring Security sẽ sử dụng AuthenticationManager để xác minh xem thông tin đăng nhập đó có đúng hay không. Nếu đúng, hệ thống sẽ công nhận danh tính của người dùng đó là hợp lệ và cho phép tiếp tục.
    - Ngược lại, authorization (phân quyền) là quá trình kiểm tra xem người dùng được phép làm gì sau khi đã xác thực thành công. Nó trả lời cho câu hỏi: “Người này có quyền truy cập vào tài nguyên cụ thể hay không?”. Ví dụ, sau khi đăng nhập thành công, một người dùng có thể gửi yêu cầu truy cập vào /dashboard. Lúc này, Spring Security sẽ kiểm tra xem người dùng đó có vai trò ROLE_ADMIN hay không. Nếu có, hệ thống cho phép truy cập; nếu không, sẽ trả về lỗi 403 Forbidden.
### Cách sử dụng
#### Triển khai:
- Phải có các phụ thuộc: spring-boot-starter-security
- Kích hoạt Spring Security -> thêm @EnableWebSecurity lên một bean bất kỳ
    - Ví dụ: @Configuration
        - @EnableWebSecurity
        - public class SecurityConfig extends WebSecurityConfigureAdapter {
        - @Override
        - protected void configure(HttpSecurity http) throws Exception {// Cấu hình phân quyền và xác thực ở đây}}
#### Xác thực người dùng
- Sử dụng In-Memory Authentication
    - Ví dụ: 
        - @Bean
        - @Override
        - public UserDetailsService userDetailsService() {// Tạo một tài khoản người dùng trong bộ nhớ (chỉ cho mục đích minh họa)
        - UserDetails user = User.*withDefaultPasswordEncoder()*.username("user").password("password").roles("USER").build();
             return new InMemoryUserDetailsManager(user);}
#### Phân quyền truy cập
- Ví dụ:
    - @Override
    - protected void configure(HttpSecurity http) throws Exception {
    - http.authorizeRequests().antMatchers("/", "/public").permitAll() // Cho phép tất cả mọi người truy cập vào trang chủ và trang public
    - .anyRequest().authenticated() // Tất cả các request khác đều cần phải xác thực mới được truy cập
    - .and().formLogin() // Cho phép người dùng xác thực bằng form login
    .loginPage("/login") // Trang đăng nhập tùy chỉnh
    .permitAll() // Tất cả đều được truy cập vào trang đăng nhập
    - .and().logout() // Cho phép logout
    .permitAll();
- Giải thích:
    - .authorizeRequests() để định cấu hình quyền truy cập.
    - .antMatchers() cho phép chúng ta xác định các URL mà người dùng có thể truy cập mà không cần xác thực.
    - .anyRequest().authenticated() yêu cầu xác thực đối với tất cả các URL còn lại.
    - .formLogin() cho phép xác thực người dùng bằng cách sử dụng một trang đăng nhập tùy chỉnh.
    - .loginPage("/login") xác định trang đăng nhập tùy chỉnh. Nếu bạn không cung cấp trang đăng nhập, Spring Security sẽ tạo một trang đăng nhập mặc định.
    - .logout() cho phép người dùng đăng xuất.
#### Controller và Giao diện người dùng
- Ví dụ: 
    - @Controller
    - public class WebController {
    - @GetMapping(value = {"/", "/public"})
    - public String home() return "home"; // Trang chủ công khai
    - @GetMapping("/private")
    public String privatePage() return "private"; // Trang yêu cầu xác thực
    - @GetMapping("/login")
    - public String loginPage() return "login"; // Trang đăng nhập tùy chỉnh}
- Sau đó nhứng html
### Spring Security với Hibernate JWT
#### Triển khai
- Cần thư viện io.jsonwebtoken.jwtt
#### Định nghĩa CustomUserDetails
- Khái niệm: CustomUserDetails là một lớp được tạo ra để thể hiện thông tin người dùng dưới dạng UserDetails mà Spring Security sử dụng. -> public class CustomUserDetails implements UserDetails
#### Triển khai UserService
- Khái niệm: UserService là một dịch vụ được tạo ra để thực hiện việc truy vấn người dùng từ cơ sở dữ liệu dựa trên username. -> public class UserService implements UserDetailsService
#### Triển khai JwtTokenProvider
- JwtTokenProvider là một lớp được tạo ra để giúp tạo và kiểm tra JWT.
#### Cấu hình và Phân quyền với Spring Security
#### Xây dựng Controller



