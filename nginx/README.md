
 
## build dockerfile

sudo docker build -t="huiwq1990/openresty" .

## ���빤��Ŀ¼����������

docker run -t -i -p 8080:8080 -v `pwd`:/workspace -w=/workspace huiwq1990/openresty 
����dockerʱ���ѵ�ǰĿ¼ӳ�䵽/workspace�������õ�ǰ����Ŀ¼Ϊ����

## container introduce

��dockerfile�ļ������nginx����������Ϊ��

```
CMD /usr/local/openresty/nginx/sbin/nginx -p `pwd` -c conf/nginx.conf
```

nginx����ʱ���õ������ļ�Ϊ��ǰĿ¼��conf/nginx.conf�ļ����������ǹ���Ŀ¼�����и�����ļ���



Dockerfile�ο�
https://github.com/torhve/openresty-docker


