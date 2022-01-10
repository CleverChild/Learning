package myPackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Euclidian_gcd_midterm1 {

	private JFrame frmGcd;
	private JTextField Num1;
	private JLabel lblNewLabel_1;
	private JTextField Num2;
	private JButton btnNewButton;
	private JLabel Answer1;
	private JLabel Log1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Euclidian_gcd_midterm1 window = new Euclidian_gcd_midterm1();
					window.frmGcd.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Euclidian_gcd_midterm1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGcd = new JFrame();
		frmGcd.setTitle("Midterm Task 4.");
		frmGcd.setBounds(100, 100, 797, 520);
		frmGcd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGcd.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Compute multiplicative inverse  of ");
		lblNewLabel.setBounds(12, 10, 213, 22);
		frmGcd.getContentPane().add(lblNewLabel);
		
		Num1 = new JTextField();
		Num1.setBounds(240, 11, 116, 21);
		frmGcd.getContentPane().add(Num1);
		Num1.setColumns(10);
		
		lblNewLabel_1 = new JLabel("over modulo");
		lblNewLabel_1.setBounds(368, 14, 90, 15);
		frmGcd.getContentPane().add(lblNewLabel_1);
		
		Num2 = new JTextField();
		Num2.setBounds(480, 11, 116, 21);
		frmGcd.getContentPane().add(Num2);
		Num2.setColumns(10);
		
		btnNewButton = new JButton("Compute");
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				int num1 = Integer.parseInt(Num1.getText());
				int num2 = Integer.parseInt(Num2.getText());
				int q,r1,r2,r=-1;
				int t1=0,t2=1,t=0;
				int ans1;
				
				r1=num2;
				r2=num1;
				
				Log1.setText("<html>");
				
					
				
				while(r!=0)
				{
					q=r1/r2;
					r=r1%r2;
					t=t1-(q*t2);
					
					Log1.setText(Log1.getText() + "q : "+q+", r1 : "+r1+",  r2 : "+r2+",  r :"+r  + "  ||  " + " t1 : "+t1+",  t2 : "+t2 + ", t : " +t +"<br>");
					
					t1=t2;
					t2=t;
					
					r1=r2;
					r2=r;
					

				}
				ans1=t1+t;
				Log1.setText(Log1.getText() + String.valueOf(num1) +"^-1 mod " + String.valueOf(num2) + "=" + String.valueOf(t1));
				Answer1.setText(String.valueOf(num1) + "^-1 mod "  + String.valueOf(num2) + "=" + String.valueOf(ans1));
				Answer1.setText(Answer1.getText() + ", Verification :  " + String.valueOf(num1) + " * " + String.valueOf(ans1)+" mod " +String.valueOf(num2)+" =1");
			}
		});
		btnNewButton.setBounds(632, 10, 97, 23);
		frmGcd.getContentPane().add(btnNewButton);
		
		Answer1 = new JLabel("...");
		Answer1.setBounds(12, 42, 337, 15);
		frmGcd.getContentPane().add(Answer1);
		
		Log1 = new JLabel("Log :");
		Log1.setVerticalAlignment(SwingConstants.TOP);
		Log1.setBounds(12, 67, 347, 354);
		frmGcd.getContentPane().add(Log1);
	}
}
