package com.example.inkspire.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import com.example.inkspire.domain.models.HomeAction
import com.example.inkspire.domain.models.Note
import com.example.inkspire.domain.util.CommonUtils.Companion.formatTimestamp
import com.example.inkspire.ui.theme.InkSpireTheme

@Composable
fun NotesCard(
    modifier: Modifier,
    contentModifier: Modifier,
    note: Note,
    onAction: (HomeAction) -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = InkSpireTheme.colors.card),
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(3.5f)
            .clickable {
                onAction(HomeAction.CreateNote(note.id ?: -1))
            }
    ) {
        Box {
            Column(
                modifier = Modifier.padding(InkSpireTheme.dimens.space16)
            ) {
                Text(
                    note.title,
                    style = InkSpireTheme.typography.titleSmall.copy(fontWeight = Bold),
                    color = InkSpireTheme.colors.secondary,
                    modifier = modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(InkSpireTheme.dimens.space4))
                Text(
                    note.description.replace("\n", " "),
                    style = InkSpireTheme.typography.bodyMedium,
                    color = InkSpireTheme.colors.description,
                    modifier = contentModifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    formatTimestamp(note.modifiedTime),
                    style = InkSpireTheme.typography.bodySmall,
                    color = InkSpireTheme.colors.time,
                    modifier = Modifier.fillMaxWidth()

                )
            }

            Icon(
                imageVector = Icons.Rounded.Delete,
                contentDescription = "delete",
                tint = InkSpireTheme.colors.secondary,
                modifier = Modifier
                    .padding(InkSpireTheme.dimens.space16)
                    .align(Alignment.BottomEnd)
                    .clickable {
                        onAction(HomeAction.DeleteNote(note))
                    }
            )
        }
    }
}