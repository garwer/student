package course.util;

import java.io.IOException;

import java.io.File;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jfree.chart.servlet.ChartDeleter;
import org.jfree.chart.servlet.ServletUtilities;



public class DisplayChartCustServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		  
	}
	
	public void service(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException {
		HttpSession session = request.getSession();
	    File file = null;
	    String tmpdir = System.getProperty("java.io.tmpdir");
	    String filename = request.getParameter("filename");
    	System.out.println("filename" + filename);
	    if (filename == null) {
	      throw new ServletException("Parameter 'filename' must be supplied");
	    }
	    
	    
	    

	    filename = ServletUtilities.searchReplace(filename, "..", "");
	    if (filename.indexOf("/") < 0)
	    {
	      System.out.println("aaa" + filename);
	      file = new File(tmpdir, filename);
	    }
	    else
	    {
	      System.out.println("bbb" + filename);
	      file = new File(filename);
	    }

	    if (!file.exists()) {
	      throw new ServletException("File '" + file.getAbsolutePath() + 
	        "' does not exist");
	    }

	    boolean isChartInUserList = false;
	    ChartDeleter chartDeleter = (ChartDeleter)session.getAttribute(
	      "JFreeChart_Deleter");
	    String theFileName = file.getName();
	    if (chartDeleter != null) {
	      isChartInUserList = chartDeleter.isChartAvailable(theFileName);
	    }

	    boolean isChartPublic = false;
	    if ((theFileName.length() >= 6) && 
	      (theFileName.substring(0, 6).equals("public"))) {
	      isChartPublic = true;
	    }

	    boolean isOneTimeChart = false;
	    if (theFileName.startsWith(ServletUtilities.getTempOneTimeFilePrefix())) {
	      isOneTimeChart = true;
	    }
	    ServletUtilities.sendTempFile(file, response);
	    if ((isChartInUserList) || (isChartPublic) || (isOneTimeChart))
	    {
	      if (isOneTimeChart) {
	        file.delete();
	      }
	    }else {
	    	
	    }
	  }
	}
