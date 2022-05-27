FROM openjdk:17-alpine
RUN apk update && apk upgrade && apk add wget
RUN wget https://netix.dl.sourceforge.net/project/hsqldb/hsqldb/hsqldb_2_6/hsqldb-2.6.1.zip -P /tmp && \
    unzip /tmp/hsqldb-*.zip -d /opt
WORKDIR /opt/hsqldb-2.6.1/hsqldb/data
EXPOSE 9001
CMD ["java", "-cp", "../lib/hsqldb.jar", "org.hsqldb.server.Server", "--database.0", "file:mydb", "--dbname.0", "db"]