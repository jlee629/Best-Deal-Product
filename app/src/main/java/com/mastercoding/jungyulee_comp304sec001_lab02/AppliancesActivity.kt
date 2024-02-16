package com.mastercoding.jungyulee_comp304sec001_lab02

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AppliancesActivity : AppCompatActivity() {

    private var checkBoxAppliance1: CheckBox? = null
    private var checkBoxAppliance2: CheckBox? = null
    private var checkBoxAppliance3: CheckBox? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appliances)

        checkBoxAppliance1 = findViewById<CheckBox>(R.id.checkBox1_washing_machine)
        checkBoxAppliance2 = findViewById<CheckBox>(R.id.checkBox2_refridgerator)
        checkBoxAppliance3 = findViewById<CheckBox>(R.id.checkbox3_dishwasher)

        restoreSelections()

        val checkoutBtn = findViewById<Button>(R.id.check_Out_Btn)
        checkoutBtn.setOnClickListener {
            saveSelections()
            navigateToCheckOutActivity()
        }
    }

    private fun saveSelections() {
        val preferenceEditor = getSharedPreferences("info", MODE_PRIVATE).edit()

        checkBoxAppliance1?.let {
            preferenceEditor.putBoolean("appliance1State", it.isChecked)
            preferenceEditor.putString("appliance1Name", it.text.toString())
            if (it.isChecked) preferenceEditor.putInt("appliance1Image", R.drawable.appliances_washing_machine)
        }
        checkBoxAppliance2?.let {
            preferenceEditor.putBoolean("appliance2State", it.isChecked)
            preferenceEditor.putString("appliance2Name", it.text.toString())
            if (it.isChecked) preferenceEditor.putInt("appliance2Image", R.drawable.appliances_refrigerator)
        }
        checkBoxAppliance3?.let {
            preferenceEditor.putBoolean("appliance3State", it.isChecked)
            preferenceEditor.putString("appliance3Name", it.text.toString())
            if (it.isChecked) preferenceEditor.putInt("appliance3Image", R.drawable.appliances_dish_washer)
        }

        preferenceEditor.apply()
        Toast.makeText(this, "Selections saved", Toast.LENGTH_SHORT).show()
    }

    private fun restoreSelections() {
        val preferences = getSharedPreferences("info", MODE_PRIVATE)

        checkBoxAppliance1?.isChecked = preferences.getBoolean("appliance1State", false)
        checkBoxAppliance2?.isChecked = preferences.getBoolean("appliance2State", false)
        checkBoxAppliance3?.isChecked = preferences.getBoolean("appliance3State", false)
    }

    private fun navigateToCheckOutActivity() {
        val intent = Intent(applicationContext, CheckOutActivity::class.java).apply {
            putExtra("title", "Appliances")
        }
        startActivity(intent)
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

