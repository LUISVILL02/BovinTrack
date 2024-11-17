package com.seminario.bovintrack.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.seminario.bovintrack.ui.navigate.NavigationItem
import com.seminario.bovintrack.ui.view.auth.TopBar
import com.seminario.bovintrack.ui.view.propietario.components.CardFincas
import com.seminario.bovintrack.ui.viewmodel.UserViewModel
import com.seminario.bovintrack.ui.viewmodel.propietario.ListFincasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListFincas(
    navController: NavController,
    viewModel: ListFincasViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel()
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    val fincas by viewModel.fincas.collectAsState()
    val user by userViewModel.user.collectAsState()

    LaunchedEffect(user) {
        userViewModel.loadUserFromToken()
        user?.let {
            viewModel.getFincasPorPropietario(it.id)
        }
    }

    Scaffold (
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBar(navController)
        },
        content = { innerPadding ->
            Box(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(16.dp)
                ) {
                    items(fincas.size) { index ->
                        CardFincas(finca = fincas[index], navController = navController)
                        Spacer(modifier = Modifier.padding(16.dp))
                    }
                }

                Button(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp),
                    onClick = {
                        navController.navigate(NavigationItem.AddBovi.route)
                    }
                ) {
                    Text(text = "Agregar finca")
                }
            }
        }
    )
}