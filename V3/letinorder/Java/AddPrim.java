import java.util.*;
//AddPrim:import//

// <prim>:AddPrim ::= ADDOP
public class AddPrim extends Prim {



    public AddPrim() {

    }

    public static AddPrim parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<prim>:AddPrim", scn$.lno);
        scn$.match(Token.Val.ADDOP, trace$);
        return new AddPrim();
    }

    @Override
    public Val apply(Val [] vals) {
        if ( vals.length != 2 )
            throw new RuntimeException( "Two arguments expected." );
        int i0 = vals[ 0 ].value;
        int i1 = vals[ 1 ].value;
        return new Val( i0 + i1 );
    }

    @Override
    public String toString() {
	return "+";
    }

}
