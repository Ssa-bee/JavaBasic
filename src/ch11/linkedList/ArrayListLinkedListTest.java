package ch11.linkedList;

import java.util.*;
public class ArrayListLinkedListTest {
    public static void main(String[] args) {
        List al = new ArrayList(2000000);
    }
    public static long add1(List list){
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            list.add(i+"");
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

    public static long add2(List list){
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            list.add(500, "X");
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

    public static long remove1(List list){
        long start = System.currentTimeMillis();
        for (int i = list.size() - 1; i >= 0; i--) {
            list.remove(i);
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

    public static long remove2(List list){
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            list.remove(i);
        }
        long end = System.currentTimeMillis();
        return end - start;
    }
}
