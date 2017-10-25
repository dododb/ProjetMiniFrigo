package Model;

import java.util.Observable;
import java.util.Observer;

public class Model extends Observable implements IModel {
	private Mode currentMode;
	private Can can;
	private ChangeConsigne change;
	
	private Temp tempConsigne;
	private Temp tempCan;
	private Temp tempFridge;
	private Temp tempModule;
	
	public Model()
	{
		this.currentMode = new Mode();
		this.can = new Can();
		this.change = new ChangeConsigne();
		
		this.tempConsigne = new Temp("Consigne", 25.0f);
		this.tempCan = new Temp("Temperature canette", -1);
		this.tempFridge = new Temp("Frigo", -1);
		this.tempModule = new Temp("Module Peltier", -1);
	}

	public Mode GetMode()
	{
		return this.currentMode;
	}
	
	public Can GetCan()
	{
		return this.can;
	}
	
	public ChangeConsigne GetChangeConsigne() {
		return this.change;
	}
	
	public Temp GettempConsigne() {
		return this.tempConsigne;
	}

	public Temp GettempCan() {
		return this.tempCan;
	}

	public Temp GettempFridge() {
		return this.tempFridge;
	}

	public Temp GettempModule() {
		return this.tempModule;
	}
	
	public void SetMode(int mode)
	{
		this.currentMode.SetMode(mode);
		this.NotifyObserver();
	}
	
	public void SetCan()
	{
		if(!this.can.GetCanB()) 
		{
			this.can.SetCanB(!this.can.GetCanB());
			this.currentMode.SetMode(1);
		}
		else
		{
			this.can.SetCanB(!this.can.GetCanB());
			this.currentMode.SetMode(2);
		}
		this.NotifyObserver();
	}
	
	public void SettempConsigne(float temp) {
		this.tempConsigne.SetDegreeF(temp);
		this.NotifyObserver();
	}

	public void SettempCan(float temp) {
		this.tempCan.SetDegreeF(temp);
		this.NotifyObserver();
	}

	public void SettempFridge(float temp) {
		this.tempFridge.SetDegreeF(temp);
		this.NotifyObserver();
	}

	public void SettempModule(float temp) {
		this.tempModule.SetDegreeF(temp);
		this.NotifyObserver();
	}

	public void IncrementConsigne() {
		if(this.tempConsigne.GetDegreeF() < 50) this.tempConsigne.SetDegreeF(this.tempConsigne.GetDegreeF() + 1);
		this.NotifyObserver();
	}

	public void DecrementConsigne() {
		if(this.tempConsigne.GetDegreeF() > 0) this.tempConsigne.SetDegreeF(this.tempConsigne.GetDegreeF() - 1);
		this.NotifyObserver();
	}
	
	public void PressedIncrementConsigne() {
		this.change.SetLessStatus();
		this.NotifyObserver();
	}
	
	public void PressedDecrementConsigne() {
		this.change.SetMoreStatus();
		this.NotifyObserver();

	}
		
	@Override
	public void AddObserver(Observer o) {
		this.addObserver(o);
	}
	
	@Override
	public void NotifyObserver() {
		this.setChanged();
		this.notifyObservers();
	}
}
