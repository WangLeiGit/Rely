package com.android.library.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.android.library.utils.Utils;

import io.realm.Realm;

/**
 * Created by dugang on 2017/3/15.Application基类
 */
@SuppressWarnings("unused")
public abstract class BaseApp extends Application {
    private static BaseApp appContext;

    @Override public void onCreate() {
        super.onCreate();
        appContext = this;
        //初始化工具类
        Utils.init(this, debugMode());
        //初始化数据库
        Realm.init(this);
    }

    @Override protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static BaseApp getAppContext() {
        return appContext;
    }

    /**
     * 设置debug模式,推荐返回app的BuildConfig.DEBUG
     */
    public abstract boolean debugMode();

    /**
     * 配置应用文件保存的根路径
     */
    public abstract String getAppRootPath();

}
