package pl.example.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by PC on 2017-06-22.
 */

@Entity
public class GardenCareToDo implements Comparable<GardenCareToDo> {

    @Id
    @GeneratedValue
    private Long id;

    private String task;
    private Boolean done;
    private Integer monthOfActivity;

    @ManyToOne
    private User user;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public Integer getMonthOfActivity() {
        return monthOfActivity;
    }

    public void setMonthOfActivity(Integer monthOfActivity) {
        this.monthOfActivity = monthOfActivity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int compareTo(GardenCareToDo gardenCareToDo) {

        int isDone = this.done.compareTo(gardenCareToDo.done);
        if (isDone == 0) {
            int monthOfActivity = this.monthOfActivity.compareTo(gardenCareToDo.monthOfActivity);
            if (monthOfActivity == 0) {
                return this.id.compareTo(gardenCareToDo.id);
            } else {
                return monthOfActivity;
            }
        } else {
            return isDone;
        }
    }

}
