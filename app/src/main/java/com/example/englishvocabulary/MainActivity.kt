package com.example.englishvocabulary

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.contains
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_add_vocabulary.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.vocabulary_item.*
import kotlinx.android.synthetic.main.vocabulary_item.view.*
import java.util.*
import java.util.Locale.filter
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var vocabAdapter: vocabAdapter
    private val mItemsList: ArrayList<Vocab> = ArrayList()
    private lateinit var recv:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initItem()
        //loadData()
        recv = findViewById(R.id.rvVob)
        vocabAdapter = vocabAdapter(mItemsList, this)
        recv.adapter = vocabAdapter
        recv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        btnThem.setOnClickListener {
            addVocabulary()


        }
        btnthuchanh.setOnClickListener {
            val intent : Intent = Intent(this, ThucHanh::class.java)
            startActivity(intent)

        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        val menuItem = menu?.findItem(R.id.search)
        val searchView = menuItem?.actionView as SearchView
        performSearch(searchView)
        return super.onCreateOptionsMenu(menu)
    }

    private fun performSearch(searchView: SearchView) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                vocabAdapter.filter.filter(newText)
                return true
            }

        })
    }



    private fun initItem() {

        mItemsList.add(Vocab("hello","n", "xin chao"))
        mItemsList.add(Vocab("one","n" ,"mot"))
        mItemsList.add(Vocab("two", "n","Hai"))


    }

    private fun addVocabulary() {
        val inflate = LayoutInflater.from(this)
        val v = inflate.inflate(R.layout.activity_add_vocabulary, null)
        val title = v.findViewById<EditText>(R.id.txttitle)
        val type = v.findViewById<EditText>(R.id.txttype)
        val mean = v.findViewById<EditText>(R.id.txtmean)

        val addDialog = AlertDialog.Builder(this)
        addDialog.setView(v)
        addDialog.setPositiveButton("OK") {
            dialog,_ ->
            val names = title.text.toString()
            val types = type.text.toString()
            val means = mean.text.toString()
            mItemsList.add(Vocab(names,types,means))
            Toast.makeText(this, "Add the vocabulary success", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        addDialog.setNegativeButton("Cancel") {
            dialog,_ ->
            dialog.dismiss()
            Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show()
        }
        addDialog.create()
        addDialog.show()
    }



}