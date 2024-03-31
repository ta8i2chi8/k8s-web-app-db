# ビルドステージ
FROM openjdk:17-jdk-slim as build-stage
WORKDIR /app
COPY . .
RUN ./mvnw clean install -Dmaven.test.skip=true


# 実行ステージ
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build-stage /app/target/*.jar /app/application.jar
CMD ["java","-jar","/app/application.jar"]