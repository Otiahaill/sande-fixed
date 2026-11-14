# sande: why the build failed and what was changed

## Root cause of the AAPT error (runs #10 to #18)
`themes.xml` had `parent="android:Theme.Material3.DayNight.NoActionBar"`.
There is no such framework theme. `Theme.Material3.*` lives in the *View-system* library
`com.google.android.material:material`, which this project does not use (it uses Compose Material 3).
The `android:` prefix means "look in the Android framework", so it can never resolve on any SDK level.
Lowering compileSdk (36 to 34) could not help. Fix: parent = `android:Theme.Material.Light.NoActionBar`.

## Why it would have failed again right after
Resource linking is only the first gate. Kotlin compilation had never run. Found and fixed:
- Compose compiler 1.5.14 needs Kotlin 1.9.24; project uses 1.9.23 (now 1.5.13)
- Compose BOM 2024.10 / Navigation 2.8 want compileSdk 35; project is on 34 (versions aligned to 34)
- AppDatabase and SandeApplication: missing imports; `addTypeConverters()` does not exist (removed, unused)
- Outbox: missing `Index` import
- Repositories imported `javax.inject.Inject` but Hilt was stripped (removed)
- Theme/Color/Type: invented APIs (`readonly`, `letterHeight`, `androidx.compose.runtime.readonly`)
- AppNavGraph: `sealed object`, Material 2 `BottomNavigation` API, missing imports
- Screens: `FloatingActionButton(icon=)`, `Card(elevation=Dp)`, `ListItem(headlineText=)`, wrong `Icons` import,
  `floatingActionButton = Button(...)` (a value, not a lambda), missing MaterialTheme imports
- ViewModels: wrong `viewModelScope` import, `stateIn` without arguments, `androidx.lifecycle.MutableStateFlow`
  (does not exist), mutating immutable data class fields
- SplashScreen used Thread.sleep inside a composable (freezes the UI)
- MovementDao: VOID subtracted stock again; a void must restore it
- Manifest duplicated at project root; local.properties with a device path was in the zip
- CI now uploads the full build log and reports when a build fails

## Not verified
No Gradle or Android SDK was available where these fixes were made, so none of it has been compiled.
Push it and read the CI result. Remaining errors, if any, will now be small.

## Known gaps (unchanged by design)
- Recording a sale is a placeholder: not atomic, no sale header, no outbox entry (blueprint M6)
- Auth is a mock; no sync; movements use type + positive quantity, not the blueprint's signed qtyDelta
- Launcher icon exists only for API 26+; on Android 7.0/7.1 it will be missing
