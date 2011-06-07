import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLRegexTest {


     @Test
     public void testRegex(){
         //Matcher matcher = Pattern.compile("amqp://(.+):(.+)@(.+):(.+)(/.+)").matcher("amqp://guest:guest@localhost:5672/");
         Matcher matcher = Pattern.compile("amqp://(.*):(.*)@(.*):(.*)(/.*)").matcher("amqp://guest:guest@localhost:5672/");

         matcher.find();


         Assert.assertEquals("guest", matcher.group(1));
         Assert.assertEquals("guest", matcher.group(2));
         Assert.assertEquals("localhost", matcher.group(3));
         Assert.assertEquals("5672", matcher.group(4));
         Assert.assertEquals("/", matcher.group(5));

     }


}
