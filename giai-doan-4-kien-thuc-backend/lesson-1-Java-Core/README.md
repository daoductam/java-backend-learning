# Buổi 1: Java Core
## Mục tiêu
Hiểu các khái niệm: (Còn trong quá trình thực hiện code sẽ hiểu thêm dần)
- OOP
- Exception
- Collections (List, Set, Map)
- Stream API
- Generic
- Unit Testing
- Build tool (maven/gradle)
- Date Time
- Java IO
Nâng cao:
- Garbage Collection (Học sau)
- Multithread & Concurrency (Học sau)
## Lý thuyết
### OOP
#### Khái niệm
- Lập trình hướng đối tượng (Object-Oriented Programming – OOP) là một phương pháp lập trình sử dụng mô hình lập trình dựa trên các đối tượng có khả năng tương tác với nhau.
- Đối tượng: Thuộc tính + Phương thức
- Lớp là một khuôn mẫu hoặc một mô tả trừu tượng của các đối tượng
Ví dụ: Lớp "Xe" có các thuộc tính màu sắc, thương hiệu và model của xe. Một đối tượng cụ thể của lớp "Xe hơi" có thể là chiếc xe cụ thể
#### Tính chất 
- 1. Tính đóng gói (Encapsulation): giúp bảo vệ dữ liệu và tránh truy cập trực tiếp đến các thuộc tính của đối tượng từ bên ngoài.
    - Ví dụ:
    public class Person {
    private String name;  // Thuộc tính name là private
    private int age;      // Thuộc tính age là private
    // Các phương thức public String getName()... public void setAge(int age)...
    }
    Trong ví dụ này, thuộc tính name và age được khai báo là private, nghĩa là chúng không thể truy cập trực tiếp từ bên ngoài lớp Person. Để lấy hoặc thay đổi giá trị của chúng, chúng ta sử dụng các phương thức công khai getName, getAge, và setAge
- 2. Kế thừa (Inheritance): giúp tái sử dụng mã nguồn và tạo ra cấu trúc phân cấp cho các lớp.
    - Ví dụ: 
    // Lớp cha "Động Vật"
    class Animal {
        void eat() {
            System.out.println("Động vật đang ăn...");
        }
    }
    // Lớp con "Chó" kế thừa từ lớp "Động Vật"
    class Dog extends Animal {
        void bark() {
            System.out.println("Chó sủa...");
        }
    }
    Trong ví dụ này, lớp "Chó" có mọi thông tin và hành vi của lớp "Động Vật," và nó cũng có thể có các phương thức và thuộc tính riêng của nó.

- 3. Tính đa hình (Polymorphism): cho phép thực hiện cùng một hành động trên các đối tượng khác nhau mà không cần quan tâm đến loại đối tượng cụ thể.
    - Ví dụ: // Lớp cha "Hình"
    class Shape {
        void draw() {
            System.out.println("Vẽ hình...");
        }
    }

    // Lớp con "Hình Tròn"
    class Circle extends Shape {
        void draw() {
            System.out.println("Vẽ hình tròn...");
        }
    }

    // Lớp con "Hình Vuông"
    class Square extends Shape {
        void draw() {
            System.out.println("Vẽ hình vuông...");
        }
    }
    Trong ví dụ này, lớp cha "Hình" có một phương thức draw mà các lớp con "Hình Tròn" và "Hình Vuông" ghi đè (override). Điều này có nghĩa là mỗi lớp con có cùng tên phương thức draw, nhưng chúng thực hiện hành động riêng biệt.
- 4. Tính trừu tượng (Abstraction): cho phép bạn tạo ra các lớp và đối tượng trừu tượng, tập trung vào các tính năng quan trọng mà bạn quan tâm và che giấu chi tiết phức tạp bên trong.
    - Ví dụ: // Lớp trừu tượng "Động Vật"
    abstract class Animal {
        abstract void sound(); // Phương thức trừu tượng
    }

    // Lớp con "Chó" kế thừa từ "Động Vật"
    class Dog extends Animal {
        void sound() {
            System.out.println("Gâu gâu!");
        }
    }
    Trong ví dụ này, lớp cha "Động Vật" là một lớp trừu tượng, nghĩa là nó chứa ít nhất một phương thức trừu tượng (sound). Các lớp con "Chó" kế thừa từ lớp cha và phải triển khai phương thức sound dựa trên bản chất riêng của mỗi loài.
