import com.evmtv.epg.constants.Constants;

import mpeg.util.FFMpegUtil;


public class Main {
	public static void main(String[] args) {
		System.out.println(Constants.FFMPEG_PATH);
		FFMpegUtil ffmpeg = new FFMpegUtil(Constants.FFMPEG_PATH, "D:\\Users\\Desktop\\resources\\20131106152552.m2v");
		ffmpeg.m2vToJpg("D:\\Users\\Desktop\\123.jpg");
	}
}
