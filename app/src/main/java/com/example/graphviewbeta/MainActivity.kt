package com.example.graphviewbeta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.commit


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val showButton = findViewById<Button>(R.id.showInput)
        val alpha = findViewById<EditText>(R.id.alpha).text
        val beta = findViewById<EditText>(R.id.beta).text
        val gamma = findViewById<EditText>(R.id.gamma).text
        val delta = findViewById<EditText>(R.id.delta).text
        fun graphres() {
            if (savedInstanceState == null) {
                val map = mapOf<String, Double>(
                    "alpha" to alpha.toString().toDouble(),
                    "beta" to beta.toString().toDouble(),
                    "gamma" to gamma.toString().toDouble(),
                    "delta" to delta.toString().toDouble()
                )
                supportFragmentManager.commit {
                    val bundle = Bundle()
                    bundle.putParcelable("arguments", GraphArgs(map))
                    setReorderingAllowed(true)
                    replace(R.id.graphfragment, GraphFragment.getNewInstance(bundle))
                }
            }
        }
        graphres()
        showButton.setOnClickListener {
            graphres()
        }
    }
}
