package myPackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Vigenere_cipher {

	private JFrame frmVigenerecipher;
	private JTextField plainTextField;
	private JTextField keyField;
	private JTextField cipherTextField;
	private JTextField decryptTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vigenere_cipher window = new Vigenere_cipher();
					window.frmVigenerecipher.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Vigenere_cipher() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVigenerecipher = new JFrame();
		frmVigenerecipher.setTitle("Vigenere_cipher");
		frmVigenerecipher.setBounds(100, 100, 545, 528);
		frmVigenerecipher.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVigenerecipher.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("plainText : ");
		lblNewLabel.setBounds(12, 10, 82, 36);
		frmVigenerecipher.getContentPane().add(lblNewLabel);
		
		plainTextField = new JTextField();
		plainTextField.setText("ABCDABCD");
		plainTextField.setBounds(106, 18, 116, 21);
		frmVigenerecipher.getContentPane().add(plainTextField);
		plainTextField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("keyWord : ");
		lblNewLabel_1.setBounds(12, 56, 68, 15);
		frmVigenerecipher.getContentPane().add(lblNewLabel_1);
		
		keyField = new JTextField();
		keyField.setText("CRYPTO");
		keyField.setBounds(106, 53, 116, 21);
		frmVigenerecipher.getContentPane().add(keyField);
		keyField.setColumns(10);
		
		JButton btnNewButton = new JButton("Encrypt");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String plainText = plainTextField.getText();
				String key = keyField.getText();
				String cipherText = "";
				
				for(int i=0;i<plainText.length();++i)
				{
					char ch1 = plainText.charAt(i);
					int n1 = (int) ch1 - 65;
					
					char ch2 = key.charAt(i % key.length());
					int n2 = (int) ch2 - 65;
					
					int cipher_int = (n1 + n2) % 26;
					
					cipherText = cipherText + String.valueOf(  (char) (cipher_int + 65)  );
					cipherTextField.setText(cipherText);
				}
			}
		});
		btnNewButton.setBounds(12, 96, 97, 23);
		frmVigenerecipher.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("cipher Text : ");
		lblNewLabel_2.setBounds(12, 147, 87, 21);
		frmVigenerecipher.getContentPane().add(lblNewLabel_2);
		
		cipherTextField = new JTextField();
		cipherTextField.setBounds(122, 147, 116, 21);
		frmVigenerecipher.getContentPane().add(cipherTextField);
		cipherTextField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Decrypt");
		btnNewButton_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String cipherText = cipherTextField.getText();
				String key = keyField.getText();
				String plainText = "";
				int plain_int;
				
				for(int i=0;i<cipherText.length();++i)
				{
					char ch1 = cipherText.charAt(i);
					int n1 = (int) ch1 - 65;
					
					char ch2 = key.charAt(i % key.length());
					int n2 = (int) ch2 - 65;
					
					if(n1-n2 < 0)
					{
						plain_int = (n1 - n2 + 26) % 26;
					}
					else
					{
						plain_int = (n1 - n2) % 26;
					}
					
					plainText = plainText + String.valueOf( (char) (plain_int + 65) );
				}
				
				decryptTextField.setText(plainText);
			}
		});
		btnNewButton_1.setBounds(12, 197, 97, 23);
		frmVigenerecipher.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_3 = new JLabel("Decrypted Text : ");
		lblNewLabel_3.setBounds(12, 242, 116, 21);
		frmVigenerecipher.getContentPane().add(lblNewLabel_3);
		
		decryptTextField = new JTextField();
		decryptTextField.setBounds(122, 242, 116, 21);
		frmVigenerecipher.getContentPane().add(decryptTextField);
		decryptTextField.setColumns(10);
	}
}
