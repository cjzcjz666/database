
package socket;

 

import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStream;

import java.io.InputStreamReader;

import java.io.OutputStream;

import java.io.PrintWriter;

import java.net.Socket;

import java.net.UnknownHostException;
import java.util.concurrent.CountDownLatch;

 

/*

 * ¿Í»§¶Ë

 */

public class Client {

	public static void main(String[] args) throws InterruptedException {
		final CountDownLatch doneSignal = new CountDownLatch(1);
		
		int num=100000;
		for(int i=0;i<num;i++) {
			mythread tt= new mythread(doneSignal);		
			Thread t=new Thread(tt);
			t.start();
			
		}
			
		doneSignal.countDown();
		
		

		

	}

	
	

}

