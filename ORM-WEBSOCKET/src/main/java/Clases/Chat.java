package Clases;


import org.eclipse.jetty.websocket.api.Session;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static j2html.TagCreator.*;
/**
 * Created by jrdis on 22/6/2017.
 */
public class Chat {


    public   static Map<Session, String> userUsernameMap = new ConcurrentHashMap<>();
    public static List<Users_Chat> users = new ArrayList<>();
    public  static int nextUserNumber = 0; //Assign to username for next connecting user


    private static String createHtmlMessageFromSender(String sender, String message) {
        return article(
            b(sender + " says:"),
            span(attrs(".timestamp"), new SimpleDateFormat("HH:mm:ss").format(new Date())),
            p(message)
        ).render();
    }
    public static void enviarMensajeAClientesConectados(String mensaje,  String username){
        for(Users_Chat sesionConectada : users){
            try {
                if (sesionConectada.getUername().equalsIgnoreCase(username))
                {
                    sesionConectada.getSesiion().getRemote().sendString(mensaje);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void enviarMensajeAAdmin(String mensaje) throws IOException {

                for(Users_Chat item : users)
                {
                    if(item.getUername().equalsIgnoreCase("User1"))
                    {
                        item.getSesiion().getRemote().sendString(mensaje);
                    }
                }
        }
    }



