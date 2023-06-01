import pynput
from pynput.keyboard import Key, Listener


with Listener(on_press=onPress, on_release=onRelease) as listener:
    listener.join()
    listener.join()
    listener.join()