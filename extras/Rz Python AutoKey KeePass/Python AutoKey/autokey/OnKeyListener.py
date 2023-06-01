
from typing import Protocol

class OnKeyListener(Protocol):
    def show(self): raise NotImplementedError