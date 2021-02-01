FROM adoptopenjdk/openjdk11:alpine

LABEL maintainer="info@virtualan.io"

ENV MAVEN_VERSION 3.2.5

RUN apk add --update \
    curl \
    && rm -rf /var/cache/apk/*
    
RUN curl -sSL http://archive.apache.org/dist/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz | tar xzf - -C /usr/share \
  && mv /usr/share/apache-maven-$MAVEN_VERSION /usr/share/maven \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

ENV MAVEN_HOME /usr/share/maven

ENV WK_HOME .

COPY . /data/idaithalam-server
WORKDIR /data/idaithalam-server

RUN ["mvn", "clean", "install"]

COPY target/idaiserver-0.1.0.jar  $WK_HOME/

WORKDIR $WK_HOME

ADD idaiserver-0.1.0.jar /openapi/virtualan/idaiserver.jar

ENTRYPOINT ["java", "-jar", "/openapi/virtualan/idaiserver.jar"] 
