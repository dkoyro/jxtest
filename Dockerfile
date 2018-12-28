FROM openjdk:8-jdk-slim
ENV PORT 8080
ENV CLASSPATH /opt/lib
EXPOSE 8080

# copy pom.xml and wildcards to avoid this command failing if there's no target/lib directory
#COPY pom.xml target/lib* /opt/lib/
ADD pom.xml target/lib* /opt/lib/

# NOTE we assume there's only 1 jar in the target dir
# but at least this means we don't have to guess the name
# we could do with a better way to know the name - or to always create an app.jar or something
WORKDIR /opt
#COPY target/*.jar /opt/jxtest.jar
ADD target/*.jar /opt/jxtest.jar
ENTRYPOINT ["java", "-jar", "jxtest.jar"]