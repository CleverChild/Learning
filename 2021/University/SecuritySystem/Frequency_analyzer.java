package myPackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Frequency_sorting {

	private JFrame frmFrequencysorting;
	private JTextArea Cipher_Text1;
	private JLabel Log;
	private String cipher_Text1= "PCQVMJYPDLBYKLYSOKBXBJXWXVBXVZCJPOEYPDKBXBJYUXJLBJOOKCPKCPLBOLBCMKXPVXPVIYJKLPYDBLQBOPKBOBXVOPVOVLBOLXROCISXXJMIKBOJCKOXPVEYKKOVLBODJCMPVZOICJOBYSKXUYPDDJOXLEYPDXLBCMKXPVXPVCPOPYDBLKYBXNOZOOPJOACMPLYPDLCUCMLBOIXZROKCIFXKLXDOKXPVLBORODOPVKCIXPAYOPLEYPDKSXUYSXEOKCZCRVXKLCAJXNOXIXNCMJCIUCMJSXGOKLUOFYRCDMOLXROKIJCSLBOLBCMKXPVXPVCPOPYDBLK";
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frequency_sorting window = new Frequency_sorting();
					window.frmFrequencysorting.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Frequency_sorting() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFrequencysorting = new JFrame();
		frmFrequencysorting.setTitle("Frequency_sorting");
		frmFrequencysorting.setBounds(100, 100, 569, 671);
		frmFrequencysorting.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFrequencysorting.getContentPane().setLayout(null);
		
		//JTextArea Cipher_Text1 = new JTextArea();
		Cipher_Text1 = new JTextArea();
		Cipher_Text1.setBounds(12, 10, 417, 196);
		frmFrequencysorting.getContentPane().add(Cipher_Text1);
		
		JButton btnNewButton = new JButton("Analyse");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				char ch;
				int int_ch, int_ch_mod;
				
				int char_frequency[] = new int [26];
				
				for(int j=0;j<26;++j)
				{
					char_frequency[j]=0;
				}
				
				for(int i=0;i<cipher_Text1.length();++i)
				{
					ch = cipher_Text1.charAt(i);
					int_ch = (int) ch;
					int_ch_mod = int_ch - 65;
					
					char_frequency[int_ch_mod] = char_frequency[int_ch_mod] + 1;
					
				}
				
				Log.setText("<html> Log : <br>");
				
				for(int k=0; k<26; ++k)
				{
					Log.setText(Log.getText() + "'" + (char) (k+65) + "'" + char_frequency[k] + ", ");
				}
				
				
				///sorting, display indexes.
				int char_frequency_INDEX_sort[] = BinarySortingIndexing(char_frequency, 26);	
				int char_frequency_sort [] = BinarySorting(char_frequency, 26);
				
				Log.setText(Log.getText() + " <br> Sorting   Characters:  ");         
				
				for(int k=0; k<26; ++k)
				{
					Log.setText(Log.getText() +  (char) (char_frequency_INDEX_sort[k] + 65) +  ":" + char_frequency_sort[k] + ", ");
				}
				
				
				/*Log.setText(Log.getText() + " <br> Indexes:  ");         
				
				for(int k=0; k<26; ++k)
				{
					Log.setText(Log.getText() +  char_frequency_INDEX_sort[k] + ", ");
				}*/
				
				
				// digram  start      
				
				int digram_freq [] = new int [26*26];
				
				for(int j=0;j<26*26;++j)
				{
					digram_freq[j]=0;
				}
				
				
				int digram_index;
				String all_digrams_string [] = new String [26*26];
				
				for(int i=0; i<26;++i)  // i --> ch1
				{
					for(int j=0; j<26;++j) // j --> ch2
					{
						digram_index = i*26 + j;
						all_digrams_string[digram_index] = String.valueOf((char) (i + 65))  + String.valueOf((char) (j + 65));
						
					}
				}
				
				
				for(int i=0;i<cipher_Text1.length() -1 ;++i)
				{
					char ch1 = cipher_Text1.charAt(i);
					char ch2 = cipher_Text1.charAt(i+1);   
					
					int int_ch1 = (int) ch1;
					int int_ch2 = (int) ch2;
					
					int int_ch1_mod = int_ch1 - 65;
					int int_ch2_mod = int_ch2 - 65;
					
					digram_index = int_ch1_mod*26 + int_ch2_mod;
					digram_freq [digram_index] += 1;  
				}
				
				
				int digrams_frequency_INDEX_sort [] = BinarySortingIndexing(digram_freq, 26*26);	
				int digrams_frequency_sort [] = BinarySorting(digram_freq, 26*26);
				
				
				Log.setText(Log.getText() + " <br> Digrams indexes :  ");         
				
				for(int k=0; k<10; ++k)
				{
					Log.setText(Log.getText() +  digrams_frequency_INDEX_sort[k] +", ");
				}
				
				
				
				Log.setText(Log.getText() + " <br> Digrams :  ");         
				
				for(int k=0; k<10; ++k)
				{
					Log.setText(Log.getText() + all_digrams_string[digrams_frequency_INDEX_sort[k]] + ":" +digrams_frequency_sort[k] + ", ");
				}
				
				
				// trigram  start  
				
				int trigram_freq [] = new int [26*26*26];
				
				for(int j=0;j<26*26*26;++j)
				{
					trigram_freq[j]=0;
				}
				
				int trigram_index;
				String all_trigrams_string [] = new String [26*26*26];
				
				
				for(int i=0; i<26;++i)  // i --> ch1
				{
					for(int j=0; j<26;++j) // j --> ch2
					{
						for(int k=0; k<26; ++k) // k--> ch3
						{
							trigram_index = i*26*26 + j*26 + k;
							all_trigrams_string[trigram_index] = String.valueOf( (char) (i + 65) )  + String.valueOf( (char) (j + 65) ) + String.valueOf( (char) (k + 65) );
						}
						
						
					}
				}
				
				for(int i=0;i<cipher_Text1.length() -2 ;++i)
				{
					char ch1 = cipher_Text1.charAt(i);
					char ch2 = cipher_Text1.charAt(i+1);
					char ch3 = cipher_Text1.charAt(i+2);
					
					int int_ch1 = (int) ch1;
					int int_ch2 = (int) ch2;
					int int_ch3 = (int) ch3;
					
					int int_ch1_mod = int_ch1 - 65;
					int int_ch2_mod = int_ch2 - 65;
					int int_ch3_mod = int_ch3 - 65;
					
					trigram_index = int_ch1_mod*26*26 + int_ch2_mod*26 + int_ch3_mod;
					trigram_freq [trigram_index] += 1;  
				}
				
				
				int trigrams_frequency_INDEX_sort [] = BinarySortingIndexing(trigram_freq, 26*26*26);	
				int trigrams_frequency_sort [] = BinarySorting(trigram_freq, 26*26*26);
				
				Log.setText(Log.getText() + " <br> Trigrams indexes :  ");
				
				for(int k=0; k<10; ++k)
				{
					Log.setText(Log.getText() +  trigrams_frequency_INDEX_sort[k] +", ");
				}
				
				
				Log.setText(Log.getText() + " <br> Trigrams :  ");         
				
				for(int k=0; k<10; ++k)
				{
					Log.setText(Log.getText() + all_trigrams_string[trigrams_frequency_INDEX_sort[k]] + ":" +trigrams_frequency_sort[k] + ", ");
				}
				
				
			}
		});
		btnNewButton.setBounds(12, 216, 97, 23);
		frmFrequencysorting.getContentPane().add(btnNewButton);
		
		//JLabel Log = new JLabel("log : ");
		Log = new JLabel("log : ");
		Log.setVerticalAlignment(SwingConstants.TOP);
		Log.setBounds(12, 249, 384, 359);
		frmFrequencysorting.getContentPane().add(Log);
		
		Display_CipherText(cipher_Text1);
	}
	
	
	public int[] BinarySortingIndexing(int [] arr, int len)
	{
		int arr_cpy[] = new int [len];
		
		for (int i=0;i<len;++i)
		{
			arr_cpy[i]=arr[i];
		}
		
		String str1;
		int tmp, num1_seq, num2_seq, num1, num2;
		int original_seq[] = new int [len];
		
		for (int i=0;i<len;++i)
		{
			original_seq[i]=i;
		}
		
		for(int i=0; i < len - 1 ;++i)
		{
			for(int j=i+1 ; j<len ; ++j)
			{
				num1 = arr_cpy[i];
				num2 = arr_cpy[j];
				
				num1_seq = original_seq[i];
				num2_seq = original_seq[j];
				
				if(num1 < num2)
				{
					tmp = num1;
					num1 = num2;
					num2 = tmp;
					
					tmp = num1_seq;
					num1_seq = num2_seq;
					num2_seq = tmp;
				}
				
				arr_cpy[i]=num1;
				arr_cpy[j]=num2;
				
				original_seq[i]=num1_seq;
				original_seq[j]=num2_seq;
			}
		}
		return original_seq;
	}
	
	public int[] BinarySorting (int [] arr, int len)
	{
		int num1,num2,tmp;
		
		for(int i=0; i < len - 1 ;++i)
		{
			
			for(int j=i+1 ; j<len ; ++j)
			{
				num1 = arr[i];
				num2 = arr[j];
				
				if(num1 < num2)
				{
					tmp=num1;
					num1=num2;
					num2=tmp;
				}
				
				arr[i]=num1;
				arr[j]=num2;
				
			}
		}
		return arr;
	}
	
	
	
	public void Display_CipherText(String input)
	{
		char ch1;
		
		Cipher_Text1.setText("");
		
		for (int i=0; i<input.length(); ++i)
		{
			ch1 = input.charAt(i);
			Cipher_Text1.setText(Cipher_Text1.getText() + String.valueOf(ch1));
			
			if ( (i%50==0) && (i>1) )
			{
				Cipher_Text1.setText(Cipher_Text1.getText() + "\n");
			}
		}
	}
	
	
}
