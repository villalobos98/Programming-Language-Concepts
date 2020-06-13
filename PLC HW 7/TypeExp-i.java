TypeExp
%%%

    @Override
    public Val eval( Env env ) {
		Val v = exp.eval( env );
        return v;
    }

    @Override
    public Type evalType( TypeEnv tenv ) {
		Type tt = exp.evalType( tenv );
		System.out.println(tt.toString());
        return tt;
    }

%%%

