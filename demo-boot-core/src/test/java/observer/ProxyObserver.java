package observer;

import java.util.Observable;
import java.util.Observer;

public class ProxyObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("This is proxy observer, the message change: " + arg);
    }
}
