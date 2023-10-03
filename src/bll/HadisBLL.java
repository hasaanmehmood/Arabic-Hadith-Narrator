package bll;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import dal.HadisDAO;
import dal.IDAL;
import to.HadisTO;


public class HadisBLL implements BLL{
	private IDAL importData;

	private Map<String, List<String>> narratorMap;

	public HadisBLL()
	{
		importData = new HadisDAO();
	} 

	public HadisBLL(IDAL data)
	{
		this.importData=data;
	}

	@Override
	public boolean ImportData(String file) {
		try {
			List<HadisTO> dataList = readExcelFile(file);
			return importData.insertData(dataList);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	private List<HadisTO> readExcelFile(String file) throws IOException {
		List<HadisTO> dataList = new ArrayList<>();
		FileInputStream fileInputStream = new FileInputStream(new File(file));
		XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			if (row.getRowNum() == 0) {
				continue;
			}
			HadisTO dataTO = new HadisTO();
			dataTO.setHadith(row.getCell(0).getStringCellValue());
			dataTO.setBook(row.getCell(1).getStringCellValue());
			dataTO.setNumHadith((int) row.getCell(2).getNumericCellValue());
			dataTO.setMatn(row.getCell(3).getStringCellValue());
			dataTO.setSanad(row.getCell(4).getStringCellValue());
			dataTO.setSanadLength((int) row.getCell(5).getNumericCellValue());
			dataList.add(dataTO);
		}
		workbook.close();
		fileInputStream.close();
		return dataList;
	}


	/**
	 * 
	 * @author Hasaan
	 *
	 */
	@Override
	public boolean updateData(HadisTO dataTo)
	{
		if(importData.updateData(dataTo))
		{
			return true;
		}else
		{
			return false;
		}
	}


	/**
	 * 
	 * @author Hasaan
	 *
	 */
	@Override 
	public boolean insertData(HadisTO dataTo)
	{
		if(importData.insertNewData(dataTo))
		{
			return true;
		}else
		{
			return false;
		}
	}


	/**
	 * @author Hasaan
	 */
	@Override 
	public List<String> getBookNames()
	{
		return importData.getUniqueBookNames();
	}


	/**
	 * 
	 * @author Ahad
	 *
	 */
	@Override
	public List<HadisTO> getData()
	{
		return importData.getAllData();
	}


	/**
	 * 
	 * @author Bilal
	 *
	 */
	@Override
	public boolean deleteBAL(HadisTO dataTo)
	{
		if(importData.deleteData(dataTo))
		{
			return true;
		}else
		{
			return false;
		}
	}



	/**
	 * @author Bilal
	 */
	@Override
	public List<String> getDirectNarrators(String givenNarrator) {
		givenNarrator = "'" + givenNarrator + "'";
		narratorMap = createNarratorMap();
		List<String> narratorsToX = narratorMap.get(givenNarrator);

		if (narratorsToX == null) {
			return new ArrayList<String>();
		}
		return narratorsToX;
	}


	@Override
	public Map<String, List<String>> createNarratorMap() {
		List<HadisTO> dataList = importData.getAllData();
		Map<String, List<String>> narratorMap = new HashMap<>();

		for (HadisTO data : dataList) {
			String sanad = data.getSanad();
			String[] narrators = sanad.split(",\\s*");
			for (int i = 0; i < narrators.length - 1; i++) {
				String currentNarrator = removeSpecialCharacters(narrators[i].trim());
				String nextNarrator = removeSpecialCharacters(narrators[i + 1].trim());
				List<String> narratorsToX = narratorMap.get(nextNarrator);
				if (narratorsToX == null) {
					narratorsToX = new ArrayList<>();
				}
				if (!narratorsToX.contains(currentNarrator)) {
					narratorsToX.add(currentNarrator);
				}
				narratorMap.put(nextNarrator, narratorsToX);
			}
		}
		return narratorMap;
	}

	private String removeSpecialCharacters(String narrator) {
		narrator = narrator.replaceAll("[,\\[\\]]", "");
		return narrator;
	}


	/**
	 * @author Ahad
	 */
	@Override
	public List<String> getFromNarrators(String givenNarrator) {
		givenNarrator = "'" + givenNarrator + "'";
		narratorMap = createNarratorMap();
		List<String> narratorsFromX = new ArrayList<>();
		for (Map.Entry<String, List<String>> entry : narratorMap.entrySet()) {
			String narrator = entry.getKey();
			List<String> narratorsToX = entry.getValue();
			for (String n : narratorsToX) {
				if (n.equals(givenNarrator)) {
					narratorsFromX.add(narrator);
					break;
				}
			}
		}
		return narratorsFromX;
	}


	/**
	 * @author Bilal
	 */
	@Override
	public List<String> getUniqueNarratorsForBook(String bookName)
	{
		List<HadisTO> dataList = importData.getDataByBook(bookName);
		Set<String> uniqueNarrators = new HashSet<>();

		if(dataList==null)
		{
			return new ArrayList<>();
		}

		for (HadisTO data : dataList) {
			String sanad = data.getSanad();
			String[] narrators = sanad.split(",\\s*");
			for (String narrator : narrators) {
				String cleanedNarrator = removeSpecialCharacters(narrator.trim());
				uniqueNarrators.add(cleanedNarrator);
			}
		}

		return new ArrayList<>(uniqueNarrators);
	}

	/**
	 * @author Bilal
	 */
	@Override
	public List<String> getUniqueNarratorsAtLevelN(String book, int level) {
		List<HadisTO> dataList = importData.getDataByBook(book);
		Map<String, Set<String>> narratorLevels = new HashMap<>();
		List<String> uniqueNarrators = new ArrayList<>();

		if(dataList!=null && level>0)
		{
			for (HadisTO data : dataList) {
				String sanad = data.getSanad();
				String[] narrators = sanad.split(",");

				if (narrators.length >= level) {
					String narrator = narrators[level - 1].trim();
					Set<String> levels = narratorLevels.get(narrator);

					if (levels == null) {
						levels = new LinkedHashSet<>();
						narratorLevels.put(narrator, levels);
					}

					levels.add(data.getBook() + "-" + data.getNumHadith() + "-" + level);
				}
			}



			for (Map.Entry<String, Set<String>> entry : narratorLevels.entrySet()) {
				String narrator = entry.getKey();
				Set<String> levels = entry.getValue();

				if (levels.size() == level) {
					uniqueNarrators.add(narrator);
				}
			}
		}


		return uniqueNarrators;
	}


}
