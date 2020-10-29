package com.capgemini.iplleagueanalyser;

import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.capgemini.opencsvbuilder.CustomCSVBuilderException;
import com.capgemini.opencsvbuilder.ExceptionType;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.MappingStrategy;

public class IPLAnalyserTest {

	IPLAnalyser iplAnalyser;

	@Before
	public void initialiseIPLAnalyserObject() {
		iplAnalyser = new IPLAnalyser();
	}
	
	@Test
	public void givenIPLBatsmenData_ShouldReturnNoOfBatsmen() throws CustomFileIOException, CustomCSVBuilderException {
		MappingStrategy<BatsmenDataStructure> mappingStrategy = new HeaderColumnNameMappingStrategy<BatsmenDataStructure>();
		mappingStrategy.setType(BatsmenDataStructure.class);
		int numOfBatsmen = iplAnalyser.loadIPLBatsmenData(Constants.BATSMEN_CSV_FILE_PATH, mappingStrategy, BatsmenDataStructure.class, ',');
		Assert.assertEquals(100, numOfBatsmen);
	}
	
	@Test
	public void givenIPLBatsmenData_ShouldReturnSortedDescendingOnBattingAverage() throws CustomFileIOException, CustomCSVBuilderException {
		MappingStrategy<BatsmenDataStructure> mappingStrategy = new HeaderColumnNameMappingStrategy<BatsmenDataStructure>();
		mappingStrategy.setType(BatsmenDataStructure.class);
		iplAnalyser.loadIPLBatsmenData(Constants.BATSMEN_CSV_FILE_PATH, mappingStrategy, BatsmenDataStructure.class, ',');
		List<BatsmenDataStructure> sortedBatsmenListOnAverageDescending = iplAnalyser.getBatsmenListSortedOnAverageDescending();
		Assert.assertEquals("David Warner", sortedBatsmenListOnAverageDescending.get(0).getPlayerName());
		Assert.assertEquals("MS Dhoni", sortedBatsmenListOnAverageDescending.get(1).getPlayerName());
	}
	
	@Test
	public void givenIncorrectCSVFile_ShouldReturnCustomException() throws CustomCSVBuilderException {
		String exceptionMessage = null;
		try {
			MappingStrategy<BatsmenDataStructure> mappingStrategy = new HeaderColumnNameMappingStrategy<BatsmenDataStructure>();
			mappingStrategy.setType(BatsmenDataStructure.class);
			iplAnalyser.loadIPLBatsmenData(Constants.INCORRECT_BATSMAN_CSV_FILE_PATH, mappingStrategy, BatsmenDataStructure.class, ',');
		} catch(CustomFileIOException e) {
			exceptionMessage = e.getMessage();
		}
		Assert.assertEquals(ExceptionTypeIO.FILE_PROBLEM.toString(), exceptionMessage);
	}

	@Test
	public void givenIncorrectCSVType_ShouldReturnCustomException() throws CustomFileIOException {
		String exceptionMessage = null;
		try {
			iplAnalyser.loadIPLBatsmenData(Constants.BATSMEN_CSV_FILE_PATH, null, null, ',');
		} catch(CustomCSVBuilderException e) {
			exceptionMessage = e.getMessage();
		}
		Assert.assertEquals(ExceptionType.PARSE_PROBLEM.toString(), exceptionMessage);
	}

	@Test
	public void givenCorrectCSVFileIncorrectDelimiter_ShouldReturnCustomException() throws CustomFileIOException {
		String exceptionMessage = null;
		try {
			MappingStrategy<BatsmenDataStructure> mappingStrategy = new HeaderColumnNameMappingStrategy<BatsmenDataStructure>();
			mappingStrategy.setType(BatsmenDataStructure.class);
			iplAnalyser.loadIPLBatsmenData(Constants.BATSMEN_CSV_FILE_PATH, mappingStrategy, BatsmenDataStructure.class, '|');
		} catch(CustomCSVBuilderException e) {
			exceptionMessage = e.getMessage();
		}
		Assert.assertEquals(ExceptionType.HEADER_OR_DELIMITER_PROBLEM.toString(), exceptionMessage);
	}

	@Test
	public void givenCorrectCSVFileIncorrectHeader_ShouldReturnCustomException() throws CustomFileIOException {
		String exceptionMessage = null;
		try {
			MappingStrategy<BatsmenDataStructure> mappingStrategy = new HeaderColumnNameMappingStrategy<BatsmenDataStructure>();
			mappingStrategy.setType(BatsmenDataStructure.class);
			iplAnalyser.loadIPLBatsmenData(Constants.INCORRECT_HEADER_BATSMAN_CSV_FILE_PATH, mappingStrategy, BatsmenDataStructure.class, ',');
		} catch(CustomCSVBuilderException e) {
			exceptionMessage = e.getMessage();
		}
		Assert.assertEquals(ExceptionType.HEADER_OR_DELIMITER_PROBLEM.toString(), exceptionMessage);
	}
	
