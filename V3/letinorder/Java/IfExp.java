import java.util.*;
//IfExp:import//

// <exp>:IfExp ::= IF <exp>test THEN <exp>thenPart ELSE <exp>elsePart
public class IfExp extends Exp {

    public Exp test;
    public Exp thenPart;
    public Exp elsePart;

    public IfExp(Exp test, Exp thenPart, Exp elsePart) {
        this.test = test;
        this.thenPart = thenPart;
        this.elsePart = elsePart;
    }

    public static IfExp parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<exp>:IfExp", scn$.lno);
        scn$.match(Token.Val.IF, trace$);
        Exp test = Exp.parse(scn$, trace$);
        scn$.match(Token.Val.THEN, trace$);
        Exp thenPart = Exp.parse(scn$, trace$);
        scn$.match(Token.Val.ELSE, trace$);
        Exp elsePart = Exp.parse(scn$, trace$);
        return new IfExp(test, thenPart, elsePart);
    }

    @Override
    public String toString() {
        return test + " ? " + thenPart + " : " + elsePart;
    }
    @Override
    public Val eval( Env env ) {
        return test.eval( env ).isTrue() ? thenPart.eval( env ) : elsePart.eval( env );
    }

}
