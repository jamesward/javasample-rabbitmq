import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLRegexTest {


     @Test
     public void testRegex(){
         Matcher matcher = Pattern.compile("amqp://(.*):(.*)@(.*):(.*)(/.*)").matcher("amqp://guest:guest@localhost:5672/");

         matcher.find();


         Assert.assertEquals("guest", matcher.group(1));
         Assert.assertEquals("guest", matcher.group(2));
         Assert.assertEquals("localhost", matcher.group(3));
         Assert.assertEquals("5672", matcher.group(4));
         Assert.assertEquals("/", matcher.group(5));

         matcher = Pattern.compile("amqp://(.*):(.*)@(.*):(.*)(/.*)").matcher("amqp://lzuuornl:GaLybFV0m6nuIRBC@iauladza.heroku.srs.rabbitmq.com:16919/iauladza");
         matcher.find();
         Assert.assertEquals("lzuuornl", matcher.group(1));
         Assert.assertEquals("GaLybFV0m6nuIRBC", matcher.group(2));
         Assert.assertEquals("iauladza.heroku.srs.rabbitmq.com", matcher.group(3));
         Assert.assertEquals("16919", matcher.group(4));
         Assert.assertEquals("/iauladza", matcher.group(5));

     }


}
