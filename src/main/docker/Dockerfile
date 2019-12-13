FROM java:8
VOLUME /tmp


#WORKDIR /application/jenkins/deploy/src/main/docker
#RUN pwd

ADD company-service-1.0-SNAPSHOT.jar company-service.jar
ENTRYPOINT ["java","-Xms200m","-Xmx300m","-jar","/company-service.jar"]