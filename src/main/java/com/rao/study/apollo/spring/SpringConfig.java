package com.rao.study.apollo.spring;

import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

@PropertySource(value = {"classpath:application.properties"})
@EnableApolloConfig
@ImportResource(locations = {"classpath:spring-context.xml"})
@Configuration
public class SpringConfig {

    private static ApplicationContext ctxt = null;

    //以下是对应公司的相关配置
//    13 studyapollo.jar -Djsse.enableSNIExtension=false -Dfile.encoding=UTF-8 -Xms2816m -Xmx2816m -Xmn938m -XX:SurvivorRatio=8 -XX:GCTimeRatio=19 -XX:ParallelGCThreads=2 -XX:+DisableExplicitGC -XX:+UseFas
//    tAccessorMethods -Xnoclassgc -XX:+ParallelRefProcEnabled -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction
//=70 -XX:+CMSClassUnloadingEnabled -XX:SoftRefLRUPolicyMSPerMB=0 -XX:+PrintGC -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=5 -XX:GCLogFileSize=256M -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX
//:+PrintHeapAtGC -Xloggc:/data/logs/studyapollo/gc.log -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/data/logs/studyapollo/oom.dump -Denv=fat -Dapollo.cluster=default -Dnamespace=Raos.pubConfig -D
//            fat_meta=http://apollo-config-fat.service.dev.rao.study.co:8080

    @Bean
    public TestJavaConfigBean getTestJavaConfigBean(){
        return new TestJavaConfigBean();
    }

    public static void main(String[] args) throws Exception {//-Denv=FAT -Dfat_meta=http://localhost:8088
        ctxt = new AnnotationConfigApplicationContext(SpringConfig.class);

        TestJavaConfigBean testJavaConfigBean = ctxt.getBean(TestJavaConfigBean.class);
        System.out.println("timeout:"+testJavaConfigBean.getTimeOut());

        System.out.println("timeout:"+testJavaConfigBean.getTimeOut());

        XmlBean xmlBean = ctxt.getBean(XmlBean.class);
        System.out.println("xmlBean:xmlName " + xmlBean.getXmlName());

        System.in.read();
    }

    @ApolloConfigChangeListener
    public void onChange(ConfigChangeEvent changeEvent){
        for (String key:changeEvent.changedKeys()){
            System.out.println(key + changeEvent.getChange(key).getNewValue());
        }
        TestJavaConfigBean testJavaConfigBean = ctxt.getBean(TestJavaConfigBean.class);
        System.out.println("timeout:"+testJavaConfigBean.getTimeOut());
        System.out.println("name:"+testJavaConfigBean.getName());

        XmlBean xmlBean = ctxt.getBean(XmlBean.class);
        System.out.println("xmlScope:"+xmlBean.getXmlScope());
    }

}
