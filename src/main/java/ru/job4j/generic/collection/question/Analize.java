package ru.job4j.generic.collection.question;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Map<Integer, String> all = new HashMap<>();
        int newElement = 0;
        int changeElement = 0;
        int removeElement = 0;
        int noChange = 0;
        for (User us : previous) {
            all.put(us.getId(), us.getName());
        }
        for (User us : current) {
            String rsl = all.put(us.getId(), us.getName());
            if (rsl == null) {
                newElement++;
            } else {
                if (rsl != us.getName()) {
                    changeElement++;
                } else {
                     noChange++;
                }
            }
        }
        removeElement = previous.size() - changeElement - noChange;
        return  new Info(newElement, changeElement, removeElement);

    }

}

