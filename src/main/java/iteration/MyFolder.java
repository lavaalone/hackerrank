package iteration;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class MyFolder<T, U> implements Folder<T, U>
{
    @Override
    public U fold(U u, Queue<T> ts, Function2<T, U, U> fun) {
        if(u == null || ts == null || fun == null)
            throw new IllegalArgumentException();

        if (ts.isEmpty()) {
            return u;
        }

//        //get an iterator first instead of forEach loop, because poll() is modifying underlying collection/queue
//        Iterator<T> iter = ts.iterator();
//        while (iter.hasNext()) { //loop over the items
//            u = fun.apply( ts.poll() , u);
//        }

        for (T t : ts) {
            u = fun.apply(t, u);

        }

        return u;
    }


    public static void main(String[] args) {
        Folder<Integer,Integer> folder = new MyFolder<Integer, Integer>();

        Queue<Integer> q = new LinkedList<Integer>();

        for(int lop =0; lop < 1000000; lop++ ){ //add some values in the Queue
            q.add(lop);
        }
//        Integer result = folder.fold(0, q, new Function2<Integer, Integer, Integer>() {
//            public Integer apply(Integer val1, Integer val2) {
//                return val1 + val2;
//            }
//        });

        Integer r = folder.fold(0, q, (a, b) -> a + b);

        System.out.println("Result: " + r);
    }
}