package tt.ttt;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
public class Tss {

	public static void main(String[] args) throws Exception {
		Server server = new Server(9999);  
        WebAppContext context = new WebAppContext();  
        context.setContextPath("/eas");  
        context.setWar("D:\\test\\eas.print\\target\\eas.print.war");  
        server.setHandler(context);  
        server.start();
        server.join();  
        /*苟富贵发动和法国  */
	}

}
