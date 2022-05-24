package com.app.emicalculator

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun calculateEMI(view: View?) {
        //initialize variables
        val mortgage: Double
        var interestRate: Double
        val years: Double
        //get mortgage from EditText to double
        val m = findViewById<View>(R.id.mortgageInput) as EditText
        val M = m.text.toString()
        mortgage = M.toDouble()
        //get interest rate from EditText to double
        val ir = findViewById<View>(R.id.interestRateInput) as EditText
        val IR = ir.text.toString()
        interestRate = IR.toDouble()
        interestRate /= 100.0
        val montlyIR = interestRate / 12
        //get years from EditText to double
        val y = findViewById<View>(R.id.yearsInput) as EditText
        val Y = y.text.toString()
        years = Y.toDouble()
        //System.out.println(mortgage+" "+interestRate+" "+years);//test
        //declare result variable
        val emi: Double
        //use EMI formula (P × r × (1 + r)n/((1 + r)n - 1)
        val months = years * 12
        val firstPart: Double
        val fraction: Double
        val numerator: Double
        val denominator: Double
        firstPart = mortgage * montlyIR
        numerator = Math.pow(1 + montlyIR, months)
        denominator = Math.pow(1 + montlyIR, months) - 1
        fraction = numerator / denominator
        emi = firstPart * fraction
        //output EMI to outputLabel
        val outputLabel = findViewById<View>(R.id.outputLabel) as TextView
        val dollars = DecimalFormat("₹0.00") //format dollars
        outputLabel.text = """
             Your equated monthly installment is : 
             ${dollars.format(emi)}
             """.trimIndent()
    }
}