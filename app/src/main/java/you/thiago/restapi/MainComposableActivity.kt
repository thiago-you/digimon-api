package you.thiago.restapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import you.thiago.restapi.datasource.Digimon
import you.thiago.restapi.datasource.Service
import you.thiago.restapi.ui.theme.RestAPITheme

class MainComposableActivity : ComponentActivity() {

    private val list = mutableListOf<Digimon>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RestAPITheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    RequestButton()
                }
                LazyColumn(Modifier.fillMaxSize()) {
                    items(list.size) {
                        ListItem(list[it])
                    }
                }
            }
        }
    }

    private fun getDigimonData() {
        lifecycleScope.launch(Dispatchers.IO) {
            Service().getDigimons().also { digimons ->
                if (digimons.isNotEmpty()) {
                    lifecycleScope.launch(Dispatchers.Main) {
                        list.addAll(digimons)
                    }
                }
            }
        }
    }

    @Composable
    fun RequestButton() {
        val interactionSource = remember { MutableInteractionSource() }

        Button(onClick = { getDigimonData() }, interactionSource = interactionSource) {
            Text(text = "Listar Digimons")
        }
    }

    @Composable
    fun ListItem(data: Digimon, modifier: Modifier = Modifier) {
        Row(modifier.fillMaxWidth()) {
            Text(text = data.name)
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        RestAPITheme {
            RequestButton()
        }
    }
}


