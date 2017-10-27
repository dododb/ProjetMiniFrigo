package Model;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

public class Model extends Observable implements IModel {
	private Mode currentMode;
	private Can can;
	private ChangeConsigne change;
	
	private Temp tempConsigne;
	private Temp tempCan;
	private Temp tempFridge;
	private Temp tempModule;
	
	boolean risqueCondensation = false;
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
		if(this.tempConsigne.GetDegreeF() < 30) this.tempConsigne.SetDegreeF(this.tempConsigne.GetDegreeF() + 1);
		this.NotifyObserver();
	}

	public void DecrementConsigne() {
		if(this.tempConsigne.GetDegreeF() > 15) this.tempConsigne.SetDegreeF(this.tempConsigne.GetDegreeF() - 1);
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
	
	@Override
	public void TranslateDataFromArduino(String data)
	{
		/*private Temp tempConsigne;
		private Temp tempCan;
		private Temp tempFridge;
		private Temp tempModule;*/
		String[] arrayData = data.split(":"); 
		if(arrayData.length == 6)
		{
			tempFridge.degree = Float.parseFloat(arrayData[3]);
			tempModule.degree = Float.parseFloat(arrayData[4]);
			tempCan.degree = Float.parseFloat(arrayData[5]);
			if(point_Rosée(Float.parseFloat(arrayData[2]), tempFridge.degree) >= tempModule.degree)
			{
				if(!risqueCondensation) this.popUp();
				risqueCondensation = true;
			}
			else risqueCondensation = false;
			this.setChanged();
		}
		else System.out.println("reception error");
	}
	
	private double point_Rosée(double hygrometrie, double temp)
	{
		double a = 17.27;
		double b = 237.7;
		double Tr = b * alpha(hygrometrie, temp, a, b) / (a - alpha(hygrometrie, temp, a, b));
		//System.out.println(Tr);
		return Tr;
	}
	
	private double alpha(double RH, double T, double a, double b)
	{
		return (a * T) / (b + T) + Math.log( RH/100);
	}
	
	private void popUp()
	{
		JOptionPane jop2 = new JOptionPane();

		jop2.showMessageDialog(null, "Risque de condensation", "Attention", JOptionPane.WARNING_MESSAGE);
	}
}
