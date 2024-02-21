package model;

public class Books {
    private int BookID;
    private String BookName;
    private String Genre;
    private String ReleaseDate;

    public Books(int bookID, String bookName, String genre, String releaseDate) {
        BookID = bookID;
        BookName = bookName;
        Genre = genre;
        ReleaseDate = releaseDate;
    }

    public int getBookID() {
        return BookID;
    }

    public void setBookID(int bookID) {
        BookID = bookID;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getReleaseDate() {
        return ReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        ReleaseDate = releaseDate;
    }
}
