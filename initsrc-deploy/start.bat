!/bin/bash

PID=`ps -ef | grep initsrc-base.jar | grep -v grep | awk '{print $2}'`
if [ -z "$PID" ]
then
    echo Application is already stopped
    mv initsrc-base.jar back/
    rz 
    nohup java -jar  initsrc-base.jar --spring.profiles.active=prod &


else
	mv initsrc-base.jar back/
    rz
    echo kill $PID
    kill -9 $PID
    nohup java -jar  initsrc-base.jar --spring.profiles.active=prod &
fi
