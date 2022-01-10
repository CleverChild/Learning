// 201621228 �ӹμ�  

import java.io.*;


enum Token 
{
     BOOL("bool"),CHAR("char"), ELSE("else"), FALSE("false"), FLOAT("float"), 
     STRING("string"), IF("if"), INT("int"),  TRUE("true"), WHILE("while"), 
     RETURN("return"), VOID("void"), FUN("fun"),  THEN("then"), LET("let"), 
     IN("in"), END("end"), READ("read"), PRINT("print"), DO("do"),FOR("for"),
     TRY("try"), CATCH("catch"), RAISE("raise"),EXC("exc"),
     EOF("<<EOF>>"), 
     LBRACE("{"), RBRACE("}"), LBRACKET("["), RBRACKET("]"),
     LPAREN("("), RPAREN(")"), SEMICOLON(";"), COMMA(","),
     ASSIGN("="), EQUAL("=="),  LT("<"), LTEQ("<="), GT(">"),  
     GTEQ(">="),  NOT("!"),    NOTEQ("!="), PLUS("+"), MINUS("-"), 
     MULTIPLY("*"), DIVIDE("/"), AND("&"), OR("|"), ID(""), 
     NUMBER(""), STRLITERAL(""),ENTER("\n"); 

    private String value; 

    private Token (String v) {
        value = v;
    }

    public String value( ) { return value; }
    public Token setValue(String v)
    { 
        this.value = v; 
        return this; 
    }

    public static Token idORkeyword (String name) {
        for (Token token : Token.values()) {
           if (token.value().equals(name)) 
	       return token;
           if (token == Token.EOF) 
               break;
        }
	return ID.setValue(name);
    } // keyword or ID
}



class Parser2
{
	Token token; 
	private PushbackInputStream input;
	final int NUMBER = 256;
    public static boolean interactive = false;
    
    int ch; int value;
    
	
	Parser2(PushbackInputStream is)
	{
		input = is;
	}
	
