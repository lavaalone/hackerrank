package com.hackerrank.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by thinhnguyen on 4/19/15.
 */
public class Solution {

    // passing car - Count the number of passing cars on the road.
//    int count = 0;
//    int passed = 0;
//    public int solution2(int[] A) {
//        // write your code in Java SE 8
////        long mil = System.currentTimeMillis();
//        long sum = IntStream.of(A).sum();
////        Log("Millisecond passed := " + (System.currentTimeMillis() - mil));
////        mil = System.currentTimeMillis();
//        IntStream ints = IntStream.of(A);
//        ints.forEach(
//                i -> {
//                    if (i == 0) {
////                        count += Arrays.stream(A).skip(idx + 1).filter(v -> v == 1).count();
//                        count += (sum - passed);
////                        if (count > 1000000000)
////                            break;
//                    } else if (i == 1) {
//                        passed++;
//                    }
//                }
//        );
////        Log("Millisecond passed := " + (System.currentTimeMillis() - mil));
//        return count > 1000000000 ? -1 : count;
//    }

    // hacker rank - angry professor
    public void Solution() throws Exception {
        Scanner in = new Scanner(new File("input.txt"));
        in.nextLine();
        int line_idx = 0;
        int max = 0;
        OUTMOST: while (in.hasNext()) {
            line_idx++;

            int attend = 0;
            String[] aos = in.nextLine().split("\\s+");
            if (line_idx % 2 == 1) {
                max = Integer.parseInt(aos[1]);
                Log("max := " + max);
            } else {
                INNER: for (String s : aos) {
                    if (Integer.parseInt(s) <= 0)
                        attend++;
//                    Log("value := " + s + ", max := " + max + ", attend := " + attend);
                    if (attend >= max) {
                        System.out.println("NO");
                        break INNER;
                    }
                }
                if (attend < max)
                    System.out.println("YES");
            }
        }
    }

    // hacker rank - funny string
    public void Solution1() throws Exception {
        Scanner in = new Scanner(new File("input.txt"));
        in.nextLine();

        while (in.hasNext()) {
            String s = in.nextLine();
            String reversed = new StringBuilder(s).reverse().toString();

//            Log("s := " + s);
//            Log("reversed := " + reversed);

            boolean funny = true;
            for (int i = 1; i < s.length() - 1; i++) {
                if (Math.abs((int)s.charAt(i) - (int)s.charAt(i-1)) != Math.abs((int)reversed.charAt(i) - (int)reversed.charAt(i-1))) {
                    funny = false;
                    break;
                }
            }
            if (funny)
                Log("Funny");
            else
                Log("Not Funny");
        }
    }

    // pangram text
    public void Solution2() throws Exception {
        Scanner in = new Scanner(new File("input.txt"));
        String content = in.nextLine();
//        Log("a := " + (int)(new Character('a')));
//        Log("a := " + (int)(new Character('A')));
//        Log("z := " + (int)(new Character('z')));
//        Log("z := " + (int)(new Character('Z')));

        boolean is_pangram = true;
        for (int i = 97; i <= 122; i++) {
//            Log("char := " + (char)i);
            if (!content.contains("" + (char)i) && !content.contains("" + (char)(i - 32))) {
                is_pangram = false;
                break;
            }
        }
        System.out.println(is_pangram ? "pangram" : "not pangram");

    }

