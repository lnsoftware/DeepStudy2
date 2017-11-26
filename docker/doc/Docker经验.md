

docker设置环境变量

```
ENV PATH="/opt/gtk/bin:${PATH}"
```
https://stackoverflow.com/questions/27093612/in-a-dockerfile-how-to-update-path-environment-variable




### Kernel address maps

运行perf时会报错，docker启动是加上 --privileged

解决报错"Kernel address maps (/proc/{kallsyms,modules}) were restricted. Check /proc/sys/kernel/kptr_restrict before running 'perf record'".

```
sh -c " echo 0 > /proc/sys/kernel/kptr_restrict"
```

http://www.mobibrw.com/date/2017/06/27