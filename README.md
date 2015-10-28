# Facebook Moments
Project to fetch most happening moment of Facebook users from their timeline.

### Technologies used:

1. Spring MVC to create RestFul wenservices.
2. Kafka Email Notfication Server.     
3. MongoDB to persist Facebook users data.  
4. Spring Scheduler to send daily/weekly emails to users about their happening moments.
5. RESTFb - Facebook API to fetch data in JSON format.
6. OAuth - To authorize facebook user credentials.

### How to use 
  
Steps to run this project:  
1. Import this project as Gradle project in your IDE.  
2. In terminal window of your IDE, run following commands: gradle build gradle run.  
3. Open browser and run http://localhost:8080/index.html   

Note: You should have gradle package installed in your system in order to import this project.  
  
