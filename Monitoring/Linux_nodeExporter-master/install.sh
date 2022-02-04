sudo chmod -R 755 node_exporter-1.1.2.linux-amd64/
cd node_exporter-1.1.2.linux-amd64/

echo ">현재 구동중인 nodeExporter 애플리캐이션 pid 확인"
CURRENT_PID=$(pgrep -f node_exporter)
echo "$CURRENT_PID"
if [ -z $CURRENT_PID ]; then
        echo "> 현재 구동중인 nodeExporter 애플리케이션이 없으므로 종료하지 않습니다."
else
        echo ">kill -9 $CURRENT_PID"
        kill -9 $CURRENT_PID
        sleep 5
fi
echo ">새 nodeExporter 어플리케이션 배포"
echo ">추후, 서비스로 등록할 것"

sudo nohup ./node_exporter --web.listen-address=:9100 &