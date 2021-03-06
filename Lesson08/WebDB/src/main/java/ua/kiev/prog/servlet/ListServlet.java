package ua.kiev.prog.servlet;

import ua.kiev.prog.entity.User;
import ua.kiev.prog.service.DataService;
import ua.kiev.prog.service.DataServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class ListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();
        DataService service = new DataServiceImpl(em);

        try {
            List<User> userList = service.getAllUsers();
            req.setAttribute("userList", userList);
        } finally {
            em.close();
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/result.jsp");
        dispatcher.forward(req, resp);
    }
}
