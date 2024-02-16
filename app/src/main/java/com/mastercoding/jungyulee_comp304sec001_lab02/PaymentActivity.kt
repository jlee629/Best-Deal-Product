package com.mastercoding.jungyulee_comp304sec001_lab02

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        val submitButton = findViewById<Button>(R.id.buttonSubmit)
        val radioGroupPaymentOptions = findViewById<RadioGroup>(R.id.radioBtnGroupPaymentOptions)

        submitButton.setOnClickListener {
            val selectedOptionId = radioGroupPaymentOptions.checkedRadioButtonId

            var selectedPaymentOption = ""

            when (selectedOptionId) {
                R.id.radioButton1 -> selectedPaymentOption = "Cash"
                R.id.radioButton2 -> selectedPaymentOption = "Credit Card"
                R.id.radioButton3 -> selectedPaymentOption = "Debit Card"
            }

            val intent = Intent(applicationContext, CustomerDetailsActivity::class.java).apply {
                putExtra("paymentOption", selectedPaymentOption)
                putExtra("totalPrice", intent.getStringExtra("totalPrice"))
            }
            startActivity(intent)
        }
    }
}
