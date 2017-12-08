package com.medium.luksrn;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.medium.luksrn.multitenant.MultitenantHolder;

/**
 * Estas são as configurações customizadas do Spring MVC.
 * 
 * @author lucas.oliveira
 *
 */
@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	 
	/**
	 * Registro de views que não necessitam de processamento em controllers.
	 */
	 @Override
     public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
     }
 
	 /**
	  * Este método registra o Interceptador {@link MultiTenantInterceptor}
	  * no Spring MVC. Sendo assim, todas as requisições aos controladores 
	  * MVC terão o tenantID setado no {@link MultitenantHolder}.
	  * 
	  */
	 @Override
	 public void addInterceptors(InterceptorRegistry registry) {
		  //registry.addInterceptor(new MultiTenantInterceptor());
	 }
	 
	
}
