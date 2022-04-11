package ch8;

public class FinallyTest2 {
    public static void main(String[] args) {
        FinallyTest2.method1();
        System.out.println("method1 의 수행을 마치고 main메서드가 종료되었습니다.");
    }
    static void method1(){
        try{
            System.out.println("method1이 호출되었습니다.");
            return;
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("finally블럭이 실행되었습니다");
        }
        System.out.println("method1이 종료되었습니다.");
    }
}
