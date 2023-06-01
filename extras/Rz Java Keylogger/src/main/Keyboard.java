package main;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.CopyOnWriteArrayList;

public class Keyboard  implements KeyEventDispatcher {
    //private final List<keyevent> pressedKeys = new CopyOnWriteArrayList<>();
    public static void main(String[] args) {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new Keyboard());
    }
    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        return false;
    }
    public String getText(final KeyEvent e) {
        System.out.println("Pressed: " + e.getKeyCode());
        if (this.isPressed(KeyEvent.VK_SHIFT) || Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK)) {
            return this.getShiftText(e);
        } else if (this.isPressed(KeyEvent.VK_ALT_GRAPH)) {
            return this.getAltText(e);
        } else {
            return this.getNormalText(e);
        }
    }
    public boolean isPressed(final int keyCode) {
        /*for (final KeyEvent key : this.pressedKeys) {
            if (key.getKeyCode() == keyCode) {
                return true;
            }
        }*/
        return false;
    }
    private String getNormalText(KeyEvent e) {
        if (e.getExtendedKeyCode() == KeyEvent.getExtendedKeyCodeForChar('ß')) {
            return "ß";
        }

        switch (e.getKeyCode()) {
            case KeyEvent.VK_NUMPAD0:
                return "0";
            case KeyEvent.VK_NUMPAD1:
                return "1";
            case KeyEvent.VK_NUMPAD2:
                return "2";
            case KeyEvent.VK_NUMPAD3:
                return "3";
            case KeyEvent.VK_NUMPAD4:
                return "4";
            case KeyEvent.VK_NUMPAD5:
                return "5";
            case KeyEvent.VK_NUMPAD6:
                return "6";
            case KeyEvent.VK_NUMPAD7:
                return "7";
            case KeyEvent.VK_NUMPAD8:
                return "8";
            case KeyEvent.VK_NUMPAD9:
                return "9";

            case KeyEvent.VK_NUMBER_SIGN:
                return "#";
            case KeyEvent.VK_PERIOD:
                return ".";
            case KeyEvent.VK_COMMA:
                return ",";
            case KeyEvent.VK_PLUS:
            case KeyEvent.VK_ADD:
                return "+";
            case KeyEvent.VK_MINUS:
            case KeyEvent.VK_SUBTRACT:
                return "-";
            case KeyEvent.VK_MULTIPLY:
                return "*";
            case KeyEvent.VK_DIVIDE:
                return "/";
            default:
                if (KeyEvent.getKeyText(e.getKeyCode()).length() == 1) {
                    String text = Character.toString(e.getKeyChar());
                    text = text.toLowerCase();
                    return text;
                } else {
                    return "";
                }
        }
    }

    private String getAltText(KeyEvent e) {
        if (e.getExtendedKeyCode() == KeyEvent.getExtendedKeyCodeForChar('ß')) {
            return "\\";
        }
        switch (e.getKeyCode()) {
            case KeyEvent.VK_0:
                return "}";
            case KeyEvent.VK_2:
                return "²";
            case KeyEvent.VK_3:
                return "³";
            case KeyEvent.VK_7:
                return "{";
            case KeyEvent.VK_8:
                return "[";
            case KeyEvent.VK_9:
                return "]";
            case KeyEvent.VK_E:
                return "€";
            case KeyEvent.VK_Q:
                return "@";
            case KeyEvent.VK_M:
                return "µ";
            case KeyEvent.VK_PLUS:
                return "~";
            default:
                return "";
        }
    }

    private String getShiftText(KeyEvent e) {
        if (e.getExtendedKeyCode() == KeyEvent.getExtendedKeyCodeForChar('ß')) {
            return "?";
        }

        switch (e.getKeyCode()) {
            case KeyEvent.VK_0:
                return "=";
            case KeyEvent.VK_1:
                return "!";
            case KeyEvent.VK_2:
                return "\"";
            case KeyEvent.VK_3:
                return "§";
            case KeyEvent.VK_4:
                return "$";
            case KeyEvent.VK_5:
                return "%";
            case KeyEvent.VK_6:
                return "&";
            case KeyEvent.VK_7:
                return "/";
            case KeyEvent.VK_8:
                return "(";
            case KeyEvent.VK_9:
                return ")";
            case KeyEvent.VK_NUMBER_SIGN:
                return "'";
            case KeyEvent.VK_PERIOD:
                return ":";
            case KeyEvent.VK_COMMA:
                return ";";
            case KeyEvent.VK_PLUS:
                return "*";
            case KeyEvent.VK_MINUS:
                return "_";
            default:
                if (KeyEvent.getKeyText(e.getKeyCode()).length() == 1) {
                    return Character.toString(e.getKeyChar());
                }

                return "";
        }
    }

}
//https://www.codota.com/web/assistant/code/rs/5c779602df79be0001d47e3a#L68