	@Test
	public void givenIPLBatsmenData_ShouldReturnSortedDescendingOnStrikeRate() throws CustomFileIOException, CustomCSVBuilderException {
		MappingStrategy<BatsmenDataStructure> mappingStrategy = new HeaderColumnNameMappingStrategy<BatsmenDataStructure>();
		mappingStrategy.setType(BatsmenDataStructure.class);
		iplAnalyser.loadIPLBatsmenData(Constants.BATSMEN_CSV_FILE_PATH, mappingStrategy, BatsmenDataStructure.class, ',');
		List<BatsmenDataStructure> sortedBatsmenListOnStrikeRateDescending = iplAnalyser.getBatsmenListSortedOnStrikeRateDescending();
		Assert.assertEquals("Andre Russell", sortedBatsmenListOnStrikeRateDescending.get(0).getPlayerName());
		Assert.assertEquals("Ishant Sharma", sortedBatsmenListOnStrikeRateDescending.get(1).getPlayerName());
	}
	
	@Test
	public void givenIPLBatsmenData_ShouldReturnSortedDescendingOnFoursAndSixes() throws CustomFileIOException, CustomCSVBuilderException {
		MappingStrategy<BatsmenDataStructure> mappingStrategy = new HeaderColumnNameMappingStrategy<BatsmenDataStructure>();
		mappingStrategy.setType(BatsmenDataStructure.class);
		iplAnalyser.loadIPLBatsmenData(Constants.BATSMEN_CSV_FILE_PATH, mappingStrategy, BatsmenDataStructure.class, ',');
		List<BatsmenDataStructure> sortedBatsmenListOnFoursDescending = iplAnalyser.getBatsmenListSortedOnFoursDescending();
		Assert.assertEquals("Shikhar Dhawan", sortedBatsmenListOnFoursDescending.get(0).getPlayerName());
		List<BatsmenDataStructure> sortedBatsmenListOnSixesDescending = iplAnalyser.getBatsmenListSortedOnSixesDescending();
		Assert.assertEquals("Ishant Sharma", sortedBatsmenListOnSixesDescending.get(0).getPlayerName());
	}
	
	@Test
	public void givenIPLBatsmenData_ShouldReturnHighestStrikeWithMaxFours() throws CustomFileIOException, CustomCSVBuilderException {
		MappingStrategy<BatsmenDataStructure> mappingStrategy = new HeaderColumnNameMappingStrategy<BatsmenDataStructure>();
		mappingStrategy.setType(BatsmenDataStructure.class);
		iplAnalyser.loadIPLBatsmenData(Constants.BATSMEN_CSV_FILE_PATH, mappingStrategy, BatsmenDataStructure.class, ',');
		List<BatsmenDataStructure> sortedBatsmenListStrikeRateWithMaxFours = iplAnalyser.getBatsmenListSortedOnStrikeRateWithMaxFours();
		Assert.assertEquals("Andre Russell", sortedBatsmenListStrikeRateWithMaxFours.get(0).getPlayerName());
	}
	
	@Test
	public void givenIPLBatsmenData_ShouldReturnHighestStrikeWithMaxSixes() throws CustomFileIOException, CustomCSVBuilderException {
		MappingStrategy<BatsmenDataStructure> mappingStrategy = new HeaderColumnNameMappingStrategy<BatsmenDataStructure>();
		mappingStrategy.setType(BatsmenDataStructure.class);
		iplAnalyser.loadIPLBatsmenData(Constants.BATSMEN_CSV_FILE_PATH, mappingStrategy, BatsmenDataStructure.class, ',');
		List<BatsmenDataStructure> sortedBatsmenListStrikeRateWithMaxSixes = iplAnalyser.getBatsmenListSortedOnStrikeRateWithMaxSixes();
		Assert.assertEquals("Ishant Sharma", sortedBatsmenListStrikeRateWithMaxSixes.get(0).getPlayerName());
	}
	
