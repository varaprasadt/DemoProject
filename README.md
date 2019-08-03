# DemoProject

# About api:
1) /projects/username
This end point provides a way to get List of projects own by a git user, we need to pass the git user id as the parameter
 Sample Usage : http://localhost:8080/projects/varaprasadt
 it shows project id,name  and url.
 
 
2) /projects/username/projectId
This end point provides a way to retieve the specific project info. like project readMe content, List of contributors to this project and number of commits so far .
 sample Usage : http://localhost:8080/projects/varaprasadt/DemoRepo
 
# Content Negotiation :

This project supports content Negotiation, default it will return JSON format response, and also supports XML based response.
you can add the Header parameter accordingly 
    for XML Response : Accept:application/XML

 
# Local Configuration:
  # pre requisites :
     JDK8
     Maven
     git
     Docker container installed
     
# Cloning:
    >git clone https://github.com/varaprasadt/DemoRepo.git
# Build :
    >cd to (project_home)/Demo
    >mvn clean package
# Run as Jar :
    cd to (project_home)/Demo/target
    >java -jar Demo-0.0.1-SNAPSHOT

# Run as Docker Image :
    cd to (project_home)/Demo
    >docker build -t demoimage .
    >docker run -p 80:80 demoimage
   
# Jenikins Pipeline :
    Go to jenkins portal , select pipeline job create new pipeline. For example DemoJob
    >Navigate to DemoJob>Configuration screen
    Copy the Jenkins file script to Pipeline>Defination section or select Pipeline Jenikins file from scm
    
    run the Job.
    
