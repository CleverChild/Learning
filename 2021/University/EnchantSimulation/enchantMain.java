
// EnchantŬ������ �̿��ؼ�  ��ȭ�ùķ��̼� �ϴ� main�� ���α׷�. 
// �Ź��� ��ȭ�ϱ� ���� enc1 ��ü
// ������ ��ȭ�ϱ� ���� enc2 ��ü
// ����  enc1 �Ǵ� enc2�� null�� ������ ���  NullPointerException �� ȣ���. 

package myPackage;

//���α׷� ���� :  �����Ű��  ������ ��ȭ ����� ��������  ����.  ���� enc1 , enc2���� �ϳ��� null�� �����ϰ� �ִ� ���¿���
// tryEnchant �޼ҵ带 ȣ���Ϸ��� NullPointerException  �߻�.    

public class enchant_test
{
	public static void main(String[] args)
	{
		int num1=80, num2=45;
		
		try
		{
			//enc1 ����ó�� �Ǵ� ����.
			Enchant enc1 = new Enchant("shoes",num1);   // �Ź� ��ȭ�� ����, shoes�� ���� ���ݷ�80 ���� �ϳ��� ��ü(�ν��Ͻ�) ����.
			
			//enc1 ����ó�� �׽�Ʈ�� ����,  enc1 ����ó�� �׽�Ʈ�Ҷ�  enc1�� ����ó�� �Ǵ� ������  �ּ�ó�� �ؾ���. 
			//Enchant enc1=null;
			
			
			//enc2 ����ó�� �Ǵ� ����.
			Enchant enc2 = new Enchant("shoulder",num2);// ���� ��ȭ�� ����, shoulder�� ���� ���ݷ�45 ���� �ϳ��� ��ü(�ν��Ͻ�) ����.
			
			//enc2 ����ó�� �׽�Ʈ�� ����,  enc2 ����ó�� �׽�Ʈ�Ҷ�  enc2�� ����ó�� �Ǵ� ������  �ּ�ó�� �ؾ���. 
			//Enchant enc2=null;
		
			
			
			// �����Ҷ����� �����ϰ� ����� ����.  ������ ���ݷ� +1, ���н� ���ݷ� -1 ,  ����ı���  -999999.  
			System.out.println("------�Ź� ��ȭ Try -------");
			System.out.println("��ȭ �õ��� attack_power : "+ num1 + ", ��ȭ �õ��� attack_power : "+enc1.TryEnchant());
		
			System.out.println("\n------���� ��ȭ Try -------");
			System.out.println("��ȭ �õ��� attack_power : "+ num2 + ", ��ȭ �õ��� ���� attack_power : "+enc2.TryEnchant());
			
		}
		
		catch(NullPointerException e)  //enc1 �Ǵ� enc2��  ��ü�� �������� �ʰ�  null�� ����� ���¿���  �޼ҵ� ȣ��� ���ܹ߻��ϰ� ��. 
		{
			System.out.println("�������� Exception �߻�!! \n" + e);
		}
		
	}
}