#### 1 số thuật ngữ
- Khai báo thuộc tính: [Access Level] [Static] [Type]  [<Tên thuộc tính>]
    - Access Level có thể là một trong các từ public, private, protected hoặc có thể bỏ trống (protected), ý nghĩa của các bổ từ này được mô tả ở phần trên
    - Static là từ khoá báo rằng đây là một thuộc tính lớp, nó là một thuộc tính sử dụng chung cho cả lớp, nó không là của riêng một đối tượng nào.
    - Type là một kiểu dữ liệu nào đó
    - <Tên thuộc tính> là tên của thuộc tính
- Phương thức
    - static: phương thức tĩnh có thể gọi nó trực tiếp từ lớp mà không cần tạo đối tượng của lớp đó
    - abstract: không có cài đặt trong lớp hiện tại và lớp chứa nó phải được đánh dấu là abstract.
    - final: không thể được ghi đè (override) trong các lớp con.
    - native: viết bằng một ngôn ngữ khác và không có cài đặt trong Java.
    - synchronized: là một phương thức đồng bộ (synchronized), nghĩa là chỉ một luồng có thể thực thi phương thức này tại một thời điểm.
- *Cập nhật sau.....*

### Xử lý ngoại lệ (Exception)
#### Khái niệm
- Là một tình trạng bất thường xảy ra trong quá trình thực thi chương trình, phá vỡ luồng chuẩn của chương trình.
- Phân loại: 
    - Checked Exception: IOException, SQLException...
    - Unchecked Exception: NullPointerException, ArithmeticException....
    - Error: OutOfMemoryError, StackOverflowError....
#### Một số thuật ngữ
- 1. Khối *try-catch*: được sử dụng để xử lý các ngoại lệ (exceptions) trong chương trình. 
    - Khi nào dùng ?
        - Lỗi logic: không thể kiểm soát toàn bộ dữ liệu đầu vào và có thể xảy ra lỗi logic
        - Chia cho 0:Khi có phép chia cho 0, Java sẽ ném ra một ngoại lệ ArithmeticException.
        - Kết nối cơ sở dữ liệu: Trong quá trình làm việc với cơ sở dữ liệu, có thể xảy ra các lỗi kết nối.
    - Ví dụ: try {
            int result = 50 / 0; // Sẽ ném ra ArithmeticException
        } catch (ArithmeticException e) {
            System.out.println("Lỗi chia cho 0: " + e.getMessage());
        }
        System.out.println("Chương trình tiếp tục chạy...");
- 2. Khối lệnh *finally* trong java luôn được thực thi cho dù có ngoại lệ xảy ra hay không hoặc gặp lệnh return trong khối try.
- 3. Một số lớp ngoại lệ chuẩn
    - Throwable: lớp cha của mọi lớp ngoại lệ trong Java.
    - Exception: lớp con trực tiếp của lớp Throwable.
    - RuntimeException: Lớp cơ sở cho nhiều ngoại lệ trong java.lang
    - ArithmeticException: Xảy ra khi có lỗi về số học, ví dụ như chia cho 0.
    - IllegalAccessException: Xảy ra khi không thể truy cập một lớp.
    - IllegalArgumentException: Xảy ra khi đối số không hợp lệ.
    - ArrayIndexOutOfBoundsException: Xảy ra khi truy cập ra ngoài mảng.
    - NullPointerException: Xảy ra khi truy cập một đối tượng null.
    - SecurityException: Xảy ra khi cơ chế bảo mật không cho phép thực hiện một thao tác.
    - ClassNotFoundException: Xảy ra khi không thể nạp lớp yêu cầu.
    - NumberFormatException: Xảy ra khi chuyển đổi từ chuỗi sang số không thành công.
    - AWTException: Đây là một lớp ngoại lệ liên quan đến AWT (Abstract Window Toolkit).
    - IOException: Là lớp cha của các lớp ngoại lệ liên quan đến I/O (Input/Output).
    - FileNotFoundException: Xảy ra khi không thể định vị tập tin.
    - EOFException: Xảy ra khi kết thúc một tập tin.
    - NoSuchMethodException: Xảy ra khi phương thức yêu cầu không tồn tại.
    - InterruptedException: Xảy ra khi một luồng bị ngắt.
