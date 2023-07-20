--leeshuttle22 멤버한사람의 카트아이템리스트
select * from 
cart c join userinfo u on c.member_id = u.member_id 
	   join product p on p.p_no = c.p_no where u.member_id = 'leeshuttle22';
       

