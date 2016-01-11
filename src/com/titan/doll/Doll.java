package com.titan.doll;

import java.util.ArrayList;

import com.titan.arithmetic.Weight;
import com.titan.arithmetic.WeightUtil;

public class Doll 
{
	private String _name;

	private ArrayList<Behavior> _behaviors;
	
	private int _manipulation;
	
	private BehaviorInterpreter _interpreter;
	
	public Doll(String name, IOperator operator) 
	{
		_name = name;
		
		_behaviors = new ArrayList<Behavior>();
		
		_interpreter = BehaviorInterpreter.getInstance();
	}
	
	public void doSometing()
	{
		Activity activity;
		ArrayList<Activity> activites = new ArrayList<Activity>();
		
		for (int i = 0; i < _behaviors.size(); i++) 
		{
			Behavior behavior = _behaviors.get(i);
			activity = _interpreter.parse(behavior);
			if (activity != null)
			{
				activites.add(activity);
			}
		}
		
		if (activites.size() > 0)
		{
			Weight weight = WeightUtil.random(WeightUtil.calculateWeights(activites, "weight"));
			activity = (Activity) weight.data;
			activity.action.doAction();
			System.out.println(activity.behavior.getName());
		}
		
	}
	
	public void addBehavior(Behavior behavior)
	{
		_behaviors.add(behavior);
	}
	
	public int getWeightValue()
	{
		return _manipulation;
	}
	
	public int getManipulation()
	{
		return _manipulation;
	}
	
	public String getName()
	{
		return _name;
	}
	
	public void setManipulation(int value)
	{
		_manipulation = value;
	}
}
