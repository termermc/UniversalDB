# UniversalDB
No-nonsense, easy to use database library with support for MySQL, PostgreSQL, and more

# "Easy to use"?
Connecting to a database and running a query can be done in 3 lines.
Example code for connecting to a MySQL database and printing a table to the console:
```java
SQLDatabaseAdapter db = new MySQLDatabaseAdapter("localhost",
                                                 "my-database",
                                                 "username",
                                                 "password");
db.connect();
System.out.println(db.executeQuery("SELECT * FROM `my-database`.`my-table`").toString());
```

# Why not just use JDBC?
While JDBC is useful, it requires lots of boilerplate code that makes even doing the simplest of tasks a pain to write. With UDB, you can connect to a database and execute queries quickly, without any unnecesary setup. Plus, if you find you need to access a JDBC API, you can simply getJDBCResultSet() on [QueryResults](https://github.com/TermerMC/UniversalDB/blob/master/net/termer/udb/QueryResult.java), and getConnection() on [SQLDatabaseAdapters](https://github.com/TermerMC/UniversalDB/blob/master/net/termer/udb/sql/SQLDatabaseAdapter.java).

# Javadoc, please
[Here you go!](https://termer.net/javadocs/universaldb/);

# Where can I learn more?
Visit the [wiki](https://github.com/TermerMC/UniversalDB/wiki) to learn more and how to use UDB!

# Planned features
Currently, integration with HikariCP is in the works. When finished, it should work seamlessly with the existing MySQLDatabaseAdapter class.