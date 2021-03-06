package com.zenglb.framework.persistence;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;


/**
 * 存储key-value 数据，支持加密
 * SharedPreferencesDao 就是操作SP
 * Created by anylife.zlb@gmail.com on 2016/11/8.
 */

// TODO: 2018/1/12  最好还是和不同的账号分开来，和DAOSession 一样
public class SPDao {
    private static final String TAG = SPDao.class.getSimpleName();
    private static final String SharedPreferencesName = "AAAAAAA-Vanke";

    private SharedPreferences sharedPreferences;

    /**
     * MODE_MULTI_PROCESS  will not work fine
     */
    public SPDao(Context mContext) {
        if (mContext != null) {
            sharedPreferences = mContext.getSharedPreferences(SharedPreferencesName, Context.MODE_PRIVATE);
        } else {
            Log.e(TAG, "WARMIKNG! You must initSharePrefenceDao in your Application ！");
        }
    }


    /**
     The best way of storing double values in SharedPreferences without losing precision is:
     Transform to bit representation to store it as long:
     prefsEditor.putLong("Latitude", Double.doubleToLongBits(location.getLatitude()));
     To retrieve, transfrom from bit representation to double:
     double latitude = Double.longBitsToDouble(prefs.getLong("Latitude", 0);
     */

    /**
     * if type of defvalue is not equal clazz
     *
     * @param defValue
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getData(String key, @NonNull Object defValue, @NonNull Class<T> clazz) {
        T t = null;
        if (!defValue.getClass().getSimpleName().equals(clazz.getSimpleName())) {
            throw new UnsupportedOperationException("defValue type does not equals whit clazz "); //转换将失败！
        }

        switch (clazz.getSimpleName()) {
            case "String":
                t = (T) sharedPreferences.getString(key, (String) defValue);
                break;
            case "Integer":
                t = (T) (Integer) sharedPreferences.getInt(key, (Integer) defValue);
                break;
            case "Float":
                t = (T) (Float) sharedPreferences.getFloat(key, (Float) defValue);
                break;
            case "Long":
                t = (T) (Long) sharedPreferences.getLong(key, (Long) defValue);
                break;
            case "Boolean":
                t = (T) (Boolean) sharedPreferences.getBoolean(key, (Boolean) defValue);
                break;
        }
        return t;
    }


    /**
     * @param key
     * @param defaultObject
     * @return
     */
    public Object get(String key, Object defaultObject) {

        if (defaultObject instanceof String) {
            return sharedPreferences.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sharedPreferences.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sharedPreferences.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sharedPreferences.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sharedPreferences.getLong(key, (Long) defaultObject);
        }
        return null;
    }


    /**
     * 存储数据
     *
     * @param key
     * @param value
     */
    public void saveData(String key, @NonNull Object value) {
        if (value instanceof String) {
            sharedPreferences.edit().putString(key, (String) value).commit();
        } else if (value instanceof Integer) {
            sharedPreferences.edit().putInt(key, (Integer) value).commit();
        } else if (value instanceof Float) {
            sharedPreferences.edit().putFloat(key, (Float) value).commit();
        } else if (value instanceof Long) {
            sharedPreferences.edit().putLong(key, (Long) value).commit();
        } else if (value instanceof Boolean) {
            sharedPreferences.edit().putBoolean(key, (Boolean) value).commit();
        }
    }

}