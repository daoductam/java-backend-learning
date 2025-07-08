# Buổi 4: Spring Security (Authorize & Authentication)
## Mục tiêu: 
- Hiểu được khác biệt giữa authentication và authorization
- Hiểu cách Spring security hoạt động và tích hợp vào spring boot
- Hiểu về JWT/Basic Auth/ Oauth2 (Ưu tiên hiểu về JWT và basic Auth trước)
- Phân quyền
## Lý thuyết
### PHẦN 1: Tổng quan & Thuật ngữ quan trọng
#### 1. Spring không còn là "framework" mà là "umbrella"
- Spring không còn chỉ là một framework mà là một hệ sinh thái gồm nhiều framework nhỏ hơn (Spring Boot, Spring Security, Spring Data, v.v.)
#### 2. Spring Security là gì?
- Một framework bảo mật mặc định của Spring.
- Cung cấp đầy đủ các tính năng authentication (xác thực) và authorization (phân quyền).
- Có sẵn filter chain để xử lý mọi request HTTP một cách có kiểm soát.
#### 3. Các thuật ngữ quan trọng
| Thuật ngữ            | Giải thích                                                            |
| -------------------- | --------------------------------------------------------------------- |
| **Authentication**   | Xác minh người dùng là ai. VD: đăng nhập với username/password.       |
| **Authorization**    | Kiểm tra người dùng có quyền gì. VD: user A có quyền gọi API X không? |
| **Principal**        | Người dùng hiện tại (sau khi xác thực).                               |
| **GrantedAuthority** | Một quyền cụ thể (ví dụ: `READ_BOOK`).                                |
| **Role**             | Một nhóm quyền, thường có tiền tố `ROLE_`, ví dụ: `ROLE_ADMIN`.       |
### PHẦN 2: Authentication & Authorization trong Spring Security
####  Mục tiêu:
- Hiểu cách Spring xử lý xác thực (Authentication) và phân quyền (Authorization).
- Hiểu luồng xử lý: từ request → qua filter → tới security context.
- Hiểu vai trò của các interface: AuthenticationManager, UserDetailsService, AuthenticationProvider
#### 1. Luồng xử lý Authentication trong Spring Security
User → (Request) → Filter Chain → AuthenticationManager
     → AuthenticationProvider → UserDetailsService
     → PasswordEncoder → Success → SecurityContextHolder
- Giải thích từng thành phần:
| Thành phần               | Vai trò                                                                            |
| ------------------------ | ---------------------------------------------------------------------------------- |
| `Filter Chain`           | Gồm nhiều filter. Ví dụ: `UsernamePasswordAuthenticationFilter`, `JwtTokenFilter`… |
| `AuthenticationManager`  | Trung tâm xử lý xác thực. Gửi request tới `AuthenticationProvider`.                |
| `AuthenticationProvider` | Nhận thông tin xác thực (username/password), kiểm tra với dữ liệu hệ thống.        |
| `UserDetailsService`     | Load thông tin người dùng từ DB (qua `username`).                                  |
| `UserDetails`            | Object mô tả người dùng (có username, password, roles, v.v.).                      |
| `PasswordEncoder`        | Mã hóa và so sánh password. (VD: BCryptPasswordEncoder)                            |
| `SecurityContextHolder`  | Lưu user sau khi xác thực thành công, để các request sau dùng.                     |
####  2. Ví dụ thực tế
- Giao diện UserDetailsService:
public interface UserDetailsService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
→ Đây là nơi bạn truy vấn từ DB để lấy thông tin người dùng (User entity) và ép về UserDetails.
- Giao diện UserDetails:
public interface UserDetails {
    String getUsername();
    String getPassword();
    Collection<? extends GrantedAuthority> getAuthorities();
    ...
}
→ Đây là thông tin được Spring Security dùng để xác thực & phân quyền.
- Giao diện AuthenticationProvider:
public interface AuthenticationProvider {
    Authentication authenticate(Authentication authentication) throws AuthenticationException;
    boolean supports(Class<?> authentication);
}
→ Có thể viết provider riêng, nhưng thường ta dùng sẵn DaoAuthenticationProvider của Spring.
#### 3. Authorization trong Spring Security
- Sau khi đã xác thực thành công, Spring sẽ dùng Authorization Filter để kiểm tra quyền truy cập.
- Cách kiểm tra:
    - Qua URL Config:
    http.authorizeRequests()
    .antMatchers("/admin/**").hasRole("ADMIN")
    .antMatchers("/user/**").hasAnyRole("USER", "ADMIN");
    - Hoặc dùng Annotation:
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser() { ... }
    @Secured("ROLE_USER")
    public void getUserInfo() { ... }
