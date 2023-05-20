# Steps to import the Project and Play the game

Here's a step-by-step guide to unzip a file, import it into IntelliJ IDEA, and set up the project to include JavaFX and MySQL:

1. First, locate the file you want to unzip and right-click on it. Choose "Extract All" or "Unzip" to extract the files.
2. Open IntelliJ IDEA and click on "File" in the top menu, then choose "New" > "Project from Existing Sources" to import the unzipped project.
3. In the "Import Project" window, select the root directory of the project and click "Open". Follow the prompts to set up the project and choose the appropriate SDK.
4. Download the JavaFX SDK and MySQL Connector from their official websites. Add the JavaFX SDK to the project by clicking on "File" > "Project Structure" and selecting "SDKs". Click the "+" button to add a new SDK and select the path to the JavaFX SDK.
5. Add the MySQL Connector to the project by clicking on "File" > "Project Structure" and selecting "Libraries". Click the "+" button to add a new library and select the path to the MySQL Connector.
6. To run the JavaFX application, click on "Run" > "Edit Configurations". In the "Run/Debug Configurations" window, select your application configuration and add the following VM options: `module-path "Path to /javafx-sdk-20/lib" --add-modules=javafx.fxml,javafx.controls,javafx.graphics`.
7. In MySQL, run the following queries to create a new database named "users" and a table named "list":

```sql
CREATE DATABASE users;

CREATE TABLE users.list (
  username VARCHAR(255),
  password VARCHAR(255),
  gamesWon INT DEFAULT 0
);

```

These queries will create a new database named "users" and a table named "list" with columns for "username", "password", and "gamesWon".

### Game Instructions

1. Run the "FirstWindow" program.
2. Create a game and wait for an opponent to join.
3. If playing on separate devices, provide the server's IP address to the opponent to join the game.
4. If playing on the same laptop, an IP address is not required.


1. To play the game on some devices, use the "option" key in combination with the arrow keys.
2. On most devices, you can simply use the arrow keys to play the game.