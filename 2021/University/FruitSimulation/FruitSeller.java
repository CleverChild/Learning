
//  FruitSeller �����ڸ� �̿��ؼ�  �Ķ���ͷ� ���޹��� ���� �̿��ؼ� �ʵ庯���� �ʱ�ȭ��Ŵ.
//  �Ǹűݾ׿� ����  ������������� �ξ��� ������ ���   ������������� ������ ������ �� ��� ����ó�� ������ ���ԵǾ���. 
// 
package myPackage;


class NegativeException2 extends Exception   //FruitSeller.java��  ���� Ŭ���� ���� 
{
	public NegativeException2()   //���� ��������� ������ ���ð�� �� ���ܷ� ó���ؾ��ϹǷ�   NegativeException2�� �̸��� �ٲ���.  
	     //Ŭ������ �̸��� NegativeException �״�� ����  FruitTest.java����  ����ó�� Ŭ������ �Ѿ��
	     // ������� ������ �����ΰ���, user�� ������� ���� �Է��� ������ �Ѱ��� ������ �߾Ȱ�. 
	{
		// super Ű�����  �θ� Ŭ����(Exception) �� ��������� �����ؼ�  ���ڿ���  �����. 
		super("���� ��� ������  ������ ���ü� �����ϴ�.  ��� ���� ������ ��� �ǸŰ����� �˸°� �����ؼ�  \n  ���� ������� ������ ������ �ʰ� ���ּ��� ");   
	}
}


public class FruitSeller 
{
	int numOfApple; //�Ǹ����� ��� ���� ����, �ʵ庯��
	int myMoney; //�Ǹ� ����,  �ʵ庯��
	final int APPLE_PRICE; //����� �ܰ�,  �ʵ庯��
	

	// �����ڸ� �̿��ؼ�  �Ǹ��ڰ� ����� �� �� ������ �ִ���, ����ܰ��� ������ , ���� ������ ������ ������ ���޹޾Ƽ�  ��ü(�ν��Ͻ�)�� �ϳ� ����. 
	public FruitSeller(int money, int appleNum, int price)  //�����ڷ� ���� �ʱ�ȭ.  �ʵ庯����� �Ű����� �������� �ٸ��� ������ thisŰ���� ������.  
	{
		myMoney = money;  
		numOfApple = appleNum;
		APPLE_PRICE = price;
	}

	public void saleApple(int money)  //�ʵ庯���� ���常 �� ���̱� ������ ��ȯ���� void
	{
		try 
		{
			int num = money / APPLE_PRICE;    // ����� �� �� �Ǹ��� ������
			numOfApple -= num;  // �Ǹŵ� ��� ����(num) ��ŭ  ���� �����ϰ� �ִ� ���(numOfApple) ����  ���̳ʽ� �ϰ�.
			
			if(numOfApple<0)  //���� ��� ������ ������ ���  ���ܰ� �߻��� ��. 
			{
				NegativeException2 ext = new NegativeException2();
				throw ext;
			}
			myMoney += money; // �Ǹűݾ� ��������. 
			//return num;
		}
		catch(NegativeException2 ne)  // throw�� ��������  ���ܸ� ���⼭  �����. 
		{
			System.out.print(ne);
		}
	}
	
	//�Ǹ����� ������¸� ����ϴ� �޼ҵ�
	public void showSaleResult()  // �ʵ庯���� ����� ���� ���.
	{
		System.out.println("[�Ǹ���] ���� ��� ���� : " + numOfApple);
		System.out.println("[�Ǹ���] �Ǹż��� : " + myMoney);
	}
}
