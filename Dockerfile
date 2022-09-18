FROM maven:3.8.3-openjdk-17
WORKDIR /todolist
COPY . .
RUN mvn clean install
RUN mvn package

EXPOSE 8099
ARG JAR_FILE=target/todolist-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]