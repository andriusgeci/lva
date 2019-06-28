package Servlet;

import Command.Command;
import Command.CommandFactory;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/UserActionServlet"})
public class UserActionServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserActionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        processRequest(request, response);
    }

    /**
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set the end destination as a backup, in case something goes wrong
        String forwardToJsp = "";

        //Check the 'action' parameter to see what the user wants...
        if (request.getParameter("action") != null) {
            // Create an appropriate Command and execute it
            CommandFactory factory = new CommandFactory();
            Command command = factory.createCommand(request.getParameter("action"));
            // execute generated Command
            forwardToJsp = command.execute(request, response);
        }

        //Get the request dispatcher object and forward the request to the appropriate JSP page...
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(forwardToJsp);
        dispatcher.forward(request, response);

    }
}
