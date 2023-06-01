
import random
from time import time, sleep
from pynput.keyboard import Key, Controller
from autokey.ParseXML import ParseXML

class AutoKey:
    __xmlDataList = any
    __milliseconds = 1000
    __minTypingDelay = 0
    __maxTypingDelay = 0
    __typingDelay = 0
    __stringList = []
    __loopIndex = 0
    __isPaused = False
    def onStart(self, data):
        #print("AutoKey_onStart {0}".format(data))
        self.__rawData = data
        self.__keyboard = Controller()
        parseXML = ParseXML("controll.xml")
        self.xmlDataList = parseXML.getXMLData()
        self.minTypingDelay = int(self.xmlDataList["min_typing_delay_millis"])
        self.maxTypingDelay = int(self.xmlDataList["max_typing_delay_millis"])
        if self.minTypingDelay > self.maxTypingDelay:
            temp = self.maxTypingDelay
            self.maxTypingDelay = self.minTypingDelay
            self.minTypingDelay = temp
        self.__isPaused = False
        self.typingDelay = self.__getTypingDelay()
        self.__run(self.__loopIndex)
    
    def __run(self, loopIndex):
        #print("__RUN")
        #print("LENGTH: {0}".format(len(self.__rawData)))
        if len(self.__rawData) <= 0:
            return
        self.__rawData = self.__rawData[loopIndex:len(self.__rawData)]
        '''for i, character in enumerate(self.__rawData):
            print(str(character))
            self.__onKeyStroke(character)'''
        for char in self.__rawData:
            self.__onKeyStroke(char)
    
    def __onKeyStroke(self, key):
        print(key)
        self.__keyboard.press(key)
        self.__keyboard.release(key)
        sleep(self.__getTypingDelay())
    
    def __getTypingDelay(self):
        return random.randint(self.__minTypingDelay, self.__maxTypingDelay) / self.__milliseconds