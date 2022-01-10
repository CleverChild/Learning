
// Enchant클래스를 이용해서  강화시뮬레이션 하는 main쪽 프로그램. 
// 신발을 강화하기 위한 enc1 객체
// 견장을 강화하기 위한 enc2 객체
// 만약  enc1 또는 enc2가 null을 참조할 경우  NullPointerException 이 호출됨. 

package myPackage;

//프로그램 설명 :  실행시키면  아이템 강화 결과가 랜덤으로  나옴.  만약 enc1 , enc2둘중 하나라도 null을 참조하고 있는 상태에서
// tryEnchant 메소드를 호출하려고 NullPointerException  발생.    

public class enchant_test
{
	public static void main(String[] args)
	{
		int num1=80, num2=45;
		
		try
		{
			//enc1 정상처리 되는 문장.
			Enchant enc1 = new Enchant("shoes",num1);   // 신발 강화를 위해, shoes와 현재 공격력80 으로 하나의 객체(인스턴스) 생성.
			
			//enc1 예외처리 테스트용 문장,  enc1 예외처리 테스트할땐  enc1의 정상처리 되는 문장을  주석처리 해야함. 
			//Enchant enc1=null;
			
			
			//enc2 정상처리 되는 문장.
			Enchant enc2 = new Enchant("shoulder",num2);// 견장 강화를 위해, shoulder와 현재 공격력45 으로 하나의 객체(인스턴스) 생성.
			
			//enc2 예외처리 테스트용 문장,  enc2 예외처리 테스트할땐  enc2의 정상처리 되는 문장을  주석처리 해야함. 
			//Enchant enc2=null;
		
			
			
			// 실행할때마다 랜덤하게 결과가 나옴.  성공시 공격력 +1, 실패시 공격력 -1 ,  장비파괴시  -999999.  
			System.out.println("------신발 강화 Try -------");
			System.out.println("강화 시도전 attack_power : "+ num1 + ", 강화 시도후 attack_power : "+enc1.TryEnchant());
		
			System.out.println("\n------견장 강화 Try -------");
			System.out.println("강화 시도전 attack_power : "+ num2 + ", 강화 시도후 현재 attack_power : "+enc2.TryEnchant());
			
		}
		
		catch(NullPointerException e)  //enc1 또는 enc2에  객체를 생성하지 않고  null이 저장된 상태에서  메소드 호출시 예외발생하게 됨. 
		{
			System.out.println("널포인터 Exception 발생!! \n" + e);
		}
		
	}
}
