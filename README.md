#  Quiz Android Application

A  quiz app built with **Jetpack Compose**, using **Firebase Authentication** for login and **Open Trivia DB (OpenTDB)** for fetching trivia questions.  
Users can register with **dummy emails** (no real email verification needed) and enjoy a smooth quiz experience based on chosen category and difficulty.

##  Features

- Firebase-based **Sign Up & Login**
  - Email/Password authentication (no email verification)
  - Dummy emails allowed (e.g., `user@fake.com`)
-  Quiz questions from the [OpenTDB API](https://opentdb.com/)
-  Category selection
-  Difficulty selection: Easy, Medium, Hard
-  Multiple-choice questions
-  Result screen with score
-  Retry option after quiz completion

## Tech Stack

 - UI: Jetpack Compose        
 - Architecture: MVVM                   
 - DI: Hilt                   
 - Network: Retrofit + Gson        
 - Backend Auth: Firebase Auth          
 - Language: Kotlin

## Screenshot

<img width="2557" height="857" alt="quiz" src="https://github.com/user-attachments/assets/c812ed45-79a5-49ba-a38e-10c529499187" />
