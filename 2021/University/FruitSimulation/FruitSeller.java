
//  FruitSeller 생성자를 이용해서  파라미터로 전달받은 값을 이용해서 필드변수를 초기화시킴.
//  판매금액에 비해  사과보유개수가 턱없이 부족한 경우   사과보유개수가 음수가 나오게 될 경우 예외처리 구문도 포함되었음. 
// 
package myPackage;


class NegativeException2 extends Exception   //FruitSeller.java의  예외 클래스 정의 
{
	public NegativeException2()   //남은 사과개수가 음수로 나올경우 이 예외로 처리해야하므로   NegativeException2로 이름을 바꿨음.  
	     //클래스명 이름을 NegativeException 그대로 쓰면  FruitTest.java쪽의  예외처리 클래스로 넘어가서
	     // 남은사과 개수가 음수인건지, user가 보유사과 개수 입력을 음수로 한건지 구별이 잘안감. 
	{
		// super 키워드로  부모 클래스(Exception) 의 멤버변수를 참조해서  문자열을  출력함. 
		super("남은 사과 개수가  음수로 나올수 없습니다.  사과 보유 개수와 사과 판매가격을 알맞게 조절해서  \n  남은 사과개수 음수가 나오지 않게 해주세요 ");   
	}
}


public class FruitSeller 
{
	int numOfApple; //판매자의 사과 보유 갯수, 필드변수
	int myMoney; //판매 수익,  필드변수
	final int APPLE_PRICE; //사과의 단가,  필드변수
	

	// 생성자를 이용해서  판매자가 사과를 몇 개 가지고 있는지, 사과단가는 얼마인지 , 현재 수익은 얼마인지 정보를 전달받아서  객체(인스턴스)를 하나 만듬. 
	public FruitSeller(int money, int appleNum, int price)  //생성자로 변수 초기화.  필드변수명과 매개변수 변수명이 다르기 때문에 this키워드 사용안함.  
	{
		myMoney = money;  
		numOfApple = appleNum;
		APPLE_PRICE = price;
	}

	public void saleApple(int money)  //필드변수에 저장만 할 것이기 때문에 반환형은 void
	{
		try 
		{
			int num = money / APPLE_PRICE;    // 사과를 몇 개 판매할 것인지
			numOfApple -= num;  // 판매된 사과 개수(num) 만큼  현재 보유하고 있는 사과(numOfApple) 에서  마이너스 하고.
			
			if(numOfApple<0)  //남은 사과 개수가 음수일 경우  예외가 발생한 것. 
			{
				NegativeException2 ext = new NegativeException2();
				throw ext;
			}
			myMoney += money; // 판매금액 정산해줌. 
			//return num;
		}
		catch(NegativeException2 ne)  // throw로 던져버린  예외를 여기서  잡아줌. 
		{
			System.out.print(ne);
		}
	}
	
	//판매자의 현재상태를 출력하는 메소드
	public void showSaleResult()  // 필드변수에 저장된 값들 출력.
	{
		System.out.println("[판매자] 남은 사과 개수 : " + numOfApple);
		System.out.println("[판매자] 판매수익 : " + myMoney);
	}
}
