package disconnection;

public class SingletonDemo {

    //这种写法虽然是多线程安全的，但是每次使用getInstance方法都需要进行同步，因此效率比较低。
    public static class Singleton {
        private static Singleton instance;
        private Singleton(){}
        public static synchronized Singleton getInstance(){
            if(instance==null){
                instance = new Singleton();
            }
            return instance;
        }
    }

    //这种方法会首先判断singleton是否为空，如果这个对象一旦被创建，在后期的调用过程中就不会进入同步的代码，因此，有更高的效率。
    public static class Singleton1 {
        private volatile static Singleton instance;
        private Singleton1() {}
        public static Singleton getInstance() {
            if (instance == null) {
                synchronized (Singleton.class) {
                    if (instance == null) {
                        instance = new Singleton();
                    }
                }
            }
            return instance;
        }
    }
}
