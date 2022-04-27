package ch8;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class TryWithResourceEx2 {
    public static void main(String[] args) throws IOException {
        try (
                FileInputStream is = new FileInputStream("file.txt");
                BufferedInputStream bis = new BufferedInputStream(is)
        ) {
            int data = -1;
            while ((data = bis.read()) != -1) {
                System.out.print((char) data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}