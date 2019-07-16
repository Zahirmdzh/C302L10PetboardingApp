package sg.edu.rp.c302.c302l10petboardingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class MainActivity extends AppCompatActivity {

    private TextView tvTitle;
    private EditText etTitle;
    private TextView tvDate;
    private EditText etDate;
    private Button btnAdd;

    private FirebaseFirestore db;
    private CollectionReference colRef;
    private DocumentReference docRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvTitle = findViewById(R.id.textViewTitle);
        etTitle = findViewById(R.id.editTextTitle);
        tvDate = findViewById(R.id.textViewDate);
        etDate = findViewById(R.id.editTextDate);

        btnAdd = findViewById(R.id.buttonAdd);

        db = FirebaseFirestore.getInstance();

        colRef = db.collection("L10C302");
        docRef = colRef.document("ToDoApp");

        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException e) {

                if (e != null) {
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
       /*           String text = (String) snapshot.get("text");
                    tvMessage.setText(text);*/

                    Message msg = snapshot.toObject(Message.class);//use POJO
                    tvTitle.setText(msg.getTitle());
                    tvDate.setText(msg.getDate());

                }

            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAddOnClick(v);
            }
        });
    }

    private void btnAddOnClick(View v) {
    /*    String text = etMessage.getText().toString();
        docRef.update("text",text, "color","red");
*/
        String title = etTitle.getText().toString(); // use Pojo
        String date = etDate.getText().toString();
        Message msg = new Message(title, date);
        docRef.set(msg);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int idItem = item.getItemId();
        if (idItem == R.id.menu_petboard) {

            startActivity(new Intent(MainActivity.this,PetBoardingActivity.class));


        }
        return super.onOptionsItemSelected(item);
    }
}
