package controller;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/home","/"})
public class HomeServlet extends HttpServlet{
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){

        ServletContext sc = request.getServletContext();
		
	try{	response.setContentType("text/html");
       response.setCharacterEncoding("UTF-8");
        sc.getRequestDispatcher("/jsp/home.jsp").forward(request, response); 
}catch (Exception e) {}
    }
}