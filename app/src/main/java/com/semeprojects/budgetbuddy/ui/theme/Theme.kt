package com.semeprojects.budgetbuddy.ui.theme
import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.semeprojects.budgetbuddy.ui.theme.backgroundDark
import com.semeprojects.budgetbuddy.ui.theme.backgroundDarkHighContrast
import com.semeprojects.budgetbuddy.ui.theme.backgroundDarkMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.backgroundLight
import com.semeprojects.budgetbuddy.ui.theme.backgroundLightHighContrast
import com.semeprojects.budgetbuddy.ui.theme.backgroundLightMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.errorContainerDark
import com.semeprojects.budgetbuddy.ui.theme.errorContainerDarkHighContrast
import com.semeprojects.budgetbuddy.ui.theme.errorContainerDarkMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.errorContainerLight
import com.semeprojects.budgetbuddy.ui.theme.errorContainerLightHighContrast
import com.semeprojects.budgetbuddy.ui.theme.errorContainerLightMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.errorDark
import com.semeprojects.budgetbuddy.ui.theme.errorDarkHighContrast
import com.semeprojects.budgetbuddy.ui.theme.errorDarkMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.errorLight
import com.semeprojects.budgetbuddy.ui.theme.errorLightHighContrast
import com.semeprojects.budgetbuddy.ui.theme.errorLightMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.inverseOnSurfaceDark
import com.semeprojects.budgetbuddy.ui.theme.inverseOnSurfaceDarkHighContrast
import com.semeprojects.budgetbuddy.ui.theme.inverseOnSurfaceDarkMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.inverseOnSurfaceLight
import com.semeprojects.budgetbuddy.ui.theme.inverseOnSurfaceLightHighContrast
import com.semeprojects.budgetbuddy.ui.theme.inverseOnSurfaceLightMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.inversePrimaryDark
import com.semeprojects.budgetbuddy.ui.theme.inversePrimaryDarkHighContrast
import com.semeprojects.budgetbuddy.ui.theme.inversePrimaryDarkMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.inversePrimaryLight
import com.semeprojects.budgetbuddy.ui.theme.inversePrimaryLightHighContrast
import com.semeprojects.budgetbuddy.ui.theme.inversePrimaryLightMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.inverseSurfaceDark
import com.semeprojects.budgetbuddy.ui.theme.inverseSurfaceDarkHighContrast
import com.semeprojects.budgetbuddy.ui.theme.inverseSurfaceDarkMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.inverseSurfaceLight
import com.semeprojects.budgetbuddy.ui.theme.inverseSurfaceLightHighContrast
import com.semeprojects.budgetbuddy.ui.theme.inverseSurfaceLightMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.onBackgroundDark
import com.semeprojects.budgetbuddy.ui.theme.onBackgroundDarkHighContrast
import com.semeprojects.budgetbuddy.ui.theme.onBackgroundDarkMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.onBackgroundLight
import com.semeprojects.budgetbuddy.ui.theme.onBackgroundLightHighContrast
import com.semeprojects.budgetbuddy.ui.theme.onBackgroundLightMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.onErrorContainerDark
import com.semeprojects.budgetbuddy.ui.theme.onErrorContainerDarkHighContrast
import com.semeprojects.budgetbuddy.ui.theme.onErrorContainerDarkMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.onErrorContainerLight
import com.semeprojects.budgetbuddy.ui.theme.onErrorContainerLightHighContrast
import com.semeprojects.budgetbuddy.ui.theme.onErrorContainerLightMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.onErrorDark
import com.semeprojects.budgetbuddy.ui.theme.onErrorDarkHighContrast
import com.semeprojects.budgetbuddy.ui.theme.onErrorDarkMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.onErrorLight
import com.semeprojects.budgetbuddy.ui.theme.onErrorLightHighContrast
import com.semeprojects.budgetbuddy.ui.theme.onErrorLightMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.onPrimaryContainerDark
import com.semeprojects.budgetbuddy.ui.theme.onPrimaryContainerDarkHighContrast
import com.semeprojects.budgetbuddy.ui.theme.onPrimaryContainerDarkMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.onPrimaryContainerLight
import com.semeprojects.budgetbuddy.ui.theme.onPrimaryContainerLightHighContrast
import com.semeprojects.budgetbuddy.ui.theme.onPrimaryContainerLightMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.onPrimaryDark
import com.semeprojects.budgetbuddy.ui.theme.onPrimaryDarkHighContrast
import com.semeprojects.budgetbuddy.ui.theme.onPrimaryDarkMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.onPrimaryLight
import com.semeprojects.budgetbuddy.ui.theme.onPrimaryLightHighContrast
import com.semeprojects.budgetbuddy.ui.theme.onPrimaryLightMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.onSecondaryContainerDark
import com.semeprojects.budgetbuddy.ui.theme.onSecondaryContainerDarkHighContrast
import com.semeprojects.budgetbuddy.ui.theme.onSecondaryContainerDarkMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.onSecondaryContainerLight
import com.semeprojects.budgetbuddy.ui.theme.onSecondaryContainerLightHighContrast
import com.semeprojects.budgetbuddy.ui.theme.onSecondaryContainerLightMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.onSecondaryDark
import com.semeprojects.budgetbuddy.ui.theme.onSecondaryDarkHighContrast
import com.semeprojects.budgetbuddy.ui.theme.onSecondaryDarkMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.onSecondaryLight
import com.semeprojects.budgetbuddy.ui.theme.onSecondaryLightHighContrast
import com.semeprojects.budgetbuddy.ui.theme.onSecondaryLightMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.onSurfaceDark
import com.semeprojects.budgetbuddy.ui.theme.onSurfaceDarkHighContrast
import com.semeprojects.budgetbuddy.ui.theme.onSurfaceDarkMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.onSurfaceLight
import com.semeprojects.budgetbuddy.ui.theme.onSurfaceLightHighContrast
import com.semeprojects.budgetbuddy.ui.theme.onSurfaceLightMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.onSurfaceVariantDark
import com.semeprojects.budgetbuddy.ui.theme.onSurfaceVariantDarkHighContrast
import com.semeprojects.budgetbuddy.ui.theme.onSurfaceVariantDarkMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.onSurfaceVariantLight
import com.semeprojects.budgetbuddy.ui.theme.onSurfaceVariantLightHighContrast
import com.semeprojects.budgetbuddy.ui.theme.onSurfaceVariantLightMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.onTertiaryContainerDark
import com.semeprojects.budgetbuddy.ui.theme.onTertiaryContainerDarkHighContrast
import com.semeprojects.budgetbuddy.ui.theme.onTertiaryContainerDarkMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.onTertiaryContainerLight
import com.semeprojects.budgetbuddy.ui.theme.onTertiaryContainerLightHighContrast
import com.semeprojects.budgetbuddy.ui.theme.onTertiaryContainerLightMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.onTertiaryDark
import com.semeprojects.budgetbuddy.ui.theme.onTertiaryDarkHighContrast
import com.semeprojects.budgetbuddy.ui.theme.onTertiaryDarkMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.onTertiaryLight
import com.semeprojects.budgetbuddy.ui.theme.onTertiaryLightHighContrast
import com.semeprojects.budgetbuddy.ui.theme.onTertiaryLightMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.outlineDark
import com.semeprojects.budgetbuddy.ui.theme.outlineDarkHighContrast
import com.semeprojects.budgetbuddy.ui.theme.outlineDarkMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.outlineLight
import com.semeprojects.budgetbuddy.ui.theme.outlineLightHighContrast
import com.semeprojects.budgetbuddy.ui.theme.outlineLightMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.outlineVariantDark
import com.semeprojects.budgetbuddy.ui.theme.outlineVariantDarkHighContrast
import com.semeprojects.budgetbuddy.ui.theme.outlineVariantDarkMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.outlineVariantLight
import com.semeprojects.budgetbuddy.ui.theme.outlineVariantLightHighContrast
import com.semeprojects.budgetbuddy.ui.theme.outlineVariantLightMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.primaryContainerDark
import com.semeprojects.budgetbuddy.ui.theme.primaryContainerDarkHighContrast
import com.semeprojects.budgetbuddy.ui.theme.primaryContainerDarkMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.primaryContainerLight
import com.semeprojects.budgetbuddy.ui.theme.primaryContainerLightHighContrast
import com.semeprojects.budgetbuddy.ui.theme.primaryContainerLightMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.primaryDark
import com.semeprojects.budgetbuddy.ui.theme.primaryDarkHighContrast
import com.semeprojects.budgetbuddy.ui.theme.primaryDarkMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.primaryLight
import com.semeprojects.budgetbuddy.ui.theme.primaryLightHighContrast
import com.semeprojects.budgetbuddy.ui.theme.primaryLightMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.scrimDark
import com.semeprojects.budgetbuddy.ui.theme.scrimDarkHighContrast
import com.semeprojects.budgetbuddy.ui.theme.scrimDarkMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.scrimLight
import com.semeprojects.budgetbuddy.ui.theme.scrimLightHighContrast
import com.semeprojects.budgetbuddy.ui.theme.scrimLightMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.secondaryContainerDark
import com.semeprojects.budgetbuddy.ui.theme.secondaryContainerDarkHighContrast
import com.semeprojects.budgetbuddy.ui.theme.secondaryContainerDarkMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.secondaryContainerLight
import com.semeprojects.budgetbuddy.ui.theme.secondaryContainerLightHighContrast
import com.semeprojects.budgetbuddy.ui.theme.secondaryContainerLightMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.secondaryDark
import com.semeprojects.budgetbuddy.ui.theme.secondaryDarkHighContrast
import com.semeprojects.budgetbuddy.ui.theme.secondaryDarkMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.secondaryLight
import com.semeprojects.budgetbuddy.ui.theme.secondaryLightHighContrast
import com.semeprojects.budgetbuddy.ui.theme.secondaryLightMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.surfaceBrightDark
import com.semeprojects.budgetbuddy.ui.theme.surfaceBrightDarkHighContrast
import com.semeprojects.budgetbuddy.ui.theme.surfaceBrightDarkMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.surfaceBrightLight
import com.semeprojects.budgetbuddy.ui.theme.surfaceBrightLightHighContrast
import com.semeprojects.budgetbuddy.ui.theme.surfaceBrightLightMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.surfaceContainerDark
import com.semeprojects.budgetbuddy.ui.theme.surfaceContainerDarkHighContrast
import com.semeprojects.budgetbuddy.ui.theme.surfaceContainerDarkMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.surfaceContainerHighDark
import com.semeprojects.budgetbuddy.ui.theme.surfaceContainerHighDarkHighContrast
import com.semeprojects.budgetbuddy.ui.theme.surfaceContainerHighDarkMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.surfaceContainerHighLight
import com.semeprojects.budgetbuddy.ui.theme.surfaceContainerHighLightHighContrast
import com.semeprojects.budgetbuddy.ui.theme.surfaceContainerHighLightMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.surfaceContainerHighestDark
import com.semeprojects.budgetbuddy.ui.theme.surfaceContainerHighestDarkHighContrast
import com.semeprojects.budgetbuddy.ui.theme.surfaceContainerHighestDarkMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.surfaceContainerHighestLight
import com.semeprojects.budgetbuddy.ui.theme.surfaceContainerHighestLightHighContrast
import com.semeprojects.budgetbuddy.ui.theme.surfaceContainerHighestLightMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.surfaceContainerLight
import com.semeprojects.budgetbuddy.ui.theme.surfaceContainerLightHighContrast
import com.semeprojects.budgetbuddy.ui.theme.surfaceContainerLightMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.surfaceContainerLowDark
import com.semeprojects.budgetbuddy.ui.theme.surfaceContainerLowDarkHighContrast
import com.semeprojects.budgetbuddy.ui.theme.surfaceContainerLowDarkMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.surfaceContainerLowLight
import com.semeprojects.budgetbuddy.ui.theme.surfaceContainerLowLightHighContrast
import com.semeprojects.budgetbuddy.ui.theme.surfaceContainerLowLightMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.surfaceContainerLowestDark
import com.semeprojects.budgetbuddy.ui.theme.surfaceContainerLowestDarkHighContrast
import com.semeprojects.budgetbuddy.ui.theme.surfaceContainerLowestDarkMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.surfaceContainerLowestLight
import com.semeprojects.budgetbuddy.ui.theme.surfaceContainerLowestLightHighContrast
import com.semeprojects.budgetbuddy.ui.theme.surfaceContainerLowestLightMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.surfaceDark
import com.semeprojects.budgetbuddy.ui.theme.surfaceDarkHighContrast
import com.semeprojects.budgetbuddy.ui.theme.surfaceDarkMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.surfaceDimDark
import com.semeprojects.budgetbuddy.ui.theme.surfaceDimDarkHighContrast
import com.semeprojects.budgetbuddy.ui.theme.surfaceDimDarkMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.surfaceDimLight
import com.semeprojects.budgetbuddy.ui.theme.surfaceDimLightHighContrast
import com.semeprojects.budgetbuddy.ui.theme.surfaceDimLightMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.surfaceLight
import com.semeprojects.budgetbuddy.ui.theme.surfaceLightHighContrast
import com.semeprojects.budgetbuddy.ui.theme.surfaceLightMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.surfaceVariantDark
import com.semeprojects.budgetbuddy.ui.theme.surfaceVariantDarkHighContrast
import com.semeprojects.budgetbuddy.ui.theme.surfaceVariantDarkMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.surfaceVariantLight
import com.semeprojects.budgetbuddy.ui.theme.surfaceVariantLightHighContrast
import com.semeprojects.budgetbuddy.ui.theme.surfaceVariantLightMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.tertiaryContainerDark
import com.semeprojects.budgetbuddy.ui.theme.tertiaryContainerDarkHighContrast
import com.semeprojects.budgetbuddy.ui.theme.tertiaryContainerDarkMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.tertiaryContainerLight
import com.semeprojects.budgetbuddy.ui.theme.tertiaryContainerLightHighContrast
import com.semeprojects.budgetbuddy.ui.theme.tertiaryContainerLightMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.tertiaryDark
import com.semeprojects.budgetbuddy.ui.theme.tertiaryDarkHighContrast
import com.semeprojects.budgetbuddy.ui.theme.tertiaryDarkMediumContrast
import com.semeprojects.budgetbuddy.ui.theme.tertiaryLight
import com.semeprojects.budgetbuddy.ui.theme.tertiaryLightHighContrast
import com.semeprojects.budgetbuddy.ui.theme.tertiaryLightMediumContrast

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