#### 4. ROLE_ prefix gây nhầm lẫn
- Spring tự động thêm tiền tố ROLE_ vào role.
- Ví dụ: hasRole("ADMIN") thực chất check ROLE_ADMIN.
- Nếu bạn không muốn Spring tự thêm ROLE_, hãy thêm vào config:
@Bean
public GrantedAuthorityDefaults grantedAuthorityDefaults() {
    return new GrantedAuthorityDefaults(""); // remove "ROLE_" prefix
}
#### 5. Ví dụ
- Giả sử bạn có người dùng trong DB với username là "john" và password là "123456" (đã mã hóa), bạn cần làm gì để Spring Security load và xác thực người dùng này?
    - Cài đặt UserDetailsService để Spring gọi userRepo.findByUsername("john").
    - User entity implement UserDetails.
    - Đăng ký userDetailsService() + passwordEncoder() vào AuthenticationManager.
#### 6. Tổng kết
| Khái niệm                  | Vai trò                           |
| -------------------------- | --------------------------------- |
| **Authentication**         | Xác thực người dùng là ai         |
| **Authorization**          | Kiểm tra người dùng có quyền gì   |
| **UserDetailsService**     | Load user từ DB                   |
| **AuthenticationProvider** | Kiểm tra thông tin đăng nhập      |
| **PasswordEncoder**        | So sánh mật khẩu                  |
| **SecurityContextHolder**  | Lưu user sau đăng nhập thành công |

### PHẦN 3: Tạo REST API & cấu hình Spring Security cơ bản
#### Mục tiêu:
- Tạo ứng dụng Spring Boot đơn giản.
- Thêm Spring Security và hiểu cách nó can thiệp mặc định.
- Biết cách cấu hình Security ban đầu: username, password, endpoint bảo vệ.
#### 1. Tạo REST API đơn giản
- Dùng https://start.spring.io, tạo project với Spring Boot Starter Web
    - pom.xml:
    <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    - Controller:
    @RestController
    @RequestMapping("/hello")
    public class HelloRestController {

        @GetMapping("/user")
        public String helloUser() {
            return "Hello User";
        }

        @GetMapping("/admin")
        public String helloAdmin() {
            return "Hello Admin";
        }
    }
-  Chạy ứng dụng → Truy cập:
    - http://localhost:8080/hello/user → "Hello User"
    - http://localhost:8080/hello/admin → "Hello Admin"
#### 2. Thêm Spring Security
- pom.xml:
    <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
-  Ngay khi thêm Spring Security, hệ thống sẽ tự động bảo vệ mọi endpoint, yêu cầu login.
- Truy cập http://localhost:8080/hello/user sẽ bị redirect đến /login
#### 3. Đăng nhập mặc định
- Spring sẽ tạo sẵn một user:
    - Username: user
    - Password: xuất hiện trong console log: Using generated security password: 3b74f776-a808-4e15-8d7d-c5d5cddaa0a1
- Muốn gán mật khẩu cố định, thêm vào application.properties:
    spring.security.user.name=admin
    spring.security.user.password=admin123
#### 4. Cấu hình Security bằng Java Config (SecurityConfig.java)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/hello/user").permitAll()
                .antMatchers("/hello/admin").authenticated()
                .and()
            .formLogin();
    }
}
- Giải thích:
    - /hello/user: ai cũng truy cập được.
    - /hello/admin: cần đăng nhập mới được.
    - .formLogin(): hiển thị form login mặc định của Spring.
#### 5. Logout
- Truy cập http://localhost:8080/logout để đăng xuất (mặc định có sẵn).
#### NOTE
- Nếu bạn chưa cấu hình gì, Spring Security mặc định cho phép ai truy cập API?
    - ❌ Không ai cả, mọi endpoint đều yêu cầu xác thực.
- Muốn mở public endpoint, bạn dùng đoạn nào?
    - http.authorizeRequests()
        .antMatchers("/api/public/**").permitAll();
