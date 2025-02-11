package com.example.inkspire.presentation.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.inkspire.R
import com.example.inkspire.domain.models.AddNotesAction
import com.example.inkspire.domain.models.CreateUiState
import com.example.inkspire.presentation.components.TransparentHintTextField
import com.example.inkspire.ui.theme.InkSpireTheme

@Composable
fun CreateNoteScreen(
    navController: NavController,
    uiState: CreateUiState,
    onAction: (AddNotesAction) -> Unit
) {
    LaunchedEffect(uiState.shouldGoBack) {
        if (uiState.shouldGoBack) {
            navController.popBackStack()
        }
    }

    Scaffold(
        containerColor = InkSpireTheme.colors.onPrimary,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onAction(AddNotesAction.AddNote)
                },
                shape = FloatingActionButtonDefaults.largeShape,
                containerColor = InkSpireTheme.colors.primary
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.save),
                    contentDescription = "save",
                    tint = InkSpireTheme.colors.white,
                    modifier = Modifier.size(InkSpireTheme.dimens.space40)
                )
            }
        }
    ) { padding->
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(padding)
                .padding(InkSpireTheme.dimens.space16)
        ) {
            item {
                TransparentHintTextField(
                    text = uiState.title,
                    hint = "title",
                    singleLine = true,
                    onValueChange = {
                        onAction(AddNotesAction.Title(it))
                    },
                    textStyle = InkSpireTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(InkSpireTheme.dimens.space16))
            }

            item {
                TransparentHintTextField(
                    text = uiState.description,
                    hint = "write something",
                    singleLine = false,
                    onValueChange = {
                        onAction(AddNotesAction.Description(it))
                    },
                    textStyle = InkSpireTheme.typography.bodyLarge,
                    modifier = Modifier.fillMaxHeight()
                )
            }
        }
    }
}