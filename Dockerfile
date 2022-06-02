FROM alpine as build

ARG MAVEN_VERSION=3.8.5
ARG USER_HOME_DIR="/root"
ARG BASE_URL=https://apache.osuosl.org/maven/maven-3/${MAVEN_VERSION}/binaries

RUN apk --update --no-cache add openjdk17 curl

RUN mkdir -p /usr/share/maven /usr/share/maven/ref \
 && curl -fsSL -o /tmp/apache-maven.tar.gz ${BASE_URL}/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
 && tar -xzf /tmp/apache-maven.tar.gz -C /usr/share/maven --strip-components=1 \
 && rm -f /tmp/apache-maven.tar.gz \
 && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"

# Define working directory.
#WORKDIR /data
WORKDIR /usr/app
# Define commonly used JAVA_HOME variable
ENV JAVA_HOME /usr/lib/jvm/default-jvm/

# Define default command.
CMD ["mvn", "--version"]

COPY . /usr/app/

#Run tests
RUN mvn clean package


#COPY ./target/gildedrose-0.0.1-SNAPSHOT.jar /usr/app/
ENV  spring.datasource.url=jdbc:postgresql://172.17.0.2:5432/postgres
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/target/gildedrose-0.0.1-SNAPSHOT.jar"]