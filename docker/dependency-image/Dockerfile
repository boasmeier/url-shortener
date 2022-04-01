FROM tomcat:8.5.77-jdk17-temurin-focal as dependencies
RUN apt-get update && apt-get -y upgrade  && \
    apt-get -y install maven && \
    apt-get -y install openjdk-17-jdk && \
    apt-get -y install wget
RUN wget https://dlcdn.apache.org/maven/maven-3/3.8.5/binaries/apache-maven-3.8.5-bin.tar.gz -P /tmp && \
    tar xf /tmp/apache-maven-*.tar.gz -C /opt && \
    ln -s /opt/apache-maven-3.8.5 /opt/maven
ENV M2_HOME=/opt/maven
ENV MAVEN_HOME=/opt/maven
ENV PATH=${M2_HOME}/bin:${PATH}
