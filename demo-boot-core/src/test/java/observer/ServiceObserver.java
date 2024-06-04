package observer;

import java.util.Observable;
import java.util.Observer;

public class ServiceObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("This is service observer, the message change: " + arg);
    }
}
