package app;

import java.util.Random;

public class SingletonRandom {

    public static SingletonRandom instance;

    private SingletonRandom(){

    }

    public static SingletonRandom getInstance(){
        SingletonRandom.instance = new SingletonRandom();
        return SingletonRandom.instance;
    }

    public int getRandomNumber(int sizeOfRandom){
        Random random = new Random();
        return random.nextInt(sizeOfRandom);
    }

}
