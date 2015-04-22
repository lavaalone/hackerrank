package com.hackerrank.app;

/**
 * Hello world!
 *
 */

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.lang.*;
import java.util.stream.*;

class App
{
    public static void main(String[] args) {
//        System.out.println(solution(new int[][1, 2, 4]));
//        int[] A = new int[100000];
//        for (int i = 0; i < 100000; i++) {
////            int value = Math.abs(new Random().nextInt() % 2);
////            System.out.println(value);
//            A[i] = new Random().nextInt() % 2;
//        }
//
////        int[] A = new int[5];
////        A[0] = 0;
////        A[1] = 1;
////        A[2] = 0;
////        A[3] = 1;
////        A[4] = 1;
        try {
            Solution solution = new Solution();
//            System.out.println(solution.solution2(A));
//            solution.Solution6();
//            int[] A = new int[6];
//            A[0] = 3;
//            A[1] = 5;
//            A[2] = 6;
//            A[3] = 3;
//            A[4] = 3;
//            A[5] = 5;

//            int[] B = new int[3];
//            B[0] = 8;
//            B[1] = 9;
//            B[2] = 10;
//            int[] A = new int[100];
//            for (int i = 0; i < 100; i++) {
//                A[i] = new Random().nextInt();
//            }
//            System.out.println(solution.findArray(A, B));
//            System.out.println(solution.Solution7(A, -1999999999));
//            System.out.println(solution.Solution9(A));

//            StringBuilder sb = new StringBuilder();
//            for (int i = 0; i < 50000; i++) {
//                int rand = new Random().nextInt() % 3;
//                if (rand == 0) {
//                    sb.append("A");
//                } else if (rand == 1) {
//                    sb.append("B");
//                } else {
//                    sb.append("C");
//                }
//            }
//            System.out.println(solution.Solution8(sb.toString()));
            List<String> input = new LinkedList<String>();
            input.add("AC");
            input.add("Aa");
            input.add("AA");
            input.add("ac");
            input.add("ab");
            System.out.println(solution.sortEmailList(input));

        }

        catch (Exception e) {
            e.printStackTrace();
        }

    }



    // missing element - 100% score & performance
    public static int solution1(int[] A) {
        List list = Arrays.stream(A).sorted().boxed().collect(Collectors.toList());
        int current = 0;
        for (Object obj : list) {
            int value = ((Integer)obj).intValue();
            if (value <= 0)
                continue;

            if (value == current || value == (current + 1)) {
                current = value;
            }
            else {
                return current + 1;
            }
        }
        return current + 1;
    }
    // frog  - wrong
//    public static int solution(int X, int[] A) {
//        // write your code in Java SE 8
////        List<Integer> list = new LinkedList<Integer>();
////        for (int i = 0; i < A.length; i++) {
////            list.add(A[i]);
////        }
////        return list.indexOf(X);
//        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
//        for (int i = 0; i < A.length; i++) {
//            map.put(i, A[i]);
//        }
//
//        if (map.containsValue(X)) {
//            for (Map.Entry<Integer, Integer> e : map.entrySet()) {
//                if (e.getValue() == X)
//                    return e.getKey();
//            }
//        }
//
//        return -1;
//    }

    // permutation Check whether array A is a permutation.
//    public static int solution(int[] A) {
//        HashMap<Integer, Boolean> map = new HashMap<Integer, Boolean>();
//        for (int i = 0; i < A.length; i++) {
//            map.put(A[i], true);
//        }
//
//        for (int i = 1; i < A.length + 1; i++) {
//            if (!map.containsKey(i))
//                return 0;
//        }
//        return 1;
//    }

    // frog jump
//    public static int solution(int X, int Y, int D) {
//        // write your code in Java SE 8
//        return (int)Math.ceil((double)(Y-X) / (double)D);
//
//    }

//    public static int solution(int[] A) {
//        // write your code in Java SE 8
//        long total = 0;
//        List<Long> list_total = new LinkedList<Long>();
//        List<Integer> list = new LinkedList<Integer>();
//        for (int i = 0; i < A.length; i++) {
//            list.add(A[i]);
//            total += A[i];
//            list_total.add(total);
//        }
//
//        int min = Integer.MAX_VALUE;
//        for (int i = 1; i < list.size(); i++) {
//            long sum_left  = list_total.get(i - 1);
//            long sum_right = (total - sum_left);
//            if (Math.abs(sum_left - sum_right) < min) {
//                min = (int) Math.abs(sum_left - sum_right);
//            }
//            if (min == 0)
//                return min;
//        }
//        return min;
//    }

