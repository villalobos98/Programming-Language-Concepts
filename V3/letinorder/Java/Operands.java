import java.util.*;
import java.util.stream.Collectors;

// <operands> **= <exp> +COMMA
public class Operands {

    public List<Exp> expList;

    public Operands(List<Exp> expList) {
        this.expList = expList;
    }

    public static Operands parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<operands>", scn$.lno);
        List<Exp> expList = new ArrayList<Exp>();
        // first trip through the parse
        Token t$ = scn$.cur();
        Token.Val v$ = t$.val;
        switch(v$) {
        case LETINORDER:
        case ADDOP:
        case SUBOP:
        case LET:
        case VAR:
        case IF:
        case ADD1OP:
        case LIT:
        case SUB1OP:
            while(true) {
                expList.add(Exp.parse(scn$, trace$));
                t$ = scn$.cur();
                v$ = t$.val;
                if (v$ != Token.Val.COMMA)
                    break; // not a separator, so we're done
                scn$.match(v$, trace$);
            }
        } // end of switch
        return new Operands(expList);

    }

    /**
     * Fetch the values of each expression in the parameter (operands) list.
     */
    public List<Val> evalOperands( Env env ) {
        return expList.stream()
                            .map( exp -> exp.eval(env) )
                            .collect( Collectors.toList() );
    }

    @Override
    public String toString() {
        return expList.stream()
                        .map( Exp::toString )
                        .collect( Collectors.joining( "," ) );
    }

}
