package com.evmtv.epg.utils.ffmpeg;

import java.io.BufferedReader;  
import java.io.IOException;
import java.io.InputStreamReader;  
import java.util.List;  
  
/**
 * <p>Title: 命令执行器</p> 
 * <p>Description: 封装对操作系统命令行发送指令相关操作</p>  
 * <p>Date: 2013年11月27日下午1:26:02</p> 
 * <p>Copyright: Copyright (c) 2013</p> 
 * <p>Company: www.evmtv.com</p> 
 * @author fangzhu@evmtv.com
 * @version 1.0
 */
public class CmdExecuter {  
      
    /** 
     * 执行指令 
     * @param cmd 执行指令 
     * @param getter 指令返回处理接口，若为null则不处理输出 
     * @throws IOException 
     * @throws InterruptedException 
     */  
    public static void exec( List<String> cmd, IStringGetter getter ) throws IOException, InterruptedException{  
		ProcessBuilder builder = new ProcessBuilder();
		builder.command(cmd);
		builder.redirectErrorStream(true);
		Process proc = builder.start();
		if (getter != null) {
			BufferedReader stdout = new BufferedReader(new InputStreamReader(
					proc.getInputStream()));
			String line;
			while ((line = stdout.readLine()) != null) {
				getter.dealString(line);
			}
			stdout.close();
		}
		proc.waitFor();   
    }  
} 