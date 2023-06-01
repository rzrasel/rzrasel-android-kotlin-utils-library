package main;
import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
public class KeyTest {
    public static void main(String[] args){
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        // Update the locking state for caps lock button to true
        // will turn the caps lock on.
        toolkit.setLockingKeyState(KeyEvent.VK_CAPS_LOCK, Boolean.TRUE);
        toolkit.setLockingKeyState(KeyEvent.VK_SCROLL_LOCK, Boolean.TRUE);
        Thread t = new Thread(){
            @Override
            public void run() {
                System.out.println("STARTING");
                Toolkit kit = Toolkit.getDefaultToolkit();
                kit.addAWTEventListener(new AWTEventListener() {

                    @Override
                    public void eventDispatched(AWTEvent event) {
                        System.out.println("EVENT");
                        if(event instanceof KeyEvent){
                            KeyEvent kEvent = (KeyEvent) event;
                            System.out.println(kEvent.getKeyCode());
                        }
                    }

                }, AWTEvent.KEY_EVENT_MASK);
                while(true);
            }
        };
        t.start();
    }
}
