package com.kaze.spring.beans.factory.impl;

import com.kaze.spring.beans.config.BeanDefinition;
import com.kaze.spring.beans.factory.BeanFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.core.ResolvableType;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleBeanFactory implements BeanFactory {
    protected final Log logger = LogFactory.getLog(getClass());

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);
    private final Map<String, String> aliasMap = new ConcurrentHashMap(16);


    @Override
    public Object getBean(String name) throws BeansException {
        return null;
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return null;
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return null;
    }

    @Override
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        return null;
    }

    @Override
    public <T> T getBean(Class<T> requiredType, Object... args) throws BeansException {
        if(requiredType == null) throw new IllegalArgumentException("requiredType必须不为null");
        return null;
    }

    @Override
    public <T> ObjectProvider<T> getBeanProvider(Class<T> requiredType) {
        return null;
    }

    @Override
    public <T> ObjectProvider<T> getBeanProvider(ResolvableType requiredType) {
        return null;
    }

    @Override
    public boolean containsBean(String name) {
        return false;
    }

    @Override
    public boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        return false;
    }

    @Override
    public boolean isPrototype(String name) throws NoSuchBeanDefinitionException {
        return false;
    }

    @Override
    public boolean isTypeMatch(String name, ResolvableType typeToMatch) throws NoSuchBeanDefinitionException {
        return false;
    }

    @Override
    public boolean isTypeMatch(String name, Class<?> typeToMatch) throws NoSuchBeanDefinitionException {
        return false;
    }

    @Override
    public Class<?> getType(String name) throws NoSuchBeanDefinitionException {
        return null;
    }

    @Override
    public Class<?> getType(String name, boolean allowFactoryBeanInit) throws NoSuchBeanDefinitionException {
        return null;
    }

    @Override
    public String[] getAliases(String name) {
        return new String[0];
    }

    public void registerBeanDeffinition(String beanName, BeanDefinition beanDefinition)
            throws Exception {
        if(!StringUtils.hasText(beanName)) throw new IllegalArgumentException("beanName不允许为空");
        if(null == beanDefinition) throw new IllegalArgumentException("BeanDefinition不允许为null");
        BeanDefinition existingDefinition = this.beanDefinitionMap.get(beanName);
        if(null!= existingDefinition) throw new IllegalArgumentException(
            "无法为bean 'beanName' 注册 BeanDefinition[" + beanDefinition + "],"+
            "因为已绑定到 [" + existingDefinition + "]。");
        if(aliasMap.containsKey(beanName)){
            String aliasedName = canonicalName(beanName);
            if (beanDefinitionMap.containsKey(aliasedName)) {
                throw new IllegalArgumentException(
                    "无法为bean 'beanName' 注册 BeanDefinition[" + beanDefinition + "],"+
                    "因为已绑定到 [" + existingDefinition + "]。");
            } else {
                throw new IllegalArgumentException("Cannot register bean definition for bean '" + beanName +
                                "' since there is already an alias for bean '" + aliasedName + "' bound.");
            }
        }
        this.beanDefinitionMap.put(beanName, beanDefinition);
    }

    //获取原始名称
    private String canonicalName(String name) {
        String canonicalName = name;
        // Handle aliasing...
        String resolvedName;
        //获取别名链的原始名称  eg: 原始名称 <-- 别名A, 别名A <-- 别名B
        do {
            resolvedName = this.aliasMap.get(canonicalName);
            if (resolvedName != null) {
                canonicalName = resolvedName;
            }
        }
        while (resolvedName != null);
        return canonicalName;
    }
    public BeanDefinition getBeanDefinition(String beanName) throws NoSuchBeanDefinitionException {
        BeanDefinition bd = this.beanDefinitionMap.get(beanName);
        if (bd == null) {
            if (logger.isTraceEnabled()) {
                logger.trace("No bean named '" + beanName + "' found in " + this);
            }
            throw new NoSuchBeanDefinitionException(beanName);
        }
        return bd;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(ObjectUtils.identityToString(this));
        sb.append(": defining beans [");
        sb.append(StringUtils.collectionToCommaDelimitedString(this.beanDefinitionNames));
        sb.append("]; ");
        org.springframework.beans.factory.BeanFactory parent = getParentBeanFactory();
        if (parent == null) {
            sb.append("root of factory hierarchy");
        }
        else {
            sb.append("parent: ").append(ObjectUtils.identityToString(parent));
        }
        return sb.toString();
    }
}