    // missing element - wrong
//    public static int solution(int[] A) {
//        // write your code in Java SE 8
////        List<Integer> list = new ArrayList<Integer>();
////        for (int i : A) {
////           list.add(i);
////        }
//
////        Collections.sort(list);
////        Arrays.sort(A);
////        for (int i = 0; i < A.length; i++) {
////            System.out.println(A[i]);
////        }
////        for (int i = 1; i < A.length + 1; i++) {
////           if (!list.contains(i)) {
////               return i;
////           }
////        }
//        for (Integer i = 1; i < A.length + 1; i++) {
//            if (!Arrays.asList(A).contains(i)){
//                return i;
//            }
//        }
//        return 0;
//    }
//    public static void main(String[] args) {
//
//        try {
//            Scanner in = new Scanner(new File("input.txt"));
//            List<Integer> input = new LinkedList<Integer>();
//            while (in.hasNext()) {
//                input.add(Integer.parseInt(in.nextLine()));
//            }
//
//            int[] A = new int[input.size()];
//            for (int i = 0; i < input.size(); i++) {
//                A[i] = input.get(i);
//            }
//
//            boolean found = false;
//            for (int i = 0; i < A.length; i++) {
//                int current = A[i];
//                // forward
//                long forward = 0;
//                for (int j = 0; j < i; j++) {
//                    forward += A[j];
//                }
//
//                long backward = 0;
//                for (int j = i+1; j < A.length; j++) {
//                    backward += A[j];
//                }
//
//                if (forward == backward) {
//                    found = true;
//                    System.out.println(i);
//                }
//            }
//
//
//            if (!found) {
//                System.out.println(-1);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    public static void main(String[] args) {
//        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
//        try {
//            Scanner in = new Scanner(new File("input.txt"));
//            String first_line = in.nextLine();
//            String[] aos = in.nextLine().split("\\s+");
//
//            List<Integer> list = new LinkedList<Integer>();
//            for (String s : aos) {
//                list.add(Integer.parseInt(s));
//            }
//
//            while (list.size() > 0) {
//                System.out.println(list.size());
//                int min = getMin(list);
//                while (list.size() > 0 && list.contains(min)) {
//                    list.remove(list.indexOf(min));
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static int getMin(List<Integer> list) {
//        int min = Integer.MAX_VALUE;
//        for (Integer i : list) {
//            if (min > i)
//                min = i;
//        }
//        return min;
//    }

//    // lonely integers
//    public static void main(String[] args) {
//        try {
//            Scanner in = new Scanner(new File("input.txt"));
//            String first_line = in.nextLine();
//            String content = in.nextLine();
//
//            String[] aos = content.split("\\s+");
//
//            for (String s1 : aos) {
//                int count = 0;
//                for (String s2 : aos) {
//                    if (s1.equals(s2)) {
//                        count++;
//                        if (count >=2)
//                            break;
//                    }
//                }
//                if (count < 2) {
//                    System.out.println(s1);
//                    break;
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    // Palindromes
//    public static void main( String[] args )
//    {
//        try {
//            Scanner in = new Scanner(new File("input.txt"));
//            String first_line = in.nextLine();
//
//            OUTMOST: while (in.hasNext()) {
//                int count = 0;
//                String s = in.nextLine();
//                String formatted = format(s);
//                if (isStringPalindromes2(formatted)) {
//                    System.out.println(count);
//                    continue;
//                }
//
//                INNER: do {
//                    int max, idx;
//                    boolean found = false;
//                    for (int i = s.length() - 1; i >= 0; i--) {
//                        if (s.charAt(i) == s.charAt(s.length() - i - 1))
//                            continue;
//
//                        if ((int)(s.charAt(i)) > (int)(s.charAt(s.length() - i - 1))) {
//                            max = (int)(s.charAt(i));
//                            idx = i;
//                        } else {
//                            max = (int)(s.charAt(s.length() - i - 1));
//                            idx = s.length() - i - 1;
//                        }
//
//                        StringBuilder sb = new StringBuilder(s);
//                        sb.setCharAt(idx, (char) (max - 1));
//                        s = sb.toString();
//                        count++;
//                        found = true;
//                    }
//
//                    if (!found)
//                        break;
//                } while (true);
//
//                System.out.println(count);
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static String format(String s) {
//        if (s.length() % 2 == 0)
//            return s;
//        else {
//            StringBuilder sb = new StringBuilder(s);
//            sb.deleteCharAt(s.length()/2);
//            return sb.toString();
//        }
//    }
//
//    public static boolean isStringPalindromes2(String s) {
//        String reversed = new StringBuilder(s).reverse().toString();
//        return (s.equals(reversed));
//    }
}
