package parseH2text;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToTextFile {
	private static int i=0;
	Matcher slashMatcher =null ;

	//	 * 通过递归得到某一路径下所有的目录及其文件
	void getFiles(String filePath,String outputpath) throws IOException
	{
		File root = new File(filePath);
		File[] files = root.listFiles();
		for(File file:files)
		{     
			if(file.isDirectory())
			{
				// * 递归调用
				getFiles(file.getAbsolutePath(),outputpath);
			}
			else
			{
				String fileName = file.getName();
				if((fileName.lastIndexOf(".")!=-1)&&fileName.substring(fileName.lastIndexOf(".")).equals(".html"))
				{
					/*"显示"+filePath+"下所有子目录"*/
					String fileDir=file.getAbsolutePath();
//					System.out.println(fileDir);
					//读取字符串第3次出现"/"符号的位置 
//					int position = getCharacterPosition(fileDir, 1, "/");
					
					BufferedWriter writer = null;
					StringBuffer buffer = new StringBuffer();
					//获取html文件解析后的StringBuffer
					buffer = new ParseHtmlToText().ParserH(file.getAbsolutePath());
					if(buffer.length()>0){
//						System.out.println(file.getAbsolutePath().substring(position+1));
						
						writer = new BufferedWriter(new FileWriter(outputpath+fileName.substring(0, fileName.lastIndexOf("."))+i+".txt"));
						writer.write(buffer.toString());
						i++;
						writer.close();
					}

				}

			}   

		}
	}
	
/*	public  int getCharacterPosition(String string ,int i,String character)
	{  
		slashMatcher = Pattern.compile("/").matcher(string);  
		int mIdx = 0;  
		while(slashMatcher.find()) {  
			mIdx++;  
			//当符号第i次出现的位置  
			if(mIdx == i){  
				break;  
			}  
		}  
		return slashMatcher.start();  
	}
	*/
	

}


