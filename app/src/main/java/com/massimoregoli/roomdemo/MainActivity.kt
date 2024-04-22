package com.massimoregoli.roomdemo

import android.annotation.SuppressLint
import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { Content() }
    }
}

class FilterModelView:ViewModel(){
    var filter= MutableStateFlow("")
    fun setFilter(string: String){
        filter= MutableStateFlow(string)
    }
    @SuppressLint("StateFlowValueCalledInComposition")
    @Composable
    fun getFilter():String{
        return filter.value
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

