public class Q2651_Calculate_Delayed_Arrival_Time {
    public static void main(String[] args) {

    }

    public int findDelayedArrivalTime(int arrivalTime, int delayedTime) {
        int sum = arrivalTime + delayedTime;
        if (sum == 24) {
            return 0;
        } else if (sum > 24) {
            sum = sum-24;
        }
        return sum;
    }
}
