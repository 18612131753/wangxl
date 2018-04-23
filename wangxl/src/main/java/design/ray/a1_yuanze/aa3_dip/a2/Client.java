package design.ray.a1_yuanze.aa3_dip.a2;

public class Client {

    public static void main(String[] args) {
        People people = new People();
        Food pizza = new Pizza();
        people.eat(pizza);

        Food cake = new Cake();
        people.eat(cake);
    }

}