    // Connected Cell in a Grid
    public void Solution3() throws Exception {
        Scanner in = new Scanner(new File("input.txt"));
        String line_0 = in.nextLine(); // row
        String line_1 = in.nextLine(); // column

        // create a list of all cell
        List<Cell> list_cell = new LinkedList<Cell>();
        int row = 0;
        while (in.hasNext()) {
            String[] aos = in.nextLine().split("\\s+");
            for (int i = 0; i < aos.length; i++) {
                Cell cell = new Cell(Integer.parseInt(aos[i]), i, row);
//                Log("Add cell, value :=  " + Integer.parseInt(aos[i]) + ", pos x := " + i + ", pos y := " + row);
                list_cell.add(cell);
            }
            row++;
        }

        int region_idx = 0;
        for (Cell cell : list_cell) {
            if (cell._value == 0)
                continue;

            if (cell._region.isEmpty()) {
                cell.SetRegion(region_idx);
                region_idx++;
            }

//            System.out.println("### cell: " + cell.toString());

            // find a list of cell around this cell (max = 8)
            List<Cell> around = list_cell.stream()
//                    .filter(inner_cell -> (inner_cell._posX != cell._posX) && (inner_cell._posY != cell._posY))
                    .filter(inner_cell -> Math.abs(inner_cell._posX - cell._posX) <= 1)
                    .filter(inner_cell -> Math.abs(inner_cell._posY - cell._posY) <= 1)
                    .filter(inner_cell -> inner_cell._value == 1)
                    .collect(Collectors.toList());

            for (Cell inner_cell : around) {
                if (inner_cell._region.isEmpty()) {
                    inner_cell.SetRegion(cell._region);
                } else {
                    cell.SetRegion(inner_cell._region);
                    inner_cell.SetRegion(cell._region);
                }
//                Log("debug cell := " + inner_cell.toString());
            }
        }

        int max_count = 0;
        for (int i = 0; i < region_idx; i++) {
            int count = 0;
            for (Cell cell : list_cell) {

                if (cell._region.isEmpty())
                    continue;

                if (cell._region.contains(i)) {
                    count++;
                }
            }
            if (count > max_count) {
                max_count = count;
            }
//            Log("region := " + i + ", count := " + count);

        }
        Log("" + max_count);

    }


    // Bot saves princess
    public void Solution4() throws Exception {
        Scanner in = new Scanner(new File("input.txt"));
        String line_0 = in.nextLine(); // size

        // create a list of all cell
        SavePrincessCell bot = new SavePrincessCell();
        SavePrincessCell princess = new SavePrincessCell();
        int row = 0;
        while (in.hasNext()) {
            String content = in.nextLine();
            for (int i = 0; i < content.length(); i++) {
                SavePrincessCell cell = new SavePrincessCell((int)content.charAt(i), i, row);
                if (cell.isPrincess()) {
                    princess = cell;
                } else if (cell.isBot()) {
                    bot = cell;
                }
            }
            row++;
        }
//        Log("bot := " + bot.ToString());
//        Log("princess := " + princess.ToString());

        // move horizontally
        while (bot._pos_x != princess._pos_x) {
            if (bot._pos_x < princess._pos_x) {
                bot._pos_x += 1;
                Log("RIGHT");
            } else {
                bot._pos_x -= 1;
                Log("LEFT");
            }
        }

        // move vertically
        while (bot._pos_y != princess._pos_y) {
            if (bot._pos_y < princess._pos_y) {
                bot._pos_y += 1;
                Log("DOWN");
            } else {
                bot._pos_y -= 1;
                Log("UP");
            }
        }
    }

    public void Solution5() throws Exception {
        Scanner in = new Scanner(new File("input.txt"));
        String bot_position = in.nextLine(); // bot
        String[] size = in.nextLine().split("\\s+"); // size
        int height = Integer.parseInt(size[0]);
        int weight = Integer.parseInt(size[1]);

        CleanBotCell bot = new CleanBotCell();
        List<CleanBotCell> list = new LinkedList<CleanBotCell>();

        int row = 0;
        while (in.hasNext()) {
            String s = in.nextLine();
            for (int i = 0; i < s.length(); i++) {
                CleanBotCell cell = new CleanBotCell((int)s.charAt(i), i, row);
                if (cell.isBot()) {
                    bot = cell;
                } else if (cell.isDirty()) {
                    list.add(cell);
                }
            }
            row++;
        }

        int distance = 1;
        do {
            List<CleanBotCell> around = around(bot, list, distance);
//            Log("distance := " + distance + ", bot pos := " + bot._pos_x + "|" + bot._pos_y +  ", around := " + around.size());
            if (around.size() == 0) {
                distance++;
                if (distance > height && distance > weight)
                    break;
            } else {
                HashMap<Integer, Integer> distance_list = new HashMap<Integer, Integer>();
                for (CleanBotCell cell : around) {
                    distance_list.put(around.indexOf(cell), distance(bot, cell));
                }

                int min = Integer.MAX_VALUE;
                int idx = -1;
                for (Map.Entry<Integer, Integer> e : distance_list.entrySet()) {
                    if (e.getValue() < min) {
                        min = e.getValue();
                        idx = e.getKey();
                    }
                }

                moveTo(bot, around.get(idx));
            }
        } while (true);
    }

