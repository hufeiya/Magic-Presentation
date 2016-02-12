import java.io.InputStreamReader;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by hufeiya on 16-2-7.
 */
public class SocketServer {

    private int port;
    public Vector<Action> v;

    public SocketServer(int port){
        this.port = port;
        v = new Vector<Action>();
    }

    public void startListen(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("start listen..");
                    ServerSocket server = new ServerSocket(port);
                    //server尝试接收其他Socket的连接请求，server的accept方法是阻塞式的
                    Socket socket = server.accept();
                    //跟客户端建立好连接之后，我们就可以获取socket的InputStream，并从中读取客户端发过来的信息了。
                    Reader reader = new InputStreamReader(socket.getInputStream());
                    char chars[] = new char[64];
                    int len;
                    StringBuilder sb = new StringBuilder();
                    while (true) {
                        //Thread.sleep(100);
                        if ((len = reader.read(chars)) != -1) {
                            //sb.append(new String(chars, 0, len));
                            //System.out.println("from client: " + sb);
                            String s = new String(chars, 0, len);
                            System.out.println(s);
                            String[] strings = s.split(",");
                            if (strings.length == 3) {
                                v.add(new Action(Double.parseDouble(strings[0]), Double.parseDouble(strings[1]), Integer.parseInt(strings[2])));
                            }

                        }

                    }
                    //reader.close();
                    //socket.close();
                    //server.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

}
