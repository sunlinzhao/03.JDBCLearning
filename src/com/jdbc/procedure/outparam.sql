create procedure outparam(in n int, out i varchar(50))
begin
    set i = (select id from user where name = n);
end;