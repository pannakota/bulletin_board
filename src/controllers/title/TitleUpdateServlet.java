package controllers.title;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Title;
import models.validators.TitleValidator;
import utils.DBUtil;


/**
 * Servlet implementation class TitleUpdateServlet
 */
@WebServlet("/titles/update")
public class TitleUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TitleUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	      String _token = (String)request.getParameter("_token");
	        if(_token != null && _token.equals(request.getSession().getId())) {
	            EntityManager em = DBUtil.createEntityManager();

	            Title e = em.find(Title.class, (Integer)(request.getSession().getAttribute("title_id")));

	            e.setTitle(request.getParameter("title"));
	            e.setContent(request.getParameter("content"));



	            e.setDelete_flag(0);

	            List<String> errors = TitleValidator.validate(e);
	            if(errors.size() > 0) {
	                em.close();

	                request.setAttribute("_token", request.getSession().getId());
	                request.setAttribute("employee", e);
	                request.setAttribute("errors", errors);

	                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/titles/edit.jsp");
	                rd.forward(request, response);
	            } else {
	                em.getTransaction().begin();
	                em.getTransaction().commit();
	                em.close();
	                request.getSession().setAttribute("flush", "更新が完了しました。");

	                request.getSession().removeAttribute("title_id");

	                response.sendRedirect(request.getContextPath() + "/titles/index");
	            }

	        }


	}

}
