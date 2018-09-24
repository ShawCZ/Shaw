package com.shaw.shaw;

import com.shaw.core.activitys.ProxyActivity;
import com.shaw.core.delegates.ShawDelegate;

/**
 * @author hx
 */
public class MainActivity extends ProxyActivity {

    @Override
    public ShawDelegate setRootDelegate() {
        return new MainDelegate();
    }
}
