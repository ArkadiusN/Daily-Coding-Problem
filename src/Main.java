import java.util.Arrays;

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

    public static void main(String[] args) {
//      getProduct(new int[]{1,2,3,4,5});

        locateSmallestWindow(new int[]{3,7,5,6,9});
        locateSmallestWindow(new int[]{3,10,5,7,9});
    }

}// End of class
