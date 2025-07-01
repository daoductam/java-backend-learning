import java.util.*;

public class MapViewMain {
    public static void main(String[] args) {

        Map<String, Contact> contacts = new HashMap<>();
        ContactData.getData("phone").forEach(c -> contacts.put(c.getName(), c));
        ContactData.getData("email").forEach(c -> contacts.put(c.getName(), c));

        Set<String> keysView = contacts.keySet();
        System.out.println(keysView);

        Set<String> copyOfKeys = new TreeSet<>(contacts.keySet());
        System.out.println(copyOfKeys);

        if(contacts.containsKey("Linus Van Pelt")) {
            System.out.println("Hellooooo");
        }

        keysView.remove("Daffy Duck");
        System.out.println(keysView);
        contacts.forEach((k,v) -> System.out.println(v));

        copyOfKeys.remove("Linus Van Pelt");
        System.out.println(copyOfKeys);
        contacts.forEach((k,v) -> System.out.println(v));

        keysView.retainAll(List.of("Linus Van Pelt", "Charlie Brown",
                        "Robin Hood", "Mickey Mouse"));
        System.out.println(keysView);
        contacts.forEach((k,v) -> System.out.println(v));

        keysView.clear();
        System.out.println(contacts);

        ContactData.getData("email").forEach(c -> contacts.put(c.getName(), c));
        ContactData.getData("phone").forEach(c -> contacts.put(c.getName(), c));
        System.out.println(keysView);

        var values = contacts.values();
        values.forEach(System.out::println);

        values.retainAll(ContactData.getData("email"));
        System.out.println(keysView);
        contacts.forEach((k,v) -> System.out.println(v));

        System.out.println("-----------------------");
        List<Contact> list = new ArrayList<>(values);
        list.sort(Comparator.comparing(Contact::getNameLastFirst));
        list.forEach(c -> System.out.println(c.getNameLastFirst()+": "+c));

        System.out.println("------------------------");
        Contact first = list.get(0);
        contacts.put(first.getNameLastFirst(), first);
        values.forEach(System.out::println);
        keysView.forEach(System.out::println);

        HashSet<Contact> set = new HashSet<>(values);
        set.forEach(System.out::println);
        if (set.size() < contacts.keySet().size()) {
            System.out.println("Lap gia tri o map");
        }

        var nodeSet = contacts.entrySet();
        for (var node : nodeSet) {
            System.out.println(nodeSet.getClass().getName());
            if (!node.getKey().equals(node.getValue().getName())) {
                System.out.println(node.getClass().getName());
                System.out.println("Key khong match name: "+node.getKey()+": "
                + node.getValue());
            }
        }
    }
}
