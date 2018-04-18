package gof.ray.Interpreter.A;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        String filename = ".\\src\\main\\java\\gof\\ray\\Interpreter\\A\\program.txt";
        try {
            BufferedReader reader = new BufferedReader(new FileReader( filename ));
            String text;
            while ((text = reader.readLine()) != null) {
                System.out.println("text = \"" + text + "\"");
                Node node = new ProgramNode();
                node.parse(new Context(text));
                System.out.println("node = " + node);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
