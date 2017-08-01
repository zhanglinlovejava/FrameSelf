package com.zhanglin.frameself.inject;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by zhanglin on 2017/7/31.
 */

public class ViewUtilsTest {
    public static void inject(final Activity activity) {
        /*
        通过字节码获取activity中所有的字段，在获取filed的时候一定要使用getDeclaredFields（），
        因为只有该方法才能获取到任何权限修饰的filed，包括私有的。
         */
        Class clazz = activity.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        //一个activity中可能有多个field，因此遍历
        for (int i = 0; i < declaredFields.length; i++) {
            Field field = declaredFields[i];
            //设置为可访问，暴力反射，就算是私有的也能访问到。
            field.setAccessible(true);
            //获取到字段上面的注解对象
            StringInject annString = field.getAnnotation(StringInject.class);
            if (annString != null) {
                int stringRes = annString.value();
                String text = activity.getResources().getString(stringRes);
                try {
                    field.set(activity, text);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            ViewInject annotation = field.getAnnotation(ViewInject.class);
            //一定对annotation 是否为空进行判断，因为并不是所有的field都有我们想要的注解。
            if (annotation != null) {
                //获取注解中的值
                int id = annotation.value();
                //获取控件
                View view = activity.findViewById(id);
                //将该控件设置给field对象
                try {
                    field.set(activity, view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }


        /**
         *
         获取所有的方法*/
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (int i = 0; i < declaredMethods.length; i++) {
            final Method method = declaredMethods[i];
            //获取方法上面的注解对象
            OnClick annotation = method.getAnnotation(OnClick.class);
            if (annotation == null) {
                //如果该方法上没有注解，循环下一个
                continue;
            }
            //获取注解中的数据，因为可以给多个button绑定点击事件，因此定义注解类时使用的是int[] 作为数据类型。
            int[] value = annotation.value();
            for (int j = 0; j < value.length; j++) {
                int id = value[j];
                final View button = activity.findViewById(id);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            method.invoke(activity, button);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }


    }
}
