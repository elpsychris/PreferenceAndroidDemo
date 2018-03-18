package day08.nguyenntt.day08_preference;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MyPreferenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_preference);
    }

    public void clickToLoad(View view) {
        Intent intent = new Intent(this, MyPreferenceDetailActivity.class);
        startActivity(intent);
    }

    public void clickToDisplay(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("day08.nguyenntt.day08_preference_preferences",MODE_PRIVATE);
        String data = sharedPreferences.getString("editTextPref","");
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }

    public void clickToModify(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("day08.nguyenntt.day08_preference_preferences",MODE_PRIVATE);
        EditText edtValue = findViewById(R.id.edtValue);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("editTextPref", edtValue.getText().toString());
        editor.commit();
    }
}
