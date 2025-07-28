# Quy trình phát triển phần mềm của dự án thực tế
## Các mô hình (Waterfall, Agile Scrum, Kanban)
### 1. Quy trình 
- Vòng đời phát triển phần mềm (SDLC-Software Development Life Cycle) là một 
quá trình bao gồm một loạt các hoạt động được lên kế hoạch để phát triển hoặc thay đổi 
Sản phẩm Phần mềm. SDLC còn được gọi là Quy trình phát triển phần mềm.
- Các hoạt động cơ bản của quy trình phát triển phần mềm:
    - Stage 1: Planning and Requirement Analysis (Lập kế hoạch và Phân tích Yêu 
    cầu)  
    - Stage 2: Defining Requirements (Xác định/Định nghĩa yêu cầu)
    - Stage 3: Designing the Product Architecture (Thiết kế Kiến trúc Sản phẩm)
    - Stage 4: Building or Developing the Product (Xây dựng/phát triển sản phẩm)
    - Stage 5: Testing the Product (Kiểm tra sản phẩm)
    - Stage 6: Deployment in the Market and Maintenance (Triển khai/phát hành trên 
    thị trường và duy trì/bảo trì)

### 2. Mô hình Waterfall 
#### 2.1 Giới thiệu
- Waterfall là mô hình phát triển phần mềm đầu tiên, theo quy trình tuyến tính, tuần tự. Mỗi giai đoạn phải hoàn thành trước khi bước sang giai đoạn tiếp theo và không có sự chồng chéo. Áp dụng phổ biến trong SDLC truyền thống.
#### 2.2 Các pha trong mô hình
- Thu thập & phân tích yêu cầu: Xác định đầy đủ các yêu cầu và lập tài liệu yêu cầu.
- Thiết kế hệ thống: Thiết kế kiến trúc tổng thể, xác định phần cứng, phần mềm, module.
- Thực hiện (Coding): Lập trình các đơn vị phần mềm, kiểm thử đơn vị.
- Tích hợp & Kiểm thử: Tích hợp các đơn vị, kiểm thử hệ thống, phát hiện lỗi.
- Triển khai hệ thống: Cài đặt phần mềm tại môi trường thật hoặc phát hành ra thị trường.
- Bảo trì: Sửa lỗi, cập nhật phiên bản, cải tiến sản phẩm sau khi triển khai.
#### 2.3. Đánh giá
- Ưu điểm:
    - Dễ hiểu, dễ quản lý do tính tuyến tính rõ ràng.
    - Giai đoạn tách biệt, có tài liệu rõ ràng.
    - Phù hợp dự án nhỏ, yêu cầu cố định.
    - Có thể lên kế hoạch và tiến độ cụ thể cho từng giai đoạn.
- Nhược điểm:
    - Khó thay đổi yêu cầu khi đã sang giai đoạn sau.
    - Không có sản phẩm chạy được cho đến cuối quy trình.
    - Không phù hợp với dự án lớn, yêu cầu thường xuyên thay đổi.
    - Rủi ro cao, khó đo lường tiến độ thực tế.
#### 2.4. Ứng dụng
Áp dụng Waterfall khi:
- Yêu cầu rõ ràng, ổn định.
- Công nghệ đã biết trước, ít thay đổi.
- Dự án ngắn hạn.
- Đội ngũ giàu kinh nghiệm.

### 3. Mô hình Kanban
#### 3.1. Giới thiệu
- Kanban là mô hình phát triển phần mềm theo phương pháp quản lý luồng công việc trực quan (Visual Workflow). Tập trung vào việc tối ưu hóa quy trình, giảm thời gian chuyển giao và liên tục cải tiến (Kaizen). Không chia thành vòng đời cố định như Scrum mà linh hoạt theo tiến độ thực tế.

