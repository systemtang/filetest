package un.smart.lab;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class download
 */
@WebServlet("/download.do")
public class download extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public download() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//获取文件下载路径
		String path = getServletContext().getRealPath("/")+"images/";
		String filename = req.getParameter("filename");
		File file = new File(path+filename);
		if(file.exists()){
			//设置相应类型application/octet-stream
			resp.setContentType("application/x-msdownload ");
			
			//设置头信息
			resp.setHeader("Content-Disposition", "attachment;filename=\""+filename+"\"");
			InputStream is = new FileInputStream(file);
			ServletOutputStream sos = resp.getOutputStream();
			byte b[] = new byte[1024];
			int n = 0;
			while((n=is.read(b))!=-1){
				sos.write(b,0,n);
			}
			//关闭流
			is.close();
			sos.close();
			
		}else{
			req.setAttribute("error", "文件不存在");
			RequestDispatcher rd = req.getRequestDispatcher("F01.jsp");
			rd.forward(req, resp);
		}
	}

}
