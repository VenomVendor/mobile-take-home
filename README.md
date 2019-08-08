<img src="./graphics/logo.png" alt="Logo" />

# Technical Test for [Guestlogix Inc.](https://www.guestlogix.com/)
Applicant Coding Challenge

# Notes
- Code is separated into multiple modules
    - core-sdk
    - episode-sdk
    - demo
- To Test
    - `./gradlew clean testDebugUnitTest`

## TODO
- [ ] SOLID Principles
- [ ] Coding Standards
- [ ] Architecture
- [ ] Unit Tests
- [ ] Git

## Addons
- [ ] Gradle Setup
- [ ] Git Flow
- [ ] Git Rebase
- [ ] Git Squash
- [ ] Git Verified Commits
- [ ] Git Release Tags
- [ ] T.D.D
- [ ] Documentation
- [ ] Inline Comments
- [ ] Modular
- [ ] Scalable
- [ ] UI Tests
- [ ] Mock API Calls
- [ ] Code Coverage
- [ ] Setup CI
- [ ] Sonarqube/CodeClimate
 
# Clean Up
- [ ] Application Class

### What is the test?
* Build a mobile application that displays a list of Rick and Morty episodes. 
* On click of an episode, display a list characters in that episode.
* Split list into dead or alive (you're free to decide how to implement this split)
* Sort the list in the order of creation date.
* On click of a character, display detail page showing the character's picture and other informations.
* Bonus points (not mandatory) -> Add functionality to kill a character.
   *  Feel free to impress us with your out of the box thinking. Hint: To kill a character swipe, CTA, long press etc.
   * When a character dies, then everything should update accordingly.

### User Story
 * First screen will have a list of episodes.
 * If the user taps into an episode the app has to display a list of characters with a clear distinction between dead and alive characters
 * If the user taps into a character the app has to display that character's information and picture
 * Bonus functionality: The user should have the ability to kill a character and if a character gets kill the character lists should update accordingly

### Requirements
The application cab be done in **Java** (No Kotlin). Otherwise, you have complete freedom in terms of how you implement the solution, as long as all user requirements are met.

Note however, that **no third party libraries may be used**. You can use any native tools available to you from the platform, but no open sourced, or third party libraries.

### Project's API
<https://rickandmortyapi.com/>
