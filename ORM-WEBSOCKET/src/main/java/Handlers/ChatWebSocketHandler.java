package Handlers;

import Clases.Chat;
import Clases.Users_Chat;
import org.eclipse.jetty.websocket.api.*;
import org.eclipse.jetty.websocket.api.annotations.*;

import java.io.IOException;
/**
 * Created by jrdis on 22/6/2017.
 */
@WebSocket
public class ChatWebSocketHandler {

    private String sender, msg;

    @OnWebSocketConnect
    public void onConnect(Session user) throws Exception {

        String username = "User" + Chat.nextUserNumber++;
        Chat.userUsernameMap.put(user, username);
        Users_Chat users_chat = new Users_Chat(user,username);
        Chat.users.add(users_chat);
    }

    @OnWebSocketClose
    public void onClose(Session user, int statusCode, String reason) {

        Chat.userUsernameMap.remove(user);

    }

    @OnWebSocketMessage
    public void onMessage(Session user, String mensaje) throws IOException {
        String username =  Chat.userUsernameMap.get(user);
        if(!username.equalsIgnoreCase("User1")){
        Chat.enviarMensajeAAdmin(mensaje + " " + username);
        }else
        {

            String username_send = mensaje.substring(0,5);
            Chat.enviarMensajeAClientesConectados(mensaje,username_send);
        }
    }


}