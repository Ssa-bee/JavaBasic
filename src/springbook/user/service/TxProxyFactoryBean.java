package springbook.user.service;

import org.springframework.beans.factory.FactoryBean;

public class TxProxyFactoryBean implements FactoryBean<Object> {
    @Override
    public Object getObject() throws Exception {
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
