package com.mastercoding.jungyulee_comp304sec001_lab02

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class FurnitureActivity : AppCompatActivity() {
    private var checkBoxFurniture1: CheckBox? = null
    private var checkBoxFurniture2: CheckBox? = null
    private var checkBoxFurniture3: CheckBox? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_furniture)

        checkBoxFurniture1 = findViewById<CheckBox>(R.id.checkBox1_bed)
        checkBoxFurniture2 = findViewById<CheckBox>(R.id.checkBox2_sofa)
        checkBoxFurniture3 = findViewById<CheckBox>(R.id.checkbox3_bookcase)

        restoreSelections()

        val checkoutBtn = findViewById<Button>(R.id.check_Out_Btn)

        checkoutBtn.setOnClickListener {
            saveSelections()
            navigateToCheckOutActivity()
        }
    }

    private fun saveSelections() {
        val preferenceEditor = getSharedPreferences("info", MODE_PRIVATE).edit()

        checkBoxFurniture1?.let {
            preferenceEditor.putBoolean("furniture1State", it.isChecked)
            preferenceEditor.putString("furniture1Name", it.text.toString())
            if (it.isChecked) preferenceEditor.putInt("furniture1Image", R.drawable.furniture_bed)
        }
        checkBoxFurniture2?.let {
            preferenceEditor.putBoolean("furniture2State", it.isChecked)
            preferenceEditor.putString("furniture2Name", it.text.toString())
            if (it.isChecked) preferenceEditor.putInt("furniture2Image", R.drawable.furniture_sofa)
        }
        checkBoxFurniture3?.let {
            preferenceEditor.putBoolean("furniture3State", it.isChecked)
            preferenceEditor.putString("furniture3Name", it.text.toString())
            if (it.isChecked) preferenceEditor.putInt("furniture3Image", R.drawable.furniture_bookcase)
        }

        preferenceEditor.apply()
        Toast.makeText(this, "Selections saved", Toast.LENGTH_SHORT).show()
    }

    private fun navigateToCheckOutActivity() {
        val intent = Intent(applicationContext, CheckOutActivity::class.java).apply {
            putExtra("title", "Furniture")
        }
        startActivity(intent)
    }

    private fun restoreSelections() {
        val preferences = getSharedPreferences("info", MODE_PRIVATE)

        checkBoxFurniture1?.isChecked = preferences.getBoolean("furniture1State", false)
        checkBoxFurniture2?.isChecked = preferences.getBoolean("furniture2State", false)
        checkBoxFurniture3?.isChecked = preferences.getBoolean("furniture3State", false)
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
