package gof.ray.Decorator.Sample;

/**
 * 全包裹
 */
public class FullBorder extends Border {

    public FullBorder(Display display) {
        super(display);
    }

    // 字符数为被装饰物的字符数加上两侧边框字符数
    @Override
    public int getColumns() {
        return 1 + display.getColumns() + 1;
    }

    // 行数为被装饰物的行数加上上下边框的行数
    @Override
    public int getRows() {
        return 1 + display.getRows() + 1; // 形成递推
    }

    @Override
    public String getRowText(int row) { // 指定的那一行的字符串
        // 上边框
        if (row == 0) {
            return "+" + makeLine('-', display.getColumns()) + "+";
        // 下边框
        } else if (row == display.getRows() + 1) {
            return "+" + makeLine('-', display.getColumns()) + "+";
        } else { // 其他边框
            return "|" + display.getRowText(row - 1) + "|";
        }
    }

    private String makeLine(char ch, int count) { // 生成一个重复count次字符ch的字符串
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < count; i++) {
            buf.append(ch);
        }
        return buf.toString();
    }
}
