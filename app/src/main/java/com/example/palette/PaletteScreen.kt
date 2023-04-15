package com.example.palette

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.palette.ui.theme.PaletteTheme
import kotlinx.coroutines.flow.update

@Composable
fun PaletteScreen(
    color: Color = MaterialTheme.colorScheme.primary,
    paletteViewModel: PaletteViewModel = viewModel()
) {
    val viewState by paletteViewModel.stateFlow.collectAsState()
    var selectedTabIndex by remember { mutableStateOf(0) }
    PaletteTheme {
        Surface(Modifier.fillMaxSize()) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Canvas(
                    modifier = Modifier
                        .size(350.dp)
                        .offset(y = 20.dp)
                        .clickable {
                            HCT.setHct(0.0, 0.0, 0.0)
                        }
                ) {
                    drawRoundRect(
                        color = viewState.color,
                        cornerRadius = CornerRadius(150F, 150F)
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                TabRow(selectedTabIndex = selectedTabIndex) {
                    Tab(selected = true,
                        modifier = Modifier.weight(1f),
                        onClick = { selectedTabIndex = 0 }) {
                        Text(
                            text = "HCT",
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                    Tab(selected = true,
                        modifier = Modifier.weight(1f),
                        onClick = { selectedTabIndex = 1 }) {
                        Text(
                            text = "Material",
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                if (selectedTabIndex == 0) {
                    MySlider(
                        text = Hct.HUE.msg,
                        numValue = viewState.hue,
                        onValueChange = { s ->
                            paletteViewModel.mutableStateFlow.update { it.copy(hue = s) }
                        },
                        onValueChangeFinished = {
                            with(viewState) {
                                val argb =
                                    HCT.setHct(hue.toDouble(), chroma.toDouble(), tone.toDouble())
                                paletteViewModel.mutableStateFlow.update {
                                    it.copy(
                                        color = Color(
                                            argb
                                        )
                                    )
                                }
                            }
                        },
                        leftClick = { paletteViewModel.change(Hct.HUE, Direction.LEFT) },
                        rightClick = { paletteViewModel.change(Hct.HUE, Direction.RIGHT) }
                    )
                    MySlider(
                        text = Hct.CHROMA.msg,
                        numValue = viewState.chroma,
                        onValueChange = { s ->
                            paletteViewModel.mutableStateFlow.update { it.copy(chroma = s) }
                        },
                        onValueChangeFinished = {
                            with(viewState) {
                                val argb =
                                    HCT.setHct(hue.toDouble(), chroma.toDouble(), tone.toDouble())
                                paletteViewModel.mutableStateFlow.update {
                                    it.copy(
                                        color = Color(
                                            argb
                                        )
                                    )
                                }
                            }
                        },
                        leftClick = { paletteViewModel.change(Hct.CHROMA, Direction.LEFT) },
                        rightClick = { paletteViewModel.change(Hct.CHROMA, Direction.RIGHT) }
                    )
                    MySlider(
                        text = Hct.TONE.msg,
                        numValue = viewState.tone,
                        onValueChange = { s ->
                            paletteViewModel.mutableStateFlow.update { it.copy(tone = s) }
                        },
                        onValueChangeFinished = {
                            with(viewState) {
                                val argb =
                                    HCT.setHct(hue.toDouble(), chroma.toDouble(), tone.toDouble())
                                paletteViewModel.mutableStateFlow.update {
                                    it.copy(
                                        color = Color(
                                            argb
                                        )
                                    )
                                }
                            }
                        },
                        leftClick = { paletteViewModel.change(Hct.TONE, Direction.LEFT) },
                        rightClick = { paletteViewModel.change(Hct.TONE, Direction.RIGHT) }
                    )
                } else {
                    LazyColumn(content = {
                        item {

                        }
                    }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PaletteScreenPreview() {
    PaletteScreen()
}