private val mediumContrastLightColorScheme = lightColorScheme(
    primary = primaryLightMediumContrast,
    onPrimary = onPrimaryLightMediumContrast,
    primaryContainer = primaryContainerLightMediumContrast,
    onPrimaryContainer = onPrimaryContainerLightMediumContrast,
    secondary = secondaryLightMediumContrast,
    onSecondary = onSecondaryLightMediumContrast,
    secondaryContainer = secondaryContainerLightMediumContrast,
    onSecondaryContainer = onSecondaryContainerLightMediumContrast,
    tertiary = tertiaryLightMediumContrast,
    onTertiary = onTertiaryLightMediumContrast,
    tertiaryContainer = tertiaryContainerLightMediumContrast,
    onTertiaryContainer = onTertiaryContainerLightMediumContrast,
    error = errorLightMediumContrast,
    onError = onErrorLightMediumContrast,
    errorContainer = errorContainerLightMediumContrast,
    onErrorContainer = onErrorContainerLightMediumContrast,
    background = backgroundLightMediumContrast,
    onBackground = onBackgroundLightMediumContrast,
    surface = surfaceLightMediumContrast,
    onSurface = onSurfaceLightMediumContrast,
    surfaceVariant = surfaceVariantLightMediumContrast,
    onSurfaceVariant = onSurfaceVariantLightMediumContrast,
    outline = outlineLightMediumContrast,
    outlineVariant = outlineVariantLightMediumContrast,
    scrim = scrimLightMediumContrast,
    inverseSurface = inverseSurfaceLightMediumContrast,
    inverseOnSurface = inverseOnSurfaceLightMediumContrast,
    inversePrimary = inversePrimaryLightMediumContrast,
    surfaceDim = surfaceDimLightMediumContrast,
    surfaceBright = surfaceBrightLightMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestLightMediumContrast,
    surfaceContainerLow = surfaceContainerLowLightMediumContrast,
    surfaceContainer = surfaceContainerLightMediumContrast,
    surfaceContainerHigh = surfaceContainerHighLightMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestLightMediumContrast,
)

