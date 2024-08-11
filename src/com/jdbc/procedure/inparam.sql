create procedure inparam(n int)
begin
    select * from user where name=n;
end;