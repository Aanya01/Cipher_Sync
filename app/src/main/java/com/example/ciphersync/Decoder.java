package com.example.ciphersync;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Decoder extends AppCompatActivity {

    EditText etdec;
    TextView dectv;
    ClipboardManager cplboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_decoder);
        etdec = findViewById(R.id.etdec);
        dectv = findViewById(R.id.dectv);
        cplboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
    }

    public void dec(View view) {
        String temp = etdec.getText().toString();
        String rv = decode(temp); // Call the new decode method
        dectv.setText(rv);
    }

    public void cp2(View view) {
        String data = dectv.getText().toString().trim();
        if (!data.isEmpty()) {
            ClipData temp = ClipData.newPlainText("text", data);
            cplboard.setPrimaryClip(temp);
            Toast.makeText(this, "Copied", Toast.LENGTH_LONG).show();
        }
    }

    // New decode method
    private String decode(String encodedMessage) {
        if (!encodedMessage.startsWith("111111111")) {
            return "Invalid message";
        }

        // Remove the initializer
        encodedMessage = encodedMessage.substring(9);

        // Initialize the decoded message
        StringBuilder decodedMessage = new StringBuilder();

        // Iterate over the encoded message in blocks of 8 characters
        for (int i = 0; i < encodedMessage.length(); i += 8) {
            // Extract the 8-character block
            String block = encodedMessage.substring(i, i + 8);

            // Convert the block to an integer
            int asciiValue = Integer.parseInt(block, 2);

            // Convert the integer to a character
            char decodedChar = (char) asciiValue;

            // Append the decoded character to the decoded message
            decodedMessage.append(decodedChar);
        }

        // Return the decoded message
        return decodedMessage.toString();
    }
}