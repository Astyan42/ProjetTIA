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

    private ChatService(String fileId) {
        this.fileModel = FileRepository.getInstance().getFileById(fileId);
        this.messages=new ArrayList<>();
    }
    public static ChatService getInstance(final String fileId) {
        return multitons.computeIfAbsent(fileId, ChatService::new);
    }

    public String lastMessage(){
        return messages.get(messages.size()).toString();
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
