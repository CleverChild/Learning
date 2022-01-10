

//  프로그램 예외처리부분 설명  :  가격 설정을 입력할때  "4r" 이런식으로  온전히 숫자가 아닌 문자열이 포함 되면  예외가 발생하게됨.

//  제네릭 클래스를 활용해보기 위해  삼성폰을 구매한다고 가정하고  상품이름과  상품가격을 시뮬레이션 해보는 프로그램. 
//  구매할 상품이름은 자료형이 String 이므로 String으로 설정.
//  가격은 int형이므로  int로 설정해서   box1객체를 만듬. 
//  box2객체에서  가격자료형을 Double로 했는데,  다른 자료형으로  제네릭 객체를 생성해보기 위함이며, 다른 의미는 없음. 
package myPackage;

import java.util.Scanner;

public class myGeneric
{
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		try {
		// 제네릭 클래스 Box를  String과 Integer자료형으로 설정하여 box1 인스턴스를 생성했음. 
		Box <String,Integer> box1 = new Box <String,Integer>(); 
		
		// 제네릭 클래스 Box를  String과 Double자료형으로 설정하여 box2 인스턴스를 생성했음.
		Box <String,Double> box2 = new Box <String,Double>();
		
		//상품 이름 설정, box1의 첫 번째 자료형은 String이므로  파라미터로 전달하는건 String형 이어야함.  
		box1.setItem("Samsung Galaxy S20");   
		
		System.out.println("갤럭시 S20의 가격 설정 : ");
		String str1 = sc.next();
		
		//상품 가격 설정, box1의 두 번째 자료형은 Integer이므로  파라미터로 전달하는건 Integer형 이어야함.
		box1.setPrice(Integer.parseInt(str1));
		
		//box1 정보출력. 
		System.out.println("구매할 상품 " + box1.getItem() + " ,  가격 : " + box1.getPrice() +"원");
		
		
		// -------------------- box2 시작 ---------------------------------
		
		//상품 이름 설정, box2의 첫 번째 자료형은 String이므로  파라미터로 전달하는건 String형 이어야함. 
		box2.setItem("\nSamsung Galaxy Note 8");
		
		System.out.println("갤럭시 노트 8의 가격 설정 : ");
		String str2 = sc.next();
		
		//상품 가격 설정, box2의 두 번째 자료형은 Double이므로  파라미터로 전달하는건 Double형 이어야함.
		box2.setPrice(Double.parseDouble(str2));
		
		//getItem메소드와 getPrice메소드를 호출해서  구매할 상품이름과  가격을 리턴받아서  출력. 
		System.out.println("구매할 상품 " + box2.getItem() + " ,  가격 : " + box2.getPrice() +"원");
		
		}
		
		catch(NumberFormatException e)     // 숫자로 변환할 수 없는 문자열이 포함되어 있으면   NumberFormat 예외가 발생한 것.
		{
			System.out.println("타입에 맞지 않는 값을 입력했습니다\n" + e);
		}
	}
		
}
