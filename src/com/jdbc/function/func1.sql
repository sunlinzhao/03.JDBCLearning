delimiter $$
create function func1(x int)
    returns varchar(50) -- 返回值类型
    reads sql data -- 是 MySQL 存储过程和函数中的一种权限修饰符，用于标识该存储过程或函数只读取数据库的数据而不修改它
begin
    declare result_id varchar(50); -- 声明变量
    select id into result_id from user where name=x;
    return result_id;
end$$
delimiter ;