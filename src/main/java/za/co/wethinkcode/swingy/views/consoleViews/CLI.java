package za.co.wethinkcode.swingy.views.consoleViews;


public class CLI implements IUserInterface {
    private final ISplashScreen splashScreen;

    public CLI() {
        splashScreen = new SplashScreenCli();
    }

    public void displayPlayerSelectionView()
    {
        clearScreen();
        String input;
        System.out.print("" +
                "Do you want to create a new player or play with a saved player?\n" +
                "1. New player\n" +
                "2. Saved player\n" +
                "3. Quit\n" +
                "\nInput: ");
    }
    public void displayHeroTypePrompt() {
        clearScreen();
        System.out.print("" +
                "Select Hero \n" +
                "1. Black Panther\n" +
                "2. Dick Milaje\n" +
                "3. Pussy\n\n" +
                "4. Back To Home\n" +
                "\nInput : ");
    }

    public void promptPlayerName() {
        clearScreen();
        printToScreen("What is your name?");
    }

    public void displayInvalidInput() {
        System.out.println("Invalid input bro");
    }


    private void printMap(final Arena arena) {
        for (int y = 0; y < arena.getMap().getSize(); y++) {
            for (int x = 0; x < arena.getMap().getSize(); x++) {
                Position position = new Position(y, x);
                if (arena.isPlayerInABattle() && position.equals(arena.getHero().getPosition()))
                    System.out.print("|*");
                else if (arena.getMap().getGameMap().containsKey(position)) {
                    APlayer player = arena.getMap().getGameMap().get(position);
                    System.out.print(player.getType().equals("Hero") ? "|0" : "|X");
                } else
                    System.out.print("| ");
            }
            System.out.print("|\n");
        }
    }

    private void displayOptions() {
        System.out.println(
                "*******************************************" +
                "* Direction         Game Options          *\n" +
                "*                                         *\n"+
                "* W - NORTH         C - Save and continue *\n" +
                "* A - WEST          Z - Save and exit     *\n" +
                "* S - SOUTH         X - Switch to gui     *\n" +
                "* D - EAST                                *\n" +
                "*******************************************");
    }


    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        printHeader();
    }

     private void printHeader() {
        printToScreen(START_DIVIDER);
        printStringToCenter(APPLICATION_HEARDER);
        printStringToCenter(APPLICATION_SLOGAN);
        printToScreen(START_DIVIDER);
     }

    private void printToScreen(String string){
        System.out.println(string);
    }

    private void printStringToCenter(String string) {
        int centerPaddingSpace =
                getPaddingCenterAdjust(string.length());
        System.out.format("%"+centerPaddingSpace +"s\n", string);
    }

     private int getPaddingCenterAdjust(int stringLength)
     {
         int padSize = 100 - stringLength;
         return (padSize / 2) + stringLength;
     }

    public void promptNewGame(boolean heroWon) {
        String request = heroWon? "Start a new game" : "try again";
        System.out.print("" +
                "Do you want to "+request + " with the same hero?" +
                "\n1. Yes" +
                "\n2. Back to Main Menu" +
                "\n3. Quit\n");
        displayPromptInput();
    }

    public void printResultsMessage(Arena arena) {
        clearScreen();
        printToScreen(arena.getGameResults().getResult().toString());
    }

    @Override
    public void show(Arena arena) {

    }

    @Override
    public void showSplashScreen() {

    }

    public void displayPromptInput() {
        System.out.print("\nInput : ");
    }

    public void showQuitDialogue() {
        clearScreen();
        System.out.print("" +
                "Are you sure you want to quit Game?\n" +
                "1. Yep\n" +
                "2. Nope\n");
        displayPromptInput();
    }

    @Override
    public void newGameDialogue() {

    }

    public void displayHeroList(Collection<Hero> allHeroes) {
        clearScreen();
        int count = 1;
        printStringToCenter("*************************************");
        printStringToCenter("*     Heroes from the database      *");
        printStringToCenter("*************************************");
        System.out.format("%-20s%-20s%-20s%-20s%-20s\n", "Index", "Name", "Hero Class Type", "Level", "Xp");
        System.out.format("_____________________________________________________________________________________\n");

        for (Hero hero: allHeroes) {
            System.out.format("%-20d%-20s%-20s%-20d%-20d\n", count++, hero.getName(), hero.getType(), hero.getLevel(), hero.getExperience());
        }
        printToScreen("\nB . Back To Main Menu\n 2 q - Quit");
        printToScreen("Choose an existing hero: ");
    }

    public void displaySplaceScreen(
            {
        clearScreen();
        splashScreen.show();
    }

    public void promptAnyKeyPress() {
        printToScreen("Press any key to continue...");
    }
}