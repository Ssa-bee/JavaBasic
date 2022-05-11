package ch11.arrays;

import java.lang.reflect.Array;
import java.util.Arrays;

public class BinarySearchTest {
    public static void main(String[] args) {
        int[] arr = {3, 2, 0, 1, 4};
        int idx = Arrays.binarySearch(arr, 2);

        System.out.println("idx = " + idx);

        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));

        int idx2 = Arrays.binarySearch(arr, 2);
        System.out.println("idx2 = " + idx2);
    }
}
