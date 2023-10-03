package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import to.HadisTO;

public class HadisDAO implements IDAL{
	private Connection connection;
	private PreparedStatement preparedStatement;

	private String deleteQuery = "DELETE FROM `dataset` WHERE `Id` = ?";
	//private String updateQuery = "UPDATE `dataset` SET hadith = ?, book = ?, num_hadith = ?, matn = ?, sanad = ?, sanad_length = ? WHERE Id = ?";
	private String updateQuery = "UPDATE `dataset` SET book = ? WHERE Id = ?";
	private String insertQuery = "INSERT INTO `dataset` (hadith, book, um_hadith, matn, sanad, sanad_length ) VALUES (?, ?, ?, ?, ?, ?)";
	private String nameQuery ="SELECT DISTINCT book FROM `dataset`";
	private String distinctNarratorsQuery = "SELECT sanad FROM `dataset` WHERE book = ?";
	public HadisDAO()
	{

	}

	public HadisDAO(Connection con)
	{
		this.connection = con;
	}


	private boolean connect() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/hadithnarrators?useSSL=false";
		String username = "root";
		String password = "";
		try {
			connection = DriverManager.getConnection(url, username, password);
			return true;
		}catch (SQLException ex) {
			return false;
				}
	}

	private void disconnect() throws SQLException {
		if (preparedStatement != null) {
			preparedStatement.close();
		}
		if (connection != null) {
			connection.close();
		}
	}

	@Override
	public boolean insertData(List<HadisTO> dataList) {
		try {
			if(connect())
			{
				String sql = this.insertQuery;
				for (HadisTO dataTO : dataList) {
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, dataTO.getHadith());
					preparedStatement.setString(2, dataTO.getBook());
					preparedStatement.setInt(3, dataTO.getNumHadith());
					preparedStatement.setString(4, dataTO.getMatn());
					preparedStatement.setString(5, dataTO.getSanad());
					preparedStatement.setInt(6, dataTO.getSanadLength());
					preparedStatement.executeUpdate();
				}
				disconnect();
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}


	/**
	 * 
	 * @author Hasaan
	 *
	 */
	@Override
	public boolean updateData(HadisTO dataTo) {
		if(dataTo!=null)
		{
			try {
				if(connect())
				{
					String sql = this.updateQuery;
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, dataTo.getHadith());
					preparedStatement.setString(2, dataTo.getBook());
					preparedStatement.setInt(3, dataTo.getNumHadith());
					preparedStatement.setString(4, dataTo.getMatn());
					preparedStatement.setString(5, dataTo.getSanad());
					preparedStatement.setInt(6, dataTo.getSanadLength());
					preparedStatement.setInt(7, dataTo.getUid());
					int rowsUpdated = preparedStatement.executeUpdate();
					disconnect();
					return rowsUpdated > 0;
				}

			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}

		return false;
	}

	/**
	 * 
	 * @author Hasaan
	 *
	 */
	@Override
	public boolean insertNewData(HadisTO dataTo) {

		if(dataTo!=null)
		{
			try {
				if(connect())
				{
					String sql = this.insertQuery;
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, dataTo.getHadith());
					preparedStatement.setString(2, dataTo.getBook());
					preparedStatement.setInt(3, dataTo.getNumHadith());
					preparedStatement.setString(4, dataTo.getMatn());
					preparedStatement.setString(5, dataTo.getSanad());
					preparedStatement.setInt(6, dataTo.getSanadLength());
					int rowsInserted = preparedStatement.executeUpdate();
					disconnect();
					return rowsInserted > 0;
				}

			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}

		return false;
	}


	/**
	 * @author Hasaan
	 */
	@Override
	public List<String> getUniqueBookNames()
	{
		List<String> uniqueBooks = new ArrayList<>();
		try {
			if(connect())
			{
				String query = this.nameQuery;
				preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					String book = resultSet.getString("book");
					uniqueBooks.add(book);
				}
				disconnect();
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}

		return uniqueBooks;
	}



	/**
	 * 
	 * @author Ahad
	 *
	 */
	@Override
	public List<HadisTO> getAllData() {
		List<HadisTO> dataList = new ArrayList<>();
		try {
			if(connect())
			{
				String sql = "SELECT * FROM `dataset`";
				preparedStatement = connection.prepareStatement(sql);
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					HadisTO dataTO = new HadisTO();
					dataTO.setUid(resultSet.getInt("Id"));
					dataTO.setHadith(resultSet.getString("hadith"));
					dataTO.setBook(resultSet.getString("book"));
					dataTO.setNumHadith(resultSet.getInt("um_hadith"));
					dataTO.setMatn(resultSet.getString("matn"));
					dataTO.setSanad(resultSet.getString("sanad"));
					dataTO.setSanadLength(resultSet.getInt("sanad_length"));
					dataList.add(dataTO);
				}
				disconnect();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataList;
	}


	/**
	 * 
	 * @author Ahad
	 *
	 */
	@Override
	public List<HadisTO> getDataByBook(String book) {
		List<HadisTO> dataList = new ArrayList<>();
		try {
			if(connect())
			{
				String sql = "SELECT * FROM `dataset` WHERE `book` = ?";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, book);
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					HadisTO dataTO = new HadisTO();
					dataTO.setUid(resultSet.getInt("Id"));
					dataTO.setHadith(resultSet.getString("hadith"));
					dataTO.setBook(resultSet.getString("book"));
					dataTO.setNumHadith(resultSet.getInt("um_hadith"));
					dataTO.setMatn(resultSet.getString("matn"));
					dataTO.setSanad(resultSet.getString("sanad"));
					dataTO.setSanadLength(resultSet.getInt("sanad_length"));
					dataList.add(dataTO);
				}
				disconnect();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataList;
	}




	/**
	 * 
	 * @author Bilal 
	 *
	 */
	@Override
	public boolean deleteData(HadisTO dataTo)
	{
		if(dataTo!=null)
		{
			try {
				if(connect())
				{
					String sql = this.deleteQuery;
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setInt(1, dataTo.getUid());
					int rowsDeleted = preparedStatement.executeUpdate();
					disconnect();
					return rowsDeleted > 0;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}


}