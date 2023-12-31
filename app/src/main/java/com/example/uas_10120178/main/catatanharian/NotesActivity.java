package com.example.uas_10120178.main.catatanharian;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uas_10120178.R;
import com.example.uas_10120178.base.DBHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

//06 Agustus 2023, 10120178, Muhammad Noer Ikhsan Hidayat, IF05
public class NotesActivity extends AppCompatActivity {

    private EditText titleInput;
    private EditText contentInput;
    private Button btn_simpan, btn_kembali;
    int kategori;
    Date dt = new Date();
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        dbHelper = new DBHelper(this);
        titleInput = findViewById(R.id.title_note);
        contentInput = findViewById(R.id.content_note);
        btn_simpan = findViewById(R.id.btn_submit);
        btn_kembali = findViewById(R.id.btn_back);

        SimpleDateFormat date = new SimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat time = new SimpleDateFormat("HH:mm a");

        Intent getKategori = getIntent();
        kategori = getKategori.getIntExtra("kategori", 0);

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into note(tanggal, waktu, judul, isi, id_kategori) values('"+ date.format(dt) +"','" +
                        time.format(dt) + "','" +
                        titleInput.getText().toString() + "','"+
                        contentInput.getText().toString() +"','" +
                        kategori + "')");
                Toast.makeText(getApplicationContext(), "Berhasil Buat Note!", Toast.LENGTH_LONG).show();
                ListNoteActivity.ln.RefreshListNote();
                finish();
            }
        });

        btn_kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }
}