
'''import time
import random
import pynput
from time import time, sleep
from random import random
from pynput.keyboard import Key, Controller
from autokey.ParseXML import ParseXML'''


import _thread
import random
from time import time, sleep
from pynput.keyboard import Key, Controller
from autokey.ParseXML import ParseXML


class SavedAutoKey01:
    keyboard = any
    xmlDataList = any
    milliseconds = 1000
    minTypingDelay = 0
    maxTypingDelay = 0
    typingDelay = 0
    strinList = []

    def __init__(self):
        try:
            _thread.start_new_thread(self.print_time, ("Thread-1", 0.02,))
        except:
            print ("Error: unable to start thread")
    
    def print_time(self, threadName, delay):
        count = 0
        while count < 5:
            sleep(delay)
            count += 1
            print ("Print form thread: {0}".format(count))
        _thread


    def __init__Ok(self):
        self.keyboard = Controller()
        parseXML = ParseXML("controll.xml")
        self.xmlDataList = parseXML.getXMLData()
        self.minTypingDelay = int(self.xmlDataList["min_typing_delay_millis"])
        self.maxTypingDelay = int(self.xmlDataList["max_typing_delay_millis"])
        if self.minTypingDelay > self.maxTypingDelay:
            temp = self.maxTypingDelay
            self.maxTypingDelay = self.minTypingDelay
            self.minTypingDelay = temp
        self.typingDelay = self.getTypingDelay()
        # print("TYPING_DELAY: {0}".format(self.typingDelay))
        self.keyboard = Controller()
        #
        string = "Hi this is a text RESTART: {CTRL}"
        for char in string:
            #self.strinList.append(char)
            self.onKeyStroke(char)
        self.keyboard.press(Key.tab)
        self.keyboard.release(Key.tab)
        sleep(self.getTypingDelay())
        self.onKeyStroke("M")
    
    def onKeyStroke(self, key):
        self.keyboard.press(key)
        self.keyboard.release(key)
        sleep(self.getTypingDelay())
        '''self.keyboard.press('a')
        self.keyboard.release('a')
        sleep(self.getTypingDelay())'''
    
    def getTypingDelay(self):
        return random.randint(self.minTypingDelay, self.maxTypingDelay) / self.milliseconds


'''
class AutoKey:
    keyboard = Controller()

    def __init__(self):
        # self.keyboard = Controller()
        # delay = random.uniform(0, 2)  # Generate a random number between 0 and 10
        # delay = random.randint(1, 60)
        #delay = random.uniform(0.1, 1.9)
        #print("DELAY: {0}".format(delay))
        '-''self.keyboard.press('H')
        self.keyboard.release('H')
        #sleep(100)
        #time.sleep(random.randint(1, 60))
        #time.sleep(5.0)
        sleep(0.4)
        self.keyboard.press('a')
        self.keyboard.release('a')
        #sleep(100)
        #time.sleep(2)
        self.keyboard.type('Hello World')''-'
        parseXML = ParseXML("controll.xml")
        xmlDataList = parseXML.getXMLData()
        #print(xmlDataList["global"])
        for item in xmlDataList.keys():
            print(xmlDataList[item])
'''