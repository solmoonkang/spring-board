FROM azul/zulu-openjdk:17

WORKDIR /app

COPY build/libs/notice-board-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

# cd build/libs
# docker build -t ssolmoon/notice-board-docker .
# docker run -p 8080:8080 ssolmoon/notice-board-docker