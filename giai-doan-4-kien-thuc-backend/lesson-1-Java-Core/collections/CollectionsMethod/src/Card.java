import java.util.ArrayList;
import java.util.List;

public record Card(Suit suit, String face, int rank) {


    public enum Suit {
        CLUB, DIAMOND, HEART, SPADE;

        public char getImage() {
            return (new char[]{9827,9830, 9829, 9824})[this.ordinal()];
        }


    }

    @Override
    public String toString() {
        int index = face.equals("10") ? 2 : 1;
        String faceString = face.substring(0, index);
        return "%s%c(%d)".formatted(faceString, suit.getImage(), rank);
    }

    // Tạo bài mặt số
    public static Card getNumericCard(Suit suit, int cardNumber) {
        if (cardNumber>1 && cardNumber <11) {
            return new Card(suit, String.valueOf(cardNumber), cardNumber-2);
        }
        System.out.println("ivalid Numeric card selected");
        return null;
    }

    // Tạo bài mặt chữ
    public static Card getFaceCard(Suit suit, char abbrev) {
        int charIndex = "JQKA".indexOf(abbrev);
        if (charIndex > -1) {
            return new Card(suit,""+abbrev,charIndex + 9 );
        }
        System.out.println("Invalid Face card selected");
        return null;
    }

    // Tạo bộ bài
    public static List<Card> getStandardDesk() {

        List<Card> desk  = new ArrayList<>(52);
        for (Suit suit: Suit.values()) {
            for (int i = 2; i <= 10 ; i++) {
                desk.add(getNumericCard(suit, i));
            }
            for (char c : new char[]{'J', 'Q', 'K', 'A'}) {
                desk.add(getFaceCard(suit, c));
            }
        }

        return desk;
    }

    //In bo bai
    public static void printDesk(List<Card> desk) {
        printDesk(desk, "Current Desk", 4);
    }

    public static void printDesk(List<Card> desk, String desc, int rows) {
        System.out.println("------------------------");
        if (desc != null) {
            System.out.println(desc);
        }
        int cardsInRow = desk.size() / rows;
        for (int i = 0; i < rows; i++) {
            int startIndex = i * cardsInRow;
            int endIndex = startIndex + cardsInRow;
            desk.subList(startIndex, endIndex)
                    .forEach(c -> System.out.print(c + " "));
            System.out.println();
        }
    }


}
