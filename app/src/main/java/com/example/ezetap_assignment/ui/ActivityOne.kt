package com.example.ezetap_assignment.ui

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.ezetap_assignment.R
import com.example.ezetap_assignment.data.HomeRepository
import com.example.ezetap_assignment.databinding.ActivityOneBinding
import com.example.ezetap_assignment.network.RetrofitService
import com.example.ezetap_assignment.utils.ConnectivityLiveData
import com.example.ezetap_assignment.viewmodel.MyViewModel
import com.example.ezetap_assignment.viewmodel.MyViewModelFactory
import com.squareup.picasso.Picasso


class ActivityOne : AppCompatActivity() {
    private lateinit var connectivityLiveData: ConnectivityLiveData
    private lateinit var binding: ActivityOneBinding
    private val retrofitService = RetrofitService.getInstance()
    lateinit var viewModel: MyViewModel
    var internetCheck: Boolean = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one)
        binding = ActivityOneBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel =
            ViewModelProvider(this, MyViewModelFactory(HomeRepository(retrofitService))).get(
                MyViewModel::
                class.java
            )
        val progressBar = binding.progressBar

        connectivityLiveData = ConnectivityLiveData(application)
        /* connectivityLiveData.observe(this, Observer { isAvailable: Boolean? ->
             when (isAvailable) {
                 true -> binding.internetChecking.text = "Connected with Internet"
                 false -> binding.internetChecking.text = "No Network"
             }
         })
 */


        if (checkConnection(this)) {
            viewModel.customUIData.observe(this,
                {
                    progressBar.visibility = View.GONE
                    it.uidata.indices.forEach { i ->
                        println(it.uidata[i])
                        if (it.uidata[i].uitype == "label" && it.uidata[i].key == "label_name") {
                            binding.nameLabel.text = it.uidata[i].value
                        }

                        if (it.uidata[i].uitype == "label" && it.uidata[i].key == "label_phone") {
                            binding.phoneLabel.text = it.uidata[i].value
                        }

                        if (it.uidata[i].uitype == "label" && it.uidata[i].key == "label_city") {
                            binding.cityLabel.text = it.uidata[i].value
                        }

                        if (it.uidata[i].uitype == "edittext" && it.uidata[i].key == "text_name") {
                            binding.edtName.hint = it.uidata[i].hint
                        }
                        if (it.uidata[i].uitype == "edittext" && it.uidata[i].key == "text_phone") {
                            binding.edtPhone.hint = it.uidata[i].hint
                        }
                        if (it.uidata[i].uitype == "edittext" && it.uidata[i].key == "text_city") {
                            binding.edtCity.hint = it.uidata[i].hint
                        }

                        if (it.uidata[i].uitype == "button") {
                            binding.uiBtn.text = it.uidata[i].value;  // "Submit"
                        }
                    }


                })
            viewModel.errorMessage.observe(this,
                {

                })
            viewModel.customUIData()


            viewModel.fetchLogoUrl.observe(this,
                {
                    progressBar.visibility = View.GONE
                    binding.logoUrl.visibility = View.VISIBLE
                    Log.e("fetchLogoUrl", "fetchLogoUrl: $it")
                    val logo: String = it.logoUrl;
                    Picasso.get().load(logo).placeholder(R.mipmap.ic_launcher)
                        .into(binding.logoUrl);
                    println(logo)
                })
            viewModel.fetchLogoUrlData()

        } else {
            binding.internetChecking.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
            binding.uiBtn.visibility = View.GONE

        }

        binding.uiBtn.setOnClickListener()
        {
            //start intent for new Activity and pass data.
            val intent = Intent(this@ActivityOne, ActivityTwo::class.java)
            intent.putExtra("Name", binding.edtName.hint)
            intent.putExtra("Phone", binding.edtPhone.hint)
            intent.putExtra("City", binding.edtCity.hint)
            startActivity(intent)


        }


    }

    private fun checkConnection(context: Context): Boolean {
        val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connMgr.activeNetworkInfo
        if (activeNetworkInfo != null) { // connected to the internet
            // connected to the mobile provider's data plan
            return if (activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
                true
            } else activeNetworkInfo.type == ConnectivityManager.TYPE_MOBILE
        }
        return false
    }


}


