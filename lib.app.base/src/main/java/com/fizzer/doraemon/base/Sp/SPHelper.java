package com.fizzer.doraemon.base.Sp;

import android.content.Context;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

/**
 * Created by Fizzer on 2019/5/30.
 * Email: fizzer503@gmail.com
 * Function:
 */

public class SPHelper {

    private static final SPHelper mSPHelper = new SPHelper();
    private static Context context;
    private SecuritySharedPreference mSP;
    private SecuritySharedPreference.Editor mEditor;
    private boolean hasFirstVersion;

    public static void initialize(Context ctx) {
        context = ctx;
    }

    public static SPHelper getInstance() {
        return mSPHelper;
    }

    public void init() {
        open();
    }

    public void init(Context ctx) {
        context = ctx;
        open(context);
    }

    private void open(Context context) {
        if (mSP == null) {
            mSP = new SecuritySharedPreference(context, SPConstant.CACHE_SP_NAME, Context.MODE_PRIVATE);
            mEditor = mSP.edit();
            hasFirstVersion = mSP.getAll().size() == 0;
        }
    }

    private void open() {
        if (mSP == null) {
            mSP = new SecuritySharedPreference(context, SPConstant.CACHE_SP_NAME, Context.MODE_PRIVATE);
            mEditor = mSP.edit();
            hasFirstVersion = mSP.getAll().size() == 0;
        }
    }

    public synchronized String getString(String key, String defValue) {
        open();
        return mSP.getString(key, defValue);
    }

    public synchronized void setString(String key, String value) {
        open();
        mEditor.putString(key, value);
        mEditor.apply();
    }

    public synchronized int getInt(String key, int defValue) {
        open();
        return mSP.getInt(key, defValue);
    }

    public synchronized void setInt(String key, int value) {
        open();
        mEditor.putInt(key, value);
        mEditor.apply();
    }

    public synchronized void remove(String key) {
        open();
        mEditor.remove(key);
        mEditor.apply();
    }

    public synchronized long getLong(String key, long defValue) {
        open();
        return mSP.getLong(key, defValue);
    }

    public synchronized void setLong(String key, long value) {
        open();
        mEditor.putLong(key, value);
        mEditor.apply();
    }

    public synchronized float getFloat(String key, float defValue) {
        open();
        return mSP.getFloat(key, defValue);
    }

    public synchronized void seFloat(String key, float value) {
        open();
        mEditor.putFloat(key, value);
        mEditor.apply();
    }

    public synchronized boolean getBoolean(String key, boolean defValue) {
        open();
        if (!mSP.contains(key)) {
            setBoolean(key, defValue);
        }
        return mSP.getBoolean(key, defValue);
    }

    public synchronized void setBoolean(String key, boolean value) {
        open();
        mEditor.putBoolean(key, value);
        mEditor.apply();
    }

    public void putObject(String key, Object object) {
        open();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(baos);
            out.writeObject(object);
            String objectVal = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
            mEditor.putString(key, objectVal);
            mEditor.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public <T> T getObject(String key, Class<T> clazz) {
        open();
        if (mSP.contains(key)) {
            String objectVal = mSP.getString(key, null);
            byte[] buffer = Base64.decode(objectVal, Base64.DEFAULT);
            ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(bais);
                T t = (T) ois.readObject();
                return t;
            } catch (StreamCorruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bais != null) {
                        bais.close();
                    }
                    if (ois != null) {
                        ois.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public boolean hasEmpty() {
        return hasFirstVersion;
    }

    public void clear() {
        mEditor.clear();
        mEditor.apply();
    }
}
