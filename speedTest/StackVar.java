public class StackVar {
    public static void main(String[] args) {
        test2();
    }
    static void test1() {
        long time1 = System.currentTimeMillis();
        int val = 9999999;
        int times = Integer.MAX_VALUE;
        while (times -- > 0) {
            for (int i = 0; i < Integer.MAX_VALUE; i ++) {
                int tmp1 = val + val;
                int tmp2 = val * val;
                int tmp3 = val - val;
                int tmp4 = val / val;
            }
        }
        long time2 = System.currentTimeMillis();
        System.out.println(time2 - time1);
    }
    static void test2() {
        long time1 = System.currentTimeMillis();
        int val = 9999999;
        int tmp1 = 0;
        int tmp2 = 0;
        int tmp3 = 0;
        int tmp4 = 0;
        int times = Integer.MAX_VALUE;
        while (times -- > 0) {
            for (int i = 0; i < Integer.MAX_VALUE; i ++) {
                tmp1 = val + val;
                tmp2 = val * val;
                tmp3 = val - val;
                tmp4 = val / val;
            }
        }
        long time2 = System.currentTimeMillis();
        System.out.println(time2 - time1);
    }
}
