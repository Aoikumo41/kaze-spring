package com.kaze.spring.beans.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.lang.Nullable;

public class BeanDefinition {
    //默认作用域 空字串，相当于单例模式
    public static final String SCOPE_DEFAULT = "";
    //单例
    public static final String SCOPE_SINGLETON = "singleton";
    //原型
    public static final String SCOPE_PROTOTYPE  = "prototype";

    private volatile Object beanClass;

    private String scope = SCOPE_DEFAULT;

    private Boolean lazyInit; //true代表懒加载

    @Nullable
    private String[] dependsOn; //依赖的其他 Bean 名称（确保初始化顺序）。

    public void setBeanClassName(String beanClassName) {
        this.beanClass = beanClassName;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public void setLazyInit(boolean lazyInit) {
        this.lazyInit = lazyInit;
    }

    public boolean isLazyInit() {
        return (this.lazyInit != null && this.lazyInit);
    }

    public void setDependsOn(@Nullable String... dependsOn) {
        this.dependsOn = dependsOn;
    }
}
