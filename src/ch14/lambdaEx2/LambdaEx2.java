package ch14.lambdaEx2;

public class LambdaEx2 {
    public static void main(String[] args) {
        MyFunction f = () -> {
        };
        Object obj = (MyFunction) (() -> { });
        String str = ((Object) (MyFunction) (() -> { })).toString();

        System.out.println("f = " + f);
        System.out.println("obj = " + obj);
        System.out.println("str = " + str);

//        System.out.println(() -> { });
        System.out.println((MyFunction)(() -> {}));
//        System.out.println((MyFunction)(() -> {}).toString());
        System.out.println(((Object)(MyFunction)(()->{})).toString());
    }
}

@FunctionalInterface
interface MyFunction{
    void myMethod();
}