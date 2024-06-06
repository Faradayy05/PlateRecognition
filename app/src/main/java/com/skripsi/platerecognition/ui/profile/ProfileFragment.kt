package com.skripsi.platerecognition.ui.profile

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
import com.skripsi.platerecognition.databinding.FragmentProfileBinding
import com.skripsi.platerecognition.ui.authentication.AuthenticationActivity
import com.skripsi.platerecognition.ui.history.HistoryActivity

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
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
        val profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()

        binding.imgCaretHistory.setOnClickListener {
            goToHistory()
        }

        binding.btnSignout.setOnClickListener {
            signOut()
        }
    }

    private fun setupView() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)
        auth = Firebase.auth
        val user = auth.currentUser

        if(user?.displayName == "" || user?.displayName == null) {
            binding.txtNamaProfil.text = user?.email?.split("@")?.get(0)
        } else {
            binding.txtNamaProfil.text = user.displayName
        }
        binding.txtEmailProfil.text = user?.email
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
        binding.profileLyt.background = drawable

        Log.d("ProfileFragment", "setupView: ${user?.photoUrl}")
        Log.d("ProfileFragment", "setupView: ${user?.email}")
    }

    private fun goToHistory() {
        activity?.let {
            startActivity(Intent(requireContext(), HistoryActivity::class.java))
        }
    }

    private fun signOut() {
        auth.signOut()
        googleSignInClient.signOut().addOnCompleteListener {
            activity?.let {
                startActivity(Intent(it, AuthenticationActivity::class.java))
                it.finish()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}