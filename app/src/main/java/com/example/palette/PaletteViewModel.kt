package com.example.palette

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

sealed class Hct(val msg: String) {
    object HUE : Hct("Hue")
    object CHROMA : Hct("Chroma")
    object TONE : Hct("Tone")
}

sealed class Direction {
    object LEFT : Direction()
    object RIGHT : Direction()
}

sealed class MaterialColor(val name:String){
    object PRIMARY:MaterialColor("primary")
    object ON_PRIMARY:MaterialColor("onPrimary")
    object PRIMARY_CONTAINER:MaterialColor("primaryContainer")
}

class PaletteViewModel : ViewModel() {
    data class hctValue(
        val color: Color = Color(0, 0, 0, 0),
        val hue: Float = 0f,
        val chroma: Float = 0f,
        val tone: Float = 0f
    )

    val mutableStateFlow = MutableStateFlow(hctValue())
    val stateFlow = mutableStateFlow.asStateFlow()

    fun hueChange(hue: Float) {
        mutableStateFlow.update { it.copy(hue = hue) }
    }

    fun change(hct: Hct, direction: Direction) {
        when (direction) {
            is Direction.LEFT -> {
                with(stateFlow.value) {
                    when (hct) {
                        is Hct.HUE -> {
                            if (hue > 0)
                                mutableStateFlow.update { it.copy(hue = it.hue - 1) }
                        }

                        is Hct.CHROMA -> {
                            if (chroma > 0)
                                mutableStateFlow.update { it.copy(chroma = it.chroma - 1) }
                        }

                        is Hct.TONE -> {
                            if (tone > 0)
                                mutableStateFlow.update { it.copy(tone = it.tone - 1) }
                        }
                    }
                }
            }

            is Direction.RIGHT -> {
                with(stateFlow.value) {
                    when (hct) {
                        is Hct.HUE -> {
                            if (hue < 360)
                                mutableStateFlow.update { it.copy(hue = it.hue + 1) }
                        }

                        is Hct.CHROMA -> {
                            mutableStateFlow.update { it.copy(chroma = it.chroma + 1) }
                        }

                        is Hct.TONE -> {
                            if (tone < 100)
                                mutableStateFlow.update { it.copy(tone = it.tone + 1) }
                        }
                    }
                }
            }
        }
        with(stateFlow.value) {
            val argb =
                HCT.setHct(hue.toDouble(), chroma.toDouble(), tone.toDouble())
            mutableStateFlow.update {
                it.copy(
                    color = Color(
                        argb
                    )
                )
            }
        }
    }
}