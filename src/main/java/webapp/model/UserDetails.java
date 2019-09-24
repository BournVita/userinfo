package webapp.model;

import com.google.gson.annotations.SerializedName;

public class UserDetails {
	@SerializedName("usr_id")

	private String usr_id;
	
	@SerializedName("usr_comment")

	private String usr_comment;
	
	@SerializedName("city_name")

	private String city_name;
	/*
	 * private Date create_date; private Date update_date;
	 */
	@SerializedName("city_alti")

	private String city_alti;
	@SerializedName("city_longi")
	private String city_longi;
	@SerializedName("city_temp")
	private String city_temp;
	@SerializedName("create_date")

	private String create_date;
	@SerializedName("update_date")

	private String update_date;
	@Override
	public String toString() {

		return "usrid: " + getUsr_id()+
				", toString()=" + super.toString() + "]";
	}

	public String getUsr_id() {
		return usr_id;
	}

	public void setUsr_id(String usr_id) {
		this.usr_id = usr_id;
	}
	public String getUsr_comment() {
		return usr_comment;
	}
	
	public void setUsr_comment(String usr_comment) {
		this.usr_comment = usr_comment;
	}
	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String getCity_alti() {
		return city_alti;
	}

	public void setCity_alti(String city_alti) {
		this.city_alti = city_alti;
	}

	public String getCity_longi() {
		return city_longi;
	}

	public void setCity_longi(String city_longi) {
		this.city_longi = city_longi;
	}

	public String getCity_temp() {
		return city_temp;
	}

	public void setCity_temp(String city_temp) {
		this.city_temp = city_temp;
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
