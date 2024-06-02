# 첫번째 단계: 빌드 환경 설정
FROM gradle:8.1.1-jdk17 AS build
WORKDIR /app

# build.gradle과 settings.gradle, 그리고 소스 코드 복사
COPY build.gradle settings.gradle ./
COPY src ./src

# 애플리케이션 빌드
RUN gradle clean build

# 두번째 단계: 실행 환경 설정
FROM openjdk:17-jdk-slim
WORKDIR /app

# 빌드 단계에서 생성된 JAR 파일 복사
COPY --from=build /app/build/libs/*.jar ./app.jar

# 애플리케이션 실행
EXPOSE 8000
CMD ["java", "-jar", "app.jar"]
