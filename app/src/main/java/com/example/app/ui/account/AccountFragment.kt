package com.example.app.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import com.example.app.R
import com.example.app.data.models.Profile
import com.example.app.databinding.AccountFragmentBinding
import com.example.app.util.BaseFragment
import com.google.accompanist.glide.GlideImage
import kotlinx.coroutines.launch

class AccountFragment : BaseFragment() {

    private lateinit var binding: AccountFragmentBinding
    private val viewModel: AccountViewModel by viewModels { viewModelFactory }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = AccountFragmentBinding.inflate(inflater, container, false).apply {
            containerView.setContent {
                MaterialTheme {
                    AccountSettingsPage(viewModel)
                }
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.profile = viewModel.profile

    }
}

@Composable
fun AccountSettingsPage(viewModel: AccountViewModel) {
    val profile by viewModel.profile.observeAsState()
    profile?.let {
        AccountSettings(it)
    }
}

@Composable
fun AccountSettings(profile: Profile) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            ProfileInfo(profile)
            SettingMenuItem("Notifications") {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar("Tapped notification setting!", duration = SnackbarDuration.Short)
                }
            }
            SettingMenuItem("FAQ") {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar("Tapped FAQ!", duration = SnackbarDuration.Short)
                }
            }
            SettingMenuItem("Policy") {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar("Tapped policy!", duration = SnackbarDuration.Short)
                }
            }
        }
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun ProfileInfo(profile: Profile) {
    val tintColor = Color(0xFF, 0xFF, 0xFF)
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        GlideImage(
            data = profile.image,
            contentDescription = null,
            fadeIn = true,
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
                fontWeight = FontWeight.Bold,
                color = tintColor,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                profile.email,
                color = tintColor,
            )
        }
    }
}

@Composable
fun SettingMenuItem(title: String, action: (() -> Unit)? = null) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable { action?.let { it() } }
    ) {
        val tintColor = Color(0xFF, 0xFF, 0xFF)
        Text(
            title,
            fontWeight = FontWeight.Bold,
            color = tintColor,
            modifier = Modifier.weight(1f)
        )
        Image(
            painterResource(R.drawable.ic_arrow_forward),
            contentDescription = null,
            colorFilter = ColorFilter.tint(tintColor),
            alignment = Alignment.Center,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Preview
@Composable
fun AccountSettingsPreview() {
    val profile = Profile(
        id = 1,
        fullName = "John Doe",
        email = "john@email.com",
        image = "https://picsum.photos/id/237/200/300"
    )
    MaterialTheme {
        AccountSettings(profile)
    }
}
