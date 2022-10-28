FROM openjdk:11-jdk-slim
# Creamos nuestro propio grupo y usuario para ejecutar la aplicación (No con root)
RUN addgroup -S paymentchain && adduser -S admin -G paymentchain
# Especifica que ejecute las instrucciones como el usuario admin
USER admin:paymentchain
# Creamos el directorio donde se copiará el jar
VOLUME /tmp

# Variables local
ARG JAR_FILE=target/*.jar
ADD target/${JAR_FILE} app.jar

# Variable de entrono
ENV JAVA_OPTS=""
# Levanta una shell y ejecuta el jar
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]

