package me.jclagache.data.mybatis.repository.core.mapping;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.expression.BeanFactoryAccessor;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.data.mapping.model.BasicPersistentEntity;
import org.springframework.data.util.TypeInformation;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SimpleMyBatisPersistentEntity<T> extends BasicPersistentEntity<T, MyBatisPersistentProperty>
        implements MyBatisPersistentEntity<T>, ApplicationContextAware {

    private final StandardEvaluationContext context;

    public SimpleMyBatisPersistentEntity(TypeInformation<T> information) {
        super(information);
        this.context = new StandardEvaluationContext();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context.addPropertyAccessor(new BeanFactoryAccessor());
        context.setBeanResolver(new BeanFactoryResolver(applicationContext));
        context.setRootObject(applicationContext);
    }
}
