package com.semeprojects.budgetbuddy.ui.screens

import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.semeprojects.budgetbuddy.domain.repository.UiState
import com.semeprojects.budgetbuddy.viewmodel.ChatViewModel
import io.noties.markwon.Markwon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    chatViewModel: ChatViewModel = viewModel(),
    navController: NavHostController,
    message: String?
) {

    val messages by chatViewModel.messages.collectAsState()
    val result by rememberSaveable { mutableStateOf("") }
    val uiState by chatViewModel.uiState.collectAsState()
    val markwon = Markwon.create(LocalContext.current)


    LaunchedEffect(key1 = message) {
        if (message?.isNotBlank() == true) {
            chatViewModel.sendPrompt(message)
        }
    }



    Scaffold(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                title = {
                    Text(text = "Financial Advice from Gemini", textAlign = TextAlign.Center)
                },
                modifier = Modifier.fillMaxWidth().shadow(elevation = 4.dp)
            )
        },){ paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues).padding(5.dp)
        ) {

            Column(modifier = Modifier.fillMaxSize().weight(0.85f)) {

                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(messages.size) { index ->
                        if(messages[index].isFromUser){
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                                    .align(Alignment.End),
                                shape = RoundedCornerShape(8.dp),
                                elevation = CardDefaults.cardElevation(4.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.secondary,
                                    contentColor = MaterialTheme.colorScheme.onSecondary
                                )
                            ) {

                                if (index == 0) {
                                    Text(
                                        text = "Requested...",
                                        modifier = Modifier.padding(8.dp)
                                    )
                                } else {
                                    println("messages")

//                                    val spannedText = markwon.toMarkdown(messages[index].content)
                                    val spannedText = markwon.toMarkdown(messages[index].content)

                                    val annotatedString = buildAnnotatedString {
                                        var currentIndex = 0

                                        // Handle **bold** patterns
                                        while (currentIndex < spannedText.length) {
                                            val startBold = spannedText.indexOf("**", currentIndex)
                                            if (startBold == -1) {
                                                append(spannedText.subSequence(currentIndex, spannedText.length))
                                                break
                                            }

                                            val endBold = spannedText.indexOf("**", startBold + 2)
                                            if (endBold == -1) {
                                                append(spannedText.subSequence(currentIndex, spannedText.length))
                                                break
                                            }

                                            append(spannedText.subSequence(currentIndex, startBold))
                                            withStyle(style = androidx.compose.ui.text.SpanStyle(fontWeight = FontWeight.Bold)) {
                                                append(spannedText.subSequence(startBold + 2, endBold))
                                            }
                                            currentIndex = endBold + 2
                                        }

                                        // Handle other spans (StyleSpan, etc.)
                                        for (span in spannedText.getSpans(0, spannedText.length, Any::class.java)) {
                                            val start = spannedText.getSpanStart(span)
                                            val end = spannedText.getSpanEnd(span)

                                            when (span) {
                                                is android.text.style.StyleSpan -> {
                                                    when (span.style) {
                                                        android.graphics.Typeface.BOLD -> {
                                                            withStyle(style = androidx.compose.ui.text.SpanStyle(fontWeight = FontWeight.Bold)) {
                                                                append(spannedText.subSequence(start, end))
                                                            }
                                                        }
                                                        android.graphics.Typeface.ITALIC -> {
                                                            withStyle(style = androidx.compose.ui.text.SpanStyle(fontStyle = androidx.compose.ui.text.font.FontStyle.Italic)) {
                                                                append(spannedText.subSequence(start, end))
                                                            }
                                                        }
                                                    }
                                                }
                                                // Handle other span types as needed
                                            }
                                        }
                                    }
                                    Text(text = annotatedString, modifier = Modifier.padding(8.dp))
                                }
                            }
                        } else {
                            val spannedText = markwon.toMarkdown(messages[index].content)
                            println(spannedText)
                            Text(text = spannedText.toString(), modifier = Modifier.padding(8.dp))
                        }
                    }
                }

                if (uiState is UiState.Loading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                } else if(uiState is UiState.Initial){
                    Text(text = result, modifier = Modifier.padding(8.dp))
                }

            }

            Row(modifier = Modifier.padding(16.dp).fillMaxWidth().weight(0.15f)) {
                var newMessage by remember { mutableStateOf("") }
                var newImage by remember { mutableStateOf<Bitmap?>(null) }

                IconButton (
                    onClick = {
                        chatViewModel.reset()
                        newMessage = ""
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "New Chat"
                    )
                }
                TextField(
                    value = newMessage,
                    onValueChange = { newMessage = it },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                )

                Button(
                    onClick = {
                        chatViewModel.sendPrompt(newMessage)
                        newMessage = ""
                    },
                    enabled = newMessage.isNotBlank() || newImage != null,
                            shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.onSecondary,
                        disabledContainerColor = MaterialTheme.colorScheme.secondary,
                        disabledContentColor = MaterialTheme.colorScheme.onSecondary
                    )
                ) {
                    Text(
                        text = "Send",
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSecondary,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 1,
                        softWrap = false,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }


        }
    }

}