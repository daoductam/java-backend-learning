# Buổi 11: Clean code/ Jira/Confluence (Agile Scrum)
## Clean code
### Tại sao cần clean code
- 1. Code là ngôn ngữ biểu đạt yêu cầu khách hàng
    - Dù công nghệ phát triển đến đâu, code vẫn là phương tiện duy nhất để truyền đạt yêu cầu một cách chính xác và rõ ràng đến máy tính.
    - Nếu không viết code sạch, việc hiểu và duy trì yêu cầu phần mềm sẽ trở nên cực kỳ khó khăn.
- 2. Code “rởm” là nguyên nhân giết chết dự án
    - Ví dụ từ một công ty có sản phẩm rất tốt, nhưng do code lộn xộn, không kiểm soát được, dẫn đến chậm cập nhật, nhiều lỗi không sửa được → mất khách → phá sản.
    - Khi code xấu, mỗi lần thay đổi lại sinh ra lỗi mới → chi phí sửa lỗi cao, đội ngũ kiệt sức.
- 3. Code xấu làm chậm tiến độ, không phải code sạch
    - Lập trình viên thường hiểu sai rằng "dọn code sẽ làm chậm tiến độ". Nhưng thực tế ngược lại: chính code bẩn mới khiến bạn chậm deadline, không thể mở rộng.
- 4. Code sạch dễ đọc, dễ bảo trì, dễ phát triển
    - 80-90% thời gian của lập trình viên là đọc code, không phải viết.
    - Nếu code dễ đọc → dễ hiểu → dễ sửa → dễ phát triển thêm.
- 5. Code sạch giúp dự án không bị sụp đổ sau vài năm
    - Code xấu tích tụ theo thời gian tạo thành “nợ kỹ thuật”.
    - Đến một lúc, đội không thể phát triển tiếp được nữa → phải đập đi xây lại.
    - Nhưng dự án "build lại" thường kéo dài hàng năm trời và... lại rơi vào vết xe đổ nếu không giữ code sạch từ đầu.
- 6. Code sạch là trách nhiệm chuyên nghiệp của lập trình viên
    - Viết code sạch không phải vì "sếp yêu cầu", mà vì đó là đạo đức nghề nghiệp, như bác sĩ phải rửa tay trước phẫu thuật.
    - Không phải "sếp ngu", mà là lập trình viên thiếu bản lĩnh bảo vệ chất lượng code.
-  7. Code sạch thể hiện tâm huyết và đẳng cấp của người viết
    - Code sạch là nghệ thuật.
    - Người có “giác quan code” sẽ nhận ra cái đẹp và cái sai trong từng dòng code.
    - Nó không chỉ kỹ thuật mà còn là thẩm mỹ, triết lý và sự chuyên nghiệp.
- 8. Clean code là hành trình, là “môn phái” để tu luyện
    - Không có định nghĩa duy nhất về code sạch, nhưng có rất nhiều “trường phái”
    - Mỗi người cần rèn luyện, học hỏi, trau dồi kỹ thuật và giác quan để dần đạt được sự thanh lịch, hiệu quả và dễ bảo trì trong từng dòng code.

-> Viết clean code không chỉ để dễ đọc hay dễ bảo trì. Nó là cách duy nhất để đảm bảo dự án sống lâu, ổn định, phát triển nhanh, tiết kiệm chi phí, và giúp lập trình viên làm việc trong sự thoải mái, chuyên nghiệp, và tự hào.
Clean code không phải xa xỉ. Clean code là sống còn.
### Nguyên tắc 
#### 1. Dùng những tên thể hiện được mục đích
- Ví dụ xấu: int d; // elapsed time in days
-> Không rõ d là gì, liên quan gì đến thời gian.
- Ví dụ tốt: int elapsedTimeInDays; int fileAgeInDays;
-> Rõ ràng, có đơn vị, có mục đích.
- Vấn đề khi tên không rõ ràng:
    public List<int[]> getThem() {
        List<int[]> list1 = new ArrayList<int[]>();
        for (int[] x : theList)
            if (x[0] == 4)
                list1.add(x);
        return list1;
    }
    - Không hiểu được:
        - theList chứa gì?
        - x[0] là gì?
        - Số 4 là gì?
        - list1 dùng để làm gì?
