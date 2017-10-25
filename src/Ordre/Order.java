package Ordre;

public abstract class Order {
	public static int ORDRE_TYPE_VEILLE = 0;
	public static int ORDRE_TYPE_CONSIGNE = 1;
	
	private static int NombreBitEnvoie = 7;
	private static int tramesLength[] = { 5, 2 };
	
	public static int OrdreToSend(int trameValue[])
	{
		int nbBitRestant = NombreBitEnvoie;
		int trameToSend = 0;// = OrdreType<<getDecalage(0);
		for(int i = 0; i<tramesLength.length; i++)
		{
			nbBitRestant -= tramesLength[i];
			trameToSend += trameValue[i]<<nbBitRestant;
		}
		return trameToSend;
	}
}
