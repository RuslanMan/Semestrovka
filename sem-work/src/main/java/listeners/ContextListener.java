package listeners;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import managers.SimpleTemplateManager;
import managers.TemplateManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import repositories.SimpleUserRepository;
import repositories.UserRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
    public static Configuration cfg;

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
        cfg.setServletContextForTemplateLoading(servletContext, "/WEB-INF/ftl");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        ContextListener.cfg = cfg;
        servletContext.setAttribute("cfg", cfg);

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/semwork");
        dataSource.setUsername("postgres");
        dataSource.setPassword("666425qwe");

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        TemplateManager templateManager = new SimpleTemplateManager(cfg);
        UserRepository userRepository = new SimpleUserRepository(jdbcTemplate);

        servletContext.setAttribute("templateManager", templateManager);
        servletContext.setAttribute("passwordEncoder", passwordEncoder);
        servletContext.setAttribute("userRepository", userRepository);
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
