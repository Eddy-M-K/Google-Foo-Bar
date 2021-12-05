public class WrongSolution
{
    public static int[] solution(int[][] m) {
        int[][] answer = new int[2][m[0].length - 1];
        int row_one_sum = m[0][1];
        int row_two_sum = m[1][0];

        for (int i = 2; i < m[0].length; i++) {
            row_one_sum += m[0][i];
            row_two_sum += m[1][i];
        }

        int total_denom = row_one_sum * row_two_sum;
        int numer = 0, denom = 0, gcd = 0;

        for (int i = 2; i < m.length; i++) {
            if (m[0][i] != 0) {
                numer = m[0][i];
                denom = row_one_sum;
            } else {
                numer = m[0][1] * m[1][i];
                denom = total_denom;
            }
            gcd = get_gcd(numer, denom);
            answer[0][i - 2] = numer / gcd;
            answer[1][i - 2] = denom / gcd;

            numer = m[1][0] * m[0][1];
            denom = total_denom;
            gcd = get_gcd(numer, denom);
            numer /= gcd;
            denom /= gcd;

            int tmp = denom;
            denom = (denom - numer) * answer[1][i - 2];
            numer = tmp * answer[0][i - 2];
            gcd = get_gcd(numer, denom);
            answer[0][i - 2] = numer / gcd;
            answer[1][i - 2] = denom / gcd;
        }

        int denom_index = answer[0].length - 1;
        answer[0][denom_index] = 1;

        for (int i = 0; i < denom_index - 1; i++) {
            answer[0][denom_index] = (answer[1][i] * answer[0][denom_index] /
            get_gcd(Math.min(answer[1][i], answer[0][denom_index]), Math.max(answer[1][i], answer[0][denom_index])));
        }

        int lcm = answer[0][denom_index];

        for (int i = 0; i < denom_index - 1; i++) {
            answer[0][i] *= lcm / answer[1][i];
        }

        return answer[0];
    }

    public static int get_gcd(int numer, int denom) {
        if (numer == 0) return denom;
        else return get_gcd(denom % numer, numer);
    }

	public static void main(String[] args) {
	    int [][] arr = {{0, 1, 0, 0, 0, 1}, {4, 0, 0, 3, 2, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}};

        int [] final_arr = new int [arr.length - 1];

        final_arr = solution(arr);

        for (int i = 0; i < final_arr.length; i++) {
            System.out.print(final_arr[i] + " ");
        }
	}
}