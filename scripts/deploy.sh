BUILD_JAR=$(ls /home/ec2-user/action/build/libs/roulette-0.0.1-SNAPSHOT.jar)
JAR_NAME=$(basename $BUILD_JAR)
LOG_FILE="/home/ec2-user/action/deploy.log"

echo "$(date '+%Y-%m-%d %H:%M:%S') > build 파일명: $JAR_NAME" >> $LOG_FILE
echo "$(date '+%Y-%m-%d %H:%M:%S') > build 파일 복사" >> $LOG_FILE

DEPLOY_PATH=/home/ec2-user/action/
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

# 디렉토리로 이동
cd $DEPLOY_PATH

DEPLOY_JAR=roulette-0.0.1-SNAPSHOT.jar
echo "$(date '+%Y-%m-%d %H:%M:%S') > DEPLOY_JAR 배포"    >> $LOG_FILE
nohup java -jar -Dspring.profiles.active=dev $DEPLOY_JAR >> /home/ec2-user/deploy.log 2>/home/ec2-user/action/deploy_err.log &
