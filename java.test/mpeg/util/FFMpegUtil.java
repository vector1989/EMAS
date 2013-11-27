package mpeg.util;
import java.util.ArrayList;  
import java.util.List;  
import java.util.regex.Matcher;  
import java.util.regex.Pattern;  
  
/** 
 * FFMpegUntil 
 * <p>Title: FFMpeg工具类</p> 
 * <p>Description: 本类封装FFMpeg对视频的操作</p> 
 * <p>Date: 2010-7-14</p> 
 * <p>Copyright: Copyright (c) 2010</p> 
 * <p>Company: novel-supertv.com</p> 
 * @author chenggong 
 * @version 1.0 
 */  
public class FFMpegUtil implements IStringGetter{  
      
    private int runtime = 0;  
    private String ffmpegUri;//ffmpeg地址  
    private String originFileUri; //视频源文件地址  
    private enum FFMpegUtilStatus { Empty, CheckingFile, GettingRuntime };  
    private FFMpegUtilStatus status = FFMpegUtilStatus.Empty;  
      
    /** 
     * 构造函数 
     * @param ffmpegUri ffmpeg的全路径  
     *      如e:/ffmpeg/ffmpeg.exe 或 /root/ffmpeg/bin/ffmpeg 
     * @param originFileUri 所操作视频文件的全路径  
     *      如e:/upload/temp/test.wmv 
     */  
    public FFMpegUtil( String ffmpegUri, String originFileUri ){  
        this.ffmpegUri = ffmpegUri;  
        this.originFileUri = originFileUri;  
    }  
  
    /** 
     * 获取视频时长 
     * @return 
     */  
    public int getRuntime(){  
        runtime = 0;  
        status = FFMpegUtilStatus.GettingRuntime;  
        cmd.clear();  
        cmd.add(ffmpegUri);  
        cmd.add("-i");  
        cmd.add(originFileUri);  
        CmdExecuter.exec(cmd, this);  
        return runtime;   
    }  
      
    /** 
     * 检测文件是否是支持的格式 
     * 将检测视频文件本身，而不是扩展名 
     * @return 
     */  
    public boolean isSupported(){  
        isSupported = true;  
        status = FFMpegUtilStatus.CheckingFile;  
        cmd.clear();  
        cmd.add(ffmpegUri);  
        cmd.add("-i");  
        cmd.add(originFileUri);  
        CmdExecuter.exec(cmd, this);  
        return isSupported;  
    }  
      
    private boolean isSupported;  
      
    /** 
     * 生成视频截图 
     * @param imageSavePath 截图文件保存全路径 
     * @param screenSize 截图大小 如640x480 
     */  
    public void makeScreenCut( String imageSavePath , String screenSize ){  
        cmd.clear();  
        cmd.add(ffmpegUri);  
        cmd.add("-i");  
        cmd.add(originFileUri);  
        cmd.add("-y");  
        cmd.add("-f");  
        cmd.add("image2");  
        cmd.add("-ss");  
        cmd.add("8");  
        cmd.add("-t");  
        cmd.add("0.007");
        cmd.add("-s");  
        cmd.add(screenSize);  
        cmd.add(imageSavePath);  
        CmdExecuter.exec(cmd, null);  
    }  
      
    /**
     * i帧转图片
     * @param imageSavePath
     */
    public void m2vToJpg(String imageSavePath){
    	cmd.clear();  
        cmd.add(ffmpegUri);  
        cmd.add("-i");  
        cmd.add(originFileUri);
        cmd.add(imageSavePath);  
        CmdExecuter.exec(cmd, null);  
    }
    /** 
     * 视频转换 
     * @param fileSavePath 文件保存全路径（包括扩展名）如 e:/abc/test.flv 
     * @param screenSize 视频分辨率 如640x480 
     * @param audioByte 音频比特率 
     * @param audioCollection 音频采样率 
     * @param quality 视频质量(0.01-255)越低质量越好 
     * @param fps 每秒帧数（15或29.97） 
     */  
    public void videoTransfer(   
            String fileSavePath,  
            String screenSize,  
            int audioByte,  
            int audioCollection,  
            double quality,  
            double fps ){  
        cmd.clear();  
        cmd.add(ffmpegUri);  
        cmd.add("-i");  
        cmd.add(originFileUri);  
        cmd.add("-y");  
        cmd.add("-ab");  
        cmd.add( Integer.toString(audioByte) );  
        cmd.add("-ar");  
        cmd.add( Integer.toString(audioCollection) );  
        cmd.add("-qscale");  
        cmd.add( Double.toString(quality) );  
        cmd.add("-r");  
        cmd.add( Double.toString(fps) );  
        cmd.add("-s");  
        cmd.add(screenSize);              
        cmd.add( fileSavePath );  
        CmdExecuter.exec(cmd, null);  
    }  
      
    private List<String> cmd = new ArrayList<String>();  
      
    @Override  
    public void dealString( String str ){  
          
        switch( status )  
        {  
            case Empty:  
                break;  
            case CheckingFile:{  
                Matcher m = Pattern.compile("Unknown format").matcher(str);  
                if( m.find() )  
                    this.isSupported = false;  
                break;  
            }  
            case GettingRuntime:{  
                Matcher m = Pattern.compile("Duration: //w+://w+://w+").matcher(str);   
                while (m.find())   
                {  
                     String msg = m.group();  
                     msg = msg.replace("Duration: ", "");  
                     runtime = 1;
//                     runtime = TimeUtil.runtimeToSecond(msg);    
                }  
                break;  
            }  
        }//switch  
    }  
} 