

public class Process extends Thread {
	Semaphores s = new Semaphores();
	
	public int processID;
    ProcessState status=ProcessState.New;	

	
	public Process(int m) {
		processID = m;
	}
	@Override
	public void run() {	
		switch(processID)
		{
		case 1:process1();break;
		case 2:process2();break;
		case 3:process3();break;
		case 4:process4();break;
		case 5:process5();break;
		}
	}
	private void process1() {
		
		s.semReadWait(this);
		s.semPrintWait(this);
		s.semInputWait(this);
		OperatingSystem.printText("Enter File Name: ");
		OperatingSystem.printText(OperatingSystem.readFile(OperatingSystem.TakeInput()));
		s.semPrintPost(this);
		s.semInputPost(this);
		s.semReadPost(this);
		setProcessState(this,ProcessState.Terminated);
		}
	
	private void process2() {
		s.semPrintWait(this);
		s.semInputWait(this);
		s.semWriteWait(this);
		OperatingSystem.printText("Enter File Name: ");
		String filename= OperatingSystem.TakeInput();
		//s.semPrintPost(this);
		//s.semInputPost(this);
		//s.semPrintWait(this);
		OperatingSystem.printText("Enter Data: ");
		
		//s.semInputWait(this);
		String data= OperatingSystem.TakeInput();
//		s.semPrintPost(this);
//		s.semInputPost(this);
		
	//	s.semWriteWait(this);
		OperatingSystem.writefile(filename,data);
		s.semWritePost(this);
		s.semPrintPost(this);
		s.semInputPost(this);
		setProcessState(this,ProcessState.Terminated);
		}
	private void process3() {
		int x=0;
		s.semPrintWait(this);	
		while (x<301)
		{ 	
			OperatingSystem.printText(x+"\n");
			x++;
		}
		s.semPrintPost(this);
		setProcessState(this,ProcessState.Terminated);
		}
	private void process4() {
		int x=500;
		s.semPrintWait(this);
		while (x<1001)
		{
			OperatingSystem.printText(x+"\n");
			x++;
		}	
		setProcessState(this,ProcessState.Terminated);
		s.semPrintPost(this);
		}
	private void process5() {
		s.semPrintWait(this);
		s.semInputWait(this);
		OperatingSystem.printText("Enter LowerBound: ");	
		String lower= OperatingSystem.TakeInput();
//		s.semInputPost(this);
//		s.semPrintPost(this);
//		s.semPrintWait(this);
//		s.semInputWait(this);
		OperatingSystem.printText("Enter UpperBound: ");
		String upper= OperatingSystem.TakeInput();
//		s.semInputPost(this);
//		s.semPrintPost(this);
		int lowernbr=Integer.parseInt(lower);
		int uppernbr=Integer.parseInt(upper);
		String data="";
		
		while (lowernbr<=uppernbr)
		{
			data+=lowernbr++ +"\n";
		}	
		OperatingSystem.writefile("P5.txt", data);
		setProcessState(this,ProcessState.Terminated);
		s.semInputPost(this);
		s.semPrintPost(this);
	}
	
	 public static void setProcessState(Process p, ProcessState s) {
		 p.status=s;
		 if (s == ProcessState.Terminated)
		 {
			 OperatingSystem.ProcessTable.remove(OperatingSystem.ProcessTable.indexOf(p));
		 }
	}
	 
	 public static ProcessState getProcessState(Process p) {
		 return p.status;
	}
}