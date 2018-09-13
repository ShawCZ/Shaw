package com.shaw.core.app;

import android.content.Context;

import java.util.HashMap;

/**
 * Author by Shaw on 2018/9/14 00:38
 */
public final class Shaw {
    public static Configurator init(Context context){
        return Configurator.getInstance().withApplication(context);
    }

    public static HashMap<Object,Object> getConfigs(){
        return Configurator.getInstance().getConfigs();
    }

    public static Object getConfig(Enum<ConfigKey> key){
        return Configurator.getInstance().getConfig(key);
    }
}
