package ch6;

import java.util.Arrays;

public class TvTest {
    public static void main(String[] args) {
        Tv t = new Tv();
        t.channel = 7;
        t.channelDown();
        System.out.println("현재 채널 = " + t.channel);
        System.out.println(t.color);
        System.out.println(t.power);
    }
}

class Tv{
    String color;
    boolean power;
    int channel;

    void power(){
        power = !power;
    }
    void channelUp(){
        ++channel;
    }
    void channelDown(){
        --channel;
    }
}