package controllers.reply;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Reply;
import models.validators.ReplyValidator;
import utils.DBUtil;

/**
 * Servlet implementation class ReplysCreateServlet
 */
@WebServlet("/replys/create")
public class ReplysCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplysCreateServlet() {
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

              Reply e = new Reply();

              e.setTitle_id(Integer.parseInt(request.getParameter("title_id")));
              e.setReply(request.getParameter("content"));
              Timestamp currentTime = new Timestamp(System.currentTimeMillis());
              e.setCreated_at(currentTime);



              List<String> errors = ReplyValidator.validate(e);
              if(errors.size() > 0) {
                  em.close();

                  request.setAttribute("_token", request.getSession().getId());
                  request.setAttribute("reply", e);
                  request.setAttribute("errors", errors);

                  RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/replys/new.jsp");
                  rd.forward(request, response);
              } else {
                  em.getTransaction().begin();
                  em.persist(e);
                  em.getTransaction().commit();
                  em.close();
                  request.getSession().setAttribute("flush", "返信を送りました");


                  request.getSession().setAttribute("sid", request.getParameter("title_id"));

                  response.sendRedirect(request.getContextPath() +"/replys/redirect");
              }
        }
	}
}
