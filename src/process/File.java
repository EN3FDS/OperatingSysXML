package process;

import java.util.ArrayList;



public class File {
	private File fichierParent;
	private final String path;
	protected String name;
	protected boolean directory = false;
	private ArrayList<File> listFichiers = new ArrayList<>();
	protected ArrayList<Integer> listBlock = new ArrayList<>();
	
	
	public File(File parent,String name,boolean directory ){
		this.fichierParent = parent;
		this.name = name;
		this.directory = directory;
		if(parent !=null)
			path = parent.getPath()+"/"+this.name;
		else
			path=this.name;
	}
	public boolean isDirectory() {
		return this.directory;
	}
	public String getName() {
		return name;
	}

	public ArrayList<Integer> getListBlock() {
		return listBlock;
	}
	public void setListBlock(ArrayList<Integer> listBlock) {
		this.listBlock = listBlock;
	}
	public int getSIZE_B() {
		int size = 0;
		for(File fichier : listFichiers){
			size+= fichier.getSIZE_B();
		}
		return size;
	}
	public String getPath() {
		return path;
	}
	public File getFichierParent() {
		return fichierParent;
	}
	public String toString() {
		return this.name;
	}


}
