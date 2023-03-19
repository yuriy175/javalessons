import java.util.Random;
import java.util.Scanner;

interface Indexable{
    int nextIndex(int i, int step);
}
public class MainClass {
    public static int SIZE = 3;
    public static int DOTS_TO_WIN = 3;
    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static char[][] map;
    public static Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();

    public static int lastHumanX = -1;
    public static int lastHumanY = -1;

    public static int lastAiX = -1;
    public static int lastAiY = -1;

    public static int moveNumber = 0;
    public static void start() {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (checkWin(lastHumanX, lastHumanY, DOT_X)) {
                System.out.println("Победил человек");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
            aiTurn();
            printMap();
            if (checkWin(lastAiX, lastAiY, DOT_O)) {
                System.out.println("Победил Искуственный Интеллект");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }/**/
        }
        System.out.println("Игра закончена");
    }
    public static boolean checkWin(int x, int y, char symb) {
        /*if(map[0][0] == symb && map[0][1] == symb && map[0][2] == symb) return true;
        if(map[1][0] == symb && map[1][1] == symb && map[1][2] == symb) return true;
        if (map[2][0] == symb && map[2][1] == symb && map[2][2] == symb) return true;
        if (map[0][0] == symb && map[1][0] == symb && map[2][0] == symb) return true;
        if (map[0][1] == symb && map[1][1] == symb && map[2][1] == symb) return true;
        if (map[0][2] == symb && map[1][2] == symb && map[2][2] == symb) return true;
        if (map[0][0] == symb && map[1][1] == symb && map[2][2] == symb) return true;
        if (map[2][0] == symb && map[1][1] == symb && map[0][2] == symb) return true;*/
        // check lefttop to rightbottom
        var sum = sumBeam(symb, lastHumanX, lastHumanY, (i, step) -> i-step, (j, step) -> j-step) +
                sumBeam(symb, lastHumanX, lastHumanY, (i, step) -> i+step, (j, step) -> j+step) + 1;
        if(sum == SIZE){
            return true;
        }

        // check centertop to centerbottom
        sum = sumBeam(symb, lastHumanX, lastHumanY, (i, step) -> i, (j, step) -> j-step) +
            sumBeam(symb, lastHumanX, lastHumanY, (i, step) -> i, (j, step) -> j+step) + 1;
        if(sum == SIZE){
            return true;
        }

        // check leftbottom to righttop
        sum = sumBeam(symb, lastHumanX, lastHumanY, (i, step) -> i-step, (j, step) -> j+step) +
                sumBeam(symb, lastHumanX, lastHumanY, (i, step) -> i+step, (j, step) -> j-step) + 1;
        if(sum == SIZE){
            return true;
        }

        // check leftcenter to rightcenter
        sum = sumBeam(symb, lastHumanX, lastHumanY, (i, step) -> i-step, (j, step) -> j) +
                sumBeam(symb, lastHumanX, lastHumanY, (i, step) -> i+step, (j, step) -> j) + 1;
        if(sum == SIZE){
            return true;
        }
        return false;
    }

    private static int sumBeam(char symb, int x, int y, Indexable nextX, Indexable nextY) {
        var sum = 0;
        var step = 0;
        while(true){
            var newX = nextX.nextIndex(x, ++step);
            var newY = nextY.nextIndex(y, step);
            if(!isCellAvailable(newX,newY) || map[newY][newX] != symb){
                break;
            }
            ++sum;
        }

        return sum;
    }

    public static boolean isMapFull() {
        return moveNumber >= SIZE * SIZE;
    }
    public static void aiTurn() {
        int x, y;
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (!isCellValid(x, y));
        System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
        map[y][x] = DOT_O;
        lastAiX = x;
        lastAiY = y;
        ++moveNumber;
    }
    public static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y)); // while(isCellValid(x, y) == false)
        map[y][x] = DOT_X;
        lastHumanX = x;
        lastHumanY = y;
        ++moveNumber;
    }
    public static boolean isCellAvailable(int x, int y) {
        return x >= 0 && x < SIZE && y >= 0 && y < SIZE;
    }
    public static boolean isCellValid(int x, int y) {
        return isCellAvailable(x,y) && map[y][x] == DOT_EMPTY;
    }
    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }
    public static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

