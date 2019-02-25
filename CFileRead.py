import pandas as pd

class CRead:
    def __init__(self):
        self.data = []

    def ReadFile(self,filename):
        self.data = pd.read_csv(filename)


    def getFileData(self):
        return self.data