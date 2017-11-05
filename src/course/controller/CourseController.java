package course.controller;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import course.entity.Collect;
import course.entity.Comment;
import course.entity.CourseTree;
import course.entity.Integral;
import course.entity.UserInfo;
import course.service.UserSelCourse;
@Controller
@RequestMapping("/getCourse.do")
/**
 *  校验用户
 */
public class CourseController {
	@Autowired
	private UserSelCourse userSelCourse;
	/*private static final Logger log = Logger.getLogger(CourseController.class);*/
	@ResponseBody
	@RequestMapping(params="method=getCourse")
	public List<Map<String, Object>> getAllCourse(HttpServletRequest request,HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");
		List<Map<String, Object>> tree = userSelCourse.getCourse();
		return tree;	
	}
	
	@ResponseBody
	@RequestMapping(params="method=findContentById")
	public Map<String,Object> findContentById(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");
		String id = request.getParameter("id");
		Map<String,Object> map = userSelCourse.findContentById(id);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(params="method=findHotCourse")
	public List<Map<String, Object>> findHotCourse(HttpServletRequest request,HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");
		String id = request.getParameter("id");
		List<Map<String, Object>> list = userSelCourse.findHotCourse(id);
		return list;	
	}
	
	@ResponseBody
	@RequestMapping(params="method=findScoreOrder")
	public List<Map<String, Object>> findScoreOrder(HttpServletRequest request,HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");
		String id = request.getParameter("id");
		List<Map<String, Object>> list = userSelCourse.findScoreOrder(id);
		return list;	
	}
	
	@ResponseBody
	@RequestMapping(params="method=findCourseByPro")
	public List<Map<String, Object>> findCourseByPro(HttpServletRequest request,HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");
		String id = request.getParameter("id");
		List<Map<String, Object>> tree = userSelCourse.findCourseByPro(id);
		return tree;	
	}
	
	@ResponseBody
	@RequestMapping(params="method=sureCollect")
	public void sureCollect(@RequestBody Map<String, Object> map) {
		String studentId = map.get("studentId").toString();
		String courseId = map.get("courseId").toString();
		List labelMsg = (List)map.get("labelMsg");
		for (int i = 0; i < labelMsg.size(); i++) {
			Collect collect = new Collect();
			collect.setCourseId(courseId);
			collect.setUserId(studentId);
			collect.setLabelName(labelMsg.get(i).toString());
			userSelCourse.saveCollect(collect);
		}
			Integral integral = new Integral();
			integral.setType("collect");
			integral.setUserId(studentId);
			integral.setCourseId(courseId);
			userSelCourse.saveIntegral(integral);
	}
	
	@ResponseBody
	@RequestMapping(params="method=isCollect")
	public String isCollect(HttpServletRequest request,HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");
		String courseId = request.getParameter("courseId");
		String userId = request.getParameter("userId");	
		String isCollect = userSelCourse.isCollect(courseId, userId);
		return isCollect;
	}
	
	@ResponseBody
	@RequestMapping(params="method=getComment")
	public List<Map<String, Object>> getComment(HttpServletRequest request,HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");
		String courseId = request.getParameter("courseId");
		String type = request.getParameter("type");
		List<Map<String, Object>> tree = userSelCourse.getComment(courseId,type);
		return tree;	
	}
	
	@ResponseBody
	@RequestMapping(params="method=saveComment")
/*	@RequestMapping(value = "/saveComment", method = RequestMethod.POST)*/
	public void saveComment(@RequestBody Map<String, String> map) {
		String commentContent = map.get("commentContent");
		String userId = map.get("userId");
		String courseId = map.get("courseId");
		String type = map.get("type");
		String integralType = map.get("integralType");
		Comment comment = new Comment();
		comment.setCommentContent(commentContent);
		comment.setCourseId(courseId);
		comment.setUserId(userId);
		comment.setType(type);
		userSelCourse.saveComment(comment);
		Integral integral = new Integral();
		integral.setType(integralType);
		integral.setUserId(userId);
		integral.setCourseId(courseId);
		userSelCourse.saveIntegral(integral);
	}
	
	@ResponseBody
	@RequestMapping(params="method=delComment")
/*	@RequestMapping(value = "/delComment", method = RequestMethod.POST)*/
	public void delComment(@RequestBody Map<String, String> map) {
		userSelCourse.delComment(map.get("commentId"));
	}
	
	@ResponseBody
	@RequestMapping(params="method=cancelCollect")
	public void cancelCollect(@RequestBody Map<String, String> map) {
		userSelCourse.cancelCollect(map.get("courseId"));
	}
	
	@ResponseBody
	@RequestMapping(params="method=getMyCollect")
	public String getMyCollect(HttpServletRequest request,HttpServletResponse response) throws IOException {
		   request.setCharacterEncoding("utf-8");
		   response.setContentType("application/json;charset=UTF-8");
		   int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		   int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		   int startRow = (pageNumber - 1) * pageSize;
		   String id = request.getParameter("id");
			Map map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("start", startRow);//当前记录数
			map.put("size", pageSize);
			Map<String, Object> dataMap = new HashMap<String, Object>();
			int toal = userSelCourse.getCollectCount(id);
			List<Map<String, Object>> myCollect = userSelCourse.getMyCollect(map);
			JSONArray jsonArray = JSONArray.fromObject(myCollect);
			dataMap.put("rows", jsonArray);
			dataMap.put("total", toal);
			JSONObject result = JSONObject.fromObject(dataMap); 
			return result.toString();
	}
	
	@ResponseBody
	@RequestMapping(params="method=getMyLabel")
	public List<Map<String, Object>> getMyLabel(@RequestBody Map<String, String> map) {
		List<Map<String, Object>> list = userSelCourse.getMyLabel(map.get("id"));
		System.out.println(list);
		return list;
	}
	
	@ResponseBody
	@RequestMapping(params="method=getIntegral")
	public String getIntegral(HttpServletRequest request,HttpServletResponse response) throws IOException {
		   request.setCharacterEncoding("utf-8");
		   response.setContentType("application/json;charset=UTF-8");
		   int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		   int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		   int startRow = (pageNumber - 1) * pageSize;
		   String id = request.getParameter("id");
			Map map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("start", startRow);//当前记录数
			map.put("size", pageSize);
			Map<String, Object> dataMap = new HashMap<String, Object>();
			int total = userSelCourse.getIntegralCount(id);
			List<Map<String, Object>> myIntegral = userSelCourse.getIntegral(map);
			JSONArray jsonArray = JSONArray.fromObject(myIntegral);
			dataMap.put("rows", jsonArray);
			dataMap.put("total", total);
			JSONObject result = JSONObject.fromObject(dataMap); 
			return result.toString();
	}
	
	@ResponseBody
	@RequestMapping(params="method=getScoreDetail")
	public Map<String,Object> getScoreDetail(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");
		String id = request.getParameter("id");
		Map<String,Object> map = userSelCourse.getScoreDetail(id);
		System.out.println(map);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(params="method=isChoose")
	public void isChoose(HttpServletRequest request,HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8"); 
		PrintWriter pw =response.getWriter(); 
		String id = request.getParameter("id");
		System.out.println(id);
		String isChoose = userSelCourse.isChoose(id);
		System.out.println(isChoose);
		pw.print(isChoose);
	}
	
	@ResponseBody
	@RequestMapping(params="method=courseChoose")
	public void courseChoose(HttpServletRequest request,HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8"); 
		PrintWriter pw =response.getWriter(); 
		String id = request.getParameter("id");
		int isChoose = userSelCourse.courseChoose(id);
		System.out.println(isChoose);
		pw.print(isChoose);
	}
	
	@ResponseBody
	@RequestMapping(params="method=sureChoose")
	public void sureChoose(HttpServletRequest request,HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8"); 
		String id = request.getParameter("id");
		String userId = request.getParameter("userId");
		userSelCourse.sureChoose(id,userId);
	}
	
	@ResponseBody
	@RequestMapping(params="method=getCenterInfo")
	public Map<String,Object> getCenterInfo(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");
		String id = request.getParameter("id");
		Map<String,Object> map = userSelCourse.getCenterInfo(id);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(params="method=cancleCourse")
	public void cancleCourse(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");
		String id = request.getParameter("id");
		userSelCourse.cancleCourse(id);
	}
	
	@ResponseBody
	@RequestMapping(params="method=findAllStudent")
	public String findAllStudent(HttpServletRequest request,HttpServletResponse response) throws IOException {
		   request.setCharacterEncoding("utf-8");
		   response.setContentType("application/json;charset=UTF-8");
		   int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		   int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		   int startRow = (pageNumber - 1) * pageSize;
		   String id = request.getParameter("id");
			Map map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("start", startRow);//当前记录数
			map.put("size", pageSize);
			Map<String, Object> dataMap = new HashMap<String, Object>();
			int toal = userSelCourse.allStudentNum(id);
			List<Map<String, Object>> allStudent = userSelCourse.findAllStudent(map);
			JSONArray jsonArray = JSONArray.fromObject(allStudent);
			dataMap.put("rows", jsonArray);
			dataMap.put("total", toal);
			JSONObject result = JSONObject.fromObject(dataMap); 
			return result.toString();
	}
	
	@ResponseBody
	@RequestMapping(params="method=findAllCourses")
	public String findAllCourses(HttpServletRequest request,HttpServletResponse response) throws IOException {
		   request.setCharacterEncoding("utf-8");
		   response.setContentType("application/json;charset=UTF-8");
		   int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		   int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		   int startRow = (pageNumber - 1) * pageSize;
		   String id = request.getParameter("id");
		   System.out.println(id);
			Map map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("start", startRow);//当前记录数
			map.put("size", pageSize);
			Map<String, Object> dataMap = new HashMap<String, Object>();
			int toal = userSelCourse.allCourseNum(id);
			System.out.println(toal);
			List<Map<String, Object>> allCourses = userSelCourse.findAllCourses(map);
			System.out.println(allCourses);
			JSONArray jsonArray = JSONArray.fromObject(allCourses);
			dataMap.put("rows", jsonArray);
			dataMap.put("total", toal);
			JSONObject result = JSONObject.fromObject(dataMap); 
			return result.toString();
	}
	
	@ResponseBody
	@RequestMapping(params="method=getAllTeacher")
	public List<Map<String, Object>> getAllTeacher(HttpServletRequest request,HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");
		String id = request.getParameter("id");
		List<Map<String, Object>> list = userSelCourse.getAllTeacher(id);
		return list;	
	}
	
	@ResponseBody
	@RequestMapping(params="method=insertCourse")
	public void insertCourse(@RequestBody Map<String, String> map) {
		String pid = map.get("pid");
		String name = map.get("name");
		String content = map.get("content");
		CourseTree courseTree = new CourseTree();
		courseTree.setPid(pid);
		courseTree.setName(name);
		courseTree.setContent(content);
		userSelCourse.insertCourse(courseTree);
	}
	
	@ResponseBody
	@RequestMapping(params="method=insertStudent")
	public void insertStudent(HttpServletRequest request,HttpServletResponse response) throws IOException, FileUploadException {
			//1、创建一个DiskFileItemFactory工厂
	        DiskFileItemFactory factory = new DiskFileItemFactory();
	        //2、创建一个文件上传解析器
	        ServletFileUpload upload = new ServletFileUpload(factory);
	        //解决上传文件名的中文乱码
	        upload.setHeaderEncoding("utf-8"); 
			factory.setSizeThreshold(1024 * 5000);//设置内存的临界值为500K
			File linshi = new File("E:\\up");//当超过500K的时候，存到一个临时文件夹中
			factory.setRepository(linshi);
			upload.setSizeMax(1024 * 1024 * 5);//设置上传的文件总的大小不能超过5M
			try {
				// 1. 得到 FileItem 的集合 items
				@SuppressWarnings("unchecked")
				List<FileItem> /* FileItem */items = upload.parseRequest(request);
				// 2. 遍历 items:
				for (FileItem item : items) {
					// 若是一个一般的表单域, 打印信息
					if (item.isFormField()) {
						String name = item.getFieldName();
						String value = item.getString("utf-8");
						System.out.println(name + ": " + value);									
					}
					// 若是文件域则把文件保存到 e:\\files 目录下.
					else {
						String fileName = item.getName();//这边获取的为详细路径
						long sizeInBytes = item.getSize();
						System.out.println("文件大小为--------------:" + sizeInBytes);
						InputStream in = item.getInputStream();
						byte[] buffer = new byte[1024];
						int len = 0;
						System.out.println("fileName的值为：" + fileName);
						File tempFile =new File(fileName.trim()); 
						fileName = tempFile.getName(); //仅获取文件名
						String path = "D:\\image\\user\\";
						File filePath = new File(path);
						if (!filePath.exists()) {
							filePath.mkdir(); //如果不存在路径 则创建
						}
						Random random = new Random();
					    //String raString = String.valueOf(random.nextInt(300));
						//String fileUploadPath = "D:\\image\\user\\" + fileName ;//文件最终上传的位置
						String fileUploadPath = "D:\\image\\user\\" + fileName ;
						OutputStream out = new FileOutputStream(fileUploadPath);
						while ((len = in.read(buffer)) != -1) {
							out.write(buffer, 0, len);
						}
						String jsonStr = new String(request.getParameter("jsonStr").getBytes("iso8859-1"),"UTF-8");
						System.out.println(jsonStr);
						//String jsonStr = request.getParameter("jsonStr");
						JSONObject jsonObject = JSONObject.fromObject(jsonStr);
						String xString = ((Object)(jsonObject.get("x"))).toString();
						String yString = ((Object)(jsonObject.get("y"))).toString(); 
						String hString = ((Object)(jsonObject.get("height"))).toString();
						String wString = ((Object)(jsonObject.get("width"))).toString(); 
						int x = Double.valueOf(xString).intValue();
						int y = Double.valueOf(yString).intValue();
						int height = Double.valueOf(hString).intValue();
						int width = Double.valueOf(wString).intValue();
						//裁剪图片操作  
						/**
						 * 图片裁剪
						 * @param srcImageFile 图片裁剪地址
						 * @param result 图片输出文件夹
						 * @param destWidth 图片裁剪宽度
						 * @param destHeight 图片裁剪高度
						 */
						String type = "";
						String prefix = fileName.substring(fileName.lastIndexOf(".")+1);     
						if ("png".equalsIgnoreCase(prefix)) {
							type="PNG";
						} else if ("jpg".equalsIgnoreCase(prefix)) {
							type="JPEG";
						} else if ("gif".equalsIgnoreCase(prefix)) {
							type = "GIF";
						}
				        Iterator iterator = ImageIO.getImageReadersByFormatName(type);//PNG,BMP   
				        ImageReader reader = (ImageReader)iterator.next();//获取图片尺寸
				        InputStream img = new FileInputStream(fileUploadPath);  
				        ImageInputStream iis = ImageIO.createImageInputStream(img);   
				        reader.setInput(iis, true);   
				        ImageReadParam param = reader.getDefaultReadParam();   
				        Rectangle rectangle = new Rectangle(x, y, width, height);//*指定截取范围*/    
				        param.setSourceRegion(rectangle);   
				        BufferedImage bi = reader.read(0,param); 
				      
				        String photoAdr = "D:\\image\\user\\" + fileName;
				        String finalAdr = "/user/" + fileName;
				        System.out.println(finalAdr);
				        ImageIO.write(bi, "JPEG", new File("D:\\image\\user\\" + fileName));
						out.close();
						in.close();
						System.out.println(jsonObject.get("user_name").toString());
						System.out.println(jsonObject.get("user_sex").toString());
						//入库操作
						UserInfo userInfo = new UserInfo();
						userInfo.setUser_id(jsonObject.get("user_id").toString());
						userInfo.setUser_password(jsonObject.get("user_pwd").toString());
						userInfo.setUser_name(jsonObject.get("user_name").toString());
						userInfo.setUser_birth(jsonObject.get("user_date").toString());
						userInfo.setUser_sex(jsonObject.get("user_sex").toString());
						userInfo.setPhoto_adr(finalAdr);
						userSelCourse.insertStudent(userInfo);
						System.out.println(userSelCourse);
					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
	}
	
	@ResponseBody
	@RequestMapping(params="method=findAllMyStudent")
	public String findAllMyStudent(HttpServletRequest request,HttpServletResponse response) throws IOException {
		   request.setCharacterEncoding("utf-8");
		   response.setContentType("application/json;charset=UTF-8");
		   int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		   int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		   int startRow = (pageNumber - 1) * pageSize;
		   String id = request.getParameter("id");
			Map map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("start", startRow);//当前记录数
			map.put("size", pageSize);
			Map<String, Object> dataMap = new HashMap<String, Object>();
			int toal = userSelCourse.allMyStudentNum(id);
			List<Map<String, Object>> allMyStudent = userSelCourse.findAllMyStudent(map);
			JSONArray jsonArray = JSONArray.fromObject(allMyStudent);
			dataMap.put("rows", jsonArray);
			dataMap.put("total", toal);
			JSONObject result = JSONObject.fromObject(dataMap); 
			return result.toString();
	}
	
	@ResponseBody
	@RequestMapping(params="method=findAllMyCourse")
	public String findAllMyCourse(HttpServletRequest request,HttpServletResponse response) throws IOException {
		   request.setCharacterEncoding("utf-8");
		   response.setContentType("application/json;charset=UTF-8");
		   int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		   int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		   int startRow = (pageNumber - 1) * pageSize;
		   String id = request.getParameter("id");
			Map map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("start", startRow);//当前记录数
			map.put("size", pageSize);
			Map<String, Object> dataMap = new HashMap<String, Object>();
			int toal = userSelCourse.allMyCourseNum(id);
			List<Map<String, Object>> allMyStudent = userSelCourse.findAllMyCourse(map);
			JSONArray jsonArray = JSONArray.fromObject(allMyStudent);
			dataMap.put("rows", jsonArray);
			dataMap.put("total", toal);
			JSONObject result = JSONObject.fromObject(dataMap); 
			return result.toString();
	}
}
