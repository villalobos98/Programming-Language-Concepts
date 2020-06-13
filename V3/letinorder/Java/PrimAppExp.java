import java.util.*;
import java.util.Arrays;

// <exp>:PrimAppExp ::= <prim> LPAREN <operands> RPAREN
public class PrimAppExp extends Exp {

    public Prim prim;
    public Operands operands;

    public PrimAppExp(Prim prim, Operands operands) {
        this.prim = prim;
        this.operands = operands;
    }

    public static PrimAppExp parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<exp>:PrimAppExp", scn$.lno);
        Prim prim = Prim.parse(scn$, trace$);
        scn$.match(Token.Val.LPAREN, trace$);
        Operands operands = Operands.parse(scn$, trace$);
        scn$.match(Token.Val.RPAREN, trace$);
        return new PrimAppExp(prim, operands);
    }

    @Override
    public Val eval( Env env ) {
        // evaluate the terms in the expression list
        // and apply the prim to the array of Vals
        List<Val> args = operands.evalOperands( env );
        Val [] va = args.toArray( new Val[ args.size() ] );
        return prim.apply( va );
    }

    @Override
    public String toString() {
	return prim + "(" + operands + ")";
    }

}
