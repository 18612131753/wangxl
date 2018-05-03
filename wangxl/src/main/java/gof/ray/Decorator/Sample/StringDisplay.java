package gof.ray.Decorator.Sample;

public class StringDisplay extends Display {
    private String string; // 要显示的字符串

    public StringDisplay(String string) { // 通过参数传入要显示的字符串
        this.string = string;
    }

    // 字符数
    public int getColumns() {
        return string.getBytes().length;
    }

    // 行数是1
    public int getRows() {
        return 1;
    }

    public String getRowText(int row) { // 仅当row为0时返回值
        if (row == 0) {
            return string;
        } else {
            return null;
        }
    }
}
