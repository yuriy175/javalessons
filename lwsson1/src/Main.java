public class Main {
    public static void main(String[] args)
    {
        System.out.println("task3 = " + task3(1,2,3,4));
        System.out.println("task4 = " + task4(1,2));
        System.out.println("task4 = " + task4(18,2));
        task5(-1);
        task5(1000);
        System.out.println("task6 = " + task6(-1));
        System.out.println("task6 = " + task6(1000));
        task7("Мир!");
        task8(0);
        task8(2);
        task8(23);
        task8(24);
        task8(1200);
        task8(1400);
        task8(1401);

        System.out.println("Hello world!");
    }

    private static void task2()
    {
        byte b = 0;
        short s = 1;
        int i = 2;
        long l = 4;
        float f = 0.1f;
        double d = 0.2;
        boolean bool = false;
        char c = 'a';
        String str = "string";
    }

    private static double task3(float a , float b, float c, float d)
    {
        return a * (b + (c / d));
    }

    private static boolean task4(int a , int b)
    {
        var sum = a + b;
        return 10 < sum && sum <= 20;
    }

    private static void task5(int a)
    {
        System.out.println(a + (a > 0 ? " - положительное " : " - отрицательное"));
    }

    private static boolean task6(int a)
    {
        return a <= 0;
    }

    private static void task7(String name)
    {
        System.out.println("Привет, "+name+"!");
    }

    private static void task8(int year)
    {
        if(year < 1) {
            System.out.println(year +" - невалидный");
        } else if(canBeLeap(year, 400)) {
            System.out.println(year +" - високосный");
        } else if(canBeLeap(year, 100)) {
            System.out.println(year +" - невисокосный");
        } else if(canBeLeap(year, 4)) {
            System.out.println(year +" - високосный");
        } else {
            System.out.println(year + " - невисокосный");
        }
    }

    private static boolean canBeLeap(int year, int checkValue)
    {
        return year % checkValue == 0;
    }
}