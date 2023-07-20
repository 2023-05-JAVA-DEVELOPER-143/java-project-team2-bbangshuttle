package bbangshuttle.member;

public class MemberSQL {
	public static final String  MEMBER_INSERT=
			"insert into userinfo(member_id,member_password,member_name,member_email,member_address,member_birth,member_number,member_regdate,member_point) values(?,?,?,?,?,?,?,sysdate,0)";
	public static final String MEMBER_DELETE=
			"delete from userinfo where member_id=?";
	public static final String MEMBER_SELECT_BY_ID=
			"select * from userinfo where member_id=?";
	public static final String MEMBER_UPDATE=
			"update userinfo set member_password=?,member_name=?,member_email=?,member_address=?,member_birth=?,member_number=?,member_regdate=sysdate,member_point=0 where member_id=?";
	public static final String MEMBER_SELECT_BY_ALL=
			"select * from userinfo";
	public static final String MEMBER_FIND_ID=
			"select member_id from userinfo where member_name=?";
	public static final String MEMBER_FIND_PW=
			"select member_password from userinfo where member_id=?";
}
