create procedure multi_result_set(x int)
begin
    select * from user where name=x;
    select * from user where name=2*x;
    select * from user where name=3*x;
end;