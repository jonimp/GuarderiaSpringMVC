package prog2.guarderiaspring;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class Interceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {

        String uri = request.getRequestURI();

        if (uri.equals("/")
                || uri.equals("/acceso")
                || uri.startsWith("/css/")
                || uri.startsWith("/imagenes/")
                || uri.startsWith("/js/")) {
            return true;
        }

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("usuarioLogeado") == null) {
            response.sendRedirect(request.getContextPath() + "/");
            return false;
        }

        return true;
    }
}
