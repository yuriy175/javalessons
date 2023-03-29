import java.util.Random;

interface IAnimal {
    void run(int value);
    void swim(int value);
    void jump(int value);
}

interface ISpreadGenerator {
    int generateFrom(int limit);
}

abstract class Animal{
    private String name = "";

    public Animal(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}

class Cat extends Animal implements IAnimal{
    //(бег: кот 200 м., собака 500 м.; прыжок: кот 2 м., собака 0.5 м.; плавание: кот не умеет плавать, собака 10 м.).
    private final static int RunLimit = 200;
    private final static int JumpLimit = 200;

    private int runLimit = 0;
    private int jumpLimit = 0;

    public Cat(String name, ISpreadGenerator generator) {
        super(name);
        runLimit = generator.generateFrom(RunLimit);
        jumpLimit = generator.generateFrom(JumpLimit);
    }


    @Override
    public void run(int value) {
        System.out.println("Кот " + getName() + " с пределом " + runLimit + " бегает на " + value +
                (value > runLimit ? " неудачно" : " успешно"));
    }

    @Override
    public void swim(int value) {
        System.out.println("Кот " + getName() + "не умеет плавать");
    }

    @Override
    public void jump(int value) {
        System.out.println("Кот " + getName() + " с пределом " + jumpLimit + " прыгает на " + value +
                (value > jumpLimit ? " неудачно" : " успешно"));
    }
}

class Dog extends Animal implements IAnimal{
    //(бег: кот 200 м., собака 500 м.; прыжок: кот 2 м., собака 0.5 м.; плавание: кот не умеет плавать, собака 10 м.).
    private final static int RunLimit = 500;
    private final static int JumpLimit = 50;
    private final static int SwimLimit = 10;

    private int runLimit = 0;
    private int jumpLimit = 0;
    private int swimLimit = 0;

    public Dog(String name, ISpreadGenerator generator) {
        super(name);
        runLimit = generator.generateFrom(RunLimit);
        jumpLimit = generator.generateFrom(JumpLimit);
        swimLimit = generator.generateFrom(SwimLimit);
    }

    @Override
    public void run(int value) {
        System.out.println("Пес " + getName() + " с пределом " + runLimit + " бегает на " + value +
                (value > runLimit ? " неудачно" : " успешно"));
    }

    @Override
    public void swim(int value) {
        System.out.println("Пес " + getName() + " с пределом " + swimLimit + " плавает на " + value +
                (value > swimLimit ? " неудачно" : " успешно"));
    }

    @Override
    public void jump(int value) {
        System.out.println("Пес " + getName() + " с пределом " + jumpLimit + " прыгает на " + value +
                (value > jumpLimit ? " неудачно" : " успешно"));
    }
}

class SpreadGenerator implements ISpreadGenerator
{
    public int generateFrom(int limit){
        var bound = Math.round((float)(limit * 0.2));
        return limit - bound + new Random().nextInt(2*bound);
    }
}