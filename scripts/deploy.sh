BUILD_JAR=$(ls /home/ec2-user/action/build/libs/roulette-0.0.1-SNAPSHOT.jar)
JAR_NAME=$(basename $BUILD_JAR)
LOG_FILE="/home/ec2-user/action/deploy.log"
DEPLOY_PATH="/home/ec2-user/action/"

# 현재 날짜 및 시간을 로그에 추가
echo "$(date '+%Y-%m-%d %H:%M:%S') > build 파일명: $JAR_NAME" >> $LOG_FILE
echo "$(date '+%Y-%m-%d %H:%M:%S') > build 파일 복사" >> $LOG_FILE

cp $BUILD_JAR $DEPLOY_PATH

echo "$(date '+%Y-%m-%d %H:%M:%S') > 현재 실행중인 애플리케이션 pid 확인" >> $LOG_FILE
CURRENT_PID=$(pgrep -f $JAR_NAME)

if [ -z $CURRENT_PID ]
then
  echo "$(date '+%Y-%m-%d %H:%M:%S') > 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다." >> $LOG_FILE
else
  echo "$(date '+%Y-%m-%d %H:%M:%S') > kill $CURRENT_PID" >> $LOG_FILE
  kill $CURRENT_PID
  sleep 5
fi

DEPLOY_JAR=$DEPLOY_PATH$JAR_NAME
echo "$(date '+%Y-%m-%d %H:%M:%S') > DEPLOY_JAR 배포" >> $LOG_FILE
nohup java -jar -Dspring.profiles.active=dev $DEPLOY_JAR >> /home/ec2-user/deploy.log 2> $DEPLOY_PATH/deploy_err.log &
