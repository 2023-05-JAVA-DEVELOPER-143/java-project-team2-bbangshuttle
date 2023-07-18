DROP TABLE order_item CASCADE CONSTRAINTS;
DROP TABLE orders CASCADE CONSTRAINTS;
DROP TABLE cart CASCADE CONSTRAINTS;
DROP TABLE userinfo CASCADE CONSTRAINTS;
DROP TABLE product CASCADE CONSTRAINTS;

CREATE TABLE product(
		p_no                          		NUMBER(10)		 NULL ,
		p_name                        		VARCHAR2(50)		 NOT NULL,
		p_price                       		NUMBER(10)		 DEFAULT 0		 NOT NULL,
		p_image                       		VARCHAR2(100)		 DEFAULT 'images/no_image.jpg'		 NOT NULL,
		p_desc                        		VARCHAR2(200)		 NULL ,
		p_view_count                  		NUMBER(10)		 DEFAULT 0		 NOT NULL,
		p_category                    		NUMBER(10)		 NULL 
);

DROP SEQUENCE product_p_no_SEQ;

CREATE SEQUENCE product_p_no_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;

CREATE TRIGGER product_p_no_TRG
BEFORE INSERT ON product
FOR EACH ROW
BEGIN
IF :NEW.p_no IS NOT NULL THEN
  SELECT product_p_no_SEQ.NEXTVAL INTO :NEW.p_no FROM DUAL;
END IF;
END;


CREATE TABLE userinfo(
		memberId                      		VARCHAR2(10)		 NULL ,
		memberPassword                		VARCHAR2(10)		 NULL ,
		memberName                    		VARCHAR2(10)		 NULL ,
		memberEmail                   		VARCHAR2(30)		 NULL ,
		memberAddress                 		VARCHAR2(50)		 NULL ,
		memberBirth                   		VARCHAR2(30)		 NULL ,
		memberNumber                  		VARCHAR2(30)		 NULL 
);


CREATE TABLE cart(
		cart_name                     		VARCHAR2(50)		 NULL ,
		cart_qty                      		NUMBER(10)		 DEFAULT 0		 NULL ,
		cart_price                    		NUMBER(10)		 NULL ,
		cart_TotPrice                 		NUMBER(10)		 NULL ,
		memberId                      		VARCHAR2(10)		 NULL 
);


CREATE TABLE orders(
		o_no                          		NUMBER(10)		 NULL ,
		o_date                        		DATE		 DEFAULT sysdate		 NULL ,
		o_price                       		NUMBER(10)		 NULL ,
		o_TotPrice                    		NUMBER(10)		 NULL ,
		o_name                        		VARCHAR2(50)		 NULL ,
		memberId                      		VARCHAR2(10)		 NULL 
);

DROP SEQUENCE orders_o_no_SEQ;

CREATE SEQUENCE orders_o_no_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;

CREATE TRIGGER orders_o_no_TRG
BEFORE INSERT ON orders
FOR EACH ROW
BEGIN
IF :NEW.o_no IS NOT NULL THEN
  SELECT orders_o_no_SEQ.NEXTVAL INTO :NEW.o_no FROM DUAL;
END IF;
END;


CREATE TABLE order_item(
		oi_no                         		NUMBER(10)		 NULL ,
		oi_qty                        		NUMBER(10)		 NULL ,
		o_no                          		NUMBER(10)		 NULL ,
		p_no                          		NUMBER(10)		 NULL 
);

DROP SEQUENCE order_item_oi_no_SEQ;

CREATE SEQUENCE order_item_oi_no_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;

CREATE TRIGGER order_item_oi_no_TRG
BEFORE INSERT ON order_item
FOR EACH ROW
BEGIN
IF :NEW.oi_no IS NOT NULL THEN
  SELECT order_item_oi_no_SEQ.NEXTVAL INTO :NEW.oi_no FROM DUAL;
END IF;
END;



ALTER TABLE product ADD CONSTRAINT IDX_product_PK PRIMARY KEY (p_no);

ALTER TABLE userinfo ADD CONSTRAINT IDX_userinfo_PK PRIMARY KEY (memberId);

ALTER TABLE cart ADD CONSTRAINT IDX_cart_PK PRIMARY KEY (cart_name, memberId);
ALTER TABLE cart ADD CONSTRAINT IDX_cart_FK0 FOREIGN KEY (memberId) REFERENCES userinfo (memberId);

ALTER TABLE orders ADD CONSTRAINT IDX_orders_PK PRIMARY KEY (o_no, memberId);
ALTER TABLE orders ADD CONSTRAINT IDX_orders_FK0 FOREIGN KEY (memberId) REFERENCES userinfo (memberId);

ALTER TABLE order_item ADD CONSTRAINT IDX_order_item_PK PRIMARY KEY (oi_no);
ALTER TABLE order_item ADD CONSTRAINT IDX_order_item_FK1 FOREIGN KEY (p_no) REFERENCES product (p_no);

