
import ctypes
from win32api import GetKeyState 
from win32con import VK_CAPITAL 

class KeyState:
    # def __init__(self):
    
    def isCapsLockOn(self):
        hllDll = ctypes.WinDLL ("User32.dll")
        VK_CAPITAL = 0x14
        return hllDll.GetKeyState(VK_CAPITAL)

'''class KeyState(object):
    def __init__(self):
        '-''if GetKeyState(VK_CAPITAL) == 1:
            print ("CAPS Lock is on.")
        elif GetKeyState(VK_CAPITAL) == 0:
            print ("CAPS Lock is off.")
        print("Class: {0} and method: {1}".format(self.__module__, self.__class__.__name__))''-'
        if self.isCapsLockOn():
            print ("CAPS Lock is on.")
        else:
            print ("CAPS Lock is off.")
    
    def isCapsLockOn(self):
        '-''hllDll = ctypes.WinDLL ("User32.dll")
        VK_CAPITAL = 0x14
        return hllDll.GetKeyState(VK_CAPITAL)''-'
        '-''if GetKeyState(VK_CAPITAL) == 1:
            return True
        elif GetKeyState(VK_CAPITAL) == 0:
            return False''-'
        hllDll = ctypes.WinDLL ("User32.dll")
        VK_CAPITAL = 0x14
        return hllDll.GetKeyState(VK_CAPITAL)'''