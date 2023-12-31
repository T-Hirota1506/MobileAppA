package jp.ac.meijou.android.s221205140;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Optional;

import jp.ac.meijou.android.s221205140.databinding.ActivityMain4Binding;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity4 extends AppCompatActivity {
    private final OkHttpClient okHttpClient = new OkHttpClient();
    private final Moshi moshi = new Moshi.Builder().build();
    private final JsonAdapter<Gist> gistJsonAdapter = moshi.adapter(Gist.class);

    private ActivityMain4Binding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMain4Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonGet.setOnClickListener(view -> {
            var text=binding.editText.getText().toString();
            var uri= Uri.parse("https://placehold.jp/350x350.png")
                    .buildUpon()
                    .appendQueryParameter("text",text)
                    .build()
                    .toString();
            getimage(uri);
        });
    }
    private void getimage(String url){
        var request=new Request.Builder()
                .url(url)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                var bitmap= BitmapFactory.decodeStream(response.body().byteStream());

                runOnUiThread(()->binding.imageView1.setImageBitmap(bitmap));
            }
        });
    }
}