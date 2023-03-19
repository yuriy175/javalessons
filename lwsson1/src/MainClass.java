import java.util.Random;
import java.util.Scanner;

interface Indexable{
    int nextIndex(int i, int step);
}
public class MainClass {
    public static int size = 3;
    public static int dotsWin = 3;
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
        initGame();
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
            if(!preventHumanWin()){
                aiTurn();
            }
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

    public static void initGame() {
        System.out.println("Введите параметры размер поля и количество точек для победы: ");
        size = sc.nextInt();
        dotsWin = sc.nextInt();
    }

    private static boolean preventHumanWin()
    {
        // check empty cells around the last human move
        // occupy the cell if the human can win by it on his next move

        // check lefttop to rightbottom
        if(preventHumanWinBeam(lastHumanX - size, lastHumanY - size, (i, step) -> i+step, (j, step) -> j+step))
        {
            return true;
        }

        // check centertop to centerbottom
        if(preventHumanWinBeam(lastHumanX, lastHumanY - size, (i, step) -> i, (j, step) -> j+step))
        {
            return true;
        }

        // check righttop to leftbottom
        if(preventHumanWinBeam(lastHumanX + size, lastHumanY - size, (i, step) -> i-step, (j, step) -> j+step))
        {
            return true;
        }

        // check leftcenter to rightcenter
        if(preventHumanWinBeam(lastHumanX - size, lastHumanY, (i, step) -> i+step, (j, step) -> j))
        {
            return true;
        }
       /* var distance = 2 * size;
        // check lefttop to rightbottom
        var x = lastHumanX - size;
        var y = lastHumanY - size;
        for (int i = 0; i < distance; ++i) {
            var newX = x + i;
            var newY = y + i;
            if( isCellValid(newX, newY) && checkWin(newX, newY, DOT_X)) {
                setAiTurn(newX, newY);
                return true;
            }
        }

        // check centertop to centerbottom
        x = lastHumanX;
        y = lastHumanY - size;
        for (int i = 0; i < distance; ++i) {
            var newY = y + i;
            if( isCellValid(x, newY) && checkWin(x, newY, DOT_X)) {
                setAiTurn(x, newY);
                return true;
            }
        }

        // check leftbottom to righttop
        x = lastHumanX + size;
        y = lastHumanY - size;
        for (int i = 0; i < distance; ++i) {
            var newX = x - i;
            var newY = y + i;
            if( isCellValid(newX, newY) && checkWin(newX, newY, DOT_X)) {
                setAiTurn(newX, newY);
                return true;
            }
        }

        // check leftcenter to rightcenter
        x = lastHumanX - size;
        y = lastHumanY;
        for (int i = 0; i < distance; ++i) {
            var newX = x + i;
            if( isCellValid(newX, y) && checkWin(newX, y, DOT_X)) {
                setAiTurn(newX, y);
                return true;
            }
        }


        /*for(int y = lastHumanY - distance; y <= lastHumanY + distance; ++y){
            for(int x = lastHumanX - distance; x <= lastHumanX + distance; ++x) {
                if( isCellValid(x, y) && checkWin(x, y, DOT_X)) {
                    setAiTurn(x, y);
                    return true;
                }
            }
        }*/
        return false;
    }

    private static boolean preventHumanWinBeam(int x, int y, Indexable nextX, Indexable nextY) {
        // check empty cells around the last human move
        // occupy the cell if the human can win by it on his next move

        var distance = 2 * size;
        for (int i = 0; i < distance; ++i) {
            var newX = nextX.nextIndex(x, i);
            var newY = nextY.nextIndex(y, i);
            if (isCellValid(newX, newY) && checkWin(newX, newY, DOT_X)) {
                setAiTurn(newX, newY);
                return true;
            }
        }

        return false;
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
        var lastX = x; //symb == DOT_X ? lastHumanX : lastAiX;
        var lastY = y; //symb == DOT_X ? lastHumanY : lastAiY;

        // check lefttop to rightbottom
        var sum = sumBeam(symb, lastX, lastY, (i, step) -> i-step, (j, step) -> j-step) +
                sumBeam(symb, lastX, lastY, (i, step) -> i+step, (j, step) -> j+step) + 1;
        if(sum == dotsWin){
            return true;
        }

        // check centertop to centerbottom
        sum = sumBeam(symb, lastX, lastY, (i, step) -> i, (j, step) -> j-step) +
            sumBeam(symb, lastX, lastY, (i, step) -> i, (j, step) -> j+step) + 1;
        if(sum == dotsWin){
            return true;
        }

        // check leftbottom to righttop
        sum = sumBeam(symb, lastX, lastY, (i, step) -> i-step, (j, step) -> j+step) +
                sumBeam(symb, lastX, lastY, (i, step) -> i+step, (j, step) -> j-step) + 1;
        if(sum == dotsWin){
            return true;
        }

        // check leftcenter to rightcenter
        sum = sumBeam(symb, lastX, lastY, (i, step) -> i-step, (j, step) -> j) +
                sumBeam(symb, lastX, lastY, (i, step) -> i+step, (j, step) -> j) + 1;
        if(sum == dotsWin){
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
        return moveNumber >= size * size;
    }
    public static void aiTurn() {
        int x, y;
        do {
            x = rand.nextInt(size);
            y = rand.nextInt(size);
        } while (!isCellValid(x, y));
        System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
        setAiTurn(x, y);
    }

    private static void setAiTurn(int x, int y) {
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
        return x >= 0 && x < size && y >= 0 && y < size;
    }
    public static boolean isCellValid(int x, int y) {
        return isCellAvailable(x,y) && map[y][x] == DOT_EMPTY;
    }
    public static void initMap() {
        map = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }
    public static void printMap() {
        for (int i = 0; i <= size; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < size; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

