package ch12.generics.fruitEx2;

import java.util.*;

public class FruitBoxEx2 {
    public static void main(String[] args) {
        FruitBox<Fruit> fruitBox = new FruitBox<>();
        FruitBox<Apple> appleBox = new FruitBox<>();
        FruitBox<Grape> grapeBox = new FruitBox<>();
//        FruitBox<Grape> grapeBox2 = new FruitBox<Apple>();    타입불일치

        fruitBox.add(new Fruit());
        fruitBox.add(new Apple());
        fruitBox.add(new Grape());
        appleBox.add(new Apple());
//        appleBox.add(new Grape());
        grapeBox.add(new Grape());

        System.out.println("fruitBox = " + fruitBox);
        System.out.println("appleBox = " + appleBox);
        System.out.println("grapeBox = " + grapeBox);
    }
}

interface Eatable{ }

class Box<T>{
    ArrayList<T> list = new ArrayList<T>();

    void add(T item) { list.add(item); }
    T get(int i) { return list.get(i); }
    int size(){ return list.size(); }

    @Override public String toString() { return list.toString(); }
}

class Fruit implements Eatable{
    @Override public String toString() { return "Fruit"; }
}

class Apple extends Fruit{ @Override public String toString() { return "Apple"; }}
class Grape extends Fruit{ @Override public String toString() { return "Grape"; }}
class Toy{ @Override public String toString() { return "Toy"; }}

class FruitBox<T extends Fruit & Eatable> extends Box<T>{ }