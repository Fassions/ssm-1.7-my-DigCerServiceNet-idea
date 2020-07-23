#!/bin/sh
#系统启动脚本，启动后，需要稍等10s左右，等待服务完全启动
serverName="tomcat"
basepath=$(cd `dirname $0`; pwd)

#tags 部署路径
time=$(date +%Y%m%d)
antpath=/opt/application/deploy/deploy
ant=${antpath}/DigCerServiceNet-build.xml
tags=${antpath}/tags/${time}
applicationWar=DigCerServiceNetV2.0.war

digs="bin"

start="startup.sh"
stop="shutdown.sh"

#if test -e $basepath/$digs/$start
#then
#    echo "启动 数字证书服务网"
#    sh $digs/$start
#fi
#echo "请稍候..."
#b=''
#i=0

#while(( $i<=100 ))
#do
#    printf "进度:[%-50s]%d%%\r" $b $i
#    sleep 0.01
#    ((i=2+$i))
#    b=#$b
#done
#echo ""

#echo "启动完成，输入service digCerServiceNet status，查看服务状态。"
start(){
	echo "启动 数字证书服务网"
	sh $basepath/$digs/$start
	echo "请稍候..."
	b=''
	i=0
	while(( $i<=100 ))
	do
		printf "进度:[%-50s]%d%%\r" $b $i
		sleep 0.01
		((i=2+$i))
		b=#$b
	done
	echo ""
	echo "启动完成，输入service DigCerServiceNet status，查看服务状态。"

}
restart(){
stop
start

}
stop(){
  echo "关闭 数字证书服务网"
	sh $basepath/$digs/$stop
	echo "请稍候..."
	b=''
	i=0
	while(( $i<=100 ))
	do
		printf "进度:[%-50s]%d%%\r" $b $i
		sleep 0.01
		((i=2+$i))
		b=#$b
	done
	echo ""
	echo "关闭完成。"
}
deploy(){
    stop
    ant -f ${ant}
    rm -rf webapps/*
	echo "替换${tags}/${applicationWar}"
    cp -rf ${tags}/${applicationWar} webapps/
    start
}
ps x|grep $serverName | grep -v grep

command=${1:-help}
shift

#if [ $? -eq 0 ];
#then
#	stop
#else
#	start
# fi
case $command in
 start)
	start
	;;
 stop)
	stop
	;;
restart)
       restart
       ;;
# deploy)
#       deploy
#       ;;
#  *)
	echo "start-------------->启动服务-------------->命令格式：[./shell.sh start]"
	echo "start-------------->停止服务-------------->命令格式：[./shell.sh stop]"
	echo "start-------------->部署服务-------------->命令格式：[./shell.sh deploy]"
    echo "start-------------->重启服务-------------->命令格式：[./shell.sh restart]"
esac

ps x|grep $serverName | grep -v grep
