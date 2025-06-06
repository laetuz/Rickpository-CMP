package id.neotica.rickpository.presentation.ui

import android.annotation.SuppressLint
import android.os.Build
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.unit.dp

@SuppressLint("ModifierFactoryUnreferencedReceiver")
actual fun Modifier.glassmorphicBlur(): Modifier {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        this.then(Modifier.blur(radius = 12.dp))
    } else this
}