private val highContrastLightColorScheme = lightColorScheme(
    primary = primaryLightHighContrast,
    onPrimary = onPrimaryLightHighContrast,
    primaryContainer = primaryContainerLightHighContrast,
    onPrimaryContainer = onPrimaryContainerLightHighContrast,
    secondary = secondaryLightHighContrast,
    onSecondary = onSecondaryLightHighContrast,
    secondaryContainer = secondaryContainerLightHighContrast,
    onSecondaryContainer = onSecondaryContainerLightHighContrast,
    tertiary = tertiaryLightHighContrast,
    onTertiary = onTertiaryLightHighContrast,
    tertiaryContainer = tertiaryContainerLightHighContrast,
    onTertiaryContainer = onTertiaryContainerLightHighContrast,
    error = errorLightHighContrast,
    onError = onErrorLightHighContrast,
    errorContainer = errorContainerLightHighContrast,
    onErrorContainer = onErrorContainerLightHighContrast,
    background = backgroundLightHighContrast,
    onBackground = onBackgroundLightHighContrast,
    surface = surfaceLightHighContrast,
    onSurface = onSurfaceLightHighContrast,
    surfaceVariant = surfaceVariantLightHighContrast,
    onSurfaceVariant = onSurfaceVariantLightHighContrast,
    outline = outlineLightHighContrast,
    outlineVariant = outlineVariantLightHighContrast,
    scrim = scrimLightHighContrast,
    inverseSurface = inverseSurfaceLightHighContrast,
    inverseOnSurface = inverseOnSurfaceLightHighContrast,
    inversePrimary = inversePrimaryLightHighContrast,
    surfaceDim = surfaceDimLightHighContrast,
    surfaceBright = surfaceBrightLightHighContrast,
    surfaceContainerLowest = surfaceContainerLowestLightHighContrast,
    surfaceContainerLow = surfaceContainerLowLightHighContrast,
    surfaceContainer = surfaceContainerLightHighContrast,
    surfaceContainerHigh = surfaceContainerHighLightHighContrast,
    surfaceContainerHighest = surfaceContainerHighestLightHighContrast,
)

