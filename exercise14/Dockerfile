FROM maven:3-openjdk-11

ENV CODE_DIR=/app
WORKDIR $CODE_DIR

ADD pom.xml $CODE_DIR
RUN mvn dependency:resolve

ADD src $CODE_DIR/src
RUN mvn install -DskipTests=true

FROM openjdk:11

COPY $CODE_DIR/target/dockertest*.jar $CODE_DIR/app.jar

CMD ["java", "-jar", "app.jar"]