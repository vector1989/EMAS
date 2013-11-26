import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;



public class MDK {
	public static void main(String[] args) {
		try {
			InetAddress address = InetAddress.getByName("192.168.48.2");
			
			try {
				boolean b = address.isReachable(5000);
				System.out.println(b);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
