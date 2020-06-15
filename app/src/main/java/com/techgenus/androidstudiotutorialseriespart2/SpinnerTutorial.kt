package com.techgenus.androidstudiotutorialseriespart2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_spinner_tutorial.*

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
}
