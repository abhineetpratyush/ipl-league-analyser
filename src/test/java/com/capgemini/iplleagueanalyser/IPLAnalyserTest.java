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
		Assert.assertEquals("MS Dhoni", sortedBatsmenListOnAverageDescending.get(0).getPlayerName());
		Assert.assertEquals("David Warner", sortedBatsmenListOnAverageDescending.get(1).getPlayerName());
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
		Assert.assertEquals("Ishant Sharma", sortedBatsmenListOnStrikeRateDescending.get(0).getPlayerName());
		Assert.assertEquals("Andre Russell", sortedBatsmenListOnStrikeRateDescending.get(1).getPlayerName());
	}
	
	@Test
	public void givenIPLBatsmenData_ShouldReturnSortedDescendingOnFoursAndSixes() throws CustomFileIOException, CustomCSVBuilderException {
		MappingStrategy<BatsmenDataStructure> mappingStrategy = new HeaderColumnNameMappingStrategy<BatsmenDataStructure>();
		mappingStrategy.setType(BatsmenDataStructure.class);
		iplAnalyser.loadIPLBatsmenData(Constants.BATSMEN_CSV_FILE_PATH, mappingStrategy, BatsmenDataStructure.class, ',');
		List<BatsmenDataStructure> sortedBatsmenListOnFoursDescending = iplAnalyser.getBatsmenListSortedOnFoursDescending();
		Assert.assertEquals("Shikhar Dhawan", sortedBatsmenListOnFoursDescending.get(0).getPlayerName());
		List<BatsmenDataStructure> sortedBatsmenListOnSixesDescending = iplAnalyser.getBatsmenListSortedOnSixesDescending();
		Assert.assertEquals("Andre Russell", sortedBatsmenListOnSixesDescending.get(0).getPlayerName());
	}
}
