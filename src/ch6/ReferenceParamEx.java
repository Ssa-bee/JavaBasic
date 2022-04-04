package ch6;

public class ReferenceParamEx {
    public static void main(String[] args) {
        Data d = new Data();
        d.x =10;

        System.out.println("main : d = " + d);
        change(d);
        System.out.println("After Change main : d = " + d);
    }
    static void change(Data d){
        Data newD = new Data();
        d = newD;
        System.out.println("d = " + d);
    }
}
