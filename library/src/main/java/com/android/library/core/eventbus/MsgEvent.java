package com.android.library.core.eventbus;

import android.os.Bundle;

/**
 * Created by dugang on 2017/3/20.EventBus的消息事件
 */

public class MsgEvent {
    private int code;
    private String msg;
    private Bundle bundle = new Bundle();

    public MsgEvent() {
    }

    public MsgEvent(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void putBundle(Bundle bundle) {
        this.bundle.putAll(bundle);
    }

    public Bundle getBundle() {
        return bundle;
    }
}
