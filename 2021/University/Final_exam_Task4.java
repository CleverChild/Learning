package myPackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class Final_exam_Task4 {

	private JFrame frmFinalexamtask;
	private JTextArea Log;
	
	int arr [] = new int [100];
	int arr_cnt [] = new int [100];
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Final_exam_Task4 window = new Final_exam_Task4();
					window.frmFinalexamtask.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Final_exam_Task4() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFinalexamtask = new JFrame();
		frmFinalexamtask.setTitle("Final_exam_task4");
		frmFinalexamtask.setBounds(100, 100, 655, 895);
		frmFinalexamtask.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFinalexamtask.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("first 20 e for RSA with  p=61, q=71");
		lblNewLabel.setBounds(12, 10, 259, 26);
		frmFinalexamtask.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Find E");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				int p=61, q=71;
				int myE=11;
				int myD=0;
				int euler=0;
				int my_E_Cnt=0;
				int cipherText=0;
				int decryptedText=0;
				
				Arrays.fill(arr,0);
				Arrays.fill(arr_cnt,0);
				
				euler = getEuler(p*q);
				
				//Log.setText("<html> Log : <br>" );
				
				while(my_E_Cnt<20)
				{		
					if(gcd(myE,euler)==1)
					{
						//encrypt
						boolean x_binary1[] = convertIntToBinary(myE);
						int A=100;
						int result=1;
						
						for(int i=0;i<x_binary1.length;++i)
						{ 
							
							if(x_binary1[i]==true)
							{
								result = (result * A) % (p*q);
							}
							
							A = (A * A) % (p*q);
						}
						
						cipherText=result;
						
						// encrypt finished . 
						
						
						
						myD = ModuloInverse(myE,euler);
						
						
						// decrypt  start
						boolean x_binary2[] = convertIntToBinary(myD);
						
						A=cipherText;
						result=1;
						
						for(int i=0;i<x_binary2.length;++i)
						{ 
							
							if(x_binary2[i]==true)
							{
								result = (result * A) % (p*q);
							}
							
							A = (A * A) % (p*q);
						}
						
						decryptedText=result;
						
						// decrypted  finsihed. 
						
						Log.setText(Log.getText() + "# : "+(my_E_Cnt+1)+", e="+myE + ", d="+ myD +", C=100^"+ myE +" mod (4331) = " +cipherText + ", m="+cipherText +"^" + myD + " mod(4331)" +"=" +decryptedText + "\n");
						
						my_E_Cnt++;
					} //if(gcd(myE,euler)==1)
					
					
					
					myE++;
				} //while(myCnt<20)
				
			}
		});
		btnNewButton.setBounds(238, 12, 97, 23);
		frmFinalexamtask.getContentPane().add(btnNewButton);
		
		Log = new JTextArea();
		Log.setBounds(12, 54, 506, 745);
		frmFinalexamtask.getContentPane().add(Log);
	}
	
	public int ModuloInverse(int a, int m)
	{	    		
	    for(int i=0; i<m; ++i)
	    {
	    	if (((a%m) * (i%m)) % m == 1)
	            return i;
	    }
		return -1;
	}
	
	public int getEuler(int num)
	{
		int euler=1;
		int idx=0;
		int tmp;
		
		FactorCalculator(num);
		
		while(arr[idx]!=0)
		{
			//apply Euler   
			tmp = (int) (Math.pow(arr[idx], arr_cnt[idx]) - Math.pow(arr[idx], arr_cnt[idx]-1) );
			euler*=tmp;
			idx++;
		}
		
		return euler;
	}
	
	public void FactorCalculator (int num)
	{
		int idx=-1;
		
		for (int i = 2; i <= num; ++i) 
		{
	        if (num % i == 0) 
	        {
	            idx++;
	            
	            while (num % i == 0) 
	            {
	                arr[idx] = i;
	                arr_cnt[idx]++;
	                num /= i;
	            }
	        }        
	    }
	}
	
	public int gcd(int a, int b)
	{
	    int tmp, n;

	    if(a<b)
	    {
	        tmp = a;
	        a = b;
	        b = tmp;
	    }
	    
	    while(b!=0)
	    {
	        n = a%b;
	        a = b;
	        b = n;
	    }
	    
	    return a;
	}
	
	public boolean[] convertIntToBinary(int X)
	{
		int binary_length = (int) (Math.log(X) / Math.log(2)) + 1; 
		boolean X_binary [] = new boolean [binary_length];
		
		for(int i=0;i<binary_length;++i)
		{
			if(X % 2 == 0)   // x mod 2 = 0, 1
			{
				X_binary[i]=false;
			}
			else
			{
				X_binary[i]=true;
			}
			
			X = X / 2;
		}
		return X_binary;
	}
}
