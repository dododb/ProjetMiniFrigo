package View;

import java.util.Observable;
import java.util.Observer;

public class View implements IView {
	
	public View()
	{
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public Observer GetObserver()
	{
		return this;
	}
}
