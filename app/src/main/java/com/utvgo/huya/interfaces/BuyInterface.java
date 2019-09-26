package com.utvgo.huya.interfaces;

public interface BuyInterface {
    public void auth(String vodID, boolean needShowBuy);

    public void authOnly();

    public void showBuy(String vodID);

    public void authFinish(boolean hadBuy, boolean needShowBuy);
}
