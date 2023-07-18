package bbangshuttle.member;

public class MemberSQL {
	public static final String  MEMBER_INSERT="insert into userinfo(userid,password,name,email) values(?,?,?,?)";
	public static final String MEMBER_SELECT_BY_ID="select userid,password,name,email from userinfo where userid=?";
	public static final String MEMBER_REMOVE="delete from userinfo where userid=?";
	public static final String MEMBER_UPDATE="update userinfo set password=?,name=?,email=? where userid=?";
	public static final String MEMBER_SELECT_BY_ID_COUNT="select count(*) as cnt  from userinfo where userid=?";
}
