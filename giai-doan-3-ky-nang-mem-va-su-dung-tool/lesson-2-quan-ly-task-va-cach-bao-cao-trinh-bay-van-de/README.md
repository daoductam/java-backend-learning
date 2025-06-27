# Buổi 2: Thiết kế hệ thống
## Mục tiêu
- Hình dung được thực tế các công ty sẽ vận hành và quản lý task như nào
- Trình bày vấn đề cho stakeholders hiểu (VD: trình bày mô tả API cho FE developer hiểu, cho tester hiểu hoặc cho business analyst hiểu mình đang làm gì và sẽ làm gì)
## Lý thuyết
### 1. Giới thiệu về Agile
- Agile là một phương pháp luận (methodology) về quản lý dự án bao gồm việc phân chia dự án thành các giai đoạn và nhấn mạnh sự hợp tác và cải tiến lên tục.
- Bốn giá trị cốt lõi:
    - Cá nhân và sự tương tác hơn là quy trình và công cụ
    - Phần mềm chạy tốt hơn là tài liệu đầy đủ
    - Cộng tác với khách hàng hơn là đàm phán hợp đồng
    - Phản hồi với các thay đổi hơn là bám sát kế hoạch
### 2. Scrum là gì?
- Khái niệm: *Scrum* là phương pháp nổi tiếng và phổ biến nhất trong tập hợp các phương pháp phát triển phần mềm theo tư tưởng Agile. Scrum không phải là một quy trình hay kỹ thuật để phát triển sản phẩm phần mềm, Scrum là một management framework cho phép chúng ta sử dụng những quy trình hay kỹ thuật khác nhau nhằm đạt hiệu quả cao nhất về sự thích ứng và sáng tạo.
- Cách hoạt động: 
    - *Product Owner* tạo ra *Product Goal* và *Product Backlog* chứa các yêu cầu của dự án với những hạng mục được xếp theo độ ưu tiên.
    - *Nhóm Scrum* sẽ phát triển sản phẩm thông qua nhiều *Sprint*. Mỗi Sprint kéo dài 1 tới 4 tuần. Bắt đầu Sprint với một cuộc họp *Sprint Planning* để ước lượng công việc, lập kế hoạch và xác định *Sprint Goal*.
    - Các ngày trong Sprint, nhóm Scrum làm việc cùng nhau, thực hiện họp *Daily Scrum* tối đa 15 phút mỗi ngày.
    - Trong Sprint, *Nhóm phát triển* tạo ra các phần tăng trưởng, gọi là *Increment*.
    - Kết thúc Sprint, nhóm Scrum thực hiện *Sprint Review* để review sản phẩm.
    - Sau đó, nhóm Scrum thực hiện *Sprint Retrospective* để cải tiến quy trình.
### 3. Scrum Team là gì?
- *Scrum Team* (nhóm Scrum) là một nhóm nhỏ, tối đa 10 người, bao gồm một Product Owner (PO), một Scrum Master và Nhóm phát triển (Developement Team).
    - Product Owner (PO): chịu trách nhiệm về việc quản lý sản phẩm, quản lý Product Backlog
    - Scrum Master: là người chịu trách nhiệm đảm bảo Scrum được hiểu và được thực hành đúng trong nhóm Scrum, đảm bảo rằng nhóm Scrum luôn tôn trọng lý thuyết Scrum và quy tắc đã được thống nhất trong nhóm.
    - Nhóm phát triển (Development Team): bao gồm các chuyên gia, những người có đầy đủ năng lực chuyên môn cùng cộng tác với nhau để đưa ra một phần tăng trưởng vào cuối mỗi Sprint. Chỉ có các thành viên của Nhóm phát triển tạo ra các phần tăng trưởng (Increment).
### 4. Các sự kiện trong Scrum
- *Sprint* là khoảng thời gian bao gồm tất cả các sự kiện Scrum khác. Nó được coi là trái tim của Scrum, nơi ý tưởng được biến thành giá trị.
- *Sprint Planning* là cuộc họp lập kế hoạch công việc cho Sprint sắp tới, thời diễn ra vào đầu Sprint, là sự kiện khởi đầu của Sprint.
    - Các nhóm hay dùng Story Point để so sánh nỗ lực tương đối của các hạng mục công việc với nhau - 
    dùng kỹ thuật Planning Poker dùng dãy số Fibonacci để ước lượng Story Point.
    - Ví dụ User story: "Là người dùng, tôi muốn đặt lại mật khẩu của mình để có thể lấy lại quyền truy cập vào tài khoản của mình nếu lỡ tôi quên mật khẩu.". Các bước thực hiện ước lượng bằng Story Point:
        - User story: PO giới thiệu chi tiết User story, đảm bảo tất cả đều hiểu rõ yêu cầu.
        - Ước lượng: Trong Sprint Planning, mỗi thành viên trong nhóm chọn một lá bài từ bộ bài mà họ cảm thấy thể hiện nỗ lực cần thiết cho User story ở trên. Các lá bài chứ một số thuộc dãy Fibonacci: 1, 2, 3, 5, 8, 13, 21, ...
        - Tiết lộ: Sau khi mọi người đã chọn một thẻ, tất cả các thành viên trong nhóm sẽ đồng thời tiết lộ thẻ của mình. Giả sử, A bốc được 5, tương tự B - 8, C - 5 và D - 8
        - Thảo luận: Nếu có sự khác biệt lớn trong ước lượng (ví dụ: A chọn 3 trong khi B chọn 21), nhóm sẽ thảo luận lý do đưa ra ước lượng của họ. Trong ví dụ này, chỉ có một sự khác biệt nhỏ: một số chọn 5 và những người khác chọn 8. Cả A và C chọn 5, có thể giải thích lý do tại sao họ cảm thấy cần nỗ lực ít hơn, B và D chọn 8, có thể làm rõ lý do tại sao họ cần nhiều nỗ lực hơn.
        - Ước lượng lại (nếu cần): Việc cá nhân chọn lại thẻ có thể diễn ra nếu cần.
        - Đồng thuận: Nhóm đạt được sự đồng thuận về ước tính nỗ lực. Trong ví dụ này, sau khi thảo luận, mọi người có thể thống nhất rằng 8 là story point. -> Kết quả: User story trên có story point là 8.
    - Hoạt động: 
        - Thành phần tham dự: ScrumMaster, Product Owner, Nhóm phát triển
        - Thời gian: Tối đa là 8 giờ cho Sprint 4 tuần
        - Kết quả: Sprint Backlog, Sprint Goal
