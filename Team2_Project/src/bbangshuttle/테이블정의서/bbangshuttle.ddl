DROP TABLE order_item CASCADE CONSTRAINTS;
DROP TABLE orders CASCADE CONSTRAINTS;
DROP TABLE cart CASCADE CONSTRAINTS;
DROP TABLE userinfo CASCADE CONSTRAINTS;
DROP TABLE product CASCADE CONSTRAINTS;

/**********************************/
/* Table Name: product */
/**********************************/
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

COMMENT ON TABLE product is 'product';
COMMENT ON COLUMN product.p_no is 'p_no';
COMMENT ON COLUMN product.p_name is 'p_name';
COMMENT ON COLUMN product.p_price is 'p_price';
COMMENT ON COLUMN product.p_image is 'p_image';
COMMENT ON COLUMN product.p_desc is 'p_desc';
COMMENT ON COLUMN product.p_view_count is 'p_view_count';
COMMENT ON COLUMN product.p_category is 'p_category';


/**********************************/
/* Table Name: userinfo */
/**********************************/
CREATE TABLE userinfo(
		member_Id                     		VARCHAR2(10)		 NULL ,
		member_password               		VARCHAR2(10)		 NULL ,
		member_name                   		VARCHAR2(10)		 NULL ,
		member_email                  		VARCHAR2(30)		 NULL ,
		member_address                		VARCHAR2(50)		 NULL ,
		member_birth                  		VARCHAR2(30)		 NULL ,
		member_number                 		VARCHAR2(30)		 NULL ,
		member_regdate                		DATE		 DEFAULT sysdate		 NULL ,
		member_point                  		NUMBER(10)		 DEFAULT 0		 NULL 
);

COMMENT ON TABLE userinfo is 'userinfo';
COMMENT ON COLUMN userinfo.member_Id is 'member_Id';
COMMENT ON COLUMN userinfo.member_password is 'member_password';
COMMENT ON COLUMN userinfo.member_name is 'member_name';
COMMENT ON COLUMN userinfo.member_email is 'member_email';
COMMENT ON COLUMN userinfo.member_address is 'member_address';
COMMENT ON COLUMN userinfo.member_birth is 'member_birth';
COMMENT ON COLUMN userinfo.member_number is 'member_number';
COMMENT ON COLUMN userinfo.member_regdate is 'member_regdate';
COMMENT ON COLUMN userinfo.member_point is 'member_point';


/**********************************/
/* Table Name: cart */
/**********************************/
CREATE TABLE cart(
		cart_name                     		VARCHAR2(50)		 NULL ,
		cart_qty                      		NUMBER(10)		 DEFAULT 0		 NULL ,
		cart_price                    		NUMBER(10)		 NULL ,
		cart_TotPrice                 		NUMBER(10)		 NULL ,
		memberId                      		VARCHAR2(10)		 NULL ,
		p_no                          		NUMBER(10)		 NULL 
);

COMMENT ON TABLE cart is 'cart';
COMMENT ON COLUMN cart.cart_name is 'cart_name';
COMMENT ON COLUMN cart.cart_qty is 'cart_qty';
COMMENT ON COLUMN cart.cart_price is 'cart_price';
COMMENT ON COLUMN cart.cart_TotPrice is 'cart_TotPrice';
COMMENT ON COLUMN cart.memberId is 'memberId';
COMMENT ON COLUMN cart.p_no is 'p_no';


/**********************************/
/* Table Name: orders */
/**********************************/
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

COMMENT ON TABLE orders is 'orders';
COMMENT ON COLUMN orders.o_no is 'o_no';
COMMENT ON COLUMN orders.o_date is 'o_date';
COMMENT ON COLUMN orders.o_price is 'o_price';
COMMENT ON COLUMN orders.o_TotPrice is 'o_TotPrice';
COMMENT ON COLUMN orders.o_name is 'o_name';
COMMENT ON COLUMN orders.memberId is 'memberId';


/**********************************/
/* Table Name: order_item */
/**********************************/
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

COMMENT ON TABLE order_item is 'order_item';
COMMENT ON COLUMN order_item.oi_no is 'oi_no';
COMMENT ON COLUMN order_item.oi_qty is 'oi_qty';
COMMENT ON COLUMN order_item.o_no is 'o_no';
COMMENT ON COLUMN order_item.p_no is 'p_no';



ALTER TABLE product ADD CONSTRAINT IDX_product_PK PRIMARY KEY (p_no);

ALTER TABLE userinfo ADD CONSTRAINT IDX_userinfo_PK PRIMARY KEY (member_Id);

ALTER TABLE cart ADD CONSTRAINT IDX_cart_PK PRIMARY KEY (cart_name, memberId);
ALTER TABLE cart ADD CONSTRAINT IDX_cart_FK1 FOREIGN KEY (p_no) REFERENCES product (p_no);

ALTER TABLE orders ADD CONSTRAINT IDX_orders_PK PRIMARY KEY (o_no, memberId);

ALTER TABLE order_item ADD CONSTRAINT IDX_order_item_PK PRIMARY KEY (oi_no);
ALTER TABLE order_item ADD CONSTRAINT IDX_order_item_FK1 FOREIGN KEY (p_no) REFERENCES product (p_no);

