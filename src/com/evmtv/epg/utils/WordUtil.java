package com.evmtv.epg.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
 
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 





import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.w3c.dom.Document;


/**
 * @author Lei<helei@evmtv.com>
 * @data 2013-6-27 上午11:43:39
 */

public class WordUtil {
	public static String wordToHtml(String filePath,String fileName) throws Throwable {
        InputStream input = new FileInputStream (filePath + fileName);
        HWPFDocument wordDocument = new HWPFDocument (input);
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter (DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument() );
        wordToHtmlConverter.setPicturesManager (new PicturesManager() {
            public String savePicture (byte[] content, PictureType pictureType, String suggestedName, float widthInches, float heightInches) {
                return suggestedName;
            }
        });
        wordToHtmlConverter.processDocument (wordDocument);

        List<Picture> pics = wordDocument.getPicturesTable().getAllPictures();
        if (pics != null) {
            for (int i = 0; i < pics.size(); i++) {
                Picture pic = (Picture) pics.get (i);
                try {
                    pic.writeImageContent (new FileOutputStream (filePath + pic.suggestFullFileName() ) );
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        Document htmlDocument = wordToHtmlConverter.getDocument();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        DOMSource domSource = new DOMSource (htmlDocument);
        StreamResult streamResult = new StreamResult (outStream);
 
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = tf.newTransformer();
        serializer.setOutputProperty (OutputKeys.ENCODING, "utf-8");
        serializer.setOutputProperty (OutputKeys.INDENT, "yes");
        serializer.setOutputProperty (OutputKeys.METHOD, "html");
        serializer.transform (domSource, streamResult);
        String content = new String (outStream.toString());
//        System.out.println(content);
        outStream.close();
        return content;
    }
	/**
	 * 
	 * @param is
	 * @param filePath 
	 * @return
	 * @throws IOException 
	 * @throws ParserConfigurationException 
	 * @throws TransformerException 
	 */
	public static String wordToHtml(InputStream is, String filePath) throws IOException, ParserConfigurationException, TransformerException {
//        InputStream input = new FileInputStream (filePath + fileName);
        HWPFDocument wordDocument = new HWPFDocument (is);
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter (DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument() );
        wordToHtmlConverter.setPicturesManager (new PicturesManager() {
            public String savePicture (byte[] content, PictureType pictureType, String suggestedName, float widthInches, float heightInches) {
                return suggestedName;
            }
        });
        wordToHtmlConverter.processDocument (wordDocument);

        List<Picture> pics = wordDocument.getPicturesTable().getAllPictures();
        if (pics != null) {
            for (int i = 0; i < pics.size(); i++) {
                Picture pic = (Picture) pics.get (i);
                try {
                    pic.writeImageContent (new FileOutputStream (filePath + pic.suggestFullFileName() ) );
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        Document htmlDocument = wordToHtmlConverter.getDocument();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        DOMSource domSource = new DOMSource (htmlDocument);
        StreamResult streamResult = new StreamResult (outStream);
 
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = tf.newTransformer();
//        serializer.setOutputProperty (OutputKeys.ENCODING, "utf-8");
        serializer.setOutputProperty (OutputKeys.INDENT, "yes");
        serializer.setOutputProperty (OutputKeys.METHOD, "html");
        serializer.transform (domSource, streamResult);
        String content = new String (outStream.toString("utf-8"));
//        System.out.println(content);
        outStream.close();
        return content;
    }
	    
    public static String htmlToWord(String filePath, String fileName, String content) { 
    	String msg = "保存失败！";
        try {  
            if (!"".equals(filePath)) {  
                // 检查目录是否存在  
                File fileDir = new File(filePath);  
                if (fileDir.exists()) {  
                    // 生成临时文件名称  
                    byte b[] = content.getBytes("utf-8");  
                    ByteArrayInputStream bais = new ByteArrayInputStream(b);  
                    POIFSFileSystem poifs = new POIFSFileSystem();  
//	                    DirectoryEntry directory = poifs.getRoot();  
//	                    DocumentEntry documentEntry = directory.createDocument("WordDocument",bais);  
                    FileOutputStream ostream = new FileOutputStream(filePath + fileName); 
                    poifs.writeFilesystem(ostream);
                    bais.close();  
                    ostream.close();  
                    msg = "保存成功！";
                }  
            }  
        } catch (Exception e) {  
            msg = e.getMessage();
      } 
      return msg;
    }
}


