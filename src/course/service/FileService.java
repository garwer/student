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
			// ����һ��һ��ı���, ��ӡ��Ϣ
			if (item.isFormField()) {
				String name = item.getFieldName();
				String value = item.getString("gbk");
				System.out.println("======" + name + ": " + value);	
			}
			// �����ļ�������ļ����浽 e:\\files Ŀ¼��.
			else {
				String fileName = item.getName();//��߻�ȡ��Ϊ��ϸ·��
				long sizeInBytes = item.getSize();
				int size = 1024 * 1024 * 1;//�����ϴ����ļ��ܵĴ�С���ܳ���5M
				upload.setSizeMax(size);
				long limitSize = upload.getSizeMax();
				System.out.println("limitSize" + limitSize);
				if (sizeInBytes > limitSize) {
					System.out.println("�����涨��С");
					return "limit";
				}
				System.out.println("=================:" + fileName);
				System.out.println("�ļ���СΪ--------------:" + sizeInBytes);
				InputStream in = item.getInputStream();
				byte[] buffer = new byte[1024];
				int len = 0;
				System.out.println("fileName��ֵΪ��" + fileName);
				File tempFile =new File(fileName.trim()); 
				System.out.println("������������" + fileName.trim());
				fileName = tempFile.getName(); //����ȡ�ļ���
				String path = "E:\\files\\";
				File filePath = new File(path);
				if (!filePath.exists()) {
					filePath.mkdir(); //���������·�� �򴴽�
				}
				String fileUploadPath = "E:\\files\\" + fileName;//�ļ������ϴ���λ��
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
