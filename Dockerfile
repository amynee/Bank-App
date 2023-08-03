FROM openjdk:20

WORKDIR /app

COPY target/Bank-1.0.jar /app/bank.jar

EXPOSE 8087

CMD ["java", "-jar", "-Dspring.profiles.active=docker", "bank.jar"]