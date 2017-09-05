package com.playground.vr.vr_playground.inject;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by petnagy on 2017. 09. 01..
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
