FROM adoptopenjdk/openjdk11:alpine
RUN addgroup -g 1000 me && \
    adduser -u 1000 -G me -D me
COPY target/rotatepdf-*-jar-with-dependencies.jar /rotatepdf.jar
ENTRYPOINT ["java", "-jar", "/rotatepdf.jar"]
