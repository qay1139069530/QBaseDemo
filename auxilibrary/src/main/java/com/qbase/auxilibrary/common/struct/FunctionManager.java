package com.qbase.auxilibrary.common.struct;


import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.HashMap;

public class FunctionManager {

    private static volatile FunctionManager Instance;

    private FunctionManager() {
        mFunctionResult = new HashMap<>();
    }

    public static FunctionManager getInstance() {
        if (Instance == null) {
            synchronized (FunctionManager.class) {
                if (Instance == null) {
                    Instance = new FunctionManager();
                }
            }
        }
        return Instance;
    }

    private HashMap<String, FunctionCallBack> mFunctionResult;


    /**
     * 清除所有调用方法
     */
    public void removeAllFunction() {
        if (mFunctionResult != null) {
            synchronized (FunctionManager.class) {
                if (mFunctionResult != null) {
                    mFunctionResult.clear();
                }
            }
        }
    }

    /**
     * @param functionName
     * @return
     */
    public FunctionManager removeFunction(@NonNull String functionName) {
        if (mFunctionResult != null) {
            synchronized (FunctionManager.class) {
                if (mFunctionResult != null) {
                    if (mFunctionResult.containsKey(functionName)) {
                        mFunctionResult.remove(functionName);
                    }
                }
            }
        }
        return this;
    }

    /**
     * 添加Function
     * @param function
     * @return
     */
    public FunctionManager addFunction(@NonNull FunctionCallBack function) {
        if (!TextUtils.isEmpty(function.functionName) && !mFunctionResult.containsKey(function.functionName)) {
            mFunctionResult.put(function.functionName, function);
        }
        return this;
    }

    /**
     * 无参数
     * @param functionName
     * @throws FunctionException
     */
    public void invokeFunction(@NonNull String functionName) throws FunctionException {
        this.invokeFunction(functionName, null, null);
    }

    /**
     * 无参数有返回
     * @param functionName
     * @param clazz
     * @return
     * @throws FunctionException
     */
    public <Result> Result invokeFunction(@NonNull String functionName, Class<Result> clazz) throws FunctionException {
        return this.invokeFunction(functionName, null, clazz);
    }

    /**
     * 有参数无返回
     * @param functionName
     * @param data
     * @throws FunctionException
     */
    public <Params> void invokeFunction(@NonNull String functionName, Params data) throws FunctionException {
        this.invokeFunction(functionName, data, null);
    }

    /**
     * 有参数有返回
     * @param functionName
     * @param data
     * @param clazz
     * @return
     * @throws FunctionException
     */
    public <Result, Params> Result invokeFunction(@NonNull String functionName, Params data, Class<Result> clazz) throws FunctionException {
        if (TextUtils.isEmpty(functionName)) {
            return null;
        }
        if (mFunctionResult != null) {
            FunctionCallBack function = mFunctionResult.get(functionName);
            if (function != null) {
                if (clazz != null) {
                    return clazz.cast(function.functionCallBack(data));
                } else {
                    return (Result) function.functionCallBack(data);
                }

            } else {
                throw new FunctionException("has no this function:" + functionName);
            }
        }
        return null;
    }


}
