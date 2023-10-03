package bll;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dal.HadisDaoStub;
import to.HadisTO;

public class HadisBLLTest {
	HadisDaoStub stub;
	HadisBLL bll;

	@BeforeEach
	void init() {
		stub = new HadisDaoStub();
		bll = new HadisBLL(stub);
	}



	/**
	 * @author Hasaan
	 */


	//FIRST POINT//
	//List the narrators that directly narrate hadiths to a given narrator A

	@Test
	@DisplayName ("Test case 11: givenNarrator is in narratorMap")
	void isGetDirectNarrators() {
		List<String> expected = Arrays.asList(
				"'عبيد الله بن معاذ العنبري'",
				"'محمد بن عبد الله بن نمير'",
				"'أبو الأحوص'",
				"'عبيد الله بن معاذ'",
				"'ابن أبي عمر'",
				"'محمد بن أبي بكر المقدمي'"
				);
		Map<String, List<String>> narratorMap;
		String givenNarrator;
		List<String> narratorsToX;

		givenNarrator = "أبو بكر بن أبي شيبة";
		narratorMap=bll.createNarratorMap();
		narratorsToX = bll.getDirectNarrators(givenNarrator);

		assertEquals(expected, narratorsToX);

	}

	@Test
	@DisplayName ("Test case 12: givenNarrator is not in narratorMap")
	void isGetDirectNarrators_2() {
		List<String> expected = new ArrayList<String>();
		Map<String, List<String>> narratorMap;
		String givenNarrator;
		List<String> narratorsToX;

		givenNarrator = "أبو بكر بن إسحاق";
		narratorMap=bll.createNarratorMap();
		narratorsToX = bll.getDirectNarrators(givenNarrator);
		assertEquals(expected, narratorsToX);
	}

	@Test
	@DisplayName("Test case 13: givenNarrator is NULL")
	void isGetDirectNarrators_3()
	{
		List<String> expected = new ArrayList<String>();
		Map<String, List<String>> narratorMap;
		String givenNarrator;
		List<String> narratorsToX;

		givenNarrator = null;
		narratorMap=bll.createNarratorMap();
		narratorsToX = bll.getDirectNarrators(givenNarrator);
		assertEquals(expected, narratorsToX);
	}

	@Test
	@DisplayName("Test case 14: givenNarrator is Empty String")
	void isGetDirectNarrators_4()
	{
		List<String> expected = new ArrayList<String>();
		Map<String, List<String>> narratorMap;
		String givenNarrator;
		List<String> narratorsToX;

		givenNarrator = null;
		narratorMap=bll.createNarratorMap();
		narratorsToX = bll.getDirectNarrators(givenNarrator);
		assertEquals(expected, narratorsToX);
	}

	//FIRST POINT END //




	/**
	 * @author Ahad
	 */
	//SECOND POINT START//
	//List the narrators that directly narrate hadiths from a given narrator A


	@Test
	@DisplayName("Test case 21: givenNarrator has Atleast one Narrators who have taken Hadis")
	void isGetFromNarrators()
	{
		List<String> expected = Arrays.asList(
				"'حجاج بن الشاعر'",
				"'وكيع'",
				"'أبو الأحوص'",
				"'غندر'",
				"'أبو الأحوص سلام بن سليم'",
				"'محمد بن بشر'",
				"'وزهير بن حرب'",
				"'سويد بن سعيد'",
				"'وأبو كريب'",
				"'أبي'"
				);
		Map<String, List<String>> narratorMap;
		String givenNarrator;
		List<String> narratorsToX;

		givenNarrator = "أبو بكر بن أبي شيبة";
		narratorMap=bll.createNarratorMap();
		narratorsToX = bll.getFromNarrators(givenNarrator);

		assertEquals(expected, narratorsToX);
	}

	@Test
	@DisplayName("Test case 22: givenNarrator has no Narrators who have taken Hadis")
	void isGetFromNarrators_2()
	{
		List<String> expected = new ArrayList<String>();
		Map<String, List<String>> narratorMap;
		String givenNarrator;
		List<String> narratorsToX;

		givenNarrator = "عتبان بن مالك";
		narratorMap=bll.createNarratorMap();
		narratorsToX = bll.getFromNarrators(givenNarrator);

		assertEquals(expected, narratorsToX);
	}

	@Test
	@DisplayName("Test case 23: givenNarrator is NON-EXSITENT")
	void isGetFromNarrators_3()
	{
		List<String> expected = new ArrayList<String>();
		Map<String, List<String>> narratorMap;
		String givenNarrator;
		List<String> narratorsToX;

		givenNarrator = "بن مالك";
		narratorMap=bll.createNarratorMap();
		narratorsToX = bll.getFromNarrators(givenNarrator);

		assertEquals(expected, narratorsToX);
	}

	@Test
	@DisplayName("Test case 24: givenNarrator is NULL")
	void isGetFromNarrators_4()
	{
		List<String> expected = new ArrayList<String>();
		Map<String, List<String>> narratorMap;
		String givenNarrator;
		List<String> narratorsToX;

		givenNarrator = null;
		narratorMap=bll.createNarratorMap();
		narratorsToX = bll.getFromNarrators(givenNarrator);

		assertEquals(expected, narratorsToX);
	}

