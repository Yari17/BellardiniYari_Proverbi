package com.massimoregoli.roomdemo

import android.content.res.Configuration
import android.icu.text.ListFormatter.Width
import android.os.Bundle
import android.provider.CalendarContract.Colors

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.massimoregoli.roomdemo.ui.theme.RoomDemoTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.massimoregoli.roomdemo.db.DbProverb
import com.massimoregoli.roomdemo.db.Repository
import com.massimoregoli.roomdemo.ui.theme.bigFontSize
import com.massimoregoli.roomdemo.ui.theme.bigFontSizeLand
import com.massimoregoli.roomdemo.ui.theme.buttonTextSize
import com.massimoregoli.roomdemo.ui.theme.buttonTextSizeLand
import com.massimoregoli.roomdemo.ui.theme.fontSize
import com.massimoregoli.roomdemo.ui.theme.fontSizeLand
import com.massimoregoli.roomdemo.ui.theme.iconSize
import com.massimoregoli.roomdemo.ui.theme.lineHeight
import com.massimoregoli.roomdemo.ui.theme.searchFontSize
import com.massimoregoli.roomdemo.ui.theme.searchFontSizeLand
import com.massimoregoli.roomdemo.ui.theme.smallPadding

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var configuration = LocalConfiguration.current
            when (configuration.orientation) {
                Configuration.ORIENTATION_PORTRAIT -> {
                    var proverb by rememberSaveable { mutableStateOf("") }

                    val context = LocalContext.current
                    val db = DbProverb.getInstance(context)
                    val repository = Repository(db.proverbDao())
                    RoomDemoTheme {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = Color(26, 22, 37) // MaterialTheme.colorScheme.background
                        ) {
                            ShowProverbPortrait(proverb) {
                                CoroutineScope(Dispatchers.IO).launch {
                                    val p = repository.readFilteredNext("%$it%", 0)
                                    proverb =
                                        p?.text ?: "C'è stato un problema, perfavore riavvia l'applicazione"
                                }
                            }
                        }
                    }
                }

                Configuration.ORIENTATION_LANDSCAPE -> {
                    var proverb by rememberSaveable { mutableStateOf("") }

                    val context = LocalContext.current
                    val db = DbProverb.getInstance(context)
                    val repository = Repository(db.proverbDao())
                    RoomDemoTheme {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = Color(26, 22, 37) // MaterialTheme.colorScheme.background
                        ) {
                            ShowProverbLandscape(proverb) {
                                CoroutineScope(Dispatchers.IO).launch {
                                    val p = repository.readFilteredNext("%$it%", 0)
                                    proverb =
                                        p?.text ?: "C'è stato un problema, perfavore riavvia l'applicazione"
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun titleFont(): FontFamily {
    val assets = LocalContext.current.assets
    return FontFamily(
        Font("Jersey10-Regular.ttf", assets)
    )
}

@Composable
fun searchFont(): FontFamily {
    val assets = LocalContext.current.assets
    return FontFamily(
        Font("BebasNeue-Regular.ttf", assets)
    )
}

@Composable
fun textFont(): FontFamily {
    val assets = LocalContext.current.assets
    return FontFamily(
        Font("Starjedi.ttf", assets)
    )
}

