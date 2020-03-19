# tinyurl
Api for short URL creation

### Prerequisites
* Redis running locally
  * host: localhost
  * port: 6379

### Run Command Local

* gradlew bootRun 
OR
* java -jar build\libs\tinyurl-0.0.1-SNAPSHOT.jar


### API Details

POST: `http://localhost:8080/tiny/url/`

Request body:
```JSON
{
    "longUrl": "https://www.bing.com/search?q=how+to+cook+chicken&rlz=1C1GCEU_en-GBIE885IE885&oq=how+to+cook+ch&aqs=chrome.1.69i57j0l7.10917j0j7&sourceid=chrome&ie=UTF-8"
}
```

Response
```JSON
{
    "id": "70db0e47",
    "longUrl": "https://www.bing.com/search?q=how+to+cook+chicken&rlz=1C1GCEU_en-GBIE885IE885&oq=how+to+cook+ch&aqs=chrome.1.69i57j0l7.10917j0j7&sourceid=chrome&ie=UTF-8",
    "creationTime": "2020-03-18T20:26:38.8381555"
}
```
#### Redirect to long URL:
GET: `http://localhost:8080/tiny/url/70db0e47`

### Docker: 

Build local:

* Build Command: `docker build -t tinyurl-docker .`
* Run Command: `docker run -p 8080:8080 tinyurl-docker`

DockerHub:

* docker pull 3805/tinyurl-docker
* Run directly: `docker run -p 5000:8080 3805/tinyurl-docker:0.0.1-SNAPSHOT`

### Extension:
* This service can be extended in terms of scalability by deploying into AWS with LoadBalancer and using AWS Redis service cluster. It will require some redis configuration changes in the project.
* It can also be extended to build User Interface in order to get LongUrl inputs from users and also to redirect the tiny Urls to Long Urls.


