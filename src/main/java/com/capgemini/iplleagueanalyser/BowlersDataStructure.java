package com.capgemini.iplleagueanalyser;

import com.opencsv.bean.CsvBindByName;

public class BowlersDataStructure {

	@CsvBindByName(column = "PLAYER", required = true)
	private String playerName;

	@CsvBindByName(column = "Mat", required = true)
	private int numOfMatchesPlayed;

	@CsvBindByName(column = "Inns", required = true)
	private int numOfInningsPlayed;

	@CsvBindByName(column = "Ov", required = true)
	private double numOfOvers;

	@CsvBindByName(column = "Runs", required = true)
	private int runsGiven;

	@CsvBindByName(column = "Wkts", required = true)
	private int wicketsTaken;

	@CsvBindByName(column = "BBI", required = true)
	private int bbi;

	@CsvBindByName(column = "Avg", required = true)
	private double average;

	@CsvBindByName(column = "Econ", required = true)
	private double economyRate;

	@CsvBindByName(column = "SR", required = true)
	private double strikeRate;

	@CsvBindByName(column = "4w", required = true)
	private int numOf4WicketHauls;

	@CsvBindByName(column = "5w", required = true)
	private int numOf5WicketHauls;

	public String getPlayerName() {
		return playerName;
	}

	public int getWicketsTaken() {
		return wicketsTaken;
	}

	public double getAverage() {
		return average;
	}

	public double getEconomyRate() {
		return economyRate;
	}

	public double getStrikeRate() {
		return strikeRate;
	}

	public int getNumOf4WicketHauls() {
		return numOf4WicketHauls;
	}

	public int getNumOf5WicketHauls() {
		return numOf5WicketHauls;
	}
}


