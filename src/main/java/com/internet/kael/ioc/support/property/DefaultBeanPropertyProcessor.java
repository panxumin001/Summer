// Copyright 2020 ALO7 Inc. All rights reserved.

package com.internet.kael.ioc.support.property;

import com.google.common.base.Preconditions;
import com.internet.kael.ioc.core.BeanFactory;
import com.internet.kael.ioc.model.PropertyArgsDefinition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author Kael He (kael.he@alo7.com)
 * @since 7.0
 */
public class DefaultBeanPropertyProcessor implements BeanPropertyProcessor {
    private static final DefaultBeanPropertyProcessor INSTANCE = new DefaultBeanPropertyProcessor();
    public static DefaultBeanPropertyProcessor getInstance() {
        return INSTANCE;
    }

    @Override
    public void propertyProcessor(
            BeanFactory beanFactory, Object instance, List<PropertyArgsDefinition> propertyArgsDefinitions) {
        Preconditions.checkNotNull(beanFactory);
        Preconditions.checkNotNull(instance);
        if (CollectionUtils.isEmpty(propertyArgsDefinitions)) return;
        for (PropertyArgsDefinition argsDefinition : propertyArgsDefinitions) {
            if (StringUtils.isNoneEmpty(argsDefinition.getRef())) {
                RefBeanPropertyProcessor.getInstance().propertyProcessor(beanFactory, instance, argsDefinition);
            } else {
                ValueBeanPropertyProcessor.getInstance().propertyProcessor(beanFactory, instance, argsDefinition);
            }
        }
    }
}
