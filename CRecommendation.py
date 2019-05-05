
import pandas as pd
import numpy as np
from sklearn.metrics.pairwise import pairwise_distances


class CRecommendation():
    def __init__(self):
        self.usercols = []
        self.userdes = 0
        self.ratingcols = []
        self.ratings = 0
        self.itemcol = []
        self.item = 0
        self.totaluser = 0
        self.totalmovie = 0
        self.item_matrix = 0
        self.usersimilarity = 0
        self.itemsimilarity = 0
        self.prdiction = []

    def Initialize(self):
        self.usercols = ['user_id', 'age', 'sex', 'occupation', 'zip_code']
        self.ratingcols = ['user_id', 'movie_id', 'rating', 'unix_timestamp']
        self.userdes = pd.read_csv('u.user', sep='|', names=self.usercols,encoding='latin-1')
        self.ratings = pd.read_csv('u.data', sep='\t', names=self.ratingcols,encoding='latin-1')
        self.itemcol = ['movie_id', 'movie_title' ,'release date','video release date', 'IMDb URL', 'unknown', 'Action', 'Adventure',
                            'Animation', 'Children\'s', 'Comedy', 'Crime', 'Documentary', 'Drama', 'Fantasy',
                            'Film-Noir', 'Horror', 'Musical', 'Mystery', 'Romance', 'Sci-Fi', 'Thriller', 'War', 'Western']
        self.item = pd.read_csv('u.item', sep='|', names=self.itemcol,encoding='latin-1')
        #print(self.item.head())
        #print(self.ratings.head())



    def CreateModel(self):
        self.totaluser = self.ratings.user_id.unique().shape[0]
        self.totalmovie = self.ratings.movie_id.unique().shape[0]
        self.item_matrix = np.zeros((self.totaluser+1, self.totalmovie))
        #random generated data for end user
        self.item_matrix[1, :] = np.random.randint(6, size=self.totalmovie)
        #print(self.item_matrix[1, :])
        for line in self.ratings.itertuples():
            #print(line)
            self.item_matrix[line[1] , line[2]-1 ] = line[3]



    def CalculateSimilarity(self):
        self.usersimilarity = pairwise_distances(self.item_matrix, metric='cosine')
        self.itemsimilarity = pairwise_distances(self.item_matrix.transpose(), metric='cosine')

    def Predict(self, type='user'):
        if type == 'user':
            mean_user_rating = self.item_matrix.mean(axis=1)
            # We use np.newaxis so that mean_user_rating has same format as ratings
            ratings_diff = (self.item_matrix - mean_user_rating[:, np.newaxis])
            self.prdiction = mean_user_rating[:, np.newaxis] + self.usersimilarity.dot(ratings_diff) / np.array(
                [np.abs(self.usersimilarity).sum(axis=1)]).transpose()
        elif type == 'item':
            self.prdiction = self.item_matrix.dot(self.itemsimilarity) / np.array([np.abs(self.itemsimilarity).sum(axis=1)])

    def GetPredictedMovie(self):
        retrive_data = {}
        movie_name = []


        for index in range(5):
            maxindex = np.argmax(self.prdiction[1,:])
            self.prdiction[1, maxindex] = 0
            movie_name.append(self.item[self.item.movie_id==maxindex]['movie_title'].iloc[0])

        retrive_data.update({"Movie":movie_name})
        return retrive_data





