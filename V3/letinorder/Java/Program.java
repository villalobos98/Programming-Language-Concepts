import java.util.*;
//Program:import//

// <program> ::= <exp>
public class Program {

    public Exp exp;

    public Program() { } // dummy constructor

    public Program(Exp exp) {
        this.exp = exp;
    }

    public static Program parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<program>", scn$.lno);
        Exp exp = Exp.parse(scn$, trace$);
        return new Program(exp);
    }

    public static Env initEnv = Env.ENV_NULL.extendEnv(
            new Bindings(Arrays.asList(
                new Binding( "i", new Val( 1 ) ),
                new Binding( "v", new Val( 5 ) ),
                new Binding( "x", new Val( 10 ) ),
                new Binding( "l", new Val( 50 ) ),
                new Binding( "c", new Val( 100 ) ),
                new Binding( "d", new Val( 500 ) ),
                new Binding( "m", new Val( 1000 ) )
            ))
    );

    @Override
    public String toString() {
        return exp.eval( initEnv ).toString();
    }

    public String toStringOld() {
	return exp.toString();
    }

}
