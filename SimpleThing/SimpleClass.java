public class SimpleClass{
	public static int simpleMethod(int simpleParameter){
		return ++simpleParameter;
	}
	public static int[] simpleMethod(int[] simpleParameter){
		for(int i = 0; i < simpleParameter.length; i++)
			simpleParameter[i]++;
		return simpleParameter;	
	}

}