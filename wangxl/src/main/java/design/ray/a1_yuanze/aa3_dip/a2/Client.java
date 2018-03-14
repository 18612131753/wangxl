package design.ray.a1_yuanze.aa3_dip.a2;

public class Client {

    public static void main(String[] args) {
        People people = new People();
        Pizza pizza = new Pizza();
        people.eat(pizza);

        Cake cake = new Cake();
        people.eat(cake);
    }

}
