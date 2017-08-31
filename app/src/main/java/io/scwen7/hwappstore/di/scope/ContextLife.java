package io.scwen7.hwappstore.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by 解晓辉  on 2017/8/31 22:06 *
 * QQ  ：811733738
 * 作用: 限定Context的生命周期
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ContextLife {
    String value() default "";
}
