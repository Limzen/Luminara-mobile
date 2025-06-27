package com.example.luminara.ui.screens.chatbot
import com.example.luminara.ui.components.ChatSuggestionCard
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import com.example.luminara.R
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
import androidx.navigation.NavController
import com.example.luminara.navigation.Screen
import com.example.luminara.ui.theme.BackgroundColor
import com.example.luminara.ui.theme.Primary
import com.example.luminara.utils.Dimensions

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
    innerPadding: PaddingValues
) {
    val backgroundColor = Color(0xFF864A11)
    val cardBorderColor = Color(0xFFAD7C52)
    val roundedShape = RoundedCornerShape(20.dp)
    var inputText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                PaddingValues(
                    top = innerPadding.calculateTopPadding(),
                    bottom = innerPadding.calculateBottomPadding()
                )
            )
            .padding(top = 12.dp)
            .background(Color.White)
    ) {

        // Greeting Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
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

        Spacer(modifier = Modifier.height(36.dp))

        // Chat Suggestions
        // Chat Suggestions
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {
            ChatSuggestionCard(
                title = "Brainstorm names",
                subtitle = "for my fantasy football team with a frog theme",
                onClick = { /* TODO */ }
            )
            ChatSuggestionCard(
                title = "Suggest calming rituals",
                subtitle = "for morning mindfulness routines",
                onClick = { /* TODO */ }
            )
            ChatSuggestionCard(
                title = "Help me reflect",
                subtitle = "on my current emotional state",
                onClick = { /* TODO */ }
            )
            ChatSuggestionCard(
                title = "Spiritual quotes",
                subtitle = "to inspire my day",
                onClick = { /* TODO */ }
            )
        }

        Spacer(modifier = Modifier.weight(1f))
        //bottom bar
        // Bottom Input Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Primary, shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
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
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 4.dp)
                        ) { innerTextField ->
                            Box {
                                if (inputText.isEmpty()) {
                                    Text(
                                        text = "Ask me anything...",
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
                                .clickable { /* TODO */ }
                        )
                    }
                }

                // Mic Button
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF814C1A))
                        .clickable { /* TODO */ },
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
                        .clickable { /* TODO */ },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.send),
                        contentDescription = "Send",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }




    }
}
