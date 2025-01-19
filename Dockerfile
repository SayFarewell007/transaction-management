# 使用官方的OpenJDK镜像作为基础镜像
FROM eclipse-temurin:21-jre

# 设置工作目录
WORKDIR /app

# 将Maven打包生成的JAR文件复制到镜像中
COPY target/transaction-management-0.0.1-SNAPSHOT.jar /app/transaction-management.jar

# 暴露应用的端口
EXPOSE 8080

# 定义启动命令
CMD ["java", "-jar", "transaction-management.jar"]
