ARG DEPENDENCIES_IMAGE
FROM ${DEPENDENCIES_IMAGE}
WORKDIR /opt/repo
COPY . .
RUN mvn package -B
RUN rm -fr /usr/local/tomcat/webapps/ROOT
RUN cp ./target/url-shortener-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
CMD ["catalina.sh", "run"]