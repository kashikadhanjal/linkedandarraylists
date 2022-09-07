package com.kashika.p2;

public class NumArrayList implements NumList
{
    private double[] list;
    private int size;
    private int capacity;
    private boolean sorted;
    public NumArrayList()
    {
        list = new double[0]; //initiates empty list of length = 0
        size = 0; //start with 0 elements
        capacity = 0; //list with 0 spaces
        sorted = true;
    }
    public NumArrayList(int length){
        list = new double[length]; //initiates empty list of length = length 
        size = 0; //still have 0 elements
        capacity = length; //list now has length amount capacity
        sorted = true;
        
    }
   
    public int size(){
        return size;
    }
    public int capacity(){
        return capacity;
    } 
    public void add(double value){
        if(size+1<=capacity){ //condition where array is big enough to accept another element
            list[size]=value;
            size++; //added another element increment size
        }
        else { // o(n)
            double[] newlist = new double[capacity+1]; //make a new list one bigger
            for(int i=0; i < capacity; i++) //iterate through copying every element to new list
            {
                newlist[i]=list[i];
            }
            newlist[size]=value; //add element to last index
            list = newlist; //make new list the lst 
            capacity++; //iterate capacity
            size++; //iterate size
        }
        sorted = isSorted(); //checks if still sorted after adding new element
    }
    public void insert(int i, double value){
        if(i<size && i<capacity && size+1 <= capacity){ //when adding the element would be within range of size and capacity
            double[] newlist = new double[capacity]; // make new array 
            for(int j = 0; j < i; j++) //copy over every element before index
            {
                newlist[j]=list[j];
            }
            newlist[i] = value; //assign index i value 
            for(int k = i+1; k<size+1; k++){ //copy over every element from the current lists index i into new list
                newlist[k]=list[k-1];
            }
            size++; //iterate size for element added 
            list = newlist; //reassign so list points to the inserted version
        }
        else if(size+1>capacity && i<size){ //case when inserting in range of size would push array out of bounds
            double[] newlist = new double[capacity+1]; //make new array with added capacity
            for(int j = 0; j < i; j++) //copy over all elements before i
            {
                newlist[j]=list[j];
            }
            newlist[i] = value; //insert desired i value
            for(int k = i+1; k<size+1; k++){ //copy over all elements from i to end from old list
                newlist[k]=list[k-1];
            }
            size++; //increase size
            capacity++; //increase capacity
            list = newlist; //point to new list 
        }
        else{
            add(value); //adding to end 
        }
        sorted = isSorted(); //checks if still sorted after inserting new element
    }
    public void remove(int i){
        if(i < size){ //only happens if i is within size 
            double[] newlist = new double[capacity]; //same capacity only size changes 
            for(int j = 0; j < i; j++) //copy over all elements until i 
            {
                newlist[j]=list[j];
            }
            for(int k = i+1; k < size; k++){//skip over i and copy over all other elements after into new list 
                newlist[k-1]=list[k];
            }  
            size--; //decrement size 
            list = newlist; //point to new list 
        }
        sorted = isSorted(); //checks if still sorted after removing an element
    }
    public boolean contains(double value){
        boolean holder = false; //initial condition set to false
        for(int n = 0; n < size; n++)//iterate through list
        {
            if(list[n] == value){ //if list has value set to true 
                holder = true;
            }
        }
        return holder; //return value after iteration
    }
    public double lookup(int i){
        if (size<i){ //if not within size 
            throw new NullPointerException();
        }
        else{ //return the element 
            return list[i];
        }
    }
    public boolean equals(NumList otherList){
        boolean placeholder = true; //set to true initially 
        for(int i = 0; i < size; i++){ //iterate through list 
            if(list[i] != otherList.lookup(i)){ // values from lists at i are not equal 
                placeholder = false; //set to false
            }
        }
        if(otherList.size() > size) //also if other list has more elements after set to false
        {
            placeholder = false;
        }
        return placeholder; //return boolean
    }
    public void removeDuplicates()
    {
        NumArrayList noduplicates = new NumArrayList(size); //make a new numarraylist of capacity size
        int count = 0; //establish duplicate counting variable to 0
        for(int i = 0; i < size; i++) //iterate through the list 
        {
            if(noduplicates.contains(list[i])==false){ //if the noduplicates list does not have value at list index i it adds
                noduplicates.add(list[i]);
            }
            else{ //else the count of number of duplicates iterates 
                count++;
            }
        }
        list = noduplicates.list; //list now points to the list of the noduplicates numarraylist
        size = size-count; // the new size is the old size minus the number of duplicates counted
    }
    public String toString(){
        if(size == 0){ // return empty string if empty 
            return " ";
        }
        else{
            String tostring = ""; //establish string with no characters
            for(int n = 0; n < size; n++){ //iterate through list and add every value and a space
                tostring += list[n]; 
                tostring += " ";
            }
            tostring = tostring.trim(); //trim off extra white space at end 
            return tostring; //return the string 
        }
    }
    public boolean isSorted() {
    	for(int n = 1; n < size; n++) //iterate through list
    	{
    		if(list[n]<list[n-1]) { //if element before is greater than current 
    			sorted = false; //set sorted to false
    		}
    	}
    	return sorted; //return sorted
    }
    public void reverse() {
    	for(int n = 0; n < size/2; n++) {
    		double value = list[n];
    		list[n] = list[size-n-1];
    		list[size-n-1] = value;
    	}
    }
    public NumList union(NumList list1, NumList list2) {
    	NumLinkedList unionized = new NumLinkedList();
    	int counter1 = 0; //inititalize counters for each list
    	int counter2 = 0;
    	int size1 = list1.size(); //save sizes for each list 
    	int size2 = list2.size();
    	if(list1.isSorted() == false || list2.isSorted() == false) 
    	{
    		sorted = false;
    	}
    	while(counter1 < size1 && counter2 < size2) { //while one of them is within range
    		double first = list1.lookup(counter1); //pull up first list element at index counter1
    		double second = list2.lookup(counter2); //pull up second list element at index counter2
    		if(first<second) { //if second greater than first 
    			unionized.add(first); //add first to list 
    			counter1++; //iterate counter1 so next element in first list will be considered 
    		}
    		else if(second<first ) { //if first greater than second
    			unionized.add(second); //add second to list 
    			counter2++; //iterate counter2 so that next element in second list will be considered
    		}
    		else //when first and second equal to each other
    		{
    			unionized.add(first); //add only one
    			counter1++; //iterate both 
    			counter2++;
    		}
    	}
    	while(counter1 < list1.size()) { //might have leftovers in either list so iterate through both in case and add 
    		unionized.add(list1.lookup(counter1));
    		counter1++;
    	}
    	while(counter2 < list2.size()) {
    		unionized.add(list2.lookup(counter2));
    		counter2++;
    	}
    	unionized.removeDuplicates(); //remove duplicates 
    	return unionized; //return list 
    }
    public static void main(String[] args)
    {
        NumArrayList n = new NumArrayList(20);
        n.add(5.0);
        n.add(6.0);
        n.add(7.0);
        n.insert(1, 4.3);
        n.remove(2);
        boolean test = n.contains(5.0);
        System.out.print(test);
        String s = n.toString();
        System.out.println(s);
        NumArrayList checkequality = new NumArrayList(15);
        checkequality.add(5.0);
        checkequality.add(4.3);
        checkequality.add(7.0);
        boolean check = n.equals(checkequality);
        System.out.println(check);
        n.add(6.0);
        n.add(2.0);
        n.add(7.0);
        n.add(5.0);
        n.add(8.0);
        n.removeDuplicates();
        String test2 = n.toString();
        System.out.println(test2);
        n.insert(9, 4.5);
        System.out.println(n.toString());
        Boolean check2 = n.isSorted();
        System.out.println(check2.toString());
        NumArrayList n2 = new NumArrayList(8);
        n2.add(2.0);
        n2.add(3.5);
        n2.add(4.0);
        n2.add(4.5);
        n2.add(7.0);
        NumList newList = n.union(n, n2);
        System.out.println(newList.toString());
        newList.reverse();
        System.out.println(newList.toString());



    }
}