	Token getToken()
	{
		while(true){
			try{
				ch = input.read();
				if(ch == ' '||ch == '\t'||ch == '\r'); //  3 + 8 �� ���� ������ ���� ��츦 ó���ؾ��ϹǷ� ch == ' ' �ʿ�. 
				else if (Character.isDigit(ch))  // input.read()��  1���ڸ� ���� �� �� ch�� ������ ���
				{
					value = number(); // global  int�� ���� value��  number()�Լ� ����ؼ� ���ڸ� ���ڷ� ��ȯ�� ����.
					                  // char�� ���� '7'��  ��¥ ���� 7�� ��ȯ�ϴ� ����. 
					input.unread(ch); //  ���� ù ���� ó���� ���� ���� ��츦 ����ؼ� ch�� operator�� ���� �ʰԲ� 
					 				  //  global���� value�� ���ڰ��� ������ ����  ch��ġ�� �ٽ� ù ���� ������ �ǵ�������.
					                  //  unread(ch)�� ���� ������, ù ���� ó�� �Ǳ����� ch�� operatpr�� �Ǳ� ������ ����.
					return Token.NUMBER; //  factor() �Լ�����  value���� �� ������ �� �ְ�  token�� NUMBER�� ������. 
				}
				else
				{
					switch (ch)
					{
					case '+':
						return Token.PLUS;
					case '-':
						return Token.MINUS;
					case '*':
						return Token.MULTIPLY;
					case '/':
						return Token.DIVIDE;
					case '(':
						return Token.LPAREN;
					case ')':
						return Token.RPAREN;
					case '&':
						return Token.AND;
					case '|':
						return Token.OR;
						
					case '!': ch=input.read();  // ���� ���� ��ġ�� �о  !������ =�� �������� Ȯ��.  
						if(ch != '=')
		            	{
							input.unread(ch);  // �̹� '!'�� ��� �ٸ� operator(+, - , *, / , &, |) �� �޸� ,
							                   // ch��ġ�� 1ȸ ������ ��������  ������  ch��ġ�� �ٽ� �ǵ��� ���ƾ���. 
							                   // ex ) !6>3 �� ��� 
							                   // unread(ch)�� ���� ������, 1���� ���� 6ó������ token�� NUMBER�� �ƴ� GT�� �Ǿ������,
							                   // factor()�Լ� �ʿ��� token == NUMBER �κ����� ���� ����.  
							                   // ���������  factor�Լ��ʿ��� ������ result = 0�� ��ȯ�Ǿ������ ���������� ó���� �ȵ�.
							                   //  !6>3 �� �Է��ߴµ�   !0>3  �̷��� �ǹ�����  Ʋ�� ������� �����Ե�. 
							                   // ���, '!'�� ���  ���ڰ� 1���̱� ������ ch�� ���⼭ �̸� ������ ���ܳ����� �ȵ�. 
		            		return Token.NOT;
		            	}
		            	else
		            	{
		            		return Token.NOTEQ;  // 9 != 5 ���� ���.
		            							 // unread�� ���� �ʾƵ�, token == NOTEQ �� ��ġ�� ���� token == NUMBER��
		            							 // �����Ӱ� �����. 
		            						     // '!='�� ���ڰ� 2���̱� ������,  '!' ���� �޸� ch�� �̸� input.read()�� ������
		            							 // �ϳ� ���ܳ��°� �ùٸ� .  
		            	}
						
		            case '=': 
		            	return Token.EQUAL;
		            	
		            case '<':  ch=input.read();
		            	if(ch!='=')
		            	{
		            		input.unread(ch);   //  ���������� '<' ó�� ���ڰ� 1�� �ϰ�� ���⼭ �̸� ���ܳ����� �ȵ�.
		            		                    // ex ) 2<4 �� ���  
		            							// unread(ch)�� ���� ������, 2���� ���� 4ó������ token�� NUMBER�� �ƴ϶� ENTER�� �Ǿ������,
		            							// factor()�Լ� �ʿ��� token == NUMBER �κ����� ���� ����.  
		            						     // ���������  factor�Լ��ʿ��� ������ result = 0�� ��ȯ�Ǿ������ ���������� ó���� �ȵ�.
		            	                         // 2<4�� �Է��ߴµ� 2<0����  �ǹ�����  Ʋ�� ������� �����Ե�.  
		            							// ���, '<'�� ���  ���ڰ� 1���̱� ������ ch�� ���⼭ �̸� ������ ���ܳ����� �ȵ�.
		            		return Token.LT;    
		            	}
		            	else
		            	{
		            		return Token.LTEQ;    // '<=' �� �۾��� 2�� �̱� ������ �̸� ������ �ϳ� ���� ���°�  �ùٸ��� ������
		            		                     // unread()�� ���� ����. 
		            	}
		            	
		            case '>': ch=input.read(); // �ٷ� �� '<'�� ���� ���� ����.  ���������� '>' ó�� ���ڰ� 1�� �ϰ�� ���⼭ �̸� ���ܳ����� �ȵ�.
		            	if(ch!='=')
		            	{
		            		input.unread(ch);
		            		return Token.GT;
		            	}
		            	else
		            	{
		            		return Token.GTEQ;   //���������� '>='�� �۾��� 2���̰� �̸� ������ �ϳ� ���ܳ��� ������ū�� NUMBER�� �����Ǳ�  ������ unread()���� ����. 
		            	}
		            	
		            case '\n':   // ����ڰ� �׻�  �����Է��� ������������ Enter�� ġ�Ƿ� Enter ('\n') �� ������ �������.
		            	         // �̰� ������ , Enter�� �ĵ�  Enter ��ū�� ���ϵ��� �ʾ�, command() �ʿ��� ������ ��� ����� ���ϰԵ�. 
						return Token.ENTER;
					}
				}
			}catch(IOException e){
				System.err.println(e);
			}
		}
	}
	
	int number()
	{
		int result = ch - '0';
		try{
			ch = input.read();
			while(Character.isDigit(ch))
			{
				result = 10*result + ch - '0';
				ch = input.read();
			}
		}catch(IOException e){
			System.err.println(e);
		}
		return result;
	}
    
	
	void match(Token c) // ���޹��� ��ū�� ���� ��ū�� ��ġ�� ���  token�� ��ĭ ������ ������ ������ū�� ����Ű����. 
	{
		if (token == c)
			token = getToken();
		
		else error();
	}
    
	/* <expr> -> <term> {+ <term>} */
	int expr()
	{
		int result = 0;  
		
		switch (token) 
		{
	    case NOT:
	    	interactive = true;  // �������̴ϱ� true false�� ����ϱ� ���� interactive�� true�� ����. 
	        match(Token.NOT);  // match�Լ� ȣ���ؼ�  ���� ��ū�� �����;���.  
	        result = expr();
	        //���⼭ �ٷ� ���� ������  bexp()�κ� ����ȵ�.
	        //������� ���� �̹� ��� ������ �� ���� ���¶� �� bexp()����Ǹ� ������. 
	        if (result==0)  // ���� ����.
	        {
	        	return 1;
	        }
	        else if (result==1)
	        {
	        	return 0;
	        }
        case TRUE:
        	match(Token.TRUE);
        case FALSE:
            match(Token.FALSE);
            
        }
		
		result = bexp();
		
		while (token == Token.AND || token == Token.OR) 
		{
			interactive = true;  // �������̴ϱ�  interactive �� true��
			if(token == Token.AND) // bexp�� ����  ��ȯ���� 0�� 1�� and(�Ǵ� or����) �� �ϰԵ�. 
			{
				match(Token.AND);
	            result &= bexp();

			}
			else if(token == Token.OR)
			{
				match(Token.OR);
				result |= bexp();
			}
        }
		 
		
		return result;
	}
	
