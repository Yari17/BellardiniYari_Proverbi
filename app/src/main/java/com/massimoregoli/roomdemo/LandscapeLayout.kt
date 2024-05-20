package com.massimoregoli.roomdemo

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.massimoregoli.roomdemo.ui.theme.bigFontSizeLand
import com.massimoregoli.roomdemo.ui.theme.buttonTextSizeLand
import com.massimoregoli.roomdemo.ui.theme.fontSizeLand
import com.massimoregoli.roomdemo.ui.theme.iconSize
import com.massimoregoli.roomdemo.ui.theme.lineHeight
import com.massimoregoli.roomdemo.ui.theme.searchFontSizeLand
import com.massimoregoli.roomdemo.ui.theme.smallPadding

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ShowProverbLandscape(text: String,filter: String,onChangeName: (String)->Unit, onclick: (filter: String) -> Unit) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column {
            Text(
                text = "Proverbium",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    .clickable {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    },
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.Black,
                        offset = Offset(11f, 11f),
                        blurRadius = 2f
                    )
                ),
                textAlign = TextAlign.Start,
                fontSize = bigFontSizeLand,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Cyan,
                lineHeight = lineHeight,
                fontFamily = titleFont()

            )
        }
        Spacer(modifier = Modifier
            .height(20.dp)
            .clickable {
                keyboardController?.hide()
                focusManager.clearFocus()
            })
        Row {
            Column(modifier = Modifier
                .width(350.dp)
                .clickable {
                    keyboardController?.hide()
                    focusManager.clearFocus()
                }){
                Row() {
                    OutlinedTextField(
                        modifier = Modifier
                            .width(340.dp)
                            .padding(smallPadding)
                            .wrapContentWidth(Alignment.CenterHorizontally)
                            .shadow(elevation = 10.dp, shape = RoundedCornerShape(22.dp)),
                        value = filter,
                        maxLines = 1,
                        shape = RoundedCornerShape(22.dp),
                        onValueChange = {
                            onChangeName(it)
                        },
                        placeholder = {
                            Text(
                                text = stringResource(id = (R.string.filterPlaceholder)),
                                textAlign = TextAlign.Center,
                                fontFamily = searchFont(),
                                color = Color.White,
                                fontSize = searchFontSizeLand
                            )
                        },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                        keyboardActions = KeyboardActions(onSearch = {
                            keyboardController?.hide()
                            focusManager.clearFocus()
                            onclick(filter)
                        }),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedTextColor = Color.Black,
                            focusedBorderColor = Color(101, 80, 164),
                            cursorColor = Color.Cyan,
                            focusedTrailingIconColor = Color.White

                        ),

                        textStyle = TextStyle(
                            color = Color.White,
                            fontFamily = searchFont(), fontSize = searchFontSizeLand
                        ),
                        trailingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.loupe),
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier
                                    .size(iconSize, iconSize)
                                    .clickable {
                                        onclick(filter)
                                        keyboardController?.hide()
                                    })
                        }
                    )

                }
                Spacer(modifier = Modifier.height(76.dp))
                Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    fun casualPick() {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                        onclick(filter)
                    }
                    Button(
                        onClick = { casualPick() },
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .padding(
                                smallPadding
                            )
                            .height(80.dp)
                            .width(180.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.generateBtn),
                            fontSize = buttonTextSizeLand,
                            fontFamily = buttonFont(),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            Column {
                Text(
                    text = if (text == "") {
                        stringResource(id = (R.string.message))
                    } else {
                        text
                    },
                    modifier = Modifier
                        .height(250.dp)
                        .width(500.dp)
                        .padding(10.dp)
                        .padding(top = 1.dp)
                        .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                        .padding(smallPadding * 2)
                        .defaultMinSize(minHeight = 90.dp)
                        .wrapContentHeight(align = Alignment.CenterVertically),
                    textAlign = TextAlign.Center,
                    fontSize = fontSizeLand,
                    fontStyle = FontStyle.Italic,
                    lineHeight = lineHeight,
                    fontFamily = textFont(),
                    fontWeight = FontWeight(20),
                    color = Color.White
                )
            }
        }
    }
}