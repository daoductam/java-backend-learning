# Buổi 1: Cơ bản về git
## 1. Mục tiêu
- Nắm được git flow
- Các thao tác cơ bản với git
- Cách tạo và điền pull request
- Áp dụng đúng cách đặt tên commit
- Xử lý khi có conflict code 
## 2. Lý thuyết
### 2.1 Một số thuật ngữ
- Nhánh(Branch): phiên bản của một kho lưu trữ nào đó khác với nơi làm việc chính
- Checkout: hành động chuyển đổi giữa các phiên bản khác nhau
- Cherry-Picking: áp dụng một số commit từ một nhánh này sang nhánh khác.
- Nhân bản(Clone): được sử dụng để tạo một bản sao của kho lưu trữ bạn cần hoặc sao chép nó.
- Tìm và lấy hay tìm nạp (Fetch): tìm lấy các nhánh và thẻ từ một hoặc nhiều kho lưu trữ khác, cập nhật các nhánh theo dõi từ xa.
- HEAD: đại diện của commit cuối cùng trong nhánh checkout hiện tại.
- Index: sử dụng làm chỉ mục để thiết lập một tập hợp các thay đổi mà bạn muốn thực hiện cùng nhau.
- Main: nhánh mặc định của Git.
- Hợp nhất(merge): đưa một nhánh tách rời trở lại với nhau.
- Gốc(Origin): một tham chiếu đến kho lưu trữ từ xa của một dự án ban đầu được nhân bản.
- Pull Request: quá trình để developer thông báo cho các thành viên trong nhóm rằng họ đã hoàn thành một tính năng.
- Đẩy(Push): tải nội dung kho lưu trữ cục bộ lên một kho lưu trữ từ xa.
- Rebase: quá trình di chuyển hoặc kết hợp một chuỗi các commit thành một commit cơ sở mới.
- Remote: có liên quan đến kho lưu trữ từ xa.
- Kho(Repository):  lưu trữ siêu dữ liệu cho một tập hợp các file và thư mục.
- Stashing: Lệnh git stash cho phép bạn chuyển đổi nhánh mà không cần commit nhánh hiện tại.
- Thẻ(Tag): đánh dấu một giai đoạn commit là quan trọng.
- Hoàn lại Git(Git Revert): git revert được sử dụng để hoàn nguyên một số commit
- Đặt lại Git(Git Reset): Lệnh git reset được sử dụng để đặt lại các thay đổi so với ban đầu.
- Bỏ qua Git(Git Ignore): chỉ định các file không được theo dõi mà Git nên bỏ qua.
- Git Diff: hiển thị các thay đổi giữa các commit, commit và cây làm việc, v.v.
- Git Cheat Sheet:  một bản tóm tắt các tài liệu tham khảo nhanh về Git.
- Git Flow: một mô hình phân nhánh cho Git
- Git Squash: dồn các commit trước đó thành một
- Git Rm: loại bỏ các file riêng lẻ hoặc một bộ sưu tập các file.\
- Git Fork: Tạo kho lưu trữ cho phép bạn tự do kiểm tra và gỡ lỗi với các thay đổi mà không ảnh hưởng đến dự án ban đầu.
### 2.2 Các lênh cơ bản
- Lệnh cấu hình Git
    - Cú pháp
        - git config --global user.name "tam"      
        - git config --global user.email "tamdao@gmail.com" 
- Lệnh Git Init
- Lệnh Git clone: git clone URL
- Lệnh Git add
    - Cú pháp:
        - Để thêm một file: git add tên file  
        - Để thêm nhiều hơn một file: git add *  
- Lệnh Git commit:
    - Cú pháp: git commit -m " Commit Message"  
- Lệnh Git Status:  git status 
- Lệnh Git Push: git push origin main
- Lệnh Git Pull: git pull URL
- Lệnh Git branch: git  branch - liệt kê
- Lệnh Git Merge: git merge BranchName
- Lệnh Git log: git  log
- Lệnh Git remote

### 2.3 Cơ bản về git flow
- Mô hình phân nhánh:
    - main
    - develop
    - hotfixes
    - release
    - feature
- Các nhánh chính: main và develop
- Các nhánh hỗ trợ: hotfixes, release, feature
- Các bước: 
    - Khởi tạo:
        - git checkout main
        - git checkout -b develop
        - git push -u origin develop
    - Tạo nhánh feature:
        - git checkout develop
        - git checkout -b feature/<ten>
        - # làm việc...
        - git checkout develop
        - git merge feature/<ten>
        - git branch -d feature/<ten>
    - Tạo nhánh release
        - git checkout develop
        - git checkout -b release/1.0.0
        - # fix bug, viết tài liệu...
        - git checkout main
        - git merge release/1.0.0
        - git tag 1.0.0
        - git checkout develop
        - git merge release/1.0.0
        - git branch -d release/1.0.0
    - Tạo nhánh hotfix
        - git checkout main
        - git checkout -b hotfix/1.0.1
        - # sửa bug...
        - git commit -am "fix bug"
        - git checkout main
        - git merge hotfix/1.0.1
        - git tag 1.0.1
        - git checkout develop
        - git merge hotfix/1.0.1
        - git branch -d hotfix/1.0.1
### 2.4 Quy ước
- Cấu trúc commit:
```text
<type>[optional scope]: <description>

[optional body]

[optional footer(s)]
```
- Định dạng `type`
    * `fix`: Sửa bug 
    * `feat`: Tính năng mới 
    * `BREAKING CHANGE`: Phá vỡ tương thích. Dùng trong footer hoặc kèm `!` sau `type`/`scope`.
Các `type` khác 
    * `chore`, `build`, `docs`, `style`, `refactor`, `perf`, `test`, `ci`...

- `scope` (không bắt buộc)
    * Xác định phạm vi ảnh hưởng. VD: `feat(parser): add array parser`

- Quy Định Cụ Thể
    * Commit **PHẢI** có `type`, theo sau là scope tùy chọn, `!` và dấu `:` + dấu cách.
    * Mô tả ngắn gọn **PHẢI** đi ngay sau dấu `:`.
    * Thân commit (body) **CÓ THỂ** thêm sau một dòng trống.
    * **Footer**: theo sau thân commit (nếu có), có định dạng `Token: Value` hoặc `Token #issueId`.
    * Token trong footer dùng `-` thay cho khoảng trắng: `Reviewed-by`, `BREAKING CHANGE:`...
