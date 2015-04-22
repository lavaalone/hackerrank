package com.hackerrank.app;

/**
 * Created by thinhnguyen on 4/20/15.
 */
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.Scanner;
//import java.util.Stack;

interface Queue
{
    /**
     * Add an item in to the queue.
     *
     * @param item The item to add
     * @throws FullException when the queue is full if it is of a fixed
     * size.
     */
    void add(String item) throws FullException;

    /**
     * Remove an item from the queue
     *
     * @return The consumed item, or null if the queue is empty
     */
    String remove();

    /**
     * The current size of the queue
     *
     * @return The current size of the queue
     */
    int size();

    /**
     * Exception thrown when a fixed size queue is full.
     */
    class FullException extends RuntimeException
    {
        private static final long serialVersionUID = -2991083849502081225L;
        private final int capacity;

        FullException(final int capacity)
        {
            super("Queue has reached maximum capacity of " + capacity);
            this.capacity = capacity;
        }

        public int getCapacity()
        {
            return this.capacity;
        }
    }
}


public class Solution2
{
    public static String test(String t)
    {
        Integer idx = Integer.valueOf(t);
        switch(idx) {
            case 1: new QueueTest().testPutAndConsume(); break;
            case 2: new QueueTest().testPutAndConsumeMultiple(); break;
            case 3: try {
                new QueueTest().testPutFull();
                throw new RuntimeException();
            } catch (Queue.FullException e) {
                // Nothing wrong here, good to go
            }
                break;
            case 4: new QueueTest().testConsumeEmpty(); break;
            case 5: new QueueTest().testSize(); break;
            case 6: new QueuePerformanceTest().testPerformance(); break;
            default: throw new RuntimeException("Unknown test " + t);
        }
        return t;
    }

//    public static void main(String[] args) throws IOException{
////        Scanner in = new Scanner(System.in);
////        final String fileName = System.getenv("OUTPUT_PATH");
////        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
////        bw.write(test(in.nextLine()));
////        bw.newLine();
////        bw.close();
//        for (int i = 1; i <= 6; i++) {
//            test("" + i);
//        }
//    }
}

class QueueImpl implements Queue
{
    private int _size;
    private java.util.LinkedList<String> _list;
    QueueImpl(final int size)
    {
        _list = new LinkedList<String>();
        this._size = size;
    }

    public void add(final String item) throws FullException
    {
        if (_list.size() >= _size)
            throw new FullException(_size);
        _list.push(item);
    }

    public String remove()
    {
        if (_list.size() == 0)
            return null;
        return _list.removeLast();
    }

    public int size()
    {
        return _list.size();
    }
}

class Test
{
    static void assertEquals(String s1, String s2) {
        if(!(s1 == null && s2 == null) && !(s1 != null && s1.equals(s2))) {
            System.out.println(s1 + " and " + s2 + " are not equals");
            throw new RuntimeException();
        }
    }
    static void assertEquals(int i1, int i2) {
        if(i1 != i2) {
            System.out.println(i1 + " and " + i2 + " are not equals");
            throw new RuntimeException();
        }
    }
    static void assertNull(Object o) {
        if(o != null) {
            System.out.println(" object is not null");
            throw new RuntimeException();
        }
    }
    static void assertTrue(boolean b) {
        if(!b) {
            System.out.println("is False");
            throw new RuntimeException();
        }
    }
}

class QueueTest extends Test
{

    /**
     * Test that putting an item on the queue returns the same item when it is
     * consumed
     */
    private static Queue createQueue(final int maxSize)
    {
        return new QueueImpl(maxSize);
    }

    //@Test
    public void testPutAndConsume()
    {
        final Queue queue = createQueue(2);
        String value = "" + System.currentTimeMillis();
        queue.add(value);
        assertEquals(value, queue.remove());
        assertNull(queue.remove());
    }

    /**
     * Test that putting and consuming multiple times works, particularly after
     * the queue has cycled through
     */
    //@Test
    public void testPutAndConsumeMultiple()
    {
        final Queue queue = createQueue(4);
        String value = "" + System.currentTimeMillis();
        queue.add(value + "1");
        queue.add(value + "2");
        queue.add(value + "3");

        assertEquals(value + "1", queue.remove());
        assertEquals(value + "2", queue.remove());
        queue.add(value + "4");
        queue.add(value + "5");
        queue.add(value + "6");
        assertEquals(value + "3", queue.remove());
        assertEquals(value + "4", queue.remove());
        assertEquals(value + "5", queue.remove());
        assertEquals(value + "6", queue.remove());
    }

    /**
     * Test that at every stage of the cycle, the queue correctly returns null
     * when it is empty
     */
    //@Test
    public void testConsumeEmpty()
    {
        final Queue queue = createQueue(2);
        String value = "" + System.currentTimeMillis();
        assertNull(queue.remove());
        queue.add(value);
        assertEquals(value, queue.remove());
        assertNull(queue.remove());
        queue.add(value);
        assertEquals(value, queue.remove());
        assertNull(queue.remove());
        queue.add(value);
        assertEquals(value, queue.remove());
        assertNull(queue.remove());
    }

    /**
     * Test that putting an item when full throws the appropriate exception
     */
    //@Test(expected = FullException.class)
    public void testPutFull()
    {
        final Queue queue = createQueue(1);
        try {
            queue.add("test1");
        } catch (Exception e) {
            // Must have no exception here
            // If it reaches here, say bye
            throw new RuntimeException();
        }

        queue.add("test2");
    }

    /**
     * Test that the size method of the queue works, at all stages of its cycle
     */
    //@Test
    public void testSize()
    {
        final Queue queue = createQueue(3);
        assertEquals(0, queue.size());
        queue.add("test");
        assertEquals(1, queue.size());
        queue.add("test");
        assertEquals(2, queue.size());
        queue.add("test");
        assertEquals(3, queue.size());
        queue.remove();
        assertEquals(2, queue.size());
        queue.add("test");
        assertEquals(3, queue.size());
        queue.remove();
        assertEquals(2, queue.size());
        queue.remove();
        assertEquals(1, queue.size());
        queue.remove();
        assertEquals(0, queue.size());
    }
}

class QueuePerformanceTest extends Test
{
    /**
     * Performance test, fails if it takes longer than 3 seconds to run
     */
    //@Test(timeout = 20000)
    public void testPerformance()
    {
        final Queue opQueue = new QueueImpl(1);
        String value = "" + System.currentTimeMillis();
        opQueue.add(value);
        assertEquals(value, opQueue.remove());
        assertNull(opQueue.remove());

        final long start = System.currentTimeMillis();
        final Queue queue = new QueueImpl(1000000);
        addElements(queue, 50000);
        for (int i = 0; i < 100; i++)
        {
            addElements(queue, 499999);
            removeElements(queue, 499999);
        }
        removeElements(queue, 50000);
        final long time = System.currentTimeMillis() - start;
        System.out.println("Total time: " + time);
        assertTrue(time < 3000);
    }

    private static void addElements(final Queue queue, final int number)
    {
        for (int i = 0; i < number; i++)
        {
            queue.add("item");
        }
    }

    private static void removeElements(final Queue queue, final int number)
    {
        for (int i = 0; i < number; i++)
        {
            queue.remove();
        }
    }


}