- Muốn tạo tài khoản cố định, bạn cấu hình ở đâu?
    - spring.security.user.name=admin
    spring.security.user.password=admin123
#### Tổng kết 
| Tính năng                              | Cấu hình/Default         |
| -------------------------------------- | ------------------------ |
| Security tự bảo vệ mọi URL             | Mặc định bật             |
| Tài khoản `user` & mật khẩu ngẫu nhiên | In ra console            |
| Đăng nhập                              | `/login` form mặc định   |
| Đăng xuất                              | `/logout`                |
| Muốn phân quyền theo URL               | `.authorizeRequests()`   |
| Cấu hình mặc định `username/password`  | `application.properties` |

### PHẦN 4: Cấu hình JWT Authentication – SecurityConfig & JwtTokenFilter
#### Mục tiêu:
- Biến ứng dụng Spring từ session-based thành stateless (dùng JWT).
- Tạo class cấu hình bảo mật (SecurityConfig) với JWT.
- Tạo JWT filter để kiểm tra token trong mỗi request.
#### 1. Mục tiêu của cấu hình JWT
| Session-based (mặc định) | JWT-based (ta cần)                          |
| ------------------------ | ------------------------------------------- |
| Có phiên đăng nhập       | Stateless hoàn toàn                         |
| Token lưu ở cookie       | Token nằm trong `Authorization: Bearer xxx` |
| Login tạo session        | Login trả về JWT token                      |
| Logout xóa session       | Logout = xóa token phía client              |
- Để đạt điều này, ta cần:
    - Tắt CSRF, bật CORS.
    - Đặt SessionCreationPolicy.STATELESS
    - Định nghĩa các endpoint public/private
    - Thêm custom JwtTokenFilter vào FilterChain
    - Cấu hình AuthenticationManager, PasswordEncoder, UserDetailsService

#### 2. SecurityConfig hoàn chỉnh
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserRepo userRepo;
    private final JwtTokenFilter jwtTokenFilter;

    public SecurityConfig(UserRepo userRepo, JwtTokenFilter jwtTokenFilter) {
        this.userRepo = userRepo;
        this.jwtTokenFilter = jwtTokenFilter;
    }

    // 1. Xác thực người dùng từ DB
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username -> userRepo
            .findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"))
        );
    }

    // 2. Cung cấp bean để mã hóa password
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 3. Cấu hình endpoint, jwt filter, session...
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http = http.cors().and().csrf().disable();

        http = http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        http = http.exceptionHandling()
                .authenticationEntryPoint((req, res, ex) -> {
                    res.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
                })
                .and();

        http.authorizeRequests()
                .antMatchers("/api/public/**").permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    // 4. Cho phép sử dụng AuthenticationManager
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // 5. CORS cho phép tất cả nguồn (tuỳ bạn chỉnh sau)
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(List.of("*"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowedMethods(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
#### 3. Tạo JwtTokenFilter (lọc token)
- Lớp này sẽ:
    - Kiểm tra header "Authorization" có token không
    - Xác minh token
    - Lấy username từ token và nạp vào SecurityContext
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepo userRepo;

    public JwtTokenFilter(JwtTokenUtil jwtTokenUtil, UserRepo userRepo) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepo = userRepo;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        // 1. Lấy header
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        // 2. Lấy token từ header
        final String token = header.split(" ")[1].trim();
        if (!jwtTokenUtil.validate(token)) {
            chain.doFilter(request, response);
            return;
        }

        // 3. Lấy thông tin người dùng từ token
        UserDetails userDetails = userRepo
            .findByUsername(jwtTokenUtil.getUsername(token))
            .orElse(null);

        // 4. Tạo authentication object và set vào context
        UsernamePasswordAuthenticationToken authentication =
            new UsernamePasswordAuthenticationToken(
                userDetails, null,
                userDetails != null ? userDetails.getAuthorities() : List.of()
            );

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }
}
#### 4. JwtTokenUtil là gì?
- Đây là class bạn cần tự viết để:
    - Sinh JWT
    - Giải mã JWT
    - Xác minh hạn
    - Trích xuất username từ token
- Ví dụ cơ bản (sẽ dạy kỹ ở PHẦN 5):
public String getUsername(String token) {
    return Jwts.parser().setSigningKey(secret)
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
}
#### NOTE
- JWT filter nên nằm trước filter nào?
    - UsernamePasswordAuthenticationFilter
- Tại sao phải set SessionCreationPolicy.STATELESS?
    - Vì ta không dùng session, JWT sẽ được gửi trong mỗi request → stateless
- Header Authorization phải có định dạng gì?
    - "Authorization: Bearer <token>"

### PHẦN 5: Tạo Login API và sinh JWT Token
#### Mục tiêu:
- Xây dựng API POST /api/public/login để client gửi username/password.
- Xác thực tài khoản.
- Sinh JWT và trả về trong header + body.
#### 1. Cấu trúc tổng quan
- AuthRequest: DTO nhận username, password
- User: entity implement UserDetails
- JwtTokenUtil: class sinh và xác minh JWT
- AuthApi: REST controller chứa /login
#### 2. DTO: AuthRequest
public class AuthRequest {
    private String username;
    private String password;

    // getters/setters
}
#### @Entity
public class User implements UserDetails {

    @Id
    private String id;

    private String username;
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    // Implement methods of UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
            .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
            .collect(Collectors.toList());
    }

    @Override public String getPassword() { return password; }
    @Override public String getUsername() { return username; }

    // Các method khác return true cho đơn giản
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
#### 4. JwtTokenUtil: Sinh JWT
@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration-in-ms}")
    private Long expirationMs;

    public String generateAccessToken(User user) {
        return Jwts.builder()
            .setSubject(user.getUsername())
            .claim("roles", user.getRoles())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact();
    }

    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsername(String token) {
        return Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }
}
- application.properties:
jwt.secret=very-secret-key
jwt.expiration-in-ms=3600000  # 1 hour

