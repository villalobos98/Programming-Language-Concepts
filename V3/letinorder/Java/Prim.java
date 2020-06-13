import java.util.*;
//Prim:import//

public abstract class Prim {

    public static Prim parse(Scan scn$, Trace trace$) {
        Token t$ = scn$.cur();
        Token.Val v$ = t$.val;
        switch(v$) {
        case SUB1OP:
            return Sub1Prim.parse(scn$,trace$);
        case ADD1OP:
            return Add1Prim.parse(scn$,trace$);
        case SUBOP:
            return SubPrim.parse(scn$,trace$);
        case ADDOP:
            return AddPrim.parse(scn$,trace$);
        default:
            throw new RuntimeException("Prim cannot begin with " + t$);
        }
    }

    /**
     * Apply this primitive (whatever it is) to the provided values.
     */
    public abstract Val apply( Val[] vals );

}
