public class Class3 implements Class0{
	private int a;
	public int f(int x){
		System.out.println("Class3");
		return a;
	}
	
	public Class3(int a){
		System.out.println("Class3 est créé");
		this.a=a;
	}
	
	public void r(){
		System.out.println("je suis dans Class3");
	}
}
