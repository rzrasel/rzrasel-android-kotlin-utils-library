
import os.path

class FileReader:
    __file = any
    __filePath = "rz_auto_file.txt"
    __fileData = ""
    '''def __init__(self):
        fileData = self.__readFile()
        print(fileData)'''
    
    def getRawData(self):
        return self.__readFile()
    
    def getData(self):
        self.__fileData = self.__readFile()
        return self.__fileData.replace('\n', '').replace('\r', '')

    def __readFile(self):
        if os.path.isfile(self.__filePath):
            with open(self.__filePath, "r") as fileReader:
                for line in fileReader.readlines():
                    self.__fileData += line
                    #print(line, end='')
            #self.__fileData = self.__fileData.replace('\n', '').replace('\r', '')
            #self.__fileData = self.__fileData.replace("\\r\\n", "")
            return self.__fileData
        return None