import java.util.*;

public class Main {
    public static void main(String[] args) {


        Card[] cardArray = new Card[13];
        Card aceOfHearts = Card.getFaceCard(Card.Suit.HEART, 'A');
        Arrays.fill(cardArray, aceOfHearts);
        Card.printDesk(Arrays.asList(cardArray), "Aces of Hearts", 1);

        // Khong hoat dong do 52 null ma list ko chua null -> fill ko dien vao null
        List<Card> cards = new ArrayList<>(52);
        Collections.fill(cards, aceOfHearts);
        System.out.println(cards);
        System.out.println("cards.size()= " + cards.size());

        // Giai phap
        List<Card> acesOfHearts =  Collections.nCopies(13, aceOfHearts);
        Card.printDesk(acesOfHearts, "Aces Of Hearts", 1);

        Card kingOfClubs = Card.getFaceCard(Card.Suit.CLUB, 'K' );
        List<Card> kingsOfClub =  Collections.nCopies(13, kingOfClubs);
        Card.printDesk(kingsOfClub, "King Of Clubs", 1);

        Collections.addAll(cards, cardArray);
        Card.printDesk(cards, "Card Collection with Aces added",2);

        // -> loi do size cards = 0 < kingsOfClub
        Collections.copy(cards, kingsOfClub); // thay the n phan tu cua king... sang n dau cua card
        Card.printDesk(cards,"Card Collection with Kings added",2);

        cards = List.copyOf(kingsOfClub); // ban sao thuc su
        Card.printDesk(cards, "List Copy Of Kings",2);

        List<Card> desk = Card.getStandardDesk();
        Card.printDesk(desk);

        Collections.shuffle(desk);
        Card.printDesk(desk, "Shuffled Desk",4);

        Collections.reverse(desk);

        var sortingA = Comparator.comparing(Card::rank)
                .thenComparing(Card::suit);
        Collections.sort(desk, sortingA);
        Card.printDesk(desk, "Standard Desk sorted by rank, suit",13);

        Collections.reverse(desk);
        Card.printDesk(desk, "Sorted by rank, suit reversed: ",13);

        List<Card> kings = new ArrayList<>(desk.subList(4,8));
        Card.printDesk(kings, "King in desk",1);

        List<Card> tens = new ArrayList<>(desk.subList(16,20));
        Card.printDesk(tens, "Tens in desk",1);

        int subListIndex = Collections.indexOfSubList(desk,tens);
        System.out.println("sublist index for tens = " + subListIndex);
        System.out.println("Contains = " + desk.containsAll(tens));

        boolean disjoint = Collections.disjoint(desk, tens);
        System.out.println("disjoint = "+ disjoint);

        boolean disjoint2 = Collections.disjoint(desk, kings);
        System.out.println("disjoint = "+ disjoint2);

        desk.sort(sortingA);
        Card tenOfHearts = Card.getNumericCard(Card.Suit.HEART, 10);
        int foundIndex = Collections.binarySearch(desk, tenOfHearts,sortingA);// neu lit nhieu phan tu va dc sap xep
        System.out.println("foundIndex = "+foundIndex);
        System.out.println("foundIndex = "+desk.indexOf(tenOfHearts));// neu list it phan tu chua dc sap xep hoac pha tu trung lap
        System.out.println(desk.get(foundIndex));

        Card tenOfClubs = Card.getNumericCard(Card.Suit.CLUB, 10);
        Collections.replaceAll(desk, tenOfClubs, tenOfHearts);
        Card.printDesk(desk.subList(32,36), "Tens row", 1);

        Collections.replaceAll(desk,tenOfHearts,tenOfClubs );
        Card.printDesk(desk.subList(32,36), "Tens row", 1);

        if (Collections.replaceAll(desk,tenOfHearts, tenOfClubs)) {
            System.out.println("Tens of Hearts replaced with tens of clubs");
        } else {
            System.out.println("No tens of hearts found in the list");
        }

        System.out.println("Ten of clubs cards = " +
                Collections.frequency(desk, tenOfClubs));

        System.out.println("Best Card: " + Collections.max(desk, sortingA));
        System.out.println("Worst Card: "+ Collections.min(desk,sortingA));

        var sortBySuit = Comparator.comparing(Card::suit)
                .thenComparing(Card::rank);
        desk.sort(sortBySuit);
        Card.printDesk(desk, "Sorted by Suit, Rank", 4);

        List<Card> copied = new ArrayList<>(desk.subList(0,13));
        Collections.rotate(copied,2);
        System.out.println("UnRotated: " + desk.subList(0,13));
        System.out.println("Rotated "+2+": "+copied);

        copied = new ArrayList<>(desk.subList(0,13));
        Collections.rotate(copied,-2);
        System.out.println("UnRotated: " + desk.subList(0,13));
        System.out.println("Rotated "+-2+": "+copied);

        copied = new ArrayList<>(desk.subList(0,13));
        for (int i = 0; i < copied.size(); i++) {
            Collections.swap(copied,i,copied.size() -1 -i);
        }
        System.out.println("Manual reverse: "+copied);

        copied = new ArrayList<>(desk.subList(0,13));
        Collections.reverse(copied);
        System.out.println("Using reverse : " + copied);
    }
}