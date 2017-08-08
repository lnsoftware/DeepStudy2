
cd broker

docker build -t mqbroker .

cd ..

cd namesrv

docker build -t mqnamesrv .

cd ..

cd console

docker build -t mqconsole .
