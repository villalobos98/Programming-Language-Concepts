import java.util.*;
//LetinorderExp:import//

// <exp>:LetinorderExp ::= LETINORDER <letDecls> IN <exp>
public class LetinorderExp extends Exp {

    public LetDecls letDecls;
    public Exp exp;

    public LetinorderExp(LetDecls letDecls, Exp exp) {
        this.letDecls = letDecls;
        this.exp = exp;
    }

    public static LetinorderExp parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<exp>:LetinorderExp", scn$.lno);
        scn$.match(Token.Val.LETINORDER, trace$);
        LetDecls letDecls = LetDecls.parse(scn$, trace$);
        scn$.match(Token.Val.IN, trace$);
        Exp exp = Exp.parse(scn$, trace$);
        return new LetinorderExp(letDecls, exp);
    }

  public Val eval( Env env ) {
      Env newEnv = letDecls.addBindingsSingly(env);
      return exp.eval(newEnv);
    }

}
