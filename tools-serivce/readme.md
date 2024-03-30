1. 镜像打包  
这里使用buildx是因为开发机是arm但是服务器是x86
```shell
docker buildx build --platform=linux/amd64 -t yuebanhutools:0.3 .
```
2. 登录远端仓库  
```shell
docker login --username=niwengang@outlook.com registry.cn-hangzhou.aliyuncs.com
```
3. 修改tag  
```shell
 docker tag yuebanhutools:0.3 registry.cn-hangzhou.aliyuncs.com/niwengang/yuebanhutools:0.3
```
4. 推送
```shell
docker push  registry.cn-hangzhou.aliyuncs.com/niwengang/yuebanhutools:0.3
```