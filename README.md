# DemoProject
Telectronics Demo

# About api:(/projects/username)
This end point privides a way to get the number of projects own by a git user, we need to pass the git user id as the parameter
 Sample Usage : http://localhost:8080/projects/varaprasadt
 
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
   
# Jenikins Pipeline :
    Go to jenkins portal , select pipeline job create new pipeline. For example DemoJob
    >Navigate to DemoJob>Configuration screen
    Copy the Jenkins file script to Pipeline>Defination section or select Pipeline Jenikins file from scm
    
    run the Job.
    
