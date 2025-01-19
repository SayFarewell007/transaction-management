#!/bin/bash

# 设置项目根目录
PROJECT_ROOT=$(pwd)

# 设置JAR文件名
JAR_FILE_NAME="transaction-management-0.0.1-SNAPSHOT.jar"

# 设置Docker镜像名和标签
DOCKER_IMAGE_NAME="transaction-management"
DOCKER_IMAGE_TAG="latest"

# 步骤一：Maven打包
echo "开始Maven打包..."
mvn clean package
if [ $? -ne 0 ]; then
    echo "Maven打包失败，退出脚本。"
    exit 1
fi
echo "Maven打包成功。"

# 步骤二：构建Docker镜像
echo "开始构建Docker镜像..."
docker build -t $DOCKER_IMAGE_NAME:$DOCKER_IMAGE_TAG .
if [ $? -ne 0 ]; then
    echo "Docker镜像构建失败，退出脚本。"
    exit 1
fi
echo "Docker镜像构建成功。"

# 步骤三：运行Docker容器
echo "开始运行Docker容器..."
docker run -d -p 8080:8080 --name transaction-management-container $DOCKER_IMAGE_NAME:$DOCKER_IMAGE_TAG
if [ $? -ne 0 ]; then
    echo "Docker容器运行失败，退出脚本。"
    exit 1
fi
echo "Docker容器运行成功。"
