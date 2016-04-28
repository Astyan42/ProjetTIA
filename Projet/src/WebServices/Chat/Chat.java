package WebServices.Chat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class Chat {
    /**
     * Map<String la clé ... qui sera le fichier ou dossier, ArrayList<Message> l'ensemble des message du projet>
     */
    public Map<String,ArrayList<Message>> messages;
    public Chat() {
        messages = new Map<String, ArrayList<Message>>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean containsKey(Object key) {
                return false;
            }

            @Override
            public boolean containsValue(Object value) {
                return false;
            }

            @Override
            public ArrayList<Message> get(Object key) {
                return null;
            }

            @Override
            public ArrayList<Message> put(String key, ArrayList<Message> value) {
                return null;
            }

            @Override
            public ArrayList<Message> remove(Object key) {
                return null;
            }

            @Override
            public void putAll(Map<? extends String, ? extends ArrayList<Message>> m) {

            }

            @Override
            public void clear() {

            }

            @Override
            public Set<String> keySet() {
                return null;
            }

            @Override
            public Collection<ArrayList<Message>> values() {
                return null;
            }

            @Override
            public Set<Entry<String, ArrayList<Message>>> entrySet() {
                return null;
            }
        };
    }
}