- Cải thiện bằng tên rõ nghĩa:
    public List<int[]> getFlaggedCells() {
        List<int[]> flaggedCells = new ArrayList<int[]>();
        for (int[] cell : gameBoard)
            if (cell[STATUS_VALUE] == FLAGGED)
                flaggedCells.add(cell);
        return flaggedCells;
    }
    - Code rõ ràng hơn nhờ: Tên biến gợi nghĩa (cell, gameBoard), Hằng số có tên (STATUS_VALUE, FLAGGED)
- Tốt hơn nữa: Dùng class thay vì mảng số:
    public List<Cell> getFlaggedCells() {
        List<Cell> flaggedCells = new ArrayList<Cell>();
        for (Cell cell : gameBoard)
            if (cell.isFlagged())
                flaggedCells.add(cell);
        return flaggedCells;
    }
    - Tránh “magic number”
    - Tên hàm isFlagged() thể hiện rõ hành vi
    - Code rõ ràng, dễ hiểu, dễ bảo trì 

#### 2. Tránh sai lệch thông tin
- Tên biến không phản ánh đúng bản chất sẽ gây hiểu nhầm
    - Ví dụ: accountList gợi ý đây là một List, nhưng nếu đó là một Set, Map, hay thậm chí chỉ là một mảng (array), tên gọi này sẽ đánh lừa người đọc.
    -  Giải pháp: Đặt tên đúng bản chất. Thay vì accountList, hãy dùng accountGroup, accounts, hoặc accountMap nếu đó là một Map.
- Tránh dùng từ có nghĩa khác trong ngữ cảnh lập trình
    - Ví dụ: hp, aix, sco là tên hệ điều hành Unix. Nếu dùng cho mục đích khác (ví dụ: viết tắt của hypotenuse), sẽ gây hiểu nhầm hoặc mâu thuẫn với kiến thức lập trình viên có sẵn.
    - Giải pháp: Chọn tên rõ ràng, dễ hiểu, không bị chồng lấn nghĩa
- Tránh các tên quá giống nhau
    - Ví dụ: XYZControllerForEfficientHandlingOfStrings và XYZControllerForEfficientStorageOfStrings khác nhau một chữ, nhưng dễ gây nhầm lẫn khi làm việc trong một module lớn.
    - Giải pháp: Đặt tên súc tích, có điểm nhấn rõ ràng, tránh dài dòng hoặc khác nhau ở vài ký tự mờ nhạt.
- Tránh ký tự dễ gây nhầm lẫn
    - Ví dụ: chữ l (L thường) và O (O hoa) rất dễ bị đọc nhầm thành số 1 và 0.
    - Giải pháp: 
        - Tránh dùng tên biến chỉ gồm 1 chữ như l, O.
        - Dùng phông chữ dễ phân biệt (với IDE như JetBrains, VSCode hỗ trợ tốt).
        - Đặt tên đầy đủ, ví dụ orderId, loginFlag.

#### 3. Tạo nên những sự khác biệt có nghĩa
- Tên khác nhau nhưng không khác nghĩa
    - Ví dụ: Product, ProductInfo, ProductData
    -> Những cái tên này gần như không phân biệt được về chức năng hoặc ý định.
- Đặt tên vô nghĩa để "lách" trình biên dịch
    - Dùng klass thay vì class, hoặc theZork vì Zork đã tồn tại.
    - Trình biên dịch hiểu, nhưng con người thì không → nên tránh.
- Tên chuỗi số vô nghĩa
    - public static void copyChars(char a1[], char a2[]) { ... }
    - a1, a2 không nói lên mục đích gì.
    - Tốt hơn:  copyChars(char source[], char destination[]) { ... }
- Từ gây nhiễu: 
    - Info, Data, Object, Variable, String, Table, The, A... chỉ làm tên dài hơn nhưng không giúp hiểu rõ hơn.
    - Ví dụ: Customer vs CustomerInfo — khác nhau gì? Không rõ!
|  Tên sai        |  Tên đúng hơn                        |
| --------------- | ------------------------------------- |
| `moneyAmount`   | `totalPrice`, `balance`               |
| `customerInfo`  | `customerDetails`, `customerProfile`  |
| `accountData`   | `accountCredentials`, `accountStatus` |
| `NameString`    | `name`                                |
| `CustomerObject`| `customerEntity`, `customerRecord`    |
| `theMessage`    | `errorMessage`, `notificationMessage` |

