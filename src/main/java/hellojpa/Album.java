package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;

// Item을 상속하여 상속관계를 매핑함
@Entity
public class Album extends Item{

    @Column(name = "ARTIST")
    private String artist;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
