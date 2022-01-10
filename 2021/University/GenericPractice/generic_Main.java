

//  ���α׷� ����ó���κ� ����  :  ���� ������ �Է��Ҷ�  "4r" �̷�������  ������ ���ڰ� �ƴ� ���ڿ��� ���� �Ǹ�  ���ܰ� �߻��ϰԵ�.

//  ���׸� Ŭ������ Ȱ���غ��� ����  �Ｚ���� �����Ѵٰ� �����ϰ�  ��ǰ�̸���  ��ǰ������ �ùķ��̼� �غ��� ���α׷�. 
//  ������ ��ǰ�̸��� �ڷ����� String �̹Ƿ� String���� ����.
//  ������ int���̹Ƿ�  int�� �����ؼ�   box1��ü�� ����. 
//  box2��ü����  �����ڷ����� Double�� �ߴµ�,  �ٸ� �ڷ�������  ���׸� ��ü�� �����غ��� �����̸�, �ٸ� �ǹ̴� ����. 
package myPackage;

import java.util.Scanner;

public class myGeneric
{
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		try {
		// ���׸� Ŭ���� Box��  String�� Integer�ڷ������� �����Ͽ� box1 �ν��Ͻ��� ��������. 
		Box <String,Integer> box1 = new Box <String,Integer>(); 
		
		// ���׸� Ŭ���� Box��  String�� Double�ڷ������� �����Ͽ� box2 �ν��Ͻ��� ��������.
		Box <String,Double> box2 = new Box <String,Double>();
		
		//��ǰ �̸� ����, box1�� ù ��° �ڷ����� String�̹Ƿ�  �Ķ���ͷ� �����ϴ°� String�� �̾����.  
		box1.setItem("Samsung Galaxy S20");   
		
		System.out.println("������ S20�� ���� ���� : ");
		String str1 = sc.next();
		
		//��ǰ ���� ����, box1�� �� ��° �ڷ����� Integer�̹Ƿ�  �Ķ���ͷ� �����ϴ°� Integer�� �̾����.
		box1.setPrice(Integer.parseInt(str1));
		
		//box1 �������. 
		System.out.println("������ ��ǰ " + box1.getItem() + " ,  ���� : " + box1.getPrice() +"��");
		
		
		// -------------------- box2 ���� ---------------------------------
		
		//��ǰ �̸� ����, box2�� ù ��° �ڷ����� String�̹Ƿ�  �Ķ���ͷ� �����ϴ°� String�� �̾����. 
		box2.setItem("\nSamsung Galaxy Note 8");
		
		System.out.println("������ ��Ʈ 8�� ���� ���� : ");
		String str2 = sc.next();
		
		//��ǰ ���� ����, box2�� �� ��° �ڷ����� Double�̹Ƿ�  �Ķ���ͷ� �����ϴ°� Double�� �̾����.
		box2.setPrice(Double.parseDouble(str2));
		
		//getItem�޼ҵ�� getPrice�޼ҵ带 ȣ���ؼ�  ������ ��ǰ�̸���  ������ ���Ϲ޾Ƽ�  ���. 
		System.out.println("������ ��ǰ " + box2.getItem() + " ,  ���� : " + box2.getPrice() +"��");
		
		}
		
		catch(NumberFormatException e)     // ���ڷ� ��ȯ�� �� ���� ���ڿ��� ���ԵǾ� ������   NumberFormat ���ܰ� �߻��� ��.
		{
			System.out.println("Ÿ�Կ� ���� �ʴ� ���� �Է��߽��ϴ�\n" + e);
		}
	}
		
}
