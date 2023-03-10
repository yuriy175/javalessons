import java.util.Arrays;

public class Main {
    public static void main(String[] args)
    {
        findMinMax(new int[]{1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1});
        changeValues(new int[]{1, 1, 0, 0, 1, 0, 1, 1, 0, 0});
        fillArray(8);
        multiplyBy2(new int[]{1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 });
        makeTwoDimArray(8);
        checkBalance(new int[]{1, 1, 1, 2, 1});
        checkBalance (new int[]{2, 1, 1, 2, 1});
        checkBalance (new int[]{10, 10});
        checkBalance (new int[]{2, 1, 1, 2, 1, 2, 1,2});
        checkBalance (new int[]{2, 1, 1, 2, 1, 2, 1,2,12});
        checkBalance (new int[]{12, 2, 1, 1, 2, 1, 2, 1,2});
        checkBalance (new int[]{12, 2, 1, 1, 2, 1, 1, 1,2});
        shiftArray(new int[]{1, 2, 3, 4, 5,6,7,8,9,0,10}, -2);
        shiftArray(new int[]{1, 2, 3, 4, 5,6,7,8,9,0,10}, 2);
        shiftArray(new int[]{1, 2, 3, 4, 5,6,7,8,9,0,10}, -27);
        shiftArray(new int[]{1, 2, 3, 4, 5,6,7,8,9,0,10}, 27);
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

    //TASK 2


    private static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; ++i) {
            System.out.print(" " + arr[i]);
        }
        System.out.println("");
    }

    private static void changeValues(int[] arr) {
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = arr[i] == 0 ? 1 : 0;
        }

        printArray(arr);
    }

    private static void fillArray(int dim) {
        var arr = new int[dim];
        for (int i = 0; i < dim; ++i) {
            arr[i] = i * 3;
        }

        printArray(arr);
    }

    private static void multiplyBy2(int[] arr) {
        for (int i = 0; i < arr.length; ++i) {
            if(arr[i] < 6) {
                arr[i] *= 2;
            }
        }

        printArray(arr);
    }

    private static void makeTwoDimArray(int dim) {
        var arr = new int[dim][dim];
        for (int i = 0; i < dim; ++i) {
            arr[i][i] = 1;
        }

        for (int i = 0; i < dim; ++i) {
            var str = "";
            for (int j = 0; j < dim; ++j) {
                str += " " + arr[i][j];
            }
            System.out.println(str);
        }
    }

    private static void findMinMax(int[] arr) {
        int min = arr[0], max = arr[0];
        var arrLength = arr.length;
        if(arrLength > 1) {
            for (int i = 1; i < arr.length; ++i) {
                if(min > arr[i])
                    min = arr[i];
                if(max < arr[i])
                    max = arr[i];
            }
        }

        System.out.print("min: " + min + " max: " + max);
    }

    private static void checkBalance(int[] arr) {
        var currLeftIndex = 0;
        var leftSum = arr[currLeftIndex ++];
        var currRightIndex = arr.length - 1;
        var rightSum = arr[currRightIndex];

        while (currLeftIndex < currRightIndex){
            if(leftSum <= rightSum) {
                leftSum += arr[currLeftIndex ++];
            }
            else{
                rightSum += arr[--currRightIndex];
            }
        }

        System.out.println("leftSum: " + leftSum + " rightSum: " + rightSum + " равны: " + (leftSum == rightSum));
        System.out.println("currLeftIndex: " + currLeftIndex + " currRightIndex: " + currRightIndex);
    }


    private static void shiftArray(int[] arr, int shift) {
        var tempIndex = shift > 0? arr.length - 1 : 0;
        var arrIndex = shift > 0? 0 : arr.length - 1;
        var srcInitialIndex = shift > 0? 0 : 1;
        var dstInitialIndex = shift > 0? 1 : 0;
        shift = Math.abs(shift % arr.length);

        for(var i = 0; i < shift; ++i){
            var temp = arr[tempIndex];
            System.arraycopy(arr, srcInitialIndex, arr , dstInitialIndex, arr.length - 1);
            arr[arrIndex] = temp;
        }

        printArray((arr));
    }
}