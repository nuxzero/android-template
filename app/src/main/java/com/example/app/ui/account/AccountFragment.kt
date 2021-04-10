package com.example.app.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.google.android.material.snackbar.Snackbar

class AccountFragment : BaseFragment() {

    private lateinit var binding: AccountFragmentBinding
    private val viewModel: AccountViewModel by viewModels { viewModelFactory }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = AccountFragmentBinding.inflate(inflater, container, false).apply {
            settingMenu.setContent {
                MaterialTheme {
                    AccountSettings(viewModel)
                }
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.profile = viewModel.profile

        binding.notificationsSettingText.setOnClickListener(::showNotificationSettings)
        binding.faqSettingText.setOnClickListener(::showFaq)
        binding.policySettingText.setOnClickListener(::showPolicy)
    }

    private fun showNotificationSettings(view: View?) {
        Snackbar.make(binding.root, "Tapped notification setting", Snackbar.LENGTH_SHORT).show()
    }

    private fun showFaq(view: View?) {
        Snackbar.make(binding.root, "Tapped FAQ", Snackbar.LENGTH_SHORT).show()
    }

    private fun showPolicy(view: View?) {
        Snackbar.make(binding.root, "Tapped policy", Snackbar.LENGTH_SHORT).show()
    }
}

@Composable
fun AccountSettings(viewModel: AccountViewModel) {
    val profile by viewModel.profile.observeAsState()
    profile?.let {
        AccountSettings(it)
    }
}

@Composable
fun AccountSettings(profile: Profile) {
    Column {
        ProfileInfo(profile)
        SettingMenu()
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
                .height(72.dp)
                .width(72.dp),
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
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
fun SettingMenu() {
    Column {
        SettingMenuItem(title = "Notifications")
        SettingMenuItem(title = "FAQ")
        SettingMenuItem(title = "Policy")
    }
}

@Composable
fun SettingMenuItem(title: String) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
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
