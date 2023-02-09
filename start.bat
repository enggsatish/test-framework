echo off
set ENV=%1
IF [%1]==[] (
set ENV=dev
)
java -Xms256m -Xmx512m -server -Xloggc:logs/gc.log -verbose:gc -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=logs -Dlogging.config=config/log4j2.xml -Dspring.config.location=config/application.properties -jar lib/hpe.tfm.restservice-0.0.1-SNAPSHOT.jar