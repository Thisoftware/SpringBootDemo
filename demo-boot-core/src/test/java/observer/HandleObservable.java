package observer;

import java.util.Observable;

public class HandleObservable extends Observable {

    public synchronized void handle(String msg) {
        try {
            if (msg == null || msg.trim().isEmpty()) {
                System.err.println("Message is null or empty.");
                return;
            }
            setChanged(); // 标记状态已改变
            notifyObservers(msg); // 通知所有观察者
        } catch (Exception e) {
            System.err.println("Error notifying observers: " + e.getMessage());
        }
    }
}