- 4. Từ khóa throw trong Java được sử dụng để ném ra một exception
    - Ví dụ: if (age < 18) throw new ArithmeticException("not valid"); -> Exception in thread "main" java.lang.ArithmeticException: not valid
- 5. Từ khóa throws trong Java được sử dụng để khai báo một ngoại lệ.
- 6. Tự tạo Exception: Định rõ tình huống đặc biệt muốn xử lý, ùy chỉnh cách xử lý ngoại lệ để phù hợp với logic ứng dụng.
    - Dùng khi phát triển một ứng dụng Java phức tạp, có thể muốn xử lý các tình huống đặc biệt mà các lớp ngoại lệ có sẵn trong Java không đáp ứng. 
    - Ví dụ: class InvalidAgeException extends Exception {
            InvalidAgeException(String s) {
                super(s);
            }
        }

### Collections
#### Khái niệm
- Collections là một khung (framework) cung cấp kiến trúc để lưu trữ và thao tác trên các tập hợp đối tượng. 
- Java Collections cho phép bạn làm việc với các dữ liệu phức tạp và cung cấp các giao diện và lớp cài đặt cho một loạt các cấu trúc dữ liệu, chẳng hạn như danh sách (List), tập hợp (Set), hàng đợi (Queue), và ánh xạ (Map).
- Một số thuật toán: 
    - binarySearch(List list, Object key): Tìm kiếm một phần tử trong danh sách đã sắp xếp bằng thuật toán tìm kiếm nhị phân.
    - fill(List list, Object obj): Thay thế tất cả các phần tử trong danh sách bằng một đối tượng cụ thể.
    - shuffle(List list): Hoán vị các phần tử trong danh sách một cách ngẫu nhiên.
    - sort(List list): Sắp xếp các phần tử trong danh sách theo thứ tự tăng dần.
#### List
- Khái niệm: dạng tập hợp các phần tử được sắp theo thứ tự (còn được gọi là dãy tuần tự) và trong đó cho phép lặp (hai phần tử giống nhau).
- Vì List là một interface, nên chúng ta không thể tạo các đối tượng từ nó. Để sử dụng các tính năng của List interface, chúng ta có thể sử dụng các class sau:
    - ArrayList
    - LinkedList
    - Vector
    - Stack
- Các phương thức: 
    - Object get(int index):	Cho lại phần tử được xác định bởi index.
    - Object set(int index, Object elem):	Thay thế phần tử được xác định bởi index bằng elem
    - void add(int index, Object elem):	Chèn elem vào sau phần tử được xác định bởi index.
    - Object remove(int index):	Bỏ đi phần tử được xác định bởi index
    - boolean addAll(int index, Collection c):	Chèn các phần tử của tập hợp c vào vị trí được xác định bởi index
    - int indexOf(Object elem):	Cho biết vị trí lần xuất hiện đầu tiên của phần tử trong danh sách
    - int lastIndexOf(Object elem):	Cho biết vị trí lần xuất hiện cuối cùng của elem trong danh sách.
    - List subList(int fromIndex, int toIndex):	Lấy ra một danh sách con từ vị trí fromIndex đến toIndex
    - ListIterator listIterator();	Cho lại các phần tử liên tiếp bắt đầu từ phần tử đầu tiên.
    - ListIterator listIterator(int index)	Cho lại các phần tử liên tiếp bắt đầu từ phần tử được xác định bởi. Trong đó ListIterator là interface mở rộng giao diện Iterator đã có trong java.lang.
- Ví du: List<String> list = new ArrayList<String>();
        list.add("Java");
        list.add("C++");
        list.add("PHP");
        list.add(1, "Python");
