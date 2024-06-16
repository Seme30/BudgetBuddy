package com.semeprojects.budgetbuddy.ui.components

import android.app.DatePickerDialog
import android.os.Build
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.time.Instant

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DDatePicker(
    eventDate: (Instant) -> Unit
){

    val context = LocalContext.current

    val calender = Calendar.getInstance()
    val currentYear = remember{ mutableStateOf(calender.get(Calendar.YEAR)) }
    val currentMonth = remember{ mutableStateOf(calender.get(Calendar.MONTH)) }
    val currentDay = remember{ mutableStateOf(calender.get(Calendar.DAY_OF_MONTH)) }
    println("Calendar: $currentYear $currentMonth $currentDay")
    println("Calendar: ${calender.time}")
    val selectedDate = remember { mutableStateOf(calender.time.toFormattedDate()) }
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed = interactionSource.collectIsPressedAsState()

    val datePickerDialog = DatePickerDialog(
        context,
        { datePicker: DatePicker, year: Int, month: Int, day: Int ->
            val newDate = Calendar.getInstance()
            newDate.set(year, month, day)
            selectedDate.value = newDate.time.toFormattedDate()
            currentYear.value = year
            currentMonth.value = month
            currentDay.value = day
            eventDate(newDate.time.toInstant())
        }, currentYear.value, currentMonth.value, currentDay.value
    )

    TextField(
        value = selectedDate.value,
        modifier = Modifier.fillMaxWidth()
            .padding(15.dp).border(
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
        textStyle = MaterialTheme.typography.bodyMedium,
        onValueChange = {},
        readOnly = true,
        interactionSource = interactionSource,
        trailingIcon = { Icon(imageVector = Icons.Default.DateRange, contentDescription = "")}
    )

    if(isPressed.value){
        datePickerDialog.show()
    }

}

fun Date.toFormattedDate(): String{
    val simpleDateFormat = SimpleDateFormat("EEE dd MMMM, yyyy", Locale.getDefault())
    return simpleDateFormat.format(this)
}
