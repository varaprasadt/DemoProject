FROM java:8

EXPOSE 8080

ADD Demo.jar target/Demo-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java" ,"-xmx512m","-jar","Demo.jar"]