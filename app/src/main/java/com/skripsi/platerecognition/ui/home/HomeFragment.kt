package com.skripsi.platerecognition.ui.home

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Shader
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.skripsi.platerecognition.R
import com.skripsi.platerecognition.databinding.FragmentHomeBinding
import com.skripsi.platerecognition.ui.account.AccountActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupview()

        binding.imgFotoProfil.setOnClickListener{
            activity?.let {
                startActivity(Intent(requireContext(), AccountActivity::class.java))
            }
        }
    }

    private fun setupview() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)
        auth = Firebase.auth
        val user = auth.currentUser

        if(user?.photoUrl == null) {
            binding.imgFotoProfil.setImageResource(R.drawable.default_profile)
        } else {
            Glide
                .with(requireContext())
                .load(user.photoUrl)
                .into(binding.imgFotoProfil)
        }

        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.memphis_bg)
        val drawable = BitmapDrawable(resources, bitmap)
        drawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)
        binding.homeLyt.background = drawable

        Log.d("Home Fragment", "User token: ${user?.getIdToken(false)?.result?.token.toString()}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}