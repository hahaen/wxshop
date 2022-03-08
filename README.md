**微型电商项目**

* 创建数据库

```
docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=wxshop mysql
```

```
winpty docker exec -it 9734ef38 mysql -uroot -proot -e 'create database if not exists `order`'
```

* 测试

```
docker run -d -p 3307:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=wxshop mysql
```

* 注意：无法使用flyway:migrate mybatis-generator:generate先创建这个

```
mvn install -DskipTest
```