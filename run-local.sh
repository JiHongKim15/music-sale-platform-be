#!/bin/bash

# Get the script directory (project root)
SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

# Load environment variables from .env.local
if [ -f "$SCRIPT_DIR/.env.local" ]; then
    set -a
    source "$SCRIPT_DIR/.env.local"
    set +a
    echo "Loaded environment from .env.local"
else
    echo "Warning: .env.local file not found at $SCRIPT_DIR/.env.local"
fi

# Run the application from project root
cd "$SCRIPT_DIR"
./gradlew :music-api:bootRun
