package gof.ray.FactoryMethod.Sample;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import gof.ray.FactoryMethod.Sample.framework.Factory;
import gof.ray.FactoryMethod.Sample.framework.Product;
import gof.ray.FactoryMethod.Sample.idcard.IDCardFactory;

public class Main {
    public static void main(String[] args) {
        Factory factory = new IDCardFactory();
        Product card1 = factory.create("小明");
        Product card2 = factory.create("小红");
        Product card3 = factory.create("小刚");
        card1.use();
        card2.use();
        card3.use();
        
        // IDCard i = new IDCard();
        @SuppressWarnings("unused")
        LocalSessionFactoryBean lsb; // hibernate
        @SuppressWarnings("unused")
        SqlSessionFactoryBean ssf;   // mybatis
    }
}
