package com.qbase.auxilibrary.common.struct;

public abstract class FunctionCallBack<Result,Params> extends Function{


    public FunctionCallBack(String functionName) {
        super(functionName);
    }


    public abstract Result functionCallBack(Params params);

    
}
