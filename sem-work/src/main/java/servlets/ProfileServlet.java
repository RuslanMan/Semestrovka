package servlets;

import managers.Page;
import managers.TemplateManager;
import models.User;
import repositories.UserRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@MultipartConfig
public class ProfileServlet extends HttpServlet {
    TemplateManager templateManager;
    UserRepository userRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        templateManager = (TemplateManager) context.getAttribute("templateManager");
        userRepository = (UserRepository) context.getAttribute("userRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();
        User user = (User) req.getAttribute("user");
        if (user == null) {
            templateManager.write(Page.login, resp, root);
            return;
        }
        root.put("user", user);
        templateManager.write(Page.profile, resp, root);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();
        User user = (User) req.getAttribute("user");
        if (user == null) {
            templateManager.write(Page.login, resp, root);
            return;
        }
        String param = req.getParameter("name");
        if ("image".equals(param)) {
            Part part = req.getPart("value");
            if (part.getSize() == 0) {
                resp.setStatus(400);
                return;
            }
        }
        root.put("user", user);
        templateManager.write(Page.profile, resp, root);
    }
}