	@Test
	public void givenIPLBatsmenData_ShouldReturnHighestAverageWithMaxStrikeRate() throws CustomFileIOException, CustomCSVBuilderException {
		MappingStrategy<BatsmenDataStructure> mappingStrategy = new HeaderColumnNameMappingStrategy<BatsmenDataStructure>();
		mappingStrategy.setType(BatsmenDataStructure.class);
		iplAnalyser.loadIPLBatsmenData(Constants.BATSMEN_CSV_FILE_PATH, mappingStrategy, BatsmenDataStructure.class, ',');
		List<BatsmenDataStructure> sortedBatsmenListAverageWithMaxStrikeRate = iplAnalyser.getBatsmenListSortedOnAverageWithMaxStrikeRate();
		Assert.assertEquals("David Warner", sortedBatsmenListAverageWithMaxStrikeRate.get(0).getPlayerName());
	}
	
	@Test
	public void givenIPLBatsmenData_ShouldReturnHighestRunsWithMaxAverage() throws CustomFileIOException, CustomCSVBuilderException {
		MappingStrategy<BatsmenDataStructure> mappingStrategy = new HeaderColumnNameMappingStrategy<BatsmenDataStructure>();
		mappingStrategy.setType(BatsmenDataStructure.class);
		iplAnalyser.loadIPLBatsmenData(Constants.BATSMEN_CSV_FILE_PATH, mappingStrategy, BatsmenDataStructure.class, ',');
		List<BatsmenDataStructure> sortedBatsmenListHighestRunsWithMaxAverage = iplAnalyser.getBatsmenListSortedOnRunsWithMaxAverage();
		Assert.assertEquals("David Warner", sortedBatsmenListHighestRunsWithMaxAverage.get(0).getPlayerName());
	}
	
	@Test
	public void givenIPLBowlerData_ShouldReturnSortedBestBowlingAverage() throws CustomFileIOException, CustomCSVBuilderException {
		MappingStrategy<BowlersDataStructure> mappingStrategy = new HeaderColumnNameMappingStrategy<BowlersDataStructure>();
		mappingStrategy.setType(BowlersDataStructure.class);
		iplAnalyser.loadIPLBowlersData(Constants.BOWLERS_CSV_FILE_PATH, mappingStrategy, BowlersDataStructure.class, ',');
		List<BowlersDataStructure> sortedBowlersListOnAverage = iplAnalyser.getBowlersListSortedOnAverage();
		Assert.assertEquals("Anukul Roy", sortedBowlersListOnAverage.get(0).getPlayerName());
	}
	
	@Test
	public void givenIPLBowlerData_ShouldReturnSortedBestStrikeRate() throws CustomFileIOException, CustomCSVBuilderException {
		MappingStrategy<BowlersDataStructure> mappingStrategy = new HeaderColumnNameMappingStrategy<BowlersDataStructure>();
		mappingStrategy.setType(BowlersDataStructure.class);
		iplAnalyser.loadIPLBowlersData(Constants.BOWLERS_CSV_FILE_PATH, mappingStrategy, BowlersDataStructure.class, ',');
		List<BowlersDataStructure> sortedBowlersListOnStrikeRate = iplAnalyser.getBowlersListSortedOnStrikeRate();
		Assert.assertEquals("Alzarri Joseph", sortedBowlersListOnStrikeRate.get(0).getPlayerName());
	}
	
	@Test
	public void givenIPLBowlerData_ShouldReturnSortedBestEconomyRate() throws CustomFileIOException, CustomCSVBuilderException {
		MappingStrategy<BowlersDataStructure> mappingStrategy = new HeaderColumnNameMappingStrategy<BowlersDataStructure>();
		mappingStrategy.setType(BowlersDataStructure.class);
		iplAnalyser.loadIPLBowlersData(Constants.BOWLERS_CSV_FILE_PATH, mappingStrategy, BowlersDataStructure.class, ',');
		List<BowlersDataStructure> sortedBowlersListOnEconomyRate = iplAnalyser.getBowlersListSortedOnEconomyRate();
		Assert.assertEquals("Shivam Dube", sortedBowlersListOnEconomyRate.get(0).getPlayerName());
	}
	
	@Test
	public void givenIPLBowlerData_ShouldReturnBestStrikeRateWithFourAndFiveWickets() throws CustomFileIOException, CustomCSVBuilderException {
		MappingStrategy<BowlersDataStructure> mappingStrategy = new HeaderColumnNameMappingStrategy<BowlersDataStructure>();
		mappingStrategy.setType(BowlersDataStructure.class);
		iplAnalyser.loadIPLBowlersData(Constants.BOWLERS_CSV_FILE_PATH, mappingStrategy, BowlersDataStructure.class, ',');
		List<BowlersDataStructure> sortedBowlersListOnStrikeRateWithFourAndFiveWickets = iplAnalyser.getBowlersListSortedOnStrikeRateWithFourAndFiveWickets();
		Assert.assertEquals("Alzarri Joseph", sortedBowlersListOnStrikeRateWithFourAndFiveWickets.get(0).getPlayerName());
	}
	
