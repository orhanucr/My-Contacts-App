package com.example.orhan_ucar_odev8.ui.anasayfa

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.orhan_ucar_odev8.R
import com.example.orhan_ucar_odev8.activity.KayitActivity
import com.example.orhan_ucar_odev8.databinding.FragmentAnasayfaBinding
import com.example.orhan_ucar_odev8.room.models.User
import com.example.orhan_ucar_odev8.room.configs.AppDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AnasayfaFragment : Fragment() {

    private var _binding: FragmentAnasayfaBinding? = null
    private val binding get() = _binding!!

    private lateinit var userListAdapter: ArrayAdapter<User>
    private lateinit var editTextSearch: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnasayfaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        userListAdapter = object : ArrayAdapter<User>(
            requireContext(),
            R.layout.list_item,
            R.id.textAd,
            mutableListOf()
        ) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent)
                val user = getItem(position)

                val textViewAd = view.findViewById<TextView>(R.id.textAd)
                val textViewSoyad = view.findViewById<TextView>(R.id.textSoyad)
                val textViewTelefon = view.findViewById<TextView>(R.id.textTelefon)
                val textViewAdres = view.findViewById<TextView>(R.id.textAdres)
                val textViewGrup = view.findViewById<TextView>(R.id.textGrup)

                textViewAd.text = user?.ad
                textViewSoyad.text = user?.soyad
                textViewTelefon.text = user?.telefon
                textViewAdres.text = user?.adres
                textViewGrup.text = user?.grup

                return view
            }
        }

        binding.listViewItems.adapter = userListAdapter
        binding.listViewItems.adapter = userListAdapter
        binding.listViewItems.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val user = userListAdapter.getItem(position)
                user?.let { navigateToKayitActivity(it) }
            }

        editTextSearch = binding.editTextSearch
        editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val searchQuery = s.toString()
                searchUsersByName(searchQuery)
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        displayLast10Users()

        return root
    }

    private fun navigateToKayitActivity(user: User) {
        val intent = Intent(requireContext(), KayitActivity::class.java)
        intent.putExtra("id", user.id.toString())
        intent.putExtra("ad", user.ad)
        intent.putExtra("soyad", user.soyad)
        intent.putExtra("telefon", user.telefon)
        intent.putExtra("adres", user.adres)
        intent.putExtra("grup", user.grup)
        startActivity(intent)
    }


    private fun displayLast10Users() {
        val userDao = AppDatabase.getDatabase(requireContext()).userDao()

        GlobalScope.launch {
            val users = userDao.getLast10Users()
            requireActivity().runOnUiThread {
                userListAdapter.clear()
                userListAdapter.addAll(users)
            }
        }
    }

    private fun searchUsersByName(name: String) {
        val userDao = AppDatabase.getDatabase(requireContext()).userDao()

        GlobalScope.launch {
            val users = userDao.getUsersByName("%$name%")
            requireActivity().runOnUiThread {
                userListAdapter.clear()
                userListAdapter.addAll(users)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}