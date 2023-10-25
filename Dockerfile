FROM eclipse-temurin:17
LABEL mentainer="chandrakantbharambe1997@gmail.com"
WORKDIR /app
COPY target/grocery-0.0.1-SNAPSHOT.jar /app/grocery-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "grocery-0.0.1-SNAPSHOT.jar"]