package ch12.fruitEx1;

import java.util.ArrayList;

public class FruitBoxEx1 {
    public static void main(String[] args) {
        Box<Fruit> fruitBox = new Box<>();
        Box<Apple> appleBox = new Box<>();
        Box<Toy> toyBox = new Box<>();
        Box<Grape> grapeBox = new Box<>();

        fruitBox.add(new Fruit());
        fruitBox.add(new Apple());

        appleBox.add(new Apple());
        appleBox.add(new Apple());
//        appleBox.add(new Toy());      Box<Apple>에는 Apple만 담을 수 있음

        toyBox.add(new Toy());
//        toyBox.add(new Apple());      Box<Toy>에는 Apple을 담을 수 없음

        System.out.println("fruitBox = " + fruitBox);
        System.out.println("appleBox = " + appleBox);
        System.out.println("toyBox = " + toyBox);
    }
}

class Fruit{ @Override public String toString() { return "Fruit"; } }
class Apple extends Fruit{ @Override public String toString() { return "Apple"; } }
class Grape extends Fruit{ @Override public String toString() { return "Grape"; } }
class Toy { @Override public String toString() { return "Toy"; } }

class Box<T>{
    ArrayList<T> list = new ArrayList<T>();
    void add(T item) { list.add(item); }
    T get(int i) { return list.get(i); }
    int size(){ return list.size(); }

    @Override
    public String toString() {
        return list.toString();
    }

//    T[] itemArr;

//    T[] toArray(){
//        T[] tmpArr = new T[itemArr.length];
//        return tmpArr;
//    }
}