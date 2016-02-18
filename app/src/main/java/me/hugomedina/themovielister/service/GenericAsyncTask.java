package me.hugomedina.themovielister.service;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import me.hugomedina.themovielister.util.CustomDialogProgress;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Hugo on 2/17/2016.
 */
public class GenericAsyncTask extends AsyncTask<Void, Void, String> {

    private String mUrl;
    public CustomDialogProgress mDialog;
    private String mResponseS;
  //  private T mOuputEntity;
    private String mTag;
    private OnFinishTask mListener;
    private Context mContext;
    private boolean mDialogExternal;
    private String mErrorMessage;
    private int mResultCode;
    private TypeWS type;
    private Map<String, ?> map;

    /**
     * To use when looking for a Movie

     * @param context App context
     * @param query The query to look up with
     * @param mnsDialog Message to be displayed on dialog
     */
    public GenericAsyncTask(Context context, String query, String mnsDialog, OnFinishTask listener){//private GenericAsyncTask(TypeWS type, Map<String, ?> map, Context context, String query, String mnsDialog, String response, T ouputEntity, String tag, OnFinishTask listener, String errorMessage, int resultCode, String data){
        mUrl = MovieDbUrl.getMovieQuery(query);
//        this.map = map;
//        this.type = type;
        //mDialog = pDialog;
//        mResponseS = response;
//        mOuputEntity = ouputEntity;
        mListener = listener;
//        mContext = context;
//        mDialogExternal = false;
//        mErrorMessage = errorMessage;
//        mResultCode = resultCode;
//        mTag = tag;
//        if(data != null){
//            formData = new LinkedMultiValueMap<String, Object>();
//            formData.add("image", new FileSystemResource(data));
//        }
    }

    @Override
    protected String doInBackground(Void... params) {
        String url = mUrl;
      //  mDialog.show();

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);

        Response response = null;

        String jsonData = null;

        try {
            response = call.execute();

            if (response.isSuccessful()) {
                jsonData = response.body().string();

            } else {
                jsonData = null;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonData;
    }

    @Override
    protected void onPostExecute(String jsonData) {
        mListener.finishTask(jsonData);
    }

    public interface OnFinishTask {
        /**
         * Funcion que se llama cuando la tarea es terminada y no hay errores ni la consulta es nulla
         *
         * @param result
         */
        public void finishTask(String result);
    }
}
