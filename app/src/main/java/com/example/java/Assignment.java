package com.example.java;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Assignment extends AppCompatActivity {

    private EditText editTextEmail, editTextUsername, editTextPassword;
    private RadioGroup radioGroupGender;
    private CheckBox checkBoxAgreement;
    private Spinner spinnerCountry;
    private Button buttonSubmit;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        checkBoxAgreement = findViewById(R.id.checkBoxAgreement);
        spinnerCountry = findViewById(R.id.spinnerCountry);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        textViewResult = findViewById(R.id.textViewResult);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.countries_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCountry.setAdapter(adapter);

        buttonSubmit.setOnClickListener(v -> submitForm());
    }

    private void submitForm() {
        String email = editTextEmail.getText().toString().trim();
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        int selectedGenderId = radioGroupGender.getCheckedRadioButtonId();
        RadioButton selectedGenderButton = findViewById(selectedGenderId);
        String gender = selectedGenderButton == null ? "" : selectedGenderButton.getText().toString();
        boolean isAgreementChecked = checkBoxAgreement.isChecked();
        String country = spinnerCountry.getSelectedItem().toString();

        if (email.isEmpty() || username.isEmpty() || password.isEmpty() || gender.isEmpty() || !isAgreementChecked) {
            textViewResult.setText("Please fill out all fields and agree to the terms.");
        } else {
            String message = String.format("Email: %s\nUsername: %s\nPassword: %s\nGender: %s\nCountry: %s\nAgreement: %s",
                    email, username, password, gender, country, isAgreementChecked ? "Agreed" : "Not Agreed");
            textViewResult.setText(message);
        }
    }
}
