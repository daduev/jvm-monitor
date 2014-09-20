package com.home.jvm.monitor.utils;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

@RequestScoped
public class FacesContextUtils implements Serializable {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Produces
	@FContext
	@RequestScoped
	private FacesContext facesContext = FacesContext.getCurrentInstance();
	
	
	@Produces
	@PContext
	@RequestScoped
	private RequestContext primeRequestContext = RequestContext.getCurrentInstance();
	
}
