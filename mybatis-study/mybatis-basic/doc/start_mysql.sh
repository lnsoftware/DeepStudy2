userName=`whoami`
echo $userName
# -v会把当前目录都映射，在MySQL Docker执行命令的时候，这个shell文件也会执行。具体看MySQL镜像
if [ "$userName"x = "hg"x ]; then
docker ps -aq -f status=exited | xargs docker rm -f
docker ps -aq -f name=mybatis-study | xargs docker rm -f
docker run --name mybatis-study  -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=test  -p 3306:3306 -v `pwd`:/docker-entrypoint-initdb.d/ -i -t mysql:5.6
fi