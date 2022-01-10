
// ���α׷� ���� :  FruitSeller Ŭ������ �̿��ؼ�  ��������� �ùķ��̼� �ϴ� ���α׷�. 
// ó���� seller1, seller2 �� ��� ���������� �Է��ϴµ� , �����Ӱ� �� 60�� �̻��� �����.   num1, num2�Ѵ�  �� 60�̻�.
// ������  saleApple�޼ҵ� ȣ���Ҷ�  �Ǹűݾ��� �Ķ���ͷ� �����ϰ� �Ǿ��ִµ� 12000��, 17000�� ��ŭ ����� �ȾҴٰ� �ùķ��̼� �߱� ������.
// ����,  �Ǹűݾ��� �� �۴ٸ� num1, num2���� �� �ٿ�����. 
// ��·��  ����ڰ� ó����  ������������� ������ �Է��ϴ� ����,  ����Ǹűݾ׿�  �˸��� �ʰ�  ��� ���������� �Է��ؼ�  ������� ������ ���ð�� ����ó����. 
package myPackage;
import java.util.Scanner;

class NegativeException extends Exception   //FruitTest.java���� Ŭ���� ���� 
{
	public NegativeException() 
	{
		// super Ű�����  �θ� Ŭ����(Exception) �� ��������� �����ؼ�  ���ڿ���  �����. 
		super("��� ���������� ������ �Էµ� �� �����ϴ�. �����  �Է����ּ��� ");  
	}
}

public class FruitTest 
{

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int num1=0;
		int num2=0;
		
		try 
		{
			System.out.println("�Ǹ���1�� ����������� �Է� : ");
			num1 = sc.nextInt();
			
			System.out.println("�Ǹ���2�� ����������� �Է� : ");
			num2 = sc.nextInt();
			
			if(num1<0 || num2<0)  // num1 �Ǵ�  num2�� ������ �ԷµǸ� ���ܰ� �߻��� ��.
			{
				NegativeException ext = new NegativeException();  //���� Ŭ���� ����
				throw ext;  // ���� ���� �߻�
			}
			
			//�Ǹ��� 1 :  �Ǹż��� �ʹݿ� 0��,  ��� �������� num1��,   �ܰ� 1000��,  seller1 ��ü ����
			FruitSeller seller1 = new FruitSeller(0, num1, 1000);
			
			//�Ǹ��� 2 :  �Ǹż��� �ʹݿ� 0��,  ��� �������� num2��,   �ܰ� 500��,  seller2 ��ü ����. 
			FruitSeller seller2 = new FruitSeller(0, num2, 500);
			
			System.out.println("�Ǹ������� �Ͼ�� ���� ����");
			seller1.showSaleResult();
			seller2.showSaleResult();
			
			seller1.saleApple(2000); // seller1��  2000�� ��ŭ ����� �Ǹ�����. 
			seller1.saleApple(10000);  // seller1��  10000�� ��ŭ ����� �Ǹ�����.
			
			seller2.saleApple(4000); // seller2��  4000�� ��ŭ ����� �Ǹ�����. 
			seller2.saleApple(13000);  // seller2��  13000�� ��ŭ ����� �Ǹ�����. 
			
			
			System.out.println("\n�Ǹ������� �Ͼ �� ����");
			seller1.showSaleResult();
			seller2.showSaleResult();
		}
		
		catch(NegativeException ne)   //throw ext;  << �� ���忡�� ���� ���ܸ� ���⼭ catch������ �޾������.  
		{
			System.out.println(ne);  //���� msg��� . 
		}
		
	}
}
