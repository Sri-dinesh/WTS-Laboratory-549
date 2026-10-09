import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class StateManagementServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int sessionVisits = ((Integer) session.getAttribute("visitCount") == null)
                ? 1
                : (Integer) session.getAttribute("visitCount") + 1;
        session.setAttribute("visitCount", sessionVisits);

        int cookieVisits = readCookieCount(request) + 1;
        Cookie visitCookie = new Cookie("visitCount", String.valueOf(cookieVisits));
        visitCookie.setMaxAge(60 * 60 * 24 * 365);
        visitCookie.setHttpOnly(true);
        response.addCookie(visitCookie);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html><html lang='en'><head><meta charset='UTF-8'>"
                + "<meta name='viewport' content='width=device-width, initial-scale=1'>"
                + "<title>State Management</title></head><body>");
        out.println("<h1>State Management Example</h1>");
        out.println("<h2>Using Session</h2><p>Session ID: " + escape(session.getId()) + "</p>");
        out.println("<p>Number of visits in this session: " + sessionVisits + "</p>");
        out.println("<h2>Using Cookie</h2><p>Number of visits recorded by cookie: "
                + cookieVisits + "</p></body></html>");
    }

    private int readCookieCount(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("visitCount".equals(cookie.getName())) {
                    try {
                        return Integer.parseInt(cookie.getValue());
                    } catch (NumberFormatException ignored) {
                        return 0;
                    }
                }
            }
        }
        return 0;
    }

    private String escape(String value) {
        return value.replace("&", "&amp;").replace("<", "&lt;")
                .replace(">", "&gt;").replace("\"", "&quot;");
    }
}