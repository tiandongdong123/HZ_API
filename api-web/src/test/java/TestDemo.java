import com.hanzhong.api.web.util.CheckUtils;
import org.junit.Test;

/**
 * @author yifei
 * @date 2018/12/1
 */
public class TestDemo {

    @Test
    public void includeChinese(){
        System.out.println(CheckUtils.includeChinese("CA11 工商行政管理局代码"));
        System.out.println(CheckUtils.includeChinese("工商行政管理局代码"));
        System.out.println(CheckUtils.includeChinese("CA11"));
        System.out.println(CheckUtils.includeChinese("哈加达和CA11"));
        System.out.println(CheckUtils.includeChinese("CA11工商行政管理局代"));
        System.out.println(CheckUtils.includeChinese("CA11工商行政管理局代码jjajd"));
        System.out.println(CheckUtils.includeChinese("^&**(&&"));
        System.out.println(CheckUtils.includeChinese("Hjd^&"));
    }
}
