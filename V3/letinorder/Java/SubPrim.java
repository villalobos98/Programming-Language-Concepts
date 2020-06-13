import java.util.*;
//SubPrim:import//

// <prim>:SubPrim ::= SUBOP
public class SubPrim extends Prim {



    public SubPrim() {

    }

    public static SubPrim parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<prim>:SubPrim", scn$.lno);
        scn$.match(Token.Val.SUBOP, trace$);
        return new SubPrim();
    }

    @Override
    public Val apply(Val [] vals) {
        if ( vals.length != 2 )
            throw new RuntimeException( "Two arguments expected." );
        int i0 = vals[ 0 ].value;
        int i1 = vals[ 1 ].value;
        return new Val( i0 - i1 );
    }

    @Override
    public String toString() {
	return "-";
    }

}
