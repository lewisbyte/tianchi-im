#!/bin/bash
mvn clean install -Dmaven.test.skip=true;

cp /Users/lewis/repo/tianchi-im/im-webapp/target/im-webapp-0.0.1-SNAPSHOT.jar /Users/lewis/repo/tianchi-im/deploy_application/

cd /Users/lewis/repo/tianchi-im/deploy_application/;

zip -r application.zip im-webapp-0.0.1-SNAPSHOT.jar;

rm -rf /Users/lewis/repo/tianchi-im/deploy_application/im-webapp-0.0.1-SNAPSHOT.jar;

zip -r deploy_application.zip ./*;

mv deploy_application.zip /Users/lewis/Desktop/