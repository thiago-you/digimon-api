package you.thiago.restapi

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import you.thiago.restapi.datasource.Digimon
import you.thiago.restapi.datasource.Service
import you.thiago.restapi.ui.adapters.DigimonsAdapter
import you.thiago.restapi.ui.theme.RestAPITheme

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setupInterface()
    }

    private fun setupInterface() {
        findViewById<Button>(R.id.btn_request_list).setOnClickListener {
            getDigimonData()
        }
    }

    private fun getDigimonData() {
        lifecycleScope.launch(Dispatchers.IO) {
            Service().getDigimons().also { digimons ->
                if (digimons.isNotEmpty()) {
                    lifecycleScope.launch(Dispatchers.Main) {
                        findViewById<RecyclerView>(R.id.recyclerview).apply {
                            adapter = DigimonsAdapter(digimons)
                            adapter?.notifyDataSetChanged()
                        }
                    }
                }
            }
        }
    }
}


