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
        @SuppressWarnings("unchecked")
        ArrayList<Student> listCopy = (ArrayList<Student>) list.clone();

        // 移除且不修改
        System.out.println("原List："+list);
        listCopy.remove(1);
        System.out.println("clone出来的List删除一个元素后："+listCopy);

        listCopy.get(0).setName("updateJack");
        System.out.println("修改还存在的Student的名字后的List："+list);
        System.out.println("修改还存在的Student的名字后的CopyList：" + listCopy);
    }

}
