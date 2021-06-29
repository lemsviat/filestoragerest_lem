# filestoragerest_lem
### **About**
An application that allows us to store files in the cloud, categorize them with tags and search through them using Elasticsearch technologies.

### **Tools/libraries used**
•	Spring Boot (Spring Boot Starter Data Elasticsearch,
Spring Boot Starter Validation, etc.)

•	Elasticsearch 7.13.2
### **Instructions**
•	Download the code and import it to any IDE;

•	Download and unzip Elasticsearch from https://www.elastic.co/downloads/elasticsearch;

•	Run bin/elasticsearch (or bin\elasticsearch.bat on Windows);

•	Run `curl http://localhost:9200;`

•	Run the project;

•	Use Postman or other similar tools to send HTTP requests and receive responses.

### **Endpoints**
•	POST /file – Upload file to Elasticsearch server;

•	DELETE /file/{ID} - Delete file, which have the ID;

•	POST /file/{ID}/tags - Assign tags to the file;

•	DELETE /file/{ID}/tags – Remove tags from the file;

•	GET /file?tags=tag1,tag2,tag3&page=2&size=3 -  Receive list of files with pagination filtered by tags.

