FROM openjdk:8-jre-alpine

RUN mfdir -p /usr/src/app/
WORKDIR /usr/src/app/

COPY . /usr/src/app/

ADD docker-demo-0.0.1-SNAPSHOT.jar app.jar
CMD java -jar ./app.jar