- List Iterator: là một interface được sử dụng để duyệt các phần tử của List trong java.
    - Các phương thức: 
        - boolean hasNext()	Phương thức này trả về true nếu list interface có tồn tại phần tử kế tiếp phần tử hiện tại.
        - Object next()	Phương thức này trả về phần tử kế tiếp trong danh sách và vị trí con trỏ tăng lên 1.
        - boolean hasPrevious()	Phương pháp này trả về true nếu list interface có tồn tại phần tử kế sau phần tử hiện tại.
        - Object previous()	Phương thức này trả về phần tử kế sau trong danh sách và vị trí con trỏ giảm đi 1
    - Ví dụ: ListIterator<String> itr = list.listIterator();
        System.out.println("Duyet cac phan tu tu dau den cuoi:");
        while (itr.hasNext()) {
            System.out.println("\t" + itr.next());
        }
        System.out.println("Duyet cac phan tu tu cuoi ve dau:");
        while (itr.hasPrevious()) {
            System.out.println("\t" + itr.previous());
        }
#### ArrayList
- Khi nào dùng ? : mảng cần khai báo độ dài -> khó thay đổi size -> Sử dụng ArrayList
- Khởi tạo: ArrayList<Type> arrayList= new ArrayList<>();
- Các phương thức
    - add(): Thêm một phần tử Ví dụ:  animals.add("Dog");
    - addAll(): thêm tất cả các phần tử của ArrayList này vào một ArrayList mới Ví dụ: animals.addAll(mammals);
    - get(): truy cập ngẫu nhiên các phần tử Ví dụ: String str = animals.get(0);
        - Để truy cập các phần tử của ArrayList một cách tuần tự, chúng ta sử dụng hàm iterator() 
        Ví dụ: Iterator<String> iterate = animals.iterator();
            System.out.print("ArrayList: ");
            // Sử dụng method của Iterator để truy cập vào phần tử
            while(iterate.hasNext()){ // Hàm hasNext() trả về true nếu có một phần tử tiếp theo trong ArrayList.
                System.out.print(iterate.next()); // Hàm next() trả về phần tử tiếp theo trong ArrayList.
                System.out.print(", "); }
    - set(): Thay đổi phần tử Ví dụ: animals.set(2, "Zebra");
    - remove(): Xóa phần tử Ví dụ: String str = animals.remove(2);
    - removeAll(): loại bỏ tất cả Ví dụ: animals.removeAll(animals); // Hoặc clear() ví du: animals.clear();
    - Lặp qua các phần tử: Dùng for thông thường
    - size(): Kiểm tra độ dài Ví dụ: System.out.println("Size: " + animals.size());
    - sort(): Sắp xếp các phần tử Ví dụ: Collections.sort(animals);
    - toArray(): chuyển một ArrayList thành một mảng Array Ví du: animals.toArray(arr);
    - asList(): Chuyển mảng thành ArrayList Ví dụ: ArrayList<String> animals = new ArrayList<>(Arrays.asList(arr));
    - toString(): Chuyển ArrayList thành String Ví dụ: String str = animals.toString();
    - clone(): Tạo một ArrayList mới với cùng phần tử, kích thước và dung lượng.
    - contains(): Tìm kiếm ArrayList cho phần tử đã chỉ định và trả về kết quả boolean.
    - ensureCapacity(): Chỉ định tổng phần tử mà ArrayList có thể chứa.
    - isEmpty(): Kiểm tra nếu ArrayList trống.
    - indexOf(): Tìm kiếm một phần tử được chỉ định trong ArrayList và trả về chỉ số của phần tử.
    - trimToSize(): Giảm dung lượng của một ArrayList về kích thước hiện tại của nó.

