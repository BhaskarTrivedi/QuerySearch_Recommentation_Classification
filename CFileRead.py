import pandas as pd

class CRead:
    def __init__(self):
        self.data = []
        self.t_data = []

    def ReadFile(self,filename):
        self.data = pd.read_csv(filename)

    def ReadTrainingData(self,filename):
        self.t_data = pd.read_csv(filename)

    def getFileData(self):
        return self.data

    def getTraingFileData(self):
        return self.t_data