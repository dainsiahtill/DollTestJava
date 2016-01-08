package com.titan.doll;

import java.util.HashMap;

public class ActionRegister 
{
	private static ActionRegister _instance;
	
	private int _actionCode;
	private HashMap<Integer, BindingAction> _code2ActionHash;
	private HashMap<String, Integer> _name2CodeHash;
	
	public ActionRegister() 
	{
		init();
	}
	
	private void init()
	{
		_actionCode = 0x1;
		
		_name2CodeHash = new HashMap<String, Integer>();
		_code2ActionHash = new HashMap<Integer, BindingAction>();
		
	}
	
	public static ActionRegister getInstance()
	{
		if (_instance == null)
		{
			_instance = new ActionRegister();
		}
		return _instance;
	}
	
	public int getIncreaseCode()
	{
		return _actionCode++;
	}
	
	public BindingAction getActionAt(int code)
	{
		return _code2ActionHash.get(code);
	}
	
	public int getActionCode(String actionName)
	{
		if (_name2CodeHash.get(actionName) == null)
		{
			return 0;
		}
		return (int)_name2CodeHash.get(actionName);
	}
	
	public void registerAction(BindingAction action)
	{
		int actionCode = getIncreaseCode();
		action.code = actionCode;
		
		_code2ActionHash.put(actionCode, action);
		_name2CodeHash.put(action.name, actionCode);
	}
	
	
	public static void register(BindingAction action)
	{
		getInstance().registerAction(action);
	}
}