#### LinkedList
- Khái niệm: Các phần tử trong LinkedList không được lưu trữ liền kề nhau giống như arrays. Mỗi phần tử trong LinkedList liên kết với nhau bằng một con trỏ, nghĩa là mỗi phần tử sẽ tham chiếu đến địa chỉ của phần tử tiếp theo.
- Khởi tạo: LinkedList<Type> linkedList = new LinkedList<>();
- Các phương thức
    - add(): Thêm một phần tử Ví dụ:  animals.add("Dog");
    - addAll(): Thêm phần tử từ LinkedList này sang LinkedList khác Ví dụ: animals.addAll(mammals);
    - get(): truy cập ngẫu nhiên các phần tử Ví dụ: String str = animals.get(0);
        - Để truy cập các phần tử của ArrayList một cách tuần tự, chúng ta sử dụng hàm iterator() 
        Ví dụ: Iterator<String> iterate = animals.iterator();
            System.out.print("ArrayList: ");
            // Sử dụng method của Iterator để truy cập vào phần tử
            while(iterate.hasNext()){ // Hàm hasNext() trả về true nếu có một phần tử tiếp theo trong ArrayList.
                System.out.print(iterate.next()); // Hàm next() trả về phần tử tiếp theo trong ArrayList.
                System.out.print(", "); }
    - contains(): Tìm kiếm các phần tử Ví dụ: if(animals.contains("Dog")) System.out.println("Dog is in LinkedList");
    - indexOf(): trả về chỉ số xuất hiện đầu tiên của một phần tử, trả về -1 nếu không tìm thấy phần tử đã chỉ định. Ví dụ:
    int index1 = animals.indexOf("Dog");
    - lastIndexOf(): trả về chỉ số của lần xuất hiện cuối cùng của một phần tử, trả về -1 nếu không tìm thấy phần tử đã chỉ định.
    - set(): Thay đổi phần tử Ví dụ: animals.set(2, "Zebra");
    - remove(): Xóa phần tử Ví dụ: String str = animals.remove(2);
    - removeAll(): loại bỏ tất cả Ví dụ: animals.removeAll(animals); // Hoặc clear() ví du: animals.clear();
    - Lặp qua các phần tử: Dùng for thông thường
    - size(): Kiểm tra độ dài Ví dụ: System.out.println("Size: " + animals.size());
    - sort(): Sắp xếp các phần tử Ví dụ: Collections.sort(animals);
    - toArray(): chuyển một ArrayList thành một mảng Array Ví du: animals.toArray(arr);
    - asList(): Chuyển mảng thành ArrayList Ví dụ: ArrayList<String> animals = new ArrayList<>(Arrays.asList(arr));
- Deque và Queue trong LinkedList: 
    - Hàm addFirst() – thêm phần tử được chỉ định vào đầu LinkedList
    - Hàm addLast() – thêm phần tử được chỉ định vào cuối LinkedList
    - Hàm getFirst() – trả về phần tử đầu tiên
    - Hàm getLast() – trả về phần tử cuối cùng
    - Hàm removeFirst() – loại bỏ phần tử đầu tiên
    - Hàm removeLast() – loại bỏ phần tử cuối cùng

    - Hàm peek() trả về phần tử đầu tiên (đầu) của LinkedList.
    - Hàm poll() trả về và loại bỏ phần tử đầu tiên trong LinkedList. 
    - Hàm offer() bổ sung thêm các phần tử cụ thể ở phần cuối của LinkedList
#### So sánh ArrayList và LinkedList
- 1. Giống nhau: đều implement giao diện List và duy trì thứ tự của các phần tử.
- 2. Khác nhau: 
    - Cơ Chế Lưu Trữ:
        - ArrayList: Sử dụng một mảng động để lưu trữ các phần tử. Điều này có nghĩa rằng nó có thể thực hiện các thao tác truy cập một cách nhanh chóng. Tuy nhiên, nếu bạn xoá một phần tử khỏi mảng, toàn bộ các phần tử sau nó phải được di chuyển trong bộ nhớ để đảm bảo thứ tự không bị thay đổi.
        - LinkedList: Sử dụng danh sách liên kết đôi để lưu trữ các phần tử. Thao tác với LinkedList nhanh hơn so với ArrayList vì khi xoá hoặc chèn một phần tử, chỉ cần điều chỉnh các liên kết giữa các nút trong danh sách liên kết, không cần di chuyển dữ liệu trong bộ nhớ
    - Sử Dụng: 
        - ArrayList: Thích hợp cho việc lưu trữ và truy cập dữ liệu khi bạn cần nhanh chóng truy cập các phần tử theo chỉ số. Ví dụ: danh sách điểm của một lớp học.
        - LinkedList: Thích hợp cho các thao tác thêm/xoá dữ liệu thường xuyên, chẳng hạn như danh sách cuộc gọi hoặc hàng đợi (queue) với việc thêm/xoá phần tử ở cả hai đầu. Nó cũng là lựa chọn tốt cho việc duyệt danh sách theo cách lùi.
    - Giao Diện: 
        - ArrayList: Chỉ hỗ trợ giao diện List.
        - LinkedList: Hỗ trợ cả giao diện List và Deque, cho phép bạn sử dụng nó như một hàng đợi (queue).
