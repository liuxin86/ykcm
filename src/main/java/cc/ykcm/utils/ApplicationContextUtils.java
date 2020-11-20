package cc.ykcm.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 用于 获取 bean对象
 * @author admin
 */
@Component
public class ApplicationContextUtils implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        ApplicationContextUtils.context = context;
    }

    /**
     * 根据 bean名称 获取指定对象
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName){
        return context.getBean(beanName);
    }
}
