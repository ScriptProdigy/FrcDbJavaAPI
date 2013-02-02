package org.mattsmith.frcdb;

public class Team {
	private String Name_;
	private int Number_;
	private String NickName_;
	private String City_;
	private String State_;
	private String Country_;
	private int RookieSeason_;
	private String WebSite_;
	private String Motto_;
	
	public Team () {}

	public String getName() {
		return Name_;
	}
	public int getNumber() {
		return Number_;
	}

	public String getCity() {
		return City_;
	}

	public String getNickName() {
		return NickName_;
	}
	public String getState() {
		return State_;
	}
	public String getCountry() {
		return Country_;
	}
	public int getRookieSeason() {
		return RookieSeason_;
	}
	public String getWebSite() {
		return WebSite_;
	}
	public String getMotto() {
		return Motto_;
	}

	public void setNickName(String nickName_) {
		NickName_ = nickName_;
	}
	public void setState(String state_) {
		State_ = state_;
	}
	public void setCountry(String country_) {
		Country_ = country_;
	}
	public void setRookieSeason(int rookieSeason_) {
		RookieSeason_ = rookieSeason_;
	}
	public void setWebSite(String webSite_) {
		WebSite_ = webSite_;
	}
	public void setMotto(String motto_) {
		Motto_ = motto_;
	}
	public void setNumber(int number_) {
		Number_ = number_;
	}
	public void setCity(String city_) {
		City_ = city_;
	}
	public void setName(String name_) {
		Name_ = name_;
	}

}
