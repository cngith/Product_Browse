package cn.wzr.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import cn.wzr.global.Const;


public class ACWhiteListor {

	private List<String> acWhileList = null; 
	
	public List<String> getIpWhileList() {
		return acWhileList;
	}

	/**
	 * 
	 * @param listFile 白名单文件路径
	 */
	public ACWhiteListor(String listFile) {
		this.acWhileList = readACWLFile(listFile);
	}
	
//	private boolean checkIP(String str) {
//        Pattern pattern = Pattern.compile("^((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]"
//                        + "|[*])\\.){3}(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]|[*])$");
//        return pattern.matcher(str).matches();
//    }
	
	/**
	 * 判断注册码是否在白名单中
	 * @param authCode
	 * @return 在白名单中返回true，否则返回false
	 */
	public boolean isLegal(String authCode){
		if (null == authCode || 0 == authCode.trim().length()) { // 如果字符串不是合法的注册码
			return false;
		}
		for(String legac:this.acWhileList){
System.out.println("legac:" + legac);
			if(legac.equals(authCode.trim())){ // 在白名单中找到了
				return true;
			}
		}
		return false;
	}
	
//	private List<String> readIpWLFile1(String filePath){
//		File file = new File(filePath);
//	    BufferedReader reader = null;
//	    String tempString = null;
//	    int line =1;
//	    List<String> reList=new ArrayList<String>();
//	    try {
//	        System.out.println("以行为单位读取文件内容，一次读一整行：");
//	        reader = new BufferedReader(new FileReader(file));
//	        while ((tempString = reader.readLine()) != null) {
//	            System.out.println("Line"+ line + ":" +tempString);
//	            reList.add(tempString);
//	            line ++ ;
//	        }
//	        reader.close();
//	    } catch (FileNotFoundException e) {
//	        // TODO Auto-generated catch block
//	        e.printStackTrace();
//	    } catch (Exception e) {
//	        // TODO Auto-generated catch block
//	        e.printStackTrace();
//	    }finally{
//	        if(reader != null){
//	            try {
//	                reader.close();
//	            } catch (IOException e) {
//	                // TODO Auto-generated catch block
//	                e.printStackTrace();
//	            }
//	        }
//	    }
//	    return reList;
//	}
	
	private List<String> readACWLFile(String filePath){
		List<String> reList = new ArrayList<String>();
		try {
			File file=new File(filePath);
			if(file.isFile() && file.exists()){ //判断文件是否存在
				InputStreamReader read = new InputStreamReader(
				new FileInputStream(file),Const.ENCODING);//考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				int i=1;
				while((lineTxt = bufferedReader.readLine()) != null){
            		if(!"".equals(lineTxt)){
	            		String prefix = lineTxt.trim().substring(0,2);
	            		if(!"//".equals(prefix)){ // 此行不是注释
	            			reList.add(lineTxt.trim());
	            		}
            		}
				}
				read.close();
			}
			else{
				System.out.println("找不到指定的文件");
			}
		} 
		catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
	    }
		return reList;
	}
}
