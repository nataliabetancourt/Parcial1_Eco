import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Communication extends Thread {

	private static Communication com;

	public static Communication getInstance() {
		if(com == null) {
			com = new Communication();
			com.start();
		}
		return com;	
	}
	
	private BufferedWriter bw;
	private BufferedReader bf;
	private Socket connection;
	private IObserver observer;
	
	private Communication() {}
	
	@Override
	public void run() {
		initCom();
	}
	
	private void initCom(){
		try {
			System.out.println("Iniciando servidor");
			ServerSocket ss = new ServerSocket(9000);
			System.out.println("Esperando clientes");
			connection = ss.accept(); //Stops program until there is a connection
			System.out.println("Conectado");
			
			//To receive
			InputStream is = connection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			bf = new BufferedReader(isr);
			
			//To transmit
			OutputStream os = connection.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			bw = new BufferedWriter(osw);
			
			receiveMessage();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void receiveMessage() {
		new Thread(
				() -> {
					while(true) {
						try {
							String msg = bf.readLine(); //Stops program until there is a message
							
							observer.notifyMessage(msg);

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}).start();	
	}
	
	public void sendMessage(String message) {
		new Thread(()->{
			//Write message
			try {
				bw.write(message + "\n");
				bw.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();
		
	}

	public IObserver getObserver() {
		return observer;
	}

	public void setObserver(IObserver observer) {
		this.observer = observer;
	}

}
