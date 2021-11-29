package Level_2_Challenge_1;
import java.lang.Math;
import java.util.Map;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.Collections;

public class Solution
{
    public static int current_num;
    public static TreeMap<Integer, Integer> tree_map;
    public static Iterator current_it;
    public static Map.Entry current_target;
    public static boolean finished;

    public static int[] solution(int h, int[] q)
    {
        finished = false;

        // Current number in the tree starts at the value of the top of the tree
        current_num = (int) Math.pow(2, h) - 1;

        // Get a new tree map in reverse order, from largest to smallest
        tree_map = new TreeMap<Integer, Integer>(Collections.reverseOrder());
        // Fill it in with values of the given list
        for (int i = 0; i < q.length; i++) {
            tree_map.put(q[i], i);
        }

        // Initialize current iterator
        current_it = tree_map.entrySet().iterator();

        // The current iterator and entry (key, value) of the tree map is the top of the tree
        current_target = (Map.Entry) current_it.next();

        // If the largest key is the value of the top of the tree
        if ((int) current_target.getKey() == Math.pow(2, h) - 1) {
            // then in the list, set -1 at that original index
            q[(int) current_target.getValue()] = -1;
            // Get the (key, value) of the next iterator if it exists, else return
            if (current_it.hasNext()) current_target = (Map.Entry) current_it.next();
            else return q;
        }

        // Call node with the value of the top of the tree, the decremented height, and the list (which is passed by reference)
        node((int) Math.pow(2, h) - 1, h - 1, q);
        node((int) Math.pow(2, h) - 1, h - 1, q);

        return q;
    }

    public static void node(int previous_num, int depth, int[] list)
    {
        // Check if already finished
        if (finished) return;
        // Decrement current number
        current_num--;
        // Save this node's number
        int node_number = current_num;
        // If the key is the value of the current number
        if ((int) current_target.getKey() == current_num) {
            // then in the list, set previous number at that original index in the array
            list[(int) current_target.getValue()] = previous_num;
            // Get the (key, value) of the next iterator if it exists, else return
            if (current_it.hasNext()) current_target = (Map.Entry) current_it.next();
            else {
                finished = true;
                return;
            }
        }

        // If the current depth is 1, return
        if (depth == 1) {
            return;
        // Else call node() twice
        } else {
            node(node_number, depth - 1, list);
            node(node_number, depth - 1, list);
        }

        return;
    }
}