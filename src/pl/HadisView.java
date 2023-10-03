package pl;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import bll.BLL;
import bll.HadisBLL;
import to.HadisTO;

public class HadisView implements ActionListener
{

	private static HadisTO obj;
	private static BLL importDataBLL;
	private static String productionData="ST.xlsx";
	private static String testData="Data.xlsx";

	private JFrame frmStproject;
	private JButton btnIterationOne;
	private JButton btnIterationTwo;
	private JButton deleteBtn;
	private JTextField deleteBox;
	private JTextField resultTextField;
	private JTable table_1;
	private JScrollPane scrollPane;
	private JFrame iterationFrame;
	private JFrame iterationFrame2;
	private JTextField updateId;
	private JLabel lblNewLabel_3;
	private JTextField updateBook;
	private JButton updateBtn;
	private JTextField narratorTxt;

	private JList<String> list;
	private JButton findToBtn;
	private JLabel lblNewLabel_5;
	private JButton findFromBtn;
	private JTextField errorTxt;
	private JLabel lblNewLabel_6;
	private JComboBox<String> bookNames;
	private List<String> uniqueBookNames;
	private JButton uniqueNarratorsBtn;
	private JLabel lblNewLabel_7;
	private JComboBox<String> levelTxt;
	private JButton uniqueNarratorsLevelBtn;

	public HadisView() throws SQLException
	{
		importDataBLL = new HadisBLL();
		obj = new HadisTO();
		uniqueBookNames = importDataBLL.getBookNames();


		frmStproject = new JFrame("ST-Project");
		frmStproject.getContentPane().setBackground(new Color(60, 179, 113));
		frmStproject.setTitle("Menu");
		btnIterationOne = new JButton("Deliverable 1");
		btnIterationOne.setFont(new Font("Ariel", Font.PLAIN, 15));
		btnIterationOne.setBackground(Color.WHITE);
		btnIterationTwo = new JButton("Deliverable 2");
		btnIterationTwo.setFont(new Font("Ariel", Font.PLAIN, 15));
		btnIterationTwo.setBackground(Color.WHITE);
	}

	public HadisView(BLL bo)
	{
		this.importDataBLL=bo;
	}


	public void viewFrame()
	{
		btnIterationOne.setBounds(41, 80, 306, 36);
		btnIterationOne.addActionListener(this);
		btnIterationTwo.addActionListener(this);
		frmStproject.getContentPane().add(btnIterationOne);

		frmStproject.setSize(407,246);
		frmStproject.getContentPane().setLayout(null);

		btnIterationTwo.setBounds(41, 139, 306, 36);
		frmStproject.getContentPane().add(btnIterationTwo);

		JLabel lblNewLabel = new JLabel("Software Testing CS4036 Final Project");
		lblNewLabel.setFont(new Font("Ariel", Font.BOLD, 20));
		lblNewLabel.setBounds(88, 24, 241, 30);
		frmStproject.getContentPane().add(lblNewLabel);
		frmStproject.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object temp = e.getSource();

		if(temp==btnIterationOne)
		{
			this.openFirstIterationFrame();
		}
		else if(temp==btnIterationTwo)
		{
			this.openSecondIterationFrame();
		}else if(temp==deleteBtn)
		{
			this.deleteRecord();
		}else if(temp==updateBtn)
		{
			this.updateRecord();
		}else if(temp==findToBtn)
		{
			this.getToNarrators();
		}else if(temp==findFromBtn)
		{
			this.getFromNarrators();
		}else if(temp==uniqueNarratorsBtn)
		{
			this.getUniqueNarratorsList();
		}else if(temp==uniqueNarratorsLevelBtn)
		{
			this.getLevelNarratorsList();
		}
	}


