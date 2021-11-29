import java.util.HashMap;

public class Solution {
    public static HashMap<Integer, Integer> hash_map;

    public static int solution(int n) {
        hash_map = new HashMap<Integer, Integer>();

        return helper(1, n) - 1;
    }

    public static int helper(int k, int n) {
        if (k > n) return 0;
        else if (k == n) return 1;

        int hash_code = return_hash_code(k, n);

        if (hash_map.containsKey(hash_code)) {
            return hash_map.get(hash_code);
        } else {
            int sum = helper(k + 1, n) + helper(k + 1, n - k);

            hash_map.put(hash_code, sum);

            return sum;
        }
    }

    public static int return_hash_code(int k, int n) {
        return (k << 16) + n;
    }
}