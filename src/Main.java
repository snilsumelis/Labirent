public class Main {
    public static void main(String[] args) {
        HW3 hw3 = new HW3();
        hw3.read_file("C:\\Users\\snils\\IdeaProjects\\File_path\\src\\HW3Q1.txt");
        String path = hw3.find_path();
        hw3.print_path(path);
        hw3.print_path_to_file("C:\\Users\\snils\\IdeaProjects\\File_path\\src\\path.txt");
    }
}