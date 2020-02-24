package br.com.douglasmotta.viewbinding

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import br.com.douglasmotta.viewbinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val names = mutableListOf<String>()
    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setMainAdapter()

        binding.buttonDoAction.setOnClickListener {
            addName(binding.inputName.text.toString())
            hideKeyboard()
        }

        binding.buttonClearList.setOnClickListener {
            clearList()
        }

        binding.inputName.setOnEditorActionListener { view, _, _ ->
            addName(view.text.toString())
            hideKeyboard()
            true
        }
    }

    private fun setMainAdapter() {
        mainAdapter = MainAdapter(names)
        with(binding.recyclerNames) {
            setHasFixedSize(true)
            adapter = mainAdapter
        }
    }

    private fun addName(name: String) {
        binding.inputName.setText("")
        mainAdapter.addName(name)
    }

    private fun clearList() {
        mainAdapter.clearNames()
    }

    private fun hideKeyboard() {
        val imm: InputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view: View? = currentFocus
        if (view == null) {
            view = View(this)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
