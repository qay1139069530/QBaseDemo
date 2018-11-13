package com.qbase.demo.fragment;

public abstract class FunctionWithParamsOnly<Params> extends Function{


    public FunctionWithParamsOnly(String functionName) {
        super(functionName);
    }



    public abstract void function(Params params);

    
}
