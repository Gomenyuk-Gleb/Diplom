package com.gmail.glebgomenyuk.facade;

import com.gmail.glebgomenyuk.dao.model.DeskEntity;
import com.gmail.glebgomenyuk.dao.model.TasksEntity;
import com.gmail.glebgomenyuk.dao.model.enumforentity.StatusTasksEnum;
import sun.security.krb5.internal.crypto.Des;

import java.util.ArrayList;
import java.util.List;

public class AddElementsForStart {


    public List<DeskEntity> addDesks(){
        List<DeskEntity> deskEntityList = new ArrayList<>();
        DeskEntity deskEntityFirst = new DeskEntity("First");
        DeskEntity deskEntitySecond = new DeskEntity("Second");
        deskEntityList.add(deskEntityFirst);
        deskEntityList.add(deskEntitySecond);
        return deskEntityList;
    }

    public List<TasksEntity> addTasks(){
        List<TasksEntity> tasksEntities = new ArrayList<>();
        TasksEntity tasksEntityFirst = new TasksEntity("First");
        TasksEntity tasksEntitySecon = new TasksEntity("Second");
        tasksEntityFirst.setStatusTask(StatusTasksEnum.WAIT);
        tasksEntities.add(tasksEntityFirst);
        tasksEntities.add(tasksEntitySecon);
        return tasksEntities;
    }
}
