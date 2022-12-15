FROM gradle AS build
WORKDIR /app
COPY . /app
RUN gradle clean build

FROM openjdk:17-alpine
WORKDIR /app
COPY --from=build /app/build/libs/epapers-0.0.1-SNAPSHOT.jar .
COPY src/main/resources .
CMD java -jar ./epapers-0.0.1-SNAPSHOT.jar