package com.qbase.demo.fragment;

public abstract class FunctionWithResultOnly<Result> extends Function{


    public FunctionWithResultOnly(String functionName) {
        super(functionName);
    }



    public abstract Result function();

    
}
