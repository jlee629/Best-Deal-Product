package com.mastercoding.jungyulee_comp304sec001_lab02

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class TVsActivity : AppCompatActivity() {

    private var checkBoxTv1: CheckBox? = null
    private var checkBoxTv2: CheckBox? = null
    private var checkBoxTv3: CheckBox? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tvs)

        checkBoxTv1 = findViewById<CheckBox>(R.id.checkBox1_neo_qled)
        checkBoxTv2 = findViewById<CheckBox>(R.id.checkBox2_oled)
        checkBoxTv3 = findViewById<CheckBox>(R.id.checkbox3_crystal_uhd)

        val checkoutBtn = findViewById<Button>(R.id.check_Out_Btn)

        restoreSelections()

        checkoutBtn.setOnClickListener {
            saveSelections()
            navigateToCheckOutActivity()
        }
    }

    private fun saveSelections() {
        val preferenceEditor = getSharedPreferences("info", MODE_PRIVATE).edit()

        checkBoxTv1?.let {
            preferenceEditor.putBoolean("tv1State", it.isChecked)
            preferenceEditor.putString("tv1Name", it.text.toString())
            if (it.isChecked) preferenceEditor.putInt("tv1Image", R.drawable.tvs_neo_qled)
        }
        checkBoxTv2?.let {
            preferenceEditor.putBoolean("tv2State", it.isChecked)
            preferenceEditor.putString("tv2Name", it.text.toString())
            if (it.isChecked) preferenceEditor.putInt("tv2Image", R.drawable.tvs_oled)
        }
        checkBoxTv3?.let {
            preferenceEditor.putBoolean("tv3State", it.isChecked)
            preferenceEditor.putString("tv3Name", it.text.toString())
            if (it.isChecked) preferenceEditor.putInt("tv3Image", R.drawable.tvs_crystal_uhd)
        }

        preferenceEditor.apply()
        Toast.makeText(this, "Selections saved", Toast.LENGTH_SHORT).show()
    }

    private fun navigateToCheckOutActivity() {
        val intent = Intent(applicationContext, CheckOutActivity::class.java).apply {
            putExtra("title", "TVs")
        }
        startActivity(intent)
    }


    private fun restoreSelections() {
        val preferences = getSharedPreferences("info", MODE_PRIVATE)

        checkBoxTv1?.isChecked = preferences.getBoolean("tv1State", false)
        checkBoxTv2?.isChecked = preferences.getBoolean("tv2State", false)
        checkBoxTv3?.isChecked = preferences.getBoolean("tv3State", false)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_product_type, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.m_appliances -> {
                Toast.makeText(this,"Displaying Appliances", Toast.LENGTH_SHORT).show()
                intent = Intent(applicationContext, AppliancesActivity::class.java)
                startActivity(intent)
            }
            R.id.m_tvs -> {
                Toast.makeText(this,"Displaying TVs", Toast.LENGTH_SHORT).show()
                intent = Intent(applicationContext, TVsActivity::class.java)
                startActivity(intent)
            }
            R.id.m_computers -> {
                Toast.makeText(this,"Displaying Computers", Toast.LENGTH_SHORT).show()
                intent = Intent(applicationContext, ComputersActivity::class.java)
                startActivity(intent)
            }
            R.id.m_furniture -> {
                Toast.makeText(this,"Displaying Furniture", Toast.LENGTH_SHORT).show()
                intent = Intent(applicationContext, FurnitureActivity::class.java)
                startActivity(intent)
            }
            R.id.m_auto_accessories -> {
                Toast.makeText(this,"Displaying Auto Accessories", Toast.LENGTH_SHORT).show()
                intent = Intent(applicationContext, AutoAccessoriesActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
