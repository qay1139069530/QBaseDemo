package com.qbase.okhttp.bean;

import java.io.Serializable;

public class FTApprovalEntity implements Serializable{

    private String rc;
    private String error;
    private FTApprovalData ret;

    public String getRc() {
        return rc;
    }

    public void setRc(String rc) {
        this.rc = rc;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public FTApprovalData getRet() {
        return ret;
    }

    public void setRet(FTApprovalData ret) {
        this.ret = ret;
    }
}
