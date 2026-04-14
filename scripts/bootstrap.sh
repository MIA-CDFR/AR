#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"

printf "==> Bootstrapping ML training env\n"
cd "$ROOT_DIR/ml/training"
if command -v python3 >/dev/null 2>&1; then
  python3 -m venv .venv
  source .venv/bin/activate
  python -m pip install --upgrade pip
  python -m pip install -e .
else
  echo "python3 not found. Install Python 3.11+ first." >&2
  exit 1
fi

printf "==> Android project scaffolding\n"
printf "Open apps/android in Android Studio and run Gradle Sync.\n"
printf "If gradle wrapper is missing, run: gradle wrapper --gradle-version 8.10\n"
