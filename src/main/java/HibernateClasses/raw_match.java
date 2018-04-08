package HibernateClasses;

import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;

@Entity
@Table(name = "raw_match")
public class raw_match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String matchId;
    private String data;

    public raw_match() {
    }

    /*public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String firstName) {
        this.matchId = matchId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Team movement [id=" + this.id + ", match Id=" + this.getMatchId() + ", action= " + data + "]";
    }
}