	int bexp()
	{
		int result = aexp();
		switch (token)
		{
		case LT: 
			interactive = true;
			// �� case���� ���Դٴ°� ��ū�� '<' �� �����̱� ������ ���� ��.  
			// ���⼭  match�� ������ū�� �о token�� ���ڷ� ����Ű�� �� ������,
			// aexp()�� ȣ���ؾ���. 
			match(Token.LT);	
			if(result < aexp())   // expr()�Լ� �ʿ�  ������ ���  1 �Ǵ� 0�� ��������. 
			{
				return 1;  
			}
			else
			{			
				return 0;
			}
		case LTEQ:
			interactive = true;
			match(Token.LTEQ);
			if(result <= aexp())
			{
				return 1;
			}
			else
			{			
				return 0;
			}
		case GT:
			interactive = true;
			match(Token.GT);
			if(result > aexp())
			{
				return 1;
			}
			else
			{			
				return 0;
			}
		case GTEQ:
			interactive = true;
			match(Token.GTEQ);
			if(result >= aexp())
			{
				return 1;
			}
			else
			{			
				return 0;
			}

		case EQUAL:
			interactive = true;
			match(Token.EQUAL);  // 6==6  ó��  '=='�� ���ڰ� 2�� �̱� ������ token�� 2�� ������ ��������  ���ڸ� ����Ű�� �� ��, aexp()ȣ��.
			match(Token.EQUAL);
			if(result == aexp())
			{
				return 1;
			}
			else
			{			
				return 0;
			}
			
		case NOTEQ:
			interactive = true;
			match(Token.NOTEQ);
			if(result != aexp())
			{
				return 1;
			}
			else
			{			
				return 0;
			}
			
		} // switch
		return result;
	}

	int aexp()
	{
		int result = term();
		
		while (token == Token.PLUS || token == Token.MINUS) // +, -��  �� ���� ������ �ʰ� ������ ���� �� �ֱ� ������ while�� ���.
		{
			if (token == Token.PLUS)
			{
				match(Token.PLUS);  // ����������  ���� ��ū�� +�̱� ������ �Ϸ� ���� ���̰�, match�Լ��� ��ū�� 1ĭ ������ ������ ��
				                   // token == NUMBER  ���·� �ٲ��� term()�Լ��� ȣ���ؾ���. 
				result += term();
			}
			else if (token == Token.MINUS)
			{
				match(Token.MINUS);
				result -= term();
			}
		}
		return result;
	}
	
	/* <term> -> <factor> {* <factor>} */
	int term()
	{
		int result = factor();
		while (token == Token.MULTIPLY || token == Token.DIVIDE)
		{
			if (token == Token.MULTIPLY)
			{
				match(Token.MULTIPLY);
				result *= factor();
			}
			else if (token == Token.DIVIDE)
			{
				match(Token.DIVIDE);
				result /= factor();
			}
		}
		return result;
	}
	
	/* <factor> -> ( <expr> ) | number */
	int factor(){
		int result = 0;
		if (token == Token.LPAREN)  //  �߰�ȣ�� �� ���� ������ ������  if���� ���. 
		{
			match(Token.LPAREN);
			result = expr();
			match(Token.RPAREN);  // '(' �� ������  �ݵ�� ')'�� ������ �ϱ� ������ �̺κ��� üũ�� �� token�� 1ĭ ������ ������. 
			                      // ���� ')'�� match�� �ȵȴٸ� ������.  
		}
		else if (token == Token.NUMBER)
		{
			result = value;  // token�� NUMBER�� ��� getToken()�Լ����� ������  global ���� value�� result���� ������.
			match(Token.NUMBER); // ���������� ���������� �����ؾ� �ϹǷ� ��ū�� 1ĭ ������ ������. 
		}
		
		return result;
	}
	
	/* <command> -> <expr> '\n' */
	void command()
	{
		int result = expr();
		if (token == Token.ENTER)
		{
			if (interactive)  // �񱳿��� �Ǵ� �������� ��� true , false�� ���. 
			{
				if(result==1)  
				{
					System.out.println(true);
				}
				else
				{
					System.out.println(false);
				}
			}
			else  // ��Ģ������ ��� �׳� result���� ���. 
			{
				System.out.println(result);
			}
		}
		else error();
	}
	
	void parse()
	{
		token = getToken();
		command();
	}

	void error(){
		System.out.printf("parse error: %d\n", ch);
		System.exit(1);
	}
	
	public static void main(String args[]){
		Parser2 p = new Parser2 (new PushbackInputStream(System.in));
		while(true)
		{
			interactive=false;  // ó�������� �� interactive�� ������ false�� �� �� , ���� �������� ���ð�� �׋� true�� �ϱ����.
			System.out.print(">> ");
			p.parse();
		}
	}
}
