# QuerySearch_Recommentation_Classification
Performing query Search , Recommendation and Classification is not so easy when we have large data set. To solve this problem we always get help from data mining concept. Vector Space Model, User similarity is some of approach to solve this issue. Once we solve data mining and able to retrieve data another issue come how to show these data to the user. To handle this issue, I am creating client and a server scenario where I am creating a client in Android and perform data mining operation in python. To communicate between them, I have created one more layer server layer which is developed in a flask and help  communication between client and server using JSON Object.


Server code

    DataMiningServer.Py

      Created to host server using flask 
      Global variable
      SearchObj:  use to store CTextSearch Obj to perform text search

      Method
      def WelcomeToDataMining() Welcoming user to my server 

      def searchText(): Provided link to perform query search to client and return result in JSON Object

      def InitialiseSearchObject(): Initialize Search Object at the start of server

      def SearchQuery(query): Call search operation on client query



    CTextSearch.py
    Class CTextSearch 

      	This Class created to perform text search on datset using vector space model. Weight matrix is created using TF-IDF 
        Variable: 
      	self.stemmer : Used to store PorterStemmer()classObject 
      	purpose: To eliminate stop word and perform stemming on token
      	self.FileHandlingObj = To store CRead Object for CSV File handling 
      	self.MovieData = to store Movie dataset
      	self.postings = to keep track of how many time token appeared in document
      	self.docF = to keep track of term appearing in how many document
      	self.dict = to keep track of Unique Token
      	self.length =  to keep track of number of token in each document
      	self.totalDocument = to keep track of total number of document in dataset
      	self.similarity_vec = to calculate similarity Vector
      	self.sorted_similarity = to keep track of similarity sorted according to priority 
       	self.logObj =  for logging and debugging purpose

      	Method

      	def tokenize: to convert word into token 

      	def Read_and_initialise_document(self): Read the document and initialize the variable self.dict and self.postings
          should be done at start of server

      	def Calculating_Document_frequency(self): calculating and storing for each terms appeared in how many document, updating self.docF

      	def Calculate_similarity(self,query_vec,doc_vec): calculate cosign similarity of two tf-idf vector

      	def Make_Query_vector(self,query_token): create tf-idf weight vector of query term


      	def Make_Document_vector(self,query_token,id): create tf-idf weight vector of query term for document

      	def Calculate_Inverse_Document_Frequency(self,term): calculate idf for given term
      	idf = log(Number of document / Number of document term appeared)


      	def DisplayData(self): added for debuging purpose

	def Search(self,query): Perform the search and return retrieved data based on priority 

    Clog.py
	class Clog : To show progress and debugging purpose

    CFileRead.py
	Class CRead : to perform file handling 


Client Side 


	class ConnectServer : Created to connect to server using Http post method 

		Method 
		public void SendSearchData(String data,int Act,SearchResult SearchResultObj) : use to send data to server

		private String getResponseFromServer(String targetUrl): Get Server response will be run in background

		private JSONObject CreateSearchJson(): Create search Object for JSON query

		private void CreateDatatoSend(HttpURLConnection urlConnection,JSONObject JObject): Create output Stream to send data to server

		private String readStream(InputStream in) : Read data received from server

		private void ReadReceivedJson() : Convert received data stream to JSON Object and called appropriate object based on Action ( Search Query, Recommendation System, classification)

	class MainActivity : Create login screen for user

	Method
	public void onClick: check for valid user
![](https://github.com/BhaskarTrivedi/QuerySearch_Recommentation_Classification/blob/master/Img/Client_Login.JPG)


	class Home: Created to perform different action, right now support added only for search
		
		Method 
		public void SearchQuery(View view) : Navigate to Search query selection on search action
		
![](https://github.com/BhaskarTrivedi/QuerySearch_Recommentation_Classification/blob/master/Img/Home.jpg)
 

	class SearchActivity : Created to get Search query from user

		Method 
		public void SearchQuery(View view) : Get the query from user and called Result GUI
![](https://github.com/BhaskarTrivedi/QuerySearch_Recommentation_Classification/blob/master/Img/Search_Query.jpg)
	
	class SearchResult: Created to show search result based on user query.

	Method 
	public void DisplayQueryResult : Show query result in GUI
	
![](https://github.com/BhaskarTrivedi/QuerySearch_Recommentation_Classification/blob/master/Img/Search_Result.jpg)

	class ClassificationActivity :  Created to get query class 
	
	Method 
	public void DisplayQueryResult: Show classification result
	
![](https://github.com/BhaskarTrivedi/QuerySearch_Recommentation_Classification/blob/master/Img/Classification.JPG)

	class Recommendation :  Created to recommended movie to the user
	
	Method
	public void DisplayQueryResult : Show recommendation result to GUI

	

Environment

	Python : 3.6.2
	Windows : 10
	Flask           1.0.2
	nltk            3.4
	numpy           1.16.1
	pandas          0.24.1
	simplejson      3.16.0


Reference:

https://github.com/mnielsen/VSM/blob/master/vsm.py</br>
http://blog.josephwilk.net/projects/building-a-vector-space-search-engine-in-python.html

Credit

Dataset Kaggle Moviedata : https://www.kaggle.com/rounakbanik/the-movies-dataset#movies_metadata.csv 
