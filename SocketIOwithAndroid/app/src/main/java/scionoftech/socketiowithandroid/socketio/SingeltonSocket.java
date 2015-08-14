package scionoftech.socketiowithandroid.socketio;


import android.content.Context;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

//*****************************************Singelton socket is a singleton class to implement socket functions*********************************//
public class SingeltonSocket {

    //create object of class
    public static SingeltonSocket Single = new SingeltonSocket();

    //----------------------------interface object------------------//
    private oneventlitsener mCallback;

    Socket socket;
    private Context context;

    /* A private Constructor prevents any other
        * class from instantiating.
        */
    private SingeltonSocket() {
    }

    //-----------------------------interface for sharing data from server--------------------------//
    public interface oneventlitsener {
        public void setData(String data, String tag);
    }

    //---------------------------------Get activity to share data----------------------------//
    public void setProperties(Context c) {
        this.context = c;
        mCallback = (oneventlitsener) context;
    }

    /* Static 'instance' method */
    public static SingeltonSocket getInstance() {
        return Single;


    }


    //---------***********------------------Run methods starts socket for listening to various event-----*******------//
    public void run() {

        try {
            socket = IO.socket("http://192.168.0.106:3000");
            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

                @Override
                public void call(Object... args) {


                    // socket.disconnect();
                }

            }).on("test_response", new Emitter.Listener() {

                @Override
                public void call(Object... args) {

                    //***********------------------send data from server to activity----------------********//
                    try {

                        mCallback.setData(args[0].toString(), "test_response");
                        System.out.println("=======>" + args[0].toString());
                    } catch (Exception e) {

                        System.out.println("=======>" + e.toString());
                    }
                }

            }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

                @Override
                public void call(Object... args) {

                }

            });
            socket.connect();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        //Method for listening to events.

    }
//-----------------------**********************End of Socket code***************-------------------//


    //*********-----------send data to server-----********//
    public void emit(String s, String j) {
        socket.emit(s, j);
        System.out.println("+++++|||||||emitting"+s+""+j);
    }

}
