package com.massimoregoli.roomdemo

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.massimoregoli.roomdemo.ui.theme.bigFontSize
import com.massimoregoli.roomdemo.ui.theme.buttonTextSize
import com.massimoregoli.roomdemo.ui.theme.fontSize
import com.massimoregoli.roomdemo.ui.theme.iconSize
import com.massimoregoli.roomdemo.ui.theme.lineHeight
import com.massimoregoli.roomdemo.ui.theme.searchFontSize
import com.massimoregoli.roomdemo.ui.theme.smallPadding

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ShowProverbPortrait(text: String, onclick: (filter: String) -> Unit) {
    var filter by rememberSaveable {
        mutableStateOf("")
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = "Proverbium",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(smallPadding)
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
                textAlign = TextAlign.Center,
                fontSize = bigFontSize,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Red,
                lineHeight = lineHeight,
                fontFamily = titleFont()

            )
        }
        Spacer(modifier = Modifier
            .height(40.dp)
            .clickable {
                keyboardController?.hide()
                focusManager.clearFocus()
            })
        Column(modifier = Modifier
            .fillMaxWidth()
            .clickable {
                keyboardController?.hide()
                focusManager.clearFocus()
            }) {
            Row(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    modifier = Modifier
                        .padding(smallPadding)
                        .wrapContentWidth(Alignment.CenterHorizontally)
                        .shadow(elevation = 10.dp, shape = RoundedCornerShape(22.dp)),
                    value = filter,
                    maxLines = 1,
                    shape = RoundedCornerShape(22.dp),
                    onValueChange = {
                        filter = it
                    },
                    placeholder = {
                        Text(
                            text = stringResource(id = (R.string.filterPlaceholder)),
                            textAlign = TextAlign.Center,
                            fontFamily = searchFont(),
                            color = Color.White,
                            fontSize = searchFontSize
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
                        cursorColor = Color.Red,
                        focusedTrailingIconColor = Color.White

                    ),

                    textStyle = TextStyle(
                        color = Color.White,
                        fontFamily = searchFont(), fontSize = searchFontSize
                    ),
                    trailingIcon = {
                        Icon(
                            Icons.Rounded.Search,
                            contentDescription = null,
                            modifier = Modifier
                                .size(iconSize, iconSize)
                                .clickable {
                                    onclick(filter)
                                    keyboardController?.hide()
                                })
                    }
                )

            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = if (text == "") {
                stringResource(id = (R.string.message))
            } else {
                text
            },
            modifier = Modifier
                .height(400.dp)
                .fillMaxWidth()
                .padding(smallPadding)
                .padding(top = 1.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                .padding(smallPadding * 2)
                .defaultMinSize(minHeight = 90.dp)
                .wrapContentHeight(align = Alignment.CenterVertically),
            textAlign = TextAlign.Center,
            fontSize = fontSize,
            fontStyle = FontStyle.Italic,
            lineHeight = lineHeight,
            fontFamily = textFont(),
            fontWeight = FontWeight(20),
            color = Color.White
        )
        fun casualPick() {
            keyboardController?.hide()
            focusManager.clearFocus()
            onclick(filter)
        }
        Spacer(modifier = Modifier.height(60.dp))
        Button(
            onClick = { casualPick() },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(
                    smallPadding
                )
                .height(70.dp)
                .width(200.dp)
        ) {
            Text(
                text = stringResource(id = R.string.generateBtn),
                fontSize = buttonTextSize,
                fontFamily = titleFont(),
                textAlign = TextAlign.Center
            )
        }
    }
}