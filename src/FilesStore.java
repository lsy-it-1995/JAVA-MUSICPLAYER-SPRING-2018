import java.io.File;
import java.util.ArrayList;

public class FilesStore {
	
	public ArrayList<File> fileContainer;
	public int indexOfFile;
	
	public FilesStore() 
	{
		indexOfFile = 0;
		fileContainer = new ArrayList<>();
	}
	public void add(File file)
	{
		fileContainer.add(file);
	}
	
	public String getFileAddress()
	{
		return fileContainer.get(indexOfFile).getAbsolutePath();
	}
	public String nextFile()
	{
		indexOfFile++;
		checkindex();
		return fileContainer.get(indexOfFile).getAbsolutePath();
	}
	public int currentIndex()
	{
		return indexOfFile;
	}
	
	public int size()
	{
		return fileContainer.size();
	}
	public String previousFile()
	{
		indexOfFile--;
		checkindex();
		return fileContainer.get(indexOfFile).getAbsolutePath();

	}
	
	private void checkindex()
	{
		if(indexOfFile==fileContainer.size())
			indexOfFile=0;
		if(indexOfFile==-1)
			indexOfFile=fileContainer.size()-1;
	}
	
}
