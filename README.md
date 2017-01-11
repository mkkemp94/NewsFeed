# NewsFeed

Access The Guardian api to retreive a json response relating to a custom search term. This is done through an HTTPUrlConnection, and the response is further traversed to retrieve specific data from which an object is created. This object is accessed through a list view adapter to display its information in each list item. All outside networking is done through a seperate process which runs on a parallel thread. The application is reponsive and displays relavant information according to the user's needs. 

Project is done in response to Udemy prompt:
https://classroom.udacity.com/courses/ud843/lessons/6752095343239847/concepts/78263106680923#

Concepts explored include:
- List View + Adapter
- Extracting data from a json string
- Retreiving json through an HTTP request
- Reading data from an HTTP request using an input stream and readers
- Performing network requests using multiple threads using an Asnyc Task
- Performing searches using an Async Task through a loader, which stops executing on pplication close
- Setting and reading preexisting application preferences
- Building a uri that the http requst can make based off of user input
