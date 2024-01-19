package org.launchcode.taskcrusher.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    private String rewardName;
    @Column(name="reward_des")
    private String rewardDescription;
    @Column(name="reward_points")
    private int points;

    public Reward() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRewardName() {
        return rewardName;
    }

    public void setRewardName(String rewardName) {
        this.rewardName = rewardName;
    }

    public String getRewardDescription() {
        return rewardDescription;
    }

    public void setRewardDescription(String rewardDescription) {
        this.rewardDescription = rewardDescription;
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
                ", rewardName='" + rewardName + '\'' +
                ", rewardDescription='" + rewardDescription + '\'' +
                ", points=" + points +
                '}';
    }
}
