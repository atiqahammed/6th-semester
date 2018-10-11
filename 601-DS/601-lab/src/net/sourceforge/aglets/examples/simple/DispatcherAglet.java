package net.sourceforge.aglets.examples.simple;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.ibm.aglet.Aglet;
import com.ibm.aglet.RequestRefusedException;

import com.ibm.aglet.event.MobilityEvent;
import com.ibm.aglet.event.MobilityListener;

public class DispatcherAglet extends Aglet implements MobilityListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int run=0;
	@Override
	public void onCreation(Object init) {
		// TODO Auto-generated method stub
		System.out.println("Created!");
		setText("created");
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		run++;
		System.out.println("Tahlil");
		File curDir = new File(".");
		getAllFiles(curDir);
		System.out.println("///////////////////////////////////////////////////////////");
		System.out.println("IN:"+run);

	}
	@Override
	public void onArrival(MobilityEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("transferrer");
	}
	@Override
	public void onDispatching(MobilityEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("dispatched");
	}
	@Override
	public void onReverting(MobilityEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	private static void getAllFiles(File curDir) {

		File[] filesList = curDir.listFiles();
		for(File f : filesList){
			if(f.isDirectory())
				getAllFiles(f);
			if(f.isFile()){
				System.out.println(f.getName());
			}
		}

	}

}