	@Test
	@DisplayName("Test case 25: givenNarrator is Empty String")
	void isGetFromNarrators_5()
	{
		List<String> expected = new ArrayList<String>();
		Map<String, List<String>> narratorMap;
		String givenNarrator;
		List<String> narratorsToX;

		givenNarrator = "";
		narratorMap=bll.createNarratorMap();
		narratorsToX = bll.getFromNarrators(givenNarrator);

		assertEquals(expected, narratorsToX);
	}


	//SECOND POINT END//



	/**
	 * @author Bilal
	 */
	//THIRD POINT//
	//List the unique narrators at level-n in a given book [we usually have some sahabi (RA) at level-1

	@Test
	@DisplayName("Test case 31: Input Book = NULL and Level=ANY")
	void isGetUniqueN()
	{
		List<String> expected = new ArrayList<String>();
		String bookName = null;


		List<String> result = bll.getUniqueNarratorsAtLevelN(bookName, 1);

		assertEquals(expected, result);
	}


	@Test
	@DisplayName("Test case 32: Input Book = ANY and Level=0")
	void isGetUniqueN_1()
	{
		List<String> expected = new ArrayList<String>();
		String bookName = "صحيح مسلم";


		List<String> result = bll.getUniqueNarratorsAtLevelN(bookName, 0);

		assertEquals(expected, result);
	}


	@Test
	@DisplayName("Test case 33: Input Book = ANY and Level=-1")
	void isGetUniqueN_3()
	{
		List<String> expected = new ArrayList<String>();
		String bookName = "صحيح مسلم";


		List<String> result = bll.getUniqueNarratorsAtLevelN(bookName, -1);

		assertEquals(expected, result);
	}

	@Test
	@DisplayName("Test case 34: Input Book = ANY and Level=20(WHERE no DATA IS available")
	void isGetUniqueN_4()
	{
		List<String> expected = new ArrayList<String>();
		String bookName = "صحيح مسلم";

		List<String> result = bll.getUniqueNarratorsAtLevelN(bookName, 20);

		assertEquals(expected, result);
	}

	@Test
	@DisplayName("Test case 35: Input Book = ANY and Level=3 (VALID)")
	void isGetUniqueN_5()
	{
		List<String> expected = Arrays.asList("'أبي'");
		String bookName = "صحيح مسلم";


		List<String> result = bll.getUniqueNarratorsAtLevelN(bookName, 3);

		assertEquals(expected, result);
	}



	//THIRD POINT END//






	/**
	 * @author Hasaan
	 */
	//FOURTH POINT//
	//List the unique narrators in a given book


	@Test
	@DisplayName("Test case 41: Existing Book with atleast one Hadith and unique narrators")
	void isGetUniqueNarratorsForBook()
	{
		String bookName = "صحيح مسلم";
		List<String> expected = Arrays.asList("'حجاج بن الشاعر'", "'وكيع'", "'أبو الأحوص'", "'غندر'", "'أبو الأحوص سلام بن سليم'", "'محمد بن بشر'", "'وزهير بن حرب'", "'سويد بن سعيد'", "'وأبو كريب'", "'أبي'");
		Map<String, List<String>> narratorMap;
		String givenNarrator;
		List<String> narratorsToX;



		givenNarrator = "أبو بكر بن أبي شيبة";
		narratorMap=bll.createNarratorMap();
		narratorsToX = bll.getFromNarrators(givenNarrator);

		assertEquals(expected, narratorsToX);
	}

	@Test
	@DisplayName("Test case 42: Existing Book with No Hadiths")
	void isGetUniqueNarratorsForBook_2()
	{
		String bookName = "جامع الترمذي";
		List<String> expectedList = new ArrayList<>();
		List<String> narrators;
		narrators = bll.getUniqueNarratorsForBook(bookName);		

		assertEquals(expectedList, narrators);
	}

	@Test
	@DisplayName("Test case 43: Non-Existing Book")
	void isGetUniqueNarratorsForBook_3()
	{
		String bookName = "جامع التمذي";
		List<String> expectedList = new ArrayList<>();
		List<String> narrators;
		narrators = bll.getUniqueNarratorsForBook(bookName);		

		assertEquals(expectedList, narrators);
	}

	@Test
	@DisplayName("Test case 44: Book Name is Null")
	void isGetUniqueNarratorsForBook_4()
	{
		String bookName =null;
		List<String> expectedList = new ArrayList<>();
		List<String> narrators;
		narrators = bll.getUniqueNarratorsForBook(bookName);		

		assertEquals(expectedList, narrators);
	}

	@Test
	@DisplayName("Test case 45: Book Name is Empty String")
	void isGetUniqueNarratorsForBook_5()
	{
		String bookName =" ";
		List<String> expectedList = new ArrayList<>();
		List<String> narrators;
		narrators = bll.getUniqueNarratorsForBook(bookName);		

		assertEquals(expectedList, narrators);
	}


	//FOURTH POINT END//
}
