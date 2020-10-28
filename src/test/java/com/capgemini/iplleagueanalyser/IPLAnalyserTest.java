package com.capgemini.iplleagueanalyser;

import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IPLAnalyserTest {

	IPLAnalyser iplAnalyser;

	@Before
	public void initialiseIPLAnalyserObject() {
		iplAnalyser = new IPLAnalyser();
	}

	@Test
	public void givenIPLBatsmenData_ShouldReturnSortedDescendingOnBattingAverage() {
		iplAnalyser.loadIPLBatsmenData(Constants.BATSMEN_CSV_FILE_PATH);
		List<BatsmenDataStructure> sortedBatsmenListOnAverageDescending = iplAnalyser.getBatsmenListSortedOnAverageDescending();
		Assert.assertEquals("David Warner", sortedBatsmenListOnAverageDescending.get(0).getPlayerName());
		Assert.assertEquals("Andre Russell", sortedBatsmenListOnAverageDescending.get(1).getPlayerName());
	}
}
