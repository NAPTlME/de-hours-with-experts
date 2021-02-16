#!/usr/bin/python3
import sys

def main():
    next_biggest_number(sys.argv[1])


def isArraySorted(arr, desc = True):
    n = len(arr)
    if n == 1 or n == 0:
        return True
    
    if desc:
        return arr[0] >= arr[1] and isArraySorted(arr[1:], desc)
    else:
        return arr[0] <= arr[1] and isArraySorted(arr[1:], desc)

def next_biggest_number(num):
    # convert to array
    numArray = [int(i) for i in str(num)]
    n = len(numArray)
    returnVal = -1 # default to -1
    
    # iterate from length 2 to n looking at arrangement of right-most digits, 
    # if not in descending order, then rearrange making n-j digit the first and then subsequent digits in ascending order
    for i in range(2, n+1):
        array1 = numArray[0:(n-i)]
        array2 = numArray[(n-i):n] # array2 will always be at least of length 2
        if not isArraySorted(array2):
            # because we are iterating from the right side, the left most digit should be the one to swap with
            # we can then choose an integer that is the smallest of those greater than this value
            leftMostValue = array2[0]
            itemsGreaterThanLMV_min = min([j for j in array2 if j > leftMostValue])
            # looping and removing only the first of this value to avoid the case where there might be 2 of the same digit
            for j in range(1, len(array2)):
                if array2[j] > leftMostValue and array2[j] == itemsGreaterThanLMV_min:
                    leftMostValue = array2[j]
                    array2.pop(j)
                    array2.sort() # sort remaining values in ascending order
                    break
            numArray = array1 + [leftMostValue] + array2
            returnVal = int("".join([str(j) for j in numArray]))
            break
            
    return returnVal

if __name__ == "__main__":
    main()



