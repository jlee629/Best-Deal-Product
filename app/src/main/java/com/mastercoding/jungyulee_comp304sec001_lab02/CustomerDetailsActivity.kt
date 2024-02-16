package com.mastercoding.jungyulee_comp304sec001_lab02

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CustomerDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_details)

        val paymentOption = intent.getStringExtra("paymentOption")
        val totalPrice = intent.getStringExtra("totalPrice")

        val customerCardNumber = findViewById<EditText>(R.id.customerCardNumber)
        val customerName = findViewById<EditText>(R.id.customerName)
        val customerAddress = findViewById<EditText>(R.id.customerAddress)
        val customerEmail = findViewById<EditText>(R.id.customerEmail)
        val customerPhoneNumber = findViewById<EditText>(R.id.customerPhoneNumber)
        val customerCountry = findViewById<EditText>(R.id.customerCountry)
        val customerCity = findViewById<EditText>(R.id.customerCity)
        val finalOutput = findViewById<TextView>(R.id.finalOutput)

        customerCardNumber.visibility = if (paymentOption == "Cash") View.GONE else View.VISIBLE

        findViewById<Button>(R.id.buttonSubmit).setOnClickListener {
            if (validateInputs(customerName, customerAddress, customerEmail, customerPhoneNumber, customerCountry, customerCity) &&
                validateCardNumber(customerCardNumber, paymentOption)) {
                val name = customerName.text.toString()
                finalOutput.text = "Thank you, $name. \nThe total price of \$$totalPrice will be charged using your ${paymentOption?.toLowerCase()}."
            }
        }
    }

    private fun validateInputs(vararg editTexts: EditText): Boolean {
        var isValid = true
        editTexts.forEach { editText ->
            when (editText.id) {
                R.id.customerPhoneNumber -> {
                    if (!editText.text.toString().matches("\\d+".toRegex())) {
                        editText.error = "Enter a valid number"
                        isValid = false
                    }
                }
                else -> {
                    if (editText.text.isEmpty()) {
                        editText.error = "${editText.hint}"
                        isValid = false
                    }
                }
            }
        }
        return isValid
    }

    private fun validateCardNumber(cardNumberEditText: EditText, paymentOption: String?): Boolean {
        if (paymentOption != "Cash") {
            if (cardNumberEditText.text.isEmpty()) {
                cardNumberEditText.error = "${cardNumberEditText.hint}"
                return false
            } else if (!cardNumberEditText.text.toString().matches("\\d+".toRegex())) {
                cardNumberEditText.error = "Enter a valid card number"
                return false
            }
        }
        return true
    }
}
