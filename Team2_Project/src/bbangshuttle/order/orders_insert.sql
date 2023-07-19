/**********************orders insert************************/
/*
이름         널?       유형            
---------- -------- ------------- 
O_NO       NOT NULL NUMBER(10)    
O_DATE              DATE          
O_PRICE             NUMBER(10)    
O_DESC              VARCHAR2(100) 
MEMBER_ID           VARCHAR2(10)  
*/
-- ORDER_INSERT
insert into orders(o_no,o_date,o_price,o_desc,member_id) 
VALUES (orders_o_no_SEQ.nextval, sysdate, 12000, '촉촉한쵹호칩', 'kimshuttle11');

insert into orders(o_no,o_date,o_price,o_desc,member_id) 
VALUES (orders_o_no_SEQ.nextval, sysdate, 13000, '파리도좋아하는 바게뜨', 'leeshuttle22');

insert into orders(o_no,o_date,o_price,o_desc,member_id) 
VALUES (orders_o_no_SEQ.nextval, sysdate, 5000, '따뜻한 아이스 아메리카노', 'parkshuttle33');

insert into orders(o_no,o_date,o_price,o_desc,member_id) 
VALUES (orders_o_no_SEQ.nextval, sysdate, 19000, '이퇄~리안 피자빵', 'choishuttle44');

insert into orders(o_no,o_date,o_price,o_desc,member_id) 
VALUES (orders_o_no_SEQ.nextval, sysdate, 7000, '카라멜레온마끼아토', 'hanshuttle155');

/****************************************************************/
/*
이름     널?       유형         
------ -------- ---------- 
OI_NO  NOT NULL NUMBER(30) 번호
OI_QTY          NUMBER(30) 수량
O_NO            NUMBER(30) 주문번호
P_NO            NUMBER(30) 상품번호
*/

-- ORDERITEM_INSERT
insert into order_item(oi_no, oi_qty, o_no, p_no) 
VALUES(order_item_oi_no_SEQ.nextval, 1, orders_o_no_SEQ.currval, 1 ); 

insert into order_item(oi_no, oi_qty, o_no, p_no) 
VALUES(order_item_oi_no_SEQ.nextval, 1, orders_o_no_SEQ.currval, 1 ); 

/****************************************************************/