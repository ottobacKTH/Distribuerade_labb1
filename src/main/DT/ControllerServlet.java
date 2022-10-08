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
                dispatcher.forward(request,response);
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
        RequestDispatcher dispatcher;
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
                dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request,response);
                break;
            case"/addCartItem":
                addCartItem(request,response);
                dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request,response);
                break;
            case "/addUser":
                addUser(request, response);
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
    private void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        UserDTO user = userManagement.addUser(new UserDTO(username,password,role));
        if(user != null){
            HttpSession session = request.getSession();
            session.invalidate();
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request,response);
        }
        else{
            response.sendRedirect("addUser.jsp");
        }

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
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        List<ItemDTO> list = itemManagement.getCart(user);
        request.setAttribute("cartList",list);
    }
    private void addStoreItem(HttpServletRequest request, HttpServletResponse response)
    {
        System.out.println("addToStore");
    }
    private void addCartItem(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("addCart");
        if(request.getParameter("amountToAdd") == null || request.getParameter("amountToAdd").equals(""))
        {
            throw new IllegalStateException("need to add 1 or more items");
        }
        String name = request.getParameter("storeItemName");
        int id = Integer.parseInt(request.getParameter("storeItemId"));
        int price = Integer.parseInt(request.getParameter("storeItemPrice"));
        int amount = Integer.parseInt(request.getParameter("amountToAdd"));
        HttpSession session = request.getSession();
        ItemDTO item = new ItemDTO(id, name, price, amount);
        UserDTO user;
        if(session.getAttribute("user") instanceof UserDTO)
        {
            user = (UserDTO) session.getAttribute("user");
            itemManagement.addItemToCart(item,user);
        }
        else {
            logout(request,response);
        }
    }
}
