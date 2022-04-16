**微型电商项目**

**预览地址：**[http://hahaen.xyz:5000](http://hahaen.xyz:5000)

* 创建数据库

```
docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=wxshop mysql
```

* 测试

```
docker run -d -p 3307:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=wxshop mysql
```

```
winpty docker exec -it 9734ef38 mysql -uroot -proot -e 'create database if not exists `order`'
```

* redis

```
docker run -p 6379:6379 -d redis
```

* zookeeper

```
docker run -p 2181:2181 -d zookeeper
```

* Flyway

```
 mvn flyway:migrate -pl wxshop-main
```

```
 mvn flyway:migrate -pl wxshop-order
```

* MyBatis Generator

```
mvn mybatis-generator:generate -pl wxshop-main
```

```
mvn mybatis-generator:generate -pl wxshop-order
```

* 检查

```
mvn clean verify
```

* 注意：无法使用flyway:migrate mybatis-generator:generate先创建这个

```
mvn install -DskipTest
```

* apidoc

```
apidoc -i wxshop-main/src/main/java/com/hahaen/wxshop/controller -o doc
```

* nginx

```
events{}
http {
    include    mime.types;
    upstream app {
        server xxx:8080;
        server xxx:8081;
    }
    server{
        location /api {
            proxy_pass http://app;
        }
        location / {
            root   /static;
            autoindex on;
        }
     }
}
```

* 服务器

```
./mvnw install -DskipTests
```

```
tmux new -s 0
tmux attach -t 0
```

```
java -Dserver.port=8082 -jar wxshop-order/target/wxshop-order-0.0.1-SNAPSHOT.jar
java -jar wxshop-main/target/wxshop-main-0.0.1-SNAPSHOT.jar
```
