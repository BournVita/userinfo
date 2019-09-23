package webapp.model;

import com.google.gson.annotations.SerializedName;

public class UserSummary {
	@SerializedName("usr_id")

	private String usr_id;
	@SerializedName("usr_comment")

	private String usr_comment;
	/*
	 * private Date create_date; private Date update_date;
	 */
	@SerializedName("create_date")

	private String create_date;
	@SerializedName("update_date")

	private String update_date;

	public String getUsr_comment() {
		return usr_comment;
	}

	public void setUsr_comment(String usr_comment) {
		this.usr_comment = usr_comment;
	}

	public String getUsr_id() {
		return usr_id;
	}

	public void setUsr_id(String usr_id) {
		this.usr_id = usr_id;
	}

	/*
	 * public Date getCreate_date() { return create_date; } public void
	 * setCreate_date(Date create_date) { this.create_date = create_date; } public
	 * Date getUpdate_date() { return update_date; } public void setUpdate_date(Date
	 * update_date) { this.update_date = update_date; }
	 */
	@Override
	public String toString() {

		return "Comment: " + getUsr_comment();
//		return "userdata [usr_comment=" + usr_comment + ", create_usr=" + create_usr + ", create_date=" + create_date
//				+ ", getUsr_comment()=" + getUsr_comment() + ", getCreate_usr()=" + getCreate_usr()
//				+ ", getCreate_date()=" + getCreate_date() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
//				+ ", toString()=" + super.toString() + "]";
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}
}
