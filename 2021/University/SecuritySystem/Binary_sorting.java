package myPackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class Binary_sorting {

	private JFrame frmBinarySorting;
	private JTextField Range;
	private JTextField Number;
	private JTextField Array;
	private JTextField SortedArray;
	private JTextArea SortingLog;
	private JRadioButton rb_small_to_big;
	private JRadioButton rb_big_to_small;
	private JLabel Log;
	
	final Random myRandom = new Random();
	
	
	int arr [] = new int [100];
	int arr_len;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Binary_sorting window = new Binary_sorting();
					window.frmBinarySorting.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Binary_sorting() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBinarySorting = new JFrame();
		frmBinarySorting.setTitle("Binary Sorting");
		frmBinarySorting.setBounds(100, 100, 585, 552);
		frmBinarySorting.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBinarySorting.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Range : ");
		lblNewLabel.setBounds(12, 28, 57, 15);
		frmBinarySorting.getContentPane().add(lblNewLabel);
		
		Range = new JTextField();
		Range.setText("10");
		Range.setBounds(81, 25, 73, 21);
		frmBinarySorting.getContentPane().add(Range);
		Range.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Number :");
		lblNewLabel_1.setBounds(202, 28, 57, 15);
		frmBinarySorting.getContentPane().add(lblNewLabel_1);
		
		Number = new JTextField();
		Number.setText("5");
		Number.setBounds(274, 25, 85, 21);
		frmBinarySorting.getContentPane().add(Number);
		Number.setColumns(10);
		
		JButton btnNewButton = new JButton("Generate");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				arr_len = Integer.parseInt(Number.getText());
				for(int i=0;i<arr_len;++i)
				{
					arr[i] = myRandom.nextInt(Integer.parseInt(Range.getText()));
							
				}
				
				Array.setText(ArrtoString(arr, arr_len));
				Log.setText("<html> Log : <br>");
				Log.setText(Log.getText() + "Reading range & arr_len. Done <br>");
				Log.setText(Log.getText() + "Generate random numbers. Done <br>");
			}
		});
		btnNewButton.setBounds(417, 24, 97, 23);
		frmBinarySorting.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Sort");
		btnNewButton_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				SortingLog.setText("");
				int sorted_arr[] = new int [arr_len];
				 
				if(rb_small_to_big.isSelected())
				{
					sorted_arr = small_to_big(arr,arr_len);
				}
				else
				{
					sorted_arr = big_to_small(arr,arr_len);
				}
				
				SortedArray.setText(ArrtoString(sorted_arr,arr_len));
				
			}
		});
		btnNewButton_1.setBounds(417, 57, 97, 23);
		frmBinarySorting.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("Array : ");
		lblNewLabel_2.setBounds(12, 103, 57, 15);
		frmBinarySorting.getContentPane().add(lblNewLabel_2);
		
		Array = new JTextField();
		Array.setBounds(81, 100, 290, 21);
		frmBinarySorting.getContentPane().add(Array);
		Array.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Sorted Array :");
		lblNewLabel_3.setBounds(12, 144, 104, 15);
		frmBinarySorting.getContentPane().add(lblNewLabel_3);
		
		SortedArray = new JTextField(); 
		SortedArray.setBounds(113, 141, 258, 21);
		frmBinarySorting.getContentPane().add(SortedArray);
		SortedArray.setColumns(10);
		
		//JLabel Log = new JLabel("Log : ");
		Log = new JLabel("Log : ");
		Log.setVerticalAlignment(SwingConstants.TOP);
		Log.setBounds(12, 340, 258, 159);
		frmBinarySorting.getContentPane().add(Log);
		
		arr_len = Integer.parseInt(Number.getText());
		Array.setText(ArrtoString(arr, arr_len));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(282, 184, 258, 295);
		frmBinarySorting.getContentPane().add(scrollPane);
		
		//JTextArea SortingLog = new JTextArea();
		SortingLog = new JTextArea();
		scrollPane.setViewportView(SortingLog);
		
		JLabel lblNewLabel_4 = new JLabel("sort : ");
		lblNewLabel_4.setBounds(12, 180, 57, 15);
		frmBinarySorting.getContentPane().add(lblNewLabel_4);
		
		//JRadioButton rb_small_to_big = new JRadioButton("from small to big");
		rb_small_to_big = new JRadioButton("from small to big");
		rb_small_to_big.setSelected(true);
		buttonGroup.add(rb_small_to_big);
		rb_small_to_big.setBounds(12, 201, 121, 23);
		frmBinarySorting.getContentPane().add(rb_small_to_big);
		
		//JRadioButton rb_big_to_small = new JRadioButton("from big to small");
		rb_big_to_small = new JRadioButton("from big to small");
		buttonGroup.add(rb_big_to_small);
		rb_big_to_small.setBounds(12, 238, 121, 23);
		frmBinarySorting.getContentPane().add(rb_big_to_small);
	}

	public String ArrtoString (int [] arr, int len)
	{
		String res = "";
		
		for(int i=0;i<len;++i)
		{
			res = res + String.valueOf(arr[i]) + ", ";
		}
		
		return res; 	
	}
	
	
	public int[] small_to_big(int [] arr, int len)
	{
		String str1;
		int tmp;
		
		for(int i=0; i < len - 1 ;++i)
		{
			SortingLog.setText(SortingLog.getText()+ "step #" + i + "\n");
			
			for(int j=i+1 ; j<len ; ++j)
			{
				str1="skip";
				
				if(arr[i] > arr[j])
				{
					tmp=arr[i];
					arr[i]=arr[j];
					arr[j]=tmp;
					str1="swap ";
				}
				
				SortingLog.setText(SortingLog.getText() + ArrtoString (arr, len));
				SortingLog.setText(SortingLog.getText() + "| " + "step : "+ i + "  substep : " + j + " (" + str1 +")" + "\n");        
			}
		}
		return arr;
	}
	
	public int[] big_to_small(int [] arr, int len)
	{
		String str1;
		int tmp;
		
		for(int i=0; i < len - 1 ;++i)
		{
			SortingLog.setText(SortingLog.getText()+ "step #" + i + "\n");
			
			for(int j=i+1 ; j<len ; ++j)
			{
				str1="skip";
				
				if(arr[i] < arr[j])
				{
					tmp=arr[i];
					arr[i]=arr[j];
					arr[j]=tmp;
					str1="swap ";
				}
				
				SortingLog.setText(SortingLog.getText() + ArrtoString (arr, len));
				SortingLog.setText(SortingLog.getText() + "| " + "step : "+ i + "  substep : " + j + " (" + str1 +")" + "\n");        
			}
		}
		return arr;
	}
	
	
}


