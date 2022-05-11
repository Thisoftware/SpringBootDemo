package disconnection;

public class SingletonDemo {

    private volatile static SingletonDemo instance;

    private SingletonDemo(){
        System.out.println("Singleton has loaded");
    }

    public static SingletonDemo getInstance(){
        if(instance == null){
            synchronized (SingletonDemo.class){
                if(instance == null){
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }
}
