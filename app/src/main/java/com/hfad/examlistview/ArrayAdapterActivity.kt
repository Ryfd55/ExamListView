package com.hfad.examlistview

import android.R
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.hfad.examlistview.databinding.ActivityListViewBinding
import com.hfad.examlistview.databinding.DialogAddCharacterBinding
import java.util.*

class ArrayAdapterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListViewBinding
    private lateinit var adapter: ArrayAdapter<Character>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListViewBinding.inflate(layoutInflater).also { setContentView(it.root) }
        binding.addButton2.setOnClickListener { onBackButtonPressed() }

        binding = ActivityListViewBinding.inflate(layoutInflater)
        setContentView(binding.root)



        setupListWithArrayAdapter()
        binding.addButton.setOnClickListener { onAddPressed() }
    }
    private fun onBackButtonPressed() {
        val intent = Intent(this@ArrayAdapterActivity, SimpleAdapterActivity::class.java)
        startActivity(intent)
    }
    private fun setupListWithArrayAdapter() {
        val data = mutableListOf(
            Character(id = UUID.randomUUID().toString(),name = "NAME1"),
            Character(id = UUID.randomUUID().toString(),name = "NAME2"),
            Character(id = UUID.randomUUID().toString(),name = "NAME3"),
            Character(id = UUID.randomUUID().toString(),name = "NAME4"),
            Character(id = UUID.randomUUID().toString(),name = "NAME5"),
            Character(id = UUID.randomUUID().toString(),name = "NAME6"),
        )
        adapter = ArrayAdapter(
            this,
            R.layout.simple_list_item_1,
            R.id.text1,
            data
        )

        binding.listView.adapter = adapter

        binding.listView.setOnItemClickListener { parent, view, position, id ->
        deleteCharacter (adapter.getItem(position)!!)
        }
    }
    private fun onAddPressed() {
        val dialogBinding = DialogAddCharacterBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(this)
            .setTitle("Create character")
            .setView(dialogBinding.root)
            .setPositiveButton("Add") { d, which ->
                val name = dialogBinding.characterNameEditText.text.toString()
                if (name.isNotBlank()) {
                    createCharacter (name)
                }
            }
            .create ()
        dialog.show()
    }
    private fun createCharacter (name: String) {
        val character = Character(
            id = UUID.randomUUID().toString(),
            name = name
        )
        adapter.add(character)
    }
    private  fun deleteCharacter(character: Character) {
        val listener = DialogInterface.OnClickListener{ dialog, which ->
            if (which == DialogInterface.BUTTON_POSITIVE) {
                adapter.remove(character)
            }
        }
        val dialog = AlertDialog.Builder(this)
            .setTitle("Delete character")
            .setMessage("Are you sure you want to delete the character '{charact...")
            .setPositiveButton("Delete", listener)
            .setNegativeButton("Cancel", listener)
            .create()
        dialog.show()
    }

    class Character (
        val id: String,
        val name: String
            ){
        override fun toString(): String {
            return name
        }
    }
}