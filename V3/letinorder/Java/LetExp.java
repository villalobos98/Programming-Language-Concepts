import java.util.*;
//LetExp:import//

// <exp>:LetExp ::= LET <letDecls> IN <exp>
public class LetExp extends Exp {

    public LetDecls letDecls;
    public Exp exp;

    public LetExp(LetDecls letDecls, Exp exp) {
        this.letDecls = letDecls;
        this.exp = exp;
    }

    public static LetExp parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<exp>:LetExp", scn$.lno);
        scn$.match(Token.Val.LET, trace$);
        LetDecls letDecls = LetDecls.parse(scn$, trace$);
        scn$.match(Token.Val.IN, trace$);
        Exp exp = Exp.parse(scn$, trace$);
        return new LetExp(letDecls, exp);
    }

    // <exp>:LetExp     ::= LET <letDecls> IN <exp>

    public Val eval( Env env ) {
        Env newEnv = letDecls.makeEnv( env );
        return exp.eval( newEnv );
    }


}
