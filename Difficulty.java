public enum Difficulty {

    EASY("Easy", "Increased player stats for comfort."),
    NORMAL("Normal", "Balanced stats for a standard experience."),
    HARD("Hard", "Reduced player stats for a challenging experience."),
    ENDLESS("Endless", "Infinite progression with unlimited stat growth.");

    private final String difficultyLevel;
    private final String difficultyDetails;

    Difficulty(String difficultyLevel, String difficultyDetails) {
        this.difficultyLevel = difficultyLevel;
        this.difficultyDetails = difficultyDetails;
    }

    public String getDifficultyLevelName() {
        return difficultyLevel;
    }
    public String getDifficultyDetails() {
        return difficultyDetails;
    }

    public static Difficulty getDifficultyLevelFromInput(int input) {
        return Difficulty.values()[input - 1];
    }

    public static Scene getSceneDifficulty(Difficulty difficulty, String playerName) {
        switch(difficulty) {
            case EASY -> {
                return new Scene(playerName, 150, 20, 200, 20, 80, 10);
            }
            case NORMAL -> {
                return new Scene(playerName, 100, 15, 100, 10, 50, 20);
            }
            case HARD -> {
                return new Scene(playerName, 80, 10, 80, 5, 40, 30);
            }
            case ENDLESS -> {
                return new Scene(playerName, 80, 10, 80, 5, 30, Integer.MAX_VALUE);  
            }
        }
        // Defaults to NORMAL
        return new Scene(playerName, 100, 10, 100, 10, 20, 20);
    }

}