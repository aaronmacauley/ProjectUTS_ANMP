package com.example.waroengujang_sembarangwes.view

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Shader
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.waroengujang_sembarangwes.R
import com.example.waroengujang_sembarangwes.databinding.FragmentProfileBinding
import com.example.waroengujang_sembarangwes.viewmodel.WaiterViewModel
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
class ProfileFragment : Fragment() {

    private lateinit var waiterViewModel: WaiterViewModel
    private lateinit var binding:FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return inflater.inflate(R.layout.fragment_profile, container, false)
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageView = view.findViewById<ImageView>(R.id.imageViewProfile)
        val txtNamaProfile = view.findViewById<TextView>(R.id.txtNamaProfile)
        val txtWorkSince = view.findViewById<TextView>(R.id.txtWorkSince)
        val btnSignOut = view.findViewById<Button>(R.id.btnSignOut)

        waiterViewModel = ViewModelProvider(this).get(WaiterViewModel::class.java)
        waiterViewModel.orderLD.observe(viewLifecycleOwner) { waiters ->
            waiters?.let {
                val firstOrder = it.first()
                txtNamaProfile.text = firstOrder.name
                txtWorkSince.text = "Work since: " + firstOrder.work_since

                val transformation: Transformation = object : Transformation {
                    override fun transform(source: Bitmap): Bitmap {
                        val size = Math.min(source.width, source.height)
                        val x = (source.width - size) / 2
                        val y = (source.height - size) / 2
                        val squaredBitmap = Bitmap.createBitmap(source, x, y, size, size)
                        if (squaredBitmap != source) {
                            source.recycle()
                        }
                        val bitmap = Bitmap.createBitmap(size, size, source.config)
                        val canvas = Canvas(bitmap)
                        val paint = Paint()
                        val shader = BitmapShader(squaredBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
                        paint.shader = shader
                        paint.isAntiAlias = true
                        val r = size / 2f
                        canvas.drawCircle(r, r, r, paint)
                        squaredBitmap.recycle()
                        return bitmap
                    }

                    override fun key(): String {
                        return "circle"
                    }
                }

                Picasso.get().load(firstOrder.img_url).transform(transformation).into(imageView)
            }
        }
        waiterViewModel.refresh()
         waiterViewModel.refresh()

        btnSignOut.setOnClickListener {
            val intent = Intent(requireActivity(), LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
            Toast.makeText(requireActivity(), "Anda Telah Keluar", Toast.LENGTH_SHORT).show()

        }
    }
}
