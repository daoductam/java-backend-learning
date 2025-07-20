# Integration
## Các yếu tố quan trọng khi tích hợp hệ thống khác
- Security - Gói tin đi ra bên ngoài -> rủi ro về security
- Reliability - Đảm bảo request, message gửi đi đúng nơi, đúng thứ tự , đảm bảo ko mất mát
- Performance 
    - Throughtput
    - Latency
- Complexity
.......
## 1. Phân tích yêu cầu
### 1.1 Phân tích yêu cầu
- (Automation) Một giải pháp thanh toán tự động
- (Security) Tính bảo mật cao
- (Pricing) Phí giao dịch thấp
- (Scalability) Hỗ trợ nhiều phương thức thanh toán
- Độ ổn định, hỗ trợ khách hàng, khuyến mại, giao diện, tốc độ tích hợp, quản lý và tính năng khác
### 1.2 Giải pháp
- Giải pháp: Tích hợp cổng thanh toán  
### 1.3. Tại sao là VNPay QR
- Bảo mật
    - Quan điểm: nên chọn nhà cung cấp lớn và uy tín và khả năng có tính bảo mật cao
    - Các bên cung cấp đều tuân thủ tiêu chuẩn bảo mật PCI - DSS
- Bảng giá của 1 số công thanh toán với phương thúc thanh toán là QR mobile banking(nội địa)
    - Phí trên mỗi giao dịch
        - Napas 1% + 1600 VNĐ
        - Momo 1.1%
        - VNPay 1.1% + 1650 VNĐ
        - ZaloPay 1.1%
        - Viettel Paygate 1.1k - 55k
    - Phí khác
        - Napas Phí cài đặt: 2tr vnđ Phí duy trì: 55k VNĐ/tháng
        - Còn lại: Không
- Yêu tố khác
    - Tốc độ xử lý giao địch nhanh và ổn định 
    - Sale và hỗ trợ kỹ thuật đều nhiệt tình hỗ trợ.
    - Giao diện không quá đẹp nhưng thân thiên
    - Liên kết với nhiều ngân hàng
    - Thỉnh thoảng có khuyến mại hấp dẫn
    - Tính năng quản lý đầy đủ
- Tại sao ko liên kết với Momo - Trích từ Robin Engineer
    - "Kết nối với Sale Momo hơi khó khăn nên là 1 điêm trừ"
    - "Tích hợp không đơn giản so với VNPay"

## 2. Thiết kê
### Tài liệu tích hợp
- https://sandbox.vnpayment.vn/apis/docs/thanh-toan-pay/pay.html
### 2.1 Luồng thanh toán QR
![alt text](image.png)
- 1.Payment
- 2.Payment Request
- 3.Create Transaction
- 4.Create the payment URL
- 5.Redirect
- 6.Payment QR
- 7.Fund transfer
- 8.Notify
- 9.1.1.IPN
- 9.1.2.Update transaction status
- 9.2.1 Redirect
- 9.2.2 Redirect
- 9.2.3 check the transaction status
- 