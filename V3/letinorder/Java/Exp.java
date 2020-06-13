import java.util.*;
//Exp:import//

public abstract class Exp {

    public static Exp parse(Scan scn$, Trace trace$) {
        Token t$ = scn$.cur();
        Token.Val v$ = t$.val;
        switch(v$) {
        case LIT:
            return LitExp.parse(scn$,trace$);
        case SUBOP:
        case ADD1OP:
        case ADDOP:
        case SUB1OP:
            return PrimAppExp.parse(scn$,trace$);
        case VAR:
            return VarExp.parse(scn$,trace$);
        case LET:
            return LetExp.parse(scn$,trace$);
        case LETINORDER:
            return LetinorderExp.parse(scn$,trace$);
        case IF:
            return IfExp.parse(scn$,trace$);
        default:
            throw new RuntimeException("Exp cannot begin with " + t$);
        }
    }

    public abstract Val eval( Env env );

}
