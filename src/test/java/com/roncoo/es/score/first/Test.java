package com.roncoo.es.score.first;

public class Test {

    public static void main(String[] args) {
        for (int i = 1900322; i <= 1000000000; i = i + 2) {
            for (int a = 2; a <= i / 2; a++) {
                if (isPrime(a) && isPrime(i - a)) {
                    print(i + "是两个质数之和 = " + a + "+" + (i - a));
                    break;
                }
            }

        }
    }

    //判断一个大于2的数是不是质数
    private static boolean isPrime(int i) {
        for (int j = 2; j < i - 1; j++) {
            if (i % j == 0) {
                return false;
            }
        }
        return true;
    }

    //输出
    private static void print(Object s) {
        System.out.print(s.toString() + "\n");
    }

}
