package com.medium.luksrn.multitenant.profiles;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Profile;

/**
 * Anotação utilitária para representar o profile "database based"
 * 
 * @author lucas
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Profile("tenancy-datasource")
public @interface DataSourceProfile {

}
