package com.zhanglin.frameself.inject;

import android.annotation.SuppressLint;
import android.support.annotation.StringRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhanglin on 2017/8/1.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface StringInject {
    @SuppressLint("SupportAnnotationUsage") @StringRes int value();
}
