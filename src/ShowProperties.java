    //    System.getProperty("os.name")

class ShowProperties {

    public static void main(String[] args) {
        boolean is64bit = false;
        if (System.getProperty("os.name").contains("Windows")) {
            is64bit = (System.getenv("ProgramFiles(x86)") != null);
        } else {
            is64bit = (System.getProperty("os.arch").indexOf("64") != -1);
        }
        System.out.println(is64bit);
    }
}