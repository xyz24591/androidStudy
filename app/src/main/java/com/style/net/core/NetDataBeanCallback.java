package com.style.net.core;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.style.manager.ToastManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import example.app.MyApp;

public class NetDataBeanCallback<T> implements Callback<String> {
    protected String TAG = "NetDataBeanCallback";
    protected Class<T> clazz;
    protected TypeReference<T> type;

    public NetDataBeanCallback() {

    }

    public NetDataBeanCallback(Class<T> clazz) {
        this.clazz = clazz;
    }

    public NetDataBeanCallback(TypeReference<T> type) {
        this.type = type;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        Log.e(TAG, "response.body==" + response.body());
        NetDataBean netDataBean = JSON.parseObject(response.body(), NetDataBean.class);
        int code = netDataBean.code;
        String jsonData = netDataBean.data;
        String msg = netDataBean.msg;

        T data = null;
        if (this.clazz != null)
            data = JSON.parseObject(jsonData, this.clazz);
        if (this.type != null)
            data = JSON.parseObject(jsonData, this.type);

        if (code == NetDataBean.SUCCESS) {
            onCodeSuccess();
            onCodeSuccess(data);
            onCodeSuccess(data, msg);
        } else {
            onCodeFailure(msg);
            onCodeFailure(code, msg);
            onCodeFailure(code, data);
            if (code == NetDataBean.SERVER_ERROR) {
                ToastManager.showToast(MyApp.getAppContext(), "服务器出错了");
            } else if (code == NetDataBean.TOKEN_EXPIRED || code == NetDataBean.TOKEN_INVALID) {
                handleTokenError();
            }
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        t.printStackTrace();
        ToastManager.showToast(MyApp.getAppContext(),"请求错误");
        onCodeFailure("请求错误");
    }
    protected void onCodeSuccess() {

    }
    protected void onCodeSuccess(T data) {

    }

    protected void onCodeSuccess(T data, String msg) {

    }

    protected void onCodeFailure(String msg) {

    }

    protected void onCodeFailure(int code, String msg) {

    }
    protected void onCodeFailure(int code, T data) {

    }
    private void handleTokenError() {
        //AccountManager.getInstance().logOut();
    }

}
