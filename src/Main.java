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
    //______________________________________________
    // Helper methods
    static int[] insertionSort(int [] arr){
        int temp = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr [j - 1]) {
                    temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
        }
        return arr;
    }
    //______________________________________________

    /**
     * Given an array of integers, return a new array such that each element
     * at index 'i' of the new array is the product of all the numbers in the original array
     * except the one at 'i'.
     * For example, if our input was '[1,2,3,4,5]', the expected output would be '[128,60,40,30,24]'.
     * If our output was '[3,2,1]', the expected output would be '[2,3,6]'.
     * Do not use division.
     * @param nums array of numbers
     */
    static void getProduct(int[] nums){
        int[] prefix = new int[nums.length];
        int[] suffix = new int[nums.length];
        prefix[0] = 1;
        suffix[nums.length -1] = 1;

        for (int num = 0; num < nums.length-1; num++) {
            prefix[num +1] = nums[num] * prefix[num];
        }

        for (int num = nums.length-1; num > 0; num--) {
            suffix[num -1] = nums[num] * suffix[num];
        }

        int [] result  = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = prefix[i] * suffix[i];
        }
        System.out.println("Prefix: " + Arrays.toString(prefix));
        System.out.println("Suffix: " + Arrays.toString(suffix));
        System.out.println("Result: " + Arrays.toString(result));
    }

    /**
     * Given an array of integers that are out of order, determine the bounds of the
     * smallest window that must be sorted in order for the entire array to be sorted. For example,
     * given '[3,7,5,6,9]', you should return '(1,3)'.
     * @param array of numbers to search through.
     */
    static void locateSmallestWindow(int [] array){
        System.out.println("Original array: " + Arrays.toString(array));
        int[] sorted = insertionSort(array);
        System.out.println("Sorted array: " + Arrays.toString(sorted));

        int left = 0, right = 0;
        for (int i = 0; i < array.length-1; i++) {
            for (int j = 0; j < i; j++) {
                if(array[i] != sorted[j] && left == 0){
                    left = i;
                }else if(array[i] != sorted[j]){
                    right = i;
                }
            }
        }
        System.out.println("(" + left + " " + right + ")");
    }

    static void locateSmallestWindowTwo(int [] array){
        int boundOne = 0, boundTwo = 0, i = 1, g = array.length-1;

        while (true){
            if(!(array[i-1] < array[i])){
                boundOne = i-1;
                break;
            }
            i++;
        }

        while (true){
            if(!(array[g] > array[g-1])){
                boundTwo = g-1;
                break;
            }
            g--;
        }

        System.out.println("Index window is: " + "(" + boundOne + ", " + boundTwo + ")");
    }

    static int maximumSubArraySum(int [] array){
        int max_ending_here = 0, max_so_far = 0;
        for (int i = array.length-1; i > 0; i--) {
            max_ending_here = Math.max(array[i], max_ending_here + array[i]);
            max_so_far = Math.max(max_so_far, max_ending_here);
        }
        return max_so_far;
    }

    static int maximum_circular_subArray(int [] array){
        int sum = Arrays.stream(array).sum();
        int max_subArray_sum_wraparound = sum - minSubArraySum(array);
        return Math.max(maximumSubArraySum(array), max_subArray_sum_wraparound);
    }

    static int minSubArraySum(int [] array){
        int min_ending_here = 0, min_so_far = 0;
        for (int i = 0; i <array.length; i++) {
            min_ending_here = Math.min(array[i], min_ending_here + array[i]);
            min_so_far = Math.min(min_so_far, min_ending_here);
        }
        return min_so_far;
    }

    /** This method checks how many number to thr right hand side are smaller
     * than each element at 'ith' element.
     * BigO(N^2)
     * @param array the array to check the elements to right.
     * @return the integer array of smaller numbers to right.
     */
    static int[] smallerElementsToRight(int[] array){
        int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            for (int j = i+1; j < array.length; j++) {
                if(array[j] < array[i]){
                    result[i] ++;
                }
            }
        }
        return result;
    }
    
    static ArrayList findAnagramIndices(String w, String s) {
        char[] charArr = s.toCharArray();
        String[] strArr = new String[s.length() - 1];
        String reverse = String.valueOf(new StringBuilder(w).reverse()), doubleCharString;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i < charArr.length; i++) {
            doubleCharString = String.valueOf(charArr[i - 1]) + charArr[i];
            strArr[i - 1] = doubleCharString;
        }
        for (int i = 0; i < strArr.length; i++) {
            if (w.matches(strArr[i]) || reverse.matches(strArr[i])) {
                list.add(i);
            }
        }
        return list;

    }

    static boolean checkPalindrome(String wordOne){
        int i = 0, j = wordOne.length()-1;
        while (i < j){
            if(wordOne.charAt(i) != wordOne.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }


    static void palindromePairs(String [] words){
        ArrayList<Integer[]> list = new ArrayList<>();
        for (int i = 1; i < words.length; i++) {
            String str = words[i - 1].concat(words[i]);
            if (checkPalindrome(str)) {
                list.add(new Integer[]{i - 1, i});
            }
        }
        for (int i = words.length-1; i > 0 ; i--) {
            String str = words[i].concat(words[i-1]);
            if(checkPalindrome(str)){
                list.add(new Integer[]{i, i-1});
            }
        }

        Collections.sort(list, comparing(o -> (o[0])));

        System.out.print("(");
        for (Integer[] integers : list) {
            System.out.print(Arrays.stream(integers).toList());
        }
        System.out.print(")");
    }




    public static void main(String[] args) {
//        getProduct(new int[]{1,2,3,4,5});
//        locateSmallestWindow(new int[]{3,7,5,6,9});
//        locateSmallestWindowTwo(new int[]{3,7,5,6,8,12,9});
//        System.out.println(maximumSubArraySum(new int[]{34,-50, 42, 14, -5, 86}));
//        System.out.println(maximum_circular_subArray(new int []{8,-1,3,4}));
//        System.out.println(Arrays.toString(smallerElementsToRight(new int[]{3,4,9,6,1})));
//        System.out.println(findAnagramIndices("ab", "abxaba"));
//        System.out.println(findAnagramIndices("df", "fddfghhhhfddf"));


        String[] s = new String[]{"code", "edoc", "da", "d"};
        palindromePairs(s);

//        System.out.println(checkPalindrome("codeedoc"));



    }

}// End of class
