package Model;

public class Temp {
	String txt;
	float degree;
	
	public Temp(String txt, float degree)
	{
		this.txt = txt;
		this.degree = degree;
	}
	
	public String GetTxt()
	{
		return this.txt;
	}
	
	public String GetDegree()
	{
		if(this.degree == -1) return "--.-";
		else return Float.toString(this.degree);
	}
	
	public float GetDegreeF()
	{
		return this.degree;
	}
	
	public void SetDegreeF(float temp)
	{
		this.degree = temp;
	}
}
