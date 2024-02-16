package com.mastercoding.jungyulee_comp304sec001_lab02

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ComputersActivity : AppCompatActivity() {
    private var checkBoxComputers1: CheckBox? = null
    private var checkBoxComputers2: CheckBox? = null
    private var checkBoxComputers3: CheckBox? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_computers)

        checkBoxComputers1 = findViewById<CheckBox>(R.id.checkBox1_imac)
        checkBoxComputers2 = findViewById<CheckBox>(R.id.checkBox2_macbook_pro)
        checkBoxComputers3 = findViewById<CheckBox>(R.id.checkbox3_macbook_air)

        restoreSelections()

        val checkoutBtn = findViewById<Button>(R.id.check_Out_Btn)

        checkoutBtn.setOnClickListener {
            saveSelections()
            navigateToCheckOutActivity()
        }
    }

    private fun saveSelections() {
        val preferenceEditor = getSharedPreferences("info", MODE_PRIVATE).edit()

        checkBoxComputers1?.let {
            preferenceEditor.putBoolean("computer1State", it.isChecked)
            preferenceEditor.putString("computer1Name", it.text.toString())
            if (it.isChecked) preferenceEditor.putInt("computer1Image", R.drawable.computers_imac)
        }
        checkBoxComputers2?.let {
            preferenceEditor.putBoolean("computer2State", it.isChecked)
            preferenceEditor.putString("computer2Name", it.text.toString())
            if (it.isChecked) preferenceEditor.putInt("computer2Image", R.drawable.computers_macbook_pro)
        }
        checkBoxComputers3?.let {
            preferenceEditor.putBoolean("computer3State", it.isChecked)
            preferenceEditor.putString("computer3Name", it.text.toString())
            if (it.isChecked) preferenceEditor.putInt("computer3Image", R.drawable.computers_macbook_air)
        }

        preferenceEditor.apply()
        Toast.makeText(this, "Selections saved", Toast.LENGTH_SHORT).show()
    }

    private fun navigateToCheckOutActivity() {
        val intent = Intent(applicationContext, CheckOutActivity::class.java).apply {
            putExtra("title", "Computers")
        }
        startActivity(intent)
    }

    private fun restoreSelections() {
        val preferences = getSharedPreferences("info", MODE_PRIVATE)

        checkBoxComputers1?.isChecked = preferences.getBoolean("computer1State", false)
        checkBoxComputers2?.isChecked = preferences.getBoolean("computer2State", false)
        checkBoxComputers3?.isChecked = preferences.getBoolean("computer3State", false)
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
