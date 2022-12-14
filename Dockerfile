FROM gradle
WORKDIR /app
COPY . /app
CMD gradle clean build

FROM alpine
WORKDIR /app
COPY --from=0 /app/build/libs/epapers-0.0.1-SNAPSHOT.jar .
CMD java -jar ./epapers-0.0.1-SNAPSHOT.jar