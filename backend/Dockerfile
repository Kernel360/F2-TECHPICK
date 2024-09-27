FROM amazoncorretto:17

LABEL authors="TechPick"

ARG JAR_FILE=build/libs/*.jar

WORKDIR /app

COPY ${JAR_FILE} /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "-Duser.timezone=Asia/Seoul", "/app/app.jar"]
