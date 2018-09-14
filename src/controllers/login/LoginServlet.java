package controllers.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setAttribute("_token", request.getSession().getId());
        request.setAttribute("hasError", false);
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        Cookie cookie[] = request.getCookies();

        if (cookie != null){
            for (int i = 0 ; i < cookie.length ; i++){
              if (cookie[i].getName().equals("nickname")){
                String val = cookie[i].getValue();
                request.setAttribute("nickname", val);

              }
            }
          }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/login/login.jsp");
        rd.forward(request, response);
	}

	   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	        String name = request.getParameter("name");

	        request.getSession().setAttribute("name_login", name);

	        Cookie cookie = new Cookie("nickname", name);
	        response.addCookie(cookie);

	        request.getSession().setAttribute("flush", "ログインしました。");
	        response.sendRedirect(request.getContextPath() + "/titles/index");




	    }



}
