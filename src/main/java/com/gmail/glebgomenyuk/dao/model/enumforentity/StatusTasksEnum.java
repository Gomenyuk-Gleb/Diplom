package com.gmail.glebgomenyuk.dao.model.enumforentity;

public enum  StatusTasksEnum {
    DONE, WAIT, DELIVERED, SIMPLIFIED;

    @Override
    public String toString() {
        return "Status " + name();
    }
}
