#!/bin/bash
mvn clean install -Dmaven.test.skip=true;

cp /Users/lewis/repo/tianchi-im/im-webapp/target/im-webapp-0.0.1-SNAPSHOT.jar /Users/lewis/repo/tianchi-im/deploy_application/application/

cd /Users/lewis/repo/tianchi-im/deploy_application;

zip -r application.zip /Users/lewis/repo/tianchi-im/deploy_application/application/;

rm -rf /Users/lewis/repo/tianchi-im/deploy_application/application/*;


