package id.ac.polinema.retrofit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import id.ac.polinema.retrofit.api.helper.ServiceGenerator;
import id.ac.polinema.retrofit.api.models.Data;
import id.ac.polinema.retrofit.api.models.Profile;
import id.ac.polinema.retrofit.api.services.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    public static final String TOKEN_KEY = "token";
    public static final String TOKEN_TYPE = "token_type";
    public static final String NAME_KEY = "name";
    public static final String EMAIL_KEY = "email";
    public static final String ID_KEY = "id";
    private static SharedPreferences preference;
    TextView id, name, email;
    private String token, token_type;
    private String nameText, emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        id = findViewById(R.id.idText);
        name = findViewById(R.id.nameText);
        email = findViewById(R.id.emailText);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            token = extras.getString(TOKEN_KEY);
            token_type = extras.getString(TOKEN_TYPE);
        }
        showProfile();
    }

    private void showProfile() {
        ApiInterface service = ServiceGenerator.createService(ApiInterface.class);
        Call<Data<Profile>> call = service.getProfile(token_type + " " + token);
        call.enqueue(new Callback<Data<Profile>>() {

            @Override
            public void onResponse(Call<Data<Profile>> call, Response<Data<Profile>> response) {
                if (response.body() != null) {
                    int idText = response.body().data.getId();
                    nameText = response.body().data.getName();
                    emailText = response.body().data.getEmail();
                    id.setText(String.valueOf((idText)));
                    name.setText(nameText);
                    email.setText(emailText);
                }
            }

            @Override
            public void onFailure(Call<Data<Profile>> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, "Gagal Menampilkan Profile", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void prosesEditProfile(View view) {
        Toast.makeText(ProfileActivity.this, token, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
        intent.putExtra(TOKEN_TYPE, token_type);
        intent.putExtra(TOKEN_KEY, token);
        intent.putExtra(NAME_KEY, nameText);
        intent.putExtra(EMAIL_KEY, emailText);
        startActivity(intent);
    }
}
