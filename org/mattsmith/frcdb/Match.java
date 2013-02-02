package org.mattsmith.frcdb;

public class Match {
	private String Type_;
	private int Number_;
	private String Time_;
	private int RedScore_;
	private int BlueScore_;
	private int[] RedAlliance_;
	private int[] BlueAlliance_;
	private String Winner_;
	
	public String getType() {
		return Type_;
	}
	public int getNumber() {
		return Number_;
	}
	public String getTime() {
		return Time_;
	}
	public int getRedScore() {
		return RedScore_;
	}
	public int getBlueScore() {
		return BlueScore_;
	}
	public int[] getRedAlliance() {
		return RedAlliance_;
	}
	public int[] getBlueAlliance() {
		return BlueAlliance_;
	}
	public String getWinner() {
		return Winner_;
	}
	
	public void setNumber(int number_) {
		Number_ = number_;
	}
	public void setTime(String time_) {
		Time_ = time_;
	}
	public void setRedScore(int redScore_) {
		RedScore_ = redScore_;
	}
	public void setBlueScore(int blueScore_) {
		BlueScore_ = blueScore_;
	}
	public void setRedAlliance(int[] redAlliance_) {
		RedAlliance_ = redAlliance_;
	}
	public void setBlueAlliance(int[] blueAlliance_) {
		BlueAlliance_ = blueAlliance_;
	}
	public void setType(String type_) {
		Type_ = type_;
	}
	public void setWinner(String winner_) {
		Winner_ = winner_;
	}
}
