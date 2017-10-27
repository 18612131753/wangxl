package ali.rule.r2.a10;

import java.util.Optional;
import java.util.function.Consumer;

public class OptionalTest {

	public static void main(String[] args) {
		// 参数不能是null ,当value为null的时候，将抛出NullPointException
		Optional<Integer> optional1 = Optional.of(1);
		// 参数可以是null
		Optional<Integer> optional2 = Optional.ofNullable(null);

		// ifPresent(Consumer consumer)：如果option对象保存的值不是null，则调用consumer对象，否则不调用
		optional1.ifPresent(new Consumer<Integer>() {
			@Override
			public void accept(Integer t) {
				System.out.println("value is " + t);
			}
		});
		
		// orElse(value)：如果optional对象保存的值不是null，则返回原来的值，否则返回value
		System.out.println( "orElse:" );
		System.out.println(optional1.orElse(1000) );// optional1不为null，返回1 
		System.out.println(optional2.orElse(1000) );// optional2为null，返回1000 
		

		//map(Function)：对Optional中保存的值进行函数运算，并返回新的Optional(可以是任何类型)
		Optional<String> str1Optional = optional1.map((value) -> "key" + value);  
		Optional<String> str2Optional = optional2.map((value) -> "key" + value);

		System.out.println( "map:" );
		System.out.println(str1Optional.get());// key1  
		System.out.println(str2Optional.isPresent());// false  
		
		//flatMap()：flatMap方法与map方法类似，区别在于mapping函数的返回值不同。
		//map方法的mapping函数返回值可以是任何类型T，
		//而flatMap方法的mapping函数必须是Optional。
		// 函数对象：Function f = (a) -> {return a+"key";};

		Optional<Optional<String>> str11Optional = optional1.map((a) -> {
		    return Optional.<String>of("key" + a);  
		});
		Optional<String> str22Optional = optional1.flatMap((a) -> {  
		    return Optional.<String>of("key" + a);  
		});
		System.out.println( "flatMap:" );
		System.out.println(str11Optional.get().get());// key1  
		System.out.println(str22Optional.get());// key1  
	}

}
