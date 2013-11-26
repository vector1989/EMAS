import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.poi.hwpf.extractor.WordExtractor;

public class PoiTest {
	public static void main(String[] args) {
		InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String ip=addr.getHostAddress().toString();//获得本机IP
		String address=addr.getHostName().toString();//获得本机名称
		
		System.out.println(ip + "----" + address);
	}
	public String poiWord(String path) {
		String text = "";
		try {
			FileInputStream in = new FileInputStream(path);
			WordExtractor extractor = new WordExtractor(in);
			text = extractor.getText();
//			extractor.
			// 对DOC文件进行提取
			System.out.println(text);
			extractor.close();
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return text;
	}
}
