import java.io.*;
/*enum Relop {
		LPAREN("("), RPAREN(")"), PLUS("+"), MINUS("-"), MULTI("*"), 
		DIVIDE("/"), AND("&"), OR("|"), TRUE("true"), FALSE("false"), NUMBER(""),
		NOT("!"), EQUAL("=="), LT("<"), LTEQ("<="), GT(">"), GTEQ(">="), NOTEQ("!="), NLINE("\n");
	}; 
	*/
enum Relop {
		//FALSE, TRUE,
		LPAREN, RPAREN, PLUS, MINUS, MULTI, DIVIDE,
		AND, OR, NUMBER, NOT,
		EQUAL, LT, LTEQ, GT, GTEQ, NOTEQ, NLINE
	};
class Calculator {
    Relop token; int value; int ch; int ch2;
	boolean bool; 
    private PushbackInputStream input;
    //final int NUMBER=256;
	final int TRUE = 1;
	final int FALSE = 0;
	

    Calculator(PushbackInputStream is) {
        input = is;
    }

    Relop getToken( )  { /* tokens are characters */
		while(true) {
			try  {
				ch = input.read();
				if (ch == ' ' || ch == '\t' || ch == '\r') ;
				else if (Character.isDigit(ch)) {
					value = number( );
					input.unread(ch);
					return Relop.NUMBER;
				}
				else if (ch == '='){
					ch2 = input.read();
					if (ch2 == '=')
						return Relop.EQUAL;
					else error();
				}	
				else if (ch == '>'){
					ch2 = input.read();
					if (ch2 == '=')
						return Relop.GTEQ;
					else {
						input.unread(ch2);
						return Relop.GT;
					}				
				}
				else if (ch == '<'){
					ch2 = input.read();
					if (ch2 == '=')
						return Relop.LTEQ;
					else {
						input.unread(ch2);
						return Relop.LT;
					}
				}
				else if (ch == '!'){
					ch2 = input.read();
					if (ch2 == '=')
						return Relop.NOTEQ;
					else
						return Relop.NOT;
				}
				else if (ch == '&')
					return Relop.AND;
				else if (ch == '|')
					return Relop.OR;
				else if (ch == '+')
					return Relop.PLUS;
				else if (ch == '-')
					return Relop.MINUS;
				else if (ch == '*')
					return Relop.MULTI;
				else if (ch == '/')
					return Relop.DIVIDE;
				else if (ch == '(')
					return Relop.LPAREN;
				else if (ch == ')')
					return Relop.RPAREN;
				else if (ch == '\n')
					return Relop.NLINE;				
			} catch (IOException e) {
                System.err.println(e);
            }
        }
    }
	
	/* <expr> -> <bexp> {& <bexp> | '|'<bexp>} | !<expr> | true | false */
	int expr(){		
		int result = bexp();
		boolean b=false;
		while(token == Relop.AND || token == Relop.OR){
			if (result == TRUE)
				b = true;
			else
				b = false;
			if (token == Relop.AND){
				match(Relop.AND);
				if (bexp() == TRUE)
					b = b && true;
				else if (bexp() == FALSE)
					b = b && false;
				if (b == true)
					result = TRUE;
				else
					result = FALSE;	
			}
			else if (token == Relop.OR){
				match(Relop.OR);
				if (bexp() == TRUE)
					b = b || true;
				else if (bexp() == FALSE)
					b = b || false;	
				if (b == true)
					result = TRUE;
				else
					result = FALSE;					
			}
		}
		if (token == Relop.NOT){			
			match(Relop.NOT);
			if (expr() == TRUE)
				b = true;
			else if(expr() == FALSE)
				b = false;
			b = !b;					
			if (b == true)
				result = TRUE;
			else
				result = FALSE;	
		}		
		return result;
	}
	
	/* <bexp> -> <aexp> [<relop> <aexp>] */	
	int bexp(){
		int result = aexp();
		if (token == Relop.EQUAL){
			match(Relop.EQUAL);
			if(result == aexp())
				result = TRUE;
			else
				result = FALSE;
		}
		else if (token == Relop.NOTEQ){
			match(Relop.NOTEQ);
			if (result != aexp())
				result = TRUE;
			else
				result = FALSE;
		}
		else if (token == Relop.LT){
			match(Relop.LT);
			if (result < aexp())
				result = TRUE;
			else
				result = FALSE;
		}
		else if (token == Relop.GT){
			match(Relop.GT);
			if (result > aexp())
				result = TRUE;
			else
				result = FALSE;
		}
		else if (token == Relop.LTEQ){
			match(Relop.LTEQ);
			if (result <= aexp())
				result = TRUE;
			else
				result = FALSE;
		}
		else if (token == Relop.GTEQ){
			match(Relop.GTEQ);
			if (result >= aexp())
				result = TRUE;
			else
				result = FALSE;
		}
		return result;
	}
	/* <relop> -> == | != | < | > | <= | >= */
	
	int aexp( ) {
    /* aexp -> term { '+' term | '-' term } */
        int result = term();
        while (token == Relop.PLUS || token == Relop.MINUS) {
            if (token == Relop.PLUS){
				match(Relop.PLUS);
				result += term();				
			}
			else if (token == Relop.MINUS){
				match(Relop.MINUS);
				result -= term();
			}				
        }
        return result;
    }

	int term( ) {
    /* term -> factor { '*' factor | '/' factor	} */
       int result = factor();
       while (token == Relop.MULTI || token == Relop.DIVIDE) {
		   if (token == Relop.MULTI){
				match(Relop.MULTI);
				result *= factor();
		   }
		   else if (token == Relop.DIVIDE){
				match(Relop.DIVIDE);
				result /= factor();					
		   }
       }
       return result;
    }

    int factor() {
    /* factor -> '(' expr ')' | number */
        int result = 0;
		/*if (token == '-'){
			result = -result;
			match('-');
		}*/
        if (token == Relop.LPAREN) {
            match(Relop.LPAREN);
            result = aexp();
            match(Relop.RPAREN);
        }
        else if (token == Relop.NUMBER) {
            result = value;
	        match(Relop.NUMBER); //token = getToken();
        }
        return result;
    }
	    
    void match(Relop c) { 
        if (token == c) 
			token = getToken();
        else error();
    }
	
	int number( )  {
    /* number -> digit { digit } */
        int result = ch - '0';
        try  {
            ch = input.read();
            while (Character.isDigit(ch)) {
                result = 10 * result + ch -'0';
                ch = input.read(); 
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        return result;
    }

    void error( ) {
        System.out.printf("parse error : %d\n", ch);
        System.exit(1);
    }

    void command( ) {
    /* command -> expr '\n' */
        int result = expr();
        if (token == Relop.NLINE) /* end the parse and print the result */
			//System.out.println("The result is: %d\n", result);
			if (result == TRUE)
				System.out.println(true);
			else if(result == FALSE)
				System.out.println(false);
			else
				System.out.println(result);
        else error();
    }   

    void parse( ) {
        token = getToken(); // get the first token
        command();          // call the parsing command
    }

    public static void main(String args[]) { 
        Calculator p = new Calculator(new PushbackInputStream(System.in));
        while(true) {
            System.out.print(">> ");
            p.parse();
        }
    }	
}