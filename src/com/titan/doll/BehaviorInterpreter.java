package com.titan.doll;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BehaviorInterpreter
{
	private static BehaviorInterpreter _instance;
	
	private ActionRegister _register;
	
	private IOperator _operator;
	
	public BehaviorInterpreter() 
	{
		_register = ActionRegister.getInstance();
	}
	
	public void init(IOperator operator)
	{
		_operator = operator;
	}
	
	public static BehaviorInterpreter getInstance()
	{
		if (_instance == null)
		{
			_instance = new BehaviorInterpreter();
		}
		
		return _instance;
	}
	
	public Activity parse(Behavior behavior)
	{
		if (_operator == null)
		{
			throw new Error("并没有初始化操作对象，无法执行。");
		}
		Activity result = null;
		
		ByteArray bytes = behavior.getBytes();
		bytes.position = 0;
		
		int code = bytes.readByte() & 0xff;
		int actionCode = bytes.readInt();
		int love = bytes.readByte() & 0xff;
		BindingAction action = _register.getActionAt(actionCode);
		
		switch (code) 
		{
			case BehaviorCode.IN_TIME:		
				
				int hourLower = bytes.readByte() & 0xff;
				int minLower = bytes.readByte() & 0xff;
				int hourUpper = bytes.readByte() & 0xff;
				int minUpper = bytes.readByte() & 0xff;
				
				if (isInTime(hourLower, minLower, hourUpper, minUpper))
				{
					result = new Activity();
					result.action = action;
					result.behavior = behavior;
					result.weight = love;
				}
			break;
			case BehaviorCode.ALL_TIME:
				result = new Activity();
				result.action = action;
				result.behavior = behavior;
				result.weight = love;
			break;
			default:
		}
		
		return result;
	}
	
	private boolean isInTime(int hl, int ml, int hu, int mu)
	{
		String time = _operator.getCurrentTime();
		
		Matcher matcher = Pattern.compile("([\\d]{1,2}):([\\d]{1,2})").matcher(time);
		
		if (matcher.find() && matcher.groupCount() == 2)
		{
			int lowerTime = hl * 60 + ml;
			int upperTime = hu * 60 + mu;
			int hour = Integer.valueOf(matcher.group(1));
			int min = Integer.valueOf(matcher.group(2));
			int currentTime = hour * 60 + min;
			if (currentTime >= lowerTime && currentTime < upperTime)
			{
				return true;
			}
		}
		
		return false;
	}
	
	
}
