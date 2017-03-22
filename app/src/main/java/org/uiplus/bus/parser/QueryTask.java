package org.uiplus.bus.parser;

import android.os.AsyncTask;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by zhangyuanxin on 2017/1/27.
 */

public class QueryTask extends AsyncTask {

    private final static String TAG = "QueryTask";

    @Override
    protected Object doInBackground(Object[] params) {
        Object result = null;
        try {

            Class clazz = (Class) params[0];
            String methodName = (String) params[1];
            Object obj = clazz.newInstance();

            if (params.length > 2) {
                String param = (String) params[2];
                Method method = clazz.getMethod(methodName, new Class[]{String.class});
                result = method.invoke(obj, param);
            } else {
                Method method = clazz.getMethod(methodName);
                result = method.invoke(obj);
            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        Log.d(TAG, result.toString());
        return result;
    }
}
