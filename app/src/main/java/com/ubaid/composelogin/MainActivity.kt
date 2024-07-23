package com.ubaid.composelogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ubaid.composelogin.ui.theme.ComposeLoginTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeLoginTheme {
                App()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun App() {
    val sheetState = rememberModalBottomSheetState()
    var isCreateSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }
    var isLoginSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }
    val scaffoldState = rememberBottomSheetScaffoldState(
        SheetState(skipHiddenState = false, skipPartiallyExpanded = false)
    )
    val scaffoldState2 = rememberBottomSheetScaffoldState(
        SheetState(skipHiddenState = false, skipPartiallyExpanded = false)
    )

    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.black))
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.picture), contentDescription = null,
                modifier = Modifier.size(300.dp)
            )

            Text(
                text = "Welcome",
                style = MaterialTheme.typography.headlineLarge,
                color = colorResource(id = R.color.red)
            )

            Text(
                text = "Welcome to lorem ipsum to lorem ipsum lorem ipsum\n" +
                        "Welcome to lorem ipsum to lorem ipsum to lorem ipsum\n" +
                        "Welcome to lorem ipsum to lorem ipsum to lorem ipsum\n" +
                        "Welcome to lorem ipsum to lorem ipsum\n ",
                color = colorResource(id = R.color.white),
                modifier = Modifier.padding(vertical = 30.dp, horizontal = 20.dp),
            )

            Button(
                onClick = { scope.launch { scaffoldState.bottomSheetState.expand() } },
                modifier = Modifier.padding(25.dp),
                contentPadding = PaddingValues(vertical = 16.dp, horizontal = 80.dp),
                colors = ButtonDefaults.buttonColors(
                    colorResource(id = R.color.yellow),
                )
            ) {
                Text(text = "Create Account", color = colorResource(id = R.color.black))
            }
            Button(
                onClick = { scope.launch { scaffoldState2.bottomSheetState.expand() } },
                modifier = Modifier.padding(2.dp),
                border = BorderStroke(2.dp, colorResource(id = R.color.yellow)),
                contentPadding = PaddingValues(vertical = 16.dp, horizontal = 110.dp),
                colors = ButtonDefaults.buttonColors(
                    colorResource(id = R.color.black),
                )
            ) {
                Text(text = "Login", color = colorResource(id = R.color.yellow))
            }
        }
        Text(
            text = "All Right Reserved @2024", color = colorResource(id = R.color.yellow),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(vertical = 30.dp)
        )

    }
    BottomSheetScaffold(scaffoldState = scaffoldState2, sheetContent = {
        Login (scope, scaffoldState2)
    }, sheetPeekHeight = 0.dp) {

    }
    BottomSheetScaffold(scaffoldState = scaffoldState, sheetContent = {
        CreateAccount (scope, scaffoldState)
    }, sheetPeekHeight = 0.dp) {

    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAccount(scope:CoroutineScope, state: BottomSheetScaffoldState) {
    var email by remember { mutableStateOf("") }
    var course by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPass by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .wrapContentSize()
            .background(color = colorResource(id = R.color.sheetBg))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Hello...", modifier = Modifier.padding(25.dp),
                style = MaterialTheme.typography.bodyLarge
            )
            Button(
                onClick = {  scope.launch { state.bottomSheetState.hide() } }, modifier = Modifier.padding(30.dp),
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.sheetBg))
            ) {
                Image(
                    Icons.Default.Close, contentDescription = "", alignment = Alignment.Center,
                    colorFilter = ColorFilter.tint(color = colorResource(id = R.color.red))
                )
            }


        }
        Column(
            modifier = Modifier
                .padding(0.dp, 70.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Register",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(horizontal = 25.dp)
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                shape = RoundedCornerShape(8.dp),
                label = { Text("username/email") },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(25.dp, 25.dp, 25.dp, 0.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = colorResource(id = R.color.sheetBg),
                    unfocusedContainerColor = colorResource(id = R.color.sheetBg),
                )

            )
            OutlinedTextField(
                value = course,
                onValueChange = { course = it },
                label = { Text("Course") },
                modifier = Modifier
                    .padding(25.dp, 25.dp, 25.dp, 0.dp)
                    .align(Alignment.CenterHorizontally),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = colorResource(id = R.color.sheetBg),
                    unfocusedContainerColor = colorResource(id = R.color.sheetBg),
                ),
                shape = RoundedCornerShape(8.dp),
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier
                    .padding(25.dp, 25.dp, 25.dp, 0.dp)
                    .align(Alignment.CenterHorizontally),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = colorResource(id = R.color.sheetBg),
                    unfocusedContainerColor = colorResource(id = R.color.sheetBg),
                ),
                shape = RoundedCornerShape(8.dp),
            )
            OutlinedTextField(
                value = confirmPass,
                onValueChange = { confirmPass = it },
                label = { Text("Confirm Password") },
                modifier = Modifier
                    .padding(25.dp, 25.dp, 25.dp, 0.dp)
                    .align(Alignment.CenterHorizontally),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = colorResource(id = R.color.sheetBg),
                    unfocusedContainerColor = colorResource(id = R.color.sheetBg),
                ),
                shape = RoundedCornerShape(8.dp),
            )

            Button(
                onClick = { },
                modifier = Modifier
                    .padding(25.dp, 25.dp, 25.dp, 0.dp)
                    .align(Alignment.CenterHorizontally),
                contentPadding = PaddingValues(vertical = 16.dp, horizontal = 110.dp),
                colors = ButtonDefaults.buttonColors(
                    colorResource(id = R.color.black),
                )
            ) {
                Text(text = "Register", color = colorResource(id = R.color.yellow))
            }

        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(scope:CoroutineScope, state: BottomSheetScaffoldState) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .wrapContentSize()
            .background(color = colorResource(id = R.color.sheetBg))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Welcome back!!!", modifier = Modifier.padding(25.dp),
                style = MaterialTheme.typography.bodyLarge
            )
            Button(
                onClick = { scope.launch { state.bottomSheetState.hide() } }, modifier = Modifier.padding(30.dp),
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.sheetBg))
            ) {
                Image(
                    Icons.Default.Close, contentDescription = "", alignment = Alignment.Center,
                    colorFilter = ColorFilter.tint(color = colorResource(id = R.color.red))
                )
            }


        }
        Column(
            modifier = Modifier
                .padding(0.dp, 70.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Login",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(horizontal = 25.dp)
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("username/email") },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(25.dp, 25.dp, 25.dp, 0.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = colorResource(id = R.color.sheetBg),
                    unfocusedContainerColor = colorResource(id = R.color.sheetBg),
                ),
                shape = RoundedCornerShape(8.dp),

                )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier
                    .padding(25.dp, 25.dp, 25.dp, 0.dp)
                    .align(Alignment.CenterHorizontally),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = colorResource(id = R.color.sheetBg),
                    unfocusedContainerColor = colorResource(id = R.color.sheetBg),
                ),
                shape = RoundedCornerShape(8.dp),
            )

            Button(
                onClick = { },
                modifier = Modifier
                    .padding(25.dp, 25.dp, 25.dp, 0.dp)
                    .align(Alignment.CenterHorizontally),
                contentPadding = PaddingValues(vertical = 16.dp, horizontal = 110.dp),
                colors = ButtonDefaults.buttonColors(
                    colorResource(id = R.color.black),
                )
            ) {
                Text(text = "Login", color = colorResource(id = R.color.yellow))
            }

        }
    }

}



