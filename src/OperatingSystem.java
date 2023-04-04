import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.Semaphore;
public class OperatingSystem {
	
public static ArrayList<Thread> ProcessTable;

//	public static int activeProcess= 0;
	//system calls:
	// 1- Read from File
	@SuppressWarnings("unused")
	public static String readFile(String name) {
		String Data="";
		File file = new File(name);
	 try {
		Scanner scan = new Scanner(file);
		while (scan.hasNextLine())
		{
			Data+= scan.nextLine()+"\n";
		}
		scan.close();
	} catch (FileNotFoundException e) {
		System.out.println(e.getMessage());
	}
		return Data;
	}
	
	// 2- Write into file
	@SuppressWarnings("unused")
	public static void writefile(String name, String data) {
		try
		{
			BufferedWriter BW = new BufferedWriter(new FileWriter(name));
			BW.write(data);
			BW.close();
		} 
		catch (IOException e) 
		{
			System.out.println(e.getMessage());
		}

	}
	//3- print to console
	@SuppressWarnings("unused")
	public static void printText(String text) {

		System.out.println(text);
		
	}
	
	//4- take input
	
	@SuppressWarnings("unused")
	public static String TakeInput() {
		Scanner in= new Scanner(System.in);
		String data = in.nextLine();
		return data;
		
	}
	
	private static void createProcess(int processID){
		Process p = new Process(processID);
		ProcessTable.add(p);
		Process.setProcessState(p,ProcessState.Ready);
		p.start();
	}
	public static void Scheduler(int p1,int p2,int p3,int p4 ,int p5) {
		Queue<Integer> processQueue = new LinkedList<Integer>();
        processQueue.add(p1);
        processQueue.add(p2);
        processQueue.add(p3);
        processQueue.add(p4);
        processQueue.add(p5);
        while(!processQueue.isEmpty()) {
        	createProcess(processQueue.remove());
        }
		
	}
	public static void main(String[] args) {
   		ProcessTable = new ArrayList<Thread>();
   		// The Scheduling algorithm is first come first serve
   		// For Example  process 3 will execute first then process 2
          Scheduler(1,3,5,5,5);
	}
}




