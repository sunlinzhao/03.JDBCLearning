linux 环境下在 /etc/mysql/my.cnf 配置文件

数据存储位置默认：/var/lib/mysql

### MySQL配置文件示例

```ini
[mysqld]

# 数据目录位置
datadir=/var/lib/mysql

# 日志文件位置
log-error=/var/log/mysqld.log
pid-file=/var/run/mysqld/mysqld.pid

# InnoDB 相关配置
innodb_buffer_pool_size=128M
innodb_log_file_size=64M
innodb_flush_log_at_trx_commit=2
innodb_file_per_table=ON
innodb_support_xa=ON
innodb_doublewrite=ON
innodb_fast_shutdown=1
innodb_io_capacity=100

# 二进制日志配置
sync_binlog=0

# 其他配置
max_connections=50
wait_timeout=86400
interactive_timeout=28800
```

### 解释

> datadir: MySQL数据文件存放的位置，这里没有更改默认位置。
> log-error: MySQL的日志文件存放位置。
> pid-file: MySQL进程ID文件的位置。
> innodb_buffer_pool_size: InnoDB缓冲池的大小，这里设置为128MB，考虑到服务器只有2GB内存。
> innodb_log_file_size: InnoDB重做日志文件的大小，这里设置为64MB，考虑到服务器的存储限制。
> innodb_flush_log_at_trx_commit: 控制InnoDB日志何时写入磁盘，这里设置为2表示每秒刷新一次日志。
> innodb_file_per_table: 每个InnoDB表使用单独的数据文件。
> innodb_support_xa: 支持XA事务。
> innodb_doublewrite: 启用双写缓冲区，提高数据恢复的安全性。
> innodb_fast_shutdown: 控制InnoDB关闭时的速度和安全性，这里设置为1表示较快但仍相对安全。
> innodb_io_capacity: 控制InnoDB I/O的最大吞吐量，这里设置为100。
> sync_binlog: 控制二进制日志何时同步到磁盘，这里设置为0表示不强制同步，以提高性能。
> max_connections: 最大并发连接数，这里设置为50。
> wait_timeout: 无交互操作的客户端超时时间（秒）。
> interactive_timeout: 有交互操作的客户端超时时间（秒）。

### 如何应用配置

保存配置文件: 保存上述配置文件。
重启MySQL服务: 为了让配置生效，需要重启MySQL服务。

```bash
   sudo systemctl restart mysqld
```

验证配置: 可以使用 SHOW VARIABLES LIKE '...' 命令来验证配置是否生效。

```bash
   SHOW VARIABLES LIKE 'innodb_buffer_pool_size';
   SHOW VARIABLES LIKE 'innodb_flush_log_at_trx_commit';
```

### 注意事项

* 由于服务器的内存和存储资源有限，建议定期监控MySQL的性能和资源使用情况。
* 根据实际的应用负载调整配置项的值。
* 定期备份数据以防止数据丢失。











[mysqld]: 指明此部分的配置针对MySQL服务本身。
[mysqld]: 指明此部分的配置针对MySQL服务本身。
