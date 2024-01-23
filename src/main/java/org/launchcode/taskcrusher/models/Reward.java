package org.launchcode.taskcrusher.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
@Entity
@Table(name = "rewards")
public class Reward {

    @Id
    @GeneratedValue
    @Column(name="reward_id")
    private int id;


    @Size(max = 50)
    @Column(name="reward_name")
    private String name;
    @Column(name="reward_des")
    private String description;
    @Column(name="reward_points")
    private int points;

    public Reward() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Reward{" +
                "id=" + id +
                ", Name='" + name + '\'' +
                ", Description='" + description + '\'' +
                ", points=" + points +
                '}';
    }
}
