
// 프로그램 설명 :  FruitSeller 클래스를 이용해서  과일장수를 시뮬레이션 하는 프로그램. 
// 처음에 seller1, seller2 의 사과 보유개수를 입력하는데 , 여유롭게 약 60개 이상을 줘야함.   num1, num2둘다  약 60이상.
// 이유는  saleApple메소드 호출할때  판매금액을 파라미터로 전달하게 되어있는데 12000원, 17000원 만큼 사과를 팔았다고 시뮬레이션 했기 때문에.
// 만약,  판매금액이 더 작다면 num1, num2수를 더 줄여도됨. 
// 어쨌든  사용자가 처음에  사과보유개수를 음수를 입력하는 경우와,  사과판매금액에  알맞지 않게  사과 보유개수를 입력해서  결과값이 음수가 나올경우 예외처리됨. 
package myPackage;
import java.util.Scanner;

class NegativeException extends Exception   //FruitTest.java예외 클래스 정의 
{
	public NegativeException() 
	{
		// super 키워드로  부모 클래스(Exception) 의 멤버변수를 참조해서  문자열을  출력함. 
		super("사과 보유개수는 음수로 입력될 수 없습니다. 양수로  입력해주세요 ");  
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
			System.out.println("판매자1의 사과보유개수 입력 : ");
			num1 = sc.nextInt();
			
			System.out.println("판매자2의 사과보유개수 입력 : ");
			num2 = sc.nextInt();
			
			if(num1<0 || num2<0)  // num1 또는  num2에 음수가 입력되면 예외가 발생한 것.
			{
				NegativeException ext = new NegativeException();  //예외 클래스 생성
				throw ext;  // 예외 강제 발생
			}
			
			//판매자 1 :  판매수익 초반엔 0원,  사과 보유개수 num1개,   단가 1000원,  seller1 객체 생성
			FruitSeller seller1 = new FruitSeller(0, num1, 1000);
			
			//판매자 2 :  판매수익 초반엔 0원,  사과 보유개수 num2개,   단가 500원,  seller2 객체 생성. 
			FruitSeller seller2 = new FruitSeller(0, num2, 500);
			
			System.out.println("판매행위가 일어나기 전의 상태");
			seller1.showSaleResult();
			seller2.showSaleResult();
			
			seller1.saleApple(2000); // seller1이  2000원 만큼 사과를 판매했음. 
			seller1.saleApple(10000);  // seller1이  10000원 만큼 사과를 판매했음.
			
			seller2.saleApple(4000); // seller2가  4000원 만큼 사과를 판매했음. 
			seller2.saleApple(13000);  // seller2가  13000원 만큼 사과를 판매했음. 
			
			
			System.out.println("\n판매행위가 일어난 후 상태");
			seller1.showSaleResult();
			seller2.showSaleResult();
		}
		
		catch(NegativeException ne)   //throw ext;  << 이 문장에서 던진 예외를 여기서 catch문으로 받아줘야함.  
		{
			System.out.println(ne);  //에러 msg출력 . 
		}
		
	}
}
