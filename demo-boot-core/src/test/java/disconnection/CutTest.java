package disconnection;

import com.demo.boot.core.util.StringUtil;
import org.junit.Test;

public class CutTest {

    @Test
    public void testStr(){
        System.out.println(StringUtil.nullToEmpty("s"));
        System.out.println(StringUtil.nullToEmpty(null));
    }

}