#### 3.2. Nguyên lý cơ bản
- Hiển thị công việc: Sử dụng bảng Kanban (Kanban board) để hiển thị các thẻ công việc (task) theo các cột như:
    - To Do (Cần làm)
    - In Progress (Đang làm)
    - Done (Hoàn tất)
- Giới hạn Work In Progress (WIP): Quy định số lượng công việc có thể thực hiện cùng lúc ở mỗi cột để tránh quá tải, tăng tập trung.
- Quản lý luồng công việc (Workflow): Theo dõi tiến độ thực tế, tối ưu tốc độ xử lý, giảm thời gian chờ.
- Cải tiến liên tục (Kaizen): Thường xuyên họp nhóm đánh giá để cải thiện quy trình.
#### 3.3. Đánh giá
- Ưu điểm:
    - Trực quan, dễ nhìn, dễ theo dõi.
    - Tối ưu hóa luồng công việc.
    - Linh hoạt, dễ thích ứng với thay đổi.
    - Giảm thời gian chờ, giảm lãng phí.
    - Không cần vai trò cố định như Scrum (không bắt buộc có Scrum Master, PO,...)
- Nhược điểm:
    - Thiếu cấu trúc định kỳ, dễ trễ deadline nếu không quản lý tốt.
    - Không phù hợp với đội chưa có kỷ luật tự quản.
    - Thiếu ranh giới rõ ràng về thời gian nếu không thiết lập giới hạn.
#### 3.4. Ứng dụng
Phù hợp với:
- Đội ngũ nhỏ hoặc trung bình.
- Dự án vận hành liên tục (DevOps, bảo trì).
- Môi trường thay đổi nhanh, yêu cầu linh hoạt.
- Quản lý tác vụ hỗ trợ, ticket support, bugs.

### Mô hình Agile Scrum
#### Giới thiệu Agile
- Agile là phương pháp phát triển phần mềm linh hoạt, chia nhỏ dự án thành các vòng đời ngắn (iteration), mỗi vòng cho ra sản phẩm có giá trị. Agile thúc đẩy sự cộng tác giữa các nhóm tự quản, liên chức năng nhằm phản ứng nhanh với thay đổi và mang lại giá trị sớm cho khách hàng. Một số phương pháp phổ biến: Scrum, ASD, DSDM, FDD, LSD.
- Triết lý Agile:
    - Con người & tương tác hơn quy trình & công cụ
    - Phần mềm chạy tốt hơn tài liệu đầy đủ
    - Cộng tác với khách hàng hơn là đàm phán hợp đồng
    - Thích ứng với thay đổi hơn là bám sát kế hoạch
- 12 nguyên tắc Agile nhấn mạnh vào: thỏa mãn khách hàng, phản ứng với thay đổi, giao hàng sớm, cộng tác chặt chẽ, động lực nhóm, giao tiếp trực tiếp, đo lường bằng phần mềm hoạt động, phát triển bền vững, kỹ thuật tốt, đơn giản, nhóm tự tổ chức và cải tiến liên tục.
- Agile phù hợp khi dự án thường xuyên thay đổi, yêu cầu linh hoạt và cần phản hồi nhanh từ khách hàng.
- Lợi ích: giao hàng sớm, tăng sự hài lòng, nâng cao chất lượng và khả năng thích ứng, tăng tinh thần nhóm.
- Hạn chế: phụ thuộc nhiều vào khách hàng, khó đảm bảo tiến độ khi thay đổi liên tục, phát sinh chi phí nếu phải lặp thêm nhiều sprint.
- Agile chia sản phẩm thành các bản dựng nhỏ (increment), thực hiện qua các vòng lặp ngắn (iteration), thường kéo dài 1–3 tuần. Mỗi vòng gồm các bước: lập kế hoạch, phân tích yêu cầu, thiết kế, lập trình, kiểm thử và nghiệm thu.
####  Giới thiệu Scrum
- Scrum là mô hình phổ biến của Agile, phát triển sản phẩm qua các vòng lặp ngắn gọi là Sprint (1–4 tuần). Mỗi Sprint có mục tiêu rõ ràng và tạo ra sản phẩm có thể chuyển giao. Scrum hoạt động theo 3 nguyên lý: Minh bạch, Thanh tra, Thích nghi.
#### Nhóm Scrum
Gồm 3 vai trò chính:
- Product Owner: tối đa hóa giá trị sản phẩm, quản lý Product Backlog.
- Development Team: nhóm liên chức năng, tự tổ chức, tạo ra Increment.
- Scrum Master: huấn luyện, hỗ trợ nhóm hiểu & áp dụng Scrum đúng cách.
#### Sprint trong Scrum
- Sprint là chu kỳ phát triển cố định (≤1 tháng), liên tục, không gián đoạn.
- Trong Sprint:
    - Không thay đổi mục tiêu Sprint
    - Nhóm phát triển giữ nguyên
    - Mục tiêu chất lượng phải đảm bảo
