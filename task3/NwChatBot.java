package task3;

import java.util.Scanner;

public class NwChatBot {
    private ConfigManager configManager;
    private ApiClient apiClient;
    private Scanner scanner;

    // ANSI Color codes for better visual appeal
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String BLUE = "\u001B[34m";
    private static final String YELLOW = "\u001B[33m";
    private static final String CYAN = "\u001B[36m";
    private static final String BOLD = "\u001B[1m";

    public NwChatBot() {
        this.configManager = new ConfigManager();
        this.apiClient = new ApiClient(configManager.getServerUrl());
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        printWelcomeMessage();

        while (true) {
            System.out.print(CYAN + "\n" + configManager.getBotName() + " > " + RESET);
            String command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "create":
                case "c":
                case "new":
                    createBlogPost();
                    break;
                case "view":
                case "v":
                case "list":
                case "show":
                    viewBlogPosts();
                    break;
                case "stats":
                case "s":
                case "statistics":
                    viewStatistics();
                    break;
                case "exit":
                case "quit":
                case "q":
                    printGoodbyeMessage();
                    return;
                case "help":
                case "h":
                case "?":
                    showHelp();
                    break;
                case "clear":
                case "cls":
                    clearScreen();
                    break;
                default:
                    printErrorMessage("Unknown command: '" + command + "'");
                    System.out.println("💡 Type " + YELLOW + "help" + RESET + " to see available commands.");
            }
        }
    }

    private void printWelcomeMessage() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println(BOLD + BLUE + "🤖 Welcome to " + configManager.getBotName() + " - Your Blog Assistant!" + RESET);
        System.out.println("=".repeat(60));
        System.out.println(GREEN + "✨ Ready to help you manage your blog posts!" + RESET);
        System.out.println("\n📋 " + BOLD + "Quick Commands:" + RESET);
        System.out.println("   • " + YELLOW + "create" + RESET + " (or 'c') - Write a new blog post");
        System.out.println("   • " + YELLOW + "view" + RESET + " (or 'v') - Browse all posts");
        System.out.println("   • " + YELLOW + "stats" + RESET + " (or 's') - Check statistics");
        System.out.println("   • " + YELLOW + "help" + RESET + " (or 'h') - Get detailed help");
        System.out.println("   • " + YELLOW + "exit" + RESET + " (or 'q') - Quit application");
        System.out.println("=".repeat(60));
    }

    private void createBlogPost() {
        System.out.println("\n" + "═".repeat(50));
        System.out.println(BOLD + BLUE + "✏️  Creating New Blog Post" + RESET);
        System.out.println("═".repeat(50));

        System.out.print(CYAN + "📝 Blog Title: " + RESET);
        String title = scanner.nextLine().trim();

        if (title.isEmpty()) {
            printErrorMessage("Title cannot be empty!");
            return;
        }

        System.out.print(CYAN + "📄 Blog Content: " + RESET);
        String content = scanner.nextLine().trim();

        if (content.isEmpty()) {
            printErrorMessage("Content cannot be empty!");
            return;
        }

        System.out.print(CYAN + "👤 Author Name: " + RESET);
        String author = scanner.nextLine().trim();

        if (author.isEmpty()) {
            printErrorMessage("Author name cannot be empty!");
            return;
        }

        System.out.println("\n⏳ " + YELLOW + "Creating your blog post..." + RESET);

        try {
            String response = apiClient.createBlogPost(title, content, author);

            System.out.println("\n" + GREEN + "✅ SUCCESS!" + RESET);
            System.out.println("🎉 Your blog post has been created successfully!");

            System.out.println("\n" + BOLD + "📋 Post Details:" + RESET);
            System.out.println("   Title: " + BLUE + title + RESET);
            System.out.println("   Author: " + BLUE + author + RESET);
            System.out.println("   Content Length: " + BLUE + content.length() + " characters" + RESET);

            if (response.trim().startsWith("{") || response.trim().startsWith("[")) {
                System.out.println("\n" + BOLD + "🔧 Server Response:" + RESET);
                System.out.println(formatJsonResponse(response));
            }

        } catch (Exception e) {
            printErrorMessage("Failed to create blog post");
            System.out.println("❌ " + RED + "Error Details: " + e.getMessage() + RESET);
            System.out.println("💡 " + YELLOW + "Please check your internet connection and try again." + RESET);
        }
    }

    private void viewBlogPosts() {
        System.out.println("\n" + "═".repeat(50));
        System.out.println(BOLD + BLUE + "📚 All Blog Posts" + RESET);
        System.out.println("═".repeat(50));
        System.out.println("⏳ " + YELLOW + "Fetching blog posts..." + RESET);

        try {
            String response = apiClient.getAllBlogPosts();

            if (response.trim().isEmpty()) {
                System.out.println("\n" + YELLOW + "📭 No blog posts found." + RESET);
                System.out.println("💡 Use the '" + CYAN + "create" + RESET + "' command to write your first post!");
                return;
            }

            System.out.println("\n" + GREEN + "✅ Blog posts retrieved successfully!" + RESET);

            if (response.trim().startsWith("{") || response.trim().startsWith("[")) {
                System.out.println("\n" + BOLD + "📋 Posts Data:" + RESET);
                System.out.println(formatJsonResponse(response));
            } else {
                System.out.println("\n" + BOLD + "📋 Response:" + RESET);
                System.out.println(response);
            }

        } catch (Exception e) {
            printErrorMessage("Failed to retrieve blog posts");
            System.out.println("❌ " + RED + "Error Details: " + e.getMessage() + RESET);
            System.out.println("💡 " + YELLOW + "Please check your internet connection and server status." + RESET);
        }
    }

    private void viewStatistics() {
        System.out.println("\n" + "═".repeat(50));
        System.out.println(BOLD + BLUE + "📊 Website Statistics" + RESET);
        System.out.println("═".repeat(50));
        System.out.println("⏳ " + YELLOW + "Loading statistics..." + RESET);

        try {
            String response = apiClient.getStatistics();

            System.out.println("\n" + GREEN + "✅ Statistics loaded successfully!" + RESET);

            if (response.trim().startsWith("{")) {
                System.out.println("\n" + BOLD + "📈 Current Stats:" + RESET);
                System.out.println(formatJsonResponse(response));
            } else {
                System.out.println("\n" + BOLD + "📈 Statistics:" + RESET);
                System.out.println(response);
            }

        } catch (Exception e) {
            printErrorMessage("Failed to load statistics");
            System.out.println("❌ " + RED + "Error Details: " + e.getMessage() + RESET);
            System.out.println("💡 " + YELLOW + "The statistics service might be temporarily unavailable." + RESET);
        }
    }

    private void showHelp() {
        System.out.println("\n" + "═".repeat(60));
        System.out.println(BOLD + BLUE + "🆘 " + configManager.getBotName() + " Help Center" + RESET);
        System.out.println("═".repeat(60));

        System.out.println("\n" + BOLD + "📝 Blog Management Commands:" + RESET);
        System.out.println("   " + YELLOW + "create" + RESET + " (c, new)     - Create a new blog post");
        System.out.println("   " + YELLOW + "view" + RESET + " (v, list, show) - Display all blog posts");
        System.out.println("   " + YELLOW + "stats" + RESET + " (s, statistics) - Show website statistics");

        System.out.println("\n" + BOLD + "🔧 System Commands:" + RESET);
        System.out.println("   " + YELLOW + "help" + RESET + " (h, ?)        - Show this help message");
        System.out.println("   " + YELLOW + "clear" + RESET + " (cls)        - Clear the screen");
        System.out.println("   " + YELLOW + "exit" + RESET + " (quit, q)     - Exit the application");

        System.out.println("\n" + BOLD + "💡 Tips:" + RESET);
        System.out.println("   • You can use short commands (c, v, s, h, q) for faster navigation");
        System.out.println("   • All fields are required when creating a blog post");
        System.out.println("   • Check your internet connection if commands fail");

        System.out.println("\n" + BOLD + "🌐 Server URL: " + RESET + configManager.getServerUrl());
        System.out.println("═".repeat(60));
    }

    private void clearScreen() {
        // Clear screen for different operating systems
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[2J\033[H");
                System.out.flush();
            }
        } catch (Exception e) {
            // Fallback: print multiple newlines
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
        System.out.println(GREEN + "✨ Screen cleared!" + RESET);
    }

    private void printErrorMessage(String message) {
        System.out.println("\n" + RED + "❌ ERROR: " + message + RESET);
    }

    private void printGoodbyeMessage() {
        System.out.println("\n" + "═".repeat(50));
        System.out.println(BOLD + GREEN + "👋 Thank you for using " + configManager.getBotName() + "!" + RESET);
        System.out.println("🌟 " + CYAN + "Happy blogging! See you next time!" + RESET);
        System.out.println("═".repeat(50));
    }

    private String formatJsonResponse(String json) {
        // Simple JSON formatting for better readability
        String formatted = json.replace("{", "{\n  ")
                .replace("}", "\n}")
                .replace(",", ",\n  ")
                .replace(":", ": ");
        return BLUE + formatted + RESET;
    }

    public static void main(String[] args) {
        try {
            NwChatBot bot = new NwChatBot();
            bot.start();
        } catch (Exception e) {
            System.err.println("❌ Failed to start ChatBot: " + e.getMessage());
            e.printStackTrace();
        }
    }
}