# Используем официальный образ OpenJDK в качестве базового
FROM openjdk:17-jdk-slim-buster

# Устанавливаем рабочую директорию (папку для хранения инструкций)
WORKDIR /app

# Копируем собранный jar-файл в контейнер
COPY /target/SpringRestAPI-0.0.1-SNAPSHOT.jar /app/spring.jar

# Указываем команду для запуска приложения
ENTRYPOINT ["java", "-jar", "spring.jar"]

# Открываем порт, на котором будет работать приложение
EXPOSE 8081
