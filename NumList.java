package com.kashika.p2;

public interface NumList {
    int size();
    int capacity();
    void add(double value);
    void insert(int i, double value);
    void remove(int i);
    boolean contains(double value);
    double lookup(int i);
    boolean equals(NumList otherList);
    void removeDuplicates();
    boolean isSorted();
    void reverse();
    NumList union(NumList list1, NumList list2);
    String toString();
}
