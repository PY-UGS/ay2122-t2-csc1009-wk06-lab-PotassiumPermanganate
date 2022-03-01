package com.T6;

import java.util.*;
import java.util.stream.IntStream;

public class Listmain {

    public static void addAndSort(LinkedList<Integer> list, int value) {
        System.out.println("Original: " + list.toString());

        for(int i=0; i < list.size(); i++) {
            if (list.get(i) > value && i == 0) {
                list.addFirst(value);
                break;
            } else if (list.get(i) > value) {
                list.add(i-1, value);
                break;
            }else if (i == list.size()-1) {
                list.addLast(value);
            }
        }
        System.out.println("After: " + list.toString());
    }

    public static void addAndSort(LinkedHashMap<Integer, Integer> list, int value) {
        Integer previous = null, tmp;
        boolean FLAG = false;
        System.out.println("Original: " + list.toString());

        // for every entry compare
        for (Map.Entry<Integer, Integer> entry: list.entrySet()) {
            if (!FLAG) {
                if (entry.getValue() > value) {
                    previous = entry.getValue();
                    list.put(entry.getKey(), value);
                    FLAG = true;
                }else if (entry.getKey() == list.size()) {
                   list.put(list.size()+1, value);
                };
            }else{
                // roll to right
                tmp = previous;
                previous = entry.getValue();
                list.put(entry.getKey(), tmp);
                if (entry.getKey() == list.size()) {
                    list.put(list.size()+1, previous);
                }
            }
        }
        System.out.println("After: " + list.toString());
    }

    public static void swapValue(LinkedList<Integer> list, int indexOne, int indexTwo) {
        System.out.println("Original: " + list.toString());
        Integer tmp = list.get(indexOne);
        list.set(indexOne, list.get(indexTwo));
        list.set(indexTwo, tmp);
        System.out.println("After: " + list.toString());
    }

    public static void swapValue(LinkedHashMap<Integer, Integer> list, int indexOne, int indexTwo) {
        Integer tmp;
        System.out.println("Original: " + list.toString());
        tmp = list.get(indexOne);
        list.replace(indexOne, list.get(indexTwo));
        list.replace(indexTwo, tmp);
        System.out.println("After: " + list.toString());
    }

    public static int findValue(LinkedList<Integer> list, int searchVal) {
        for (Integer i: list) {
            if (i == searchVal) {
                return list.indexOf(i);
            }
        }
        return -1;
    }

    public static int findValue(LinkedHashMap<Integer, Integer> list, int searchVal) {
        for (Map.Entry<Integer, Integer> entry: list.entrySet()) {
            if (entry.getValue() == searchVal) {
                return entry.getKey();
            }
        }
        return -1;
    }


    public static void main(String[] args) {

        // for creating the random linked list and linkedhashmap with the
        // same random int stream
        Integer buf;
        LinkedList<Integer> randomll = new LinkedList<>();
        LinkedHashMap<Integer, Integer> randomlh = new LinkedHashMap<>();
        Random random = new Random();
        // random.setSeed(3);
        PrimitiveIterator.OfInt rand = random.ints(1000, 9999).iterator();
        for (int i = 0; i < 500; i++) {
            buf = rand.next();
            randomll.add(buf);
            randomlh.put(i+1, buf);
        }
        int find = rand.nextInt();

        System.out.println("-----------------LinkedList--------------");
        LinkedList<Integer> ll = new LinkedList<Integer>(Arrays.asList(1,3,5,7,9,11));
        addAndSort(ll, 0);
        swapValue(ll, 1, 5);
        System.out.println("Found: " + find + " at: " + findValue(randomll, find));


        System.out.println("-----------------LinkedHashMap-------------");
        LinkedHashMap<Integer, Integer> lh = new LinkedHashMap<>();
        int data[] = {1,3,5,7,9,11};
        for(int i=0; i < data.length; i++) {
            lh.put(i+1, data[i]);
        }
        addAndSort(lh, 15);
        swapValue(lh, 1, 5);
        System.out.println("Found: " + find + " at: " + findValue(randomlh, find));

    }
}
