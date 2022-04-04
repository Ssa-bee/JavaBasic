package ch6;

public class CarTest {
    public static void main(String[] args) {
        Car c1 = new Car();
        c1.color = "White";
        c1.gearType = "auto";
        c1.door = 4;

        Car c2 = new Car("Black", "auto", 4);
        System.out.println(c1.toString());
        System.out.println(c2.toString());
    }
}
class Car{
    String color;
    String gearType;
    int door;

    Car(){}
    Car(String c, String g, int d){
        color = c;
        gearType = g;
        door = d;
    }

    @Override
    public String toString() {
        return "Car{" +
                "color='" + color + '\'' +
                ", gearType='" + gearType + '\'' +
                ", door=" + door +
                '}';
    }
}