	@Test
	public void givenIPLBowlerData_ShouldReturnBestBowlingAveragesWithHighestStrikeRates() throws CustomFileIOException, CustomCSVBuilderException {
		MappingStrategy<BowlersDataStructure> mappingStrategy = new HeaderColumnNameMappingStrategy<BowlersDataStructure>();
		mappingStrategy.setType(BowlersDataStructure.class);
		iplAnalyser.loadIPLBowlersData(Constants.BOWLERS_CSV_FILE_PATH, mappingStrategy, BowlersDataStructure.class, ',');
		List<BowlersDataStructure> sortedBowlersListOnBowlingAverageWithHighestStrikeRates = iplAnalyser.getBowlersListSortedOnBowlingAverageWithHighestStrikeRates();
		Assert.assertEquals("Sherfane Rutherford", sortedBowlersListOnBowlingAverageWithHighestStrikeRates.get(0).getPlayerName());
	}
	
	@Test
	public void givenIPLBowlerData_ShouldReturnBowlersWithMaxWicketsWithBestAverages() throws CustomFileIOException, CustomCSVBuilderException {
		MappingStrategy<BowlersDataStructure> mappingStrategy = new HeaderColumnNameMappingStrategy<BowlersDataStructure>();
		mappingStrategy.setType(BowlersDataStructure.class);
		iplAnalyser.loadIPLBowlersData(Constants.BOWLERS_CSV_FILE_PATH, mappingStrategy, BowlersDataStructure.class, ',');
		List<BowlersDataStructure> sortedBowlersListOnWicketsWithBestAverages = iplAnalyser.getBowlersListSortedOnWicketsWithBestAverages();
		Assert.assertEquals("Kagiso Rabada", sortedBowlersListOnWicketsWithBestAverages.get(0).getPlayerName());
	}
	
	@Test
	public void givenIPLBatsmenAndBowlerData_ShouldReturnBestBattingAndBowlingAverages() throws CustomFileIOException, CustomCSVBuilderException {
		MappingStrategy<BowlersDataStructure> mappingStrategyBowler = new HeaderColumnNameMappingStrategy<BowlersDataStructure>();
		mappingStrategyBowler.setType(BowlersDataStructure.class);
		MappingStrategy<BatsmenDataStructure> mappingStrategyBatsmen = new HeaderColumnNameMappingStrategy<BatsmenDataStructure>();
		mappingStrategyBatsmen.setType(BatsmenDataStructure.class);
		iplAnalyser.loadIPLBowlersData(Constants.BOWLERS_CSV_FILE_PATH, mappingStrategyBowler, BowlersDataStructure.class, ',');
		iplAnalyser.loadIPLBatsmenData(Constants.BATSMEN_CSV_FILE_PATH, mappingStrategyBatsmen, BatsmenDataStructure.class, ',');
		List<BatsmenDataStructure> sortedBatsmenListOnAverageDescending = iplAnalyser.getBatsmenListSortedOnAverageDescending();
		Assert.assertEquals("David Warner", sortedBatsmenListOnAverageDescending.get(0).getPlayerName());
		List<BowlersDataStructure> sortedBowlersListOnAverage = iplAnalyser.getBowlersListSortedOnAverage();
		Assert.assertEquals("Anukul Roy", sortedBowlersListOnAverage.get(0).getPlayerName());
	}
	
	@Test
	public void givenIPLBatsmenAndBowlerData_ShouldReturnBestAllRounders() throws CustomFileIOException, CustomCSVBuilderException {
		MappingStrategy<BowlersDataStructure> mappingStrategyBowler = new HeaderColumnNameMappingStrategy<BowlersDataStructure>();
		mappingStrategyBowler.setType(BowlersDataStructure.class);
		MappingStrategy<BatsmenDataStructure> mappingStrategyBatsmen = new HeaderColumnNameMappingStrategy<BatsmenDataStructure>();
		mappingStrategyBatsmen.setType(BatsmenDataStructure.class);
		iplAnalyser.loadIPLBowlersData(Constants.BOWLERS_CSV_FILE_PATH, mappingStrategyBowler, BowlersDataStructure.class, ',');
		iplAnalyser.loadIPLBatsmenData(Constants.BATSMEN_CSV_FILE_PATH, mappingStrategyBatsmen, BatsmenDataStructure.class, ',');
		List<AllRounderDataStructure> allRounderList = iplAnalyser.getBestAllRounderList(); 
		Assert.assertEquals("", allRounderList.get(0).getPlayerName());
	}
}
