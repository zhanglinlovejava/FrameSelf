package com.zhanglin.frameself.inject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhanglin on 2017/7/31.
 * todo 注解类 用于添加在filed上
 *
 * @Retention 用于生命该注解类生效的生命周期，有三个枚举值可以选择。
 * 1，RetentionPolicy.SOURCE 注释住保留在源码上面，编译成class的时候自动被编译器抹除。
 * 2，RetentionPolicy.CLASS  注释只保留到字节码上面，VM加载字节码时自动抹除。
 * 3，RetentionPolicy.RUNTIME 注释永久保留，可以被VM加载时加载到内存中
 * 注意：由于我们的目的是想在VM运行时对Filed上的该注解进行反射操作，因此Retention值必须设置为RUNTIME
 * @Target 用于指定该注解可以声明在哪些成员上面，常见的值有FIELD和Method，
 * 由于我们的当前注解类是想声明在Filed上面
 * 因此这里设置为ElementType.FIELD。
 * 注意：如果@Target值不设置，则默认可以添加到任何元素上，不推荐这么写。
 * @interface 是声明注解类的组合关键字。
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewInject {
    public abstract int value();
}
