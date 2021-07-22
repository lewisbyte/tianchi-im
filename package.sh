#!/bin/bash
mvn clean install -Dmaven.test.skip=true;

cp /Users/lewis/repo/tianchi-im-vert.x/target/tianchi-im-1.0.0-SNAPSHOT-fat.jar /Users/lewis/repo/tianchi-im-vert.x/deploy_application/

cd /Users/lewis/repo/tianchi-im-vert.x/deploy_application/;

zip -r application.zip tianchi-im-1.0.0-SNAPSHOT-fat.jar;

zip -r deploy_application.zip ./*;

mv deploy_application.zip /Users/lewis/Desktop/