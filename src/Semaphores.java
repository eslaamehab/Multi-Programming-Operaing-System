import java.util.LinkedList;
import java.util.Queue;

public class Semaphores {

	static boolean read=true;
	static boolean write=true;
	static boolean print=true;
	static boolean input=true;
	static Queue<Process> queueR = new LinkedList<Process>();
	static Queue<Process> queueW = new LinkedList<Process>();
	static Queue<Process> queueP = new LinkedList<Process>();
	static Queue<Process> queueI = new LinkedList<Process>();
	
	public void semReadWait(Process x) {
		if(read==true) { //semaphore NOT being used by other process
			read=false;
		}
		else {
			queueR.add(x); //place process in s.queue
			Process.setProcessState(x, ProcessState.Waiting); //this process is blocked
			x.suspend();
		}
		
	}
	
	public void semReadPost(Process x) {
		if(queueR.isEmpty()) {
			read=true;
		}
		else {
			(queueR.remove()).resume();; //remove from queue
			Process.setProcessState(x, ProcessState.Ready); //this process is ready  
		 
		}
		
	}
	
	public  void semWriteWait(Process x) {
		if(write==true) { //semaphore NOT being used by other process
			write=false;
		}
		else {
			queueW.add(x); //place process in s.queue
			Process.setProcessState(x, ProcessState.Waiting); //this process is blocked
		     x.suspend();
		}
	}
	public  void semWritePost(Process x) {
		if(queueW.isEmpty()) {
			write=true;
		}
		else {
			(queueW.remove()).resume();; //remove from queue
			Process.setProcessState(x, ProcessState.Ready); //this process is ready
		}
	}
	
	public void semPrintWait(Process x) {
		if(print==true) { //semaphore NOT being used by other process
			print=false;
			 
		}
		else {
			queueP.add(x); //place process in s.queue
			Process.setProcessState(x, ProcessState.Waiting); //this process is blocked
			x.suspend();
		}
	}
	
	public void semPrintPost(Process x) {
		if(queueP.isEmpty()) {
			print=true;
			
		}
		else {
			Process.setProcessState(x, ProcessState.Ready); //this process is ready
			(queueP.remove()).resume();; //remove from queue
		}
	}
	
	public  void semInputWait(Process x) {
		if(input==true) { //semaphore NOT being used by other process
			input=false;
		}
		else {
			queueI.add(x); //place process in s.queue
			Process.setProcessState(x, ProcessState.Waiting); //this process is blocked
	        x.suspend();
		}
	}
	
	public  void semInputPost(Process x) {
		if(queueI.isEmpty()) {
			input=true;
		}
		else {
			Process.setProcessState(x, ProcessState.Ready); //this process is ready
			(queueI.remove()).resume(); //remove from queue
		
		}
	}
	
}
