import CFileRead as fr
import CTextSearch as ts
from collections import defaultdict
import CNaiveBayes as nb
import CRecommendation as cr
#import CClassification as clf


def TextSearch():


    SearchObj = ts.CTextSearch()
    FileHandlingObj = SearchObj.getFileReadObj()
    naivebObj = nb.CNaivebase()
    naivebObj.setFileReadObj(FileHandlingObj)

    naivebObj.Initialize()
    naivebObj.CalculateClassProbability()
    classres = naivebObj.CalculateTermProbablity('gun bomb explode kill gun gun')
    print(classres)
    naivebObj.CalculateTraingAccuracy()
    naivebObj.CalculateTestAccuracy()

    '''
    RecObj = cr.CRecommendation()
    RecObj.Initialize()
    RecObj.CreateModel()
    RecObj.CalculateSimilarity()
    RecObj.Predict()
    retrive_data = RecObj.GetPredictedMovie()
    print(retrive_data)

    '''

    '''
    ClassObj = clf.CClassification()
    ClassObj.setFileReadObj(FileHandlingObj)
    ClassObj.Initialize()
    ClassObj.CreateTraingData()
    ClassObj.TrainingClassification()
    [data,actual_label,index] = ClassObj.getTestData()
    print(data)
    print(actual_label)
    PredictedClass = ClassObj.PredictedClass(data,index)
    print(PredictedClass)
    '''




TextSearch()


'''
Myword = "hello world"
    Myword1 = "xcr trivedi"
postings = defaultdict(dict)
    document_token = SearchObj.tokenize(Myword)
    for index in range(1):
        for term in document_token:
            postings[term][index] = document_token.count(term)

    document_token = SearchObj.tokenize(Myword1)
    for term in document_token:
        postings[term][3] = document_token.count(term)
    query = " hello "
    query_token = SearchObj.tokenize(query)
    print(postings)
    for q_token in query_token:
        if q_token in postings:
            if 0 in postings[q_token]:
                print(postings[q_token][0])
    #SearchObj.DisplayData()
    #SearchObj.Read_and_initialise_document()
'''



#print(np.arange(2))