#### 4. Dùng những tên phát âm được 
- Ví dụ minh họa:
    - private Date genymdhms; rất khó hiểu và khó phát âm.
    - private Date generationTimestamp; rõ ràng, dễ hiểu, dễ đọc.
-> Hãy đặt tên biến, tên class bằng từ ngữ dễ phát âm, tránh viết tắt vô nghĩa. Điều đó giúp tăng khả năng giao tiếp và hiểu code dễ hơn.
#### 5. Dùng những tên tìm kiếm được 
- Tên biến ngắn, một chữ cái (như e, j) hoặc số "trơ" (như 7, 5) gây khó khăn khi tìm kiếm.
    - Ví dụ: rất khó xác định 7 có ý nghĩa gì trong hàng ngàn dòng code.
    - Tên như MAX_CLASSES_PER_STUDENT dễ tìm và dễ hiểu hơn nhiều.
- Rủi ro khi dùng hằng số trực tiếp (magic number): Nếu ai đó thay đổi giá trị (như đổi 7 thành 6), lỗi có thể khó phát hiện và truy vết.
- Tên biến ngắn chỉ nên dùng cho biến cục bộ trong phương thức ngắn.
    - Độ dài tên nên tỉ lệ với phạm vi sử dụng: càng dùng ở nhiều nơi, càng cần tên rõ ràng.
- Ưu tiên dùng tên dài, rõ nghĩa cho hằng số và biến quan trọng.
    - Ví dụ tốt:
        int realDaysPerIdealDay = 4;
        const int WORK_DAYS_PER_WEEK = 5;
#### 6. Tránh việc mã hóa tên biến
- Không cần tiền tố như m_, I, C
    - Ví dụ: m_dsc nên được đổi thành description
    - Tiền tố như IShapeFactory gây nhiễu, ShapeFactory là đủ rõ.
#### 6. Biến, hàm, lớp cần đúng loại từ
| Khái niệm           | Sử dụng từ gợi ý                                                     |
| ------------------- | -------------------------------------------------------------------- |
| **Lớp**             | Danh từ: `Customer`, `WikiPage`, `AddressParser`                     |
| **Phương thức**     | Động từ: `postPayment()`, `deletePage()`, `save()`                   |
| **Biến cục bộ nhỏ** | Tên ngắn đủ nghĩa: `sum`, `count`, `i` (cho vòng lặp rất nhỏ)        |
| **Hằng hệ thống**   | Tên rõ ràng, in hoa: `MAX_CLASSES_PER_STUDENT`, `WORK_DAYS_PER_WEEK` |
#### 7. Chọn một từ cho một khái niệm và nhất quán
- Tránh dùng fetch, get, retrieve cho cùng một hành động; chọn 1 và dùng xuyên suốt.
- Tương tự với các danh từ như Manager, Controller, Driver — cần nhất quán:
    - Đừng gọi DeviceManager và ProtocolController nếu cả hai làm nhiệm vụ tương tự → gây hoang mang về vai trò thực sự.
- Tên hàm hoặc class nên đủ rõ ràng, nhất quán, và đứng một mình cũng phải có nghĩa.
- Không chơi chữ: nếu là thêm phần tử, dùng add; nếu chèn, dùng insert hoặc append để phân biệt.
#### 8. Thêm ngữ cảnh phù hợp
- Tên biến nên chứa bối cảnh: addrCity, addrFirstName nếu chưa tách lớp Address.
- Tách cấu trúc để code rõ ràng: ví dụ hình thành lớp GuessStatisticsMessage để nhóm các biến liên quan.
#### 9. Không “hiếp dâm não” người đọc
- Tránh tên hacker, viết tắt không phổ thông.
- Chọn tên rõ nghĩa để code dễ đọc, dễ hiểu ngay cả với lập trình viên khác.
#### 10. Dùng thuật ngữ kỹ thuật/Pattern khi nên dùng
- Nếu code liên quan thuật toán hay Pattern (như VISITOR, QUEUE, FACTORY…), nên dùng tên tương ứng như AccountVisitor, JobQueue.
- Giúp người hiểu nhanh ngay lập tức nếu đã quen kỹ thuật đó.

