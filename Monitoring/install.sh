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
fi
echo ">새 grafana 어플리케이션 배포"
echo ">추후, 서비스로 등록할 것"
sudo nohup ./grafana-server &

cd ..
cd ..
cd prometheus-2.26.0.linux-amd64/
echo ">현재 구동중인 prometheus 애플리캐이션 pid 확인"
CURRENT_PID=$(pgrep -f prometheus)
echo "$CURRENT_PID"
if [ -z $CURRENT_PID ]; then
        echo "> 현재 구동중인 prometheus 애플리케이션이 없으므로 종료하지 않습니다."
else
        echo ">kill -9 $CURRENT_PID"
        kill -9 $CURRENT_PID
        sleep 5
fi
echo ">새 prometheus 어플리케이션 배포"
echo ">추후, 서비스로 등록할 것"
sudo nohup ./prometheus --config.file=prometheusConfig/prometheus.yml --web.listen-address=:9090 --storage.tsdb.retention.time=15d --web.enable-admin-api &





