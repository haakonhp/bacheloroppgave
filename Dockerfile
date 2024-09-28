FROM gradle:jdk21 as builder

COPY --chown=gradle:gradle . /home/gradle/src

WORKDIR /home/gradle/src

RUN gradle build --no-daemon -x test

FROM openjdk:21-jdk-slim

COPY --from=builder /home/gradle/src/springweb/build/libs/springweb-1.0.jar /usr/app/springweb-1.0.jar

# mysqldump
RUN apt-get update && apt-get install -y default-mysql-client && rm -rf /var/lib/apt/lists/*

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/usr/app/springweb-1.0.jar"]
