package Level_2_Challenge_2;
public class Solution {
    public static int solution(String s) {
        int answer = 0, right = 0, left = 0; // Unused `left` variable

        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case '>':
                    right++;
                    break;
                case '<':
                    left++; // Unused code
                    answer += (2 * right);
                    break;
                default:
            }
        }

        return answer;
    }
}