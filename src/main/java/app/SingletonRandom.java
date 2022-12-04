package app;

import java.util.Random;

public class SingletonRandom {

    public static SingletonRandom instance;
    Random random = new Random();;

    private SingletonRandom(){

    }

    public static SingletonRandom getInstance(){
        SingletonRandom.instance = new SingletonRandom();
        return SingletonRandom.instance;
    }

    public int getRandomNumber(int sizeOfRandom){
        return random.nextInt(sizeOfRandom);
    }

}
