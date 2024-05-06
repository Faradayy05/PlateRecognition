package com.skripsi.platerecognition.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skripsi.platerecognition.data.local.entity.Kendaraan
import com.skripsi.platerecognition.databinding.ItemKendaraanBinding
import com.skripsi.platerecognition.ui.platedetail.DetailPlateActivity
import java.io.File

class KendaraanAdapter(private val listKendaraan: List<Kendaraan>) : RecyclerView.Adapter<KendaraanAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemKendaraanBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemKendaraanBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (noPolisi) = listKendaraan[position]
        holder.binding.apply {
            ivAvatar.setImageResource(com.skripsi.platerecognition.R.drawable.car)
            tvNoPolisi.text = noPolisi
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailPlateActivity::class.java)
            intent.putExtra("NO_POLISI", noPolisi)
            intent.putExtra("IMG_FILE", null as File?)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount() = listKendaraan.size
}