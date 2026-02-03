public class Q2739_Total_Distance_Traveled {
    public static void main(String[] args) {

    }

    /*
    Một chiếc xe tải có 2 bình nhiên liệu:

mainTank: lượng nhiên liệu ban đầu trong bình chính (lít)

additionalTank: lượng nhiên liệu trong bình phụ (lít)

Xe chạy được 10 km cho mỗi lít nhiên liệu.

Quy tắc đặc biệt:

Mỗi khi bình chính tiêu thụ được 5 lít nhiên liệu, nếu bình phụ còn ít nhất 1 lít, thì 1 lít sẽ được chuyển ngay từ bình phụ sang bình chính.

Việc chuyển nhiên liệu không diễn ra liên tục, mà chỉ xảy ra ngay sau khi đúng 5 lít đã tiêu hao.

Yêu cầu: Tính quãng đường tối đa mà xe tải có thể đi được.
     */
    public int distanceTraveled(int mainTank, int additionalTank) {

    }

    class Solution {
        public int distanceTraveled(int mainTank, int additionalTank) {
            int distance = 0;

            // Chỉ khi còn nhiên liệu trong bình chính thì xe mới chạy tiếp
            while (mainTank > 0) {
                if (mainTank >= 5) {
                    // Tiêu 5 lít từ bình chính -> đi được 50 km
                    mainTank -= 5;
                    distance += 50;

                    // Nếu bình phụ còn >= 1 lít thì bơm 1 lít sang bình chính
                    if (additionalTank > 0) {
                        mainTank += 1;
                        additionalTank--;
                    }
                } else {
                    // Nếu dưới 5 lít còn lại => dùng hết -> compute once
                    distance += mainTank * 10;
                    mainTank = 0;
                }
            }

            return distance;
        }
    }

}
