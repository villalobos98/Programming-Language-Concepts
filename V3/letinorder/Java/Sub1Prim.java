import java.util.*;
//Sub1Prim:import//

// <prim>:Sub1Prim ::= SUB1OP
public class Sub1Prim extends Prim {



    public Sub1Prim() {

    }

    public static Sub1Prim parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<prim>:Sub1Prim", scn$.lno);
        scn$.match(Token.Val.SUB1OP, trace$);
        return new Sub1Prim();
    }

    @Override
    public Val apply(Val [] vals) {
        if ( vals.length != 1 )
            throw new RuntimeException( "One argument expected." );
        int i0 = vals[ 0 ].value;
        return new Val( i0 - 1 );
    }

    @Override
    public String toString() {
	return "sub1";
    }

}
