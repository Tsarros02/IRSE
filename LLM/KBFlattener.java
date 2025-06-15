import java.io.File;
public class KBFlattener {
    static File parentPath = new File("C:/Users/mikec/Documents/kb_data");
    public static void main(String[] args){
        if (!parentPath.isDirectory()){
            System.err.println("Invalid parent directory");
            return;
        }
        File[] subdirs = parentPath.listFiles(File::isDirectory);
        if (subdirs==null) return;
        for (File subdir : subdirs){
            File[] files = subdir.listFiles();
            if (files != null){
                for (File file : files){
                    addTxt(file);
                }
            }
            deleteDirectory(subdir);
        }
        System.out.println("Flattening complete, documents can be inserted in the Knowledge Base");
    }

    static void addTxt(File file){
        if (file.isFile() && file.getName().endsWith(".txt")) {
            file.renameTo(new File(parentPath+"/"+file.getName()));
        }
    }
    //delete empty directories
    static void deleteDirectory(File dir){
        File[] contents = dir.listFiles();
        if (contents!=null && contents.length == 0) dir.delete();     
    }
}