	private void openSecondIterationFrame() {

		iterationFrame2 = new JFrame();
		iterationFrame2.setTitle("Deliverable 2");
		iterationFrame2.setLocationRelativeTo(null);
		iterationFrame2.getContentPane().setBackground(	new Color(144,238,144));
		iterationFrame2.setSize(688, 534);
		iterationFrame2.getContentPane().setLayout(null);

		JLabel lblNewLabel_4 = new JLabel("Narrator Name");
		lblNewLabel_4.setFont(new Font("Ariel", Font.BOLD, 15));
		lblNewLabel_4.setBounds(10, 26, 126, 40);
		iterationFrame2.getContentPane().add(lblNewLabel_4);

		narratorTxt = new JTextField();
		narratorTxt.setFont(new Font("Ariel", Font.PLAIN, 14));
		narratorTxt.setBounds(135, 29, 277, 40);
		iterationFrame2.getContentPane().add(narratorTxt);
		narratorTxt.setColumns(10);

		list = new JList<>();
		list.setFont(new Font("Ariel", Font.PLAIN, 15));

		scrollPane = new JScrollPane(list);
		scrollPane.setSize(277, 170);
		scrollPane.setLocation(135, 240);
		iterationFrame2.getContentPane().add(scrollPane);

		findToBtn = new JButton("To Narrator");
		findToBtn.setFont(new Font("Ariel", Font.PLAIN, 15));
		findToBtn.setBounds(435, 66, 216, 40);
		findToBtn.setBackground(Color.WHITE);
		findToBtn.addActionListener(this);
		iterationFrame2.getContentPane().add(findToBtn);

		lblNewLabel_5 = new JLabel("Result");
		lblNewLabel_5.setFont(new Font("Ariel", Font.BOLD, 15));
		lblNewLabel_5.setBounds(10, 266, 126, 40);
		iterationFrame2.getContentPane().add(lblNewLabel_5);

		findFromBtn = new JButton("From Narrator");
		findFromBtn.setFont(new Font("Ariel", Font.PLAIN, 15));
		findFromBtn.setBounds(435, 128, 216, 40);
		findFromBtn.setBackground(Color.WHITE);
		findFromBtn.addActionListener(this);
		iterationFrame2.getContentPane().add(findFromBtn);

		errorTxt = new JTextField();
		errorTxt.setFont(new Font("Ariel", Font.BOLD | Font.ITALIC, 13));
		errorTxt.setHorizontalAlignment(SwingConstants.CENTER);
		errorTxt.setEditable(false);
		errorTxt.setBounds(146, 448, 394, 40);
		iterationFrame2.getContentPane().add(errorTxt);
		errorTxt.setColumns(10);

		lblNewLabel_6 = new JLabel("Book Name");
		lblNewLabel_6.setFont(new Font("Ariel", Font.BOLD, 15));
		lblNewLabel_6.setBounds(10, 94, 126, 40);
		iterationFrame2.getContentPane().add(lblNewLabel_6);

		bookNames = new JComboBox<String>();
		bookNames.setBackground(new Color(255, 255, 255));
		for(String book:this.uniqueBookNames)
		{
			bookNames.addItem(book);
		}
		bookNames.setBounds(135, 96, 277, 40);
		iterationFrame2.getContentPane().add(bookNames);

		uniqueNarratorsBtn = new JButton("Find Unique Narrators");
		uniqueNarratorsBtn.setFont(new Font("Ariel", Font.PLAIN, 15));
		uniqueNarratorsBtn.setBounds(435, 197, 216, 40);
		uniqueNarratorsBtn.addActionListener(this);
		uniqueNarratorsBtn.setBackground(Color.WHITE);
		iterationFrame2.getContentPane().add(uniqueNarratorsBtn);

		lblNewLabel_7 = new JLabel("Level #");
		lblNewLabel_7.setFont(new Font("Ariel", Font.BOLD, 15));
		lblNewLabel_7.setBounds(10, 161, 126, 40);
		iterationFrame2.getContentPane().add(lblNewLabel_7);

		levelTxt = new JComboBox<String>();
		levelTxt.setFont(new Font("Ariel", Font.PLAIN, 15));
		levelTxt.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"
				, "12", "13", "14", "15", "16", "17", "18", "19", "20"}));
		levelTxt.setBackground(Color.WHITE);
		levelTxt.setBounds(135, 163, 277, 40);
		iterationFrame2.getContentPane().add(levelTxt);

		uniqueNarratorsLevelBtn = new JButton("Find Unique Narrators LVL");
		uniqueNarratorsLevelBtn.setFont(new Font("Ariel", Font.PLAIN, 15));
		uniqueNarratorsLevelBtn.setBounds(435, 266, 216, 40);
		uniqueNarratorsLevelBtn.addActionListener(this);
		uniqueNarratorsLevelBtn.setBackground(Color.WHITE);
		iterationFrame2.getContentPane().add(uniqueNarratorsLevelBtn);

		iterationFrame2.setVisible(true);
		iterationFrame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void openFirstIterationFrame() {


		iterationFrame = new JFrame("First Iteration Frame");
		iterationFrame.getContentPane().setBackground(	new Color(144,238,144));
		iterationFrame.setTitle("Deliverable 1");
		iterationFrame.setSize(686, 600);
		iterationFrame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Hadis ID");
		lblNewLabel.setFont(new Font("Ariel", Font.BOLD, 16));
		lblNewLabel.setBounds(24, 333, 79, 33);
		iterationFrame.getContentPane().add(lblNewLabel);

		deleteBox = new JTextField();
		deleteBox.setFont(new Font("Ariel", Font.PLAIN, 15));
		deleteBox.setBounds(103, 337, 175, 25);
		iterationFrame.getContentPane().add(deleteBox);
		deleteBox.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Delete Record");
		lblNewLabel_1.setFont(new Font("Ariel", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_1.setBounds(24, 298, 254, 25);
		iterationFrame.getContentPane().add(lblNewLabel_1);

		deleteBtn = new JButton("Delete");
		deleteBtn.setFont(new Font("Ariel", Font.PLAIN, 15));
		deleteBtn.setBounds(194, 437, 111, 29);
		deleteBtn.addActionListener(this);
		deleteBtn.setBackground(Color.WHITE);
		iterationFrame.getContentPane().add(deleteBtn);

		resultTextField = new JTextField();
		resultTextField.setHorizontalAlignment(SwingConstants.CENTER);
		resultTextField.setFont(new Font("Ariel", Font.BOLD | Font.ITALIC, 20));
		resultTextField.setEditable(false);
		resultTextField.setBounds(26, 489, 621, 48);
		iterationFrame.getContentPane().add(resultTextField);
		resultTextField.setColumns(10);

		List<HadisTO> dataList = importDataBLL.getData();

		String[] columnNames = {"UID", "Hadith", "Book", "Num Hadith", "Matn", "Sanad"};
		Object[][] rowData = new Object[dataList.size()][7];

		for (int i = 0; i < dataList.size(); i++) {
			HadisTO dataTO = dataList.get(i);
			rowData[i][0] = dataTO.getUid();
			rowData[i][1] = dataTO.getHadith();
			rowData[i][2] = dataTO.getBook();
			rowData[i][3] = dataTO.getNumHadith();
			rowData[i][4] = dataTO.getMatn();
			rowData[i][5] = dataTO.getSanad();
		}

		table_1 = new JTable(rowData,columnNames);
		table_1.setEnabled(false);
		table_1.setFont(new Font("Ariel", Font.PLAIN, 13));
		table_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		table_1.setBounds(36, 178, 681, -136);
		scrollPane = new JScrollPane(table_1);
		scrollPane.setBounds(26, 30, 621,224);
		iterationFrame.getContentPane().add(scrollPane);

		JLabel lblNewLabel_1_1 = new JLabel("Update Record");
		lblNewLabel_1_1.setFont(new Font("Ariel", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_1_1.setBounds(372, 298, 254, 25);
		iterationFrame.getContentPane().add(lblNewLabel_1_1);

		updateBtn = new JButton("Update");
		updateBtn.setFont(new Font("Ariel", Font.PLAIN, 15));
		updateBtn.setBounds(338, 437, 111, 29);
		updateBtn.setBackground(Color.WHITE);
		updateBtn.addActionListener(this);
		iterationFrame.getContentPane().add(updateBtn);

		JLabel lblNewLabel_2 = new JLabel("Hadis ID");
		lblNewLabel_2.setFont(new Font("Ariel", Font.BOLD, 16));
		lblNewLabel_2.setBounds(372, 333, 79, 33);
		iterationFrame.getContentPane().add(lblNewLabel_2);

		updateId = new JTextField();
		updateId.setFont(new Font("Ariel", Font.PLAIN, 15));
		updateId.setColumns(10);
		updateId.setBounds(461, 333, 175, 25);
		iterationFrame.getContentPane().add(updateId);

		lblNewLabel_3 = new JLabel("Book Name");
		lblNewLabel_3.setFont(new Font("Ariel", Font.BOLD, 15));
		lblNewLabel_3.setBounds(372, 376, 79, 33);
		iterationFrame.getContentPane().add(lblNewLabel_3);

		updateBook = new JTextField();
		updateBook.setFont(new Font("Ariel", Font.PLAIN, 15));
		updateBook.setColumns(10);
		updateBook.setBounds(461, 380, 175, 25);
		iterationFrame.getContentPane().add(updateBook);
		iterationFrame.setVisible(true);

		iterationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void refreshRecord()
	{
		List<HadisTO> dataList = importDataBLL.getData();

		String[] columnNames = {"UID", "Hadith", "Book", "Num Hadith", "Matn", "Sanad"};
		Object[][] rowData = new Object[dataList.size()][7];

		for (int i = 0; i < dataList.size(); i++) {
			HadisTO dataTO = dataList.get(i);
			rowData[i][0] = dataTO.getUid();
			rowData[i][1] = dataTO.getHadith();
			rowData[i][2] = dataTO.getBook();
			rowData[i][3] = dataTO.getNumHadith();
			rowData[i][4] = dataTO.getMatn();
			rowData[i][5] = dataTO.getSanad();
		}

		table_1 = new JTable(rowData,columnNames);
		table_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		table_1.setBounds(36, 178, 681, -136);
		scrollPane = new JScrollPane(table_1);
		scrollPane.setBounds(26, 30, 720,224);
		iterationFrame.getContentPane().add(scrollPane);
	}

	private void deleteRecord()
	{
		if(!deleteBox.getText().isEmpty())
		{
			Integer id = Integer.parseInt(deleteBox.getText());
			obj.setUid(id);

			boolean success = importDataBLL.deleteBAL(obj);
			if(success)
			{
				resultTextField.setText("Record Deleted Successfully");
				resultTextField.setForeground(new Color(124,252,0));
				refreshRecord();
			}else
			{
				resultTextField.setText("No Record is Present");
				resultTextField.setForeground(new Color(255,160,122));
			}
		}else
		{
			resultTextField.setText("Enter Your Desired ID in the Text Field");
			resultTextField.setForeground(new Color(255,160,122));
		}
	}

	private void updateRecord()
	{
		if(!updateId.getText().isEmpty() || !updateBook.getText().isEmpty() )
		{
			Integer id = Integer.parseInt(updateId.getText());
			String bookname = updateBook.getText();

			obj.setUid(id);
			obj.setBook(bookname);

			boolean success = importDataBLL.updateData(obj);
			if(success)
			{
				resultTextField.setText("Record Updated Successfully");
				resultTextField.setForeground(new Color(124,252,0));
				refreshRecord();
			}else
			{
				resultTextField.setText("No Record Found with Desired ID");
				resultTextField.setForeground(new Color(255,160,122));
			}

		}else
		{
			resultTextField.setText("Enter Your Desired ID and Book Name");
			resultTextField.setForeground(new Color(255,160,122));
		}

	}

	private void getToNarrators()
	{
		if(!narratorTxt.getText().isEmpty())
		{
			String givenNarrator = narratorTxt.getText();
			List<String> narratorList = importDataBLL.getDirectNarrators(givenNarrator);
			if(narratorList.isEmpty())
			{
				errorTxt.setText("No Narrators Found");
				errorTxt.setForeground(new Color(255,0,0));
			}else
			{
				errorTxt.setText("");
				String[] dataArray = narratorList.toArray(new String[narratorList.size()]);
				list.setListData(dataArray);
			}
		}else
		{
			String[] dataArray =new String[1];
			list.setListData(dataArray);
			errorTxt.setText("Enter Narrator Name");
			errorTxt.setForeground(new Color(255,0,0));
		}


	}

	private void getFromNarrators()
	{
		if(!narratorTxt.getText().isEmpty())
		{
			String givenNarrator = narratorTxt.getText();
			List<String> narratorList = importDataBLL.getFromNarrators(givenNarrator);
			if(narratorList.isEmpty())
			{
				errorTxt.setText("No Narrators Found");
				errorTxt.setForeground(new Color(255,0,0));
			}else
			{
				errorTxt.setText(" ");
				String[] dataArray = narratorList.toArray(new String[narratorList.size()]);
				list.setListData(dataArray);
			}
		}else
		{
			String[] dataArray =new String[1];
			list.setListData(dataArray);
			errorTxt.setText("Enter Narrator Name");
			errorTxt.setForeground(new Color(255,0,0));
		}
	}

	public void getUniqueNarratorsList()
	{

		String selectedBook = (String) bookNames.getSelectedItem();

		if(!selectedBook.isEmpty())
		{
			List<String> uniqueNarrators = importDataBLL.getUniqueNarratorsForBook(selectedBook);
			if(uniqueNarrators.isEmpty())
			{
				String[] dataArray =new String[1];
				list.setListData(dataArray);
				errorTxt.setText("No Narrators Found");
				errorTxt.setForeground(new Color(255,0,0));
			}else
			{
				errorTxt.setText("");
				String[] dataArray = uniqueNarrators.toArray(new String[uniqueNarrators.size()]);
				list.setListData(dataArray);
			}

		}else
		{
			String[] dataArray =new String[1];
			list.setListData(dataArray);
			errorTxt.setText("Select Book Name");
			errorTxt.setForeground(new Color(255,0,0));
		}


	}

	public void getLevelNarratorsList() {
		String selectedBook = (String) bookNames.getSelectedItem();
		Integer level = Integer.parseInt((String) levelTxt.getSelectedItem());

		List<String> uniqueNarratorsAtN = importDataBLL.getUniqueNarratorsAtLevelN(selectedBook, level);
		if(uniqueNarratorsAtN.isEmpty())
		{
			String[] dataArray =new String[1];
			list.setListData(dataArray);
			errorTxt.setText("No Narrators Found");
			errorTxt.setForeground(new Color(255,0,0));
		}else
		{
			errorTxt.setText("");
			String[] dataArray = uniqueNarratorsAtN.toArray(new String[uniqueNarratorsAtN.size()]);
			list.setListData(dataArray);
		}

	}
	public static void main(String[] args) 
	{
		HadisView app;
		try {
			app = new HadisView();;
			//app.openSecondIterationFrame();
			app.viewFrame();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static class __Tmp {
		private static void __tmp() {
			javax.swing.JPanel __wbp_panel = new javax.swing.JPanel();
		}
	}
}




