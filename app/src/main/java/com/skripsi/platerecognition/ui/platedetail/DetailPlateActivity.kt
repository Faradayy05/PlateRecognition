package com.skripsi.platerecognition.ui.platedetail

import android.graphics.BitmapFactory
import android.graphics.Shader
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.skripsi.platerecognition.R
import com.skripsi.platerecognition.data.local.entity.Kendaraan
import com.skripsi.platerecognition.data.remote.response.DataItem
import com.skripsi.platerecognition.data.remote.retrofit.ApiConfig.Companion.token
import com.skripsi.platerecognition.databinding.ActivityDetailPlateBinding
import com.skripsi.platerecognition.repository.Result
import com.skripsi.platerecognition.ui.platedetail.DetailPlateViewModel.Companion.noPolisi
import com.skripsi.platerecognition.utils.ViewModelFactory
import java.io.File

class DetailPlateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPlateBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private val detailPlateViewModel by viewModels<DetailPlateViewModel> {
        ViewModelFactory.getInstance(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPlateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()

    }

    private fun setupView() {
        supportActionBar?.hide()
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        val img = intent.getSerializableExtra(EXTRA_IMG_FILE) as File?

        if (img != null) {
            if (img.exists()) {
                binding.imgKendaraan.setImageURI(img.toUri())
            }
        } else {
            binding.imgKendaraan.setImageResource(R.drawable.car)
        }

        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.memphis_bg)
        val drawable = BitmapDrawable(resources, bitmap)
        drawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)
        binding.detailPlateLyt.background = drawable

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
        auth = Firebase.auth
        token = auth.currentUser?.getIdToken(false)?.result?.token.toString()

        noPolisi = intent.getStringExtra(EXTRA_NO_POLISI).toString()

        val kendaraan = Kendaraan()

        detailPlateViewModel.detailPlate.observe(this) { result ->
            if (result != null) {
                when (result) {
                    is Result.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.errMsg.visibility = View.GONE
                    }
                    is Result.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.errMsg.visibility = View.GONE
                        val detailPlate = result.data.data
                        if (detailPlate != null) {
                            setDetailPLate(detailPlate)
                            kendaraan.noPolisi = detailPlate[0].noPolisi.toString()
                            kendaraan.merk = detailPlate[0].idMerekKendaraan.toString()
                            kendaraan.type = detailPlate[0].idTypeKendaraan.toString()
                            kendaraan.jenis = detailPlate[0].idJenisKendaraan.toString()
                        }
                    }
                    is Result.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.errMsg.text = result.error
                        binding.errMsg.visibility = View.VISIBLE
                    }
                }
            }
        }

        var isSaved = false
        detailPlateViewModel.getKendaraanById(noPolisi).observe(this) {
            if (it != null) {
                isSaved = true
                binding.fabSave.setImageResource(R.drawable.floppy_disk_fill)
            } else {
                isSaved = false
                binding.fabSave.setImageResource(R.drawable.floppy_disk)
            }
        }

        binding.fabSave.setOnClickListener {
            if (isSaved) {
                detailPlateViewModel.deleteKendaraan(kendaraan)
                Toast.makeText(this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show()
            } else {
                detailPlateViewModel.insertKendaraan(kendaraan)
                Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show()
            }
            isSaved = !isSaved
        }
    }

    private fun setDetailPLate(detailPlate: List<DataItem>) {
        binding.apply {
            val data = detailPlate[0]
            platKend.text = data.noPolisi
            txtNoDaftarEriValue.text = data.noDaftarEri
            txtTanggalMaxBayarPkbValue.text = data.tanggalMaxBayarPkb
            txtTanggalAkhirStnkValue.text = data.tanggalAkhirStnk
            txtTanggalAkhirStnkLamaValue.text = data.tanggalAkhirStnkLama
            txtTanggalFakturValue.text = data.tanggalFaktur
            txtFungsiKendaraanValue.text = data.idFungsiKendaraan.toString()
            txtIdKepemilikanValue.text = data.idKepemilikan.toString()
            txtTypeValue.text = data.idTypeKendaraan.toString()
            txtTahunRakitValue.text = data.tahunRakit.toString()
            txtDati2ProsesValue.text = data.dati2Proses
            txtPkbPokokValue.text = data.pkbPokok.toString()
            txtNoDaftarValue.text = data.noDaftar
            txtNjkbValue.text = data.njkb.toString()
            txtTanggalJatuhTempoLamaValue.text = data.tanggalJatuhTempoLama
            txtBebanDendaValue.text = data.bbn1Denda.toString()
            txtTanggalJatuhTempoDpwkpValue.text = data.tanggalJatuhTempoDpwkp
            txtNoMesinValue.text = data.noMesin
            txtNoBpkbValue.text = data.noBpkb
            txtIdWarnaTnkbValue.text = data.idWarnaTnkb.toString()
            txtDpwkpValue.text = data.dpwkp.toString()
            txtTanggalJatuhTempoValue.text = data.tanggalJatuhTempo
            txtPkbDendaValue.text = data.pkbDenda.toString()
            txtSwdklljPokokValue.text = data.swdklljPokok.toString()
            txtTanggalKwitansiValue.text = data.tanggalKwitansi
            txtTanggalMaxBayarBebanValue.text = data.tanggalMaxBayarBbn
            txtNoRangkaValue.text = data.noRangka
            txtBebanPokokValue.text = data.bbn1Pokok.toString()
            txtIdLokasiProsesValue.text = data.idLokasiProses.toString()
            if (data.statusBlokir == false) {
                txtStatusBlokirValue.text = "Aktif"
            } else {
                txtStatusBlokirValue.text = "Tidak Aktif"
            }
            if (data.subsidi == false) {
                txtSubsidiValue.text = "Tidak"
            } else {
                txtSubsidiValue.text = "Ya"
            }
            txtNoPolisiValue.text = data.noPolisi
            txtTahunBuatValue.text = data.tahunBuat.toString()
            txtProgresifTarifValue.text = data.progresifTarif.toString()
            txtNoTeleponValue.text = data.noTelp
            txtBahanBakarValue.text = data.idBahanBakar.toString()
            txtKodePembayaranValue.text = data.kodePembayaran
            txtNoSkpdValue.text = data.noSkpd
            txtIdLokasiValue.text = data.idLokasi.toString()
            txtModelValue.text = data.idModelKendaraan.toString()
            txtJumlahSumbuValue.text = data.jumlahSumbu.toString()
            txtIdPendaftaranValue.text = data.idPendaftaran.toString()
            txtIdJenisMapValue.text = data.idJenisMap.toString()
            txtIdStatusValue.text = data.idStatus.toString()
            txtTanggalBayarValue.text = data.tanggalBayar
            txtTahunUbValue.text = data.tahunUb.toString()
            txtKkValue.text = data.noKk
            txtSwdklljDendaValue.text = data.swdklljDenda.toString()
            txtMerkValue.text = data.idMerekKendaraan.toString()
            txtDati2IndukValue.text = data.dati2Induk
            txtProgresifValue.text = data.progresif.toString()
            txtNamaValue.text = data.namaPemilik
            txtWarnaKendaraanValue.text = data.warnaKendaraan.toString()
            txtJenisValue.text = data.idJenisKendaraan.toString()
            txtNoKohirValue.text = data.noKohir
            txtNoSkumValue.text = data.noSkum
            txtNoPolisiLamaValue.text = data.noPolisiLama
            txtKodeJenisValue.text = data.kodeJenis
            txtStnkValue.text = data.stnk.toString()
            txtTujuanMutasiValue.text = data.tujuanMutasi
            txtTanggalMaxBayarSwdklljValue.text = data.tanggalMaxBayarSwdkllj
            txtNamaPemilikLamaValue.text = data.namaPemilikLama
            txtPkbBungaValue.text = data.pkbBunga.toString()
            txtAlamat1Value.text = data.alamat1
            txtTahunBerlakuValue.text = data.tahunBerlaku.toString()
            txtAlamat2Value.text = data.alamat2
            txtCylinderValue.text = data.cylinder.toString()
            txtTanggalDaftarValue.text = data.tanggalDaftar
            txtKetDpwkpValue.text = data.ketDpwkp
            txtIdGolonganKendaraanValue.text = data.idGolonganKendaraan.toString()
            txtIdKelurahanValue.text = data.idKelurahan.toString()
        }
    }

    companion object {
        const val EXTRA_IMG_FILE = "IMG_FILE"
        const val EXTRA_NO_POLISI = "NO_POLISI"
    }
}