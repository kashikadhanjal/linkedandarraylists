package com.kashika.p2;

import org.junit.Assert;
import org.junit.Test;

public class NumLinkedListTest {
	NumLinkedList test = new NumLinkedList();
	@Test
    public void testSize(){
        Assert.assertEquals(0, test.size());
        Assert.assertNotEquals(10, test.size());
    }
    
    @Test
    public void testCapacity(){
        Assert.assertEquals(50000, test.capacity());
        Assert.assertNotEquals(0, test.capacity());
    }
    
    @Test
    public void testAdd(){
        test.add(1.0);
        test.add(2.0);
        test.add(3.0);
        test.add(4.0);
        test.add(5.0);
        test.add(6.0);
        Assert.assertEquals("1.0 2.0 3.0 4.0 5.0 6.0", test.toString());
        Assert.assertNotEquals("1.0 2.0 3.0 4.0 5.0", test.toString());
    }
    
    @Test
    public void testInsert(){
        NumLinkedList test2 = new NumLinkedList();
    	test2.insert(1, 1.5);
        test2.insert(7, 7.0);
        Assert.assertTrue(test2.lookup(0)== 1.5);
        Assert.assertNotEquals("1.0 2.0 3.0 4.0 5.0", test.toString());
    }
    
    @Test
    public void testRemove(){
        test.add(1.0);
        test.add(2.0);
        test.add(3.0);
        test.add(4.0);
        test.add(5.0);
        test.add(6.0);
        test.insert(1, 1.5);
        test.insert(7, 7.0);
        test.remove(1);
        test.remove(8);
        Assert.assertEquals("1.0 2.0 3.0 4.0 5.0 6.0 7.0", test.toString());
        Assert.assertNotEquals("1.0 2.0 3.0 4.0 5.0", test.toString());
    }

    @Test
    public void testContains(){
        test.add(1.0);
        test.add(2.0);
        test.add(3.0);
        test.add(4.0);
        test.add(5.0);
        test.add(6.0);
        test.insert(1, 1.5);
        test.insert(7, 7.0);
        test.remove(1);
        test.remove(8);
        Boolean find = test.contains(3.0);
        Assert.assertEquals("true", find.toString());
        Assert.assertNotEquals("false", find.toString());
    }

    @Test
    public void testLookup(){
        test.add(1.0);
        test.add(2.0);
        test.add(3.0);
        test.add(4.0);
        test.add(5.0);
        test.add(6.0);
        test.insert(1, 1.5);
        test.insert(7, 7.0);
        test.remove(1);
        test.remove(8);
        Double three = test.lookup(2);
        Assert.assertEquals("3.0", three.toString());
        Assert.assertNotEquals("2.0", three.toString());
    }

    @Test
    public void testEquals(){
        test.add(1.0);
        test.add(2.0);
        test.add(3.0);
        test.add(4.0);
        test.add(5.0);
        test.add(6.0);
        test.insert(1, 1.5);
        test.insert(7, 7.0);
        test.remove(1);
        test.remove(8);
        NumArrayList test2 = new NumArrayList(8);
        test2.add(1.0);
        test2.add(2.0);
        test2.add(3.0);
        test2.add(4.0);
        test2.add(5.0);
        test2.add(6.0);
        test2.add(7.0);
        test2.add(1.0);
        Boolean check = test.equals(test2);
        Assert.assertEquals("false", check.toString());
        Assert.assertNotEquals("true", check.toString());
        test2.remove(7);
        check = test.equals(test2);
        Assert.assertEquals("true", check.toString());
        Assert.assertNotEquals("false", check.toString());
    }

    @Test
    public void testRemoveDuplicates(){
        test.add(1.0);
        test.add(2.0);
        test.add(3.0);
        test.add(1.0);
        test.add(3.0);
        test.add(4.0);
        test.removeDuplicates();
        Assert.assertEquals("1.0 2.0 3.0 4.0", test.toString());
        Assert.assertNotEquals("1.0 2.0 3.0 4.0 5.0 6.0 7.0 1.0 3.0 4.0", test.toString());
    }

    @Test
    public void testtoString(){
        test.add(1.0);
        test.add(2.0);
        test.add(3.0);
        test.add(4.0);
        test.add(5.0);
        test.add(6.0);
        test.insert(1, 1.5);
        test.insert(7, 7.0);
        test.remove(1);
        test.remove(8);
        Assert.assertEquals("1.0 2.0 3.0 4.0 5.0 6.0 7.0", test.toString());
        Assert.assertNotEquals("1.0 2.0 3.0 4.0 5.0 6.0 7.0 ", test.toString());
    }
    
    @Test
    public void testisSorted(){
        test.add(1.0);
        test.add(2.0);
        test.add(3.0);
        test.add(4.0);
        test.add(5.0);
        test.add(6.0);
        Boolean check = test.isSorted();
        Assert.assertEquals("true", check.toString());
        Assert.assertNotEquals("false", check.toString());
        test.insert(1, 8.5);
        check = test.isSorted();
        Assert.assertEquals("false", check.toString());
        Assert.assertNotEquals("true", check.toString());
    }
    
    @Test
    public void testReverse(){
        test.add(1.0);
        test.add(2.0);
        test.add(3.0);
        test.add(4.0);
        test.add(5.0);
        test.add(6.0);
        test.reverse();
        Assert.assertEquals("6.0 5.0 4.0 3.0 2.0 1.0", test.toString());
        Assert.assertNotEquals("1.0 2.0 3.0 4.0 5.0 6.0 7.0", test.toString());
    }
    
    @Test
    public void testunion(){
    	test.add(1.0);
        test.add(2.0);
        test.add(3.0);
        test.add(4.0);
        test.add(5.0);
        test.add(6.0);
        NumArrayList test2 = new NumArrayList(8);
        test2.add(2.0);
        test2.add(3.5);
        test2.add(4.0);
        test2.add(4.5);
        test2.add(7.0);
        NumList l = test.union(test, test2);
        Assert.assertEquals("1.0 2.0 3.0 3.5 4.0 4.5 5.0 6.0 7.0", l.toString());
        Assert.assertNotEquals("1.0 2.0 3.5 3.0 4.0 5.0 4.5 5.0 6.0 7.0", l.toString());
    }
}
