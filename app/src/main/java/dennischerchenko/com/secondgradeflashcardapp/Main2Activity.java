package dennischerchenko.com.secondgradeflashcardapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final Button btnEnter = (Button) findViewById(R.id.btnEnter);

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView txtUsername = (TextView) findViewById(R.id.txtUsername);
                TextView txtPassword = (TextView) findViewById(R.id.txtPassword);

                if(txtUsername.getText().toString().equals("cs591") == false){
                    Toast.makeText(getApplicationContext(), "Incorrect Username, please try again", Toast.LENGTH_LONG).show();
                }else if(txtPassword.getText().toString().equals("mobileapp") == false){
                    Toast.makeText(getApplicationContext(), "Incorrect Password, please try again", Toast.LENGTH_LONG).show();
                }else{
                    Intent IntentToMoveNext = new Intent("dennischerchenko.com.secondgradeflashcardapp.MainActivity");
                    startActivity(IntentToMoveNext);
                }
            }
        });
    }
}
