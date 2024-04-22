package com.massimoregoli.roomdemo

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { Content() }
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
        Font("BebasNeue-Regular.ttf", assets)
    )
}

@Composable
fun buttonFont(): FontFamily {
    val assets = LocalContext.current.assets
    return FontFamily(
        Font("BebasNeue-Regular.ttf", assets)
    )
}

