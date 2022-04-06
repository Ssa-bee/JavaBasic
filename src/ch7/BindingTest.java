package ch7;

public class BindingTest {

}

class Parent{
    int x = 100;
    void method(){
        System.out.println("Parent Method");
    }
}

class Child extends Parent{
    int x = 200;

    void method(){
        System.out.println("Child Method");
    }
}