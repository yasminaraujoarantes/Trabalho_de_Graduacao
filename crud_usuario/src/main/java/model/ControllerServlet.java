package model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private UsuarioService service;
 
    public void init() {
    	service = new UsuarioService();
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
 
        try {
            switch (action) {
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
                insertUsuario(request, response);
                break;
            case "/delete":
                deleteUsuario(request, response);
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
                updateUsuario(request, response);
                break;
            default:
                listUsuario(request, response);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
 
    private void listUsuario(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
 
        List<Usuario> listUsuario = service.listAllUsuario();
        
        request.setAttribute("listUsuario", listUsuario);
        RequestDispatcher dispatcher = request.getRequestDispatcher("UsuarioList.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("UsuarioForm.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Usuario existingUsuario = service.getUsuario(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("UsuarioForm.jsp");
        request.setAttribute("usuario", existingUsuario);
        dispatcher.forward(request, response);
 
    }
 
    private void insertUsuario(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        int idade = Integer.parseInt(request.getParameter("idade"));
 
        Usuario newUsuario = new Usuario(nome, email, senha, idade);
        service.insertUsuario(newUsuario);
        response.sendRedirect("list");
    }
 
    private void updateUsuario(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        System.out.println(request.getParameter("idade"));
        int idade = Integer.parseInt(request.getParameter("idade"));
 
        Usuario usuario = new Usuario(id, nome, email, senha, idade);
        service.updateUsuario(usuario);
        response.sendRedirect("list");
    }
 
    private void deleteUsuario(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
 
        Usuario usuario = new Usuario(id);
        service.deleteUsuario(usuario);
        response.sendRedirect("list");
 
    }
}