private val mediumContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkMediumContrast,
    onPrimary = onPrimaryDarkMediumContrast,
    primaryContainer = primaryContainerDarkMediumContrast,
    onPrimaryContainer = onPrimaryContainerDarkMediumContrast,
    secondary = secondaryDarkMediumContrast,
    onSecondary = onSecondaryDarkMediumContrast,
    secondaryContainer = secondaryContainerDarkMediumContrast,
    onSecondaryContainer = onSecondaryContainerDarkMediumContrast,
    tertiary = tertiaryDarkMediumContrast,
    onTertiary = onTertiaryDarkMediumContrast,
    tertiaryContainer = tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = onTertiaryContainerDarkMediumContrast,
    error = errorDarkMediumContrast,
    onError = onErrorDarkMediumContrast,
    errorContainer = errorContainerDarkMediumContrast,
    onErrorContainer = onErrorContainerDarkMediumContrast,
    background = backgroundDarkMediumContrast,
    onBackground = onBackgroundDarkMediumContrast,
    surface = surfaceDarkMediumContrast,
    onSurface = onSurfaceDarkMediumContrast,
    surfaceVariant = surfaceVariantDarkMediumContrast,
    onSurfaceVariant = onSurfaceVariantDarkMediumContrast,
    outline = outlineDarkMediumContrast,
    outlineVariant = outlineVariantDarkMediumContrast,
    scrim = scrimDarkMediumContrast,
    inverseSurface = inverseSurfaceDarkMediumContrast,
    inverseOnSurface = inverseOnSurfaceDarkMediumContrast,
    inversePrimary = inversePrimaryDarkMediumContrast,
    surfaceDim = surfaceDimDarkMediumContrast,
    surfaceBright = surfaceBrightDarkMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkMediumContrast,
    surfaceContainerLow = surfaceContainerLowDarkMediumContrast,
    surfaceContainer = surfaceContainerDarkMediumContrast,
    surfaceContainerHigh = surfaceContainerHighDarkMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkMediumContrast,
)

