package com.utvgo.huya.beans;

import android.text.TextUtils;

import java.util.List;

public class TPageData<T> extends BaseResponse<T> {
    /**
     * "message":"success",
     * "data":Object{...},
     * "currentPage":1,
     * "code":"1",
     * "count":5,
     * "totalPage":1,
     * "currentSize":5
     */
    private int totalPage;
    private int count;
    private int currentPage;
    private int currentSize;

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public void setCurrentSize(int currentSize) {
        this.currentSize = currentSize;
    }
}
