
LetDecls:import
%%%
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;
%%%


LetDecls
%%%

    // <letDecls>       **= <VAR> ASSIGN <exp>

    public Env makeEnv( Env oldEnv ) {
        assert varList.size() == expList.size(): "AST is messed up";

        // For each var-exp pair, make a new Binding
        // out of the corresponding token "lexeme" and expr evaluation.
        
        List< Binding > bindings = new LinkedList<>();
        Iterator< Token > varIter = varList.iterator();
        Iterator< Exp > expIter = expList.iterator();
        while ( varIter.hasNext() ) {
            Binding binding = new Binding(
                varIter.next().str,
                expIter.next().eval( oldEnv )
                );
            bindings.add( binding );
        }

        // Make an environment out of the Bindings.
        return oldEnv.extendEnv( new Bindings( bindings ) );
    }

        //The addBindingsSingly method will create a new environment each time one variable/expression pair is processed.
        //Instead of making a list of Bindings, you make a chain of Environments, each of which contains only one Binding.
    public Env addBindingsSingly( Env oldEnv ) {
         assert varList.size() == expList.size(): "AST is messed up";

        // For each var-exp pair, make a new Binding
        // out of the corresponding token "lexeme" and expr evaluation.
        
        Iterator< Token > varIter = varList.iterator();
        Iterator< Exp > expIter = expList.iterator();
        Env curr = oldEnv;
        while ( varIter.hasNext() ) {
            List< Binding > bindings = new LinkedList<>();
            Binding binding = new Binding(
                varIter.next().str,
                expIter.next().eval( oldEnv )
                );
            bindings.add( binding );
            curr = oldEnv.extendEnv( new Bindings( bindings ));

        }

        return curr; 
    }

%%%
