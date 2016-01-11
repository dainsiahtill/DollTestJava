package com.titan.doll;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BehaviorCompiler 
{
	private static BehaviorCompiler _instance;
	
	public static final String CODE_IN_TIME = "intime";
	public static final String CODE_ALL_TIME = "alltime";
	
	private ActionRegister _register;
	
	public BehaviorCompiler() 
	{
		_register = ActionRegister.getInstance();
	}
	
	
	public static BehaviorCompiler getInstance()
	{
		if (_instance == null)
		{
			_instance = new BehaviorCompiler();
		}
		return _instance;
	}
	
	public static ByteArray compile(String script)
	{
		return getInstance().compileLine(script);
	}
	
	//"intime:11:40-12:50, meal";
	private ByteArray compileLine(String line)
	{
		ByteArray bytes = new ByteArray();
		
		Pattern pattern = Pattern.compile("([a-z]+):([a-zA-Z0-9]+)-([\\d]+)");
		Matcher matcher = pattern.matcher(line);
		
		boolean isFind = matcher.find();
		int groupCount = matcher.groupCount();
		if (isFind && groupCount == 3)
		{
			String cmdCode = matcher.group(1);
			String action = matcher.group(2);
			int love = Integer.valueOf(matcher.group(3));
			int code = _register.getActionCode(action);
			
			if (cmdCode.equals(CODE_IN_TIME))
			{
				pattern = Pattern.compile("([a-zA-Z0-9]+)-([\\d]+)[\\s]*,[\\s]*([\\d]{1,2}):([\\d]{1,2})-([\\d]{1,2}):([\\d]{1,2})");
				matcher = pattern.matcher(line);
				
				if (matcher.find() && matcher.groupCount() == 6)
				{
					int hourLower = Integer.valueOf(matcher.group(3));
					int minLower = Integer.valueOf(matcher.group(4));
					int hourUpper = Integer.valueOf(matcher.group(5));
					int minUpper = Integer.valueOf(matcher.group(6));
					
					if (_register.getActionCode(action) == 0)
					{
						throw new Error("±àÒë´íÎó£º" + action + "²¢Ã»ÓÐ×¢²á¡£");
					}
					
					bytes.writeByte(BehaviorCode.IN_TIME);
					bytes.writeInt(code);
					bytes.writeByte(love);
					bytes.writeByte(hourLower);
					bytes.writeByte(minLower);
					bytes.writeByte(hourUpper);
					bytes.writeByte(minUpper);
				}
			}
			if (cmdCode.equals(CODE_ALL_TIME))
			{
				bytes.writeByte(BehaviorCode.ALL_TIME);
				bytes.writeInt(code);
				bytes.writeByte(love);
			}
		}
		
		
		return bytes;
	}
	
}
