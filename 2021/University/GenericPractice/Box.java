
// main�ʿ��� ���� �ڷ������� item�� price�� �ڷ����� ������. 

package myPackage;

public class Box <I, P>  //Ÿ�� �Ű����� I�� P ����
{
	private I item;  // item�� �ڷ����� String, Integer, Double�� �ƹ��ų� �� ����.
	                 //  �ʵ庯��
	
    private P price; // price�� �ڷ����� String, Integer, Double�� �ƹ��ų� �� ����.
                     //  �ʵ庯��  
    
                     //  main�ʿ��� � �ڷ������� �ν��Ͻ��� ���� �ϴ��Ŀ� ���� �ʵ庯���� �ڷ����� �޶����� �� ��.

    public void setItem(I item)  // main�ʿ��� setItem �޼ҵ带 ȣ���Ͽ�  ������ ��ǰ�� ������. 
    {                            // setItem �޼ҵ� ȣ���Ҷ� �Ű������� �����ϰ� ���� ��ǰ�̸��� �����������.  
        this.item = item;        // this.item �� �ʵ� ���� item�� �ǹ���.  �Ű����� item�� �ƴ�. 
                                 // �Ű����� item�� �ǹ��ϴ°�  ���Թ�  =�� ��������  ������ �κ���.     
    }
    
    public void setPrice(P price) // main�ʿ��� setPrice �޼ҵ带 ȣ���Ͽ�  ������ ��ǰ�� ������ ����.
    {                             // setPrice �޼ҵ� ȣ���Ҷ� �Ű������� �����ϰ� ���� ��ǰ������ �����������.
        this.price = price;
        							// this.price �� �ʵ� ���� price�� �ǹ���.  �Ű����� price�� �ƴ�. 
        							// �Ű����� price�� �ǹ��ϴ°�  ���Թ�  =�� ��������  ������ �κ���. 
    }
    
    
    public I getItem()  //main �ʿ��� getItem�޼ҵ带 ȣ���Ͽ�  ��ǰ�̸��� ��ȯ����.   
    {
        return item;   //�ʵ庯�� item ��ȯ
    }
    
    
    public P getPrice()  //main �ʿ��� getPrice�޼ҵ带 ȣ���Ͽ�  ��ǰ������ ��ȯ����. 
    {
        return price;    //�ʵ庯�� price ��ȯ
    }

    
}