#### 5. Tạo API /api/public/login
@Api(tags = "Authentication")
@RestController
@RequestMapping("/api/public")
public class AuthApi {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepo userRepo;

    public AuthApi(AuthenticationManager authenticationManager,
                   JwtTokenUtil jwtTokenUtil,
                   UserRepo userRepo) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepo = userRepo;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.getUsername(), request.getPassword()
                )
            );

            User user = (User) authentication.getPrincipal();
            String token = jwtTokenUtil.generateAccessToken(user);

            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .body(Map.of(
                        "username", user.getUsername(),
                        "roles", user.getRoles(),
                        "token", token
                    ));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid username or password"));
        }
    }
}
- Nếu xác thực thành công:
    - Trả về HTTP 200 + JWT token trong header & body.
    - JWT được dùng trong mọi request sau: Authorization: Bearer <token>
#### 6. Test login bằng Postman
- Request:
    POST http://localhost:8080/api/public/login
    Content-Type: application/json

    {
    "username": "admin",
    "password": "admin123"
    }
- Response:
    {
    "username": "admin",
    "roles": ["USER_ADMIN"],
    "token": "eyJhbGciOiJIUzUxMiJ9..."
    }
- Header:
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9...
### PHẦN 6: Phân quyền bằng Role (Authorization) - URL & Annotation
####  Mục tiêu:
- Giới hạn quyền truy cập API theo role của người dùng.
- Có hai cách cấu hình:
    - Phân quyền theo URL (hasRole, hasAuthority)
    - Phân quyền bằng annotation trên method/class (@RolesAllowed, @PreAuthorize)
#### 1. Các khái niệm cơ bản

| Khái niệm             | Ý nghĩa                                     |
| --------------------- | ------------------------------------------- |
| **Role**              | Một nhóm quyền (permissions)                |
| **Authority**         | Một quyền cụ thể                            |
| **GrantedAuthority**  | Interface đại diện cho cả Role và Authority |
| **hasRole('X')**      | Kiểm tra có quyền `ROLE_X`                  |
| **hasAuthority('Y')** | Kiểm tra có quyền `Y`                       |

- Spring tự động thêm prefix ROLE_ vào role, tức: 
    hasRole("ADMIN") → tìm "ROLE_ADMIN"
####  2. Phân quyền theo URL (trong SecurityConfig)
- Ví dụ:
    http.authorizeRequests()
        .antMatchers("/api/public/**").permitAll()
        .antMatchers("/api/admin/**").hasRole("ADMIN")
        .antMatchers("/api/user/**").hasAnyRole("USER", "ADMIN")
        .anyRequest().authenticated();
