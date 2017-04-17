package ru.ncedu.java.tasks;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayVectorImpl implements ArrayVector {
    double[] arr;

    @Override
    public void set(double... elements) {
        arr = elements;
    }

    @Override
    public double[] get() {
        return arr;
    }

    @Override
    public ArrayVector clone() {
        ArrayVector arr2 = new ArrayVectorImpl();
        arr2.set(get().clone());
        return arr2;
    }

    @Override
    public int getSize() {
        return arr.length;
    }

    @Override
    public void set(int index, double value) {
        if((index >= 0) && ( index < arr.length )) {
            arr[index] = value ;
        } else if( index >= arr.length ) {
            arr = Arrays.copyOf(arr, index + 1);
            arr[index] = value ;
        }
    }

    @Override
    public double get(int index) throws ArrayIndexOutOfBoundsException {
        if( (index < 0) && (index > arr.length)){
            throw new ArrayIndexOutOfBoundsException();
        }
        return arr[index];
    }

    @Override
    public double getMax() {
        double max = arr[0];
        for (double i : arr){
            if(i > max){
                max = i;
            }
        }
        return max;
    }

    @Override
    public double getMin() {
        double min = arr[0];
        for (double i : arr){
            if(i < min){
                min = i;
            }
        }
        return min;
    }

    @Override
    public void sortAscending() {
        Arrays.sort(arr);
    }

    @Override
    public void mult(double factor) {
        for(int i = 0; i < arr.length; ++i){
            arr[i] = arr[i] * factor;
        }
    }

    @Override
    public ArrayVector sum(ArrayVector anotherVector) {
        if(anotherVector.getSize() > this.getSize()){
            for (int i = 0; i < this.getSize(); ++i){
                this.arr[i] = this.arr[i] + anotherVector.get(i);
            }
        } else {
            for (int i = 0; i < anotherVector.getSize(); ++i){
                this.arr[i] = this.arr[i] + anotherVector.get(i);
            }
        }
        return this;
    }

    @Override
    public double scalarMult(ArrayVector anotherVector) {
        double mult = 0;
        if(arr.length < anotherVector.getSize()){
            for (int i = 0 ; i < arr.length ; ++i){
                mult += arr[i] * anotherVector.get()[i];
            }
        } else {
            for(int i = 0 ; i < anotherVector.getSize() ; ++i){
                mult += arr[i] * anotherVector.get()[i];
            }
        }
        return mult;
    }

    @Override
    public double getNorm() {
        double norm = Math.sqrt(scalarMult( clone()));
        return norm;
    }

    public static void main(String[] Args){
        ArrayVector a = new ArrayVectorImpl();
        a.set(1,2,3,4,5,6,7);
    }
}
