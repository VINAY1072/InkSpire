package com.example.inkspire.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.inkspire.domain.models.HomeAction
import com.example.inkspire.domain.models.HomeUiState
import com.example.inkspire.presentation.components.MenuDialog
import com.example.inkspire.presentation.components.NotesCard
import com.example.inkspire.ui.theme.InkSpireTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenComposable(
    uiState: HomeUiState,
    onAction: (HomeAction) -> Unit
) {
    Scaffold(
        containerColor = InkSpireTheme.colors.onPrimary,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(containerColor = InkSpireTheme.colors.onPrimary),
                modifier = Modifier.statusBarsPadding(),
                title = {
                    Text(
                        text = "InkSpire",
                        style = InkSpireTheme.typography.displaySmall.copy(fontWeight = FontWeight.SemiBold),
                        maxLines = 1,
                        color = InkSpireTheme.colors.secondary
                    )
                },
                actions = {
                    Icon(
                        imageVector = Icons.Rounded.Menu,
                        contentDescription = "settings",
                        tint = InkSpireTheme.colors.secondary,
                        modifier = Modifier
                            .padding(end = InkSpireTheme.dimens.space16)
                            .clickable {
                                onAction(HomeAction.SetMenuDialogStatus(true))
                            }
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onAction(HomeAction.CreateNote(-1))
                },
                shape = FloatingActionButtonDefaults.largeShape,
                containerColor = InkSpireTheme.colors.primary
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "create",
                    tint = InkSpireTheme.colors.white,
                    modifier = Modifier.size(InkSpireTheme.dimens.space46)
                )
            }
        }
    ) { padding->
        Box {

            uiState.notes?.let { notes ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(horizontal = InkSpireTheme.dimens.space16),
                    verticalArrangement = Arrangement.spacedBy(InkSpireTheme.dimens.space16)
                ) {
                    items(notes) { note ->
                        NotesCard(
                            note = note,
                            onAction = onAction
                        )
                    }
                }
            }

            if (uiState.showMenu) {
                MenuDialog(
                    onDismiss = { onAction(HomeAction.SetMenuDialogStatus(false)) },
                    sortOrder = uiState.sortOrder,
                    onSave = { order -> onAction(HomeAction.SortData(order)) }
                )
            }

        }
    }
}