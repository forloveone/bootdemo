# 基于哪个镜像
FROM java:8
# 将本地文件夹挂载到当前容器
VOLUME /tmp
# 拷贝文件到容器
ADD art-0.0.1.jar art-0.0.1.jar
RUN bash -c 'touch /art-0.0.1.jar'
# 配置容器启动后执行的命令
ENTRYPOINT  ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/art-0.0.1.jar"]
EXPOSE 8761

# 没有验证可能有用   !!! dockerfile写法