package com.semeprojects.budgetbuddy.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BDropDown(
    inputState: MutableState<String>,
    placeholder: String,
    leadingIcon: (@Composable() () -> Unit)? = null,
    types: List<String>,
    onError: Boolean = false
){

    val isExpanded = remember{
        mutableStateOf(false)
    }

    ExposedDropdownMenuBox(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        expanded = isExpanded.value,
        onExpandedChange = {
            isExpanded.value = it
        }
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp)).background(
                    color = MaterialTheme.colorScheme.background
                )
        ){

            TextField(
                value = inputState.value,
                onValueChange = {
                    inputState.value = it
                },
                readOnly = true,
                isError = onError,
                textStyle = MaterialTheme.typography.bodyMedium,
                leadingIcon = leadingIcon,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded.value) },
                placeholder = { Text(text = placeholder, style = MaterialTheme.typography.bodyMedium)},
                modifier = Modifier.fillMaxWidth()
                    .menuAnchor()
                    .padding(horizontal = 10.dp).border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.onBackground,
                        shape = MaterialTheme.shapes.medium
                    ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),

            )
        }

        ExposedDropdownMenu(
            expanded = isExpanded.value,
            onDismissRequest = { isExpanded.value = false }
        ) {
            types.forEach{
                DropdownMenuItem(
                    text = { BText(text = it, size = 15.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        isExpanded.value = false
                        inputState.value = it
                    }
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewDropDown(){
    val selectedWallet = remember { mutableStateOf("") }
    BDropDown(
        inputState = selectedWallet,
        placeholder = "Expenses",
        types = listOf("MonoBank", "Revolut", "PayPal")
    )
}