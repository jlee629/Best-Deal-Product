package com.mastercoding.jungyulee_comp304sec001_lab02

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AutoAccessoriesActivity : AppCompatActivity() {
    private var checkBoxAutoAccessories1: CheckBox? = null
    private var checkBoxAutoAccessories2: CheckBox? = null
    private var checkBoxAutoAccessories3: CheckBox? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_accessories)

        checkBoxAutoAccessories1 = findViewById<CheckBox>(R.id.checkBox1_gps)
        checkBoxAutoAccessories2 = findViewById<CheckBox>(R.id.checkBox2_radar_detector)
        checkBoxAutoAccessories3 = findViewById<CheckBox>(R.id.checkbox3_speaker)

        restoreSelections()

        val checkoutBtn = findViewById<Button>(R.id.check_Out_Btn)

        checkoutBtn.setOnClickListener {
            saveSelections()
            navigateToCheckOutActivity()
        }
    }

    private fun saveSelections() {
        val preferenceEditor = getSharedPreferences("info", MODE_PRIVATE).edit()

        checkBoxAutoAccessories1?.let {
            preferenceEditor.putBoolean("autoAccessory1State", it.isChecked)
            preferenceEditor.putString("autoAccessory1Name", it.text.toString())
            if (it.isChecked) preferenceEditor.putInt("autoAccessory1Image", R.drawable.auto_accessories_gps)
        }
        checkBoxAutoAccessories2?.let {
            preferenceEditor.putBoolean("autoAccessory2State", it.isChecked)
            preferenceEditor.putString("autoAccessory2Name", it.text.toString())
            if (it.isChecked) preferenceEditor.putInt("autoAccessory2Image", R.drawable.auto_accessories_radar_detector)
        }
        checkBoxAutoAccessories3?.let {
            preferenceEditor.putBoolean("autoAccessory3State", it.isChecked)
            preferenceEditor.putString("autoAccessory3Name", it.text.toString())
            if (it.isChecked) preferenceEditor.putInt("autoAccessory3Image", R.drawable.auto_accessories_speaker)
        }

        preferenceEditor.apply()
        Toast.makeText(this, "Selections saved", Toast.LENGTH_SHORT).show()
    }

    private fun navigateToCheckOutActivity() {
        val intent = Intent(applicationContext, CheckOutActivity::class.java).apply {
            putExtra("title", "Auto Accessories")
        }
        startActivity(intent)
    }

    private fun restoreSelections() {
        val preferences = getSharedPreferences("info", MODE_PRIVATE)

        checkBoxAutoAccessories1?.isChecked = preferences.getBoolean("autoAccessory1State", false)
        checkBoxAutoAccessories2?.isChecked = preferences.getBoolean("autoAccessory2State", false)
        checkBoxAutoAccessories3?.isChecked = preferences.getBoolean("autoAccessory3State", false)
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
