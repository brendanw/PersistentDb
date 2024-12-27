package com.basebeta.wasmdb

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import persistentdbdemo.composeapp.generated.resources.Res
import persistentdbdemo.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
   val appModel = remember { AppModel() }

   MaterialTheme {
      val showContent = appModel.showContentFlow.collectAsState().value

      Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
         Button(
            onClick = {
               appModel.updateBoolean(!showContent)
            }
         ) {
            Text("Click me!")
         }
         AnimatedVisibility(showContent) {
            val greeting = remember { Greeting().greet() }
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
               Image(painterResource(Res.drawable.compose_multiplatform), null)
               Text("Compose: $greeting")
            }
         }
      }
   }
}