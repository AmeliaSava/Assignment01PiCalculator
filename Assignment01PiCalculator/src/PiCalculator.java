import java.lang.Math;

public class PiCalculator implements Runnable{
	
	float piAccuracy;
	
	public PiCalculator(float accuracy) {
		this.piAccuracy = accuracy;
	}
	
	public void run() {
		
		double pi = 4;
		double den = 3;
		int i = 1;
		
		while(!Thread.interrupted() && (pi - Math.PI) > this.piAccuracy) {
			
			if (i % 2 == 0) {
				pi = pi + (4 / den);
			} else {
				pi = pi - (4 / den);
			}
			den = den + 2;
			i++;
		}
		
		System.out.println("Pi value claculated through Gregory-Leibniz: " + pi);
		System.out.println("Pi value:" + Math.PI);
		System.out.println("Accuracy: " + this.piAccuracy);
		
	}
	
	public static void main(String[] args) {
		
		int sleepTime = Integer.parseInt(args[1]);
		
		Thread PiCalc = new Thread(new PiCalculator(Float.parseFloat(args[0])));
		PiCalc.start();
		

		
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			System.err.println("Error: sleep main");
		}
		
		System.out.println("Timeout, interrupting thread");
		PiCalc.interrupt();
		
		try {
			PiCalc.join();
		} catch (InterruptedException e) {
			System.err.println("Error: join main");
		}

	}

}
