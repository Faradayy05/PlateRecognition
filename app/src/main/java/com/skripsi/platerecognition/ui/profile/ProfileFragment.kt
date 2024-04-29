package com.skripsi.platerecognition.ui.profile

import android.content.Intent
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
import com.skripsi.platerecognition.ui.account.AccountActivity
import com.skripsi.platerecognition.ui.authentication.AuthenticationActivity

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

        binding.imgCaretAccount.setOnClickListener {
            startActivity(Intent(requireContext(), AccountActivity::class.java))
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

        binding.txtNamaProfil.text = user?.displayName
        binding.txtEmailProfil.text = user?.email
        Glide
            .with(requireContext())
            .load(user?.photoUrl)
            .into(binding.imgFotoProfil)
        Log.d("ProfileFragment", "setupView: ${user?.photoUrl}")
        Log.d("ProfileFragment", "setupView: ${user?.isEmailVerified}")
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