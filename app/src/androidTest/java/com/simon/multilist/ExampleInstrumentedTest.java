package com.simon.multilist;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.simon.multilist.bean.Bean;
import com.simon.multilist.bean.Parent;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static com.simon.multilist.util.DataConverter.convert;
import static com.simon.multilist.util.DataConverter.createDemoData;
import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.simon.xrecyclerview", appContext.getPackageName());
    }
    @Test
    public void convertTest(){
        Parent beijing = createDemoData();
        beijing.open();
        Log.d("test","Tree " + beijing);
        List<? extends Bean> list = convert(beijing);
        Log.d("test", "List " + list);
    }

}
