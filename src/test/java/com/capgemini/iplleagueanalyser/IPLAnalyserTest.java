package com.capgemini.iplleagueanalyser;

import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.capgemini.opencsvbuilder.CustomCSVBuilderException;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.MappingStrategy;

public class IPLAnalyserTest {

	IPLAnalyser iplAnalyser;

	@Before
	public void initialiseIPLAnalyserObject() {
		iplAnalyser = new IPLAnalyser();
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
}
