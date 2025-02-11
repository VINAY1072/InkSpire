package com.example.inkspire.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import com.example.inkspire.domain.models.HomeAction
import com.example.inkspire.domain.models.Note
import com.example.inkspire.domain.util.CommonUtils.Companion.formatTimestamp
import com.example.inkspire.ui.theme.InkSpireTheme

@Composable
fun NotesCard(
    note: Note,
    onAction: (HomeAction) -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = InkSpireTheme.colors.card),
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(3.5f)
            .clickable {
                onAction(HomeAction.SetBottomSheetStatus(Pair(true, note)))
            }
    ) {
        Column(
            modifier = Modifier.padding(InkSpireTheme.dimens.space16)
        ) {
            Text(
                note.title,
                style = InkSpireTheme.typography.titleSmall.copy(fontWeight = Bold),
                color = InkSpireTheme.colors.secondary,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(InkSpireTheme.dimens.space4))
            Text(
                note.description.replace("\n", " "),
                style = InkSpireTheme.typography.bodyMedium,
                color = InkSpireTheme.colors.description,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                formatTimestamp(note.modifiedTime),
                style = InkSpireTheme.typography.bodySmall,
                color = InkSpireTheme.colors.time,
                modifier = Modifier.fillMaxWidth()

            )
        }
    }
}