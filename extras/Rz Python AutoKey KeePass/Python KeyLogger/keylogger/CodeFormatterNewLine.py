
import os.path

class CodeFormatterNewLine:
    __codeFilePath = "rz-generated-code.txt"
    __formedFilePath = "rz-ready-code.txt"
    def __init__(self):
        fileData = "{DELAY=150}" + self.__getFileData(self.__codeFilePath)
        '''print("\n")
        print("\n")
        print("\n")
        print(fileData)'''
        self.__writeFile(self.__formedFilePath, fileData)
    
    def __getFileData(self, filePath):
        fileData = self.__readFile(filePath)
        print(fileData)
        return fileData.replace("\n", "{ENTER}").replace("\r", "")
        #return fileData.replace("\n", "{====ENTER====}").replace("\r", "{----ENTER----}")

    def __readFile(self, filePath):
        fileData = ""
        if os.path.isfile(filePath):
            with open(filePath, "r") as fileReader:
                for line in fileReader.readlines():
                    fileData += line
            return fileData
        return fileData
    
    def __writeFile(self, filePath, fileData):
        fileWriter = open(filePath, "w")
        fileWriter.write(fileData)
        fileWriter.close()