package com.evmtv.epg.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.evmtv.epg.entity.TUser;
import com.evmtv.epg.utils.DateUtils;

public class Main {

	private static int pos = 0;
	private String s = "";
	public static void main(String[] args) {
//		String s = "F:/EVM/黑龙江/广告/哈分20130116.inc";
//		Main m = new Main();
//		m.readFile(s);
//		m.parse();
		/*for(Program p : ps){
			System.out.println(p);
		}*/
//		test();
//		TUser u = new TUser();
//		u.setFname("li");
//		t(u);

		Integer i = 2;
		t(i);
		System.out.println(i);
	}
	public static void t(Integer i){
		i = 10;
	}
	public static void t(TUser u){
		u.setFname("candy");
	}
	private static void test() {
		// TODO Auto-generated method stub
		List<TUser> us = new ArrayList<TUser>();
		for(int i=0;i<5;i++){
			TUser u = new TUser();
			u.setFname("candy"+i);
			us.add(u);
		}
		int i=0;
		for(TUser u : us){
			u.setFcreatetime(DateUtils.getCurrentTime() + i);
			i++;
		}
		
		for(TUser u : us){
			System.out.println(u.getFname() + "------" + u.getFcreatetime());
		}
	}
	/**
	 * 读取文件
	 * @param s
	 * @return
	 */
	public String readFile(String s){
		File file = new File(s);
		try {
			StringBuilder sb = new StringBuilder();
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = "";
			while((line = br.readLine())!=null)
				sb.append(line);
			s = sb.toString().replace(" ", "");
			this.s = s;
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return s;
	}
	
	public List<Program> parse(){
		List<Program> ps = new ArrayList<Program>();
		//第一段
		{
			read8();
			read16();
			int bnum = read8();
			for(int i=0;i<bnum;i++){
				read16();
				int bnl = read8();
				for(int j =0;j<bnl;j++){
					read8();
				}
			}
		}
		//第二段
		{
			int tsnum = read8();
			for(int i =0;i<tsnum;i++){
				read16();
				read16();
				read32();
				read24();
				read8();
				int scount = read8();
				for(int j=0;j<scount;j++){
					read16();
				}
				read8();
				read8();
				read8();
			}
		}
		//第三段
		{
			int progCnt = read16();
			System.out.println("items_count:" + progCnt);
			for(int i = 0; i < progCnt;i++){
				Program p = new Program();
				read8();
				read16();
				p.setOnid((read16())+"");
				p.setServiceid((read16())+"");
				read8();
				read16();
				read8();
				read16();
				int acount = read8();
				for(int j = 0;j<acount;j++){
					read8();
					read16();
				}
				read16();
				read16();
				read16();
				read8();
				int spnl = read8();
	//			System.out.println("service_provider_name:"+.substring(pos*spnl));
				for(int k=0;k<spnl;k++){
					read8();
				}
				
				int snl = read8();
				String name = "";
				for(int j=0;j<snl;j++){
					name += read8();
				}			
				p.setServicename(name);
				
				int bcount = read8();
				for(int k=0;k<bcount;k++){
					read16();
				}
				int authorcount = read8();
				for(int k=0;k<authorcount;k++){
					read16();
				}
				
				int prcount = read8();
				for(int k=0;k<prcount;k++){
					read8();
				}
				p.setTsid(read16()+"");
				System.out.println(p);
	//			ps.add(p);
			}
		}
		return ps;
	}
	
	public int to10(String s){
		return Integer.parseInt(s,16);
	}
	
	public String toStringHex(String s) {
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

	public int read8(){
		int flag = pos;
		pos += 2;
		return to10(s.substring(flag, pos));
	}
	public int read16(){
		int flag = pos;
		pos += 4;
		return to10(s.substring(flag, pos));
	}
	public int read32(){
		int flag = pos;
		pos += 8;
		return to10(s.substring(flag, pos));
	}
	public int read24(){
		int flag = pos;
		pos += 6;
		return to10(s.substring(flag, pos));
	}
}
