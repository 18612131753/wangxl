package ali.rule.r1.velocity;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public class VelocityTest {

	public static String value = 
			"#set( $iAmVariable = \"good!\" ) \n" + 
			"Welcome $name to velocity.com \n" + 
			"today is $date. \n" + 
			"#foreach ($i in $list) \n" + 
			"$i \n" + 
			"#end \n" + 
			"$iAmVariable \n";
	
	public static void main(String[] args) {
		Velocity.init();

		//Template t = Velocity.getTemplate("hellovelocity.vm");
		VelocityContext ctx = new VelocityContext();

		ctx.put("name", "hellovelocity.vm");
		ctx.put("date", (new Date()).toString());

		List<String> temp = new ArrayList<String>();
		temp.add("1");
		temp.add("2");
		ctx.put("list", temp);

		StringWriter sw = new StringWriter();
		Velocity.evaluate( ctx , sw , "mystring", VelocityTest.value);
		//t.merge(ctx, sw);

		System.out.println(sw.toString());
	}

}
