package dal;

import java.util.List;

import to.HadisTO;

public interface IDAL {
	 public boolean insertData(List<HadisTO> dataList);
	 public boolean updateData(HadisTO dataTo);
	 public boolean insertNewData(HadisTO dataTo);
	 public boolean deleteData(HadisTO dataTo);
	 public List<HadisTO> getAllData();
	 public List<HadisTO> getDataByBook(String Book);
	 public List<String> getUniqueBookNames();
}
