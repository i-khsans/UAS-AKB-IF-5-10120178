package com.example.uas_10120178.main.catatanharian;

import android.content.Intent;
import android.database.Cursor;
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
public class UpdateActivity extends AppCompatActivity {

    private EditText titleInput;
    private EditText contentInput;
    private Button btn_simpan, btn_kembali;
    private Cursor cursor;
    private Date dt = new Date();
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        dbHelper = new DBHelper(this);
        titleInput = findViewById(R.id.title_note);
        contentInput = findViewById(R.id.content_note);
        btn_simpan = findViewById(R.id.btn_submit);
        btn_kembali = findViewById(R.id.btn_back);

        SimpleDateFormat date = new SimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat time = new SimpleDateFormat("HH:mm a");

        Intent get_ID = getIntent();
        int ID = get_ID.getIntExtra("id", 0);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM note WHERE id = " +
                ID, null);

        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);
            titleInput.setText(cursor.getString(3));
            contentInput.setText(cursor.getString(4));
        }

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("update note set tanggal='"+
                        date.format(dt) +"', waktu='" +
                        time.format(dt) +"', judul='"+
                        titleInput.getText().toString() +"', isi='" +
                        contentInput.getText().toString() +"' where id= "+ID);
                Toast.makeText(getApplicationContext(), "Berhasil diubah", Toast.LENGTH_LONG).show();
                com.example.uas_10120178.main.catatanharian.ListNoteActivity.ln.RefreshListNote();
                finish();
            }
        });

        btn_kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}