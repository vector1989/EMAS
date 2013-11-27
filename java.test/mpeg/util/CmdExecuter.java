package mpeg.util;

import java.io.BufferedReader;  
import java.io.InputStreamReader;  
import java.util.List;  
  
  
/** 
 * CmdExecuter 
 * <p>Title: 命令执行器</p> 
 * <p>Description: 封装对操作系统命令行发送指令相关操作</p> 
 * <p>Date: 2010-7-14</p> 
 * <p>Copyright: Copyright (c) 2010</p> 
 * <p>Company: novel-supertv.com</p> 
 * @author chenggong 
 * @version 1.0 
 */  
public class CmdExecuter {  
      
    /** 
     * 执行指令 
     * @param cmd 执行指令 
     * @param getter 指令返回处理接口，若为null则不处理输出 
     */  
    static public void exec( List<String> cmd, IStringGetter getter ){  
        try {  
            ProcessBuilder builder = new ProcessBuilder();    
            builder.command(cmd);  
            builder.redirectErrorStream(true);  
            Process proc = builder.start();  
            if( getter != null ){
	            BufferedReader stdout = new BufferedReader(new InputStreamReader(proc.getInputStream()));  
	            String line;  
	            	while ((line = stdout.readLine()) != null) {  
	                    getter.dealString(line);  
	            	}  
	            proc.waitFor();     
	            stdout.close();  
            }
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
} 