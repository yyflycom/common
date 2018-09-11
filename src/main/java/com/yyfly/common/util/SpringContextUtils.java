package com.yyfly.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * spring context 工具
 *
 * @author : yyfly / developer@yyfly.com
 * @date   : 2018-08-08
 */
@Component
public class SpringContextUtils implements ApplicationContextAware, DisposableBean {


    private static ApplicationContext applicationContext = null;

    private static Logger logger = LoggerFactory.getLogger(SpringContextUtils.class);

    /**
     * 取得存储在静态变量中的ApplicationContext.
     *
     * @return the application context
     * @author : yyfly / 2018-08-08
     */
    public static ApplicationContext getApplicationContext() {
        assertContextInjected();
        return applicationContext;
    }

    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     *
     * @param <T>  the type parameter
     * @param name the name
     * @return the t
     * @author : yyfly / 2018-08-08
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        assertContextInjected();
        return (T) applicationContext.getBean(name);
    }

    /**
     * 获取实例
     *
     * @param <T>  the type parameter
     * @param name Bean名称
     * @param type Bean类型
     * @return T 实例
     * @author : yyfly / 2018-08-08
     */
    public static <T> T getBean(String name, Class<T> type) {

        return applicationContext.getBean(name, type);
    }

    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     *
     * @param <T>          the type parameter
     * @param requiredType the required type
     * @return the t
     * @author : yyfly / 2018-08-08
     */
    public static <T> T getBean(Class<T> requiredType) {
        assertContextInjected();
        return applicationContext.getBean(requiredType);
    }

    /**
     * 清除SpringContextHolder中的ApplicationContext为Null.
     *
     * @author : yyfly / 2018-08-08
     */
    public static void clearHolder() {
        logger.debug("清除Spring Context Holder中的ApplicationContext:" + applicationContext);
        applicationContext = null;
    }

    /**
     * 实现ApplicationContextAware接口, 注入Context到静态变量中.
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        logger.debug("注入ApplicationContext到SpringContextHolder:{}", applicationContext);

        if (SpringContextUtils.applicationContext != null) {
            logger.warn("SpringContextHolder中的ApplicationContext被覆盖, 原有ApplicationContext为:" + SpringContextUtils.applicationContext);
        }

        SpringContextUtils.applicationContext = applicationContext;
    }

    /**
     * 实现DisposableBean接口, 在Context关闭时清理静态变量.
     */
    @Override
    public void destroy() {
        SpringContextUtils.clearHolder();
    }

    /**
     * 检查ApplicationContext不为空.
     *
     * @author : yyfly / 2018-08-08
     */
    private static void assertContextInjected() {
        validState(applicationContext != null, "Spring Context属性未注入.");
    }

    private static void validState(final boolean expression, final String message, final Object... values) {
        if (!expression) {
            throw new IllegalStateException(String.format(message, values));
        }
    }

}
