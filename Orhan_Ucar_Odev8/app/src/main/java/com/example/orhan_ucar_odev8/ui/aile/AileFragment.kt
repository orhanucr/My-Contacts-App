package com.example.orhan_ucar_odev8.ui.aile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.orhan_ucar_odev8.R
import com.example.orhan_ucar_odev8.activity.KayitActivity
import com.example.orhan_ucar_odev8.databinding.FragmentAileBinding
import com.example.orhan_ucar_odev8.room.models.User
import com.example.orhan_ucar_odev8.room.configs.AppDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AileFragment : Fragment() {

    private var _binding: FragmentAileBinding? = null
    private val binding get() = _binding!!

    private lateinit var userListAdapter: ArrayAdapter<User>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAileBinding.inflate(inflater, container, false)
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

        binding.listViewAile.adapter = userListAdapter
        binding.listViewAile.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val user = userListAdapter.getItem(position)
                user?.let { navigateToKayitActivity(it) }
            }

        getGroupAile()

        return root
    }

    private fun getGroupAile() {
        val userDao = AppDatabase.getDatabase(requireContext()).userDao()

        GlobalScope.launch {
            val users = userDao.getUsersByGrup("Aile")
            requireActivity().runOnUiThread {
                userListAdapter.clear()
                userListAdapter.addAll(users)

            }
        }
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


