FROM maven:3.9-eclipse-temurin-17

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
COPY testng-selenoid.xml .

CMD ["mvn", "test", "-DsuiteXmlFile=testng-selenoid.xml"]