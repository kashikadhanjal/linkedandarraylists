package com.kashika.p2;

public class NumLinkedList implements NumList{
	private Node lst_head;
    private int size;
    private int capacity;
    private boolean sorted;
    private class Node { //private helper node class
    	private double data;
        private Node next;
        public Node(double data) {
        	this.data = data;
        	this.next = null; //singly linked list
        }
    }
    public NumLinkedList()
    {
        lst_head = null; //null list to begin with 
        size = 0; //start with 0 elements
        capacity = 50000; //infinitely large list
        sorted = true; //starts out null so true
    }
   
    public int size(){
        return size;
    }
    public int capacity(){
        return capacity;
    } 
    public void add(double value){
    	 	capacity++;
    		Node p = lst_head; //set equal to lstPhead initially 
    		if(lst_head == null) { //edge case where list is empty to begin with
    			lst_head = new Node(value);
    			size++;
    		}
    		else {
    			while(p.next != null)//iterate till you get to when the next is null
    			{
    				p = p.next;
    			}
    			p.next = new Node(value); //set next to the new node with entered value
    			size++;
    		}
    		sorted = isSorted(); //call sorted flag to decide if sorted after addition
           
    }
    public void insert(int i, double value){
        int counter = 0;
        Node holder = lst_head; //set temp variable to head
        if(i > size) { //if past size just add to end
        	add(value);
        }
        else if(i ==0) { //if inserting to the front, edge case
        	Node first = new Node(value);
        	first.next = lst_head;
        	first = lst_head; //reassign head to point to new node
        }
        else {
        	while(counter < i-1) { //iterate till calling .next should be new node
        			holder = holder.next;
        			counter++;
        		}
        	Node temp = holder.next; //save the current .next in a temp
        	Node newNode = new Node(value); //make a new node with value
        	holder.next = newNode; //make .next new node
        	newNode.next = temp; //set newnodes next to the previous next so order is not broken
        	size++; //iterate size and capacity
        	capacity++;
        	}
        sorted = isSorted(); //updated sorted flag after adding new element 
    }
    public void remove(int i){
        if(i==0) { //edge case for removing first element
        	Node temp = lst_head.next;  //save .next of lst_head
        	lst_head = temp; //make it the new lst_head
        	size--;
        }
        else if(i < size){ //if less than sixe then proceed
           int counter = 0;
           Node holder = lst_head;
           while(counter < i-1) { //iterate to node where .next is equal to node that must be removed
        	   holder = holder.next;
        	   counter++;
           }
           Node temp = holder.next.next; //save .next.next 
           holder.next = temp; //set .next to .next.next so skiped over
           size--;
        }
    	sorted = isSorted(); //update sorted  after removing element 
    }
    public boolean contains(double value){
        boolean holder = false;//initial condition set to false
        Node p = lst_head;
        while(p != null)//iterate through list
        {
            if(p.data == value){ //if list has value set to true 
                return true;
            }
            else {
            	p = p.next;
            }
        }
        return holder; //return value after iteration
    }
    public double lookup(int i){
        if (size<=i){ //if not within size 
            System.out.println("Out of bounds you really tried it huh");
            return -1; //rep exit status 
        }
        else{ //return the element 
            Node holder = lst_head;
            int counter = 0;
            while(counter < i) {
            	holder = holder.next;
            	counter++;
            }
            return holder.data;
        }
    }
    public boolean equals(NumList otherList){
        boolean placeholder = true; //set to true initially 
        Node holder = lst_head;
        if(otherList.size() != size) //case for where sizes are different so inherently unequal
        {
        	return false;
        }
        else {
        	int i = 0;
        	while(holder.next != null){ //iterate through list 
        		if( holder.data != otherList.lookup(i)){ // values from lists at i are not equal 
        			return false; //return false - terminate loop
        		}
        		holder = holder.next;
        		i++;
        	}
        	return placeholder; //return boolean
        }
    }
    public void removeDuplicates()
    {
    	NumLinkedList noduplicates = new NumLinkedList(); //make a new NumLinkedList of capacity size
        Node holder = lst_head; 
        int count = 0; //establish duplicate counting variable to 0
        while(holder != null) //iterate through the list 
        {
            if(noduplicates.size() == 0) { //if empty list, just add first element
            	noduplicates.add(holder.data);
            }
            else if(noduplicates.contains(holder.data)==false){ //if the noduplicates list does not have value it adds
                noduplicates.add(holder.data);
            }
            else{ //else the count of number of duplicates iterates 
                count++;
            }
            holder = holder.next;
        }
        lst_head = noduplicates.lst_head; //lst_head now points to the head of the noduplicates NumLinkedList
        size = size-count; // the new size is the old size minus the number of duplicates counted 
    }
    public String toString(){
        if(size == 0){ // return empty string if empty 
            return " ";
        }
        else{
            String tostring = ""; //establish string with no characters
            Node holder = lst_head; //initialize node to head
            while(holder != null){ //iterate through list and add every value and a space
                tostring += holder.data; 
                tostring += " ";
                holder = holder.next;
            }
            tostring = tostring.trim(); //trim off extra white space at end 
            return tostring; //return the string 
        }
    }
    public boolean isSorted() {
    	Node holder = lst_head; //initialize node to head
    	if(holder == null) {
    		return sorted;
    	}
    	else {
    		while(holder.next != null) { //iterate while next still exists
    			if(holder.data >= holder.next.data) { //check that current isnt greater then next
    				sorted = false; //if is set flag to false
    			}
    			holder = holder.next;
    		}
    	return sorted; //returns flag 
    	}
    }
    public void reverse() {
    	Node current = lst_head; // set the current node pointer to head
    	Node prev = null; //prev and next are null currently 
    	Node next = null;
    	while(current != null) { //while there exists a node 
    		next = current.next; //next is equal to whatever is currents next 
    		current.next = prev; //currents next is equal to whatever prev is 
    		prev = current; //move prev to current for next iteration
    		current = next; //set current to next so can move to next node
    	}
    	lst_head = prev; //change the list head to point to prev after since it has been reversed 
    }
    public NumList union(NumList list1, NumList list2) {
    	NumLinkedList unionized = new NumLinkedList();
    	int counter1 = 0; //inititalize counters for each list
    	int counter2 = 0;
    	int size1 = list1.size(); //save sizes for each list 
    	int size2 = list2.size();
    	if(list1.isSorted() == false || list2.isSorted() == false) { //if one of the lists isn't sorted then unionized is also not sorted
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
        NumLinkedList n = new NumLinkedList();
        n.add(5.0);
        n.add(6.0);
        n.add(7.0);
        n.insert(1, 4.3);
        n.remove(2);
        boolean test = n.contains(5.0);
        System.out.print(test);
        String s = n.toString();
        System.out.println(s);
        NumLinkedList checkequality = new NumLinkedList();
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
