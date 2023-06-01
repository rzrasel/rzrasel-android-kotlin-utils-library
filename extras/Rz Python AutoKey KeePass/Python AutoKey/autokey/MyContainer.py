
from autokey.OnKeyListener import OnKeyListener


class MyContainer(OnKeyListener):
    def show(self):
        print("RUN_FROM_MyContainer")