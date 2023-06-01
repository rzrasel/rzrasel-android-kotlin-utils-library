
import os.path

class CodeFormatter:
    __codeFilePath = "rz-generated-code.txt"
    __formedFilePath = "rz-ready-code.txt"
    def __init__(self):
        fileData = "{DELAY=150}" + self.__getFileData(self.__codeFilePath)
        #print(fileData)
        self.__writeFile(self.__formedFilePath, fileData)
    
    def __getFileData(self, filePath):
        fileData = self.__readFile(filePath)
        print(fileData)
        return fileData.replace("\n", "").replace("\r", "")
        #return fileData.replace('\n', '').replace('\r', '')

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

'''def main():
    CodeFormatter()

if __name__ == "__main__":
    main()'''
'''
this code is writen for auto genaretion
i will take time for it

ch
check for 10 second enter code

enter applied
'''