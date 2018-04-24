package gof.ray.Prototype.ListClone;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList<Student>();

        Student stJack = new Student(13, "Jack");
        Student stTom = new Student(15, "Tom");
        list.add(stJack);
        list.add(stTom);

        // 克隆,浅复制
        ArrayList<Student> listCopy = (ArrayList<Student>) list.clone();

        // 移除且不修改
        listCopy.remove(1);
        System.out.println(list);
        System.out.println(listCopy);

        listCopy.get(0).setName("updateJack");
        System.out.println(list);
        System.out.println(listCopy);
    }

}
