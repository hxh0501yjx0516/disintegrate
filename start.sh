#!/bin/sh
PRG="$0"
source /etc/profile
while [ -h "$PRG" ]; do
  ls=`ls -ld "$PRG"`
  link=`expr "$ls" : '.*-> \(.*\)$'`
  if expr "$link" : '/.*' > /dev/null; then
    PRG="$link"
  else
    PRG=`dirname "$PRG"`/"$link"
  fi
done

# Get standard environment variables
PRGDIR=`dirname "$PRG"`


[ -z "$APP_HOME" ] && APP_HOME=`cd "$PRGDIR" >/dev/null; pwd`
[ -z "$APP_PID" ] && APP_PID=/var/run/disintegrate.pid


FILE_LIST=`ls  ${APP_HOME}`

JAR_FILE=""
LOG_FILE=""

for FILE in $FILE_LIST
do
	if [ "${FILE##*.}" = "jar" ]; then
		JAR_FILE="${APP_HOME}/${FILE}"
		LOG_FILE="${FILE%.*}.log"
	fi
done

if [ -z $JAR_FILE ]; then
	echo "Error : no jar file in $APP_HOME"
	exit
fi

if [ -f "$APP_PID" ]; then
	PID=`cat $APP_PID`
  rm -rf $APP_PID
  kill -9 $PID
fi

OPTS="-Xms3096m -Xmx3096m -XX:PermSize=256m -XX:MaxPermSize=256m  -XX:SurvivorRatio=8 -Xmn1025m -XX:OldSize=1536m -Xss512k  -XX:+AggressiveOpts -XX:+UseBiasedLocking -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+UseParNewGC  -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -XX:+PrintGCDateStamps -XX:+PrintGCDetails -Xloggc:gc.log -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./ "
nohup java $DEBUG -jar $OPTS ${JAR_FILE}  >>$APP_HOME/${LOG_FILE} 2>&1 &
#nohup java $DEBUG -jar -Xmx768m ${JAR_FILE}  >>$APP_HOME/${LOG_FILE} 2>&1 &
PID=$!
echo $PID > ${APP_PID}
if [ ! -f $APP_HOME/${LOG_FILE} ]; then
	echo "begin....." >>$APP_HOME/${LOG_FILE}
fi
tail -fn 400 $APP_HOME/${LOG_FILE}

