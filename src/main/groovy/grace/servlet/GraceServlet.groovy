package grace.servlet

import grace.app.GraceApp
import grace.controller.WebRequest
import grace.route.Route
import grace.route.Routes
import groovy.util.logging.Slf4j
import javax.servlet.GenericServlet
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Slf4j
class GraceServlet extends GenericServlet {
    @Override
    void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req
        HttpServletResponse response = (HttpServletResponse) res
        Route route = Routes.routes.find { it.path == request.requestURI }
        if (route) {
            WebRequest webRequest = new WebRequest(request: request, response: response)
            Closure closure = route.closure.clone()
            closure.delegate = webRequest
            closure.setResolveStrategy(Closure.DELEGATE_ONLY)

            GraceApp.instance.waitingForRefresh()

            Object result
            use(GraceCategory.class) {
                result = closure()
            }

            log.info("$route.path returned $result")
        } else {
            res.writer.write("No route fond")
        }
    }
}
