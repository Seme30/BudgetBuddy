package com.semeprojects.budgetbuddy.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun BTextField(
    modifier: Modifier = Modifier,
    inputState: MutableState<String> = mutableStateOf(""),
    leadingIcon: (@Composable() () -> Unit)? = null,
    trailingIcon: (@Composable() () -> Unit)? = null,
    label: String = "",
    placeholder: (@Composable() () -> Unit)? = null,
    onError: Boolean = false,
    supportingText: (@Composable() () -> Unit)? = null,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions =KeyboardOptions(keyboardType = KeyboardType.Text),
    readOnly: Boolean = false,
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(
                RoundedCornerShape(10.dp)
            ).background(
                color = MaterialTheme.colorScheme.surface
            )
    ){

        TextField(
            value = inputState.value,
            onValueChange = {
                inputState.value = it
            },
            modifier = Modifier.fillMaxWidth()
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
            keyboardActions = keyboardActions,
            readOnly = readOnly,
            interactionSource = interactionSource,
            isError = onError,
            supportingText = supportingText,
            textStyle = MaterialTheme.typography.bodyMedium,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            singleLine = true,
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation,
            placeholder = placeholder
        )
    }
}