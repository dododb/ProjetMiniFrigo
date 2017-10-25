package Ordre;

public abstract class Order {
	public static int ORDRE_TYPE_VEILLE = 0;
	public static int ORDRE_TYPE_CONSIGNE = 1;
	
	private static int tramesIndex[] = { 6, 0 };
	
	public static int OrdreToSend(int trameValue[], int OrdreType)
	{
		int trameToSend = OrdreType<<getDecalage(0);
		for(int i = 1; i<tramesIndex.length; i++)
		{
			trameToSend += trameValue[i-1]<<getDecalage(i);
		}
		return trameToSend;
	}
	private static int getDecalage(int index)
	{
		int decal = 0;
		for(int i = index; i<tramesIndex.length;i++)
		{
			decal += tramesIndex[i];
		}
		return decal;
	}
}
