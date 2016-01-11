package com.titan.doll;

public class BindingAction 
{
	public int code;
	
	public String _name;
	public IAction _action;
	
	public BindingAction(String name, IAction action) 
	{
		_name = name;
		_action = action;
	}
	
	public void doAction()
	{
		if (_action != null)
		{
			_action.doAction();
		}
	}
	public String getName()
	{
		return _name;
	}
	
}
