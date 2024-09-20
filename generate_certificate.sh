#!/bin/bash
docker run --rm -v $(pwd)/certs:/certs -u root -v .:/usr/share/elasticsearch/config/certificates docker.elastic.co/elasticsearch/elasticsearch:7.17.24  bin/elasticsearch-certutil cert --in config/certificates/instances.yml --pem -out /certs/bundle.zip
unzip certs/bundle.zip -d certs
docker run --rm --entrypoint cat openjdk:17-jdk-alpine /etc/ssl/certs/java/cacerts > cacerts
docker run --rm -v `pwd`:/tmp/certs openjdk:17-jdk-alpine sh -c 'cd /tmp/certs && keytool -keystore cacerts -storepass changeit -noprompt -trustcacerts -importcert -alias elastic -file certs/es01/es01.crt'
