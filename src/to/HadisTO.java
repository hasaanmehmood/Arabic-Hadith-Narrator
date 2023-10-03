package to;

public class HadisTO {
	private int uid;
	private String hadith;
    private String book;
    private int num_hadith;
    private String matn;
    private String sanad;
    private int sanad_length;
    
    public int getUid() {
    	return uid;
    }
    
    public void setUid(int Id) {
        this.uid = Id;
    }
    
    public String getHadith() {
        return hadith;
    }
    
    public void setHadith(String hadith) {
        this.hadith = hadith;
    }
    
    
    public String getBook() {
        return book;
    }
    
    public void setBook(String book) {
        this.book = book;
    }
    
    
    public int getNumHadith() {
        return num_hadith;
    }
    
    public void setNumHadith(int num_hadith) {
        this.num_hadith = num_hadith;
    }
    
    
    public String getMatn() {
        return matn;
    }
    
    public void setMatn(String matn) {
        this.matn = matn;
    }
    
    
    public String getSanad() {
        return sanad;
    }
    
    public void setSanad(String sanad) {
        this.sanad = sanad;
    }
    
    
    public int getSanadLength() {
        return sanad_length;
    }
    
    public void setSanadLength(int sanad_length) {
        this.sanad_length = sanad_length;
    }
}
