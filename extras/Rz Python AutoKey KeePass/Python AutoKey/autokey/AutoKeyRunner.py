

from autokey.AutoKey import AutoKey
from autokey.FileReader import FileReader
from autokey.KeyLogger import KeyLogger

class AutoKeyRunner:
    __autoKey = AutoKey()
    def __init__(self):
        self.onStart()
    
    def onStart(self):
        self.fileData = self.__onLoadFileData()
        #print(fileData)
        self.__onStartKeyLogger()
        #self.__autoKey = AutoKey()
    
    def __onLoadFileData(self):
        fileReader = FileReader()
        return fileReader.getData()
    
    def __onStartKeyLogger(self):
        keyLogger = KeyLogger(self)
    
    def onStartAutoKey(self):
        #print("onStartAutoKey")
        self.__autoKey.onStart(self.fileData)
    
    def onPauseAutoKey(self):
        print("onPauseAutoKey")
    
    def onResumeAutoKey(self):
        print("onResumeAutoKey")
    
    def onReloadAutoKey(self):
        print("onReloadAutoKey")
        fileData = self.__onLoadFileData()
    
    def onExitAutoKey(self):
        print("onExitAutoKey")
    
    def onTest(self):
        print("ontest running==========")