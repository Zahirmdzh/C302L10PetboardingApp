package sg.edu.rp.c302.c302l10petboardingapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.Date;
import java.text.DateFormat;

public class PetBoardingActivity extends AppCompatActivity {

    EditText etName,etDays,etDate;
    Spinner spnPet;
    CheckBox cb;
    Button btnReq;
    private FirebaseFirestore db;
    private CollectionReference colRef;
    private DocumentReference docRef;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_boarding);

        etDate = findViewById(R.id.editTextDate);
        etDays = findViewById(R.id.editTextDays);
        etName = findViewById(R.id.editTextName);
        spnPet = findViewById(R.id.spn);
        btnReq = findViewById(R.id.buttonReq);

        cb = findViewById(R.id.cbVac);

        pref = PreferenceManager.getDefaultSharedPreferences(this);
        editor = pref.edit();

        FieldValue date = FieldValue.serverTimestamp();
        Log.d("DATEfieldvalue", String.valueOf(date));

        etDate.setText(String.valueOf(date));

        db = FirebaseFirestore.getInstance();

        colRef = db.collection("L10C302");
        docRef = colRef.document("PetBoard");

        btnReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnRequestOnClick(v);
            }
        });

        spnPet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {

                String pet = String.valueOf(spnPet.getItemAtPosition(position));
                Log.d("PET",pet);

                editor.putString("pet",pet);
                editor.commit();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO Auto-generated method stub)

            }
        });

    }

    private void btnRequestOnClick(View v) {



        String pet = pref.getString("pet","");
        String name = etName.getText().toString(); // use Pojo
        String days = etDays.getText().toString();


        FieldValue date = FieldValue.serverTimestamp();
        Boolean checked = cb.isChecked();


        Petboard petboard = new Petboard(name, Integer.parseInt(days),pet,date,checked);
        colRef.document("petboard2").set(petboard);
    }
}