- Giải thích:
    - /api/public/**: không cần login
    - /api/admin/**: chỉ ai có "ROLE_ADMIN"
    - /api/user/**: cả "ROLE_USER" và "ROLE_ADMIN" đều vào được
    - anyRequest(): yêu cầu phải đăng nhập
#### 3. Phân quyền bằng Annotation
- Spring Security hỗ trợ nhiều loại annotation:

| Annotation                  | Hỗ trợ SpEL | Mô tả                         |
| --------------------------- | ----------- | ----------------------------- |
| `@Secured`                  | ❌           | Đơn giản, chỉ hỗ trợ role     |
| `@RolesAllowed`             | ❌ (JSR-250) | Tương tự `@Secured`           |
| `@PreAuthorize`             | ✅           | Phổ biến, mạnh mẽ (dùng SpEL) |
| `@PostAuthorize`            | ✅           | Kiểm tra sau khi method chạy  |
| `@PreFilter`, `@PostFilter` | ✅           | Lọc input/output list/array   |

- Kích hoạt annotation trong SecurityConfig
@EnableGlobalMethodSecurity(
    securedEnabled = true,
    jsr250Enabled = true,
    prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // ...
}
- Cách dùng các annotation
    - 1. @RolesAllowed (JSR-250): Role trong DB là "ADMIN", Spring sẽ tự thêm "ROLE_"
        @RolesAllowed("ADMIN")
        @GetMapping("/admin/hello")
        public String helloAdmin() {
            return "Hello admin";
        }
    - 2. @PreAuthorize
        @PreAuthorize("hasRole('ADMIN')")
        @GetMapping("/admin/hello")
        public String helloAdmin() {
            return "Hello Admin";
        }
        - Bạn cũng có thể dùng authority: @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    - 3. Trên class + method
        @RestController
        @RequestMapping("/api/user")
        @RolesAllowed("USER")
        public class UserApi {

            @GetMapping("/profile")
            public String profile() {
                return "User profile";
            }

            @RolesAllowed("ADMIN")
            @DeleteMapping("/ban")
            public String banUser() {
                return "User banned";
            }
        }
        - Lớp áp dụng USER, method banUser() yêu cầu ADMIN.

- Lưu ý về prefix ROLE_
    - Spring Security mặc định thêm ROLE_ vào role → nếu bạn ghi: hasRole("ADMIN") thì Spring thực chất tìm: new SimpleGrantedAuthority("ROLE_ADMIN")
    - Tắt tự động thêm prefix ROLE_:
        @Bean
        GrantedAuthorityDefaults grantedAuthorityDefaults() {
            return new GrantedAuthorityDefaults(""); // không còn tự thêm "ROLE_"
        }

#### Test nhanh
- Giả sử user "alice" có role "USER":
    {
    "username": "alice",
    "roles": ["USER"],
    "token": "eyJ..."
    }
- Khi gọi API:
    GET /api/user/profile
    Authorization: Bearer eyJ...
-> ✅ Được phép
    GET /api/admin/hello
    Authorization: Bearer eyJ...
-> ❌ 403 Forbidden

### PHẦN 7: Gán Role cho User trong DB và phân quyền động theo Role
#### Mục tiêu:
- Lưu role của user trong database.
- Gán nhiều role cho một user.
- Dùng thông tin role này để phân quyền động khi xác thực bằng JWT.
#### 1. Thiết kế bảng/collection User với role
- Nếu dùng MongoDB:
@Document
public class User implements UserDetails {
    @Id
    private String id;

    private String username;
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    // implement UserDetails ...
}
- Trường roles chứa danh sách vai trò: ["USER_ADMIN", "BOOK_ADMIN"]
#### 2. Chuyển Role thành GrantedAuthority
- Spring Security phân quyền dựa vào interface GrantedAuthority.
- Cài đặt trong getAuthorities():
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
            .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
            .collect(Collectors.toList());
    }
- Lưu ý: Spring mặc định kiểm tra "ROLE_X" khi dùng hasRole("X").
#### 3. Gán role khi tạo user mới
- Bạn có thể gán role lúc đăng ký user, hoặc tạo sẵn user với role:
- Ví dụ khi tạo tài khoản admin:
    User user = new User();
    user.setUsername("admin");
    user.setPassword(passwordEncoder.encode("admin123"));
    user.setRoles(List.of("USER_ADMIN", "BOOK_ADMIN"));
    userRepo.save(user);
#### 4. Dùng role từ DB để phân quyền động
- Khi user login:
    - Spring Security gọi UserDetailsService
    - Lấy User từ DB → lấy roles
    - Gán vào Security Context qua JwtTokenFilter
→ Các endpoint sử dụng @RolesAllowed, @PreAuthorize, hasRole() sẽ tự hoạt động đúng.
#### 5. Ví dụ sử dụng vai trò động
- User có role "AUTHOR_ADMIN":
    {
    "username": "alice",
    "roles": ["AUTHOR_ADMIN"],
    "token": "eyJ..."
    }
- Gọi API tạo Author:
    @RolesAllowed("AUTHOR_ADMIN")
    @PostMapping("/api/author")
    public void createAuthor() { }
- ⏩ Token JWT chứa "AUTHOR_ADMIN" → truy cập được.
#### 6. Tùy chỉnh Role theo nhu cầu
- Role theo enum:
    public enum Role {
        USER_ADMIN,
        BOOK_ADMIN,
        AUTHOR_ADMIN
    }
- Role theo bảng riêng trong SQL (quan hệ nhiều-nhiều)
- Hoặc cấu hình Permission chi tiết hơn trong bảng khác
#### 7. Tắt prefix "ROLE_" nếu muốn dùng raw role
- Nếu bạn không muốn prefix "ROLE_", thêm vào SecurityConfig:
    @Bean
    GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults(""); // bỏ ROLE_ prefix
    }
- Sau đó: .hasAuthority("BOOK_ADMIN") thay vì: .hasRole("BOOK_ADMIN")

### PHẦN 8: Viết Unit Test / Integration Test cho Spring Security dùng JWT
#### Mục tiêu:
    - Kiểm thử bảo mật (security) của API.
    - Test các endpoint yêu cầu xác thực/ủy quyền.
    - Làm quen với mock JWT và SecurityContext trong Spring Test.
#### 1. Thêm dependency test
- Trong pom.xml, thêm các dependencies sau:
    <!-- Spring Boot Test -->
    <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
    <exclusions>
        <exclusion>
        <groupId>org.junit.vintage</groupId>
        <artifactId>junit-vintage-engine</artifactId>
        </exclusion>
    </exclusions>
    </dependency>

    <!-- Spring Security Test -->
    <dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-test</artifactId>
    <scope>test</scope>
    </dependency>
#### 2. Dùng @SpringBootTest và @AutoConfigureMockMvc
    @SpringBootTest
    @AutoConfigureMockMvc
    public class BookApiTest {

        @Autowired
        private MockMvc mockMvc;

        @Test
        public void test_public_api() throws Exception {
            mockMvc.perform(get("/api/public/ping"))
                .andExpect(status().isOk());
        }
    }
####  3. Dùng @WithMockUser để test secured endpoint
@Test
@WithMockUser(username = "admin", roles = {"BOOK_ADMIN"})
public void test_create_book_with_admin() throws Exception {
    mockMvc.perform(post("/api/book")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"title\": \"Clean Code\"}"))
        .andExpect(status().isOk());
}
- ✅ Spring tự tạo user có vai trò ROLE_BOOK_ADMIN.
#### 4. Dùng @WithAnonymousUser
    @Test
    @WithAnonymousUser
    public void test_create_book_without_login() throws Exception {
        mockMvc.perform(post("/api/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"Clean Code\"}"))
            .andExpect(status().isUnauthorized());
    }
#### 5. Dùng @WithUserDetails nếu bạn có User trong DB
- Ví dụ: trong DB có user "user1" đã đăng ký với ROLE_USER
    @Test
    @WithUserDetails("user1")
    public void test_profile_access() throws Exception {
        mockMvc.perform(get("/api/user/profile"))
            .andExpect(status().isOk());
    }
- Annotation này gọi UserDetailsService để lấy user thật từ DB
#### 6. Giả lập SecurityContext thủ công
- Nếu bạn cần nhiều quyền kiểm soát hơn, có thể viết custom annotation với @WithSecurityContext, hoặc thủ công setup SecurityContextHolder.
- Ví dụ:
    @BeforeEach
    public void setupSecurityContext() {
        UserDetails user = new CustomUser("admin", "pass", List.of(new SimpleGrantedAuthority("ROLE_BOOK_ADMIN")));
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(token);
    }
#### 7. Test Token thực tế (Integration Test với JWT thật)
- Nếu bạn muốn test với JWT thật sự, bạn cần:
    - Login lấy token:
        String token = mockMvc.perform(post("/api/public/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"admin\",\"password\":\"admin123\"}"))
            .andReturn().getResponse().getHeader(HttpHeaders.AUTHORIZATION);
    - Dùng token đó để test endpoint:
        mockMvc.perform(get("/api/book")
                .header(HttpHeaders.AUTHORIZATION, token))
            .andExpect(status().isOk());
#### 8. Tổng kết
| Kỹ thuật                   | Khi dùng                              |
| -------------------------- | ------------------------------------- |
| `@WithMockUser`            | Test đơn giản với role mock           |
| `@WithUserDetails`         | Có user thật trong DB                 |
| `@WithAnonymousUser`       | Kiểm thử truy cập trái phép           |
| Token thực                 | Test sát thực tế (integration)        |
| Thủ công `SecurityContext` | Test logic độc lập không dùng MockMvc |
#### Bonus: Lưu ý quan trọng    
- Nếu bạn dùng JWT filter, nhớ disable CSRF, stateless session, và bật MockMvc với đúng context.
### PHẦN 9: Cấu trúc Dự Án Thực Tế cho Spring Security + JWT
#### Mục tiêu:
- Xây dựng cấu trúc chuẩn, dễ mở rộng cho dự án backend sử dụng Spring Boot + Spring Security + JWT.
- Phân chia rõ ràng: controller, service, repository, security, dto, mapper, ...
- Dễ dàng tích hợp thêm tính năng (OAuth2, refresh token, RBAC...)
#### 1. Tổng quan cấu trúc chuẩn
src/
└── main/
    ├── java/com/example/securityjwt/
    │   ├── config/                  <-- Cấu hình chung (CORS, security, beans)
    │   ├── controller/             <-- REST API (Auth, User, Book, Author)
    │   ├── dto/                    <-- Dữ liệu truyền giữa client/server
    │   ├── entity/                 <-- Entity (MongoDB/SQL)
    │   ├── exception/              <-- Custom exception
    │   ├── mapper/                 <-- Mapper giữa Entity và DTO
    │   ├── repository/             <-- Spring Data (JPA/Mongo)
    │   ├── security/               <-- JWT, SecurityConfig, Filters
    │   ├── service/                <-- Business logic
    │   ├── util/                   <-- TokenUtil, Utils khác
    │   └── SecurityJwtApplication.java
    └── resources/
        ├── application.properties
        └── ...
#### 2. Chi tiết từng thành phần
- security/
    - SecurityConfig.java – cấu hình Spring Security.
    - JwtTokenFilter.java – filter kiểm tra và xác thực JWT.
    - JwtTokenUtil.java – tạo và validate token.
    - CustomUserDetailsService.java – load user từ DB.
    - GrantedAuthorityDefaults bean – nếu muốn bỏ ROLE_ prefix.
- controller/
    AuthController         // /api/public/login, register
    UserController         // /api/user/me
    BookController         // /api/book/**
    AuthorController       // /api/author/**
→ Controller chỉ xử lý HTTP logic, gọi service.
- service/
    AuthService            // Xử lý login, register, sinh token
    UserService            // Trả về thông tin user đang đăng nhập
    BookService
    AuthorService
→ Viết business logic ở đây, dễ test, dễ bảo trì.
- entity/
    User.java              // implements UserDetails
    Book.java
    Author.java
→ Nếu dùng Mongo: annotate @Document, nếu SQL: annotate @Entity.
- repository/
    UserRepository extends MongoRepository<User, String>
    BookRepository
    AuthorRepository
- mapper/
    UserViewMapper.java    // Entity → DTO
    BookMapper
→ Tách mapping để controller/service không dính logic.
- dto/
    AuthRequest
    UserView
    BookRequest / BookView
→ Giao tiếp client/server: dùng DTO để tách entity khỏi public interface.
- exception/
    GlobalExceptionHandler
    ApiException
→ Xử lý lỗi tập trung, throw message phù hợp cho client.
- util/
    JwtTokenUtil
    DateUtil
####  3. Thêm thư mục test
src/
└── test/
    └── java/com/example/securityjwt/
        ├── integration/
        └── unit/
→ Dễ phân biệt test chức năng (integration) và logic (unit)
#### 4. Nguyên tắc vàng trong tổ chức code
| Quy tắc                                   | Giải thích                                     |
| ----------------------------------------- | ---------------------------------------------- |
| Tách `controller`, `service`, `mapper`    | Đảm bảo nguyên tắc SRP (Single Responsibility) |
| Không dùng `@Entity` trong response       | Dùng DTO để tránh lộ DB model                  |
| Không gọi repo trực tiếp trong controller | Phải đi qua service                            |
| Viết rõ ràng `exception`                  | Không dùng `throw RuntimeException()` bừa bãi  |
| Cấu hình `SecurityConfig` riêng biệt      | Không lẫn với logic business                   |
#### 5. Gợi ý tên gói thực tế
com.bookstore.securityjwt
├── config
├── controller
├── dto
├── entity
├── exception
├── mapper
├── repository
├── security
├── service
├── util
#### 6. Ghi nhớ khi mở rộng
| Bạn muốn               | Thêm vào                                    |
| ---------------------- | ------------------------------------------- |
| Refresh Token          | `RefreshTokenService`, DB lưu token         |
| OAuth2 Google/Facebook | `Oauth2SuccessHandler`, `OAuth2UserService` |
| 2FA hoặc OTP           | `OtpService`, flow xác thực thứ 2           |
| RBAC hoặc ABAC         | Cấu trúc bảng Role-User, Permission-Role    |
#### 7. Tổng kết
- Cấu trúc rõ ràng = dễ mở rộng + dễ test + dễ onboard developer khác.
- Tách biệt JWT logic, SecurityConfig, Business logic, Entity, DTO.
- Áp dụng kiến trúc 3 lớp (Controller → Service → Repository) kết hợp Spring Security đúng chuẩn.














### Thực hành
#### authentication flow
![alt text](aaaa.png)
- 1. Actor → Gửi request
Người dùng (actor) gửi request HTTP đến server, ví dụ POST /login.
- 2. Security Filter Chains
Request được lọc qua Security Filter Chain. Đây là nơi các Filter như UsernamePasswordAuthenticationFilter hoạt động.
- 3. UsernamePasswordAuthenticationToken
UsernamePasswordAuthenticationFilter tạo ra object UsernamePasswordAuthenticationToken chứa username/password do user nhập vào.
- 4. Gửi token đến AuthenticationManager
Token được gửi đến AuthenticationManager (thường là ProviderManager) để xử lý xác thực.
- 5. AuthenticationManager → AuthenticationProvider
AuthenticationManager chuyển token đến một hoặc nhiều AuthenticationProvider. Đây là nơi bạn cài logic xác thực (VD: xác thực từ DB, OAuth...).
- 6. Provider dùng PasswordEncoder
AuthenticationProvider sẽ dùng PasswordEncoder (ví dụ: BCryptPasswordEncoder) để kiểm tra password người dùng nhập có khớp với DB không.
- 7. Provider gọi UserDetailsService
AuthenticationProvider gọi UserDetailsService.loadUserByUsername() để lấy thông tin user từ DB.
- 8. Lấy thông tin từ User
UserDetailsService truy vấn DB hoặc service khác để lấy UserDetails (chứa username, password mã hóa, roles…).
- 9. Trả lại UserDetails
UserDetailsService trả lại UserDetails cho AuthenticationProvider.
- 10. Provider xác thực thành công
So sánh password, nếu đúng → trả về Authentication object (đã được xác thực) cho AuthenticationManager.
- 11. AuthenticationManager trả kết quả
AuthenticationManager gửi kết quả xác thực thành công trở lại cho Security Filter Chain.
- 12. Lưu vào SecurityContextHolder
Cuối cùng, Authentication Filter sẽ lưu Authentication vào SecurityContextHolder. Từ đây, bạn có thể gọi SecurityContextHolder.getContext().getAuthentication() để biết người dùng hiện tại là ai.



