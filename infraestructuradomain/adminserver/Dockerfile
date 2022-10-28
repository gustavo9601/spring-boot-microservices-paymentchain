FROM openjdk:11.0.10-jdk-oracle
# Creamos nuestro propio grupo y usuario para ejecutar la aplicación (No con root)
ARG user=admin
ARG group=paymentchain
ARG uid=1000
ARG gid=1000

RUN groupadd -g ${gid} ${group} && useradd -u ${uid} -G ${group} -s /bin/sh ${user}


# Especifica que ejecute las instrucciones como el usuario admin
USER admin:paymentchain

# Directorio de trabajo en la imagen
WORKDIR /app

# Variables local
COPY ./target/*.jar /app/app.jar


EXPOSE 8082
# Variable de entrono
ENV JAVA_OPTS=""
# Levanta una shell y ejecuta el jar
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]

