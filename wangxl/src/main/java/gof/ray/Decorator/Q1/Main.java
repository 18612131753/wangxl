package gof.ray.Decorator.Q1;

import gof.ray.Decorator.A1.Display;
import gof.ray.Decorator.A1.FullBorder;
import gof.ray.Decorator.A1.SideBorder;
import gof.ray.Decorator.A1.StringDisplay;
import gof.ray.Decorator.A1.UpDownBorder;

public class Main {
    public static void main(String[] args) {
        Display b1 = new StringDisplay("Hello, world.");
        Display b2 = new UpDownBorder(b1, '-');
        Display b3 = new SideBorder(b2, '*');
        b1.show();
        b2.show();
        b3.show();
        Display b4 = 
                    new FullBorder(
                        new UpDownBorder(
                            new SideBorder(
                                new UpDownBorder(
                                    new SideBorder(
                                        new StringDisplay("你好，世界。"),
                                        '*'
                                    ),
                                    '='
                                ),
                                '|'
                            ),
                            '/'
                        )
                    );
        b4.show();
    }
}