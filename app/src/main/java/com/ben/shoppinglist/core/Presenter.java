package com.ben.shoppinglist.core;

public interface Presenter<V extends MVPView> {

    void attachView(V v);

    void detachView();
}
