package com.titan.doll;

public class BindingAction 
{
	public int code;
	public String name;
	public Object func;
	
	public BindingAction(String name, Object func) 
	{
		this.name = name;
		this.func = func;
	}
}
