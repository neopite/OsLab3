package com.company.neophite;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Process {
    private long id;
    private int execTime;
    private int waitTime;

    public Process(long id, int execTime) {
        this.id = id;
        this.execTime = execTime;
    }

    public Process(long id, int execTime, int waitTime) {
        this.id = id;
        this.execTime = execTime;
        this.waitTime = waitTime;
    }




    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getExecTime() {
        return execTime;
    }

    public void setExecTime(int execTime) {
        this.execTime = execTime;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    @Override
    public String toString() {
        return
                "ID : " + id +
                "  EXEC TIME : " + execTime +
                "  WAITING : " + waitTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Process process = (Process) o;
        return execTime == process.execTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(execTime);
    }
}