- Các hoạt động chính trong Sprint:
    - Sprint Planning: lập kế hoạch Sprint, xác định mục tiêu và cách thực hiện.
    - Daily Scrum: họp 15 phút hằng ngày để cập nhật tiến độ và điều chỉnh kế hoạch.
    - Sprint Review: trình bày kết quả Sprint, nhận phản hồi để cải tiến.
    - Sprint Retrospective: nhóm phản ánh và đề xuất cải tiến cho Sprint tiếp theo.
- Product Backlog: danh sách các yêu cầu được sắp xếp theo mức độ ưu tiên, do Product Owner quản lý.
- Sprint Backlog: danh sách các mục tiêu Sprint đã chọn từ Product Backlog, do nhóm phát triển quản lý và cập nhật.
#### Đánh giá Scrum
- Chất lượng cao hơn
    - Kiểm thử & phản hồi liên tục qua từng Sprint.
    - Tối ưu hóa quy trình qua các buổi Sprint Review & Retrospective.
- Giảm thời gian ra thị trường (Time-to-Market)
    - Ra mắt sớm hơn 30–40% so với phương pháp truyền thống nhờ Sprint ngắn.
    - Tầm quan trọng & rủi ro cao được xử lý trước, bắt tay phát triển sớm.
- ROI cao hơn
    - Ra mắt sớm thu lợi nhanh.
    - Tự động hóa, phát hiện lỗi sớm tiết kiệm chi phí.
    -  Dự án thất bại sớm giúp giảm lãng phí.
- Khách hàng hài lòng hơn
    - Tham gia chặt chẽ qua chu kỳ Sprint.
    - Xem sản phẩm thực tế thường xuyên & đưa ra phản hồi.
- Tinh thần nhóm cao
    - Tự quản, sáng tạo và được tôn trọng.
    - Cân bằng công việc & cuộc sống.
    - Môi trường hỗ trợ và tin tưởng.
- Tăng cộng tác và tính sở hữu
    - Họp Daily Scrum, Sprint Review/Retrospective thúc đẩy trao đổi.
    - Đồng thuận cao trong nhóm về mục tiêu và cách làm.
- Đánh giá chính xác hơn
    - Số liệu rõ ràng (burndown chart, etc.) do nhóm trực tiếp cập nhật.
    - Ước lượng dựa trên khả năng thực tế của nhóm.
- Minh bạch tiến độ dự án
    - Mọi người có thể theo dõi tiến trình bất cứ khi nào.
    - Thông tin rõ ràng từ giao tiếp hàng ngày và visual board.
- Tăng kiểm soát dự án
    - Ưu tiên điều chỉnh nhanh theo từng Sprint.
    - Thích ứng tốt với thay đổi từ thị trường.
- Giảm thiểu rủi ro
    - Phát hiện sớm qua Sprint ngắn.
    - Yêu cầu rủi ro cao được triển khai đầu tiên, giảm chi phí thất bại.













