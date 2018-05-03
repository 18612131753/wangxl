package gof.ray.Composite.Sample;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Making root entries...");
            Directory rootdir = new Directory("root");
            Directory bindir = new Directory("bin");
            Directory tmpdir = new Directory("tmp");
            Directory usrdir = new Directory("usr");
            Directory yuki = new Directory("yuki");
            rootdir.add(bindir);
            rootdir.add(tmpdir);
            rootdir.add(usrdir);
            usrdir.add(yuki);
            yuki.add(new File("diary.html", 100));
            bindir.add(new File("vi.txt", 10000));
            bindir.add(new File("latex.txt", 20000));
            rootdir.printList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
