-- delete
delete from userinfo where member_id='kimshuttle11';

-- update
update userinfo set 
    member_password='1234',
    member_name='김변경',
    member_email='kimbyun11',
    member_address='LA',
    member_birth='1940/02/01',
    member_number='010-xxxx-xxxx',
    member_regdate=sysdate,
    member_point=0
where member_id='kimshuttle11';


-- selete pk
select member_id, 
       member_password, 
       member_name, 
       member_email, 
       member_address, 
       member_birth, 
       member_number, 
       member_regdate, 
       member_point
from userinfo where member_id = 'parkshuttle33';

-- select all
select member_id, 
       member_password, 
       member_name, 
       member_email, 
       member_address, 
       member_birth, 
       member_number, 
       member_regdate, 
       member_point
from userinfo;

commit;
desc userinfo;