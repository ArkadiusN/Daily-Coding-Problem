import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.net.*;
import java.util.Comparator;

import static java.util.Comparator.*;

/**
 * Implementation of the problems for the book called "Daily Coding Problem"
 * by Alex Miller & Lawrence Wu.
 * @author Arkadiusz Nowacki
 * @version 17.01
 */
public class Main {

    static String productAllOtherElm(int[] arr){
        int[] prefix = new int[arr.length];
        int[] suffix = new int[arr.length];
        int [] result = new int[arr.length];
        prefix[0] = 1;
        suffix[arr.length-1] = 1;
        for (int i = 0; i < arr.length-1; i++) {
            prefix[i+1] = arr[i] * prefix[i];
        }

        for (int i = arr.length-1; i > 0 ; i--) {
            suffix[i-1] = arr[i] * suffix[i];
        }

        for (int i = 0; i < arr.length; i++) {
            result[i] = prefix[i] * suffix[i];
        }
        System.out.println(Arrays.toString(result));
        return result.toString();
    }

    static int[] bubbleSort(int[] arr){
        int n = arr.length;
        int temp = 0;
        int [] sorted = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n-1; j++) {
                if(arr[j-1] > arr[j]){
                    temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;
                }
            }
            sorted[i] = arr[i];
        }
        return sorted;
    }

    static void smallestWindow(int[] arr){
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE, left = 0, right = 0;
        int[] bound = new int[2];
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            if(arr[i] < max){
                right = i;
            }
        }
        for (int i = arr.length-1; i > 0; i--) {
            min = Math.min(min, arr[i]);
            if(arr[i] > min){
                left = i;
            }
        }
        bound[0] = left;
        bound[1] = right;
        System.out.println(Arrays.toString(bound));
    }





    public static void main(String[] args) {
//        productAllOtherElm(new int []{1,2,3,4,5});
        smallestWindow(new int[]{3,7,5,6,9});
        smallestWindow(new int[]{ 1, 2, 3, 4, 7, 5, 6, 8 });
//
//        smallestWindow(new int[]{1,2,3,4,5,9,8,7,6,10,11,12});

    }

}// End of class
