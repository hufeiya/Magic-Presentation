package com.hufeiya.magicpresentation.net;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

/**
 * Created by hufeiya on 16-2-7.
 */
public class SocketClient {

    private String host;
    private int port;
    private Socket socket;
    private Writer writer;
    private Handler handler;

    public SocketClient(final String host, final int port) {
        this.host = host;
        this.port = port;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket(host,port);
                    writer = new OutputStreamWriter(socket.getOutputStream());
                    Looper.prepare();
                    handler = new Handler(){
                        @Override
                        public void handleMessage(Message msg) {
                            try {
                                String s = msg.getData().getString("message");
                                Log.d("fuck",s);
                                writer.write(s);
                                writer.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    };
                    Looper.loop();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    public void send(String message){
        Bundle bundle = new Bundle();
        bundle.putString("message",message);
        Message msg = Message.obtain();
        msg.setData(bundle);
        Log.d("fuck","正在发送 "+msg.getData().getString("message"));
        handler.dispatchMessage(msg);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (writer != null){
            writer.close();
        }
        if(socket != null){
            socket.close();
        }
    }
}
