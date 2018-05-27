#!/bin/sh


mvn clean package

echo 'Copy files...'

scp target/MsgSpringBoot-1.0-SNAPSHOT.jar \
    user@192.168.1.52:/home/user/

echo 'Restart server.....'

ssh user@192.168.1.52 << EOF

pgrep java | xargs kill -9
nohup java -jar MsgSpringBoot-1.0-SNAPSHOT.jar > log.txt &
EOF

echo 'Bye'
