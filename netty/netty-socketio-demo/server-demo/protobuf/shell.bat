cd /D D:\SoftwareForCode\MyEclipseProject\NettyDemo\protobuf
protoc --java_out=../src TestModel.proto
copy D:\SoftwareForCode\MyEclipseProject\NettyDemo\src\com\xiyuan\demo\model\TestModel.java D:\SoftwareForCode\MyEclipseProject\NettyDemoClient\src\com\xiyuan\demo\model\
pause