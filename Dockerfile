FROM maven AS mvn_builder

# copie le pom.xml dans le répertoire app de l'image maven
WORKDIR /app 
COPY pom.xml .
# charge toutes les dépendences nécessaires au projet spring
RUN mvn dependency:go-offline
# copie des fichiers source dans /app/src
COPY src ./src
# construction du .jar
RUN mvn package -DskipTests
#===========================================================
FROM openjdk
# copie du .jar dans le répertoire /app
WORKDIR /app
COPY --from=mvn_builder /app/target/ex2_todo-0.0.1-SNAPSHOT.jar .
# Ouverture du 8080 et lancement de l'application
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "ex2_todo-0.0.1-SNAPSHOT.jar"]