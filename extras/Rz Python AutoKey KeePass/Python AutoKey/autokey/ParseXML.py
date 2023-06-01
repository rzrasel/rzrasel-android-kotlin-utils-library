
import xml.etree.ElementTree as ET

class ParseXML:
    xmlTree = any
    xmlRoot = any
    xmlDataList = {}

    def __init__(self, xmlFilePath):
        self.xmlTree = ET.parse(xmlFilePath)
        self.xmlRoot = self.xmlTree.getroot()
        #self.getXMLData(xmlRoot)
        
    
    def getXMLData(self):
        xmlItemCounter = 0
        for child in self.xmlRoot:
            # value = item.find("item").text
            '''print(child.text)
            print(child.tag)'''
            #value = child.find("item").text
            self.xmlDataList[child.tag] = child.text
            xmlItemCounter += 1
            #print(item.attrib)
        return self.xmlDataList