package com.evmtv.epg.utils;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import com.evmtv.epg.entity.TResourceWithBLOBs;
import com.evmtv.epg.entity.TUser;

import de.innosystec.unrar.Archive;
import de.innosystec.unrar.rarfile.FileHeader;

/**
 * 上传文件处理,解压缩zip、rar文件
 * 
 * @author fangzhu(fangzhu@rvmtv.com)
 * @time 2012-12-19 下午12:55:21
 */
public class UploadFileUtils {
	//图片类型
	private String[] imageType = {"jpg","png","gif","bmp"};
	/**
	 * 解压缩
	 * @param sourceFile 源文件
	 * @param realPath 服务器绝对路径
	 * @param uploadFilePath 文件保存相对路径
	 * @param user 当前用户
	 * @return
	 * @throws Exception
	 */
	public List<TResourceWithBLOBs> deCompress(String sourceFile, String realPath,String uploadFilePath, String contextPath,TUser user,TResourceWithBLOBs resource)
			throws Exception {
		List<TResourceWithBLOBs> resources = null;
		// 保证文件夹路径最后是"/"或者"\"
		realPath = FileUtils.checkFilePathEndSep(realPath);
		uploadFilePath = FileUtils.checkFilePathEndSep(uploadFilePath);
		// 根据类型，进行相应的解压缩
		String type = sourceFile.substring(sourceFile.lastIndexOf(".") + 1);
		if (type.equalsIgnoreCase("zip")) {
			resources = unZip(sourceFile, realPath, uploadFilePath, contextPath,user,resource);
		} else if (type.equalsIgnoreCase("rar")) {
			resources = unrar(sourceFile, realPath, uploadFilePath, contextPath,user,resource);
		} else {
			throw new Exception("只支持zip和rar格式的压缩包！");
		}
		/**
		 * 解压成功后删除源文件
		 */
		File deleteFile = new File(sourceFile);
		if (deleteFile.exists()) {
			deleteFile.delete();
		}
		return resources;
	}

