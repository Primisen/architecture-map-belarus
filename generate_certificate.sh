#!/bin/bash

# run `export container_exec="your_execution_command"` to set exec command, otherwise docker will be used
if [[ -z $container_exec ]];
then
  export container_exec="docker"
fi

$container_exec run --rm -v $(pwd)/certs:/certs -u root -v .:/usr/share/elasticsearch/config/certificates docker.elastic.co/elasticsearch/elasticsearch:7.17.24  bin/elasticsearch-certutil cert --in config/certificates/instances.yml --pem -out /certs/bundle.zip
sudo unzip certs/bundle.zip -d certs
$container_exec run --rm --entrypoint cat openjdk:17-jdk-alpine /etc/ssl/certs/java/cacerts > cacerts
$container_exec run --rm -v `pwd`:/tmp/certs openjdk:17-jdk-alpine sh -c 'cd /tmp/certs && keytool -keystore cacerts -storepass changeit -noprompt -trustcacerts -importcert -alias elastic -file certs/es01/es01.crt'
