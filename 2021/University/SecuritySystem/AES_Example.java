package myPackage;

import java.awt.EventQueue;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;


import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.awt.event.ActionEvent;

public class tmptmp {

	private JFrame frmAes;
	private JTextField plainTextField;
	private JTextField decryptTextField;
	
	SecretKey secKey;
	byte[] cipherText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tmptmp window = new tmptmp();
					window.frmAes.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public tmptmp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frmAes = new JFrame();
		frmAes.setTitle("AES");
		frmAes.setBounds(100, 100, 450, 406);
		frmAes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAes.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("plain Text : ");
		lblNewLabel.setBounds(12, 10, 69, 31);
		frmAes.getContentPane().add(lblNewLabel);
		
		plainTextField = new JTextField();
		plainTextField.setBounds(12, 37, 139, 31);
		frmAes.getContentPane().add(plainTextField);
		plainTextField.setColumns(10);
		
		JLabel lblGeneratedAesEncryption = new JLabel("Generated AES encryption Key : ");
		lblGeneratedAesEncryption.setBounds(12, 86, 200, 31);
		frmAes.getContentPane().add(lblGeneratedAesEncryption);
		
		JLabel aesKey = new JLabel("...");
		aesKey.setBounds(12, 127, 182, 31);
		frmAes.getContentPane().add(aesKey);
		
		JLabel lblNewLabel_1 = new JLabel("cipher Text (Hex) : ");
		lblNewLabel_1.setBounds(12, 173, 182, 41);
		frmAes.getContentPane().add(lblNewLabel_1);
		
		JLabel cipherTextField = new JLabel("...");
		cipherTextField.setBounds(12, 212, 200, 57);
		frmAes.getContentPane().add(cipherTextField);
		
		JLabel lblDecryptedText = new JLabel("Decrypted Text : ");
		lblDecryptedText.setBounds(12, 279, 200, 31);
		frmAes.getContentPane().add(lblDecryptedText);
		
		decryptTextField = new JTextField();
		decryptTextField.setBounds(12, 320, 200, 38);
		frmAes.getContentPane().add(decryptTextField);
		decryptTextField.setColumns(10);
		
		JButton btnNewButton = new JButton("Encrypt");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String plainText = plainTextField.getText();
				
				decryptTextField.setText("");
				
				try
				{
					secKey = getSecretEncryptionKey();
					
					aesKey.setText(bytesToHex(secKey.getEncoded()));
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
				}
				
				try
				{
					cipherText = encryptText(plainText,secKey);
					
				}
				
				catch(Exception e1)
				{
					e1.printStackTrace();
				}
				
				String encrypted_text = bytesToHex(cipherText);
				String encrypted_text_display = "";
				cipherTextField.setText("<html>");;
				
				for(int i=0;i<encrypted_text.length();++i)
				{
					cipherTextField.setText(cipherTextField.getText() + String.valueOf(encrypted_text.charAt(i)));
					if(i%70==69)
					{
						cipherTextField.setText(cipherTextField.getText() + "<br>");
					}
				}
			}
			
		});
		btnNewButton.setBounds(224, 41, 97, 23);
		frmAes.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Decrypt");
		btnNewButton_1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				try
				{
					String decrypted_str = decryptText(cipherText, secKey);
					decryptTextField.setText(decrypted_str);
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(224, 90, 97, 23);
		frmAes.getContentPane().add(btnNewButton_1);
	}
	
	public static SecretKey getSecretEncryptionKey() throws Exception
	{
		KeyGenerator generator = KeyGenerator.getInstance("AES");
		generator.init(128);
		SecretKey secKey = generator.generateKey();
		return secKey;
	}
	
	public static byte[] encryptText (String plainText, SecretKey secKey) throws Exception
	{
		Cipher aesCipher = Cipher.getInstance("AES");
		aesCipher.init(Cipher.ENCRYPT_MODE,secKey);
		byte[] byteCipherText = aesCipher.doFinal(plainText.getBytes());
		return byteCipherText;
	}
	
	public static String decryptText (byte[] byteCipherText, SecretKey secKey) throws Exception
	{
		Cipher aesCipher = Cipher.getInstance("AES");
		aesCipher.init(Cipher.DECRYPT_MODE,secKey);
		byte[] bytePlainText = aesCipher.doFinal(byteCipherText);
		return new String(bytePlainText);
	}
	
	private static String bytesToHex(byte[] hash)
	{
		String out = new BigInteger(1, hash).toString(16);
			return out;
	}
	
}
