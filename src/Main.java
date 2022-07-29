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


    /**
     *  Brute force approach looking at each sub array and saving its
     *  sum. Keeps track of the largest sum.
     * <br>
     *  Complexity of O(N^2)
     **/
    static int maxSubArraySum(int[] arr){
        int maximumSubArraySum = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if(sum > maximumSubArraySum){
                    maximumSubArraySum = sum;
                }
            }
        }
        return maximumSubArraySum;
    }

    /**
     *  Kadane's Algorithm that looks for the max_so_far
     *  at the given index.
     *  <br>
     *  If it is greater than previous one's
     *  saves it in max_so_far and keeps searching until the end of
     *  array.
     * <br>
     *  Complexity of O(N)
     **/
    static int maxSubArraySum2(int[] arr){
        int max_so_far = arr[0];
        int max_ending_here = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max_ending_here + arr[i]) {
                max_ending_here = arr[i];
            } else {
                max_ending_here = max_ending_here + arr[i];
            }
            if(max_so_far < max_ending_here){
                max_so_far = max_ending_here;
            }
        }
        return max_so_far;
    }

    /**
     * Complexity of O(N^2)
     * @param arr array of numbers to check on.
     */
    static void smallerToRight(int[] arr){
        int [] counter = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int count = 0;
            for (int j = i+1; j < arr.length; j++) {
                if(arr[i] > arr[j]){
                    count++;
                }
            }
            counter[i] = count;
        }
        System.out.println(Arrays.toString(counter));
    }





    public static void main(String[] args) {
//        productAllOtherElm(new int []{1,2,3,4,5});
//        smallestWindow(new int[]{3,7,5,6,9});
//        System.out.println(maxSubArraySum(new int[]{34,-50,42,14,-5,86}));
//        System.out.println(maxSubArraySum2(new int[]{-3,1,-8,4,-1,2,1,-5,5}));
        smallerToRight(new int[]{3,4,9,6,1});
        smallerToRight(new int[]{15,16,9,8,10}); //3,3,1,0,0

    }

}// End of class
