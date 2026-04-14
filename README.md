# dance4life - Android + Reinforcement Learning Monorepo

This repository is organized to keep Android app code, RL experimentation, and model export pipelines separated but connected.

Mission: help older adults move more, reduce sedentary behavior, and improve daily health through personalized movement prompts.

## Repository layout

```text
AR/
  apps/
    android/                # Android application (Gradle project)
      app/                  # Main app module
      build-logic/          # Optional convention plugins/version catalogs later
  libs/
    core-domain/            # Shared domain models/contracts (Kotlin)
    core-data/              # Data sources/repositories (Kotlin)
  plugins/
    rl-inference/           # Android/Kotlin module for on-device policy inference
  ml/
    training/               # RL training code (Python)
    export/                 # Export scripts (ONNX/TFLite)
  scripts/                  # Utility scripts (bootstrap, checks, release helpers)
  docs/                     # Architecture and decision records
  .github/workflows/        # CI pipelines
```

## Why this structure

- Keeps Android runtime code independent from training experiments.
- Makes model handoff explicit: `ml/export` -> `apps/android/app/src/main/assets/models`.
- Lets you test and version each area independently.

## Suggested technology split

- Android app: Kotlin + Jetpack Compose + Clean Architecture.
- RL training: Python (Gymnasium, Stable-Baselines3 or RLlib).
- Inference on device: TensorFlow Lite or ONNX Runtime Mobile.

## Suggested milestones

1. Initialize Android app in `apps/android`.
2. Define model input/output contract in `libs/core-domain`.
3. Train baseline policy in `ml/training` and export to TFLite/ONNX.
4. Implement `plugins/rl-inference` for model loading + inference API.
5. Integrate module in `apps/android/app`.
6. Automate model validation and copy in CI.
