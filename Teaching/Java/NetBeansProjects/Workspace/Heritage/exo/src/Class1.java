public class Class1 implements Class0{
	Class0 a;
	Class0 b;
	public int f(int x){
		System.out.println("Class1");
		return a.f(x)+b.f(x);
	}
	
	public Class1(Class0 a,Class0 b){
		System.out.println("Class1 est créé");
		this.a=a;
		this.b=b;
	}
	
	public void r(){
	}
}