- *Daily Scrum* là cuộc họp diễn ra trong tối đa 15 phút, tại một thời điểm cố định, tại một vị trí cố định, trong đó các thành viên trong nhóm phát triển lần lượt trả lời đúng 3 câu hỏi sau:
    - Tôi đã làm gì trong 24 giờ đã qua để đạt được Sprint Goal?
    - Tôi sẽ làm gì trong 24 giờ tiếp theo để đạt được Sprint Goal?
    - Tôi thấy có trở ngại gì ảnh hưởng tới tôi hoặc nhóm phát triển để đạt được Sprint Goal?
    - Hoạt động: 
        - Thành phần tham dự: ScrumMaster, Nhóm phát triển
        - Thời gian: Tối đa là 15 phút
        - Kết quả: Kế hoạch được cập nhật cho ngày
- *Sprint Review* là sự kiện được tổ chức vào cuối Sprint để đánh giá kết quả của Sprint.
    - Hoạt động: 
        - Thành phần tham dự: ScrumMaster, Product Owner, Nhóm phát triển, Các bên có liên quan
        - Thời gian: Tối đa là 4 giờ với Sprint 4 tuần
        - Kết quả: Product Backlog được chỉnh sửa cho phù hợp với Sprint tiếp theo
- *Sprint Retrospective* là cuộc họp để để nhìn lại Sprint vừa qua và lên kế hoạch để nâng cao chất lượng và hiệu quả cho các Sprint sắp tới, thường diễn ra sau Sprint Review và trước Sprint Planning.
    - Hoạt động: 
        - Thành phần tham dự: ScrumMaster, Product Owner, Nhóm phát triển
        - Thời gian: Tối đa là 3 giờ với Sprint 4 tuần
        - Kết quả: Cải tiến quy trình
- *Backlog Refinement* (Grooming) là một sự kiện diễn ra để cập nhật và sắp xếp ưu tiên cho Product Backlog. Đây không phải là sự kiện bắt buộc trong Scrum như Daily Scrum hay Sprint Review.
### 5. Scrum Artifacts
- *Artifact* (tạo tác) trong Scrum được hiểu là những thể hiện của công việc hoặc giá trị, được *minh bạch* nhằm tạo khả năng *thanh tra* và *thích ứng*.
- *User Story*:  có dạng: Là <người dùng>, tôi muốn <mục tiêu>, để <giá trị>
    - Tính chất theo mô hình *INVEST*:
        - Independent (độc lập)
        - Negotiable (có thể thương lượng)
        - Valuable (có giá trị)
        - Estimable (có thể ước lượng)
        - Small (nhỏ)
        - estable (có thể kiểm thử)
- *Product Backlog* là một danh sách các hạng mục được sắp xếp theo độ ưu tiên, và khi những hạng mục đó được hoàn thành thì dự án hoàn thành.
- *Product Goal* mô tả trạng thái tương lai của sản phẩm, đóng vai trò là mục tiêu dài hạn để lập kế hoạch của Srum Team. Product Owner là người chịu trách nhiệm tạo ra Product Goal và truyền đạt nó tới các thành viên khác trong nhóm Scrum.
- *Sprint Backlog* như tên gọi là Backlog cho Sprint, thay vì Backlog cho cả sản phẩm như Product Backlog.
- *Sprint Goal* là mục tiêu duy nhất của Sprint và được cam kết thực hiện bởi Nhóm phát triển. Sprint Goal được tạo bởi nhóm Scrum trong buổi họp Sprint Planning dựa trên các hạng mục có giá trị nhất ở Product Backlog.
- *Burn-down Chart* : Có 2 loại Burn-down Chart phổ biến là Release Burn-down Chart và Sprint Burn- down Chart, hướng tới việc theo dõi tiến độ và khả năng đạt được của Release Goal và Sprint Goal. Như tên gọi, 2 loại biểu đồ này nhằm cung cấp thông tin về khả năng nhóm Scrum có thể đạt được những mốc để release sản phẩm hay Sprint.
## Link tham khảo: https://200lab.io/blog/agile-la-gi-scrum-la-gi
