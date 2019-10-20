package com.aseassignments.isbntools;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.aseassignment.isbntools.Book;
import com.aseassignment.isbntools.ExternalISBNDataService;
import com.aseassignment.isbntools.StockManager;

public class StockManagementTests {
	
	
	ExternalISBNDataService testWebService;
	StockManager stockManager;
	ExternalISBNDataService testDatabaseService;
	
	
	@Before
	public void setUp()
	{
		testWebService = Mockito.mock(ExternalISBNDataService.class);
		stockManager = new StockManager();
		stockManager.setWebService(testWebService);
		testDatabaseService = Mockito.mock(ExternalISBNDataService.class);
		stockManager.setDatabaseService(testDatabaseService);
	}

	@Test
	public void testCanGetACorrectLocatorCode() {
		
		Mockito.when(testWebService.lookup(Mockito.anyString())).thenReturn(new Book("0104177396", "Of Mice and Men", "J. Steinbeck"));
		
		Mockito.when(testDatabaseService.lookup(Mockito.anyString())).thenReturn(null);
		
		String isbn = "0104177396";
		String locatorCode = stockManager.getLocatorCode(isbn);
		assertEquals("7396J4", locatorCode);
	}
	
	@Test	
	public void databaseIsUsedIfDataIsPresent()
	{
		
		Mockito.when(testDatabaseService.lookup("0104177396")).thenReturn(new Book("0104177396", "abc", "abc"));
	
		String isbn = "0104177396";
		
		String locatorCode = stockManager.getLocatorCode(isbn);
		Mockito.verify(testDatabaseService).lookup("0104177396");
		Mockito.verify(testWebService, Mockito.never()).lookup(Mockito.anyString());
	}
	
	@Test
	public void webServiceIsUsedIfDataIsNotPresentInDatabase()
	{
			
		Mockito.when(testDatabaseService.lookup("0104177396")).thenReturn(null);
		Mockito.when(testWebService.lookup("0104177396")).thenReturn(new Book("0104177396", "abc", "abc"));

		String isbn = "0104177396";
		
		String locatorCode = stockManager.getLocatorCode(isbn);
		
		Mockito.verify(testDatabaseService).lookup("0104177396");
		Mockito.verify(testWebService).lookup("0104177396");
	}

}
