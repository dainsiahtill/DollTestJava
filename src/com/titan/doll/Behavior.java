package com.titan.doll;

public class Behavior 
{
	//��Ϊ����
	private String _name;
	
	//�ű�
	private String _script;
	
	//������Ŀ�ִ�г���
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
