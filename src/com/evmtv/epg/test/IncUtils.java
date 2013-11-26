package com.evmtv.epg.test;

import java.io.IOException;

import com.evmtv.epg.utils.FileUtils;
import com.evmtv.epg.utils.StringUtils;

public class IncUtils {
	public static void main(String[] args) {
		System.out.println(toStringHex("CEC4D2D5D7DBBACF31"));
		IncUtils inc = new IncUtils();
		inc.p("F:\\Project\\JavaEE\\EAMS\\src\\com\\evmtv\\epg\\test\\rh.inc");
	}
	public void parse(String path){
		try {
			StringBuilder content = new StringBuilder(FileUtils.readFileToString(path,"utf-8").replace(" ", ""));

//			System.out.println(content.length() + "-----" +content.indexOf("F4"));
			
//			content = content.delete(0, content.indexOf("F4")-4);
			
			System.out.println("-leng:"+content.length());
			
			//第一段
			/*{
				substr(content, 8);
				substr(content, 16);
				int len = to10(substr(content, 8));
				for(int i=0;i<len;i++){
					substr(content, 16);
					substr(content, 8);
					substr(content, 8);
				}
			}
			//第二段
			{
				int len = to10(substr(content, 8));
				for(int i=0;i<len;i++){
					substr(content, 16);
					substr(content, 16);
					substr(content, 32);
					substr(content, 24);
					substr(content, 8);
					int l = to10(substr(content, 8));
					substr(content, l*4);

					substr(content, 8);
					substr(content, 8);
					substr(content, 8);
				}
				
			}*/
			//第三段
			{
				int progCnt = to10(substr(content, 16));
				for(int i=0;i<progCnt;i++){
					Inc inc = new Inc();
					substr(content, 8);//descriptor_tag
					substr(content, 16);//descriptor_length
					inc.setSid(to10(substr(content, 16))+"");//service_id
					substr(content, 16);//service_type
					substr(content, 8);//video_type
					substr(content, 16);//pmt_pid
					substr(content, 16);//video_pid
					inc.setOnid(to10(substr(content, 8))+"");//original_network_id
					
					substr(content, 8);//audio_count
					int acount = 3;
					for(int j=0;j<acount;j++){
						inc.setAtype(substr(content, 8));//audio_type
						inc.setApid(to10(substr(content, 16))+"");//audio_pid
					}
					int service_name_length = to10(substr(content, 8));//service_name_length
					inc.setServiceName(toStringHex(substr(content, 8*service_name_length)));//service_name
					
					substr(content, 16);//pcr_pid
					substr(content, 16);//subtitle_pid
					substr(content, 16);//teletext_pid
					substr(content, 8);//scramble_flag
					int spNameLen = to10(substr(content, 8));//service_provider_name_length
					substr(content, 8*spNameLen);//service_provider_name
					int bidLen = to10((substr(content, 8)));//bouquet_id_number
					substr(content, 8*bidLen);//bouquet_id
//					int aidLen = to10(substr(content, 0));//authorization_id_number
//					inc.setAuthorId(substr(content, 8*aidLen));//authorization_id
//					int pploopLeng = to10(substr(content, 16));//program_private_loop_length
					int prLeng = to10(substr(content, 8));//program_reserved_length
//					if(prLeng > 5) prLeng = 1;
					substr(content, 8*prLeng);//reserved
					
					inc.setTsid(to10(substr(content, 16))+"");//transport_stream_id
					System.out.println(i+"---"+prLeng+"--"+inc);
				}
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void p(String path){
		StringBuilder content = null;
		try {
			content = new StringBuilder(FileUtils.readFileToString(path,"utf-8").replace(" ", ""));
		} catch (IOException e) {
			e.printStackTrace();
		}
		int progCnt = to10(substr(content, 16));
		for(int i=0;i<progCnt;i++){
			Inc inc = new Inc();
			String descriptor_tag = substr(content, 8);//descriptor_tag
			String descriptor_length = substr(content, 16);//descriptor_length
			String service_id = substr(content, 16);
			String service_type = substr(content, 8);//service_type
			String pmt_pid = substr(content, 16);//pmt_pid
			String video_type = substr(content, 8);//video_type
			String video_pid = substr(content, 16);
			String original_network_id = substr(content, 16);
			
			String audio_count = substr(content, 8);//audio_count
			for(int j=0;j<3;j++){
				String audio_type = substr(content, 8);//audio_type
				String audio_pid = to10(substr(content, 16))+"";//audio_pid
			}
			int service_name_length = to10(substr(content, 8));//service_name_length
			String service_name = substr(content, service_name_length * 8);
			String pcr_pid = substr(content, 16);//pcr_pid
			String subtitle_pid = substr(content, 16);//subtitle_pid
			String teletext_pid = substr(content, 16);//teletext_pid
			String scramble_flag = substr(content, 8);//scramble_flag
			int service_provider_name_length = to10(substr(content, 8));//service_provider_name_length
			String service_provider_name = substr(content, 8*service_provider_name_length);//service_provider_name
			int bouquet_id_number = to10((substr(content, 8)));//bouquet_id_number
			String bouquet_id = substr(content, 16*bouquet_id_number);//bouquet_id
			int authorization_id_number = to10(substr(content, 8));//authorization_id_number
			String authorization_id = substr(content, 16*authorization_id_number);//authorization_id
			int program_reserved_length = to10(substr(content, 16));//program_reserved_length
			String program_reserved = substr(content, 8*program_reserved_length);//reserved
			
			inc.setServiceName(toStringHex(service_name));//service_name
			inc.setSid(to10(service_id)+"");//service_id
			inc.setOnid(to10(original_network_id)+"");//original_network_id
			inc.setTsid(to10(substr(content, 16))+"");//transport_stream_id
			System.out.println(i+"-----"+inc);
		}
	}
	private String substr(StringBuilder str,int len){
		len = len / 4;
		String s = str.substring(0, len);
		str = str.delete(0, len);
		return s;
	}
	
	private Integer to10(String s){
		if(StringUtils.hasText(s))
			return Integer.valueOf(s,16);
		return 0;
	}
	
	public static String toStringHex(String s) {
		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			try {
				baKeyword[i] = (byte) (0xff & Integer.parseInt(
						s.substring(i * 2, i * 2 + 2), 16));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		try {
			s = new String(baKeyword, "gbk");// UTF-16le:Not
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return s;
	}
	
}
