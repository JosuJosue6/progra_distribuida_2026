#docker build -f src/main/docker/Dockerfile -t app-customers .
#docker build -f src/main/docker/Dockerfile -t anthonyjoel/app-customers .
#docker push anthonyjoel/app-customers

FROM eclipse-temurin:21.0.9_10-jre-alpine-3.23
ENV LANGUAGE='en_US:en'

RUN mkdir /app
WORKDIR /app

COPY build/libs/app-customers-*.jar app.jar

#
CMD ["java", "-jar", "app.jar"]