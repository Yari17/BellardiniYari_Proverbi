package com.massimoregoli.roomdemo

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import com.massimoregoli.roomdemo.db.DbProverb
import com.massimoregoli.roomdemo.db.Repository
import com.massimoregoli.roomdemo.ui.theme.RoomDemoTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun Content(){
    var proverb by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current
    val db = DbProverb.getInstance(context)
    val repository = Repository(db.proverbDao())
    var configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_PORTRAIT -> {

            RoomDemoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(26, 22, 37) // MaterialTheme.colorScheme.background
                ) {
                    ShowProverbPortrait(proverb) {
                        CoroutineScope(Dispatchers.IO).launch {
                            val p = repository.readFilteredNext("%$it%", 0)
                            proverb =
                                p?.text
                                    ?: "C'è stato un problema, perfavore riavvia l'applicazione"
                        }
                    }
                }
            }
        }

        Configuration.ORIENTATION_LANDSCAPE -> {
            RoomDemoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(26, 22, 37) // MaterialTheme.colorScheme.background
                ) {
                    ShowProverbLandscape(proverb) {
                        CoroutineScope(Dispatchers.IO).launch {
                            val p = repository.readFilteredNext("%$it%", 0)
                            proverb =
                                p?.text
                                    ?: "C'è stato un problema, perfavore riavvia l'applicazione"
                        }
                    }
                }
            }
        }
    }

}