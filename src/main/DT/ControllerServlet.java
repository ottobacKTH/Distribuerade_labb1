/**
 * Authors; Otto & Habib
 */
package main.DT;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import main.BO.ItemManagement;
import main.BO.UserManagement;
import org.apache.coyote.Request;

import java.io.IOException;
import java.util.List;

/**
 * A controller class responsible for managing the requests to the server.
 * Extends HttpServlet
 */
public class ControllerServlet extends HttpServlet {
    private UserManagement userManagement;
    private ItemManagement itemManagement;

    /**
     * Creates the handles to the BO layer. This method runs when the servlet is created
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException
    {
        userManagement = new UserManagement();
        itemManagement = new ItemManagement();
    }

    /**
     * Manages all GET requests: getStore, getCart and getUser
     * @param request The HttpRequest received from the call
     * @param response The HttpResponse to the call
     * @throws ServletException
     * @throws IOException
     */
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
            case"/getUser":
                getUsers(request,response);
                dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request,response);
                break;
            default:
                getStore(request,response);
                dispatcher = request.getRequestDispatcher("index.jsp");
                break;
        }
    }

    /**
     * Manages all the POST request: login, logout, addCartItem, purchaseCart, addUser and removeUser
     * @param request  The HttpRequest received from the call
     * @param response  The HttpResponse to the call
     * @throws ServletException
     * @throws IOException
     */
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
            case"/addCartItem":
                addCartItem(request,response);
                dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request,response);
                break;
            case"/purchaseCart":
                purchaseCart(request,response);
                dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request,response);
                break;
            case "/addUser":
                addUser(request, response);
                dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request,response);
                break;
            case "/removeUser":
                removeUser(request, response);
                dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request,response);
                break;
            default:
                break;
        }
    }

    /**
     * Logs in the user if the specified user could be found. Otherwise redirect back to login.jsp
     * User is kept logged in through the HttpSession, where the userDTO is stored
     * @param request  The HttpRequest received from the call
     * @param response  The HttpResponse to the call
     * @throws ServletException
     * @throws IOException
     */
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

    /**
     * Logs the user out by removin it from the session and invalidating the session.
     * redirects to login.jsp
     * @param request  The HttpRequest received from the call
     * @param response  The HttpResponse to the call
     * @throws ServletException
     * @throws IOException
     */
    private void logout(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        session.invalidate();
        response.sendRedirect("login.jsp");
    }

    /**
     * Gets all users in the system. Appends list as the attribute userList in the request
     * @param request  The HttpRequest received from the call
     * @param response  The HttpResponse to the call
     */
    private void getUsers(HttpServletRequest request, HttpServletResponse response)
    {
        HttpSession session = request.getSession();
        if(((UserDTO) session.getAttribute("user")).getRole().equalsIgnoreCase("admin")) {
            List<UserDTO> list = userManagement.getUsers();
            request.setAttribute("userList", list);
        }
        else{
            throw new IllegalStateException("You have no permission to get users! Only who have 'admin' role can get user.");
        }
    }

    /**
     * Adds a specific user to the system, throws exception if the user attempting to do this isn't an admin
     * @param request  The HttpRequest received from the call
     * @param response  The HttpResponse to the call
     * @throws ServletException
     * @throws IOException
     */
    private void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        HttpSession session = request.getSession();
        if(session.getAttribute("user") instanceof UserDTO && session.getAttribute("user")!= null)
        {
            if(((UserDTO) session.getAttribute("user")).getRole().equalsIgnoreCase("admin")) {
                UserDTO user = (UserDTO) session.getAttribute("user");
                UserDTO newUser = userManagement.addUser(new UserDTO(username, password, role));
            }
            else{
                throw new IllegalStateException("You have no permission to add user! Only who have 'admin' role can add user.");
            }
        }
        else {
            logout(request,response);
        }
    }

    /**
     * Removes a specific user, throws exception if the user attempting to do this isn't an admin
     * @param request  The HttpRequest received from the call
     * @param response  The HttpResponse to the call
     * @throws ServletException
     * @throws IOException
     */
    private void removeUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        HttpSession session = request.getSession();
        if(session.getAttribute("user") instanceof UserDTO && session.getAttribute("user")!= null)
        {
            if(((UserDTO) session.getAttribute("user")).getRole().equalsIgnoreCase("admin")) {
                UserDTO user = (UserDTO) session.getAttribute("user");
                UserDTO newUser = userManagement.removeUser(new UserDTO(username, password, role));
            }
            else{
                throw new IllegalStateException("You have no permission to remove user! Only who have 'admin' role can remove user.");
            }
        }
        else {
            logout(request,response);
        }
    }

    /**
     * Gets all the items in the system
     * @param request  The HttpRequest received from the call
     * @param response  The HttpResponse to the call
     */
    private void getStore(HttpServletRequest request, HttpServletResponse response)
    {
        System.out.println("get Store!");
        List<ItemDTO> list = itemManagement.getStorage();
        request.setAttribute("storeList",list);
    }

    /**
     * Gets all the items in the users cart, user is taken from the session
     * @param request  The HttpRequest received from the call
     * @param response  The HttpResponse to the call
     */
    private void getCart(HttpServletRequest request, HttpServletResponse response)
    {
        System.out.println("getFromCart");
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        List<ItemDTO> list = itemManagement.getCart(user);
        request.setAttribute("cartList",list);
    }

    /**
     * adds a specific item to the cart. Throws exception if amount of items to add to cart is invalid
     * @param request  The HttpRequest received from the call
     * @param response  The HttpResponse to the call
     * @throws IOException
     * @throws ServletException
     */
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
        if(session.getAttribute("user") instanceof UserDTO && session.getAttribute("user")!= null)
        {
            UserDTO user = (UserDTO) session.getAttribute("user");
            itemManagement.addItemToCart(item,user);
        }
        else {
            logout(request,response);
        }
    }

    /**
     * Purchase a users cart. If user is invalid redirects to logout
     * @param request  The HttpRequest received from the call
     * @param response  The HttpResponse to the call
     * @throws ServletException
     * @throws IOException
     */
    private void purchaseCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("purchaseCart");
        HttpSession session = request.getSession();
        if(session.getAttribute("user") instanceof UserDTO && session.getAttribute("user")!= null)
        {
            UserDTO user = (UserDTO) session.getAttribute("user");
            request.setAttribute("purchased", itemManagement.purchaseCart(user));
        }
        else {
            logout(request,response);
        }
    }
}
