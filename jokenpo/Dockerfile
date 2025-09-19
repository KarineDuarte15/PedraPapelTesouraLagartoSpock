# Estágio 1: Build da Aplicação com Maven e Java 17
FROM maven:3.8.5-openjdk-17 AS build

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o pom.xml primeiro para aproveitar o cache de dependências
COPY pom.xml .

# Baixa as dependências
RUN mvn dependency:go-offline

# Copia o resto do código-fonte
COPY src ./src

# Compila e empacota a aplicação
RUN mvn package -DskipTests

# Estágio 2: Execução da Aplicação com JRE 17
FROM openjdk:17-jre-slim

WORKDIR /app

# Copia o .jar empacotado do estágio de build
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta que a aplicação vai usar
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]