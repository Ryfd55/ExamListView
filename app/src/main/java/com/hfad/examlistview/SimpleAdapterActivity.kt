package com.hfad.examlistview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.SimpleAdapter
import androidx.appcompat.app.AlertDialog
import com.hfad.examlistview.databinding.ActivitySimpleListViewBinding

class SimpleAdapterActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySimpleListViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySimpleListViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListViewSimple ()
    }

    private fun setupListViewSimple() {
        val data = listOf(
            mapOf(
                KEY_TITLE to "Americano",
                KEY_DESCRIPTION to "Espresso & hot water"
            ),
            mapOf(
                KEY_TITLE to "Capuchino",
                KEY_DESCRIPTION to "Espresso with steamed & frothed milk"
            ),
            mapOf(
                KEY_TITLE to "Caffe Latte",
                KEY_DESCRIPTION to "Espresso with steamed milk & foam"
            )
        )
        val adapter = SimpleAdapter(
            this,
            data,
            android.R.layout.simple_list_item_2,
            arrayOf(KEY_TITLE, KEY_DESCRIPTION),
            intArrayOf(android.R.id.text1,android.R.id.text2)
        )
        binding.listView.adapter = adapter
        binding.listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val selectedItemTitle = data [position][KEY_TITLE]
            val selectedItemDescription = data [position][KEY_DESCRIPTION]

            val dialog = AlertDialog.Builder(this)
                .setTitle(selectedItemTitle)
                .setMessage(getString(R.string.item_selected_message,selectedItemDescription))
                .setPositiveButton("OK") { dialog, which ->}
                .create ()
            dialog.show()
        }
    }
//    private fun setupListViewWithSimpleGeneratedData() {
//        val data = (1..100).map {
//            mapOf(
//                KEY_TITLE to "Item #$it",
//                KEY_DESCRIPTION to "Description #$it"
//            )
//        }
//        val adapter = SimpleAdapter(
//            this,
//            data,
//            R.layout.item_custom,
//            arrayOf(KEY_TITLE, KEY_DESCRIPTION),
//            intArrayOf(R.id.titleTextView,R.id.descriptionTextView)
//        )
//        binding.listView.adapter = adapter
//    }

    companion object {
        @JvmStatic val KEY_TITLE = "title"
        @JvmStatic val KEY_DESCRIPTION = "description"
    }
}