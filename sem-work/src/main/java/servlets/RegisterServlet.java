package servlets;

import managers.Page;
import managers.TemplateManager;
import models.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import repositories.UserRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class RegisterServlet extends HttpServlet {
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
        if (user != null) {
            root.put("user", user);
            templateManager.write(Page.home, resp, root);
            return;
        }
        templateManager.write(Page.register, resp, root);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();
        User user = (User) req.getAttribute("user");
        if (user != null) {
            root.put("user", user);
            templateManager.write(Page.home, resp, root);
            return;
        }
        user = new User(
                req.getParameter("login"),
                req.getParameter("password"),
                req.getParameter("email")
        );
        userRepository.save(user);
        templateManager.write(Page.login, resp, root);
    }
}
