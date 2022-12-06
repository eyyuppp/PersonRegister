package com.eyyuperdogan.personregister

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.AlertDialogLayout
import com.eyyuperdogan.personregister.databinding.ActivityPersonAddBinding
import com.eyyuperdogan.personregister.model.User
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers


@SuppressLint("StaticFieldLeak")
private var compositeDisposable = CompositeDisposable()
private lateinit var binding: ActivityPersonAddBinding
var userFromMain:User?=null

class PersonAdd : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var slectedUsername=intent.getStringExtra("username")
        var PhoneNumber=intent.getStringExtra("numberPhone")

        var selected= intent.getStringExtra("info")

        userFromMain=intent.getSerializableExtra("users") as? User

        if (selected=="new"){
            binding.editTextTextPersonName.setText(slectedUsername)
            binding.editTextPhoneNumber.setText(PhoneNumber)

        }


        //KAYDETME
        binding.buttonSave.setOnClickListener() {
            var nameUser = binding.editTextTextPersonName.text.toString()
            var numberPhone = binding.editTextPhoneNumber.text.toString()
            var user = User(nameUser, numberPhone)
            if (nameUser.isNotEmpty() && numberPhone.isNotEmpty()) {
               compositeDisposable.add(
                userDao.insert(user)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::hendleReponse)
               )
            } else {
                Toast.makeText(this, "ad veya telefon numarası boş olamaz", Toast.LENGTH_LONG).show()
            }
        }


        //SİLME
        binding.buttonDelete.setOnClickListener() {
          userFromMain?.let {
                compositeDisposable.add(
                userDao.delete(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::hendleReponse)
                )
            }
        }







    }
    @SuppressLint("SuspiciousIndentation")
    private fun hendleReponse()
    {
        val intent= Intent(this,MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }
}