import com.logica.smpp.Connection;
import com.logica.smpp.Session;
import com.logica.smpp.TCPIPConnection;
import com.logica.smpp.pdu.BindRequest;
import com.logica.smpp.pdu.BindTransmitter;
import com.logica.smpp.pdu.SubmitSM;




public class SimpleSMSTransmitter {

	/**
	 * @param args
	 */
	private Session session = null;
	private String ipAddress = "192.168.43.173";
	private String systemId = "smppclient1";
	private String password = "password";
	private int port = 2775;
	private String shortMessage = "abcdefg";
	private String sourceAddress = "1234";
	private String destinationAddress = "5678";

	
	public static void main(String[] args) {
		
		SimpleSMSTransmitter objSimpleSMSTransmitter = new SimpleSMSTransmitter();
		objSimpleSMSTransmitter.bindToSMSC();
		objSimpleSMSTransmitter.sendSingleSMS();
		
		System.out.println("Program terminated");
	}

	public void bindToSMSC() {
		try {
			Connection conn = new TCPIPConnection(ipAddress, port);
			session = new Session(conn);
			
			BindRequest breq = new BindTransmitter();
			breq.setSystemId(systemId);
			breq.setPassword(password);
			session.bind(breq);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendSingleSMS() {
		try {
			SubmitSM request = new SubmitSM();
			
			// set values
			request.setSourceAddr(sourceAddress);
			request.setDestAddr(destinationAddress);
			request.setShortMessage(shortMessage);
			
			// send the request
			session.submit(request);
			System.out.println("Message submitted....");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to submit message....");
		}
	}
}
