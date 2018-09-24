package com.shaw.core.app;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * Author by Shaw on 2018/9/14 00:40
 */
public class Configurator {
    private static final HashMap<Object, Object> SHAW_CONFIGS = new HashMap<>();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

    private Configurator() {
        SHAW_CONFIGS.put(ConfigKey.CONFIG_READY, false);
    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    public HashMap<Object, Object> getConfigs() {
        return SHAW_CONFIGS;
    }

    public final Configurator withApiHost(String host) {
        SHAW_CONFIGS.put(ConfigKey.API_HOST, host);
        return this;
    }

    public final Configurator withApplication(Context context) {
        SHAW_CONFIGS.put(ConfigKey.APPLICATION_CONTEXT, context);
        return this;
    }

    public final Configurator withIntercepter(Interceptor intercepter) {
        INTERCEPTORS.add(intercepter);
        SHAW_CONFIGS.put(ConfigKey.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    public final Configurator withInterceptera(ArrayList<Interceptor> intercepters) {
        INTERCEPTORS.addAll(intercepters);
        SHAW_CONFIGS.put(ConfigKey.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    public final void config() {
        SHAW_CONFIGS.put(ConfigKey.CONFIG_READY, true);
    }

    private void checkConfiguration() {
        final boolean isReady = (boolean) SHAW_CONFIGS.get(ConfigKey.CONFIG_READY);
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready, call configure");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfig(Enum<ConfigKey> key) {
        checkConfiguration();
        return (T) SHAW_CONFIGS.get(key);
    }

}
