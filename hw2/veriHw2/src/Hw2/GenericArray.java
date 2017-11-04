/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hw2;

import java.util.*;

/**
 * This is Generic array. This keep the values witch only array
 *
 * @author onder
 */
@SuppressWarnings("unchecked")
public class GenericArray<T> {

    private Object arr[];
    private int count;
    private int capasity;

    public GenericArray() {
        count = 0;
        capasity = 5;
        arr = new Object[capasity];
    }

    public GenericArray(GenericArray<T> other) {
        count = other.count;
        capasity = other.capasity;
        arr = new Object[capasity];
        for (int i = 0; i < other.count; i++) {
            arr[i] = other.arr[i];

        }
    }

    public GenericArray<T> clone() {
        GenericArray<T> temp;
        temp = new GenericArray<>(this);
        return temp;
        
    }
    public void clear(){
        arr=new Object[capasity];
        count=0;
    }

    public Object getarr() {
        return this.arr;

    }

    public int size() {
        return this.count;
    }

    public void add(T value) {
        if (!isfull()) {
            arr[count++] = value;
        } else {
            setcapasity(2);
            arr[count++] = value;
        }

    }

    public void removelast() {
        count--;
        setcapasity(1);
    }

    public T get(int index) {
        return (T) this.arr[index];
    }
    public void remove(T obj){
      
        for (int i = 0; i < size(); i++) {
            if(obj.equals(arr[i]))
            {
                for (int j = i+1; j < size(); j++,i++) {
                   arr[i]=arr[j];
                }
                
            }
            
        }
        count--;
    }
    public void setvalue(int index, T value) {
        if (index >= 0 && index < size()) {
            arr[index] = value;
        }

    }

    private boolean isfull() {
        if (capasity == count) {
            return true;
        }
        return false;
    }

    public void setcapasity(int mult) {
        capasity *= 2;
        Object newarr[] = new Object[capasity];
        for (int i = 0; i < count; i++) {
            newarr[i] = arr[i];
        }
        arr = newarr;
    }

}
