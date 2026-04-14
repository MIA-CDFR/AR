# Architecture Notes

## Bounded contexts

- Android UI/UX: `apps/android/app`
- App domain contracts: `libs/core-domain`
- Data and repository wiring: `libs/core-data`
- Model inference adapter: `plugins/rl-inference`
- Training and experiment tracking: `ml/training`
- Model artifact export: `ml/export`

## Artifact flow

1. Train policy in `ml/training`.
2. Export model in `ml/export`.
3. Validate model signature and metrics.
4. Publish/copy model into `apps/android/app/src/main/assets/models`.
5. App consumes model only through `plugins/rl-inference` API.

## Contract-first recommendation

Define a stable contract before model integration:

- Observation schema (shape, normalization).
- Action schema (discrete/continuous mapping).
- Model metadata (version, reward baseline, build hash).

Store this contract as Kotlin data classes in `libs/core-domain` and mirror in Python dataclasses for export validation.
