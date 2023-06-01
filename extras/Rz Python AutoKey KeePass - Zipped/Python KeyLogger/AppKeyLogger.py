
import threading
import tkinter as tk
from keylogger.CodeFormatter import CodeFormatter
from keylogger.CodeFormatterNewLine import CodeFormatterNewLine
from keylogger.KeyLogger import KeyLogger

class AppKeyLogger:
    def __init__(self):
        self.keyLogger = KeyLogger(self)
        self.__root = tk.Tk()
        #self.__root.protocol("WM_DELETE_WINDOW", self.onExitApp)
        self.__canvas1 = tk.Canvas(self.__root, width = 500, height = 400)
        self.__canvas1.pack()

        btnClearFile = tk.Button(text="Clear File", command=self.onBtnClickClearFile, bg="brown", fg="white")
        btnKeyLogger = tk.Button(text="Run Code Writer", command=self.onBtnClickKeyLogger, bg="brown", fg="white")
        btnCodeFormat = tk.Button(text="Run Code Format", command=self.onCodeFormatter, bg="brown", fg="white")
        btnCodeFormatNewLine = tk.Button(text="Run Code Format New Line", command=self.onCodeFormatterNewLine, bg="brown", fg="white")
        btnClearFile.pack()
        btnKeyLogger.pack()
        btnCodeFormat.pack()
        btnCodeFormatNewLine.pack()
        self.__canvas1.create_window(250, 140, window=btnClearFile)
        self.__canvas1.create_window(250, 180, window=btnKeyLogger)
        self.__canvas1.create_window(250, 220, window=btnCodeFormat)
        self.__canvas1.create_window(250, 260, window=btnCodeFormatNewLine)

        self.lblBtnInfo = tk.Label(self.__root, text= "", fg="green", font=("helvetica", 12, "bold"))
        self.__canvas1.create_window(250, 290, window=self.lblBtnInfo)

        self.__root.title("KeyLogger")
        self.__root.attributes('-alpha', 0.0)
        menubar = tk.Menu(self.__root)
        filemenu = tk.Menu(menubar, tearoff=0)
        '''filemenu.add_command(label="Format Code", command=self.onCodeFormatter())
        filemenu.add_command(label="Clear File", command=self.keyLogger.onClearFile())'''
        filemenu.add_separator()
        filemenu.add_command(label="Exit", command=self.__root.destroy)
        menubar.add_cascade(label="File", menu=filemenu)
        self.__root.config(menu=menubar)
        '''frm = tk.Frame(root, bd=4, relief='raised')
        frm.pack(fill='x')
        lab = tk.Label(frm, text='KeyLogger', bd=4, relief='sunken')
        lab.pack(ipadx=4, padx=4, ipady=4, pady=4, fill='both')'''
        self.center(self.__root)
        self.__root.attributes('-alpha', 1.0)
        self.__root.mainloop()

    def center(self, win):
        """
        centers a tkinter window
        :param win: the main window or Toplevel window to center
        """
        win.update_idletasks()
        width = win.winfo_width()
        frm_width = win.winfo_rootx() - win.winfo_x()
        win_width = width + 2 * frm_width
        height = win.winfo_height()
        titlebar_height = win.winfo_rooty() - win.winfo_y()
        win_height = height + titlebar_height + frm_width
        x = win.winfo_screenwidth() // 2 - win_width // 2
        y = win.winfo_screenheight() // 2 - win_height // 2
        win.geometry('{}x{}+{}+{}'.format(width, height, x, y))
        win.deiconify()

    def onBtnClickClearFile(self):
        self.lblBtnInfo["text"] = "Clear Code Running"
        self.keyLogger.onClearFile()

    def onBtnClickKeyLogger(self):
        '''self.lblBtnInfo = tk.Label(self.__root, text= 'Script Running', fg='green', font=('helvetica', 12, 'bold'))
        self.__canvas1.create_window(250, 290, window=self.lblBtnInfo)'''
        self.lblBtnInfo["text"] = "Script Running"
        thread = threading.Thread(target=self.keyLogger.onKeyListener)
        thread.start()
    
    def onCodeFormatter(self):
        #print("Code")
        self.lblBtnInfo["text"] = "Code Formatter Running"
        self.codeFormatter = CodeFormatter()
    
    def onCodeFormatterNewLine(self):
        #print("Code")
        self.lblBtnInfo["text"] = "Code Formatter New Line Running"
        self.codeFormatterNewLine = CodeFormatterNewLine()
    
    def onExitApp(self):
        self.keyLogger.onClearFile()
        print("Window closed")
        self.__root.destroy
        self.__root.quit
    def onEscapeKeyLog(self):
        self.lblBtnInfo["text"] = "Keylogger script stoped"

class App:
    def main(self):
        AppKeyLogger()

if __name__ == "__main__":
    App().main()
