package tp4;

public class Thread1 implements Runnable {

	@Override
	public void run() {
		while ( true ) {

			while(Exercice1.turn==2)
				try{
					Thread.sleep(10);
			}catch(Exception e){}

			while(Exercice1.turn==1)
				try{
					Thread.sleep(10);
				}catch(Exception e){}

			System.out.println("Thread 1");

			Exercice1.turn=2;
		}
	}
}