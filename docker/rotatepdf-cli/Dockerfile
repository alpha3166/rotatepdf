FROM eclipse-temurin:11-alpine as builder
RUN apk -U add maven
COPY . /rotatepdf
WORKDIR /rotatepdf
RUN mvn package

FROM eclipse-temurin:11-jre-alpine as runner
RUN addgroup -g 1000 me && \
    adduser -u 1000 -G me -D me
COPY --from=builder /rotatepdf/target/rotatepdf-*-jar-with-dependencies.jar /rotatepdf.jar
ENTRYPOINT ["java", "-jar", "/rotatepdf.jar"]
