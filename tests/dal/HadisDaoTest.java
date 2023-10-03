package dal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bll.HadisBLL;
import to.HadisTO;

class HadisDaoTest {

	HadisDaoStub stub;
	HadisBLL bll;
	
	@BeforeEach
	void init() {
		stub = new HadisDaoStub();
		bll = new HadisBLL(stub);
	}
	
	
										// INSERT FUNCTION TEST CASES
	
	/**
	 * 
	 * @author Bilal
	 *
	 */
	@Test
	@DisplayName("Test case 11: Input dataTo is NULL")
	void isInsert()
	{
		HadisTO insertObj = new HadisTO();
		insertObj=null;
		Assertions.assertFalse(bll.insertData(insertObj));
	}
	
	
	/**
	 * 
	 * @author Bilal
	 *
	 */
	@Test
	@DisplayName("Test case 12: Input dataTo is Valid")
	void isInsert_2()
	{
		HadisTO insertObj = new HadisTO();
		insertObj.setHadith("وحدثنا");
		insertObj.setBook("صحيح");
		insertObj.setNumHadith(20);
		insertObj.setMatn("وحدثنا");
		insertObj.setSanad("وحدثنا");
		insertObj.setSanadLength(20);
		Assertions.assertTrue(bll.insertData(insertObj));
	}
	
	
	
	/**
	 * 
	 * @author Ahad
	 *
	 */
	@Test
	@DisplayName("Test case 13: SQL Exception")
	void isInsert_3()
	{
		HadisTO insertObj = new HadisTO();
		insertObj.setHadith("وحدثنا");
		insertObj.setBook("صحيح");
		insertObj.setNumHadith(20);
		insertObj.setMatn("وحدثنا");
		insertObj.setSanad("وحدثنا");
		insertObj.setSanadLength(20);
		Assertions.assertFalse(bll.insertData(insertObj));
	}
	
	
	
	
											// UPDATE FUNCTION TEST CASES
	
	
	/**
	 * 
	 * @author Ahad
	 *
	 */
	@Test
	@DisplayName("Test case 21: Input Data HadisTO is NULL")
	void isUpdate()
	{
		HadisTO updateObj = new HadisTO();
		updateObj=null;
		Assertions.assertFalse(bll.updateData(updateObj));
		
	}
	
	
	/**
	 * 
	 * @author Bilal
	 *
	 */
	@Test
	@DisplayName("Test case 22: Input Data UID is Valid")
	void isUpdate_1()
	{
		HadisTO updateObj = new HadisTO();
		updateObj.setUid(46);
		updateObj.setHadith("وحدثنا");
		updateObj.setBook("صحيح");
		updateObj.setNumHadith(10);
		updateObj.setMatn("وحدثنا");
		updateObj.setSanad("وحدثنا");
		updateObj.setSanadLength(0);
		Assertions.assertTrue(bll.updateData(updateObj));
		
	}

	
	
	/**
	 * 
	 * @author Ahad
	 *
	 */
//	@Test
	@DisplayName("Test case 23: Input Data UID is Not Valid")
	void isUpdate_2()
	{
		HadisTO updateObj = new HadisTO();
		updateObj.setUid(50);
		updateObj.setHadith("وحدثنا");
		updateObj.setBook("صحيح");
		updateObj.setNumHadith(10);
		updateObj.setMatn("وحدثنا");
		updateObj.setSanad("وحدثنا");
		updateObj.setSanadLength(0);
		Assertions.assertFalse(bll.updateData(updateObj));
		
	}
	
	
	
	/**
	 * 
	 * @author Bilal
	 *
	 */
	@Test
	@DisplayName("Test case 24: SQL Exception")
	void isUpdate_3()
	{
		HadisTO updateObj = new HadisTO();
		updateObj.setUid(50);
		updateObj.setHadith("وحدثنا");
		updateObj.setBook("صحيح");
		updateObj.setNumHadith(10);
		updateObj.setMatn("وحدثنا");
		updateObj.setSanad("وحدثنا");
		updateObj.setSanadLength(0);
		Assertions.assertFalse(bll.updateData(updateObj));
		
	}
	
	
	
	
										// DELETE FUNCTION TEST CASES
	
	
	/**
	 * 
	 * @author Hasaan
	 *
	 */
	@Test
	@DisplayName("Test case 31: Input Data HadisTO is NULL")
	void isDelete()
	{
		HadisTO deleteObj = new HadisTO();
		deleteObj=null;

		Assertions.assertFalse(bll.deleteBAL(deleteObj));
		
	}
	
	/**
	 * 
	 * @author Hasaan
	 *
	 */
	@Test
	@DisplayName("Test case 32: Input Data UID is Valid")
	void isDelete_1()
	{
		HadisTO deleteObj = new HadisTO();
		deleteObj.setUid(46);

		Assertions.assertTrue(bll.deleteBAL(deleteObj));
		
	}
	
	/**
	 * 
	 * @author Hasaan
	 *
	 */
	@Test
	@DisplayName("Test case 33: Input Data UID is Not Valid")
	void isDelete_2()
	{
		HadisTO deleteObj = new HadisTO();
		deleteObj.setUid(48);

		Assertions.assertFalse(bll.deleteBAL(deleteObj));
		
	}
	
	/**
	 * 
	 * @author Hasaan
	 *
	 */
	@Test
	@DisplayName("Test case 34: SQL Exception")
	void isDelete_3()
	{
		HadisTO deleteObj = new HadisTO();
		deleteObj.setUid(48);

		Assertions.assertFalse(bll.deleteBAL(deleteObj));
		
	}
	
	

}
