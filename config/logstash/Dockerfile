FROM docker.elastic.co/logstash/logstash-oss:8.15.1

RUN curl https://jdbc.postgresql.org/download/postgresql-42.7.3.jar -o /usr/share/logstash/postgresql-42.7.3.jar

RUN rm /usr/share/logstash/pipeline/logstash.conf

ADD *.conf /usr/share/logstash/pipeline/
