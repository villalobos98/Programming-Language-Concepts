import java.util.*;
import java.util.regex.*;

// Token class with match patterns (used with the built-in Scan class)
public class Token {
       
    public enum Val {
        WHITESPACE ("\\s+", true),
        COMMENT ("%.*", true),
        ADD1OP ("add1"),
        ADDOP ("\\+"),
        ASSIGN ("="),
        COMMA (","),
        ELSE ("else"),
        IF ("if"),
        IN ("in"),
        LET ("let"),
        LPAREN ("\\("),
        RPAREN ("\\)"),
        SUB1OP ("sub1"),
        SUBOP ("\\-"),
        THEN ("then"),
        LETINORDER ("letinorder"),
        LIT ("\\d+"),
        VAR ("[A-Za-z]\\w*"),
        $EOF (null),
        $ERROR (null);

	public String pattern;
        public boolean skip;
	public Pattern cPattern; // compiled pattern

        // a token pattern (skip == false)
        Val(String pattern) {
            this(pattern, false);
	}

        Val(String pattern, boolean skip) {
	    this.pattern = pattern;
            this.skip = skip;
            if (pattern != null)
	        this.cPattern = Pattern.compile(pattern, Pattern.DOTALL);
            else
                this.cPattern = null;
	}
    }

    public Val val;          // token
    public String str;       // the token string matched
    public int lno;          // the line number where this token was found

    public Token() {
	val = null;
        str = null;
        lno = 0;
    }
    
    public Token(Val val, String str, int lno) {
        this.val = val;
        this.str = str;
	this.lno = lno;
    }

    public Token(Val val, String str) {
	this(val, str, 0);
    }

    public String toString() {
        return str;
    }

    public boolean isEOF() {
        return this.val == Token.Val.$EOF;
    }

    public static void main(String [] args) {
	for (Val v : Val.values()) {
            if (v == Val.$EOF)
                break;
            String what;
            if (v.skip)
                what = "skip";
            else
                what = "token";
	    System.out.println(String.format("%s %s '%s'", what, v, v.pattern));
        }
    }

//Token//

}
