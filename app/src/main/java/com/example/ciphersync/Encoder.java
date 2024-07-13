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

public class Encoder extends AppCompatActivity {
    EditText etenc;
    TextView enctv;
    ClipboardManager cpb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_encoder);
        etenc = findViewById(R.id.etenc);
        enctv = findViewById(R.id.enctv);
        cpb = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
    }

    public void enc(View view) {
        String temp = etenc.getText().toString();
        String rv = encodeMessage(temp); // modified to use the new encodeMessage method
        enctv.setText(rv);
    }

    public void cp2(View view) {
        String data = enctv.getText().toString().trim();
        if(!data.isEmpty()){
            ClipData temp = ClipData.newPlainText("text", data);
            cpb.setPrimaryClip(temp);
            Toast.makeText(this, "Copied", Toast.LENGTH_LONG).show();
        }
    }

    // new encodeMessage method
    public static String encodeMessage(String message) {
        String initializer = "111111111";
        StringBuilder encodedMessage = new StringBuilder();

        // Convert each character of the message to binary
        for (char c : message.toCharArray()) {
            String binaryChar = Integer.toBinaryString(c);
            // Pad with leading zeros to ensure 8 bits
            binaryChar = String.format("%8s", binaryChar).replace(' ', '0');
            encodedMessage.append(binaryChar);
        }

        // Add the initializer to the beginning of the encoded message
        encodedMessage.insert(0, initializer);

        return encodedMessage.toString();
    }
}