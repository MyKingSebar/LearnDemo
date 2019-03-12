package com.w.annotationcompiler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangpanfeng@nationsky.com on 18/11/5.
 *
 * TODO 把该类从AnnotationCompiler包中移出来，新建为一个android lib，可以把打开activity的方法封装进来
 */
public class Router {

    public static final String CLASSNAME = "AnnotatedClassUtil";

    private List<String> packageNames = new ArrayList<>();

    private static Router mInstance = new Router();

    private Map<String, String> map = new HashMap<>();

    private Router() {
    }

    public static Router getInstance() {
        return mInstance;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    private void saveMap(Map<String, String> map) {
        this.map.putAll(map);
    }

    public Router addModule(String packageName) {
        packageNames.add(packageName);
        return mInstance;
    }

    public void init() {
        for(String packageName : packageNames) {
            Class util = null;
            try {
                util = Class.forName(packageName + "." + CLASSNAME);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Method getAnnotatedClasses = null;//得到方法对象
            try {
                getAnnotatedClasses = util.getMethod("getAnnotatedClasses");
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            try {
                Map map = (Map)getAnnotatedClasses.invoke(util.newInstance());
                if(null != map) {
                    saveMap(map);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }


        }
    }

    public void open(String url) {

    }
}
