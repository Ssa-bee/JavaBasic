package ch14.streamEx1;

import java.util.*;
import java.util.stream.*;

public class StreamEx1 {
    public static void main(String... args) {
//        String[] strArr = {"aaa", "ddd", "ccc"};
//        List<String> strList = Arrays.asList(strArr);

//        Stream<String> strStream1 = strList.stream();
//        Stream<String> strStream2 = Arrays.stream(strArr);

//        strStream1.sorted().forEach(System.out::println);
//        strStream2.sorted().forEach(System.out::println);

//        List<String> sortedList = strStream2.sorted().collect(Collectors.toList());
//        System.out.println("sortedList = " + sortedList);

//        long numOfStr = strStream1.count();
//        System.out.println("numOfStr = " + numOfStr);

        Stream<Student> studentStream = Stream.of(
                new Student("이자바", 3, 300),
                new Student("김자바", 1, 200),
                new Student("안자바", 2, 100),
                new Student("박자바", 2, 150),
                new Student("소자바", 1, 200),
                new Student("나자바", 3, 290),
                new Student("감자바", 3, 180)
                );

        studentStream.sorted(Comparator.comparing(Student::getBan)
                .thenComparing(Comparator.naturalOrder()))
                .forEach(System.out::println);
    }
}

class Student implements Comparable<Student> {
    String name;
    int ban;
    int totalScore;

    public Student(String name, int ban, int totalScore) {
        this.name = name;
        this.ban = ban;
        this.totalScore = totalScore;
    }

    @Override
    public String toString() {
        return String.format("[%s, %d, %d]", name, ban, totalScore);
    }

    public String getName() {
        return name;
    }

    public int getBan() {
        return ban;
    }

    public int getTotalScore() {
        return totalScore;
    }

    @Override
    public int compareTo(Student s) {
        return s.totalScore - this.totalScore;
    }

}