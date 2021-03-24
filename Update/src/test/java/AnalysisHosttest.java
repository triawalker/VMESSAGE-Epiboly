import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

public class AnalysisHosttest {

    public static void main(String[] args) throws UnknownHostException {

            InetAddress[] addresses = InetAddress.getAllByName("ssw.xzzpig.com");
            for (InetAddress addr : addresses) {
                System.out.println(addr.getHostAddress());
            }
        }


    public AnalysisHosttest(){
        URL url= null;
        try {
            url = new URL("www.baidu.com");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        System.out.println(url.getHost());

    }
}
