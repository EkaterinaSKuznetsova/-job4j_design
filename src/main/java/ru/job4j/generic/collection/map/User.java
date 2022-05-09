package ru.job4j.generic.collection.map;

import java.util.*;
import java.util.Map;

public class User {
    private String name;
    private int children;
    private Calendar birthday;



    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
        User user1 = new User("Artem", 1, new GregorianCalendar(1982, 12, 24, 12, 15, 20));
        User user2 = new User("Artem", 1, new GregorianCalendar(1982, 12, 24, 12, 15, 20));
        Map<User, Object> mapUsers = new HashMap<>();
        mapUsers.put(user1, new Object());
        mapUsers.put(user2, new Object());
        System.out.println(mapUsers);
    }
}

