package com.rao.study.apollo.javaApi;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;

public class APITest {
    public static void main(String[] args) throws Exception {//运行这个需要设置jvm参数-Denv=DEV -Ddev_meta=http://localhost:8080,apollo会自动去获取这些配置信息,并定时请求
//        Config config = ConfigService.getAppConfig(); //config instance is singleton for each namespace and is never null
//        String someKey = "timeout";
//        String someDefaultValue = "someDefaultValueForTheKey";
//        String value = config.getProperty(someKey, someDefaultValue);
//        System.out.println("timeout="+value);

        Config config = ConfigService.getAppConfig(); //config instance is singleton for each namespace and is never null
        config.addChangeListener(new ConfigChangeListener() {
            @Override
            public void onChange(ConfigChangeEvent changeEvent) {
                System.out.println("Changes for namespace " + changeEvent.getNamespace());
                for (String key : changeEvent.changedKeys()) {
                    ConfigChange change = changeEvent.getChange(key);
                    System.out.println(String.format("Found change - key: %s, oldValue: %s, newValue: %s, changeType: %s", change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType()));
                }
            }
        });

        System.in.read();
    }
}
