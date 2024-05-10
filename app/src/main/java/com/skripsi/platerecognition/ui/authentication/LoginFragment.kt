package com.skripsi.platerecognition.ui.authentication

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Shader
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.skripsi.platerecognition.MainActivity
import com.skripsi.platerecognition.R
import com.skripsi.platerecognition.data.local.entity.User
import com.skripsi.platerecognition.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupFirebase()

        binding.toolbar.setOnClickListener {
            val mFragmentManager = parentFragmentManager
            mFragmentManager.popBackStack()
        }

        binding.btnLogin.setOnClickListener {
            val edEmail = binding.edEmail.text.toString().trim()
            val edPassword = binding.edPassword.text.toString().trim()
            val userLogin = User(email = edEmail, password = edPassword)
            val isValid = validateForm(edEmail, edPassword)

            if (isValid) {
                auth.signInWithEmailAndPassword(userLogin.email, userLogin.password)
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            val user = auth.currentUser
                            updateUI(user)
                            Toast.makeText(requireContext(), "Authentication success", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(requireContext(), "${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.memphis_bg)
        val drawable = BitmapDrawable(resources, bitmap)
        drawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)
        binding.loginLyt.background = drawable
    }

    private fun validateForm(email: String, password: String): Boolean {
        var isValid = true

        when {
            email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                binding.edEmail.error = if (email.isEmpty()) "Email tidak boleh kosong" else "Email tidak valid"
                isValid = false
            }

            password.isEmpty() -> {
                binding.edPassword.error = "Password tidak boleh kosong"
                isValid = false
            }
        }

        return isValid
    }

    private fun setupFirebase() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)
        auth = Firebase.auth
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null){
            activity?.let {
                startActivity(Intent(it, MainActivity::class.java))
                it.finish()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }
}