## JIRA (task, backlog, sprint, epic, story, task, bug, sprint)
- Issue (tổng quát) là đơn vị công việc cơ bản trong Jira, bao gồm epic, story, task, bug, v.v.
- Epic là một tính năng lớn hoặc một mục tiêu dài hạn cần nhiều story để hoàn thành.
VD: “Thanh toán đơn hàng”, “Quản lý người dùng”.
- Story (User Story) là một yêu cầu chức năng cụ thể từ góc nhìn người dùng.
    - Cấu trúc: “Là một [role], tôi muốn [mục tiêu] để [lợi ích]”.
    - VD: “Là khách hàng, tôi muốn xem lịch sử đơn hàng để dễ tra cứu”.
- Task là một công việc kỹ thuật cụ thể, không nhất thiết từ góc nhìn người dùng.
    - VD: “Tạo bảng orders trong cơ sở dữ liệu”, “Triển khai API GET /orders”.
- Bug là một lỗi trong hệ thống cần được sửa.
    - VD: “Không thể đăng nhập bằng tài khoản Google”, “API trả sai định dạng ngày”.
- Backlog là danh sách các issue (epic, story, task, bug) chưa được làm. Sẽ được lên kế hoạch và kéo vào sprint sau khi được ưu tiên.
- Sprint là chu kỳ làm việc ngắn hạn (1–4 tuần) để hoàn thành một phần công việc trong backlog. Có mục tiêu rõ ràng, các task/story được giao và đánh giá sau mỗi sprint.

## nghiệp vụ, confluence (spec, SRS, BRD)
### BRD – Business Requirements Document (Tài liệu yêu cầu nghiệp vụ)
- Mục tiêu: Ghi lại các nhu cầu của doanh nghiệp, từ góc độ phi kỹ thuật.
- Đối tượng: Product Owner, BA, Stakeholder, khách hàng.
- Nội dung thường gồm:
    - Mục tiêu dự án
    - Các yêu cầu nghiệp vụ chính
    - Các quy trình nghiệp vụ hiện tại và mong muốn cải thiện

### SRS – Software Requirements Specification (Tài liệu đặc tả yêu cầu phần mềm)
- Mục tiêu: Chuyển BRD thành yêu cầu kỹ thuật chi tiết cho team dev.
- Đối tượng: BA, Developer, Tester.
- Nội dung:
    - Chức năng hệ thống (Functional Requirements)
    - Phi chức năng (Non-functional: bảo mật, hiệu năng…)
    - Use case, user flow, sơ đồ ERD/API

### Spec – Specification - Thông số kỹ thuật chi tiết (thường ở cấp độ tính năng hoặc màn hình)
- Là phần nhỏ trong SRS, thường dùng để mô tả 1 tính năng/màn hình/API cụ thể.
- Có thể là:
    - UI Spec: mô tả thiết kế giao diện, layout, button, input
    - API Spec: định nghĩa endpoint, method, request/response format
    - Logic Spec: mô tả các điều kiện, business rule, trạng thái…
- Ví dụ:
    API POST /orders
    Request: { userId, items[] }
    Rule: nếu tổng > 1 triệu, tự động áp dụng giảm giá 5%

### Agile (scrum, daily standup, sprint planning, review, retrospective)
- Scrum: Framework quản lý phát triển phần mềm theo phương pháp Agile. Gồm các vai trò (Scrum Master, Product Owner, Development Team), các sự kiện và các artefact.
- Sprint: Chu kỳ làm việc ngắn (thường 1–4 tuần), trong đó nhóm phát triển hoàn thành một số lượng công việc cụ thể (Product Backlog Items).
- Sprint Planning: Cuộc họp đầu mỗi Sprint. Nhóm xác định mục tiêu Sprint (Sprint Goal) và chọn các task từ Product Backlog để thực hiện.
- Daily Standup (Daily Scrum): Cuộc họp ngắn (~15 phút) hàng ngày. Mỗi thành viên trả lời 3 câu:    
    - Hôm qua đã làm gì?
    - Hôm nay định làm gì?
    - Có gì cản trở không?
- Sprint Review: Diễn ra cuối Sprint. Nhóm demo sản phẩm đã hoàn thành cho stakeholders để lấy feedback. Cập nhật Product Backlog nếu cần.
- Sprint Retrospective: Diễn ra sau Sprint Review. Nhóm nhìn lại quá trình làm việc để:
    - Điều gì làm tốt?
    - Điều gì cần cải thiện?
    - Kế hoạch cải thiện cho Sprint tiếp theo.














