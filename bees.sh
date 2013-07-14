#!/bin/bash

mvn clean install && bees app:deploy \
 -a retour1024/vote \
 -t java \
 -R class=net.codestory.vote.Main \
 -R java_version=1.8 \
 -R JAVA_OPTS="-DPROD_MODE=true -Dmongo.uri=mongodb://cloudbees:5151d5a3d9dbfec9182aca7c0d474b5c@dharma.mongohq.com:10004/Yo0I0g6UUgZ5PEZI3qfl8Q" \
 -R classpath='classes:lib/*' target/distribution.zip