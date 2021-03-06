/* Generated by AN DISI Unibo */ 
/*
This code is generated only ONCE
*/
package it.unibo.myled;
//import it.unibo.bls.raspberry.components.adhoc.DeviceLedPi4jAdHoc;
import it.unibo.myled.LedWithGui;
import it.unibo.is.interfaces.IOutputEnvView;
import it.unibo.qactors.QActorContext;

public class Myled extends AbstractMyled { 
	
	private BlinkLedCommander myBLC;
	private   LedWithRasp ledPi4j ;

	private LedWithGui    ledGui ;
	private boolean withGui = false ;
	
	public Myled(String actorId, QActorContext myCtx, IOutputEnvView outEnvView )  throws Exception{
		super(actorId, myCtx, outEnvView);
	}
	
	public void createGuiLed(int pinNum ){
		withGui = true;
		ledGui = new LedWithGui(  );		
		outEnvView.getEnv().addPanel(ledGui);
	}
	
	public void createPi4jLed(int pinNum ){
		ledPi4j = new LedWithRasp(pinNum);
	}
	
	public void switchLedState(){
		if (!withGui){
			ledPi4j.ledSwitch();
			System.out.println("LED is " + (ledPi4j.isOn()?"ON":"OFF"));
		}
		else {
			ledGui.ledSwitch();
		}
	}
	
	public void startBlink() {
		myBLC = new BlinkLedCommander();
		myBLC.start();
		
	}
	
	public void stopBlink() {
		myBLC.stop();
		ledGui.turnOff();
	}
	
	//gestore blink
	class BlinkLedCommander extends Thread {
		@Override
		public void run() {
			while(true) {
				ledGui.ledSwitch();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	}
	
}