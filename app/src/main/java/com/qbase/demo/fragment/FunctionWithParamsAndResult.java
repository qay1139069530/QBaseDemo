package com.qbase.demo.fragment;

public abstract class FunctionWithParamsAndResult<Result,Params> extends Function{


    public FunctionWithParamsAndResult(String functionName) {
        super(functionName);
    }



    public abstract Result function(Params params);

    
}
