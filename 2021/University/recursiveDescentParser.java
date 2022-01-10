// 201621228 임민섭  

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
				if(ch == ' '||ch == '\t'||ch == '\r'); //  3 + 8 과 같이 공백이 있을 경우를 처리해야하므로 ch == ' ' 필요. 
				else if (Character.isDigit(ch))  // input.read()로  1글자를 읽은 후 그 ch가 숫자일 경우
				{
					value = number(); // global  int형 변수 value에  number()함수 사용해서 문자를 숫자로 변환후 저장.
					                  // char형 문자 '7'을  진짜 숫자 7로 변환하는 과정. 
					input.unread(ch); //  아직 첫 숫자 처리를 하지 못할 경우를 대비해서 ch가 operator로 가지 않게끔 
					 				  //  global변수 value에 숫자값만 저장해 놓고  ch위치는 다시 첫 숫자 쪽으로 되돌려놓음.
					                  //  unread(ch)를 하지 않으면, 첫 숫자 처리 되기전에 ch가 operatpr로 되기 때문에 에러.
					return Token.NUMBER; //  factor() 함수에서  value값을 잘 가져갈 수 있게  token을 NUMBER로 세팅함. 
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
						
					case '!': ch=input.read();  // 다음 글자 위치를 읽어서  !다음에 =이 나오는지 확인.  
						if(ch != '=')
		            	{
							input.unread(ch);  // 이미 '!'일 경우 다른 operator(+, - , *, / , &, |) 과 달리 ,
							                   // ch위치를 1회 앞으로 보내놨기  때문에  ch위치를 다시 되돌려 놓아야함. 
							                   // ex ) !6>3 일 경우 
							                   // unread(ch)를 하지 않으면, 1번쨰 숫자 6처리될쯤 token이 NUMBER가 아닌 GT로 되어버려서,
							                   // factor()함수 쪽에서 token == NUMBER 부분으로 가질 못함.  
							                   // 결과적으로  factor함수쪽에서 무조건 result = 0이 반환되어버려서 정상적으로 처리가 안됨.
							                   //  !6>3 을 입력했는데   !0>3  이렇게 되버려서  틀린 결과값이 나오게됨. 
							                   // 어쩄든, '!'일 경우  글자가 1개이기 때문에 ch를 여기서 미리 앞으로 떙겨놓으면 안됨. 
		            		return Token.NOT;
		            	}
		            	else
		            	{
		            		return Token.NOTEQ;  // 9 != 5 같은 경우.
		            							 // unread를 하지 않아도, token == NOTEQ 를 거치고 나면 token == NUMBER로
		            							 // 순조롭게 진행됨. 
		            						     // '!='는 글자가 2개이기 떄문에,  '!' 경우와 달리 ch를 미리 input.read()로 앞으로
		            							 // 하나 떙겨놓는게 올바름 .  
		            	}
						
		            case '=': 
		            	return Token.EQUAL;
		            	
		            case '<':  ch=input.read();
		            	if(ch!='=')
		            	{
		            		input.unread(ch);   //  마찬가지로 '<' 처럼 글자가 1개 일경우 여기서 미리 땡겨놓으면 안됨.
		            		                    // ex ) 2<4 일 경우  
		            							// unread(ch)를 하지 않으면, 2번쨰 숫자 4처리될쯤 token이 NUMBER가 아니라 ENTER로 되어버려서,
		            							// factor()함수 쪽에서 token == NUMBER 부분으로 가질 못함.  
		            						     // 결과적으로  factor함수쪽에서 무조건 result = 0이 반환되어버려서 정상적으로 처리가 안됨.
		            	                         // 2<4를 입력했는데 2<0으로  되버려서  틀린 결과값이 나오게됨.  
		            							// 어쩄든, '<'일 경우  글자가 1개이기 때문에 ch를 여기서 미리 앞으로 떙겨놓으면 안됨.
		            		return Token.LT;    
		            	}
		            	else
		            	{
		            		return Token.LTEQ;    // '<=' 는 글씨가 2개 이기 때문에 미리 앞으로 하나 떙겨 놓는게  올바르기 때문에
		            		                     // unread()를 하지 않음. 
		            	}
		            	
		            case '>': ch=input.read(); // 바로 위 '<'일 경우랑 설명 동일.  마찬가지로 '>' 처럼 글자가 1개 일경우 여기서 미리 땡겨놓으면 안됨.
		            	if(ch!='=')
		            	{
		            		input.unread(ch);
		            		return Token.GT;
		            	}
		            	else
		            	{
		            		return Token.GTEQ;   //마찬가지로 '>='는 글씨가 2개이고 미리 앞으로 하나 떙겨놔야 다음토큰이 NUMBER로 설정되기  때문에 unread()하지 않음. 
		            	}
		            	
		            case '\n':   // 사용자가 항상  수식입력후 마지막순간에 Enter를 치므로 Enter ('\n') 도 감지를 해줘야함.
		            	         // 이게 없으면 , Enter를 쳐도  Enter 토큰이 리턴되지 않아, command() 쪽에서 마지막 결과 출력을 못하게됨. 
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
    
	
	void match(Token c) // 전달받은 토큰이 현재 토큰과 일치할 경우  token을 한칸 앞으로 보내서 다음토큰을 가리키게함. 
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
	    	interactive = true;  // 논리연산이니까 true false로 출력하기 위해 interactive를 true로 설정. 
	        match(Token.NOT);  // match함수 호출해서  다음 토큰을 가져와야함.  
	        result = expr();
	        //여기서 바로 리턴 떄려야  bexp()부분 실행안됨.
	        //여기까지 오면 이미 모든 수식이 다 끝난 상태라 또 bexp()실행되면 에러임. 
	        if (result==0)  // 반전 과정.
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
			interactive = true;  // 논리연산이니까  interactive 는 true로
			if(token == Token.AND) // bexp로 부터  반환받은 0과 1로 and(또는 or연산) 을 하게됨. 
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
			// 이 case문에 들어왔다는건 토큰이 '<' 인 상태이기 때문에 들어온 것.  
			// 여기서  match로 다음토큰을 읽어서 token을 숫자로 가리키게 한 다음에,
			// aexp()를 호출해야함. 
			match(Token.LT);	
			if(result < aexp())   // expr()함수 쪽에  논리연산 결과  1 또는 0을 리턴해줌. 
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
			match(Token.EQUAL);  // 6==6  처럼  '=='은 글자가 2개 이기 때문에 token을 2번 앞으로 내보내서  숫자를 가리키게 한 후, aexp()호출.
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
		
		while (token == Token.PLUS || token == Token.MINUS) // +, -는  한 번만 나오지 않고 여러번 나올 수 있기 때문에 while문 사용.
		{
			if (token == Token.PLUS)
			{
				match(Token.PLUS);  // 마찬가지로  현재 토큰이 +이기 떄문에 일로 들어온 것이고, match함수로 토큰을 1칸 앞으로 내보낸 후
				                   // token == NUMBER  형태로 바뀐후 term()함수를 호출해야함. 
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
		if (token == Token.LPAREN)  //  중괄호는 한 번만 나오기 떄문에  if문을 사용. 
		{
			match(Token.LPAREN);
			result = expr();
			match(Token.RPAREN);  // '(' 이 나오면  반드시 ')'로 끝나야 하기 떄문에 이부분을 체크한 후 token을 1칸 앞으로 내보냄. 
			                      // 만약 ')'이 match가 안된다면 에러임.  
		}
		else if (token == Token.NUMBER)
		{
			result = value;  // token이 NUMBER일 경우 getToken()함수에서 설정한  global 변수 value를 result값에 대입함.
			match(Token.NUMBER); // 숫자저장후 다음연산을 진행해야 하므로 토큰을 1칸 앞으로 내보냄. 
		}
		
		return result;
	}
	
	/* <command> -> <expr> '\n' */
	void command()
	{
		int result = expr();
		if (token == Token.ENTER)
		{
			if (interactive)  // 비교연산 또는 논리연산일 경우 true , false로 출력. 
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
			else  // 사칙연산일 경우 그냥 result값을 출력. 
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
			interactive=false;  // 처음시작할 떄 interactive는 무조건 false로 한 후 , 만약 논리연산이 나올경우 그떄 true로 하기로함.
			System.out.print(">> ");
			p.parse();
		}
	}
}
