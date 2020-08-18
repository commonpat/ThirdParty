package com.utvgo.handsome.bean;

import com.utvgo.huya.beans.BaseResponse;

import java.io.Serializable;

/**
 * @author wzb
 * @description:
 * @date : 2020/3/4 9:21
 */
public class BeanProductParam  implements Serializable {
    private Data data;
    private String code;
    private String message;
    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data{
          private String productCategoryId;
          private String svcCodes;
          private String tryBestCmbId;

        public String getProductCategoryId() {
            return productCategoryId;
        }

        public void setProductCategoryId(String productCategoryId) {
            this.productCategoryId = productCategoryId;
        }

        public String getSvcCodes() {
            return svcCodes;
        }

        public void setSvcCodes(String svcCodes) {
            this.svcCodes = svcCodes;
        }

        public String getTryBestCmbId() {
            return tryBestCmbId;
        }

        public void setTryBestCmbId(String tryBestCmbId) {
            this.tryBestCmbId = tryBestCmbId;
        }
    }
}
