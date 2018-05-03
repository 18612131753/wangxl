package gof.ray.Visitor.Q3;

import gof.ray.Visitor.A3.Directory;
import gof.ray.Visitor.A3.File;
import gof.ray.Visitor.A3.FileTreatmentException;
import gof.ray.Visitor.A3.ListVisitor;
import gof.ray.Visitor.A3.ElementArrayList;

public class Main {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        try {
            Directory root1 = new Directory("root1");
            root1.add(new File("diary.html", 10));
            root1.add(new File("index.html", 20));

            Directory root2 = new Directory("root2");
            root2.add(new File("diary.html", 1000));
            root2.add(new File("index.html", 2000));

            ElementArrayList list = new ElementArrayList();
            list.add(root1);
            list.add(root2);
            list.add(new File("etc.html", 1234));

            list.accept(new ListVisitor());
        } catch (FileTreatmentException e) {
            e.printStackTrace();
        }
    }
}
