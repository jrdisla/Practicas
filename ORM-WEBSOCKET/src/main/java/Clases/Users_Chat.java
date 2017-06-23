package Clases;

import org.eclipse.jetty.websocket.api.Session;

/**
 * Created by jrdis on 16/6/2017.
 */
public class Users_Chat {
    public Session sesiion;
    public String uername;

    public Users_Chat() {
    }

    public Users_Chat(Session sesiion, String uername) {
        this.sesiion = sesiion;
        this.uername = uername;
    }

    public Session getSesiion() {
        return sesiion;
    }

    public void setSesiion(Session sesiion) {
        this.sesiion = sesiion;
    }

    public String getUername() {
        return uername;
    }

    public void setUername(String uername) {
        this.uername = uername;
    }
}
