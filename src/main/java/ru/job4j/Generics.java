package ru.job4j;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Generics {
    public static void main(String[] args) {
        Generics gen = new Generics();
        List<Animal> first = new ArrayList<>();
        List<Predator> second = new ArrayList<>();
        List<Tiger> third = new ArrayList<>();
        first.add(new Animal());
        second.add(new Predator());
        third.add(new Tiger());

        gen.printObject(first);
        gen.printObject(second);
        gen.printObject(third);
        System.out.println();

        /**
         * gen.printBoundedWildCard(first);
         */
        gen.printBoundedWildCard(second);
        gen.printBoundedWildCard(third);
        System.out.println();

        gen.printLowerBoundedWildCard(first);
        gen.printLowerBoundedWildCard(second);
        /**
         *  gen.printLowerBoundedWildCard(third);
         */
    }

    public void printObject(List<?> list) {
        for (Iterator<?> it = list.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    /**
     * Метод выводит выводит элементы List. Но тк входной параметр ограничен
     * сверху классом Predator, то объект класса Animal не может быть
     * элементом этого List. Поэтому строка: gen.printBoundedWildCard(first);
     * вызывает ошибку компиляции.
     */
    public void printBoundedWildCard(List<? extends Predator> list) {
        for (Iterator<? extends Predator> it = list.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    /**
     * Метод выводит выводит элементы List. Но тк входной параметр ограничен
     * снизу классом Predator, то объект класса Tiger не может быть
     * элементом этого List. Поэтому строка: gen.printLowerBoundedWildCard(third);
     * вызывает ошибку компиляции
     */
    public void printLowerBoundedWildCard(List<? super Predator> list) {
        for (Iterator<? super Predator> it = list.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }
}
