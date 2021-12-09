import java.math.BigInteger;

public class Solution {
    public static BigInteger num;

    public static String solution(String x, String y) {
        num = BigInteger.ZERO;
        BigInteger target_x = new BigInteger(x);
        BigInteger target_y = new BigInteger(y);


        if (target_x.compareTo(BigInteger.ONE) == 0 && target_y.compareTo(BigInteger.ONE) == 0) return "0";
        else if (target_x.compareTo(BigInteger.ONE) == 0) return (target_y.subtract(BigInteger.ONE)).toString();
        else if (target_y.compareTo(BigInteger.ONE) == 0) return (target_x.subtract(BigInteger.ONE)).toString();
        else if (target_x.compareTo(target_y) == 1) {
            num = num.add(target_x.divide(target_y));
            target_x = target_x.subtract(target_y.multiply(num));
            helper(target_x, target_y.subtract(target_x));
        } else {
            num = num.add(target_y.divide(target_x));
            target_y = target_y.subtract(target_x.multiply(num));
            helper(target_x.subtract(target_y), target_y);
        }

        if (num.compareTo(BigInteger.ZERO) == 0) return "impossible";
        else return num.toString();
    }

    public static void helper(BigInteger target_x, BigInteger target_y) {
        num = num.add(BigInteger.ONE);
        if (target_x.compareTo(BigInteger.ONE) == 0 && target_y.compareTo(BigInteger.ONE) == 0) {
            return;
        } else if (target_x.compareTo(BigInteger.ONE) == -1 || target_y.compareTo(BigInteger.ONE) == -1) {
            num = BigInteger.ZERO;
            return;
        } else if (target_x.compareTo(target_y) == 1) {
            helper(target_x.subtract(target_y), target_y);
        } else {
            helper(target_x, target_y.subtract(target_x));
        }
    }
}