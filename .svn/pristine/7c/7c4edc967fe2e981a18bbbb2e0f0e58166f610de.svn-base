package com.evmtv.epg.test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @blog http://sjsky.iteye.com
 * @author Michael
 */
public class TestPingCmd {

    /**
     * @param args
     * @throws Exception
     */
    @SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {

    	String line = null;
        try
        {
            Process pro = Runtime.getRuntime().exec("ping www.baidu.com -t");
            BufferedReader buf = new BufferedReader(new InputStreamReader(pro.getInputStream(),"gbk"));
            while ((line = buf.readLine()) != null)
                System.out.println(new Date().toLocaleString()+":"+line);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

}