package Services;

import DAL.FileRepository;
import Model_Objects.File;
import WebServices.Chat.Message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ChatService {
    private static final ConcurrentMap<String, ChatService> multitons = new ConcurrentHashMap<>();
    private final File fileModel;
    private ArrayList<Message> messages;

    private ChatService(String name) {
        this.fileModel = FileRepository.getInstance().getFileByName(name);
        this.messages=new ArrayList<>();
        messages.add(new Message("chat de "+fileModel.name," Bienvenue sur le Chat Vous pouvez des a présent echanger avec vos collaborateur"));
    }
    public static ChatService getInstance(final String name) {
        return multitons.computeIfAbsent(name, ChatService::new);
    }

    public String lastMessage(){
        return messages.get(messages.size()-1).toString()+'\n';
    }

    public void addMessage(String pseudo,String message){
        messages.add(new Message(pseudo,message));
    }

    public String getMessages(){
        String result="";
        for (Message message : messages) {
            result += message.toString();
            result += "\n";
        }
        return result;
    }

}
