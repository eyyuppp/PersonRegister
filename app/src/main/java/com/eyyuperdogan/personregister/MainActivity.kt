package com.eyyuperdogan.personregister

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.eyyuperdogan.personregister.adapter.UserAdapter
import com.eyyuperdogan.personregister.databinding.ActivityMainBinding
import com.eyyuperdogan.personregister.model.User
import com.eyyuperdogan.personregister.roomDb.UserDao
import com.eyyuperdogan.personregister.roomDb.UserDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

@SuppressLint("StaticFieldLeak")
private lateinit var binding: ActivityMainBinding
private var compositeDisposable=CompositeDisposable()
lateinit var db:UserDatabase
lateinit var userDao: UserDao
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

      db=Room.databaseBuilder(applicationContext,UserDatabase::class.java,"user").build()
      userDao= db.userDao()

     compositeDisposable.add(
         userDao.getall()
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe(this::handleResponse)
     )


        binding.textViewAdd.setOnClickListener(){
            var intent=Intent(applicationContext,PersonAdd::class.java)
            startActivity(intent)
        }
    }
    private fun handleResponse(placeList: List<User>){
        binding.recyclerView.layoutManager= LinearLayoutManager(this)
        val adapter=UserAdapter(placeList)
        binding.recyclerView.adapter=adapter
    }


}