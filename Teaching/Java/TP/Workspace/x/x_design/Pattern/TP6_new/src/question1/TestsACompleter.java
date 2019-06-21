package question1;


public class TestsACompleter extends junit.framework.TestCase{
	private Memoire m;
	private Variable i,j;
	private VisiteurExpression<Integer> ve;
	private VisiteurExpression<String>  vp,vi;
	
	
	public void setUp(){
	  m  = new Memoire();
	  i  = new Variable(m,"i",3);
	  j = new Variable(m,"j",5);
	  ve = new VisiteurEvaluation( m);
	  vi = new VisiteurInfixe( m);
	  vp = new VisiteurPostfixe( m);
	  assertNotNull(i);assertNotNull(j);
	  assertNotNull(ve);assertNotNull(vp);assertNotNull(vi);
	}
	
	
	public void testUneAddition(){
		  Expression expr = new Addition(new Constante(3), new Constante(2));
	    assertEquals(" 3+2 != 5 ?, curieux ",5,expr.accepter(ve).intValue());
	}
	
	public void testMultiplication(){
	  // a completer
	  // a completer
	  // a completer
	}

	public void testSoustraction(){
	  // a completer
	  // a completer
	  // a completer 
	}
	
	public void testDivision(){
	  // a completer
	  // a completer
	  // a completer
	  
	  try{
	    new Division(i,new Constante(0)).accepter(ve);
	    fail("division par zero ? possible ");
	   }catch(ArithmeticException ae){
	   }
	 }
	 
	 public void testVisiteurInfixe(){
	  // a completer
	  // a completer
	  // a completer
	 }
	
	 public void testVisiteurPostfixe(){
	  // a completer
	  // a completer
	  // a completer
	 }
	 
	 
	 	// a completer
	  // a completer
	  // a completer

}
