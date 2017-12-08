package com.medium.luksrn.multitenant;

import com.medium.luksrn.multitenant.web.MultitenantHolderFilter;

/**
 * Holder que mantém uma referência ao tenant ID da Thread em execução.
 * 
 * @see MultitenantHolderFilter
 * 
 * @author lucas.oliveira
 *
 */
public class MultitenantHolder {
	
	private static final ThreadLocal<String> CONTEXT = new ThreadLocal<>();
	
	public static final String DEFAULT_TENANT = "public";
	
	static {
		CONTEXT.set(DEFAULT_TENANT);
	}
	
	public static void setTenantId(String tenantId) {
		CONTEXT.set(tenantId);
	}

	public static String getTenantId() {
		return CONTEXT.get();
	}
	
	public static void clear() {
		CONTEXT.set(null);
	}
}
