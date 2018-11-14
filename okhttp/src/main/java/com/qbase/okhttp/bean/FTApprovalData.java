package com.qbase.okhttp.bean;

import java.util.List;

public class FTApprovalData {

    private int rowCount;
    private List<FTApprovalParams> items;

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public List<FTApprovalParams> getItems() {
        return items;
    }

    public void setItems(List<FTApprovalParams> items) {
        this.items = items;
    }
}
