FROM tomcat:9.0.56-jdk17-temurin
RUN rm -fr /usr/local/tomcat/webapps/ROOT
COPY ./target/url-shortener-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
CMD ["catalina.sh", "run"]
