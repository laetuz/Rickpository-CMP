package id.neotica.rickpository

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import id.neotica.rickpository.di.initializeKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        initializeKoin()

        setContent {
            App()
//            Image(painterResource(Res.drawable.compose_multiplatform), null)
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}