sudo chmod -R 755 MonitoringInfras/
cd MonitoringInfras/

cd grafana-7.5.5/
cd bin/
echo ">현재 구동중인 grafana 애플리캐이션 pid 확인"
CURRENT_PID=$(pgrep -f grafana-server)
echo "$CURRENT_PID"
if [  -z $CURRENT_PID  ]; then
        echo "> 현재 구동중인 grafana 애플리케이션이 없으므로 종료하지 않습니다."
else
        echo ">kill -9 $CURRENT_PID"
        kill -9 $CURRENT_PID
        sleep 5
        echo ">grafana 삭제완료"
fi


cd ..
cd ..
cd prometheus-2.26.0.linux-amd64/
echo ">현재 구동중인 prometheus 애플리캐이션 pid 확인"
CURRENT_PID=$(pgrep -f prometheus)
echo "$CURRENT_PID"
if [  -z $CURRENT_PID  ]; then
        echo "> 현재 구동중인 prometheus 애플리케이션이 없으므로 종료하지 않습니다."
else
        echo ">kill -9 $CURRENT_PID"
        kill -9 $CURRENT_PID
        sleep 5
        echo ">prometheus 삭제완료"
fi





