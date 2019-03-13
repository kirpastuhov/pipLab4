package controller;

        import javax.ws.rs.ApplicationPath;
/* это контроллер всего приложение, без него вроде ничего не робит
именно с /web начинаются все пути
*/
@ApplicationPath("/web")
public class ApplicationController extends javax.ws.rs.core.Application {

}