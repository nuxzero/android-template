package com.example.app.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.app.R
import com.example.app.data.models.Profile
import com.example.app.ui.theme.AppTheme
import com.google.accompanist.glide.rememberGlidePainter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AccountFragment : Fragment() {

    private val viewModel: AccountViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        ComposeView(requireContext()).apply {
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            setContent {
                AppTheme {
                    AccountScreen(viewModel)
                }
            }
        }
}

enum class SettingMenu {
    NOTIFICATION,
    FAQ,
    POLICY,
}

@Composable
fun AccountScreen(viewModel: AccountViewModel) {
    val profile by viewModel.profile.observeAsState()
    profile?.let { AccountContent(it) }
}

@Composable
fun AccountContent(profile: Profile) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text("Account", color = MaterialTheme.colors.primary) },
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
            )
        },
        snackbarHost = {
            SnackbarHost(it) { data ->
                Snackbar(snackbarData = data)
            }
        },
        content = {
            AccountSettings(profile) { settingMenu ->
                val message = when (settingMenu) {
                    SettingMenu.NOTIFICATION -> "notifications"
                    SettingMenu.FAQ -> "FAQ"
                    SettingMenu.POLICY -> "policy"
                }
                coroutineScope.launch {
                    scaffoldState.snackbarHostState.showSnackbar("Tapped $message!", duration = SnackbarDuration.Short)
                }
            }
        }
    )
}

@Composable
fun AccountSettings(profile: Profile, onSettingClicked: (SettingMenu) -> Unit) {
    Column {
        ProfileInfo(profile)
        SettingMenuItem("Notifications") {
            onSettingClicked(SettingMenu.NOTIFICATION)
        }
        SettingMenuItem("FAQ") {
            onSettingClicked(SettingMenu.FAQ)
        }
        SettingMenuItem("Policy") {
            onSettingClicked(SettingMenu.POLICY)
        }
    }
}

@Composable
fun ProfileInfo(profile: Profile) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = rememberGlidePainter(
                request = profile.image,
                previewPlaceholder = R.drawable.sample_feature_image,
                fadeIn = true,
            ),
            contentDescription = "profile image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(80.dp)
                .width(80.dp)
                .clip(shape = RoundedCornerShape(8.dp)),
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                profile.fullName,
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.h6,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                profile.email,
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.body1,
            )
        }
    }
}

@Composable
fun SettingMenuItem(title: String, action: (() -> Unit)? = null) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { action?.let { it() } }
    ) {
        Text(
            title,
            style = MaterialTheme.typography.subtitle2,
            color = MaterialTheme.colors.primary,
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        )
        Image(
            painterResource(R.drawable.ic_arrow_forward),
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colors.primary),
            alignment = Alignment.Center,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(16.dp)
        )
    }
}

@Preview
@Composable
fun AccountContentPreview() {
    val profile = Profile(
        id = 1,
        fullName = "John Doe",
        email = "john@email.com",
        image = "https://picsum.photos/id/237/200/300"
    )
    AppTheme(darkTheme = false) {
        AccountContent(profile)
    }
}

@Preview
@Composable
fun DarkAccountContentPreview() {
    val profile = Profile(
        id = 1,
        fullName = "John Doe",
        email = "john@email.com",
        image = "https://picsum.photos/id/237/200/300"
    )
    AppTheme(darkTheme = true) {
        AccountContent(profile)
    }
}
