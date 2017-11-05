package course.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import course.service.FileService;

@Controller
@RequestMapping("/uploadFile.do")
public class FileController {
	@Autowired
	private FileService fileService;
	@ResponseBody
	@RequestMapping(params="method=uploadFile")
	public String uploadFile(HttpServletRequest request,HttpServletResponse response) throws IOException, FileUploadException {
	    request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String name = request.getParameter("username");
        String aaa = request.getParameter("namea");
        System.out.println(name);
        System.out.println(aaa);
		//1、创建一个DiskFileItemFactory工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //2、创建一个文件上传解析器
        ServletFileUpload upload = new ServletFileUpload(factory);
        //解决上传文件名的中文乱码
        upload.setHeaderEncoding("utf-8"); 
		factory.setSizeThreshold(1024 * 500);//设置内存的临界值为500K
		File linshi = new File("E:\\up");//当超过500K的时候，存到一个临时文件夹中
		factory.setRepository(linshi);
		System.out.print("啊啊啊啊啊啊啊");
		@SuppressWarnings("unchecked")
		List<FileItem> /*FileItem */items = upload.parseRequest(request);
		
		String flag = fileService.upload(upload,items);
        name = request.getParameter("descwhat");
        System.out.println("flag的值为" + flag);
		return flag;	
	}
}
