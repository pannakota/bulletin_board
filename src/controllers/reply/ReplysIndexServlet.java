package controllers.reply;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Reply;
import models.Title;
import utils.DBUtil;

/**
 * Servlet implementation class ReplysIndexServlet
 */
@WebServlet("/replys/index")
public class ReplysIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplysIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    EntityManager em = DBUtil.createEntityManager();

        Title ee = (Title) em.find(Title.class, Integer.parseInt(request.getParameter("id")));



        request.setAttribute("title", ee);
        request.setAttribute("_token", request.getSession().getId());

        int page;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        } catch(Exception e) {
            page = 1;
        }
        List<Reply> replys = em.createNamedQuery("getAllReports" , Reply.class)
                                  .setParameter(1, Integer.parseInt(request.getParameter("id")))
                                  .setFirstResult(15 * (page - 1))
                                  .setMaxResults(15)
                                  .getResultList();

        long replys_count = (long)em.createNamedQuery("getReportsCount", Long.class)
                                     .setParameter(1, Integer.parseInt(request.getParameter("id")))
                                     .getSingleResult();

        em.close();

        request.setAttribute("replys", replys);
        request.setAttribute("replys_count", replys_count);
        request.setAttribute("page", page);
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }


        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/replys/index.jsp");
        rd.forward(request, response);


	}

}
