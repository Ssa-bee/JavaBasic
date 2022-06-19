package ch12.annotation;

import java.util.ArrayList;
import java.util.List;

class NewClass{
    int newField;
    @Deprecated
    int oldField;

    public int getNewField() {
        return newField;
    }

    @Deprecated
    public int getOldField() {
        return oldField;
    }
}

public class AnnotationEx3 {
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        NewClass nc = new NewClass();
        nc.oldField =10;
        System.out.println(nc.getOldField());

        @SuppressWarnings("unchecked")
        List<NewClass> list = new ArrayList();
        list.add(nc);

    }
}
