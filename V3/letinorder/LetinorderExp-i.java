
LetinorderExp
%%%
  public Val eval( Env env ) {
      Env newEnv = letDecls.addBindingsSingly(env);
      return exp.eval(newEnv);
    }
%%%
