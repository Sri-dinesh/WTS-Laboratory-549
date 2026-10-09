package com.greenswap.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        DBUtil.configure(
                getValue(context, "db.url", DBConfig.DEFAULT_URL),
                getValue(context, "db.user", DBConfig.DEFAULT_USER),
                getValue(context, "db.password", DBConfig.DEFAULT_PASSWORD),
                getValue(context, "db.driver", DBConfig.DEFAULT_DRIVER));
    }

    private String getValue(ServletContext context, String key, String fallback) {
        String value = context.getInitParameter(key);
        return value == null || value.trim().isEmpty() ? fallback : value.trim();
    }
}
