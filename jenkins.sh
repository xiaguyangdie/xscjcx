docker rmi app-xscjcx:1.0
echo '------rm images--------'
docker build -f docker_file -t app-xscjcx:1.0 .
echo '------build images--------'
app_name='xscjcx'
docker stop ${app_name}
echo '------stop container--------'
docker rm ${app_name}
echo '------rm container----------'
docker run -id --name ${app_name} -p 6000:6000 app-xscjcx:1.0
echo '------start container----------'