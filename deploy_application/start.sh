#!/bin/sh

#/**
# * 使用自己的镜像启动自己的应用程序测试后，再咨询，
# * 启动方式，使用镜像id创建ecs，然后讲application.zip拷贝到/tmp目录，
# * 将start.sh拷贝到 ～ 然后使用 start.sh default完成部署和首次运行，
# * 以及start.sh run来运行，完成后再提交，如果没有测试，需要自己测试，
# * 测试完成后，如果还是0分，再at我。另外需要考虑程序的启动时间，可以再
# * start.sh的run中进行sleep比如30秒或者check一下是否启动
# *
# */

# shellcheck disable=SC2039
if [[ $# != 1 ]]; then
   echo "USAGE $0 option<deploy | run>"
   exit 1
fi
option=$1
echo $1
zip_file_name="application.zip"
app_file_name="im-webapp-0.0.1-SNAPSHOT.jar"

# shellcheck disable=SC2120
# shellcheck disable=SC2112
function deploy() {
  cd /tmp || exit
  unzip "${zip_file_name}" -d ~/
  unzip "${zip_file_name}" -d /tmp/
  mv /tmp/"${app_file_name}" ~/
  cd ~ || exit
  # +x and execute
  chmod +x "${app_file_name}"
}

# shellcheck disable=SC2120
# shellcheck disable=SC2112
function run() {
  cd ~ || exit
  start
}
start() {
    echo "start ..."
    nohup java -XX:+UseConcMarkSweepGC \
    -Xms5120m -Xmx5120m -XX:+PrintGCDateStamps \
    -Dfile.encoding=UTF-8 -Dsun.jnu.encoding=UTF-8 -Duser.timezone=Asia/Shanghai \
    -XX:+HeapDumpOnOutOfMemoryError -jar ~/"${app_file_name}" \
    --isJar=true > ./im.log 2>&1 &
    service mysqld start
    sleep 15;
    warmup
    echo "start end ..."
}
warmup(){
  curl -X 'GET' \
  'http://localhost:8080/userLogin?username=1&password=1' \
  -H 'accept: */*';
}

# shellcheck disable=SC2166
if [ "$option" == 'deploy' ]
then
  deploy
elif [ "$option" == 'run' ]
then
  run
else
  deploy
  run
fi


