import java.time.LocalDate;
import java.util.*;

public class Main {
    public static Map<String, Purchase> purchases = new LinkedHashMap<>();
    private static NavigableMap<String, Student> students = new TreeMap<>();
    public static void main(String[] args) {
        Course jmc = new Course("jmc101", "Java Master Class",
                "Java");
        Course python = new Course("put101", "Python Master Class",
                "Python");
        addPurchase("Mary Martin",jmc, 129.99);
        addPurchase("Andy Martin",jmc, 139.99);
        addPurchase("Mary Martin",python, 149.99);
        addPurchase("Joe Jones",jmc, 129.99);
        addPurchase("Bill Brown",python, 119.99);

        addPurchase("Chuck Cheese",python, 119.99);
        addPurchase("Davey Jones",jmc, 119.99);
        addPurchase("Eva East",python, 139.99);
        addPurchase("Fred Forker",jmc, 139.99);
        addPurchase("Greg Brady",python, 119.99);

        purchases.forEach((key, value) -> System.out.println(key+": "+value));
        System.out.println("----------------------------------------");
        students.forEach((key, value) -> System.out.println(key+": "+value));

        NavigableMap<LocalDate, List<Purchase>> datedPurchases = new TreeMap<>();

        for (Purchase p : purchases.values()) {
            datedPurchases.compute(p.purchaseDate(),
                    (pdate, plist)-> {
                List<Purchase> list =
                        (plist == null) ? new ArrayList<>() : plist;
                        list.add(p);
                        return list;
                    });
        }

        datedPurchases.forEach((key, value) -> System.out.println(key+": "+value));

        int currentYear = LocalDate.now().getYear();

        LocalDate firstDay = LocalDate.ofYearDay(currentYear, 1);
        LocalDate week1 = firstDay.plusDays(7);
        Map<LocalDate, List<Purchase>> week1Purchases = datedPurchases.headMap(week1);
        Map<LocalDate, List<Purchase>> week2Purchases = datedPurchases.tailMap(week1);

//        System.out.println("-------------------------");
//        week1Purchases.forEach((key, value)-> System.out.println(key+": "+value));
//        System.out.println("-------------------------");
//        week2Purchases.forEach((key, value)-> System.out.println(key+": "+value));

        displayStatus(1, week1Purchases);
        displayStatus(2, week2Purchases);

        System.out.println("-----------------------------");

        LocalDate lastDate = datedPurchases.lastKey();
        var previousEntry = datedPurchases.lastEntry();

        while (previousEntry != null) {
            List<Purchase> lastDaysData = previousEntry.getValue();
            System.out.println(lastDate + " purchases: "+lastDaysData.size());

            LocalDate prevDate = datedPurchases.lowerKey(lastDate);
            previousEntry= datedPurchases.lowerEntry(lastDate);
            lastDate = prevDate;
        }

        System.out.println("------------------------------------");
        var reversed = datedPurchases.descendingMap();

        LocalDate firstDate = reversed.firstKey();
//        var nextEntry = reversed.firstEntry();
        var nextEntry = reversed.pollFirstEntry();

        while (nextEntry != null) {
            List<Purchase> lastDaysData = nextEntry.getValue();
            System.out.println(firstDate+ " purchase : " + lastDaysData.size());

            LocalDate nextDate = reversed.higherKey(firstDate);
            nextEntry = reversed.pollFirstEntry();
//            nextEntry = reversed.higherEntry(firstDate);
            firstDate = nextDate;
        }
        System.out.println("----------------------------");
        datedPurchases.forEach((key, value)-> System.out.println(key+": "+value));

    }

    private static void addPurchase(String name, Course course, double price) {
        Student existingStudent= students.get(name);
        if (existingStudent==null) {
            existingStudent= new Student(name, course);
            students.put(name, existingStudent);
        } else {
            existingStudent.addCourse(course);
        }

        int day =new Random().nextInt(1,15);
        String key = course.courseId()+"_"+existingStudent.getId();
        int year = LocalDate.now().getYear();
        Purchase purchase = new Purchase(course.courseId(),
                existingStudent.getId(), price, year,day);
        purchases.put(key,purchase);

    }
    public static void displayStatus(int period,
                                     Map<LocalDate, List<Purchase>> periodData) {
        System.out.println("-----------------");
        Map<String, Integer> weeklyCounts = new TreeMap<>();
        periodData.forEach((key, value) -> {
            System.out.println(key+": "+value);
            for (Purchase p :value) {
                weeklyCounts.merge(p.courseId(), 1,(prev, current) -> {
                    return prev + current;
                });
            }
        });
        System.out.printf("Week %d Purchase = %s%n",period, weeklyCounts);
    }
}