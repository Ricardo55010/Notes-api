FROM eclipse-temurin:21
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["sh", "-c", "java -Duser.timezone=CET $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar"]