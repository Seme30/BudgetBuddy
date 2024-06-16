package com.semeprojects.budgetbuddy.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun BText(
    text: String,
    size: TextUnit,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.bodyLarge,
    textAlign: TextAlign = TextAlign.Center,
    maxLines: Int = 1
){


    Text(
        text = text,
        maxLines = maxLines,
        modifier = modifier,
        style = style,
        fontSize = size,
        letterSpacing = 0.1.sp,
        textAlign = textAlign
    )

}


