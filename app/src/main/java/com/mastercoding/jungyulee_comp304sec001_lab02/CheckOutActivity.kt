package com.mastercoding.jungyulee_comp304sec001_lab02

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import android.graphics.Color
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class CheckOutActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private var totalPrice = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_out)

        sharedPreferences = getSharedPreferences("info", MODE_PRIVATE)
        val linLayoutContainer = findViewById<LinearLayout>(R.id.linLayoutContainer)
        val emptyCheckoutTextView = findViewById<TextView>(R.id.emptyCheckoutTextView)
        val buttonNextbtn = findViewById<Button>(R.id.buttonNextBtn)

        val productCategories = listOf(
            "appliance" to 3,
            "tv" to 3,
            "computer" to 3,
            "furniture" to 3,
            "autoAccessory" to 3
        )

        productCategories.forEach { (category, count) ->
            for (i in 1..count) {
                val productKey = "$category$i"
                val state = sharedPreferences.getBoolean("${productKey}State", false)
                if (state) {
                    val name = sharedPreferences.getString("${productKey}Name", null) ?: ""
                    val imageResId = sharedPreferences.getInt("${productKey}Image", 0)
                    addProductView(category, i, name, imageResId, linLayoutContainer, emptyCheckoutTextView)
                }
            }
        }

        updateEmptyViewVisibility(linLayoutContainer, emptyCheckoutTextView)
        updateTotalPrice()

        buttonNextbtn.setOnClickListener {
            if (totalPrice == 0){
                Toast.makeText(applicationContext,"Please select at least one", Toast.LENGTH_SHORT).show()
            } else {
                intent = Intent(applicationContext, PaymentActivity::class.java).apply {
                    putExtra("totalPrice", totalPrice.toString())
                }
                startActivity(intent)
            }
        }
    }

    private fun addProductView(category: String, index: Int, name: String, imageResId: Int, container: LinearLayout, emptyView: TextView) {
        val pricePattern = "\\$(\\d+)".toRegex()
        val price = pricePattern.find(name)?.groupValues?.get(1)?.toIntOrNull() ?: 0
        totalPrice += price

        val productLayout = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            tag = "productView"
            setPadding(dpToPx(8), dpToPx(8), dpToPx(8), dpToPx(8))
        }

        val textView = TextView(this).apply {
            text = name
            textSize = 20f
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).also { lp ->
                lp.setMargins(dpToPx(4), dpToPx(4), dpToPx(4), dpToPx(4))
            }
            maxLines = 2
        }

        val imageView = ImageView(this).apply {
            layoutParams = LinearLayout.LayoutParams(dpToPx(50), dpToPx(50)).also { lp ->
                lp.setMargins(dpToPx(4), dpToPx(4), dpToPx(4), dpToPx(4))
            }
            scaleType = ImageView.ScaleType.CENTER_INSIDE
            setImageResource(imageResId)
        }

        val deleteButton = ImageButton(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).also { lp ->
                lp.setMargins(dpToPx(4), dpToPx(4), dpToPx(4), dpToPx(4))
            }
            setImageResource(android.R.drawable.ic_menu_delete)
            setBackgroundColor(Color.TRANSPARENT)
            setOnClickListener {
                sharedPreferences.edit().apply {
                    remove("${category}${index}State")
                    remove("${category}${index}Name")
                    remove("${category}${index}Image")
                    apply()
                }
                container.removeView(productLayout)
                totalPrice -= price
                updateEmptyViewVisibility(container, emptyView)
                updateTotalPrice()
            }
        }

        productLayout.addView(textView)
        productLayout.addView(imageView)
        productLayout.addView(deleteButton)
        container.addView(productLayout)
    }

    private fun updateTotalPrice() {
        val totalPriceTextView = findViewById<TextView>(R.id.totalPriceTextView)
        totalPriceTextView.text = "Total Price: $$totalPrice"
        totalPriceTextView.visibility = if (totalPrice == 0) View.GONE else View.VISIBLE
    }

    private fun updateEmptyViewVisibility(container: LinearLayout, emptyView: TextView) {
        var hasProductView = false
        for (i in 0 until container.childCount) {
            if (container.getChildAt(i).tag == "productView") {
                hasProductView = true
                break
            }
        }
        emptyView.visibility = if (hasProductView) View.GONE else View.VISIBLE
    }

    private fun dpToPx(dp: Int): Int {
        return (dp * resources.displayMetrics.density).toInt()
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
