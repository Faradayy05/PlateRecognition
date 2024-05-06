package com.skripsi.platerecognition.data.remote.response

import com.google.gson.annotations.SerializedName

data class DetailPlateResponse(

	@field:SerializedName("data")
	val data: List<DataItem>? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class DataItem(

	@field:SerializedName("no_daftar_eri")
	val noDaftarEri: String? = null,

	@field:SerializedName("tanggal_max_bayar_pkb")
	val tanggalMaxBayarPkb: String? = null,

	@field:SerializedName("tanggal_akhir_stnk")
	val tanggalAkhirStnk: String? = null,

	@field:SerializedName("tanggal_akhir_stnk_lama")
	val tanggalAkhirStnkLama: String? = null,

	@field:SerializedName("tanggal_faktur")
	val tanggalFaktur: String? = null,

	@field:SerializedName("id_fungsi_kendaraan")
	val idFungsiKendaraan: Int? = null,

	@field:SerializedName("id_kepemilikan")
	val idKepemilikan: String? = null,

	@field:SerializedName("id_type_kendaraan")
	val idTypeKendaraan: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("tahun_rakit")
	val tahunRakit: Int? = null,

	@field:SerializedName("dati2_proses")
	val dati2Proses: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("pkb_pokok")
	val pkbPokok: Int? = null,

	@field:SerializedName("no_daftar")
	val noDaftar: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null,

	@field:SerializedName("njkb")
	val njkb: Int? = null,

	@field:SerializedName("tanggal_jatuh_tempo_lama")
	val tanggalJatuhTempoLama: String? = null,

	@field:SerializedName("bbn1_denda")
	val bbn1Denda: Int? = null,

	@field:SerializedName("tanggal_jatuh_tempo_dpwkp")
	val tanggalJatuhTempoDpwkp: String? = null,

	@field:SerializedName("no_mesin")
	val noMesin: String? = null,

	@field:SerializedName("no_bpkb")
	val noBpkb: String? = null,

	@field:SerializedName("id_warna_tnkb")
	val idWarnaTnkb: Int? = null,

	@field:SerializedName("dpwkp")
	val dpwkp: Int? = null,

	@field:SerializedName("tanggal_jatuh_tempo")
	val tanggalJatuhTempo: String? = null,

	@field:SerializedName("pkb_denda")
	val pkbDenda: Int? = null,

	@field:SerializedName("swdkllj_pokok")
	val swdklljPokok: Int? = null,

	@field:SerializedName("tanggal_kwitansi")
	val tanggalKwitansi: String? = null,

	@field:SerializedName("tanggal_max_bayar_bbn")
	val tanggalMaxBayarBbn: String? = null,

	@field:SerializedName("no_rangka")
	val noRangka: String? = null,

	@field:SerializedName("bbn1_pokok")
	val bbn1Pokok: Int? = null,

	@field:SerializedName("id_lokasi_proses")
	val idLokasiProses: String? = null,

	@field:SerializedName("status_blokir")
	val statusBlokir: Boolean? = null,

	@field:SerializedName("subsidi")
	val subsidi: Boolean? = null,

	@field:SerializedName("no_polisi")
	val noPolisi: String? = null,

	@field:SerializedName("tahun_buat")
	val tahunBuat: Int? = null,

	@field:SerializedName("progresif_tarif")
	val progresifTarif: Long? = null,

	@field:SerializedName("no_telp")
	val noTelp: String? = null,

	@field:SerializedName("id_bahan_bakar")
	val idBahanBakar: Int? = null,

	@field:SerializedName("kode_pembayaran")
	val kodePembayaran: String? = null,

	@field:SerializedName("no_skpd")
	val noSkpd: String? = null,

	@field:SerializedName("id_lokasi")
	val idLokasi: String? = null,

	@field:SerializedName("id_model_kendaraan")
	val idModelKendaraan: String? = null,

	@field:SerializedName("jumlah_sumbu")
	val jumlahSumbu: Int? = null,

	@field:SerializedName("id_pendaftaran")
	val idPendaftaran: String? = null,

	@field:SerializedName("id_jenis_map")
	val idJenisMap: String? = null,

	@field:SerializedName("uid")
	val uid: String? = null,

	@field:SerializedName("id_status")
	val idStatus: String? = null,

	@field:SerializedName("tanggal_bayar")
	val tanggalBayar: String? = null,

	@field:SerializedName("tahun_ub")
	val tahunUb: Int? = null,

	@field:SerializedName("no_kk")
	val noKk: String? = null,

	@field:SerializedName("swdkllj_denda")
	val swdklljDenda: Int? = null,

	@field:SerializedName("id_merek_kendaraan")
	val idMerekKendaraan: String? = null,

	@field:SerializedName("dati2_induk")
	val dati2Induk: String? = null,

	@field:SerializedName("progresif")
	val progresif: Long? = null,

	@field:SerializedName("nama_pemilik")
	val namaPemilik: String? = null,

	@field:SerializedName("warna_kendaraan")
	val warnaKendaraan: String? = null,

	@field:SerializedName("id_jenis_kendaraan")
	val idJenisKendaraan: String? = null,

	@field:SerializedName("no_kohir")
	val noKohir: String? = null,

	@field:SerializedName("no_skum")
	val noSkum: String? = null,

	@field:SerializedName("no_polisi_lama")
	val noPolisiLama: String? = null,

	@field:SerializedName("kode_jenis")
	val kodeJenis: String? = null,

	@field:SerializedName("stnk")
	val stnk: Int? = null,

	@field:SerializedName("tujuan_mutasi")
	val tujuanMutasi: String? = null,

	@field:SerializedName("tanggal_max_bayar_swdkllj")
	val tanggalMaxBayarSwdkllj: String? = null,

	@field:SerializedName("nama_pemilik_lama")
	val namaPemilikLama: String? = null,

	@field:SerializedName("pkb_bunga")
	val pkbBunga: Int? = null,

	@field:SerializedName("alamat1")
	val alamat1: String? = null,

	@field:SerializedName("tahun_berlaku")
	val tahunBerlaku: Int? = null,

	@field:SerializedName("alamat2")
	val alamat2: String? = null,

	@field:SerializedName("cylinder")
	val cylinder: Int? = null,

	@field:SerializedName("tanggal_daftar")
	val tanggalDaftar: String? = null,

	@field:SerializedName("ket_dpwkp")
	val ketDpwkp: String? = null,

	@field:SerializedName("id_golongan_kendaraan")
	val idGolonganKendaraan: String? = null,

	@field:SerializedName("id_kelurahan")
	val idKelurahan: String? = null
)