#### Set
- Khái niệm: Set Interface là một loại Interface Collection. 
    - Set không có thứ tự.
    - Set không chứa các phần tử trùng lặp.
    - Set có thể chứa một phần tử null duy nhất.
    - Set rất hữu ích vì các thao tác trên nó thường rất nhanh.
- Những lớp implementation của Set: 
    - EnumSet
    - HashSet
    - LinkedHashSet
    - TreeSet
- Phương thức của Set: 
    - Set định nghĩa các phương thức cơ bản như add, remove và clear.... để quản lý các phần tử trong tập hợp
    - Kiểm tra xem một phần tử cụ thể có tồn tại trong Set hay không bằng phương thức contains.
    - Không có cách nào để truy xuất trực tiếp một phần tử từ Set -> kiểm tra sự tồn tại của một phần tử bằng contains, hoặc duyệt qua tất cả phần tử trong Set
- HashSet 
    - Sử dụng cơ chế băm (hashing) để lưu trữ các phần tử. -> phương thức hashCode() được dùng để phân phối các đối tượng một cách đồng đều trong tập hợp.
    - Hiệu năng Time cho các phương thức cơ bản(add, remove, contains và size) là  O(1) -> do hàm băm phân tán các phần tử một cách hợp lý
- Nếu cần một tập hợp có thứ tự, có thể sử dụng LinkedHashSet hoặc TreeSet.
    - LinkedHashSet duy trì thứ tự thêm vào (insertion order) của các phần tử.
    - Còn TreeSet là một tập hợp được sắp xếp, sắp xếp theo thứ tự tự nhiên của các phần tử hoặc theo thứ tự được chỉ định khi khởi tạo tập hợp.
- LinkedHashSet: 
    - LinkedHashSet kế thừa từ lớp HashSet
    - Nó duy trì mối quan hệ giữa các phần tử bằng cách sử dụng danh sách liên kết đôi (doubly linked list) giữa các phần tử trong tập hợp. -> thứ tự lặp (iteration order) sẽ giống với thứ tự thêm vào (insertion order), nghĩa là thứ tự phần tử trong tập hợp là dự đoán được.
    - Tất cả các phương thức của LinkedHashSet đều giống với HashSet.
    - Tương tự như HashSet, nó cung cấp hiệu năng thời gian hằng số O(1) cho các thao tác add, contains và remove.
- TreeSet: 
    - Lớp TreeSet sử dụng một cấu trúc dữ liệu gọi là cây tìm kiếm nhị phân (binary search tree), hoặc viết tắt là B-tree, dựa trên khái niệm và hiệu quả của thuật toán tìm kiếm nhị phân.
    - Khi các phần tử được thêm vào TreeSet, chúng sẽ được tổ chức dưới dạng một cây, trong đó đỉnh của cây đại diện cho điểm giữa (mid-point) của các phần tử.
        - Các lần phân chia nhị phân tiếp theo sẽ trở thành các nút con trong cây.
        - Nút bên trái và các nút con của nó là các phần tử có giá trị nhỏ hơn nút cha.
        - Nút bên phải và các nút con của nó là các phần tử có giá trị lớn hơn nút cha.
        - Thay vì phải duyệt qua toàn bộ các phần tử trong tập hợp để tìm phần tử phù hợp, cấu trúc cây cho phép duyệt nhanh bằng cách đi qua các nút, trong đó mỗi nút chỉ là một điểm ra quyết định đơn giản (so sánh nhỏ hơn hay lớn hơn). 
        -> Điểm quan trọng là: cây được giữ cân bằng khi các phần tử được thêm vào.
    - TreeSet đảm bảo độ phức tạp O(log(n)) cho các thao tác add, remove và contains, trong khi HashSet cung cấp thời gian hằng số O(1) cho những thao tác tương tự.
    - Lớp này là một tập hợp được sắp xếp (sorted) và cài đặt giao diện SortedSet, trong đó bao gồm các phương thức như first, last, headSet và tailSet, cũng như comparator
    - Tập hợp này cũng cài đặt giao diện NavigableSet, do đó nó có thêm các phương thức như: ceiling, floor, higher, lower, descendingSet và một số phương thức khác.
    - Các phần tử triển khai giao diện Comparable (tức là có khả năng sắp xếp theo thứ tự tự nhiên, như String và các kiểu số) có thể được sử dụng làm phần tử trong TreeSet. 
    -> Nếu phần tử của bạn không triển khai Comparable, bạn phải truyền một Comparator vào constructor của TreeSet.
