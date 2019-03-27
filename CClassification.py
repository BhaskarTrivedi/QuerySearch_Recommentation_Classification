
import random
from sklearn.svm import SVC

class CClassification():
    def __init__(self):
        self.FileHandlingObj = ''
        self.MovieData = []
        self.Training_data = []
        self.Training_label = []
        self.training_num_data = []
        self.total_train_data = 0
        self.model = ''
        self.total_dimension = 0
        self.movieMap = {1:"Fantacy",2:"Comedy",3:"Family ",4:"Romance",5:"sci-fi",6:"History",7:"Drama",8:"Adventure",9:"Thriller"}

    def setFileReadObj(self,FileObj):
        self.FileHandlingObj = FileObj

    def Initialize(self):
        self.MovieData = self.FileHandlingObj.getFileData()
        self.FileHandlingObj.ReadTrainingData("y_training.csv")

        self.Training_data = self.FileHandlingObj.getTraingFileData()
        #print(self.Training_data.loc[:, 'Fantacy'])
        #print(self.Training_data.shape)
        #print(self.MovieData.shape)
        [self.total_train_data,self.total_dimension] = self.Training_data.shape

    def CreateTraingData(self):
        self.Training_label = self.Training_data.iloc[0:self.total_train_data, 8:self.total_dimension]
        self.Training_label = self.Training_label.to_numpy()
        #self.Training_label = self.Training_label.transpose()
        #self.Training_label = self.Training_label.as_matrix()
        self.training_num_data = self.Training_data.iloc[0:self.total_train_data, 0:self.total_dimension-1]
        self.training_num_data = self.training_num_data.to_numpy()
        #print(self.training_num_data)
        #print(self.Training_label)

    def TrainingClassification(self):
        self.model = SVC(kernel='linear', probability=True)

        self.model.fit(self.training_num_data, self.Training_label)

    def PredictedClass(self,data,index):
        print(index)
        retrive_data = {}
        [total_data,_] = data.shape
        movie_name = []
        print(data)
        pred = self.model.predict(data)
        predicted_class = []
        for data_index in range(total_data):
            movie_name.append(self.MovieData.loc[index[data_index], 'title'])
            predicted_class.append(self.movieMap[pred[data_index]])
        retrive_data.update({"Movie": movie_name})
        retrive_data.update({"Class": predicted_class})
        return retrive_data

    def getTestData(self):
        #k = random.randint(1, 200)
        k = random.sample(range(1, 200), 3)
        [_,tolcol] = self.MovieData.shape
        data = self.MovieData.iloc[k, tolcol-9:tolcol-1]
        actual_label = self.MovieData.iloc[k, tolcol-1:tolcol]
        data = data.to_numpy()
        return [data,actual_label,k]



