package com.example.palette

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.palette.ui.theme.PaletteTheme

@Preview
@Composable
fun MySlider(
    text: String = "",
    numValue: Float = 0f,
    onValueChange: (Float) -> Unit = {},
    onValueChangeFinished: () -> Unit = {},
    leftClick: () -> Unit = {},
    rightClick: () -> Unit = {}
) {
    PaletteTheme {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row {
                Text(text = text, modifier = Modifier.padding(start = 10.dp))
                Spacer(modifier = Modifier.weight(1f))
                Text(text = numValue.toInt().toString(), modifier = Modifier.padding(end = 10.dp))
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                        .clickable { leftClick() }
                )
                Slider(
                    modifier = Modifier.weight(1f),
                    value = numValue,
                    valueRange = when (text) {
                        Hct.HUE.msg -> (0f..360f)
                        Hct.CHROMA.msg -> (0f..256f)
                        else -> (0f..100f)
                    },
                    steps = when (text) {
                        Hct.HUE.msg -> 359
                        Hct.CHROMA.msg -> 255
                        else -> 99
                    },
                    onValueChange = onValueChange,
                    onValueChangeFinished = onValueChangeFinished
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 5.dp)
                        .clickable { rightClick() }
                )

            }
        }
    }
}