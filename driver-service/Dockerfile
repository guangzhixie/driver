FROM java:8
MAINTAINER Frank Xie "guangzhi.xie@berkeley.edu"
VOLUME /tmp
EXPOSE 8080
ADD /build/libs/driver-service-1.0-SNAPSHOT.jar driver-service-docker-1.0.jar
ENTRYPOINT ["java","-DLog4jContextSelector=org.apache.logging.log4j.core.async.AsyncLoggerContextSelector","-Dspring.messages.basename=messages_en","-Dspring.application.name=driver-service","-jar","driver-service-docker-1.0.jar"]