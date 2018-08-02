package SOAP;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;

import javax.xml.ws.Endpoint;
import java.lang.reflect.Method;

public class SOAP_Start {
    public static void init() throws Exception{

        Server server = new Server(7777);
        ContextHandlerCollection contextHandlerCollection = new ContextHandlerCollection();
        server.setHandler(contextHandlerCollection);
        server.start();
    }
}
