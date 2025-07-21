# Buổi 14: Cronjob
## Khái niệm:
- Cronjob là một tác vụ được lập lịch sẵn, cho phép bạn thực hiện một hành động tự động theo một chu kỳ định kỳ nào đó (ví dụ: mỗi ngày lúc 0h, mỗi 5 phút, mỗi thứ 2…). Đây là một cách phổ biến để tự động hóa các tác vụ như gửi email, backup dữ liệu, dọn dẹp file tạm, kiểm tra trạng thái hệ thống,...
## Cách hoạt động
- Cronjob được cấu hình bằng một biểu thức cron (cron expression).
- Mỗi khi hệ thống khớp với điều kiện thời gian trong biểu thức cron, tác vụ tương ứng sẽ được thực thi.
## Cấu trúc biểu thức cron trong Spring Boot
- Trong Spring (và nhiều hệ thống khác), biểu thức cron có dạng:
 ┌───────────── second (0-59)
 │ ┌───────────── minute (0 - 59)
 │ │ ┌───────────── hour (0 - 23)
 │ │ │ ┌───────────── day of month (1 - 31)
 │ │ │ │ ┌───────────── month (1 - 12)
 │ │ │ │ │ ┌───────────── day of week (0 - 6) (Sunday=0 or 7)
 │ │ │ │ │ │
 │ │ │ │ │ │
 * * * * * *
- Ví dụ sử dụng trong Spring:
@Scheduled(cron = "0 0 0 * * *") // 0h00 hàng ngày
public void sendFlashSaleEmail() {
    // logic gửi email
}
## Giải thích một số ký hiệu trong cron:
| Ký hiệu | Ý nghĩa                                                                          |
| ------- | -------------------------------------------------------------------------------- |
| `*`     | Bất kỳ giá trị nào                                                               |
| `*/n`   | Mỗi n đơn vị (ví dụ: `*/10` là mỗi 10 giây/phút/giờ...)                          |
| `a-b`   | Từ a đến b (ví dụ: `1-5` là từ thứ Hai đến thứ Sáu)                              |
| `a,b`   | Các giá trị cụ thể (ví dụ: `1,3,5` là thứ Hai, Tư, Sáu)                          |
| `?`     | Không xác định (chỉ dùng trong Quartz Scheduler, không dùng trong Spring cơ bản) |
- Ví dụ:
| Biểu thức cron       | Mô tả                         |
| -------------------- | ----------------------------- |
| `0 * * * * *`        | Mỗi phút                      |
| `0 */10 * * * *`     | Mỗi 10 phút                   |
| `0 0 0 * * *`        | 00:00 mỗi ngày                |
| `0 30 9 * * MON-FRI` | 9:30 sáng từ thứ 2 đến thứ 6  |
| `0 0 12 1 * *`       | 12:00 trưa ngày 1 hàng tháng  |
| `0 0 12 * * 0`       | 12:00 trưa Chủ nhật hàng tuần |
## Tích hợp cron vào Spring Boot
- Cần bật scheduler bằng @EnableScheduling trong @SpringBootApplication:
@SpringBootApplication
@EnableScheduling
public class MyApp {
    public static void main(String[] args) {
        SpringApplication.run(MyApp.class, args);
    }
}
- Viết một bean có annotated @Scheduled:
@Component
public class EmailScheduler {

    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 0 0 * * *") // 00:00 mỗi ngày
    public void sendPromotionEmail() {
        emailService.sendFlashSaleEmail();
    }
}

