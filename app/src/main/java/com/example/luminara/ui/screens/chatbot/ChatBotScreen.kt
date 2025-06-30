package com.example.luminara.ui.screens.chatbot

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.luminara.R
import com.example.luminara.navigation.Screen
import com.example.luminara.ui.components.ChatMessageItem
import com.example.luminara.ui.components.ChatSuggestionCard
import com.example.luminara.ui.components.TypingIndicator
import com.example.luminara.ui.theme.BackgroundColor
import com.example.luminara.ui.theme.Primary
import com.example.luminara.utils.Dimensions
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatbotTopBar(
    navController: NavController
) {
    TopAppBar(
        modifier = Modifier
            .shadow(
                elevation = Dimensions.TopBarElevation
            ),
        title = {
            Text(
                modifier = Modifier.padding(start = Dimensions.TopBarHorizontalPadding),
                text = "Chatbot",
                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = BackgroundColor
        ),
    )
}

@Composable
fun ChatBotScreen(
    innerPadding: PaddingValues,
    viewModel: ChatbotViewModel = hiltViewModel()
) {
    var inputText by remember { mutableStateOf("") }
    val messages by viewModel.messages.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val showQuickActions by viewModel.showQuickActions.collectAsStateWithLifecycle()
    
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    // Auto scroll to bottom when new messages arrive
    LaunchedEffect(messages.size) {
        if (messages.isNotEmpty()) {
            coroutineScope.launch {
                listState.animateScrollToItem(messages.size)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                PaddingValues(
                    top = innerPadding.calculateTopPadding(),
                    bottom = innerPadding.calculateBottomPadding()
                )
            )
            .background(Color.White)
    ) {
        // Chat Messages
        LazyColumn(
            state = listState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(top = 12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Greeting Section (only shown at the start)
            if (messages.size == 1) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp, vertical = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Hi Milano Cherry!",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = Primary
                            )
                        )
                        Text(
                            text = "How may I assist you on your spiritual journey today? 😊",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontStyle = FontStyle.Italic,
                                color = Color.Gray
                            ),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            // Chat Messages
            items(messages) { message ->
                ChatMessageItem(message = message)
            }

            // Quick Actions (only shown initially)
            if (showQuickActions && messages.size == 1) {
                item {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
                    ) {
                        Text(
                            text = "Atau coba pertanyaan populer ini:",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Medium,
                                color = Color.Gray
                            ),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        
                        viewModel.quickActions.forEach { action ->
                            ChatSuggestionCard(
                                title = action.text,
                                subtitle = action.message,
                                onClick = { viewModel.sendQuickAction(action.message) }
                            )
                        }
                    }
                }
            }

            // Typing indicator
            if (isLoading) {
                item {
                    TypingIndicator()
                }
            }

            // Add spacing at the bottom
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        // Bottom Input Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Primary,
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                )
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Input Field + Image
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.White, shape = RoundedCornerShape(50))
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        BasicTextField(
                            value = inputText,
                            onValueChange = { inputText = it },
                            singleLine = true,
                            textStyle = LocalTextStyle.current.copy(color = Color.Black),
                            enabled = !isLoading,
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 4.dp)
                        ) { innerTextField ->
                            Box {
                                if (inputText.isEmpty()) {
                                    Text(
                                        text = "Ketik pesan Anda di sini...",
                                        color = Color.Gray,
                                        fontStyle = FontStyle.Italic
                                    )
                                }
                                innerTextField()
                            }
                        }

                        Icon(
                            painter = painterResource(id = R.drawable.image),
                            contentDescription = "Image",
                            tint = Color(0xFF814C1A),
                            modifier = Modifier
                                .size(20.dp)
                                .clickable { /* TODO: Implement image selection */ }
                        )
                    }
                }

                // Mic Button
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF814C1A))
                        .clickable { /* TODO: Implement voice input */ },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.mic),
                        contentDescription = "Mic",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }

                // Send Button
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF814C1A))
                        .clickable(enabled = inputText.trim().isNotEmpty() && !isLoading) {
                            if (inputText.trim().isNotEmpty()) {
                                viewModel.sendMessage(inputText)
                                inputText = ""
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.send),
                        contentDescription = "Send",
                        tint = if (inputText.trim().isNotEmpty() && !isLoading) Color.White else Color.Gray,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}
