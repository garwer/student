package course.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings(value = "all")
@Service("FileService")
@Transactional
public class FileService {
	public String upload(ServletFileUpload upload, List<FileItem> items) throws IOException {
		for (FileItem item : items) {
			// 若是一个一般的表单域, 打印信息
			if (item.isFormField()) {
				String name = item.getFieldName();
				String value = item.getString("gbk");
				System.out.println("======" + name + ": " + value);	
			}
			// 若是文件域则把文件保存到 e:\\files 目录下.
			else {
				String fileName = item.getName();//这边获取的为详细路径
				long sizeInBytes = item.getSize();
				int size = 1024 * 1024 * 1;//设置上传的文件总的大小不能超过5M
				upload.setSizeMax(size);
				long limitSize = upload.getSizeMax();
				System.out.println("limitSize" + limitSize);
				if (sizeInBytes > limitSize) {
					System.out.println("超出规定大小");
					return "limit";
				}
				System.out.println("=================:" + fileName);
				System.out.println("文件大小为--------------:" + sizeInBytes);
				InputStream in = item.getInputStream();
				byte[] buffer = new byte[1024];
				int len = 0;
				System.out.println("fileName的值为：" + fileName);
				File tempFile =new File(fileName.trim()); 
				System.out.println("啊啊啊啊啊啊" + fileName.trim());
				fileName = tempFile.getName(); //仅获取文件名
				String path = "E:\\files\\";
				File filePath = new File(path);
				if (!filePath.exists()) {
					filePath.mkdir(); //如果不存在路径 则创建
				}
				String fileUploadPath = "E:\\files\\" + fileName;//文件最终上传的位置
				System.out.println("=================:" + fileUploadPath);
				OutputStream out = new FileOutputStream(fileUploadPath);
				while ((len = in.read(buffer)) != -1) {
					out.write(buffer, 0, len);
				}
				System.out.println(out.toString());
				out.close();
				in.close();
			}
		}
		return "OK";
	} 
}
