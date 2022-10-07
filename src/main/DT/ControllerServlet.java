package main.DT;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import main.BO.ItemManagement;
import main.BO.UserManagement;

import java.io.IOException;
import java.util.List;

public class ControllerServlet extends HttpServlet {
    private UserManagement userManagement;
    private ItemManagement itemManagement;

    @Override
    public void init() throws ServletException
    {
        userManagement = new UserManagement();
        itemManagement = new ItemManagement();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {
        System.out.println("do get!");
        String requestAction = request.getServletPath();
        RequestDispatcher dispatcher;
        switch(requestAction)
        {
            case"/getStore":
                getStore(request,response);
                dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request,response);
                break;
            case"/getCart":
                getCart(request,response);
                dispatcher = request.getRequestDispatcher("index.jsp");
                break;
            default:
                getStore(request,response);
                dispatcher = request.getRequestDispatcher("index.jsp");
                break;
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {
        System.out.println("do post!");
        String requestAction = request.getServletPath();
        switch(requestAction)
        {
            case"/login":
                login(request,response);
                break;
            case"/logout":
                logout(request,response);
                break;
            case"/addStoreItem":
                addStoreItem(request,response);
                break;
            case"/addCartItem":
                addCartItem(request,response);
                break;
            default:
                break;
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDTO user = userManagement.login(new UserDTO(username,password));
        System.out.println("logging in "+ username + " " + password);
        if(user != null)
        {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request,response);
        }
        else
        {
            response.sendRedirect("login.jsp");
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        session.invalidate();
        response.sendRedirect("login.jsp");
    }
    private void getStore(HttpServletRequest request, HttpServletResponse response)
    {
        System.out.println("get Store!");
        List<ItemDTO> list = itemManagement.getStorage();
        request.setAttribute("storeList",list);
    }
    private void getCart(HttpServletRequest request, HttpServletResponse response)
    {
        System.out.println("getFromCart");
    }
    private void addStoreItem(HttpServletRequest request, HttpServletResponse response)
    {
        System.out.println("addToStore");
    }
    private void addCartItem(HttpServletRequest request, HttpServletResponse response)
    {
        System.out.println("addToCart");
    }
}
