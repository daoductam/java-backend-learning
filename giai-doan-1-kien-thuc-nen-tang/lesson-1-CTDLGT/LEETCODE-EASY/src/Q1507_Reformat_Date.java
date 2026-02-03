import java.util.HashMap;
import java.util.Map;

public class Q1507_Reformat_Date {
    public static void main(String[] args) {

    }

    public static String reformatDate(String date) {
        Map<String, String> monthMap = new HashMap<>();
        monthMap.put("Jan", "01");
        monthMap.put("Feb", "02");
        monthMap.put("Mar", "03");
        monthMap.put("Apr", "04");
        monthMap.put("May", "05");
        monthMap.put("Jun", "06");
        monthMap.put("Jul", "07");
        monthMap.put("Aug", "08");
        monthMap.put("Sep", "09");
        monthMap.put("Oct", "10");
        monthMap.put("Nov", "11");
        monthMap.put("Dec", "12");

        String[] parts = date.split(" ");
        String day = parts[0].replaceAll("[a-z]+", "");
        if (day.length() == 1) day = "0" + day;

        String month = monthMap.get(parts[1]);
        String year = parts[2];

        return year + "-" + month + "-" + day;
    }
}
