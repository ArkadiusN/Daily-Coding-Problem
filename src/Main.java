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

    static void productAllOtherElm(int[] arr){
        int[] prefix = new int[arr.length];
        int[] suffix = new int[arr.length];
        int [] result = new int[arr.length];
        prefix[0] = 1;
        suffix[arr.length-1] = 1;

        System.out.println(arr.length);

        for (int i = 0; i < arr.length-1; i++) {
            prefix[i+1] = arr[i] * prefix[i];

        }
        System.out.println(Arrays.toString(prefix));











        for (int i = arr.length-1; i > 0 ; i--) {
            suffix[i-1] = arr[i] * suffix[i];
        }



        for (int i = 0; i < arr.length; i++) {
            result[i] = prefix[i] * suffix[i];
        }

        System.out.println(Arrays.toString(suffix));

        System.out.println(Arrays.toString(result));



    }





    public static void main(String[] args) {
        productAllOtherElm(new int []{1,2,3,4,5});

    }

}// End of class
