// 201621228  임민섭
// 먼저  생성자로 아이템이름과  현재 공격력을  전달받아서  필드변수에 초기화시킴. 
// main 쪽에서 TryEnchant 메소드 호출을 하면  확률에 따라서 강화실패, 성공, 파괴로 나눠짐. 
// 성공확률은 약 20% , 실패확률 약 55%, 파괴확률 25%정도.    
// 성공했을경우  SuccessEnchant 메소드 호출
// 실패했을경우 FailEnchant 메소드호출. 
// 파괴되었을 경우는  메소드를 따로 호출하지 않고  TryEnchant 메소드 내에서 공격력을 -999999로 설정후 리턴. 

package myPackage;

import java.util.Random;

public class Enchant 
{
	final Random myRandom = new Random();  //강화 확률 설정을 위해 랜덤 객체생성.
	
	private String item;  //필드변수
	private int attack_power;
	private int probability;
	
	Enchant(String item,int attack_power)  // Enchant 인스턴스를 생성할때 아이템이름과  아이템 현재 성능(공격력)을 같이 전달받음. 
	{
		this.item=item;  // 필드변수 item에다가  전달받은 파라미터 item 정보를 저장.
		this.attack_power = attack_power;  //필드변수 attack_power 에다가  전달받은  현재 아이템 공격력 정보저장.  
	}
	
	public int TryEnchant()   // main 쪽에서 호출 해야하기 떄문에 public으로 선언. 
	{                         //  강화시도후  공격력이 증가했는지 낮아졌는지, 아예 파괴(-999999) 되었는지 여부를 
		                      // main 쪽으로 알려주기 위해 int 형으로 리턴설정. 
		
		probability = myRandom.nextInt(10);  //0~9사이 확률 만듬.
		
		if(probability>7)   //강화성공 
		{
			SuccessEnchant(item);  // 강화성공 메소드 호출  , 공격력 증가 
			return attack_power;  // 증가된 공격력 반환 
		}
		else if(probability>3 && probability<=7) 
		{
			FailEnchant(item);  // 강화실패 메소드 호출,  공격력 감소 
			return attack_power;  // 감소된 공격력 반환 . 
		}
		else
		{
			return -999999;  //장비파괴 , 공격력이 -999999로 되어 아예 사용하지 못하게됨. 
		}
		
	}
	
	private void FailEnchant(String item) // Enchant클래스 내에서만 호출 가능하도록 private로 선언
	{
		attack_power--;
	}
	
	private void SuccessEnchant(String item) // Enchant클래스 내에서만 호출 가능하도록 private로 선언
	{
		attack_power++;
	}
	
}