	/**
	 * 解压zip包
	 * @param unZipfileName 需要解压的zip文件路径名
	 * @param realPath 项目真实路径
	 * @param uploadFilePath 文件保存路径，相对服务器项目
	 * @param user 当前用户
	 * @return
	 */
	private List<TResourceWithBLOBs> unZip(String unZipfileName,String realPath,String uploadFilePath,String contextPath,TUser user,TResourceWithBLOBs resource) {
		
		// 存放解压后的文件
		List<TResourceWithBLOBs> resources = new ArrayList<TResourceWithBLOBs>();
		ZipEntry zipEntry;
		byte[] buf = new byte[1024];
		int readedBytes;
		ZipInputStream zipIn = null;
		try {
			zipIn = new ZipInputStream(new BufferedInputStream(
					new FileInputStream(unZipfileName)),Charset.forName("GBK"));
			while ((zipEntry = zipIn.getNextEntry()) != null) {
				if (!zipEntry.isDirectory()) {
					//原始文件名
					String compressFileName = zipEntry.getName().trim();
					//文件后缀名
					String suffix = FileUtils.getFileSuffix(compressFileName);
					for (String type : imageType) {
						if (suffix.equalsIgnoreCase("." + type)) {
							String assetsName = createRandomName();
							//文件名
							String fileName = assetsName + suffix;
							//文件路径
							String destFile = realPath + uploadFilePath
									+ fileName;
							// 3解压缩文件
							File file = new File(destFile);
							File parent = file.getParentFile();
							if (!parent.exists()) {
								parent.mkdirs();
							}
							if (file.exists()) {
								file.createNewFile();
							}
							//解压缩文件
							FileOutputStream fileOut = new FileOutputStream(file);
							while ((readedBytes = zipIn.read(buf)) > 0) {
								fileOut.write(buf, 0, readedBytes);
							}
							fileOut.close();
														
							compressFileName = compressFileName.substring(compressFileName.indexOf("/")+1).replace(suffix, "");
							String path = uploadFilePath + fileName;
							//资源对象
							resource = setResource(resource,user.getId(),compressFileName,path,file);
							resources.add(resource);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				zipIn.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resources;
	}

	/**
	 * 解压zip格式压缩包 对应的是ant.jar
	 */
	/*private void unzip(String sourceZip, String destDir) {
		try {
			Project p = new Project();
			Expand e = new Expand();

			e.setProject(p);
			e.setSrc(new File(sourceZip));
			e.setOverwrite(false);
			e.setDest(new File(destDir));
			
			 * ant下的zip工具默认压缩编码为UTF-8编码， 而winRAR软件压缩是用的windows默认的GBK 或者GB2312编码
			 * 所以解压缩时要制定编码格式
			 
			e.setEncoding("gbk");
			e.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * 解压rar格式压缩包。
	 * 对应的是java-unrar-0.3.jar，但是java-unrar-0.3.jar又会用到commons-logging-1.1.1.jar
	 * @param sourceRar rar源文件
	 * @param realPath 项目服务器路径
	 * @param uploadFilePath 上传文件保存路径,相对于项目下
	 * @return
	 * @throws Exception
	 */
	private List<TResourceWithBLOBs> unrar(String sourceRar,String realPath,String uploadFilePath,String contextPath,TUser user,TResourceWithBLOBs resource)
			throws Exception {
		// 存放解压后的文件
		List<TResourceWithBLOBs> resources = new ArrayList<TResourceWithBLOBs>();
		Archive a = null;
		FileOutputStream fos = null;
		try {
			a = new Archive(new File(sourceRar));
			FileHeader fh = a.nextFileHeader();
			while (fh != null) {
				if (!fh.isDirectory()) {
					//原始文件名
					String compressFileName = fh.getFileNameString().trim();
					//文件后缀名
					String suffix = FileUtils.getFileSuffix(compressFileName);
					for(String type : imageType){
						if(suffix.equalsIgnoreCase("."+type)){
							String assetsName = createRandomName();
							//文件名
							String fileName = assetsName  + suffix;
							//文件路径
							String destFile = realPath + uploadFilePath + fileName;
							//输出文件
							File file = new File(destFile);
							// 3解压缩文件
							fos = new FileOutputStream(file);
							a.extractFile(fh, fos);
							fos.close();
							fos = null;

							compressFileName = compressFileName.substring(compressFileName.indexOf("/")+1).replace(suffix, "");
							String path = uploadFilePath + fileName;
							//资源对象
							resource = setResource(resource,user.getId(),compressFileName,path,file);
							resources.add(resource);
						}
					}
				}
				fh = a.nextFileHeader();
			}
			a.close();
			a = null;
		} catch (Exception e) {
			throw e;
		} finally {
			if (fos != null) {
				try {
					fos.close();
					fos = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (a != null) {
				try {
					a.close();
					a = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return resources;
	}
	 /**
	  * 文件写入服务器
	  * @param in 输入流
	  * @param file 输出文件
	  */
    public void copyFile(InputStream in,File file){ 
    	FileOutputStream fos = null;
        try {
			fos = new FileOutputStream(file);  
			  byte[] buffer = new byte[1024 * 1024];  
			  int byteread = 0;  
			  while ((byteread = in.read(buffer)) != -1) {  
			      fos.write(buffer, 0, byteread);  
			      fos.flush();
			  }  
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				fos.close();  
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }  

	/**
	 * 创建随机文件名
	 * 
	 * @return
	 */
	public String createRandomName() {
		return DateUtils.formatTime() + getRandomInt();
	}

	/**
	 * 随机数
	 * 
	 * @return
	 */
	public int getRandomInt() {
		return (new Random()).nextInt(10000);
	}

	/**
	 * 获取上传文件路径
	 * 
	 * @param fileType
	 * @return 文件上传路径
	 */
	public String getUploadPath(String fileType) {
		return getResourceBundle().getString(fileType);
	}

	/**
	 * 获取ResourceBundle实例对象
	 * 
	 * @return
	 */
	public ResourceBundle getResourceBundle() {
		return ResourceBundle.getBundle("upload");
	}

	/***
	 * 获取图片属性、宽高
	 * @param file
	 * @return 宽高
	 */
	public String[] getImageAttribute(File file) {
		String [] attribute = new String[2];
		try {
			Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName(FileUtils.getSuffix(file.getCanonicalPath()));
			ImageReader reader = readers.next();
			ImageInputStream iis = ImageIO.createImageInputStream(file);
			reader.setInput(iis, true);
			attribute[0] = reader.getWidth(0)+"";//图片宽
			attribute[1] = reader.getHeight(0) +"";//图片高
		} catch (IOException e) {
			e.printStackTrace();
		}
		return attribute;
	}
	/**
	 * 图片缩放
	 * @param file
	 * @param width
	 * @param height
	 * @return
	 */
	public boolean rize(File file, int width, int height) {
		boolean bool = true;
		BufferedImage bufTarget = null;
		BufferedImage srcBufImage = null;
		try {
			srcBufImage = ImageIO.read(file);
		} catch (IOException e1) {
			bool = false;
		}
		double sx = (double) width / srcBufImage.getWidth();
		double sy = (double) height / srcBufImage.getHeight();
		
		int type = srcBufImage.getType();
		if (type == BufferedImage.TYPE_CUSTOM) {
			ColorModel cm = srcBufImage.getColorModel();
			WritableRaster raster = cm.createCompatibleWritableRaster(width, height);
			boolean alphaPremultiplied = cm.isAlphaPremultiplied();
			bufTarget = new BufferedImage(cm, raster, alphaPremultiplied, null);
		} else
			bufTarget = new BufferedImage(width, height, type);

		Graphics2D g = bufTarget.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g.drawRenderedImage(srcBufImage,
				AffineTransform.getScaleInstance(sx, sy));
		g.dispose();
		try {
			bool = ImageIO.write(srcBufImage, FileUtils.getSuffix(file.getName()), file);
		} catch (IOException e) {
			bool = false;
		}
		return bool;
	}
	/**
	 * 
	 * @param resource 资源对象
	 * @param userId 用户id
	 * @param compressFileName 原始文件名
	 * @param path 文件保存路径
	 * @param file 文件
	 * @return
	 */
	public TResourceWithBLOBs setResource(TResourceWithBLOBs resource, Long userId, String compressFileName, String path, File file){
		//资源信息
		String stype = resource.getFtype();
		Long advType = resource.getFadvclassid();
		String fontColor = resource.getFfontcolor();
		String backColor = resource.getFbackcolor();
		String speed = resource.getFspeed();
		Integer scollType = resource.getFscrolltype();
		Integer fileformat = resource.getFfileformat();
		
		//获取图片资源属性
		String[] attrbuite = getImageAttribute(file);
		int width = Integer.parseInt(attrbuite[0]);
		int height = Integer.parseInt(attrbuite[1]);
		if(width > 1280 || height >720){
			rize(file, width, height);
		}
		
		//资源对象
		resource = new TResourceWithBLOBs();
		resource.setFadvclassid(advType);
		resource.setFbackcolor(backColor);
		resource.setFfontcolor(fontColor);
		resource.setFfileformat(fileformat);
		resource.setFscrolltype(scollType);
		resource.setFspeed(speed);
		resource.setFtype(stype);
		resource.setFdeleted("0");
		resource.setFfreezed("0");
		
		resource.setFcreateuserid(userId);
		resource.setFcreatetime(DateUtils.getCurrentTime());
		int length = compressFileName.indexOf("//");
		length = length == -1 ? 0 : length;
		compressFileName = compressFileName.substring(length);
		resource.setFname(compressFileName);
		resource.setFpath(path);
		resource.setFwidth(width+"");
		resource.setFheight(height+"");
		
		return resource;
	}
}
