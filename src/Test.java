import com.titan.doll.ActionRegister;
import com.titan.doll.Behavior;
import com.titan.doll.BehaviorInterpreter;
import com.titan.doll.BindingAction;
import com.titan.doll.ByteArray;
import com.titan.doll.Doll;
import com.titan.doll.IOperator;


public class Test
{

	public static void main(String[] args)
	{
		IOperator operator = new IOperator() 
		{
			
			@Override
			public String getCurrentTime() 
			{
				return "12:01";
			}
		};
		
		BehaviorInterpreter.getInstance().init(operator);
		
		Doll doll = new Doll("Heyme", operator);
		ActionRegister.register(new BindingAction("doo", new Object()));
		ActionRegister.register(new BindingAction("taobao", new Object()));
		ActionRegister.register(new BindingAction("meal", new Object()));
		
		doll.addBehavior(new Behavior("doo", "alltime:doo-3"));
		doll.addBehavior(new Behavior("meal", "intime:meal-20, 11:40-12:50"));
		
		for (int i = 0; i < 100; i++) 
		{
			doll.doSometing();
		}
		
		
	}

}
