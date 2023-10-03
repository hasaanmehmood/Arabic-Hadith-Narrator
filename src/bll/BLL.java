package bll;

import java.util.List;
import java.util.Map;

import to.HadisTO;

public interface BLL {

	 public boolean ImportData(String file);
	 public boolean updateData(HadisTO dataTo);
	 public boolean insertData(HadisTO dataTo);
	 public boolean deleteBAL(HadisTO dataTo);
	 public List<HadisTO> getData();
	 public List<String> getDirectNarrators(String givenNarrator);
	 public List<String> getFromNarrators(String givenNarrator);
	 public List<String> getUniqueNarratorsAtLevelN(String book, int level);
	 public Map<String, List<String>> createNarratorMap();
	 public List<String> getBookNames();
	 public List<String> getUniqueNarratorsForBook(String bookName);
	 
}