private val highContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkHighContrast,
    onPrimary = onPrimaryDarkHighContrast,
    primaryContainer = primaryContainerDarkHighContrast,
    onPrimaryContainer = onPrimaryContainerDarkHighContrast,
    secondary = secondaryDarkHighContrast,
    onSecondary = onSecondaryDarkHighContrast,
    secondaryContainer = secondaryContainerDarkHighContrast,
    onSecondaryContainer = onSecondaryContainerDarkHighContrast,
    tertiary = tertiaryDarkHighContrast,
    onTertiary = onTertiaryDarkHighContrast,
    tertiaryContainer = tertiaryContainerDarkHighContrast,
    onTertiaryContainer = onTertiaryContainerDarkHighContrast,
    error = errorDarkHighContrast,
    onError = onErrorDarkHighContrast,
    errorContainer = errorContainerDarkHighContrast,
    onErrorContainer = onErrorContainerDarkHighContrast,
    background = backgroundDarkHighContrast,
    onBackground = onBackgroundDarkHighContrast,
    surface = surfaceDarkHighContrast,
    onSurface = onSurfaceDarkHighContrast,
    surfaceVariant = surfaceVariantDarkHighContrast,
    onSurfaceVariant = onSurfaceVariantDarkHighContrast,
    outline = outlineDarkHighContrast,
    outlineVariant = outlineVariantDarkHighContrast,
    scrim = scrimDarkHighContrast,
    inverseSurface = inverseSurfaceDarkHighContrast,
    inverseOnSurface = inverseOnSurfaceDarkHighContrast,
    inversePrimary = inversePrimaryDarkHighContrast,
    surfaceDim = surfaceDimDarkHighContrast,
    surfaceBright = surfaceBrightDarkHighContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkHighContrast,
    surfaceContainerLow = surfaceContainerLowDarkHighContrast,
    surfaceContainer = surfaceContainerDarkHighContrast,
    surfaceContainerHigh = surfaceContainerHighDarkHighContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkHighContrast,
)

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

@Composable
fun BudgetBuddyTheme(
    darkTheme: Boolean,
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable() () -> Unit
) {
  val colorScheme = when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
          val context = LocalContext.current
          if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }
      
      darkTheme -> darkScheme
      else -> lightScheme
  }
  val view = LocalView.current
  if (!view.isInEditMode) {
    SideEffect {
      val window = (view.context as Activity).window
      window.statusBarColor = colorScheme.primary.toArgb()
      WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
    }
  }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = AppTypography,
    content = content
  )
}

