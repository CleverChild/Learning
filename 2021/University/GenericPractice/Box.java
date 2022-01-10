
// main쪽에서 정한 자료형으로 item과 price의 자료형이 정해짐. 

package myPackage;

public class Box <I, P>  //타입 매개변수 I랑 P 선언
{
	private I item;  // item의 자료형은 String, Integer, Double등 아무거나 다 가능.
	                 //  필드변수
	
    private P price; // price의 자료형은 String, Integer, Double등 아무거나 다 가능.
                     //  필드변수  
    
                     //  main쪽에서 어떤 자료형으로 인스턴스를 생성 하느냐에 따라 필드변수의 자료형이 달라지게 될 것.

    public void setItem(I item)  // main쪽에서 setItem 메소드를 호출하여  구매할 상품을 설정함. 
    {                            // setItem 메소드 호출할때 매개변수로 구매하고 싶은 상품이름을 전달해줘야함.  
        this.item = item;        // this.item 은 필드 변수 item을 의미함.  매개변수 item이 아님. 
                                 // 매개변수 item을 의미하는건  대입문  =를 기준으로  오른쪽 부분임.     
    }
    
    public void setPrice(P price) // main쪽에서 setPrice 메소드를 호출하여  구매할 상품의 가격을 설정.
    {                             // setPrice 메소드 호출할때 매개변수로 구매하고 싶은 상품가격을 전달해줘야함.
        this.price = price;
        							// this.price 은 필드 변수 price을 의미함.  매개변수 price가 아님. 
        							// 매개변수 price을 의미하는건  대입문  =를 기준으로  오른쪽 부분임. 
    }
    
    
    public I getItem()  //main 쪽에서 getItem메소드를 호출하여  상품이름을 반환해줌.   
    {
        return item;   //필드변수 item 반환
    }
    
    
    public P getPrice()  //main 쪽에서 getPrice메소드를 호출하여  상품가격을 반환해줌. 
    {
        return price;    //필드변수 price 반환
    }

    
}
