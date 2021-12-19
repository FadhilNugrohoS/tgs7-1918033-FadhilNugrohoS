package com.example.restapi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainRead extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private ListView mListView;
    private CustomListAdapter adapter_off;
    private MyDatabase db;
    private List<Barang> ListBarang = new ArrayList<Barang>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_read);
        db = new MyDatabase(this);
        adapter_off = new CustomListAdapter(this, ListBarang
        );
        mListView = (ListView) findViewById(R.id.list_barang);
        mListView.setAdapter(adapter_off);
        mListView.setOnItemClickListener(this);
        mListView.setClickable(true);
        ListBarang.clear();
        List<Barang> barang = db.ReadBarang();
        for (Barang brg : barang) {
            Barang daftar = new Barang();
            daftar.set_id(brg.get_id());
            daftar.set_nama(brg.get_nama());
            daftar.set_harga(brg.get_harga());
            ListBarang.add(daftar);if ((ListBarang.isEmpty()))
                Toast.makeText(MainRead.this, "Tidak ada data",
                        Toast.LENGTH_SHORT).show();
            else {
            }
        }
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
        Object o = mListView.getItemAtPosition(i);
        Barang detailBrg = (Barang)o;
        String Sid = detailBrg.get_id();
        String Snama = detailBrg.get_nama();
        String Sharga = detailBrg.get_harga();
        Intent goUpdel = new Intent(MainRead.this, MainUpdel.class);
        goUpdel.putExtra("Iid", Sid);
        goUpdel.putExtra("Inama", Snama);
        goUpdel.putExtra("Iharga", Sharga);
        startActivity(goUpdel);
    }
    @Override
    protected void onResume() {
        super.onResume();
        ListBarang.clear();
        mListView.setAdapter(adapter_off);
        List<Barang> barang = db.ReadBarang();
        for (Barang brg : barang) {
            Barang daftar = new Barang();
            daftar.set_id(brg.get_id());
            daftar.set_nama(brg.get_nama());
            daftar.set_harga(brg.get_harga());
            ListBarang.add(daftar);
            if ((ListBarang.isEmpty()))
                Toast.makeText(MainRead.this, "Tidak ada data",
                        Toast.LENGTH_SHORT).show();
            else {
            }
        }
    }
}
