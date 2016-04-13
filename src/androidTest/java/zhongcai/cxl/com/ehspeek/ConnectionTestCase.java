package zhongcai.cxl.com.ehspeek;

import android.test.InstrumentationTestCase;
import android.util.Log;

import zhongcai.cxl.com.ehspeek.http.HttpEngine;

/**
 * Created by chenjun on 16/4/12.
 */
public class ConnectionTestCase extends InstrumentationTestCase{

    public void test() throws Exception {
        String result = HttpEngine.doGet("给我讲一个笑话");

        if(result == null){
            Log.i("chan","result = null");
        }

        Log.i("chan",result);

        result = HttpEngine.doGet("hello");

        Log.i("chan",result);

    }
}