    // find a list of cell around the bot
    public List<CleanBotCell> around(CleanBotCell bot, List<CleanBotCell> list, int distance) {
        return list.stream()
                .filter(cell -> cell.isDirty())
                .filter(cell -> Math.abs(cell._pos_x - bot._pos_x) <= distance)
                .filter(cell -> Math.abs(cell._pos_y - bot._pos_y) <= distance)
                .collect(Collectors.toList());
    }

    // compute the shortest distance between bot and dirty cell
    public int distance(CleanBotCell bot, CleanBotCell dirty) {
        int count = 0;
        int bot_pos_x = bot._pos_x;
        int bot_pos_y = bot._pos_y;
        int dirty_pos_x = dirty._pos_x;
        int dirty_pos_y = dirty._pos_y;
        while (bot_pos_x != dirty_pos_x) {
            if (bot_pos_x < dirty_pos_x)
                bot_pos_x++;
            else
                bot_pos_x--;

            count++;
        }

        while (bot_pos_y != dirty_pos_y) {
            if (bot_pos_y < dirty_pos_y)
                bot_pos_y++;
            else
                bot_pos_y--;

            count++;
        }
//        Log("distance := " + count);
        return count;
    }

    // move the bot to dirty cell using the shortest path
    public void moveTo(CleanBotCell bot, CleanBotCell dirty) {
//        Log("target, pos x := " + dirty._pos_x + ", pos y := " + dirty._pos_y);
        while (bot._pos_x != dirty._pos_x) {
            if (bot._pos_x < dirty._pos_x)
            {
                bot._pos_x++;
                Log("RIGHT");
            }
            else
            {
                bot._pos_x--;
                Log("LEFT");
            }
        }

        while (bot._pos_y != dirty._pos_y) {
            if (bot._pos_y < dirty._pos_y)
            {
                bot._pos_y++;
                Log("DOWN");
            }
            else
            {
                bot._pos_y--;
                Log("UP");
            }
        }
        dirty.Clean();
    }

    public void Solution6() throws Exception {
        BufferedReader in = new BufferedReader(new FileReader("input.txt"));
        int N = Integer.parseInt(in.readLine());
        int K = Integer.parseInt(in.readLine());
        int[] list = new int[N];

        for(int i = 0; i < N; i ++)
            list[i] = Integer.parseInt(in.readLine());

        int unfairness = Integer.MAX_VALUE;

        List<Integer> unfairness_list = new LinkedList<Integer>();
        List sorted_input = IntStream.of(list).sorted().boxed().collect(Collectors.toList());
        for (int i = 0; i <= sorted_input.size() - K; i++) {
            List sub_list = sorted_input.subList(i, i + K);
            int unfair_value = (int)sub_list.get(sub_list.size() - 1) - (int)sub_list.get(0);
            if (unfair_value == 0)
            {
                unfairness = 0;
                break;
            }
            unfairness_list.add(unfair_value);
        }
        if (unfairness != 0)
            unfairness = unfairness_list.stream().mapToInt(Integer::intValue).min().getAsInt();

      /*
       * Write your code here, to process numPackets N, numKids K, and the packets of candies
       * Compute the ideal value for unfairness over here
      */

        System.out.println(unfairness);
    }


    /*
    Unfortunately, despite the fact that the function may return expected result for
    the example input, there is a bug in the implementation, which may produce incorrect results
    for other inputs. Find the bug and correct it. You should modify at most three lines of code
     */
    public int Solution7(int[] A, int X) {
        int N = A.length;
        if (N == 0) {
            return -1;
        }
        int l = 0;
        int r = N - 1;
        while (l < r) {
            int m = (l + r) / 2;
            if (A[m] > X) {
                r = m - 1;
            } else if (A[m] < X) {
                l = m + 1;
            } else {l = m; break;}
        }
        if (A[l] == X) {
            return l;
        }
        return -1;
    }

