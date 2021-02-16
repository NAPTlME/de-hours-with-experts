package com.labs1904;

import java.util.Arrays;
import java.util.Collections;

public class NextBiggestNumber {

    public static void main(String[] args) {
        Integer input = Integer.parseInt(args[0]);
        Integer nextBiggestNumber = getNextBiggestNumber(input);
        System.out.println("Input: " + input);
        System.out.println("Next biggest number: " + nextBiggestNumber);
    }

    public static Integer[] convertIntToArray(Integer i) {
        // First convert to string
        String temp = Integer.toString(i);
        // Then split string into array
        String[] tempArr = temp.split("");
        // Then iterate over string items and pass into int array
        Integer[] intArr = new Integer[tempArr.length];
        for (int j = 0; j < tempArr.length; j++){
            intArr[j] = Integer.parseInt(tempArr[j]);
        }
        return intArr;
    }

    public static boolean isArraySorted(Integer[] intArr){
        for(int i = 1; i < intArr.length; i++){
            if(intArr[i-1] < intArr[i]){
                return false;
            }
        }
        return true;
    }

    public static Integer[] swapIntegerArrayValues(Integer[] intArr, int swapA, int swapB){
        // note that swapA and swapB are indices of the values being exchanged
        Integer[] temp = Arrays.copyOfRange(intArr, 0, intArr.length);
        intArr[swapA] = intArr[swapB];
        intArr[swapB] = temp[swapA];
        return intArr;
    }

    public static String convertIntArrayToString (Integer[] intArr){
        String[] temp = new String[intArr.length];
        for (int i = 0; i < intArr.length; i++){
            temp[i] = intArr[i].toString();
        }
        return String.join("", temp);
    }

    public static int getNextBiggestNumber(Integer i) {
        int returnVal = -1;
        Integer[] intArr = convertIntToArray(i);
        int n = intArr.length;

        for (int j = n-1; j > 0; j--){
            if(intArr[j-1] < intArr[j]){
                // find index of smallest value that is greater than intArr[j-1]
                int nextLargestIndex = -1;
                for (int k = j; k < n; k++){
                    if (intArr[k] > intArr[j-1] && (nextLargestIndex == -1 || intArr[k] < intArr[nextLargestIndex])){
                        nextLargestIndex = k;
                    }
                }
                // now swap nextLargestIndex with j-1 then sort j:n (asc)
                intArr = swapIntegerArrayValues(intArr, j-1, nextLargestIndex);
                Arrays.sort(intArr, j, n);
                returnVal = Integer.parseInt(convertIntArrayToString(intArr));
                break;
            }
        }
        return returnVal;
    }
}
