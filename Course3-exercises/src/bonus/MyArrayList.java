package bonus;

import java.util.Arrays;

// a
public class MyArrayList {
    private int maxIndex;
    private int currIndex;
    private float[] arr;

    public MyArrayList() {
        currIndex = 0;
        maxIndex = 10;
        arr = new float[maxIndex];
    }

    public MyArrayList(int maxIndex) {
        currIndex = 0;
        if (maxIndex <= 0) {
            maxIndex = 1;
        }
        this.maxIndex = maxIndex;
        arr = new float[maxIndex];
    }

    public void add(float value) {
        if (currIndex + 1 > maxIndex) {
            float[] newArr = new float[2 * maxIndex];
            System.arraycopy (arr, 0, newArr, 0, maxIndex);
            maxIndex *= 2;
            arr = newArr;
        }
        arr[currIndex++] = value;
    }

    public boolean contains(float value) {
        for (int i = 0; i < maxIndex; ++i) {
            if (arr[i] == value) {
                return true;
            }
        }
        return false;
    }

    public void remove(int index) {
        if (index >= currIndex) {
            System.out.println("Fail to remove index " + index + "(index out of range)");
            return;
        }
        System.arraycopy (arr, index+1, arr, index, currIndex-index-1);
        --currIndex;
    }

    public float get(int index) {
        if (index < 0 || index >= currIndex) {
            System.out.println("Fail to get index " + index + "(index out of range)");
            return 0;
        }
        return arr[index];
    }

    public int size() {
        return currIndex;
    }

    @Override
    public String toString() {
        if (currIndex == 0) {return "Empty";}
        String str = "MyArrayList{" + "arr=[";
        for (int i = 0; i < currIndex - 1; ++i) {
            str += arr[i] + ", ";
        }
        return  str += arr[currIndex - 1] + "]" + ", size: " + size() + "}";
    }
}