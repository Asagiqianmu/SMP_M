package com.pms.code.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pms.code.util.DateUtil;
import com.pms.code.util.FileTool;
import com.pms.code.util.JsonUtil;
import com.pms.code.util.MD5;
import com.pms.code.util.RandomUtils;

@Controller
public class FileUploadController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(FileUploadController.class);

	
	public static void main(String[] args) {
		String separator = File.separator;
		String path = "C:\\tomcat-8.0.50_8082\\webapps\\v1\\";
		String Apath = path.substring(0, path.lastIndexOf(separator));
		Apath = Apath.substring(0, Apath.lastIndexOf(separator));
		System.out.println(Apath);
		String filename = "http://tmp/wxf56fd8fb6499bccb.o6zAJsyptk_c1I3wbUO03PULGI_0.8VnYF19mteSz9134b44c2c01d53d397eece6116b6929.png";
		String suffix = filename.substring(filename.lastIndexOf("."));
		System.out.println(suffix);
	
	}
	/**
	 * 文件上传
	 * 
	 * @author dengfei E-mail:dengfei200857@163.com
	 * @time 2018年4月4日 上午10:39:32
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String fileupload() {
		String separator = File.separator;
		String pathStr = DateUtil.getDatePath();
		//文件存储文件夹目录
		String filePath = "images" + separator + "liveimg" + separator + pathStr;
		String path = request.getSession().getServletContext().getRealPath("/");
		String Apath = path.substring(0, path.lastIndexOf(separator));
		Apath = Apath.substring(0, Apath.lastIndexOf(separator));
		//文件存储文件夹全目录
		String savePath = Apath + separator + filePath;
		logger.error(savePath);
		File file = new File(savePath);
		// 判断上传文件的保存目录是否存在
		if (!file.exists() && !file.isDirectory()) {
			logger.error(savePath + "目录不存在，需要创建");
			FileTool.createDir(savePath);
		}
		// 消息提示
		String message = "";
		try {
			// 使用Apache文件上传组件处理文件上传步骤：
			// 1、创建一个DiskFileItemFactory工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 2、创建一个文件上传解析器
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 解决上传文件名的中文乱码
			upload.setHeaderEncoding("UTF-8");
			// 3、判断提交上来的数据是否是上传表单的数据
			if (!ServletFileUpload.isMultipartContent(request)) {
				// 按照传统方式获取数据
				logger.error("没有文件上传");
				return "index.html";
			}
			// 4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem item : list) {
				// 如果fileitem中封装的是普通输入项的数据
				if (item.isFormField()) {
					String name = item.getFieldName();
					// 解决普通输入项的数据的中文乱码问题
					String value = item.getString("UTF-8");
					// value = new String(value.getBytes("iso8859-1"),"UTF-8");
					logger.error("编码：" + name + "=" + value);
				} else {// 如果fileitem中封装的是上传文件,得到上传的文件名称，
					String filename = item.getName();
					logger.error("得到上传的文件名称:" + filename);
					if (filename == null || filename.trim().equals("")) {
						continue;
					}
					// 注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：
					// c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
					// 处理获取到的上传文件的文件名的路径部分，只保留文件名部分
					filename = filename.substring(filename.lastIndexOf(separator) + 1);
					String newfilename = MD5.MD5Encode(filename+RandomUtils.generateString(10)+new Date().getTime());
					String suffix = filename.substring(filename.lastIndexOf("."));
					// 获取item中的上传文件的输入流
					InputStream in = item.getInputStream();
					// 创建一个文件输出流
					FileOutputStream out = new FileOutputStream(savePath + separator + newfilename + suffix);
					// 创建一个缓冲区
					byte buffer[] = new byte[1024];
					// 判断输入流中的数据是否已经读完的标识
					int len = 0;
					// 循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
					while ((len = in.read(buffer)) > 0) {
						// 使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + separator + newfilename + suffix)当中
						out.write(buffer, 0, len);
					}
					// 关闭输入流
					in.close();
					// 关闭输出流
					out.close();
					// 删除处理文件上传时生成的临时文件
					item.delete();
					message = "文件上传成功！";
					//拿到文件存储相对路径
					String file_path = filePath + separator + newfilename + suffix;
					map.put("code", "200");
					map.put("img", file_path);
					logger.error("---------------------"+message);
					logger.error("---------------------"+map);
					return JsonUtil.objectToJson(map);
				}
			}
		} catch (Exception e) {
			message = "文件上传失败！";
			e.printStackTrace();
		}
		map.put("code", "201");
		map.put("msg", message);
		logger.error("---------------------"+message);
		logger.error("---------------------"+map);
		return JsonUtil.objectToJson(map);
	}
	
}
