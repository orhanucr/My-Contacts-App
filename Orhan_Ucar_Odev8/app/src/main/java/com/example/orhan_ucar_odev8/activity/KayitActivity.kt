package com.example.orhan_ucar_odev8.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.room.Update
import com.example.orhan_ucar_odev8.R
import com.example.orhan_ucar_odev8.databinding.ActivityKayitBinding
import com.example.orhan_ucar_odev8.room.configs.AppDatabase
import com.example.orhan_ucar_odev8.room.models.User
import com.example.orhan_ucar_odev8.ui.anasayfa.AnasayfaFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*


class KayitActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKayitBinding
    private var secilenGrup: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKayitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gruplar = resources.getStringArray(R.array.gruplar)

        binding.spinnerGrup.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                secilenGrup = gruplar[position]
                // Seçilen grupla ilgili işlemler
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Hiçbir şey seçilmediğinde yapılacak işlemler
            }
        }

        goster()

        binding.btnKaydet.setOnClickListener {
            val ad = binding.editTextAd.text.toString()
            val soyad = binding.editTextSoyad.text.toString()
            val telefon = binding.editTextTelefon.text.toString()
            val adres = binding.editTextAdres.text.toString()

            kaydet(ad, soyad, telefon, adres, secilenGrup)
        }

        binding.btnVazgec.setOnClickListener {
            vazgec()
        }

        binding.btnSil.setOnClickListener {
            sil()
        }
    }

    private fun kaydet(ad: String, soyad: String, telefon: String, adres: String, grup: String) {

        if (ad.isEmpty() || soyad.isEmpty() || telefon.isEmpty() || adres.isEmpty()) {
            Toast.makeText(this, "Lütfen tüm alanları doldurun!", Toast.LENGTH_SHORT).show()
            return
        }

        val userDao = AppDatabase.getDatabase(this).userDao()
        val user = User(id = null, ad = ad, soyad = soyad, telefon = telefon, adres = adres, grup = grup)

        GlobalScope.launch {
            val isSuccess = try {
                withContext(Dispatchers.IO) {
                    userDao.insertUser(user)
                }
                true
            } catch (e: Exception) {
                false
            }
            if (isSuccess) {
                Log.d("yeni",user.toString())
                runOnUiThread {
                    val snackbar = Snackbar.make(binding.root, "Kayıt başarılı oldu", Snackbar.LENGTH_SHORT)
                    snackbar.show()

                    Handler(Looper.getMainLooper()).postDelayed({
                        snackbar.dismiss()
                        navigateToAnasayfaFragment()
                    }, 700)
                }

            } else {
                runOnUiThread {
                    Toast.makeText(this@KayitActivity, "Kayıt edilemedi", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    private fun navigateToAnasayfaFragment() {
        val intent = Intent(this, AnasayfaFragment::class.java)
        startActivity(intent)
        finish()
    }

    private fun vazgec() {
        val intent = Intent(this, AnasayfaFragment::class.java)
        startActivity(intent)
        finish()
    }

    private fun sil() {
        val ad = binding.editTextAd.text.toString()
        val soyad = binding.editTextSoyad.text.toString()

        if (ad.isEmpty() || soyad.isEmpty()) {
            Toast.makeText(this, "Lütfen ad ve soyad alanlarını doldurun!", Toast.LENGTH_SHORT).show()
            return
        }

        val userDao = AppDatabase.getDatabase(this).userDao()

        GlobalScope.launch {
            val user = userDao.getUserByAdSoyad(ad, soyad)
            if (user != null) {
                val isSuccess = try {
                    withContext(Dispatchers.IO) {
                        userDao.deleteUser(user)
                    }
                    true
                } catch (e: Exception) {
                    false
                }

                if (isSuccess) {
                    runOnUiThread {
                        Toast.makeText(this@KayitActivity, "Kullanıcı silindi", Toast.LENGTH_SHORT).show()
                        navigateToAnasayfaFragment()
                    }
                } else {
                    runOnUiThread {
                        Toast.makeText(this@KayitActivity, "Kullanıcı silinirken bir hata oluştu", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                runOnUiThread {
                    Toast.makeText(this@KayitActivity, "Belirtilen ad ve soyada sahip kullanıcı bulunamadı", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    @Update
    suspend fun updateUser(user: User): Int {
        val userDao = AppDatabase.getDatabase(applicationContext).userDao()
        return (userDao.updateUser(user) ?: 0) as Int
    }


    private fun goster() {
        val intent = intent
        if (intent != null) {
            val userId = intent.getStringExtra("id")?.toIntOrNull()
            val ad = intent.getStringExtra("ad")
            val soyad = intent.getStringExtra("soyad")
            val telefon = intent.getStringExtra("telefon")
            val adres = intent.getStringExtra("adres")
            val grup = intent.getStringExtra("grup")


            // Verileri ilgili alanlarda göstermek için kullanabilirsiniz
            binding.editTextAd.setText(ad)
            binding.editTextSoyad.setText(soyad)
            binding.editTextTelefon.setText(telefon)
            binding.editTextAdres.setText(adres)

            val gruplar = resources.getStringArray(R.array.gruplar)
            val grupIndex = gruplar.indexOf(grup)
            if (grupIndex != -1) {
                binding.spinnerGrup.setSelection(grupIndex)
            }

            // Tüm alanlar dolu ise btnSil'i görünür yap
            if (ad != null && soyad != null && telefon != null && adres != null) {
                binding.btnSil.visibility = View.VISIBLE
                binding.btnGuncelle.visibility = View.VISIBLE
            } else {
                binding.btnSil.visibility = View.INVISIBLE
                binding.btnGuncelle.visibility = View.INVISIBLE
            }

            binding.btnGuncelle.setOnClickListener {
                val ad = binding.editTextAd.text.toString()
                val soyad = binding.editTextSoyad.text.toString()
                val telefon = binding.editTextTelefon.text.toString()
                val adres = binding.editTextAdres.text.toString()
                val grup = binding.spinnerGrup.selectedItem.toString()

                val updatedUser = User(
                    id = userId,
                    ad = ad,
                    soyad = soyad,
                    telefon = telefon,
                    adres = adres,
                    grup = grup
                )

                GlobalScope.launch(Dispatchers.IO) {
                    val rowsAffected = updateUser(updatedUser)
                    withContext(Dispatchers.Main) {
                        if (rowsAffected > 0) {
                            Toast.makeText(this@KayitActivity, "Kullanıcı güncellendi.", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this@KayitActivity, "Güncelleme işlemi başarısız.", Toast.LENGTH_SHORT).show()
                        }

                        val intent = Intent(this@KayitActivity, AnasayfaFragment::class.java)
                        startActivity(intent)
                    }
                }
            }

        }
    }
}



