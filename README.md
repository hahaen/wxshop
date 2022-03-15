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