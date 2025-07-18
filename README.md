# NoteApp

A modern, feature-rich note-taking application built with Jetpack Compose and following Material Design 3 principles.

## Features

### Core Functionality
- ✅ Create, edit, and delete notes
- ✅ Auto-save functionality
- ✅ Search notes by title and content
- ✅ Pin important notes
- ✅ Color-coded notes with custom tags
- ✅ Trash/recycle bin for deleted notes
- ✅ Restore notes from trash

### User Experience
- 🎨 Material Design 3 UI
- 🌙 Dynamic theming support (Android 12+)
- 📱 Responsive design
- ⚡ Smooth animations and transitions
- 🔍 Real-time search functionality

## Tech Stack

### Architecture
- **MVVM** (Model-View-ViewModel) pattern
- **Repository Pattern** for data management
- **Dependency Injection** with Hilt
- **Clean Architecture** principles

### Technologies
- **Kotlin** - Primary programming language
- **Jetpack Compose** - Modern UI toolkit
- **Room Database** - Local data persistence
- **Hilt** - Dependency injection
- **Coroutines & Flow** - Asynchronous programming
- **Navigation Compose** - In-app navigation
- **Material Design 3** - UI design system
- **WorkManager** - Background tasks
- **DataStore** - Preferences storage

## Project Structure

```
app/
├── src/main/java/com/app/noteapp/
│   ├── data/
│   │   ├── database/          # Room database setup
│   │   ├── model/             # Data models
│   │   └── repository/        # Repository implementations
│   ├── di/                    # Dependency injection modules
│   ├── ui/
│   │   ├── components/        # Reusable UI components
│   │   ├── navigation/        # Navigation setup
│   │   ├── screens/           # App screens
│   │   ├── theme/             # Material Design theme
│   │   └── viewmodel/         # ViewModels
│   ├── MainActivity.kt        # Main activity
│   └── NoteApplication.kt     # Application class
```

## Screenshots

### Home Screen
- View all notes in a clean, organized layout
- Search functionality with real-time filtering
- Quick access to create new notes
- Pin important notes to the top

### Note Editor
- Rich text editing experience
- Auto-save functionality
- Color coding for better organization
- Pin/unpin notes directly from editor

### Trash/Recycle Bin
- View deleted notes
- Restore or permanently delete notes
- Keep your workspace clean

## Getting Started

### Prerequisites
- Android Studio Hedgehog or later
- JDK 17
- Android SDK 24+

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/noteapp.git
   cd noteapp
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Select "Open an existing project"
   - Navigate to the cloned repository

3. **Build and Run**
   ```bash
   ./gradlew assembleDebug
   ```
   Or use Android Studio's build tools

### Configuration

The app uses default configurations, but you can customize:

- **Database name**: Modify in `DatabaseModule.kt`
- **App theme**: Update colors in `ui/theme/Color.kt`
- **Navigation**: Modify routes in `ui/navigation/NoteNavigation.kt`

## Development

### Building
```bash
# Debug build
./gradlew assembleDebug

# Release build
./gradlew assembleRelease

# Run tests
./gradlew test
```

### Code Style
- Follows official Kotlin coding conventions
- Uses ktlint for code formatting
- Material Design 3 guidelines for UI

### Testing
- Unit tests for ViewModels and Repository
- UI tests with Compose Testing
- Integration tests for database operations

## Architecture Details

### Data Layer
- **Room Database** for local storage
- **Repository Pattern** for data abstraction
- **Flow** for reactive data streams

### Presentation Layer
- **Jetpack Compose** for UI
- **ViewModel** for state management
- **Navigation Compose** for screen navigation

### Dependency Injection
- **Hilt** for dependency management
- Modular approach with separate modules for different concerns

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

### Code Guidelines
- Follow existing code style and patterns
- Add tests for new features
- Update documentation as needed
- Ensure all tests pass before submitting

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Material Design 3 guidelines
- Android Jetpack Compose documentation
- Open source community contributions

## Roadmap

### Upcoming Features
- [ ] Note categories/folders
- [ ] Export notes (PDF, TXT)
- [ ] Cloud synchronization
- [ ] Rich text formatting
- [ ] Voice notes
- [ ] Note sharing
- [ ] Dark/Light theme toggle
- [ ] Note templates
- [ ] Reminder notifications
- [ ] Note encryption

### Performance Improvements
- [ ] Lazy loading for large note collections
- [ ] Image optimization
- [ ] Database indexing improvements
- [ ] Memory usage optimization

## Support

If you encounter any issues or have questions:

1. Check the [Issues](https://github.com/yourusername/noteapp/issues) page
2. Create a new issue with detailed information
3. Contact the maintainers

---

**Made with ❤️ using Jetpack Compose**
