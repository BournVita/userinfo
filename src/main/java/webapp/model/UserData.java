package webapp.model;

public class UserData {
	
	private String usr_comment;
	private String create_usr;
	private String create_date;
	
	public String getUsr_comment() {
		return usr_comment;
	}
	public void setUsr_comment(String usr_comment) {
		this.usr_comment = usr_comment;
	}
	public String getCreate_usr() {
		return create_usr;
	}
	public void setCreate_usr(String create_usr) {
		this.create_usr = create_usr;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	
	@Override
	public String toString() {
		
		return "Comment: " + getUsr_comment();
//		return "userdata [usr_comment=" + usr_comment + ", create_usr=" + create_usr + ", create_date=" + create_date
//				+ ", getUsr_comment()=" + getUsr_comment() + ", getCreate_usr()=" + getCreate_usr()
//				+ ", getCreate_date()=" + getCreate_date() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
//				+ ", toString()=" + super.toString() + "]";
	}

}
