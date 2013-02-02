package org.mattsmith.frcdb;

public class TeamStanding {
	private int Number_;
	private int Rank_;
	private int MatchesPlayed_;
	private int GameYear_;
	private int Wins_;
	private int Losses_;
	private int Ties_;
	private String FinalMatchLevel_;
	private double OPR_;
	private double DPR_;
	
	public int getNumber() {
		return Number_;
	}
	public int getRank() {
		return Rank_;
	}
	public int getMatchesPlayed() {
		return MatchesPlayed_;
	}
	public int getGameYear() {
		return GameYear_;
	}
	public int getWins() {
		return Wins_;
	}
	public int getLosses() {
		return Losses_;
	}
	public int getTies() {
		return Ties_;
	}
	public String getFinalMatchLevel() {
		return FinalMatchLevel_;
	}
	public double getOPR() {
		return OPR_;
	}
	public double getDPR() {
		return DPR_;
	}
	
	public void setDPR(double dPR_) {
		DPR_ = dPR_;
	}
	public void setOPR(double oPR_) {
		OPR_ = oPR_;
	}
	public void setFinalMatchLevel(String finalMatchLevel_) {
		FinalMatchLevel_ = finalMatchLevel_;
	}
	public void setTies(int ties_) {
		Ties_ = ties_;
	}
	public void setLosses(int losses_) {
		Losses_ = losses_;
	}
	public void setWins(int wins_) {
		Wins_ = wins_;
	}
	public void setGameYear(int gameYear_) {
		GameYear_ = gameYear_;
	}
	public void setMatchesPlayed(int matchesPlayed_) {
		MatchesPlayed_ = matchesPlayed_;
	}
	public void setRank(int rank_) {
		Rank_ = rank_;
	}
	public void setNumber(int number_) {
		Number_ = number_;
	}
}
