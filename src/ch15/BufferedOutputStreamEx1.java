package ch15;

import java.io.*;

public class BufferedOutputStreamEx1 {
    public static void main(String[] args) {
        try{
            FileOutputStream fos = new FileOutputStream("123.txt");
            BufferedOutputStream bos = new BufferedOutputStream(fos, 5);

            for (int i = '1'; i <= '9'; i++) {
                bos.write(i);
            }

            fos.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
