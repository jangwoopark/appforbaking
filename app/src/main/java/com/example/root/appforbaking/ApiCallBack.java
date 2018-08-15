package com.example.root.appforbaking;

public interface ApiCallBack<T> {
    void onResponse(T result);

    void onCancel();
}
