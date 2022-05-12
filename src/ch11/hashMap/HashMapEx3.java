package ch11.hashMap;

import java.util.*;

public class HashMapEx3 {
    static HashMap phoneBook = new HashMap();

    public static void main(String[] args) {
        addPhone("친구", "이자바", "010-111-1111");
        addPhone("친구", "김자바", "010-222-2222");
        addPhone("친구", "김자바", "010-333-3333");
        addPhone("회사", "김대리", "010-444-4444");
        addPhone("회사", "김대리", "010-555-5555");
        addPhone("회사", "박대리", "010-666-6666");
        addPhone("회사", "이과장", "010-777-7777");
        addPhone("세탁", "010-888-8888");

        printList();
    }

    static void addPhone(String groupName, String name, String tel) {
        addGroup(groupName);
        HashMap group = (HashMap) phoneBook.get(groupName);
        group.put(tel, name);
    }

    static void addGroup(String groupName) {
        if (!phoneBook.containsKey(groupName)) {
            phoneBook.put(groupName, new HashMap());
        }
    }

    static void addPhone(String name, String tel) {
        addPhone("기타", name, tel);
    }

    static void printList(){
        Set set = phoneBook.entrySet();
        Iterator it = set.iterator();

        while (it.hasNext()) {
            Map.Entry e = (Map.Entry) it.next();

            Set subSet = ((HashMap) e.getValue()).entrySet();
            Iterator subIt = subSet.iterator();

            System.out.println(" * " + e.getKey() + "[" + subSet.size() + "]");

            while (subIt.hasNext()) {
                Map.Entry subE = (Map.Entry) subIt.next();
                String telNo = (String) subE.getKey();
                String name = (String) subE.getValue();
                System.out.println(name + " " + telNo);
            }
            System.out.println();
        }
    }
}
