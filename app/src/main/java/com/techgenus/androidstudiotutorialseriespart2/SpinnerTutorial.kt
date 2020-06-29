package com.techgenus.androidstudiotutorialseriespart2

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_spinner_tutorial.*
import java.util.*

@Suppress("UNREACHABLE_CODE")
class SpinnerTutorial : AppCompatActivity() {

    val dropDownList = arrayOf("option A", "option B","Option C")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner_tutorial)

        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,dropDownList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        statusFilter.adapter = adapter
        statusFilter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?,view: View?,position: Int,id: Long) {

                if(statusFilter.selectedItemPosition ==0){
                    condition1()
                }
                if(statusFilter.selectedItemPosition ==1){
                    condition2()
                }
                if(statusFilter.selectedItemPosition ==2){
                    condition3()
                }
            }


        }

        //Todo Date picker Tutorial

        edtDatePicker.setOnClickListener {
            setDate()
        }
    }
    private fun condition1(){
       Toast.makeText(this,"selected Item: " + statusFilter.selectedItem, Toast.LENGTH_LONG).show()
    }
    private fun condition2(){
        Toast.makeText(this,"selected Item: " + statusFilter.selectedItem, Toast.LENGTH_LONG).show()
    }
    private fun condition3(){
        Toast.makeText(this,"Checked Item: ", Toast.LENGTH_LONG).show()
    }

    private fun setDate(){
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)



        val dpd = DatePickerDialog(
            this, DatePickerDialog.OnDateSetListener{view,year,monthOfYear,dayOfMonth->
                val returnDate = "${monthOfYear + 1} $dayOfMonth $year"
                val date = StringHelper.parseDate(
                    "MM dd yyyy",
                    "MM/dd/yyyy",
                    returnDate
                )
                val dateString = date
                edtDatePicker.setText(StringHelper.parseDate("MM/dd/yyyy","MMM dd yyyy",date))
                edtDatePicker.error = null
                Toast.makeText(this, "pick date input format and display $dateString",Toast.LENGTH_LONG).show()
                //Thanks for watching this tutorial
            },year-30,month,day
        )
        dpd.show()
    }
}