- EnumSet: là một implementation đặc biệt của Set được thiết kế riêng để làm việc với các giá trị enum.Tất cả phần tử trong một EnumSet phải thuộc cùng một kiểu enum.
    - EnumSet là một lớp trừu tượng (abstract), vì vậy chúng ta không thể khởi tạo nó trực tiếp bằng từ khóa new.
    -> Thay vào đó, nó cung cấp nhiều phương thức factory (như EnumSet.allOf(...), noneOf(...), of(...)) .
- 
#### Map
- Khái niệm: Map trong Collection Framework là một cấu trúc dữ liệu khác. Mặc dù vẫn là một tập hợp các phần tử, nhưng khác biệt ở chỗ các phần tử được lưu trữ thông qua các khóa (keyed references).
-> Map yêu cầu hai tham số: K cho kiểu của key và V cho kiểu của value. -> Chúng phải là kiểu tham chiếu (reference types), không phải kiểu nguyên thủy (primitive types).
- Map trong Java không thể chứa các khóa (key) trùng lặp. Mỗi khóa chỉ có thể ánh xạ (map) tới một giá trị duy nhất.
- 3 “view collection”: keySet, entrySet, và values
    - Truy xuất danh sách các key này dưới dạng một Set view bằng cách gọi phương thức *keySet()* trên bất kỳ đối tượng Map nào. Có thể sử dụng các phương thức sau trên keySet(): remove(Object key), removeAll(Collection<?> c), retainAll(Collection<?> c), clear() 
    - Một Set view của các phần tử (Entry), hoặc các Node trong trường hợp của HashMap, có thể được truy xuất thông qua phương thức *entrySet()*.
    - Có thể truy xuất Collection view của các giá trị này bằng cách gọi phương thức *values()* trên một đối tượng Map.
- 3 lớp triển khai giao diện Map:
    - HashMap: không có thứ tự — các phần tử không được sắp xếp hay duy trì theo thứ tự thêm vào.
    - LinkedHashMap: duy trì thứ tự thêm vào — khi bạn lặp qua map, các phần tử xuất hiện theo đúng thứ tự đã được thêm vào.
    - TreeMap: là một map được sắp xếp — các khóa được sắp xếp theo thứ tự tự nhiên hoặc theo comparator tùy chỉnh.
- HashMap:
    - Trong giao diện Map có một giao diện lồng nhau tĩnh (static nested interface), và tên của nó là Entry
    - HashMap triển khai Map và có một lớp lồng nhau tĩnh (static nested class) tên là Node, lớp này triển khai giao diện Map.Entry.
    - HashMap duy trì một mảng các Node trong một biến trường (field) tên là table.
    - Kích thước của mảng này được Java quản lý tự động, và chỉ số (index) của từng phần tử được xác định bởi hàm băm (hash function).
    -> HashMap không có thứ tự (unordered).
- LinkedHashMap: tập hợp các cặp key–value, trong đó các key được duy trì theo thứ tự thêm vào (insertion order).
- TreeMap: TreeMap thì được sắp xếp theo key thì được sắp xếp theo key -> cần phải triển khai Comparable hoặc được truyền vào một Comparator tùy chỉnh khi khởi tạo TreeMap
- EnumMap: là một implementation đặc biệt của Map, được thiết kế riêng để sử dụng với các khóa kiểu enum.
    - Tất cả các khóa (key) trong EnumMap phải thuộc cùng một kiểu enum, và chúng được sắp xếp theo thứ tự tự nhiên, tức là theo giá trị  (chỉ số vị trí) của các hằng số enum.
    - EnumMap cung cấp chức năng tương tự như HashMap, với các thao tác cơ bản (put, get, containsKey, v.v.) có độ phức tạp thời gian hằng số O(1)
