#!/bin/bash

mvn clean install && bees app:deploy -a retour1024/vote -t java -R class=net.codestory.vote.MainVote -R java_version=1.8 -R classpath='classes:lib/*' target/distribution.zip