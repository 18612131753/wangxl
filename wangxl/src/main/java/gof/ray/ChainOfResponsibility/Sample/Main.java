package gof.ray.ChainOfResponsibility.Sample;

public class Main {
    public static void main(String[] args) {
        Support no = new NoSupport("no");
        Support limit_1 = new LimitSupport("Limit_1", 100); //解决小于100编号的问题
        Support special_1 = new SpecialSupport("Special_1", 429); //只解决固定问题编号的问题
        Support limit_2 = new LimitSupport("Limit_2", 200);//解决小于200编号的问题
        Support odd = new OddSupport("Odd"); //解决奇数编号的问题
        Support limit_3 = new LimitSupport("Limit_3", 300);
        // 形成职责链
        no.setNext(limit_1).setNext(special_1).setNext(limit_2).setNext(odd).setNext(limit_3);
        // 制造各种问题
        for (int i = 0; i < 500; i += 33) {
            no.support(new Trouble(i));
        }
    }
}
