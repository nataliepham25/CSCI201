package nqpham_CSCI201_Lab11;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SchoolFormServlet")
public class SchoolFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String fname= request.getParameter("fname");
		String lname = request.getParameter("lname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String birthday = request.getParameter("birthday");
		String newStudent = request.getParameter("newstudent");
		String phone = request.getParameter("phone");
		String dropOut = request.getParameter("dropout");
		String college = request.getParameter("college");
		String major = request.getParameter("major");
		String color = request.getParameter("favcolor");
		String text = request.getParameter("textarea");
		String termsAndConditions = request.getParameter("agree");
		//printData(fname, lname, email, password, birthday, newStudent, phone, college, major, color, termsAndConditions);
		
		//String json = formJson(fname, lname, email, password, birthday, newStudent, phone, dropOut, college, major, color, text, termsAndConditions);
		
		String json = "{"+ "\n"+
				"\"fname\":\"" + fname + "\""+",\n"+
				"\"lname\":\"" + lname+"\""+",\n"+
				"\"email\":\"" + email + "\""+",\n"+
                "\"password\":\"" + password +"\""+",\n"+
                "\"birthday\":\"" + birthday + "\""+",\n"+
                "\"new student\":\"" + newStudent +"\""+",\n"+
                "\"phone\":\"" + phone + "\""+",\n"+
                "\"college\":\"" + college +"\""+",\n"+
                "\"major\":\"" + major + "\""+",\n"+
                "\"color\":\"" +color + "\""+",\n"+
                "\"text\":\"" + text + "\""+",\n"+
                "\"Terms and conditions\":\"" + termsAndConditions + "\""+",\n"+
                '}';
		
		response.setContentType("application/json");
		out.println(json);
  }
}
