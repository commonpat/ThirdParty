package com.utvgo.huya.net;

public interface IVolleyRequestSuccess {
    void onSucceeded(String method, String key, Object object) throws Exception;//添加一个key，为缓存数据使用标志
}
