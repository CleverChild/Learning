package myPackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class caesar_cipher_v2 {

	private JFrame frmCeasarcipherv;
	private JTextField PlainText1;
	private JTextField CipherText1;
	private JTextField DecryptText1;
	
	private JLabel Log1;
	private JTextField Key1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					caesar_cipher_v2 window = new caesar_cipher_v2();
					window.frmCeasarcipherv.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public caesar_cipher_v2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCeasarcipherv = new JFrame();
		frmCeasarcipherv.setTitle("ceasar_cipher_v2");
		frmCeasarcipherv.setBounds(100, 100, 809, 557);
		frmCeasarcipherv.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCeasarcipherv.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("plainText : ");
		lblNewLabel.setFont(new Font("±¼¸²", Font.PLAIN, 17));
		lblNewLabel.setBounds(25, 20, 84, 20);
		frmCeasarcipherv.getContentPane().add(lblNewLabel);
		
		PlainText1 = new JTextField();
		PlainText1.setText("thenextyear");
		PlainText1.setBounds(25, 50, 242, 32);
		frmCeasarcipherv.getContentPane().add(PlainText1);
		PlainText1.setColumns(10);
		
		JLabel lblCiphertext = new JLabel("cipherText : ");
		lblCiphertext.setFont(new Font("±¼¸²", Font.PLAIN, 17));
		lblCiphertext.setBounds(290, 20, 165, 20);
		frmCeasarcipherv.getContentPane().add(lblCiphertext);
		
		CipherText1 = new JTextField();
		CipherText1.setColumns(10);
		CipherText1.setBounds(290, 50, 242, 32);
		frmCeasarcipherv.getContentPane().add(CipherText1);
		
		JLabel lblDecryptedtext = new JLabel("decryptedText : ");
		lblDecryptedtext.setFont(new Font("±¼¸²", Font.BOLD, 18));
		lblDecryptedtext.setBounds(544, 10, 180, 32);
		frmCeasarcipherv.getContentPane().add(lblDecryptedtext);
		
		DecryptText1 = new JTextField();
		DecryptText1.setColumns(10);
		DecryptText1.setBounds(544, 52, 242, 32);
		frmCeasarcipherv.getContentPane().add(DecryptText1);
		
		
		
		JButton btnNewButton = new JButton("Encrypt");
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				DecryptText1.setText("");
				String save_cipher_string = "";
				String plainText1 = PlainText1.getText().toString();
				int key1 = Integer.parseInt(Key1.getText());
				
				char plain_char;
				int plain_int;
				
				int cipher_int;
				int cipher_int_mod;
				
				Log1.setText("<html>");
				
				for(int i=0; i < plainText1.length() ; i++) 
				{
					plain_char = plainText1.charAt(i);
					plain_int = (int) plain_char - 97;
					cipher_int = plain_int+key1;
					cipher_int_mod = cipher_int % 26;
					 
					Log1.setText(Log1.getText()+String.valueOf(i)+").........." + plain_char + "........." + plain_int + "......." + (plain_int+key1) + "............"+ cipher_int_mod  +"............" + (char) (cipher_int_mod + 97) + "<br>");
					save_cipher_string = save_cipher_string + (char) (cipher_int_mod+97); 
				}
				CipherText1.setText(save_cipher_string);
								
			}
		});
		btnNewButton.setFont(new Font("±¼¸²", Font.PLAIN, 15));
		btnNewButton.setBounds(25, 92, 91, 23);
		frmCeasarcipherv.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Decrypt");
		btnNewButton_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String cipherText1 = CipherText1.getText();
				int key1 = Integer.parseInt(Key1.getText());
				char cipher_char;
				int cipher_int;
				
				int plain_int;
				int plain_int_mod;
				
				Log1.setText("<html>");
				Log1.setText(Log1.getText()+ "Decryption : <br>");
				
				for (int i=0;i<cipherText1.length();i++)
				{
					cipher_char = cipherText1.charAt(i);
					cipher_int = (int) (cipher_char-97);
					
					plain_int = cipher_int-key1;
					
					if(plain_int < 0)
					{
						plain_int = plain_int+26;
					}
					
					plain_int_mod = plain_int % 26;
					DecryptText1.setText(DecryptText1.getText()+ (char) (plain_int_mod+97));
					
					Log1.setText(Log1.getText()+i +")" + "........." + cipher_char +"..........." +(int) (cipher_char-97)  + ".........." + (plain_int) + "........." + plain_int_mod + "........" + (char) (plain_int_mod+97) +"<br>");
				}
				
				
			}
		});
		btnNewButton_1.setFont(new Font("Gulim", Font.PLAIN, 15));
		btnNewButton_1.setBounds(290, 92, 91, 23);
		frmCeasarcipherv.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("message");
		lblNewLabel_1.setFont(new Font("Gulim", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(58, 125, 72, 20);
		frmCeasarcipherv.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("P");
		lblNewLabel_1_1.setFont(new Font("Gulim", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(152, 125, 72, 20);
		frmCeasarcipherv.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("P+K");
		lblNewLabel_1_2.setFont(new Font("Gulim", Font.PLAIN, 12));
		lblNewLabel_1_2.setBounds(205, 125, 72, 20);
		frmCeasarcipherv.getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("(P+K) mod 26");
		lblNewLabel_1_1_1.setFont(new Font("Gulim", Font.PLAIN, 12));
		lblNewLabel_1_1_1.setBounds(280, 125, 99, 20);
		frmCeasarcipherv.getContentPane().add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_3 = new JLabel("cipherText");
		lblNewLabel_1_3.setFont(new Font("Gulim", Font.PLAIN, 12));
		lblNewLabel_1_3.setBounds(415, 125, 99, 20);
		frmCeasarcipherv.getContentPane().add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_2 = new JLabel("Key : ");
		lblNewLabel_2.setFont(new Font("Gulim", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(554, 100, 50, 15);
		frmCeasarcipherv.getContentPane().add(lblNewLabel_2);
		
		Log1 = new JLabel("");
		Log1.setVerticalAlignment(SwingConstants.TOP);
		Log1.setBounds(23, 151, 544, 339);
		frmCeasarcipherv.getContentPane().add(Log1);
		
		Key1 = new JTextField();
		Key1.setText("3");
		Key1.setBounds(597, 94, 96, 21);
		frmCeasarcipherv.getContentPane().add(Key1);
		Key1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("i");
		lblNewLabel_3.setBounds(15, 128, 50, 15);
		frmCeasarcipherv.getContentPane().add(lblNewLabel_3);
		
		
	}
}
