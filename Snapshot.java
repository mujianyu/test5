package team.swust.st.experment4;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
/**
 * 
 * @author mjy
 *
 */
public class Snapshot {
	
	/**
	 * 
	 * @param drivername 驱动名
	 * @param filename 文件名
	 */
	public static void takeSnap(TakesScreenshot drivername, String filename){   
	    String currentPath = System.getProperty("user.dir");  
	    System.out.println(currentPath);
	    File scrFile = drivername.getScreenshotAs(OutputType.FILE); 
	        try {
	            System.out.println("保存路径:"+currentPath+"/"+filename); 
	            FileUtils.copyFile(scrFile, new File(currentPath+"\\"+filename));
	        } catch (IOException e) { 
	            System.out.println("截图错误");
	            e.printStackTrace();
	        } finally { 
	            System.out.println("截图");
	        }
	  } 
}
