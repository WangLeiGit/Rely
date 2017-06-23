package com.android.library.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.android.library.utils.Utils;
import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by dugang on 2016/05/18. Fragment基类
 */
@SuppressWarnings("unused")
public abstract class BaseFragment extends RxFragment {
    protected Context mContext;
    private Unbinder unbinder;

    @Override public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = createView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public abstract View createView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    public abstract void initData();

    /**
     * 跳转到指定的Activity
     */
    protected void skipToActivity(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(getActivity(), clazz);
        if (bundle != null) intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 跳转到指定的Activity并结束当前Activity
     */
    protected void skipToActivityAndFinish(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(getActivity(), clazz);
        if (bundle != null) intent.putExtras(bundle);
        startActivity(intent);
        getActivity().finish();
    }

    /**
     * 带返回的跳转到指定Activity
     */
    protected void skipToActivityForResult(Class<?> clazz, Bundle bundle, int requestCode) {
        Intent intent = new Intent(getActivity(), clazz);
        if (bundle != null) intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }

    /**
     * 关闭Activity
     */
    protected void closeActivity(Bundle bundle) {
        if (bundle != null) {
            Intent intent = new Intent();
            intent.putExtras(bundle);
            getActivity().setResult(Activity.RESULT_OK, intent);
        }
        getActivity().finish();
    }


    /**
     * 显示Toast消息
     */
    protected void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 获取SharedPreferences
     */
    protected SharedPreferences getSharedPreferences() {
        return Utils.getDefaultSharePreferences();
    }

    /**
     * 保存SharedPreferences，根据传入类型自动转换
     */
    protected void setSharedPreferences(String keyName, Object value) {
        if (value instanceof Boolean) {
            getSharedPreferences().edit().putBoolean(keyName, (Boolean) value).apply();
        } else if (value instanceof Integer) {
            getSharedPreferences().edit().putInt(keyName, (Integer) value).apply();
        } else if (value instanceof String) {
            getSharedPreferences().edit().putString(keyName, (String) value).apply();
        } else if (value instanceof Float) {
            getSharedPreferences().edit().putFloat(keyName, (Float) value).apply();
        } else if (value instanceof Long) {
            getSharedPreferences().edit().putLong(keyName, (Long) value).apply();
        }
    }

    /**
     * 移除SharedPreferences
     */
    protected void removeSharedPreferences(String key) {
        getSharedPreferences().edit().remove(key).apply();
    }

    /**
     * 清空SharedPreferences
     */
    protected void clearSharedPreferences() {
        getSharedPreferences().edit().clear().apply();
    }

    /**
     * 通过资源名称获取指定类型的资源ID;<br>
     */
    protected int getResId(String resName, String resType) {
        return getResources().getIdentifier(resName, resType, mContext.getPackageName());
    }

    /**
     * 关闭软键盘
     */
    protected void closeKeyboard() {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
        }
    }

}
