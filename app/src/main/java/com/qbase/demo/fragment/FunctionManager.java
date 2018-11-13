package com.qbase.demo.fragment;


import android.text.TextUtils;

import java.util.HashMap;

public class FunctionManager {

    private static volatile FunctionManager Instance;

    private FunctionManager() {
        mFunctionNoParamsNoResult = new HashMap<>();
        mFunctionWithParamsOnly = new HashMap<>();
        mFunctionWithResultOnly = new HashMap<>();
        mFunctionWithParamsAndResult = new HashMap<>();
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

    private HashMap<String, FunctionNoParamsNoResult> mFunctionNoParamsNoResult;
    private HashMap<String, FunctionWithParamsOnly> mFunctionWithParamsOnly;
    private HashMap<String, FunctionWithResultOnly> mFunctionWithResultOnly;
    private HashMap<String, FunctionWithParamsAndResult> mFunctionWithParamsAndResult;


    public FunctionManager addFunction(FunctionNoParamsNoResult function) {
        mFunctionNoParamsNoResult.put(function.functionName, function);
        return this;
    }


    public FunctionManager addFunction(FunctionWithParamsAndResult function) {
        mFunctionWithParamsAndResult.put(function.functionName, function);
        return this;
    }

    public void invokeFunction(String functionName) {
        if (TextUtils.isEmpty(functionName)) {
            return;
        }
        if (mFunctionNoParamsNoResult != null) {
            FunctionNoParamsNoResult function = mFunctionNoParamsNoResult.get(functionName);
            if (function != null) {
                function.function();
            }

            if (function == null) {
                try {
                    throw new FunctionException("has no this function:" + functionName);
                } catch (FunctionException e) {
                    e.printStackTrace();
                }
            }
        }

//        if (mFunctionWithParamsAndResult != null) {
//            FunctionWithParamsAndResult function = mFunctionWithParamsAndResult.get(functionName);
//            if (function != null) {
//                function.function("");
//            }
//
//            if (function == null) {
//                try {
//                    throw new FunctionException("has no this function:" + functionName);
//                } catch (FunctionException e) {
//                    e.printStackTrace();
//                }
//            }
//        }


    }

}
