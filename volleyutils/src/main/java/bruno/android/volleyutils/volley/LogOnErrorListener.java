package bruno.android.volleyutils.volley;

import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.error.NoConnectionError;
import com.android.volley.error.VolleyError;

/**
 * Created by dev on 23/07/2014.
 */
public class LogOnErrorListener  implements Response.ErrorListener {

    private String url;

    public LogOnErrorListener(String url) {
        this.url = url;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
//        if (error instanceof NoConnectionError){
//            VolleyLog.e("No connection error.");
//            return;
//        }
        VolleyLog.e(error, "Error on \"%s\"", url);
    }
}
