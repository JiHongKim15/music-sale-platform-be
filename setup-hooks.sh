#!/bin/bash
# Setup git hooks

echo "Setting up git hooks..."

# Configure git to use .githooks directory
git config core.hooksPath .githooks

echo "✅ Git hooks configured successfully!"
echo "Pre-commit hook will now run Spotless check before each commit."