    /*
    The rule is useful if we can transform the string by using it....
     */
    public String Solution8(String S) {
        // write your code in Java SE 8
        HashMap<String, String> rules = new HashMap<String, String>();
        rules.put("AB", "AA");
        rules.put("BA", "AA");
        rules.put("CB", "CC");
        rules.put("BC", "CC");
        rules.put("AA", "A");
        rules.put("CC", "C");

        OUTMOST: do {
            boolean found = false;
            INNER: for (Map.Entry<String, String> e : rules.entrySet()) {
                if (S.contains(e.getKey())) {
                    S = S.replace(e.getKey(), e.getValue());
                    found = true;
                    break INNER;
                }
            }
            if (!found)
                break OUTMOST;
        } while (true);
        return S;
    }

    /*
    There are four pairs of identical indices: (0, 3), (0, 4), (...)...
     */
    public int Solution9(int[] A) {
        // write your code in Java SE 8
        List<Integer> list = IntStream.of(A).boxed().collect(Collectors.toList());
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            List<Integer> sublist = list.subList(i+1, list.size());
            final int current = list.get(i);
            count += sublist.stream().filter(j -> j == current).count();
//            System.out.println("sublist := " + sublist + ", current := " + current + ", count := " + count);
        }
        return count;
    }

    public static int findArray(int[] array, int[] subArray) {
        List<Integer> list = new LinkedList<Integer>();
        for (int i : array) {
            list.add(i);
        }
        List<Integer> sub_list = new LinkedList<Integer>();
        for (int i : subArray) {
            sub_list.add(i);
        }
        return Collections.indexOfSubList(list, sub_list);
    }

    public static List sortEmailList(List<String> input) {
        List input_lower_case = input.stream().map(s -> s.toLowerCase()).collect(Collectors.toList());
        Collections.sort(input_lower_case);
        return input_lower_case;
    }



    public void Log(String s) {
        System.out.println(s);
    }
}

class CleanBotCell {
    public int _value = -1;
    public int _pos_x = -1;
    public int _pos_y = -1;

    public CleanBotCell() {

    }

    public CleanBotCell(int value, int pos_x, int pos_y) {
        this._value = value;
        this._pos_x = pos_x;
        this._pos_y = pos_y;
    }

    public void setValue(int value) {
        this._value = value;
    }

    public boolean isBot() {
        return ((char)_value == 'b');
    }

    public boolean isDirty() {
        return ((char)_value == 'd');
    }

    public boolean isPath() {
        return ((char)_value == '-');
    }

    public void Clean() {
        System.out.println("CLEAN");
        this._value = (int)'-';
    }

    public String ToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("_pos_x :=").append(_pos_x);
        sb.append(", _pos_y :=").append(_pos_y);
        sb.append(", _value :=").append(_value);
        sb.append(", isBot :=").append(isBot());
        sb.append(", isDirty :=").append(isDirty());
        sb.append(", isPath :=").append(isPath());
        return sb.toString();
    }
}

class SavePrincessCell {
    public int _value = -1;
    public int _pos_x = -1;
    public int _pos_y = -1;

    public SavePrincessCell() {

    }

    public SavePrincessCell(int value, int pos_x, int pos_y) {
        this._value = value;
        this._pos_x = pos_x;
        this._pos_y = pos_y;
    }

    public boolean isPrincess() {
        return ((char)_value == 'p');
    }

    public boolean isBot() {
        return ((char)_value == 'm');
    }

    public boolean isPath() {
        return ((char)_value == '-');
    }

    public String ToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("_pos_x :=").append(_pos_x);
        sb.append(", _pos_y :=").append(_pos_y);
        sb.append(", _value :=").append(_value);
        sb.append(", isPrincess :=").append(isPrincess());
        sb.append(", isBot :=").append(isBot());
        sb.append(", isPath :=").append(isPath());
        return sb.toString();
    }
}

class Cell {
    public int _value = -1;
    public int _posX = -1;
    public int _posY = -1;
    public List<Integer> _region = new LinkedList<Integer>();
    public Cell() {
    }

    public Cell(int value, int pos_x, int pos_y) {
        _value = value;
        _posX = pos_x;
        _posY = pos_y;
    }

    public void SetRegion(List<Integer> region) {
        for (Integer i : region) {
            SetRegion(i);
        }
    }

    public void SetRegion(int region_id) {
        if (!_region.contains(region_id)) {
            _region.add(region_id);
        }
    }

//    public String getRegion() {
////        StringBuilder sb = new String
//    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("pos x := " + _posX + ", pos y := " + _posY + ", value := " + _value + ", region := " + _region);
        return sb.toString();
    }
}
