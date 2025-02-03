package com.example.inkspire

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.inkspire.ui.theme.InkSpireTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InkSpireTheme {
                HomeScreenComposable()
            }
        }
    }
}

@Composable
fun HomeScreenComposable() {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                shape = FloatingActionButtonDefaults.largeShape,
                containerColor = InkSpireTheme.colors.onPrimary
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "create",
                    tint = InkSpireTheme.colors.primary,
                    modifier = Modifier.size(InkSpireTheme.dimens.space46)
                )
            }
        }
    ) { padding->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

        }
    }
}