package ch7;

public class FighterTest {
    public static void main(String[] args) {
        Fighter f = new Fighter();

        if (f instanceof Unit) {
            System.out.println("f는 Unit의 자손클래스 입니다.");
        }
        if(f instanceof Fightable){
            System.out.println("f는 Fightable인터페이스를 구현했습니다.");
        }
        if(f instanceof Moveable){
            System.out.println("f는 Moveable인터페이스를 구현했습니다.");
        }
        if(f instanceof Attackable){
            System.out.println("f는 Attackable인터페이스를 구현했습니다.");
        }
        if(f instanceof Object){
            System.out.println("f는 Object클래스의 자손입니다.");
        }
    }
}

class Fighter extends Unit implements Fightable{
    @Override
    public void move(int x, int y) {
        //구현생략
    }

    @Override
    public void attack(Unit u) {
        //구현생략
    }
}

class Unit {
    int currentHP;
    int x;
    int y;
}

interface Moveable {
    void move(int x, int y);
}

interface Attackable {
    void attack(Unit u);
}

interface Fightable extends Moveable, Attackable {
}