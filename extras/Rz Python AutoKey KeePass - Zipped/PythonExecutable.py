
import tkinter as tk

root= tk.Tk()

canvas1 = tk.Canvas(root, width = 300, height = 300)
canvas1.pack()

def center(win):
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

def hello ():  
    label1 = tk.Label(root, text= 'Script Running', fg='green', font=('helvetica', 12, 'bold'))
    canvas1.create_window(150, 200, window=label1)
    
button1 = tk.Button(text='Click Me', command=hello, bg='brown', fg='white')
canvas1.create_window(150, 150, window=button1)

root.title("KeyLogger")
root.attributes('-alpha', 0.0)
menubar = tk.Menu(root)
filemenu = tk.Menu(menubar, tearoff=0)
filemenu.add_command(label="Exit", command=root.destroy)
menubar.add_cascade(label="File", menu=filemenu)
root.config(menu=menubar)
'''frm = tk.Frame(root, bd=4, relief='raised')
frm.pack(fill='x')
lab = tk.Label(frm, text='KeyLogger', bd=4, relief='sunken')
lab.pack(ipadx=4, padx=4, ipady=4, pady=4, fill='both')'''
center(root)
root.attributes('-alpha', 1.0)
root.mainloop()