/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insertionsort;

/**
 *
 * @author onder
 */
public class Insertionsort {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int arr[] = {24, 4, 1, 15, 1, 1, 6, 26, 34, 67, 456, 34, 62, 2, 561, 4, 21, 141, 123, 44, 65};
        arr = InsertionSort(arr, arr.length - 1);
        for (int i = 0; i < arr.length - 1; i++) {
            System.out.println(arr[i]);

        }

    }

    public static int[] InsertionSort(int[] array, int n) {
        int i;
        if (n > 1) {
            InsertionSort(array, n - 1);
        } else {
            int k = array[n];
            i = n - 1;
            while (i >= 0 && array[i] > k) {
                array[i + 1] = array[i];
                i = i - 1;
            }
            array[i + 1] = k;
        }
        return array;
    }
    
}
