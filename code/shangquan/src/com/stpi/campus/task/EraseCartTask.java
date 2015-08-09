package com.stpi.campus.task;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.stpi.campus.R;
import com.stpi.campus.items.user.PostResult;
import com.stpi.campus.util.JsonGetter;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cyc on 14-1-1.
 */
public class EraseCartTask extends AsyncTask<String, Void, String> {

    private Context activity;
    private ProgressBar progressBar;

    public EraseCartTask(Context theActivity, ProgressBar theProgressBar) {
        activity = theActivity;
        progressBar = theProgressBar;
    }

    @Override
    protected String doInBackground(String... args) {
        String url = activity.getString(R.string.erase_cart_head);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("userId", args[0]));
        params.add(new BasicNameValuePair("itemId", args[1]));

        PostResult res = new JsonGetter<PostResult>().getFromUrl(url, PostResult.class, params);
        if (res == null)
            return "error";
        if (res.getState().equals("success"))
            return "success";
        else
            return "error";
    }

    @Override
    protected void onPostExecute(final String retCode) {
        progressBar.setVisibility(View.INVISIBLE);
        System.out.println(retCode);

        if (retCode.equals("error"))
            Toast.makeText(activity, "网络异常", Toast.LENGTH_SHORT).show();
        else {
            Toast.makeText(activity, "移除购物车成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCancelled() {
        progressBar.setVisibility(View.INVISIBLE);
    }

}
