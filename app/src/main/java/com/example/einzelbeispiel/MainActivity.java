package com.example.einzelbeispiel;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textStudentNr = ((EditText)(findViewById(R.id.editTextNumber))).getText().toString();
                Connection connection = new Connection(textStudentNr);
                connection.start();
                try {
                    connection.join();
                    ((TextView)findViewById(R.id.serverAnswer_Tv)).setText(connection.getServerMsg());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        });
        findViewById(R.id.buttonCalculation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentNr_et = ((EditText)(findViewById(R.id.editTextNumber))).getText().toString();
                StringBuilder builder = new StringBuilder();
                for(int i = 0; i<studentNr_et.length(); i++){
                    if(i % 2 == 1){
                        int digit = studentNr_et.charAt(i) - '0';
                        char letter = (char) ( 'a' + digit);
                        builder.append(letter);

                    }
                    else {
                        builder.append(studentNr_et.charAt(i));
                    }
                    String result = builder.toString();
                    ((TextView)findViewById(R.id.serverAnswer_Tv)).setText(result);
                }

            }
        });

    }


}
