

from nltk.corpus import stopwords
from nltk.stem.porter import PorterStemmer
import pandas as pd
from collections import defaultdict
from collections import Counter

class CNaivebase():
    def __init__(self):
        self.FileHandlingObj = ''
        self.MovieData = []
        #self.postings = defaultdict(dict)
        self.ClassF = defaultdict(int) # store classfrequency/ Probablity
        self.queryClassPrabablity = defaultdict(int)  # store  Probablity of class given term
        self.ClassTermCount = defaultdict(set)  # to store each class has how may total term
        self.TermClassFrequency = defaultdict(dict) # to store count of term in each class
        self.totalDocument = 0
        self.dict = set()
        self.length = []
        self.unique_terms = set([]) # store number of unique term
        self.probclass = set([])

    def setFileReadObj(self,FileObj):
        self.FileHandlingObj = FileObj

    # create token from the string description and remove stop word(common word)
    def tokenize(self, description):
        if pd.isnull(description):
            return []
        else:
            terms = description.lower().split()
            # remove stop word
            filtered = [word for word in terms if not word in stopwords.words('english')]
            return filtered

    def Initialize(self):
        self.MovieData = self.FileHandlingObj.getFileData()
        #print(self.MovieData.loc[1, 'Mgenres'])

        [self.totalDocument, TotalDimension] = self.MovieData.shape
        self.totalDocument = 200  # need to comment only for debuging
        for index in range(self.totalDocument):
            current_class = self.MovieData.loc[index, 'Mgenres']
            if pd.isna(current_class):
                continue
            terms = self.tokenize(self.MovieData.loc[index, 'overview'])
            self.length.append(len(terms))
            self.ClassF[current_class] = self.ClassF[current_class] + 1
            u_term = Counter(terms).keys()
            u_count = list(Counter(terms).values())

            term_index = 0
            for term in u_term:

                #updating count of each term in posting(document)
                self.ClassTermCount[current_class].add(term)
                self.TermClassFrequency[term][current_class] = self.TermClassFrequency[term].get(current_class,0) + u_count[term_index]
                term_index += 1
            #print(terms)
            # print(self.length[index])
            # updating dictionary with all available terms


            self.unique_terms.update(set(terms))
            #print(u_term)
            #print(u_count)
            #print(current_class)

            '''
            self.dict = self.dict.union(unique_terms)
            for term in unique_terms:
                # updating count of each term in posting(document)

                self.postings[term][index] = terms.count(term)
            self.logObj.progress_track(index, self.totalDocument)
            '''

        #print(self.unique_terms)
        #print(self.ClassF)
        #print(self.ClassTermCount)
        #print(len(self.unique_terms))
        print(self.TermClassFrequency)



        #print(self.MovieData["Mgenres"].values)
        #self.FileHandlingObj.ReadTrainingData("y_training.csv")

        #self.Training_data = self.FileHandlingObj.getTraingFileData()
        #print(self.Training_data.loc[:, 'Fantacy'])
        #print(self.Training_data.shape)
        #print(self.MovieData.shape)
        #[self.total_train_data,self.total_dimension] = self.Training_data.shape

    def CalculateClassProbability(self):
        for key in self.ClassF:
            self.ClassF[key] = self.ClassF[key] / self.totalDocument

        #print(self.ClassF[' Animation'])
        #print(len(self.ClassTermCount[' Romance']))
        #print(len(self.ClassF))
        #print(self.TermClassFrequency['gh'].get(' Comedy',0))

    def CalculateTermProbablity(self,query):
        terms = self.tokenize(query)
        retrive_data = {}
        probablity = 0
        className = ''
        for key in self.ClassF:
            #print((self.ClassTermCount[key]))
            currentProb = self.ClassF[key]
            for term in terms:
                currentProb = currentProb * (( self.TermClassFrequency[term].get(key,0)+1) /( len(self.ClassTermCount[key]) + len( self.unique_terms)))

            self.queryClassPrabablity[key] = currentProb
            print(currentProb)
            if currentProb > probablity:
                probablity = currentProb
                className = key

        retrive_data.update({"Movie":[className]})
        retrive_data.update({"Prabablity": [probablity]})
        return retrive_data








