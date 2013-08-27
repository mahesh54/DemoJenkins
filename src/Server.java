/*import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;
import com.thoughtworks.selenium.Selenium;
import java.net.BindException;
import java.net.ServerSocket;
 
public class Server {
    public static SeleniumServer sserver;
    public static void startSeleniumServer() throws Exception {
 
       try {
        ServerSocket serverSocket = new ServerSocket(4444);
        serverSocket.close();
                //Server not up, start it
                try {
                 RemoteControlConfiguration rcc = new RemoteControlConfiguration();
                 rcc.setPort(4444);
                 sserver = new SeleniumServer(false, rcc);
 
                } catch (Exception e) {
                    System.err.println("Could not create Selenium Server because of: "
                            + e.getMessage());
                    e.printStackTrace();
                }
                try {
                	sserver.start();
                    System.out.println("Server started");
                    
                } catch (Exception e) {
                    System.err.println("Could not start Selenium Server because of: "
                            + e.getMessage());
                    e.printStackTrace();
                }
            } catch (BindException e) {
                System.out.println("Selenium server already up, will reuse...");
            }
    }
 
    public static void stopSeleniumServer(Selenium selenium){
        selenium.stop();
        if (sserver != null)
          {
             try
             {
                 selenium.shutDownSeleniumServer();
                 sserver.stop();
 
                 sserver = null;
             }
             catch (Exception e)
             {
                e.printStackTrace();
             }
          }
    }
    public static void main(String args[]){
    	try {
			Server.startSeleniumServer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 
}
*/