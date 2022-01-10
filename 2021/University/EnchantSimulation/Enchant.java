// 201621228  �ӹμ�
// ����  �����ڷ� �������̸���  ���� ���ݷ���  ���޹޾Ƽ�  �ʵ庯���� �ʱ�ȭ��Ŵ. 
// main �ʿ��� TryEnchant �޼ҵ� ȣ���� �ϸ�  Ȯ���� ���� ��ȭ����, ����, �ı��� ������. 
// ����Ȯ���� �� 20% , ����Ȯ�� �� 55%, �ı�Ȯ�� 25%����.    
// �����������  SuccessEnchant �޼ҵ� ȣ��
// ����������� FailEnchant �޼ҵ�ȣ��. 
// �ı��Ǿ��� ����  �޼ҵ带 ���� ȣ������ �ʰ�  TryEnchant �޼ҵ� ������ ���ݷ��� -999999�� ������ ����. 

package myPackage;

import java.util.Random;

public class Enchant 
{
	final Random myRandom = new Random();  //��ȭ Ȯ�� ������ ���� ���� ��ü����.
	
	private String item;  //�ʵ庯��
	private int attack_power;
	private int probability;
	
	Enchant(String item,int attack_power)  // Enchant �ν��Ͻ��� �����Ҷ� �������̸���  ������ ���� ����(���ݷ�)�� ���� ���޹���. 
	{
		this.item=item;  // �ʵ庯�� item���ٰ�  ���޹��� �Ķ���� item ������ ����.
		this.attack_power = attack_power;  //�ʵ庯�� attack_power ���ٰ�  ���޹���  ���� ������ ���ݷ� ��������.  
	}
	
	public int TryEnchant()   // main �ʿ��� ȣ�� �ؾ��ϱ� ������ public���� ����. 
	{                         //  ��ȭ�õ���  ���ݷ��� �����ߴ��� ����������, �ƿ� �ı�(-999999) �Ǿ����� ���θ� 
		                      // main ������ �˷��ֱ� ���� int ������ ���ϼ���. 
		
		probability = myRandom.nextInt(10);  //0~9���� Ȯ�� ����.
		
		if(probability>7)   //��ȭ���� 
		{
			SuccessEnchant(item);  // ��ȭ���� �޼ҵ� ȣ��  , ���ݷ� ���� 
			return attack_power;  // ������ ���ݷ� ��ȯ 
		}
		else if(probability>3 && probability<=7) 
		{
			FailEnchant(item);  // ��ȭ���� �޼ҵ� ȣ��,  ���ݷ� ���� 
			return attack_power;  // ���ҵ� ���ݷ� ��ȯ . 
		}
		else
		{
			return -999999;  //����ı� , ���ݷ��� -999999�� �Ǿ� �ƿ� ������� ���ϰԵ�. 
		}
		
	}
	
	private void FailEnchant(String item) // EnchantŬ���� �������� ȣ�� �����ϵ��� private�� ����
	{
		attack_power--;
	}
	
	private void SuccessEnchant(String item) // EnchantŬ���� �������� ȣ�� �����ϵ��� private�� ����
	{
		attack_power++;
	}
	
}
