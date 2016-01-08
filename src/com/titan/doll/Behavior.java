package com.titan.doll;

public class Behavior 
{
	//行为名称
	private String _name;
	
	//脚本
	private String _script;
	
	//编译过的可执行程序
	private ByteArray _bytes;
	
	
	public Behavior(String name, String script) 
	{
		_name = name;
		_script = script;
		
		_bytes = BehaviorCompiler.compile(_script);
	}
	
	public String getName() 
	{
		return _name;
	}
	
	public ByteArray getBytes()
	{
		return _bytes;
	}
	
	public String getScript()
	{
		return _script;
	}
	
}
