package jp.ac.meijou.android.s221205140;

import static jp.ac.meijou.android.s221205140.R.drawable.baseline_elderly_24;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import jp.ac.meijou.android.s221205140.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private PrefDataStore prefDataStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding =ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        prefDataStore =PrefDataStore.getInstance(this);
        prefDataStore.getString("name").ifPresent(name ->binding.text.setText(name));
        binding.savebutton.setOnClickListener(view -> {
            var text=binding.editTextText.getText().toString();
            prefDataStore.setString("name",text);
        });

        binding.button.setOnClickListener((View view) -> {
            var text=binding.editTextText.getText().toString();
            binding.text.setText(text);
        });

        Log.d("Hirota","onCreate text: "+binding.editTextText);


    }

    @Override
    protected void onStart() {
        super.onStart();
        prefDataStore.getString("name").ifPresent(name ->binding.text.setText(name));

    }
}