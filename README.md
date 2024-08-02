![The logo of Wavify.](assets/wavify-white-pink-word.svg?raw=true "Wavify logo")

[![Documentation](https://img.shields.io/badge/documentation-grey)](https://www.wavify.dev/docs)
![Static Badge](https://img.shields.io/badge/platforms-Linux%20%7C%20Android%20%7C%20macOS%20%7C%20iOS%20%7C%20Windows-green)
![GitHub Actions Workflow Status](https://img.shields.io/github/actions/workflow/status/wavify-labs/wavify-sdks/release.yaml)

Wavify is a collection of small speech models and a runtime that is blazingly fast and runs anywhere.

## Benchmarks

Running `assets/samples_jfk.wav` on a Raspberry Pi 4.

|Engine   |Size   |Threads   |Time   |Real-time factor   |
|---|---|---|---|---|
| Whisper.cpp <br> (-O3 with NEON)   | 75MB <br> (Whisper tiny)  | 4  | 9.2s  | 0.84  |
| Wavify  | 45MB  | 4  | 3.8s  | 0.35  |

## Demo

https://github.com/user-attachments/assets/d8cf06e2-c29e-4d0f-9466-a1269b92a584

## Usage

Speech-to-text models for supported languages are available [here](https://github.com/wavify-labs/wavify-sdks/tree/main/models). The filename specifies the language in which 
the model operates, indicated by the ISO 639-1 code.

You'll also need an API key which is available for free. You can get it from your [dashboard](https://www.wavify.dev/signin/password_signin) once signed in.

### Python

```bash
pip install wavify
```

```python
import os
from wavify.stt import SttEngine

engine = SttEngine("path/to/your/model", os.getenv("WAVIFY_API_KEY"))
result = engine.stt_from_file("/path/to/your/file")
```

### Rust

```bash
cargo add wavify
```

```rust
use std::env;
use anyhow::Result;
use wavify::SttEngine;

fn main() -> Result<()> {
  let engine = SttEngine::new("/path/to/your/model", &env::var("WAVIFY_API_KEY")?)?;
  let result = engine.stt_from_file("/path/to/your/file")?;
  Ok(())
}
```

### Android

Kotlin bindings and an example app showcasing the integration of Wavify is available in `android/`.

```kotlin
import dev.wavify.SttEngine

val modelPath = File(applicationContext.filesDir, "/your/model").absolutePath
val apiKey = BuildConfig.WAVIFY_AP_KEY
val appName = applicationContext.packageName
val engine = SttEngine.create(modelPath, apiKey, appName) 

val audioFloats = floatArrayOf(0.0f, 0.1f, 0.2f) // Replace with actual audio data
val result = engine.stt(audioFloats)
```

### iOS

Swift bindings and an example app showcasing the integration of Wavify is available in `ios/`.

```swift
guard let modelPath = Bundle.main.path(forResource: "/your/model", ofType: "bin") else {
  fatalError("Failed to find model file.")
}
guard let apiKey = Bundle.main.object(forInfoDictionaryKey: "WAVIFY_API_KEY") as? String else {
  fatalError("No api key found.")
}
engine = SttEngine(modelPath: modelPath, apiKey: apiKey)!
let audioFloats: [Float] = [3.14, 2.71, 1.61]
engine.recognizeSpeech(from: convertDataToFloatArray(data: floatValues.withUnsafeBufferPointer { Data(buffer: $0) })
```

## Compatibility

### Platforms and architectures

- `aarch64-apple-ios`
- `aarch64-linux-android`
- `aarch64-apple-darwin`
- `x86_64-pc-windows-gnu`
- `x86_64-unknown-linux-gnu`

### Bindings

Wavify comes with support for Python, Kotlin, Swift and Rust.
Additional foreign language bindings can be developed externally and we welcome contributions to list them here. 
Function signature are available in `lib/wavify_core.h`.

## Contributing

Contributions to `wavify` are welcome. 

- Please report bugs as GitHub issues.
- Questions via GitHub issues are welcome!
- To build from source, check the [contributing page](https://github.com/wavify-labs/wavify-sdks/blob/main/CONTRIBUTING.md).
