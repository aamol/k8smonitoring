# Kubernetes Monitoring app

[![Build Status](https://travis-ci.org/aamol/k8smonitoring.svg?branch=master)](https://travis-ci.org/aamol/k8smonitoring)

**Why ?** 
Using the kubernetes and having multiple environments sometime has an overhead of maintaining what is deployed in which environment hence this app.

**What ?** 

You can configure the environments(Currently only username and password is supported) and then it will fetch all the deployments from the environments along with the image version

**How ?**

This is a sping boot based application which uses mongo to store the credentails. Hence to start the application pull the image from k8smonitoring and mongo from docker hub and start using . here are the commands

```
docker run --name monitoring-mongo -v /app/mongo/datadir:/data/db -d mongo 
docker run --link monitoring-mongo:monitoring-mongo -e SPRING_DATA_MONGODB_HOST=monitoring-mongo --name k8smonitoring -p 8080:8080 -d aamol/k8smonitoring:<tag>
```
