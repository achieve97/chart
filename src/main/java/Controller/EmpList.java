package Controller;

// ctrl+shift+O
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import DAO.EmpDao;
import DTO.Emp;
import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;


@WebServlet("*.do")
public class EmpList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EmpList() {
		super();
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	 	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String url_Command = requestURI.substring(contextPath.length());
	
    	Action action=null;
    	ActionForward forward=null;
    	
    	if(url_Command.equals("/data.do")) { // get
    		//UI+로직

    		ArrayList<Emp> list = new ArrayList<>();
    		
    		try {
    			EmpDao dao = new EmpDao();
    			list = dao.getAvgSalList();
    			
    			JSONArray empList = new JSONArray();
    			for(Emp e : list) {
    				JSONObject obj = new JSONObject();
    				obj.put("job", e.getJob().toString());
    				obj.put("avg", String.valueOf(e.getAvgSal()));
    				empList.add(obj);
    			}
    			
    			PrintWriter out = response.getWriter();
    	         response.setContentType("application/json; charset=uft-8");
    	         JSONObject obj = new JSONObject();
    	         obj.put("empList", empList);
    	         String result = obj.toJSONString();
    	         out.print(result);
    	         response.setStatus(200);
			} catch (Exception e) {
				// TODO: handle exception
			}
    		
    	}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}
