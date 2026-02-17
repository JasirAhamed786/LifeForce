# Firebase Setup Instructions

## Sensitive Data Notice

The `app/google-services.json` file contains sensitive Firebase credentials and has been added to `.gitignore`. **DO NOT** commit this file to version control.

## Setup Steps

### Option 1: Download from Firebase Console (Recommended)

1. Go to [Firebase Console](https://console.firebase.google.com/)
2. Select your project: `life-force-854e3` (or create a new one)
3. Go to Project Settings (gear icon ⚙️)
4. Scroll down to "Your apps" section
5. Select Android app (package name: `com.smartrecipe.life_force`)
6. Click "Download google-services.json"
7. Move the downloaded file to: `app/google-services.json`

### Option 2: Use the Template

If you're setting up a new Firebase project:

1. Copy the template: `cp app/google-services.json.template app/google-services.json`
2. Replace placeholder values with your actual Firebase credentials:
   - `YOUR_PROJECT_NUMBER` → Your Firebase project number
   - `your-project-id` → Your Firebase project ID
   - `YOUR_API_KEY` → Your Firebase Web API key
   - `YOUR_APP_ID` → Your mobile app ID

### Important Security Notes

- Never commit `google-services.json` to GitHub
- The current credentials in your local file have been exposed in Git history
- You should rotate the API key in Firebase Console to prevent unauthorized use
- Go to: Firebase Console → Project Settings → General → Web API key → Rotate

## Gradle Configuration

The project uses the Google Services Gradle plugin to read the configuration:

```
kotlin
// app/build.gradle.kts
plugins {
    id("com.google.gms.google-services")
}
```

The plugin automatically loads `google-services.json` from the `app/` directory.
